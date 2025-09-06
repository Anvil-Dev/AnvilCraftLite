package dev.anvilcraft.lite.integration.jei.category.anvil;

import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import dev.anvilcraft.lite.integration.jei.drawable.DrawableBlockStateIcon;
import dev.anvilcraft.lite.integration.jei.util.JeiRecipeUtil;
import dev.anvilcraft.lite.integration.jei.util.JeiRenderHelper;
import dev.anvilcraft.lite.integration.jei.util.JeiSlotUtil;
import dev.anvilcraft.lite.recipe.anvil.predicate.block.HasCauldron;
import dev.anvilcraft.lite.recipe.anvil.wrap.SuperHeatingRecipe;
import dev.anvilcraft.lite.recipe.component.HasCauldronSimple;
import dev.anvilcraft.lite.util.CauldronUtil;
import dev.anvilcraft.lite.util.RenderHelper;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

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
        SuperHeatingRecipe recipe = recipeHolder.value();
        float anvilYOffset = JeiRenderHelper.getAnvilAnimationOffset(timer);
        RenderHelper.renderBlock(
            guiGraphics,
            Blocks.ANVIL.defaultBlockState(),
            81,
            12 + anvilYOffset,
            20,
            12,
            RenderHelper.SINGLE_BLOCK
        );
        RenderHelper.renderBlock(guiGraphics, Blocks.CAULDRON.defaultBlockState(), 81, 30, 10, 12, RenderHelper.SINGLE_BLOCK);
        RenderHelper.renderBlock(
            guiGraphics,
            Blocks.LAVA_CAULDRON.defaultBlockState(),
            81,
            40,
            0,
            12,
            RenderHelper.SINGLE_BLOCK
        );

        arrowIn.draw(guiGraphics, 54, 20);
        arrowOut.draw(guiGraphics, 92, 19);

        JeiSlotUtil.drawInputSlots(guiGraphics, slotDefault, recipe.getInputItems().size());
        if (JeiRecipeUtil.isChance(recipe.getResultItems())) {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotProbability, recipe.getResultItems().size());
        } else {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotDefault, recipe.getResultItems().size());
        }

        HasCauldronSimple hasCauldron = recipe.getHasCauldron();
        if (!HasCauldron.isNotEmpty(hasCauldron.transform())) return;
        BlockState cauldron = CauldronUtil.fullState(hasCauldron.getTransformCauldron());
        RenderHelper.renderBlock(guiGraphics, cauldron, 133, 30, 0, 12, RenderHelper.SINGLE_BLOCK);
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
