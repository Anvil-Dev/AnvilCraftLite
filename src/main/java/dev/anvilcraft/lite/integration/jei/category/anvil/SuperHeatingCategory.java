package dev.anvilcraft.lite.integration.jei.category.anvil;

import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import dev.anvilcraft.lite.integration.jei.drawable.DrawableBlockStateIcon;
import dev.anvilcraft.lite.integration.jei.util.JeiRecipeUtil;
import dev.anvilcraft.lite.integration.jei.util.JeiRenderHelper;
import dev.anvilcraft.lite.integration.jei.util.JeiSlotUtil;
import dev.anvilcraft.lite.recipe.anvil.wrap.SuperHeatingRecipe;
import dev.anvilcraft.lite.util.render.RenderHelper;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SuperHeatingCategory extends AbstractProgressCategory<SuperHeatingRecipe> {
    public SuperHeatingCategory(IGuiHelper helper) {
        super(
            helper,
            new DrawableBlockStateIcon(
                Blocks.CAULDRON.defaultBlockState(),
                Blocks.LAVA_CAULDRON.defaultBlockState()
            ),
            Component.translatable("gui.anvilcraft.category.super_heating")
        );
    }

    @Override
    public IRecipeType<RecipeHolder<SuperHeatingRecipe>> getRecipeType() {
        return AnvilCraftJeiPlugin.SUPER_HEATING;
    }

    @Override
    public void draw(
        RecipeHolder<SuperHeatingRecipe> recipeHolder,
        IRecipeSlotsView recipeSlotsView,
        GuiGraphics guiGraphics,
        double mouseX,
        double mouseY
    ) {
        Rect2i area = AnvilCraftJeiPlugin.AREA_WHEN_DRAW.get();
        int left = area.getX() - 9;
        int top = area.getY();
        SuperHeatingRecipe recipe = recipeHolder.value();
        float anvilYOffset = JeiRenderHelper.getAnvilAnimationOffset(timer);
        RenderHelper.renderMultipleBlocks(
            guiGraphics,
            List.of(Blocks.CAULDRON.defaultBlockState(), Blocks.LAVA_CAULDRON.defaultBlockState()),
            left + 81,
            top + 30,
            12
        );
        RenderHelper.renderSingleBlock(
            guiGraphics,
            Blocks.ANVIL.defaultBlockState(),
            left + 81,
            top + 12 + anvilYOffset,
            12
        );

        arrowIn.draw(guiGraphics, 54, 30);
        arrowOut.draw(guiGraphics, 92, 29);

        JeiSlotUtil.drawInputSlots(guiGraphics, slotDefault, recipe.getInputItems().size());
        if (JeiRecipeUtil.isChance(recipe.getResultItems())) {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotProbability, recipe.getResultItems().size());
        } else {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotDefault, recipe.getResultItems().size());
        }
    }

    @Override
    public void getTooltip(
        ITooltipBuilder tooltip,
        RecipeHolder<SuperHeatingRecipe> recipeHolder,
        IRecipeSlotsView recipeSlotsView,
        double mouseX,
        double mouseY
    ) {
        SuperHeatingRecipe recipe = recipeHolder.value();
        if (mouseX >= 72 && mouseX <= 90) {
            if (mouseY >= 24 && mouseY <= 43) {
                tooltip.add(recipe.getHasCauldron().getFluidCauldron().getName());
            }
            if (mouseY >= 34 && mouseY <= 53) {
                tooltip.add(Blocks.LAVA_CAULDRON.getName());
            }
        }
        if (mouseX >= 124 && mouseX <= 140) {
            if (mouseY >= 24 && mouseY <= 42) {
                if (recipe.getResultItems().isEmpty()) {
                    Component text = recipe.getHasCauldron().getTransformCauldron().getName();
                    tooltip.add(text);
                }
            }
        }
    }

    public static void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(
            AnvilCraftJeiPlugin.SUPER_HEATING,
            JeiRecipeUtil.getRecipeHoldersFromType(ModRecipeTypes.SUPER_HEATING_TYPE.get())
        );
    }

    public static void registerCraftingStations(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(AnvilCraftJeiPlugin.SUPER_HEATING, new ItemStack(Items.ANVIL));
        registration.addCraftingStation(AnvilCraftJeiPlugin.SUPER_HEATING, new ItemStack(Items.CAULDRON));
    }
}
