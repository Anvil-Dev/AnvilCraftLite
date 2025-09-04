package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCrushRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockCrushRecipeLoader {
    public static void init(RecipeOutput provider) {
        blockCrush(provider, Blocks.COBBLESTONE, Blocks.GRAVEL);
        blockCrush(provider, Blocks.GRAVEL, Blocks.SAND);
        blockCrush(provider, Blocks.POLISHED_GRANITE, Blocks.GRANITE);
        blockCrush(provider, Blocks.GRANITE, Blocks.RED_SAND);
        blockCrush(provider, Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);
        blockCrush(provider, Blocks.POLISHED_DIORITE, Blocks.DIORITE);
        blockCrush(provider, Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS);
        blockCrush(provider, Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        blockCrush(provider, Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS);
        blockCrush(provider, Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES);
        blockCrush(provider, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        blockCrush(provider, Blocks.SOUL_SOIL, Blocks.SOUL_SAND);
    }

    private static void blockCrush(RecipeOutput provider, Block input, Block result) {
        BlockCrushRecipe.builder().input(input).result(result).save(provider);
    }
}
