package dev.anvilcraft.lite.mixin.integration;

import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import mezz.jei.library.gui.recipes.RecipeLayout;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeLayout.class)
public abstract class JeiRecipeLayoutMixin {
    @Shadow public abstract Rect2i getRect();

    @Inject(
        method = "drawRecipe",
        at = @At(
            value = "INVOKE",
            target = "Lmezz/jei/api/recipe/category/IRecipeCategory;draw("
                     + "Ljava/lang/Object;Lmezz/jei/api/gui/ingredient/IRecipeSlotsView;"
                     + "Lnet/minecraft/client/gui/GuiGraphics;DD)V"
        )
    )
    private void getArea(GuiGraphics guiGraphics, int mouseX, int mouseY, CallbackInfo ci) {
        AnvilCraftJeiPlugin.AREA_WHEN_DRAW.set(this.getRect());
    }
}
