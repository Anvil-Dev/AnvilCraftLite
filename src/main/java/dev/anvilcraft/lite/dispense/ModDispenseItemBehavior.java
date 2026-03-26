package dev.anvilcraft.lite.dispense;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

@SuppressWarnings("resource")
public interface ModDispenseItemBehavior extends DispenseItemBehavior {
    DefaultDispenseItemBehavior DEFAULT_DISPENSE_ITEM_BEHAVIOR = new DefaultDispenseItemBehavior();

    static void playDefaultSound(BlockSource blockSource) {
        blockSource.level().levelEvent(1000, blockSource.pos(), 0);
    }

    static void playDefaultAnimation(BlockSource blockSource, Direction direction) {
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

    static ItemStack consumeWithRemainder(BlockSource blockSource, ItemStack stack, ItemStack remainder) {
        stack.shrink(1);
        if (stack.isEmpty()) {
            return remainder;
        } else {
            addToInventoryOrDispense(blockSource, remainder);
            return stack;
        }
    }

    static void addToInventoryOrDispense(BlockSource blockSource, ItemStack remainder) {
        ItemStack itemstack = blockSource.blockEntity().insertItem(remainder);
        if (!itemstack.isEmpty()) {
            Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
            spawnItem(blockSource.level(), itemstack, 6, direction, DispenserBlock.getDispensePosition(blockSource));
            playDefaultSound(blockSource);
            playDefaultAnimation(blockSource, direction);
        }
    }

    static void spawnItem(Level level, ItemStack stack, int speed, Direction facing, Position position) {
        double d0 = position.x();
        double d1 = position.y();
        double d2 = position.z();
        if (facing.getAxis() == Direction.Axis.Y) {
            d1 -= 0.125F;
        } else {
            d1 -= 0.15625F;
        }

        ItemEntity itementity = new ItemEntity(level, d0, d1, d2, stack);
        double d3 = level.getRandom().nextDouble() * 0.1 + 0.2;
        itementity.setDeltaMovement(
            level.getRandom().triangle((double) facing.getStepX() * d3, 0.0172275 * (double) speed),
            level.getRandom().triangle(0.2, 0.0172275 * (double) speed),
            level.getRandom().triangle((double) facing.getStepZ() * d3, 0.0172275 * (double) speed)
        );
        level.addFreshEntity(itementity);
    }
}
