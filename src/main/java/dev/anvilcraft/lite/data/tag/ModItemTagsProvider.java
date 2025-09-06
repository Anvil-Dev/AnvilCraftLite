package dev.anvilcraft.lite.data.tag;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.item.ModItemTags;
import dev.anvilcraft.lite.util.Util;
import dev.anvilcraft.lite.util.tag.BetterTagBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, AnvilCraftLite.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.addTag(ModItemTags.SUPER_HEATING_BOOST_PRODUCTION)
            .addTag(Tags.Items.RAW_MATERIALS)
            .addTag(Tags.Items.ORES);

        this.addTag(ModItemTags.COMPRESS_ITEM)
            .addElement(Items.SNOW_BLOCK)
            .addElement(Items.WHITE_WOOL)
            .addTag(Tags.Items.INGOTS)
            .addTag(Tags.Items.STORAGE_BLOCKS);
    }

    private BetterTagBuilder addTag(TagKey<Item> key) {
        return Util.cast(this.builders.computeIfAbsent(key.location(), p_236442_ -> new BetterTagBuilder()));
    }
}
