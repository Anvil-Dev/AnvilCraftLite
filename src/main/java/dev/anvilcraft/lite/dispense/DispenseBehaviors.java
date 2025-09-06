package dev.anvilcraft.lite.dispense;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.item.ModItems;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = AnvilCraftLite.MOD_ID)
public class DispenseBehaviors {
    @SubscribeEvent
    public static void init(FMLLoadCompleteEvent event) {
        DispenseBehaviors.register(Items.GLASS_BOTTLE, EmptyBottleBehavior::new);
        DispenseBehaviors.register(Items.POTION, WaterBottleBehavior::new);
        DispenseBehaviors.register(Items.BUCKET, EmptyBucketBehavior::new);
        DispenseBehaviors.register(Items.WATER_BUCKET, WaterBucketBehavior::new);
        DispenseBehaviors.register(Items.LAVA_BUCKET, LavaBucketBehavior::new);
        DispenseBehaviors.register(Items.POWDER_SNOW_BUCKET, PowerSnowBucketBehavior::new);
        DispenseBehaviors.register(Items.BOWL, BowlBucketBehavior::new);
        DispenseBehaviors.register(Items.IRON_INGOT, IronIngotBehavior::new);
        DispenseBehaviors.register(ModItems.MAGNET_INGOT, MagnetIngotBehavior::new);
        DispenseBehaviors.register(Items.ANVIL, AnvilBehavior::new);
        DispenseBehaviors.register(Items.CHIPPED_ANVIL, ChippedAnvilBehavior::new);
        DispenseBehaviors.register(Items.DAMAGED_ANVIL, DamageAnvilBehavior::new);
    }

    public static void register(ItemLike item, Supplier<DispenseItemBehavior> factory) {
        DispenserBlock.DISPENSER_REGISTRY.put(item.asItem(), factory.get());
    }
}
