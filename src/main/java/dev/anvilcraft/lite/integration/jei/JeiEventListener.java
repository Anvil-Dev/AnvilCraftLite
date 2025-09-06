package dev.anvilcraft.lite.integration.jei;

import dev.anvilcraft.lib.integration.Integration;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

public class JeiEventListener {
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        event.sendRecipes(
            ModRecipeTypes.MESH_TYPE.get(),
            ModRecipeTypes.BLOCK_COMPRESS_TYPE.get(),
            ModRecipeTypes.BLOCK_CRUSH_TYPE.get(),
            ModRecipeTypes.BLOCK_SMEAR_TYPE.get(),
            ModRecipeTypes.ITEM_CRUSH_TYPE.get(),
            ModRecipeTypes.SQUEEZING_TYPE.get(),
            ModRecipeTypes.ITEM_INJECT_TYPE.get(),
            ModRecipeTypes.ITEM_COMPRESS_TYPE.get(),
            ModRecipeTypes.UNPACK_TYPE.get(),
            ModRecipeTypes.COOKING_TYPE.get(),
            ModRecipeTypes.BOILING_TYPE.get(),
            ModRecipeTypes.STAMPING_TYPE.get(),
            ModRecipeTypes.BULGING_TYPE.get()
        );
    }

    public static void onRecipeReceived(RecipesReceivedEvent event) {
        AnvilCraftJeiPlugin.recipes = event.getRecipeMap();
    }

    public static void onLevelUnload(LevelEvent.Unload event) {
        AnvilCraftJeiPlugin.recipes = null;
    }
}
