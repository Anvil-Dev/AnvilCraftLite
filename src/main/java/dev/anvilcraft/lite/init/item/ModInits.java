package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.init.block.ModBlocks;
import dev.anvilcraft.lite.init.reicpe.ModRecipeInits;
import net.neoforged.bus.api.IEventBus;

public class ModInits {
    public static void init(IEventBus modEventBus) {
        ModBlocks.init();
        ModComponents.init();
        ModItems.init();
        ModItemGroups.init();
        ModRecipeInits.init(modEventBus);
    }
}
