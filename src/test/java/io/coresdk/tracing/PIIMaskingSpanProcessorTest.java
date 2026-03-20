package io.coresdk.tracing;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.TraceFlags;
import io.opentelemetry.api.trace.TraceState;
import io.opentelemetry.sdk.testing.trace.TestSpanData;
import io.opentelemetry.sdk.trace.ReadWriteSpan;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.data.StatusData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PIIMaskingSpanProcessorTest {

    private PIIMaskingSpanProcessor processor;

    @BeforeEach
    void setUp() {
        processor = PIIMaskingSpanProcessor.create();
    }

    @Test
    void redactsSSN() {
        ReadWriteSpan span = mockSpanWithAttribute("user.info", "SSN is 123-45-6789 please");
        processor.onEnd(span);
        verifyAttributeSet(span, "user.info", "[REDACTED] please");
    }

    @Test
    void redactsEmail() {
        ReadWriteSpan span = mockSpanWithAttribute("message", "Contact alice@example.com for help");
        processor.onEnd(span);
        verifyAttributeSet(span, "message", "Contact [REDACTED] for help");
    }

    @Test
    void redactsSensitiveKeyName() {
        ReadWriteSpan span = mockSpanWithAttribute("password", "s3cr3tP@ss!");
        processor.onEnd(span);
        verifyAttributeSet(span, "password", "[REDACTED]");
    }

    @Test
    void redactsApiKey() {
        ReadWriteSpan span = mockSpanWithAttribute("api_key", "sk-prod-abc123");
        processor.onEnd(span);
        verifyAttributeSet(span, "api_key", "[REDACTED]");
    }

    @Test
    void doesNotModifyCleanAttribute() {
        ReadWriteSpan span = mockSpanWithAttribute("http.method", "GET");
        processor.onEnd(span);
        // setAttribute should NOT be called for clean values
        verify(span, never()).setAttribute(any(AttributeKey.class), any());
    }

    @Test
    void isEndRequiredReturnsTrue() {
        assertTrue(processor.isEndRequired());
    }

    @Test
    void isStartRequiredReturnsFalse() {
        assertFalse(processor.isStartRequired());
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private ReadWriteSpan mockSpanWithAttribute(String key, String value) {
        ReadWriteSpan span = mock(ReadWriteSpan.class);
        Attributes attrs = Attributes.of(AttributeKey.stringKey(key), value);
        SpanData spanData = TestSpanData.builder()
                .setName("test-span")
                .setKind(SpanKind.INTERNAL)
                .setSpanContext(SpanContext.create(
                        "00000000000000000000000000000001",
                        "0000000000000001",
                        TraceFlags.getDefault(),
                        TraceState.getDefault()))
                .setStartEpochNanos(0)
                .setEndEpochNanos(1000)
                .setAttributes(attrs)
                .setHasEnded(true)
                .setStatus(StatusData.ok())
                .setTotalAttributeCount(1)
                .setTotalRecordedEvents(0)
                .setTotalRecordedLinks(0)
                .build();
        when(span.toSpanData()).thenReturn(spanData);
        return span;
    }

    @SuppressWarnings("unchecked")
    private void verifyAttributeSet(ReadWriteSpan span, String key, String expectedValue) {
        ArgumentCaptor<AttributeKey> keyCaptor = ArgumentCaptor.forClass(AttributeKey.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        verify(span, atLeastOnce()).setAttribute(keyCaptor.capture(), valueCaptor.capture());

        boolean found = false;
        var keys = keyCaptor.getAllValues();
        var values = valueCaptor.getAllValues();
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).getKey().equals(key)) {
                assertEquals(expectedValue, values.get(i));
                found = true;
                break;
            }
        }
        assertTrue(found, "Expected setAttribute called for key: " + key);
    }
}
