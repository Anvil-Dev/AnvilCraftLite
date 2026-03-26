package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.init.item.ModItems;
import dev.anvilcraft.lite.recipe.anvil.wrap.BoilingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.CookingRecipe;
import net.minecraft.world.item.Items;

public class CookingRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        CookingRecipeLoader.boiling(provider)
            .requires(ModItems.RESIN)
            .result(Items.SLIME_BALL)
            .unlockedBy("has_resin", provider.has(ModItems.RESIN))
            .save(provider.getOutput());
    }

    public static BoilingRecipe.Builder boiling(RegistrumRecipeProvider provider) {
        return BoilingRecipe.builder(provider.getItems());
    }

    public static CookingRecipe.Builder cooking(RegistrumRecipeProvider provider) {
        return CookingRecipe.builder(provider.getItems());
    }
}
