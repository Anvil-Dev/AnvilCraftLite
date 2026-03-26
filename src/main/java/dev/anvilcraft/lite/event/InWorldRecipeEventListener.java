package dev.anvilcraft.lite.event;

import dev.anvilcraft.lib.v2.event.InWorldRecipeManagerEvent;
import dev.anvilcraft.lib.v2.event.ItemCacheEvent;
import dev.anvilcraft.lib.v2.recipe.InWorldRecipe;
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

    @SubscribeEvent
    public static void inWorldRecipe(ItemCacheEvent.SpawnItemEntity event) {
        event.getEntity().anvilcraftLite$setIsAdsorbable(false);
    }
}
