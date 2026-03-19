package io.coresdk;

public final class ClaimsContext {
    private static final ThreadLocal<Claims> HOLDER = new ThreadLocal<>();

    private ClaimsContext() {}

    public static void set(Claims claims) { HOLDER.set(claims); }
    public static Claims get() { return HOLDER.get(); }
    public static void clear() { HOLDER.remove(); }
}
