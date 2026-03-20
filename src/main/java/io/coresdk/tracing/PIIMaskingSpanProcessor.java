package io.coresdk.tracing;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.context.Context;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.ReadWriteSpan;
import io.opentelemetry.sdk.trace.ReadableSpan;
import io.opentelemetry.sdk.trace.SpanProcessor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * OTel SpanProcessor that redacts PII from span attributes before they reach the export queue.
 *
 * <p>Fires on {@code onEnd} (not {@code onStart}) so that any attributes added during span
 * execution are also covered. Redaction is in-place via {@link ReadWriteSpan#setAttribute}.
 *
 * <p>Patterns detected:
 * <ul>
 *   <li>Email addresses</li>
 *   <li>US Social Security Numbers (XXX-XX-XXXX)</li>
 *   <li>Credit card numbers (13–16 digits, space/dash separated)</li>
 * </ul>
 *
 * <p>Attribute keys on a blocklist (case-insensitive) are fully redacted regardless of value:
 * {@code password, token, secret, api_key, apikey, authorization, access_token, refresh_token,
 * private_key, credentials}.
 */
public final class PIIMaskingSpanProcessor implements SpanProcessor {

    public static final String REDACTED = "[REDACTED]";

    private static final Pattern EMAIL =
            Pattern.compile("[\\w.+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}");
    private static final Pattern SSN =
            Pattern.compile("\\b\\d{3}-\\d{2}-\\d{4}\\b");
    private static final Pattern CREDIT_CARD =
            Pattern.compile("\\b(?:\\d[ \\-]?){13,16}\\d\\b");

    private static final Set<String> SENSITIVE_KEYS = new HashSet<>(Arrays.asList(
            "password", "token", "secret", "api_key", "apikey",
            "authorization", "access_token", "refresh_token",
            "private_key", "credentials"
    ));

    private PIIMaskingSpanProcessor() {}

    /** Factory method — preferred over direct construction. */
    public static PIIMaskingSpanProcessor create() {
        return new PIIMaskingSpanProcessor();
    }

    @Override
    public void onStart(Context parentContext, ReadWriteSpan span) {
        // no-op: attributes may not be fully populated at start
    }

    @Override
    public boolean isStartRequired() {
        return false;
    }

    @Override
    public void onEnd(ReadWriteSpan span) {
        span.toSpanData().getAttributes().forEach((key, value) -> {
            if (key.getType() == io.opentelemetry.api.common.AttributeType.STRING) {
                @SuppressWarnings("unchecked")
                AttributeKey<String> strKey = (AttributeKey<String>) key;
                String strValue = (String) value;
                String masked = maskValue(strKey.getKey(), strValue);
                if (!masked.equals(strValue)) {
                    span.setAttribute(strKey, masked);
                }
            }
        });
    }

    @Override
    public boolean isEndRequired() {
        return true;
    }

    @Override
    public CompletableResultCode shutdown() {
        return CompletableResultCode.ofSuccess();
    }

    @Override
    public CompletableResultCode forceFlush() {
        return CompletableResultCode.ofSuccess();
    }

    private String maskValue(String key, String value) {
        if (value == null) return null;
        // Blocklisted key name → redact entire value
        if (SENSITIVE_KEYS.contains(key.toLowerCase())) {
            return REDACTED;
        }
        // Pattern-based redaction
        String masked = EMAIL.matcher(value).replaceAll(REDACTED);
        masked = SSN.matcher(masked).replaceAll(REDACTED);
        masked = CREDIT_CARD.matcher(masked).replaceAll(REDACTED);
        return masked;
    }
}
