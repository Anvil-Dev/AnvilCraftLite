package dev.anvilcraft.lite.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider){
        return new LootTableProvider(
            packOutput,
            Set.of(),
            List.of(
                new LootTableProvider.SubProviderEntry(ModBlockSubProviderEntry::new, LootContextParamSets.BLOCK)
            ),
            provider
        );
    }
}
