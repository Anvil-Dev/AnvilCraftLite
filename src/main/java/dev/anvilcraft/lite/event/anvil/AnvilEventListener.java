package dev.anvilcraft.lite.event.anvil;

import dev.anvilcraft.lib.recipe.util.InWorldRecipeContext;
import dev.anvilcraft.lib.recipe.util.InWorldRecipeManager;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.api.event.AnvilEvent;
import dev.anvilcraft.lite.init.reicpe.ModRecipeTriggers;
import dev.anvilcraft.lite.recipe.anvil.outcome.DamageAnvil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@EventBusSubscriber(modid = AnvilCraftLite.MOD_ID)
public class AnvilEventListener {
    /**
     * 侦听铁砧落地事件
     *
     * @param event 铁砧落地事件
     */
    @SubscribeEvent
    public static void onLand(AnvilEvent.@NotNull OnLand event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        MinecraftServer server = level.getServer();
        if (null == server) return;
        final BlockPos hitBlockPos = pos.below();
        BlockPos belowPos = hitBlockPos.below();
        BlockState hitBelowState = level.getBlockState(belowPos);
        if (hitBelowState.is(Blocks.STONECUTTER)) {
            AnvilEventListener.brokeBlock(level, hitBlockPos, event);
            return;
        }
        AnvilEventListener.handleNeoAnvilRecipe(event);
    }

    private static void brokeBlock(Level level, BlockPos pos, AnvilEvent.OnLand event) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        BlockState state = level.getBlockState(pos);
        //noinspection deprecation
        if (state.getBlock().getExplosionResistance() >= 1200.0) event.setAnvilDamage(true);
        if (state.getDestroySpeed(level, pos) < 0) return;
        ItemStack dummyTool = ItemStack.EMPTY;
        state.spawnAfterBreak(serverLevel, pos, dummyTool, false);
        List<ItemStack> drops;
        drops = AnvilEventListener.dropWithTool(serverLevel, pos, dummyTool);
        AnvilEventListener.dropItems(drops, level, pos.getCenter());
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }

    public static void handleNeoAnvilRecipe(AnvilEvent.OnLand event) {
        Level level = event.getLevel();
        if (!(level instanceof ServerLevel serverLevel)) return;
        BlockPos pos = event.getPos();
        FallingBlockEntity entity = event.getEntity();
        InWorldRecipeManager manager = (serverLevel.getServer().getRecipeManager()).anvillib$getInWorldRecipeManager();
        InWorldRecipeContext context = new InWorldRecipeContext(serverLevel, pos.getCenter().subtract(0.0, 0.5, 0.0), entity);
        manager.trigger(ModRecipeTriggers.ON_ANVIL_FALL_ON, context);
        boolean damageAnvil = context.get(DamageAnvil.DAMAGE_ANVIL);
        if (!event.isAnvilDamage()) event.setAnvilDamage(damageAnvil);
        context.accept();
    }

    public static List<ItemStack> dropWithTool(ServerLevel level, BlockPos pos, ItemStack tool) {
        BlockState state = level.getBlockState(pos);
        if (state.isAir()) return List.of();
        LootParams.Builder builder = new LootParams.Builder(level)
            .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
            .withParameter(LootContextParams.TOOL, tool)
            .withOptionalParameter(LootContextParams.BLOCK_ENTITY, level.getBlockEntity(pos));
        return state.getDrops(builder);
    }

    public static void dropItems(List<ItemStack> items, Level level, Vec3 pos) {
        for (ItemStack item : items) {
            if (item.isEmpty()) continue;
            int count = item.getCount();
            int maxStack = item.getMaxStackSize();
            for (; count >= maxStack; count -= maxStack) {
                ItemEntity entity = new ItemEntity(
                    level,
                    pos.x,
                    pos.y,
                    pos.z,
                    item.copyWithCount(maxStack),
                    0.0d,
                    0.0d,
                    0.0d
                );
                level.addFreshEntity(entity);
            }
            if (count <= 0) continue;
            ItemEntity entity = new ItemEntity(
                level,
                pos.x,
                pos.y,
                pos.z,
                item.copyWithCount(count),
                0.0d,
                0.0d,
                0.0d
            );
            level.addFreshEntity(entity);
        }
    }
}
