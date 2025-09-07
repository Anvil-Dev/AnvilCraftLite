package dev.anvilcraft.lite.integration.jei.drawable;

import dev.anvilcraft.lite.util.render.RenderHelper;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DrawableBlockStateIcon implements IDrawable {
    private final BlockState upState;
    private final BlockState downState;

    public DrawableBlockStateIcon(BlockState upState, BlockState downState) {
        this.upState = upState;
        this.downState = downState;
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
        RenderHelper.renderMultipleBlocks(
            guiGraphics,
            List.of(this.upState, this.downState),
            xOffset + 2.75f,
            yOffset - 1,
            7
        );
    }
}
