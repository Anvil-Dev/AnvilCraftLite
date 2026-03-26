package dev.anvilcraft.lite.item;

import dev.anvilcraft.lite.init.item.ModComponents;
import dev.anvilcraft.lite.item.property.component.SavedEntity;
import net.minecraft.ChatFormatting;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HasMobBlockItem extends BlockItem {
    public HasMobBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(
        ItemStack stack,
        TooltipContext context,
        TooltipDisplay tooltipDisplay,
        Consumer<Component> tooltipAdder,
        TooltipFlag flag
    ) {
        if (!HasMobBlockItem.hasMob(stack)) return;
        Optional.ofNullable(context.level())
            .map(level -> HasMobBlockItem.getMobFromItem(level, stack))
            .ifPresent(entity -> tooltipAdder.accept(Component.literal("- ")
                .append(entity.getDisplayName())
                .withStyle(ChatFormatting.DARK_GRAY)));
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    public static boolean hasMob(ItemStack stack) {
        return stack.has(ModComponents.SAVED_ENTITY);
    }

    /**
     * 获取物品中的实体
     */
    public static @Nullable Entity getMobFromItem(Level level, ItemStack stack) {
        if (!hasMob(stack)) return null;
        SavedEntity savedEntity = stack.get(ModComponents.SAVED_ENTITY);
        // make idea happy
        if (savedEntity == null) return null;
        return savedEntity.toEntity(level);
    }

    /**
     * 向物品中存入实体
     * 适用于来自玩家的请求
     */
    @SuppressWarnings("deprecation")
    public static void saveMobInItem(Level level, Mob entity, Player player, ItemStack stack) {
        if (level.isClientSide()) {
            Item item = stack.getItem();
            if (item instanceof ResinBlockItem item1) {
                BlockPos blockPos = entity.getOnPos();
                BlockState blockState = item1.getBlock().defaultBlockState();
                SoundType soundType = blockState.getSoundType();
                level.playSound(
                    player,
                    blockPos,
                    item1.getPlaceSound(blockState),
                    SoundSource.BLOCKS,
                    (soundType.getVolume() + 1.0f) / 2.0f,
                    soundType.getPitch() * 0.8f
                );
            }
            return;
        }
        SavedEntity savedEntity = SavedEntity.fromMob(entity);
        if (entity.getType().getCategory() == MobCategory.MONSTER) {
            MobEffectInstance instance = entity.getEffect(MobEffects.WEAKNESS);
            if (instance == null && !player.getAbilities().instabuild) return;
        }
        stack = stack.split(1);
        stack.set(ModComponents.SAVED_ENTITY, savedEntity);
        player.getInventory().placeItemBackInInventory(stack);
        if (entity instanceof Villager villager) {
            villager.releasePoi(MemoryModuleType.HOME);
            villager.releasePoi(MemoryModuleType.JOB_SITE);
            villager.releasePoi(MemoryModuleType.POTENTIAL_JOB_SITE);
            villager.releasePoi(MemoryModuleType.MEETING_POINT);
        }
        entity.remove(Entity.RemovalReason.DISCARDED);
    }
}
