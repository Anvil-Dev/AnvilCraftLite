package dev.anvilcraft.lite.data;

import dev.anvilcraft.lib.v2.registrum.providers.ProviderType;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.data.lang.LangHandler;
import dev.anvilcraft.lite.data.loot.ModLootTableProvider;
import dev.anvilcraft.lite.data.recipe.ModRecipeHandler;
import dev.anvilcraft.lite.data.tag.TagsHandler;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTRUM;

@EventBusSubscriber(modid = AnvilCraftLite.MOD_ID)
public class AnvilCraftLiteDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput packOutput = generator.getPackOutput();
        generator.addProvider(true, ModLootTableProvider.create(packOutput, lookupProvider));
    }

    public static void init() {
        REGISTRUM.addDataGenerator(ProviderType.LANG, LangHandler::init);
        REGISTRUM.addDataGenerator(ProviderType.RECIPE, ModRecipeHandler::init);
        REGISTRUM.addDataGenerator(ProviderType.ITEM_TAGS, TagsHandler::initItem);
    }
}
