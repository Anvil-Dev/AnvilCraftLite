package dev.anvilcraft.lite.entity;

import dev.anvilcraft.lite.api.extension.IItemEntityExtension;
import dev.anvilcraft.lite.init.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MagnetizedNodeEntity extends Entity {
    private static final EntityDataAccessor<BlockPos> DATA_BLOCK_POS = SynchedEntityData.defineId(
        MagnetizedNodeEntity.class,
        EntityDataSerializers.BLOCK_POS
    );
    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE = SynchedEntityData.defineId(
        MagnetizedNodeEntity.class,
        EntityDataSerializers.BLOCK_STATE
    );

    public BlockPos blockPos = BlockPos.ZERO;
    private BlockState blockState = Blocks.AIR.defaultBlockState();

    public MagnetizedNodeEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
        this.setInvulnerable(true);
    }

    public MagnetizedNodeEntity(Level level, Vec3 pos, BlockPos blockPos) {
        super(ModEntities.MAGNETIZED_NODE.get(), level);
        this.setPos(pos);
        this.xo = pos.x;
        this.yo = pos.y;
        this.zo = pos.z;
        this.noPhysics = true;
        this.setInvulnerable(true);
        this.blockPos = blockPos;
        this.blockState = level.getBlockState(blockPos);
    }

    @Override
    @SuppressWarnings("resource")
    public void tick() {
        Level level = this.level();
        super.tick();
        if (level instanceof ServerLevel serverLevel && !level.getBlockState(this.blockPos).is(this.blockState.getBlock())) {
            BlockState currentState = level.getBlockState(this.blockPos);
            if (!currentState.is(this.blockState.getBlock()) && (
                !currentState.is(BlockTags.CAULDRONS) || !this.blockState.is(BlockTags.CAULDRONS)
            )) {
                this.kill(serverLevel);
            }
        }
        AABB aabb = new AABB(
            blockPos.getX() - 0.01,
            blockPos.getY() - 0.01,
            blockPos.getZ() - 0.01,
            blockPos.getX() + 1.01,
            blockPos.getY() + 1.01,
            blockPos.getZ() + 1.01
        );
        level.getEntities(EntityType.ITEM, aabb, IItemEntityExtension::anvilcraftLite$isAdsorbable).forEach(entity -> {
            entity.teleportTo(position().x, position().y, position().z);
            entity.setDeltaMovement(Vec3.ZERO);
        });
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource damageSource, float amount) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        this.blockState = input.read("BlockState", BlockState.CODEC).orElse(Blocks.AIR.defaultBlockState());
        this.blockPos = input.read("BlockPos", BlockPos.CODEC).orElse(BlockPos.ZERO);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.store("BlockState", BlockState.CODEC, this.blockState);
        output.store("BlockPos", BlockPos.CODEC, this.blockPos);
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_BLOCK_POS, BlockPos.ZERO).define(DATA_BLOCK_STATE, Blocks.AIR.defaultBlockState());
    }

    @Override
    protected AABB makeBoundingBox(Vec3 position) {
        return EntityDimensions.scalable(0.25f, 0.25f).makeBoundingBox(this.position());
    }

    @Override
    public PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }
}
