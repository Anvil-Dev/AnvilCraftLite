package dev.anvilcraft.lite.integration.jei.drawable;

import mezz.jei.api.gui.inputs.IJeiGuiEventListener;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.navigation.ScreenRectangle;

import java.util.function.Consumer;

@MethodsReturnNonnullByDefault
public class JeiButton<T> implements IJeiGuiEventListener {
    private final Consumer<T> onClickCallback;
    private final int x;
    private final int y;
    private final int size;
    private final T metadataKey;

    public JeiButton(int x, int y, int size, Consumer<T> onClickCallback, T metadataKey) {
        this.onClickCallback = onClickCallback;
        this.x = x;
        this.y = y;
        this.size = size;
        this.metadataKey = metadataKey;
    }

    @Override
    public ScreenRectangle getArea() {
        return new ScreenRectangle(new ScreenPosition(x, y), size, size);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            onClickCallback.accept(metadataKey);
            return true;
        }
        return false;
    }
}
