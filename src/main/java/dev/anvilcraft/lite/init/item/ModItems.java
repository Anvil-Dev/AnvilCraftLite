package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.init.block.ModBlocks;
import dev.anvilcraft.lite.item.MagnetItem;
import dev.anvilcraft.lite.item.ResinBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTER;

public class ModItems {
    public static final DeferredItem<Item> RESIN = REGISTER.item("resin", Item::new).register();

    public static final DeferredItem<MagnetItem> MAGNET_INGOT = REGISTER.item("magnet_ingot", MagnetItem::new).register();

    public static final DeferredItem<ResinBlockItem> RESIN_BLOCK = REGISTER.blockItem(ModBlocks.RESIN_BLOCK, ResinBlockItem::new);

    public static final DeferredItem<BlockItem> MAGNET_BLOCK = REGISTER.simpleBlockItem(ModBlocks.MAGNET_BLOCK);

    public static final DeferredItem<BlockItem> HOLLOW_MAGNET_BLOCK = REGISTER.simpleBlockItem(ModBlocks.HOLLOW_MAGNET_BLOCK);

    public static final DeferredItem<BlockItem> FERRITE_CORE_MAGNET_BLOCK = REGISTER.simpleBlockItem(ModBlocks.FERRITE_CORE_MAGNET_BLOCK);

    public static void init() {
    }
}
