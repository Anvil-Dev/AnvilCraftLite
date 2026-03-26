package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lib.v2.registrum.util.entry.ItemEntry;
import dev.anvilcraft.lite.item.CrabClawItem;
import dev.anvilcraft.lite.item.MagnetItem;
import net.minecraft.world.item.Item;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTRUM;

public class ModItems {
    static {
        REGISTRUM.defaultCreativeTab(ModItemGroups.ANVILCRAFT_LITE.getKey());
    }

    public static final ItemEntry<Item> RESIN = REGISTRUM.item("resin", Item::new).register();

    public static final ItemEntry<MagnetItem> MAGNET_INGOT = REGISTRUM.item("magnet_ingot", MagnetItem::new).register();

    public static final ItemEntry<CrabClawItem> CRAB_CLAW = REGISTRUM.item("crab_claw", CrabClawItem::new)
        .model(() -> (ctx, generator) -> generator.createWithExistingModel(ctx.get(), generator.modLoc(ctx.getName())))
        .register();

    public static void init() {
    }
}
