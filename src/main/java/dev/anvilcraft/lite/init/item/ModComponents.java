package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.item.property.component.SavedEntity;
import net.minecraft.core.component.DataComponentType;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTER;

public class ModComponents {
    public static final DataComponentType<SavedEntity> SAVED_ENTITY = REGISTER.componentType(
        "saved_entity",
        b -> b.persistent(SavedEntity.CODEC).networkSynchronized(SavedEntity.STREAM_CODEC)
    );

    public static void init() {
    }
}
