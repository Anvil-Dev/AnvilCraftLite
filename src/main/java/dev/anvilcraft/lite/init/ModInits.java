package dev.anvilcraft.lite.init;

import dev.anvilcraft.lite.init.block.ModBlocks;
import dev.anvilcraft.lite.init.entity.ModEntities;
import dev.anvilcraft.lite.init.item.ModComponents;
import dev.anvilcraft.lite.init.item.ModItemGroups;
import dev.anvilcraft.lite.init.item.ModItems;
import dev.anvilcraft.lite.init.reicpe.ModRecipeInits;
import net.neoforged.bus.api.IEventBus;

public class ModInits {
    public static void init(IEventBus modEventBus) {
        ModItemGroups.init();
        ModBlocks.init();
        ModComponents.init();
        ModItems.init();
        ModRecipeInits.init(modEventBus);
        ModEntities.init();
    }
}
