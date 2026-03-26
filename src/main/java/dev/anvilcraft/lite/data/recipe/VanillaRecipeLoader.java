package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.init.block.ModBlocks;
import dev.anvilcraft.lite.init.item.ModItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Items;

public class VanillaRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        provider.nineBlockStorageRecipesRecipesWithCustomUnpacking(
            RecipeCategory.MISC,
            ModItems.MAGNET_INGOT,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.MAGNET_BLOCK,
            "magnet_ingot_from_magnet_block",
            "magnet_ingot"
        );
        provider.nineBlockStorageRecipesRecipesWithCustomUnpacking(
            RecipeCategory.MISC,
            ModItems.RESIN,
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.RESIN_BLOCK,
            "resin_from_resin_block",
            "resin"
        );
        provider.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.HOLLOW_MAGNET_BLOCK)
            .pattern("AAA")
            .pattern("A A")
            .pattern("AAA")
            .define('A', ModItems.MAGNET_INGOT)
            .unlockedBy("has_magnet_ingot", provider.has(ModItems.MAGNET_INGOT))
            .save(provider.getOutput());
        provider.shapeless(RecipeCategory.MISC, ModItems.MAGNET_INGOT, 8)
            .requires(ModBlocks.HOLLOW_MAGNET_BLOCK)
            .unlockedBy("has_hollow_magnet_block", provider.has(ModBlocks.HOLLOW_MAGNET_BLOCK))
            .save(provider.getOutput());
        provider.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FERRITE_CORE_MAGNET_BLOCK)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('A', ModItems.MAGNET_INGOT)
            .define('B', Items.IRON_INGOT)
            .unlockedBy("has_magnet_ingot", provider.has(ModItems.MAGNET_INGOT))
            .unlockedBy("has_iron_ingot", provider.has(Items.IRON_INGOT))
            .save(provider.getOutput());
    }
}
