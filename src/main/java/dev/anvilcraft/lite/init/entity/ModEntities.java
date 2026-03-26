package dev.anvilcraft.lite.init.entity;

import dev.anvilcraft.lite.entity.MagnetizedNodeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTRUM;

public class ModEntities {
    public static final DeferredHolder<EntityType<?>, EntityType<MagnetizedNodeEntity>> MAGNETIZED_NODE = REGISTRUM.<MagnetizedNodeEntity>entity(
        "magnetized_node",
        MagnetizedNodeEntity::new,
        MobCategory.MISC
    ).register();

    public static void init() {
    }
}
