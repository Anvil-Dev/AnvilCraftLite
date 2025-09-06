package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.ItemInjectRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ItemInjectRecipeLoader extends ModRecipeLoader {
    public ItemInjectRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        ItemInjectRecipe.builder(this.items, this.blocks)
            .requires(Items.RAW_COPPER_BLOCK, 3)
            .inputBlock(Blocks.DEEPSLATE)
            .resultBlock(Blocks.DEEPSLATE_COPPER_ORE)
            .save(this.output);

        ItemInjectRecipe.builder(this.items, this.blocks)
            .requires(Items.RAW_IRON_BLOCK)
            .inputBlock(Blocks.DEEPSLATE)
            .resultBlock(Blocks.DEEPSLATE_IRON_ORE)
            .save(this.output);

        ItemInjectRecipe.builder(this.items, this.blocks)
            .requires(Items.RAW_GOLD_BLOCK)
            .inputBlock(Blocks.DEEPSLATE)
            .resultBlock(Blocks.DEEPSLATE_GOLD_ORE)
            .save(this.output);

        ItemInjectRecipe.builder(this.items, this.blocks)
            .requires(Items.GOLD_INGOT, 2)
            .inputBlock(Blocks.NETHERRACK)
            .resultBlock(Blocks.NETHER_GOLD_ORE)
            .save(this.output);

        ItemInjectRecipe.builder(this.items, this.blocks)
            .requires(Items.GOLD_INGOT)
            .inputBlock(Blocks.BLACKSTONE)
            .resultBlock(Blocks.GILDED_BLACKSTONE)
            .save(this.output);
    }
}
