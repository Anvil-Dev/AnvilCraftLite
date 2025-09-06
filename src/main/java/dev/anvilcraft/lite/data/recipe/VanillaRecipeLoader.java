package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.init.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

public class VanillaRecipeLoader extends ModRecipeLoader {
    protected VanillaRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.nineBlockStorageRecipesRecipesWithCustomUnpacking(
            RecipeCategory.MISC,
            ModItems.MAGNET_INGOT,
            RecipeCategory.BUILDING_BLOCKS,
            ModItems.MAGNET_BLOCK,
            "magnet_ingot_from_magnet_block",
            "magnet_ingot"
        );
        this.nineBlockStorageRecipesRecipesWithCustomUnpacking(
            RecipeCategory.MISC,
            ModItems.RESIN,
            RecipeCategory.BUILDING_BLOCKS,
            ModItems.RESIN_BLOCK,
            "resin_from_resin_block",
            "resin"
        );
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.HOLLOW_MAGNET_BLOCK)
            .pattern("AAA")
            .pattern("A A")
            .pattern("AAA")
            .define('A', ModItems.MAGNET_INGOT)
            .unlockedBy("has_magnet_ingot", this.has(ModItems.MAGNET_INGOT))
            .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.MAGNET_INGOT, 8)
            .requires(ModItems.HOLLOW_MAGNET_BLOCK)
            .unlockedBy("has_hollow_magnet_block", this.has(ModItems.HOLLOW_MAGNET_BLOCK))
            .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.FERRITE_CORE_MAGNET_BLOCK)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('A', ModItems.MAGNET_INGOT)
            .define('B', Items.IRON_INGOT)
            .unlockedBy("has_magnet_ingot", this.has(ModItems.MAGNET_INGOT))
            .unlockedBy("has_iron_ingot", this.has(Items.IRON_INGOT))
            .save(this.output);
    }
}
