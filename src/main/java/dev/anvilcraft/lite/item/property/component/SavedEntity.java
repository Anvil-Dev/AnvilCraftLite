package dev.anvilcraft.lite.item.property.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.anvilcraft.lite.AnvilCraftLite;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record SavedEntity(CompoundTag tag, boolean isMonster) {
    public static final Codec<SavedEntity> CODEC = RecordCodecBuilder.create(ins -> ins.group(
            CompoundTag.CODEC.fieldOf("tag")
                .forGetter(SavedEntity::tag), Codec.BOOL.fieldOf("isMonster").forGetter(SavedEntity::isMonster)
        )
        .apply(ins, SavedEntity::new));

    public static final StreamCodec<ByteBuf, SavedEntity> STREAM_CODEC = StreamCodec.composite(
        ByteBufCodecs.COMPOUND_TAG,
        SavedEntity::tag,
        ByteBufCodecs.BOOL,
        SavedEntity::isMonster,
        SavedEntity::new
    );

    @Nullable
    public Entity toEntity(Level level) {
        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(AnvilCraftLite.LOGGER)) {
            ValueInput tagValueInput = TagValueInput.create(reporter, level.registryAccess(), tag);
            Optional<EntityType<?>> optional = EntityType.by(tagValueInput);
            if (optional.isEmpty()) return null;
            EntityType<?> type = optional.get();
            Entity entity = type.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
            if (entity == null) return null;
            entity.load(tagValueInput);
            return entity;
        }
    }

    @SuppressWarnings("resource")
    public static SavedEntity fromMob(Mob entity) {
        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(AnvilCraftLite.LOGGER)) {
            TagValueOutput tagValueOutput = TagValueOutput.createWithContext(reporter, entity.level().registryAccess());
            entity.saveAsPassenger(tagValueOutput);
            CompoundTag entityTag = tagValueOutput.buildResult();
            entityTag.remove(Entity.TAG_UUID);
            entityTag.remove(Entity.TAG_POS);
            entityTag.remove(Entity.TAG_MOTION);
            entityTag.remove(Entity.TAG_FIRE);
            entityTag.remove(Entity.TAG_AIR);
            entityTag.remove(Entity.TAG_FALL_DISTANCE);
            return new SavedEntity(entityTag, !entity.getType().getCategory().isFriendly());
        }
    }
}
