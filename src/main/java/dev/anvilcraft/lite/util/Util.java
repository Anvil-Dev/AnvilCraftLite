package dev.anvilcraft.lite.util;

import org.jetbrains.annotations.NotNull;

public class Util {
    public static <T> T cast(@NotNull Object o) {
        //noinspection unchecked
        return (T) o;
    }
}
