package dev.anvilcraft.lite.data.lang;

import dev.anvilcraft.lib.v2.registrum.providers.RegistrumLangProvider;

public class JeiLang {
    public static void init(RegistrumLangProvider provider) {
        provider.add("gui.anvilcraft.category.chance", "Chance: %s%%");
        provider.add("gui.anvilcraft.category.average_output", "Average: %s");
        provider.add("gui.anvilcraft.category.min_output", "Min: %s");
        provider.add("gui.anvilcraft.category.max_output", "Max: %s");

        provider.add("gui.anvilcraft.category.mesh", "Mesh");

        provider.add("gui.anvilcraft.category.block_compress", "Block Compress");
        provider.add("gui.anvilcraft.category.block_crush", "Block Crush");
        provider.add("gui.anvilcraft.category.block_smear", "Block Smear");

        provider.add("gui.anvilcraft.category.item_compress", "Item Compress");
        provider.add("gui.anvilcraft.category.item_crush", "Item Crush");
        provider.add("gui.anvilcraft.category.unpack", "Unpack");

        provider.add("gui.anvilcraft.category.cooking", "Cooking");
        provider.add("gui.anvilcraft.category.boiling", "Boiling");

        provider.add("gui.anvilcraft.category.stamping", "Stamping");

        provider.add("gui.anvilcraft.category.super_heating", "Super Heating");
        provider.add("gui.anvilcraft.category.super_heating.consume_fluid", "Consume: %d mB of %s");
        provider.add("gui.anvilcraft.category.super_heating.produce_fluid", "Produce: %d mB of %s");
        provider.add("gui.anvilcraft.category.super_heating.need_activated", "Need Activated");

        provider.add("gui.anvilcraft.category.squeezing", "Squeezing");

        provider.add("gui.anvilcraft.category.item_inject", "Item Inject");

        provider.add("gui.anvilcraft.category.bulging", "Bulging");
        provider.add("gui.anvilcraft.category.bulging.consume_fluid", "Consume: %d mB of %s");
        provider.add("gui.anvilcraft.category.bulging.produce_fluid", "Produce: %d mB of %s");

        provider.add("jei.anvilcraft.tooltip.not_consumed", "Not Consumed");
    }
}
