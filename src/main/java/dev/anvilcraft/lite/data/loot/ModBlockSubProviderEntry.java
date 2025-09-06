package dev.anvilcraft.lite.data.loot;

import dev.anvilcraft.lite.init.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockSubProviderEntry extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    protected ModBlockSubProviderEntry(HolderLookup.Provider registries) {
        super(ModBlockSubProviderEntry.EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.RESIN_BLOCK.get());
        this.dropSelf(ModBlocks.MAGNET_BLOCK.get());
        this.dropSelf(ModBlocks.HOLLOW_MAGNET_BLOCK.get());
        this.dropSelf(ModBlocks.FERRITE_CORE_MAGNET_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Set.of(
            ModBlocks.RESIN_BLOCK.get(),
            ModBlocks.MAGNET_BLOCK.get(),
            ModBlocks.HOLLOW_MAGNET_BLOCK.get(),
            ModBlocks.FERRITE_CORE_MAGNET_BLOCK.get()
        );
    }
}
