package dev.anvilcraft.lite.dispense;

import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

@SuppressWarnings("resource")
public interface ModDispenseItemBehavior extends DispenseItemBehavior {
    DefaultDispenseItemBehavior DEFAULT_DISPENSE_ITEM_BEHAVIOR = new DefaultDispenseItemBehavior();

    private static void playDefaultSound(BlockSource blockSource) {
        blockSource.level().levelEvent(1000, blockSource.pos(), 0);
    }

    private static void playDefaultAnimation(BlockSource blockSource, Direction direction) {
        blockSource.level().levelEvent(2000, blockSource.pos(), direction.get3DDataValue());
    }

    @Override
    default ItemStack dispense(BlockSource blockSource, ItemStack item) {
        this.playSound(blockSource);
        this.playAnimation(blockSource, blockSource.state().getValue(DispenserBlock.FACING));
        ItemStack stack = this.execute(blockSource, item.copy());
        if (!ItemStack.isSameItemSameComponents(item, stack) || stack.getCount() != item.getCount()) return stack;
        return DEFAULT_DISPENSE_ITEM_BEHAVIOR.dispense(blockSource, item);
    }

    ItemStack execute(BlockSource blockSource, ItemStack item);

    default void playSound(BlockSource blockSource) {
        ModDispenseItemBehavior.playDefaultSound(blockSource);
    }

    default void playAnimation(BlockSource blockSource, Direction direction) {
        ModDispenseItemBehavior.playDefaultAnimation(blockSource, direction);
    }
}
