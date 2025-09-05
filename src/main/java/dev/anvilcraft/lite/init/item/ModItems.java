package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.block.ModBlocks;
import dev.anvilcraft.lite.item.HasMobBlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items DR = DeferredRegister.createItems(AnvilCraftLite.MOD_ID);

    public static final DeferredItem<Item> RESIN = DR.register("resin", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MAGNET_INGOT = DR.register("magnet_ingot", () -> new Item(new Item.Properties()));

    public static final DeferredItem<HasMobBlockItem> RESIN_BLOCK = DR.register(
        "resin_block",
        () -> new HasMobBlockItem(ModBlocks.RESIN_BLOCK.get(), new Item.Properties())
    );
}
