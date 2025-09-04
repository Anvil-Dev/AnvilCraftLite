package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCompressRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockCompressRecipeLoader {
    public static void init(RecipeOutput provider) {
        blockCompress(provider, Blocks.STONE, Blocks.STONE, Blocks.DEEPSLATE);
        blockCompress(provider, Blocks.ICE, Blocks.ICE, Blocks.PACKED_ICE);
        blockCompress(provider, Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE);
        blockCompress(provider, Blocks.NETHER_WART_BLOCK, Blocks.NETHERRACK, Blocks.CRIMSON_NYLIUM);
        blockCompress(provider, Blocks.WARPED_WART_BLOCK, Blocks.NETHERRACK, Blocks.WARPED_NYLIUM);
        blockCompress(provider, Blocks.BASALT, Blocks.BASALT, Blocks.BLACKSTONE);
        blockCompress(provider, BlockTags.LEAVES, Blocks.DIRT, Blocks.PODZOL);
    }

    private static void blockCompress(RecipeOutput provider, Block block1, Block block2, Block result) {
        BlockCompressRecipe.builder().input(block1).input(block2).result(result).save(provider);
    }

    @SuppressWarnings("SameParameterValue")
    private static void blockCompress(RecipeOutput provider, TagKey<Block> tag1, Block block2, Block result) {
        BlockCompressRecipe.builder().input(tag1).input(block2).result(result).save(provider);
    }
}
