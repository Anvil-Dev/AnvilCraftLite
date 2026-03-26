package dev.anvilcraft.lite.dispense;

import dev.anvilcraft.lite.init.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class IronIngotBehavior implements ModDispenseItemBehavior {
    @Override
    @SuppressWarnings("resource")
    public ItemStack execute(BlockSource blockSource, ItemStack item) {
        ServerLevel level = blockSource.level();
        BlockState state = blockSource.state();
        BlockPos pos1 = blockSource.pos().relative(state.getValue(DispenserBlock.FACING));
        BlockState blockState = level.getBlockState(pos1);
        if (blockState.is(ModBlocks.HOLLOW_MAGNET_BLOCK)) {
            level.setBlockAndUpdate(pos1, ModBlocks.FERRITE_CORE_MAGNET_BLOCK.get().defaultBlockState());
            item.shrink(1);
            return item;
        }
        List<IronGolem> ironGolems = level.getEntities(
            EntityTypeTest.forClass(IronGolem.class),
            new AABB(pos1.getX(), pos1.getY(), pos1.getZ(), pos1.getX() + 1, pos1.getY() + 1, pos1.getZ() + 1),
            Entity::isAlive
        );
        if (!ironGolems.isEmpty()) {
            ironGolems.stream()
                .filter(ironGolem -> ironGolem.getHealth() < ironGolem.getMaxHealth())
                .findFirst()
                .ifPresent(ironGolem -> {
                    ironGolem.heal(Math.min(ironGolem.getHealth() + 25, ironGolem.getMaxHealth()));
                    item.shrink(1);
                });
        }
        return item;
    }
}
