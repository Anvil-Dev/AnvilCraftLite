package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.item.property.component.SavedEntity;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModComponents {
    public static final DeferredRegister<DataComponentType<?>> DR = DeferredRegister.create(
        Registries.DATA_COMPONENT_TYPE,
        AnvilCraftLite.MOD_ID
    );

    public static final DataComponentType<SavedEntity> SAVED_ENTITY = register(
        "saved_entity",
        b -> b.persistent(SavedEntity.CODEC).networkSynchronized(SavedEntity.STREAM_CODEC)
    );

    @SuppressWarnings("SameParameterValue")
    private static <T> @NotNull DataComponentType<T> register(String name, Consumer<DataComponentType.Builder<T>> customizer) {
        var builder = DataComponentType.<T>builder();
        customizer.accept(builder);
        var componentType = builder.build();
        DR.register(name, () -> componentType);
        return componentType;
    }
}
