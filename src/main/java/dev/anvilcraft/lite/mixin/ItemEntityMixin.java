package dev.anvilcraft.lite.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.anvilcraft.lite.init.block.ModBlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEntity.class)
abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/item/ItemEntity;getDeltaMovement()Lnet/minecraft/world/phys/Vec3;"
        )
    )
    @SuppressWarnings("resource")
    private Vec3 slowDown(ItemEntity instance, Operation<Vec3> original) {
        Vec3 vec3 = original.call(instance);
        double dy = 1.0;
        if (this.level().getBlockState(this.blockPosition()).is(ModBlockTags.MAGNET)) dy *= 0.2;
        return new Vec3(vec3.x, vec3.y * dy, vec3.z);
    }
}
