package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.UnpackRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class UnpackRecipeLoader extends ModRecipeLoader {
    public UnpackRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.unpack(Items.MELON, Items.MELON_SLICE, 9);
        this.unpack(Items.SNOW_BLOCK, Items.SNOWBALL, 4);
        this.unpack(Items.CLAY, Items.CLAY_BALL, 4);
        this.unpack(Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4);
        this.unpack(Items.QUARTZ_BLOCK, Items.QUARTZ, 4);
        this.unpack(Items.DRIPSTONE_BLOCK, Items.POINTED_DRIPSTONE, 4);
        this.unpack(Items.AMETHYST_BLOCK, Items.AMETHYST_SHARD, 4);
        this.unpack(Items.HONEYCOMB_BLOCK, Items.HONEYCOMB, 4);

        UnpackRecipe.builder(this.items)
            .requires(Items.HONEY_BLOCK)
            .requires(Items.GLASS_BOTTLE, 4)
            .result(Items.HONEY_BOTTLE, 4)
            .save(this.output);

        UnpackRecipe.builder(this.items)
            .requires(Items.PRISMARINE)
            .result(Items.PRISMARINE_SHARD, 4)
            .save(this.output, AnvilCraftLite.of("unpack/prismine_shard_from_prismine"));

        UnpackRecipe.builder(this.items)
            .requires(Items.PRISMARINE_BRICKS)
            .result(Items.PRISMARINE_SHARD, 9)
            .save(this.output, AnvilCraftLite.of("unpack/prismine_shard_from_prismine_bricks"));

        UnpackRecipe.builder(this.items)
            .requires(ItemTags.WOOL)
            .result(Items.STRING, 4)
            .save(this.output, AnvilCraftLite.of("unpack/string_from_wools"));
    }

    private void unpack(ItemLike input, ItemLike result, int count) {
        UnpackRecipe.builder(this.items).requires(input).result(result, count).save(this.output);
    }
}
