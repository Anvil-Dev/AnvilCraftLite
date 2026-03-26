package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCrushRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockCrushRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.COBBLESTONE, Blocks.GRAVEL);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.GRAVEL, Blocks.SAND);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.POLISHED_GRANITE, Blocks.GRANITE);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.GRANITE, Blocks.RED_SAND);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.POLISHED_DIORITE, Blocks.DIORITE);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        BlockCrushRecipeLoader.blockCrush(provider, Blocks.SOUL_SOIL, Blocks.SOUL_SAND);
    }

    private static void blockCrush(RegistrumRecipeProvider provider, Block input, Block result) {
        BlockCrushRecipe.builder(provider.getRegistries().lookupOrThrow(Registries.BLOCK))
            .input(input)
            .result(result)
            .save(provider.getOutput());
    }
}
