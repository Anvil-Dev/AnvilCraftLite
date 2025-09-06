package dev.anvilcraft.lite.api.extension;

public interface IItemEntityExtension {
    default void anvilcraftLite$setIsAdsorbable(boolean value) {
        throw new AssertionError();
    }

    default boolean anvilcraftLite$isAdsorbable() {
        throw new AssertionError();
    }
}
