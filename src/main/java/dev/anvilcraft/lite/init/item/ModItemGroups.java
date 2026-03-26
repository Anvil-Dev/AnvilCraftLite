package dev.anvilcraft.lite.init.item;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTRUM;

public class ModItemGroups {
    private static final DeferredRegister<CreativeModeTab> DF =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AnvilCraftLite.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANVILCRAFT_LITE = DF.register(
        "tab",
        identifier -> CreativeModeTab.builder()
            .icon(() -> ModItems.MAGNET_INGOT.get().getDefaultInstance())
            .title(REGISTRUM.addLang("itemGroup", AnvilCraftLite.of("tab"), "AnvilCraft: Lite"))
            .displayItems((ctx, entries) -> {
                entries.accept(ModItems.RESIN.get());
                entries.accept(ModItems.MAGNET_INGOT.get());
                entries.accept(ModBlocks.RESIN_BLOCK.get());
                entries.accept(ModBlocks.MAGNET_BLOCK.get());
                entries.accept(ModBlocks.HOLLOW_MAGNET_BLOCK.get());
                entries.accept(ModBlocks.FERRITE_CORE_MAGNET_BLOCK.get());
                entries.accept(ModItems.CRAB_CLAW.get());
            })
            .build()
    );

    public static void init() {
    }
}
