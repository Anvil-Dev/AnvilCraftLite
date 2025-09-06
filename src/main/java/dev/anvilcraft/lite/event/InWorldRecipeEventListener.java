package dev.anvilcraft.lite.event;

import dev.anvilcraft.lib.event.InWorldRecipeManagerEvent;
import dev.anvilcraft.lib.recipe.InWorldRecipe;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.VanillaRecipesWrap;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber(modid = AnvilCraftLite.MOD_ID)
public class InWorldRecipeEventListener {
    @SubscribeEvent
    public static void inWorldRecipe(InWorldRecipeManagerEvent.Init event) {
        RecipeManager manager = event.getRecipeManager();
        List<RecipeHolder<InWorldRecipe>> init = VanillaRecipesWrap.init(manager.anvillib$getRegistries(), manager.getRecipes());
        manager.anvillib$addRecipes(init);
    }
}
