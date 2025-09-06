package dev.anvilcraft.lite.util.tag;

import dev.anvilcraft.lite.util.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class BetterTagBuilder extends TagBuilder {
    public BetterTagBuilder addTag(TagKey<Item> key) {
        return Util.cast(this.addTag(key.location()));
    }

    public BetterTagBuilder addOptionalTag(TagKey<Item> key) {
        return Util.cast(this.addOptionalTag(key.location()));
    }

    public BetterTagBuilder addElement(Item key) {
        return Util.cast(this.addElement(BuiltInRegistries.ITEM.getKey(key)));
    }

    public BetterTagBuilder addElement(Supplier<Item> key) {
        return this.addElement(key.get());
    }

    public BetterTagBuilder addOptionalElement(Item key) {
        return Util.cast(this.addOptionalElement(BuiltInRegistries.ITEM.getKey(key)));
    }

    public BetterTagBuilder addOptionalElement(Supplier<Item> key) {
        return this.addOptionalElement(key.get());
    }
}
