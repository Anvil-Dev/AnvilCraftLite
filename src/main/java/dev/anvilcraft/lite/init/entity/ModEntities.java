package dev.anvilcraft.lite.init.entity;

import dev.anvilcraft.lib.v2.registrum.util.entry.EntityEntry;
import dev.anvilcraft.lite.entity.MagnetizedNodeEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTRUM;

public class ModEntities {
    public static final EntityEntry<MagnetizedNodeEntity> MAGNETIZED_NODE = REGISTRUM.<MagnetizedNodeEntity>entity(
        "magnetized_node",
        MagnetizedNodeEntity::new,
        MobCategory.MISC
    ).loot((tables, entityType) -> {
        tables.add(entityType, new LootTable.Builder());
    }).register();

    public static void init() {
    }
}
