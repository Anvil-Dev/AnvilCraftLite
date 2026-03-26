package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCompressRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockCompressRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        BlockCompressRecipeLoader.blockCompress(provider, Blocks.STONE, Blocks.STONE, Blocks.DEEPSLATE);
        BlockCompressRecipeLoader.blockCompress(provider, Blocks.ICE, Blocks.ICE, Blocks.PACKED_ICE);
        BlockCompressRecipeLoader.blockCompress(provider, Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE);
        BlockCompressRecipeLoader.blockCompress(provider, Blocks.NETHER_WART_BLOCK, Blocks.NETHERRACK, Blocks.CRIMSON_NYLIUM);
        BlockCompressRecipeLoader.blockCompress(provider, Blocks.WARPED_WART_BLOCK, Blocks.NETHERRACK, Blocks.WARPED_NYLIUM);
        BlockCompressRecipeLoader.blockCompress(provider, Blocks.BASALT, Blocks.BASALT, Blocks.BLACKSTONE);
        BlockCompressRecipeLoader.blockCompress(provider, BlockTags.LEAVES, Blocks.DIRT, Blocks.PODZOL);
    }

    private static void blockCompress(RegistrumRecipeProvider provider, Block block1, Block block2, Block result) {
        BlockCompressRecipe.builder(provider.getRegistries().lookupOrThrow(Registries.BLOCK))
            .input(block1)
            .input(block2)
            .result(result)
            .save(provider.getOutput());
    }

    @SuppressWarnings("SameParameterValue")
    private static void blockCompress(RegistrumRecipeProvider provider, TagKey<Block> tag1, Block block2, Block result) {
        BlockCompressRecipe.builder(provider.getRegistries().lookupOrThrow(Registries.BLOCK))
            .input(tag1)
            .input(block2)
            .result(result)
            .save(provider.getOutput());
    }
}
