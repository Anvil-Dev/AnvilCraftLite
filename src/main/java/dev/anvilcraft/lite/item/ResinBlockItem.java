package dev.anvilcraft.lite.item;

import dev.anvilcraft.lite.init.item.ModComponents;
import dev.anvilcraft.lite.init.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ResinBlockItem extends HasMobBlockItem {
    public ResinBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();
        if (!ResinBlockItem.hasMob(stack)) return super.place(context);
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.FAIL;
        ResinBlockItem.spawnMobFromItem(level, player, pos, stack);
        return InteractionResult.SUCCESS;
    }

    /**
     * 右键实体
     */
    public static InteractionResult useEntity(Player player, Entity target, ItemStack stack) {
        if (!(target instanceof Mob mob) || target.getBbHeight() > 2.0 || target.getBbWidth() > 1.5 || ResinBlockItem.hasMob(stack)) {
            return InteractionResult.PASS;
        }
        ResinBlockItem.saveMobInItem(player.level(), mob, player, stack);
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    private static void spawnMobFromItem(Level level, Player player, BlockPos pos, ItemStack stack) {
        ItemStack copy = stack.copy();
        stack.shrink(1);
        stack.remove(ModComponents.SAVED_ENTITY);
        if (level.isClientSide()) {
            Item item = copy.getItem();
            if (item instanceof ResinBlockItem item1) {
                BlockState blockState = item1.getBlock().defaultBlockState();
                SoundType soundType = blockState.getSoundType();
                level.playSound(
                    player,
                    pos,
                    item1.getPlaceSound(blockState),
                    SoundSource.BLOCKS,
                    (soundType.getVolume() + 1.0f) / 2.0f,
                    soundType.getPitch() * 0.8f
                );
            }
            return;
        }
        Entity entity = HasMobBlockItem.getMobFromItem(level, copy);
        if (entity == null) return;
        Vec3 center = pos.getCenter();
        entity.teleportTo(center.x(), center.y(), center.z());
        level.addFreshEntity(entity);
        RandomSource random = level.getRandom();
        ItemStack back = new ItemStack(ModItems.RESIN.asItem(), random.nextInt(1, 4));
        if (!player.getAbilities().instabuild) {
            player.getInventory().placeItemBackInInventory(back);
        }
    }
}
