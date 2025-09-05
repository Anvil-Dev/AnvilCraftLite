package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.AnvilCraftLite;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItemGroups {
    private static final DeferredRegister<CreativeModeTab> DF = DeferredRegister.create(
        Registries.CREATIVE_MODE_TAB,
        AnvilCraftLite.MOD_ID
    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANVILCRAFT_LITE = DF.register(
        "tab",
        () -> CreativeModeTab.builder()
            .icon(ModItems.RESIN.get()::getDefaultInstance)
            .displayItems((ctx, entries) -> {
                entries.accept(ModItems.RESIN.get());
            })
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .build()
    );
}
