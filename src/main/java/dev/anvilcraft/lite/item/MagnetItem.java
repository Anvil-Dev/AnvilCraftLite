package dev.anvilcraft.lite.item;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.entity.MagnetizedNodeEntity;
import dev.anvilcraft.lite.init.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MagnetItem extends Item {
    public MagnetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        ItemStack item = context.getItemInHand();
        if (player == null) return InteractionResult.PASS;
        if (!player.isShiftKeyDown()) return InteractionResult.PASS;
        BlockPos pos = context.getClickedPos();
        BlockState blockState = level.getBlockState(pos);
        if (blockState.isAir()) return InteractionResult.PASS;
        double maxY = blockState.getCollisionShape(level, pos).max(Direction.Axis.Y, 0.5, 0.5);
        if (!blockState.getBlock().hasCollision && maxY == 0) return InteractionResult.PASS;
        for (MagnetizedNodeEntity entity : level.getEntities(
            ModEntities.MAGNETIZED_NODE.get(),
            new AABB(pos).setMaxY(pos.getY() + 1.1),
            EntitySelector.NO_SPECTATORS
        )) {
            if (entity.blockPos.equals(pos)) {
                entity.discard();
                player.getCooldowns().addCooldown(item, 5);
                return InteractionResult.SUCCESS;
            }
        }
        Vec3 nodePos = pos.getBottomCenter().add(0, maxY, 0);
        MagnetizedNodeEntity magnetizedNodeEntity = new MagnetizedNodeEntity(level, nodePos, pos);
        level.addFreshEntity(magnetizedNodeEntity);
        player.getCooldowns().addCooldown(item, 5);
        return InteractionResult.SUCCESS;
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (player.isShiftKeyDown()) return InteractionResult.PASS;
        ItemStack itemStack = player.getItemInHand(hand);
        double radius = AnvilCraftLite.CONFIG.magnetItemAttractsRadius;
        Vec3 position = player.position();
        AABB aabb = new AABB(position.add(-radius, -radius, -radius), position.add(radius, radius, radius));
        level.getEntities(EntityTypeTest.forClass(ItemEntity.class), aabb, Entity::isAlive)
            .forEach(e -> e.teleportTo(position.x(), position.y(), position.z()));
        player.getCooldowns().addCooldown(itemStack, 5);
        return InteractionResult.SUCCESS;
    }
}
