package dev.anvilcraft.lite.data.tag;

import dev.anvilcraft.lib.v2.registrum.providers.RegistrumTagsProvider;
import dev.anvilcraft.lite.init.item.ModItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class TagsHandler {
    public static void initItem(RegistrumTagsProvider<Item> provider) {
        provider.rawBuilder(ModItemTags.SUPER_HEATING_BOOST_PRODUCTION)
            .addTag(Tags.Items.RAW_MATERIALS.location())
            .addTag(Tags.Items.ORES.location());

        provider.rawBuilder(ModItemTags.COMPRESS_ITEM)
            .addElement(BuiltInRegistries.ITEM.getKey(Items.SNOW_BLOCK))
            .addElement(BuiltInRegistries.ITEM.getKey(Items.WHITE_WOOL))
            .addTag(Tags.Items.INGOTS.location())
            .addTag(Tags.Items.STORAGE_BLOCKS.location());
    }
}
