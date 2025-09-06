package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCompressRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockCompressRecipeLoader extends ModRecipeLoader {
    public BlockCompressRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.blockCompress(Blocks.STONE, Blocks.STONE, Blocks.DEEPSLATE);
        this.blockCompress(Blocks.ICE, Blocks.ICE, Blocks.PACKED_ICE);
        this.blockCompress(Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE);
        this.blockCompress(Blocks.NETHER_WART_BLOCK, Blocks.NETHERRACK, Blocks.CRIMSON_NYLIUM);
        this.blockCompress(Blocks.WARPED_WART_BLOCK, Blocks.NETHERRACK, Blocks.WARPED_NYLIUM);
        this.blockCompress(Blocks.BASALT, Blocks.BASALT, Blocks.BLACKSTONE);
        this.blockCompress(BlockTags.LEAVES, Blocks.DIRT, Blocks.PODZOL);
    }

    private void blockCompress(Block block1, Block block2, Block result) {
        BlockCompressRecipe.builder(this.blocks).input(block1).input(block2).result(result).save(this.output);
    }

    @SuppressWarnings("SameParameterValue")
    private void blockCompress(TagKey<Block> tag1, Block block2, Block result) {
        BlockCompressRecipe.builder(this.blocks).input(tag1).input(block2).result(result).save(this.output);
    }
}
