package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.item.ModItems;
import dev.anvilcraft.lite.recipe.anvil.wrap.StampingRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class StampingRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        StampingRecipeLoader.stamping(provider, Items.IRON_INGOT, Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
        StampingRecipeLoader.stamping(provider, Items.GOLD_INGOT, Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
        StampingRecipeLoader.stamping(provider, Items.SNOWBALL, Items.SNOW);
        StampingRecipeLoader.stamping(provider, Items.CHERRY_LEAVES, Items.PINK_PETALS);
        StampingRecipe.builder(provider.getItems())
            .requires(Items.SUGAR_CANE)
            .result(Items.PAPER)
            .result(Items.SUGAR)
            .save(provider.getOutput(), AnvilCraftLite.of("stamping/paper_from_sugar_cane"));
        StampingRecipe.builder(provider.getItems())
            .requires(ItemTags.LOGS)
            .result(ModItems.RESIN)
            .result(Items.STICK, 8)
            .save(provider.getOutput(), AnvilCraftLite.of("stamping/resin_from_logs"));
    }

    @SuppressWarnings("SameParameterValue")
    private static void stamping(RegistrumRecipeProvider provider, ItemLike input, ItemLike result, int count) {
        StampingRecipe.builder(provider.getItems()).requires(input).result(result, count).save(provider.getOutput());
    }

    private static void stamping(RegistrumRecipeProvider provider, ItemLike input, ItemLike result) {
        StampingRecipeLoader.stamping(provider, input, result, 1);
    }

    private static void stamping(RegistrumRecipeProvider provider, TagKey<Item> input, ItemLike result) {
        StampingRecipe.builder(provider.getItems()).requires(input).result(result, 1).save(provider.getOutput());
    }
}
