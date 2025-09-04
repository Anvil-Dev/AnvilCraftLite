package dev.anvilcraft.lite.api.event;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.EntityEvent;

@Getter
public class AnvilEvent extends EntityEvent {
    private final FallingBlockEntity entity;

    public AnvilEvent(FallingBlockEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Getter
    public static class OnLand extends AnvilEvent {
        private final Level level;
        private final BlockPos pos;
        private final double fallDistance;

        @Setter
        private boolean isAnvilDamage = false;

        /**
         * 铁砧落地事件
         *
         * @param level        世界
         * @param pos          位置
         * @param entity       铁砧
         * @param fallDistance 下落距离
         */
        public OnLand(Level level, BlockPos pos, FallingBlockEntity entity, double fallDistance) {
            super(entity);
            this.level = level;
            this.pos = pos;
            this.fallDistance = fallDistance;
        }
    }
}
