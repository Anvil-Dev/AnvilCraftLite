package dev.anvilcraft.lite.event;

import dev.anvilcraft.lite.init.item.ModItems;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber
public class LootTableEventListener {
    @SubscribeEvent
    public static void lootTable(@NotNull LootTableLoadEvent event) {
        Identifier id = event.getName();
        LootTable table = event.getTable();
        if (BuiltInLootTables.FISHING_JUNK.location().equals(id)) {
            table.addPool(
                LootPool.lootPool()
                    .add(LootItem.lootTableItem(ModItems.CRAB_CLAW))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                    .build()
            );
        }
    }
}
