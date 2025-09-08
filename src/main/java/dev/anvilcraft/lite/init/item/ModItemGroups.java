package dev.anvilcraft.lite.init.item;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTER;

public class ModItemGroups {
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANVILCRAFT_LITE = REGISTER.itemGroup(
        "tab",
        builder -> builder
            .icon(() -> ModItems.MAGNET_INGOT.get().getDefaultInstance())
            .title(REGISTER.lang("itemGroup.anvilcraft_lite.tab", "AnvilCraft: Lite"))
            .displayItems((ctx, entries) -> {
                entries.accept(ModItems.RESIN.get());
                entries.accept(ModItems.MAGNET_INGOT.get());
                entries.accept(ModItems.RESIN_BLOCK.get());
                entries.accept(ModItems.MAGNET_BLOCK.get());
                entries.accept(ModItems.HOLLOW_MAGNET_BLOCK.get());
                entries.accept(ModItems.FERRITE_CORE_MAGNET_BLOCK.get());
                entries.accept(ModItems.CRAB_CLAW.get());
            })
    );

    public static void init() {

    }
}
