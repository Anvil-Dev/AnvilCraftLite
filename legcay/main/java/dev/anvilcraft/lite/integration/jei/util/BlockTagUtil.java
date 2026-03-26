package dev.anvilcraft.lite.integration.jei.util;

import dev.anvilcraft.lib.v2.recipe.component.BlockStatePredicate;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class BlockTagUtil {
    /**
     * 根据方块配方输入，获取需要展示的工具提示
     *
     * @param input 方块标签或方块的配方输入
     * @return 展示方块对应的工具提示。
     */
    public static List<Component> getTooltipsForInput(BlockStatePredicate input) {
        List<Component> tooltipList = new ArrayList<>();
        tooltipList.add(
            input.constructStatesForRender().get((int) ((System.currentTimeMillis() / 1000) % input.constructStatesForRender().size()))
                .getBlock().getName());
        return tooltipList;
    }
}
