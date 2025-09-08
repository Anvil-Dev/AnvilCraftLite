package dev.anvilcraft.lite.item.better;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Consumer;

/**
 * 用于替换部分方法以去除<b>无用</b>的警告，<br>
 * 如将
 * {@link Item#appendHoverText(ItemStack, TooltipContext, TooltipDisplay, Consumer, TooltipFlag) appendHoverText}
 * 替换为
 * {@link BetterItem#appendTooltip(ItemStack, TooltipContext, TooltipDisplay, Consumer, TooltipFlag) appendTooltip}
 * 以去除其弃用警告
 */
@SuppressWarnings("deprecation")
public abstract class BetterItem extends Item {
    public BetterItem(Properties properties) {
        super(properties);
    }

    @Override
    @ApiStatus.NonExtendable
    public void appendHoverText(
        ItemStack stack,
        TooltipContext context,
        TooltipDisplay display,
        Consumer<Component> adder,
        TooltipFlag flag
    ) {
        super.appendHoverText(stack, context, display, adder, flag);
        this.appendTooltip(stack, context, display, adder, flag);
    }

    public void appendTooltip(
        ItemStack stack,
        TooltipContext context,
        TooltipDisplay display,
        Consumer<Component> adder,
        TooltipFlag flag
    ) {
    }
}
