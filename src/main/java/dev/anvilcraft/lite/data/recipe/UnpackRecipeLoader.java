package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.UnpackRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class UnpackRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        UnpackRecipeLoader.unpack(provider, Items.MELON, Items.MELON_SLICE, 9);
        UnpackRecipeLoader.unpack(provider, Items.SNOW_BLOCK, Items.SNOWBALL, 4);
        UnpackRecipeLoader.unpack(provider, Items.CLAY, Items.CLAY_BALL, 4);
        UnpackRecipeLoader.unpack(provider, Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4);
        UnpackRecipeLoader.unpack(provider, Items.QUARTZ_BLOCK, Items.QUARTZ, 4);
        UnpackRecipeLoader.unpack(provider, Items.DRIPSTONE_BLOCK, Items.POINTED_DRIPSTONE, 4);
        UnpackRecipeLoader.unpack(provider, Items.AMETHYST_BLOCK, Items.AMETHYST_SHARD, 4);
        UnpackRecipeLoader.unpack(provider, Items.HONEYCOMB_BLOCK, Items.HONEYCOMB, 4);

        UnpackRecipe.builder(provider.getItems())
            .requires(Items.HONEY_BLOCK)
            .requires(Items.GLASS_BOTTLE, 4)
            .result(Items.HONEY_BOTTLE, 4)
            .save(provider.getOutput());

        UnpackRecipe.builder(provider.getItems())
            .requires(Items.PRISMARINE)
            .result(Items.PRISMARINE_SHARD, 4)
            .save(provider.getOutput(), AnvilCraftLite.of("unpack/prismine_shard_from_prismine"));

        UnpackRecipe.builder(provider.getItems())
            .requires(Items.PRISMARINE_BRICKS)
            .result(Items.PRISMARINE_SHARD, 9)
            .save(provider.getOutput(), AnvilCraftLite.of("unpack/prismine_shard_from_prismine_bricks"));

        UnpackRecipe.builder(provider.getItems())
            .requires(ItemTags.WOOL)
            .result(Items.STRING, 4)
            .save(provider.getOutput(), AnvilCraftLite.of("unpack/string_from_wools"));
    }

    private static void unpack(RegistrumRecipeProvider provider, ItemLike input, ItemLike result, int count) {
        UnpackRecipe.builder(provider.getItems())
            .requires(input)
            .result(result, count)
            .save(provider.getOutput());
    }
}
