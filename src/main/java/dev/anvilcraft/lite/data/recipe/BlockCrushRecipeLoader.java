package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCrushRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockCrushRecipeLoader extends ModRecipeLoader {
    public BlockCrushRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.blockCrush(Blocks.COBBLESTONE, Blocks.GRAVEL);
        this.blockCrush(Blocks.GRAVEL, Blocks.SAND);
        this.blockCrush(Blocks.POLISHED_GRANITE, Blocks.GRANITE);
        this.blockCrush(Blocks.GRANITE, Blocks.RED_SAND);
        this.blockCrush(Blocks.POLISHED_ANDESITE, Blocks.ANDESITE);
        this.blockCrush(Blocks.POLISHED_DIORITE, Blocks.DIORITE);
        this.blockCrush(Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS);
        this.blockCrush(Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        this.blockCrush(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS);
        this.blockCrush(Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES);
        this.blockCrush(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        this.blockCrush(Blocks.SOUL_SOIL, Blocks.SOUL_SAND);
    }

    private void blockCrush(Block input, Block result) {
        BlockCrushRecipe.builder(this.blocks).input(input).result(result).save(this.output);
    }
}
