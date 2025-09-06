package dev.anvilcraft.lite.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.anvilcraft.lite.api.event.AnvilEvent;
import dev.anvilcraft.lite.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("resource")
@Mixin(FallingBlockEntity.class)
abstract class FallingBlockEntityMixin extends Entity {
    @Shadow
    private BlockState blockState;

    @Shadow
    private boolean cancelDrop;

    @Unique
    private double anvilcraftLite$fallDistance;

    public FallingBlockEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
        method = "tick",
        at =
        @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/Fallable;"
                     + "onLand("
                     + "Lnet/minecraft/world/level/Level;"
                     + "Lnet/minecraft/core/BlockPos;"
                     + "Lnet/minecraft/world/level/block/state/BlockState;"
                     + "Lnet/minecraft/world/level/block/state/BlockState;"
                     + "Lnet/minecraft/world/entity/item/FallingBlockEntity;"
                     + ")V"
        )
    )
    @SuppressWarnings("UnreachableCode")
    private void anvilFallOnGround(CallbackInfo ci, @Local BlockPos blockPos) {
        if (this.level().isClientSide()) return;
        if (!this.blockState.is(BlockTags.ANVIL)) return;
        FallingBlockEntity entity = Util.cast(this);
        AnvilEvent.OnLand event = new AnvilEvent.OnLand(this.level(), blockPos, entity, this.anvilcraftLite$fallDistance);
        NeoForge.EVENT_BUS.post(event);
        if (event.isAnvilDamage()) {
            BlockState state = AnvilBlock.damage(this.blockState);
            if (state != null) {
                this.level().setBlockAndUpdate(blockPos, state);
            } else {
                this.level().setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                if (!this.isSilent()) this.level().levelEvent(1029, this.getOnPos(), 0);
                this.cancelDrop = true;
            }
        }
    }

    @Inject(
        method = "tick",
        at =
        @At(
            value = "INVOKE",
            ordinal = 0,
            target = "Lnet/minecraft/world/entity/item/FallingBlockEntity;level()Lnet/minecraft/world/level/Level;"
        )
    )
    private void anvilPerFallOnGround(CallbackInfo ci) {
        if (this.level().isClientSide()) return;
        if (this.onGround()) return;
        this.anvilcraftLite$fallDistance = this.fallDistance;
    }


    @SuppressWarnings("UnreachableCode")
    @Inject(
        method = "causeFallDamage",
        at =
        @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;"
                     + "getEntities("
                     + "Lnet/minecraft/world/entity/Entity;"
                     + "Lnet/minecraft/world/phys/AABB;"
                     + "Ljava/util/function/Predicate;"
                     + ")Ljava/util/List;"
        )
    )
    private void anvilHurtEntity(
        double p_397518_,
        float p_149643_,
        DamageSource p_149645_,
        CallbackInfoReturnable<Boolean> cir,
        @Local Predicate<Entity> predicate,
        @Local(ordinal = 1) float f
    ) {
        FallingBlockEntity anvil = Util.cast(this);
        Level level = this.level();
        List<Entity> entities = level.getEntities(this, this.getBoundingBox(), predicate);
        for (Entity entity : entities) {
            NeoForge.EVENT_BUS.post(new AnvilEvent.HurtEntity(anvil, this.getOnPos(), level, entity, f));
        }
    }
}
