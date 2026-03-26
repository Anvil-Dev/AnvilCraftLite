package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.item.property.component.SavedEntity;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModComponents {
    public static final DeferredRegister.DataComponents DF = DeferredRegister.createDataComponents(
        Registries.DATA_COMPONENT_TYPE,
        AnvilCraftLite.MOD_ID
    );

    public static final DataComponentType<SavedEntity> SAVED_ENTITY = DataComponentType.<SavedEntity>builder()
        .persistent(SavedEntity.CODEC)
        .networkSynchronized(SavedEntity.STREAM_CODEC)
        .build();

    public static void init() {
        DF.register(
            "saved_entity",
            _ -> SAVED_ENTITY
        );
    }
}
