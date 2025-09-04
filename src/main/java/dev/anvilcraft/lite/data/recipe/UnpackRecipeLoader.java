package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.UnpackRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class UnpackRecipeLoader {
    public static void init(RecipeOutput provider) {
        unpack(provider, Items.MELON, Items.MELON_SLICE, 9);
        unpack(provider, Items.SNOW_BLOCK, Items.SNOWBALL, 4);
        unpack(provider, Items.CLAY, Items.CLAY_BALL, 4);
        unpack(provider, Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4);
        unpack(provider, Items.QUARTZ_BLOCK, Items.QUARTZ, 4);
        unpack(provider, Items.DRIPSTONE_BLOCK, Items.POINTED_DRIPSTONE, 4);
        unpack(provider, Items.AMETHYST_BLOCK, Items.AMETHYST_SHARD, 4);
        unpack(provider, Items.HONEYCOMB_BLOCK, Items.HONEYCOMB, 4);

        UnpackRecipe.builder()
            .requires(Items.HONEY_BLOCK)
            .requires(Items.GLASS_BOTTLE, 4)
            .result(Items.HONEY_BOTTLE, 4)
            .save(provider);

        UnpackRecipe.builder()
            .requires(Items.PRISMARINE)
            .result(Items.PRISMARINE_SHARD, 4)
            .save(provider, AnvilCraftLite.of("unpack/prismine_shard_from_prismine"));

        UnpackRecipe.builder()
            .requires(Items.PRISMARINE_BRICKS)
            .result(Items.PRISMARINE_SHARD, 9)
            .save(provider, AnvilCraftLite.of("unpack/prismine_shard_from_prismine_bricks"));
    }

    private static void unpack(RecipeOutput provider, ItemLike input, ItemLike result, int count) {
        UnpackRecipe.builder().requires(input).result(result, count).save(provider);
    }
}
