package dev.anvilcraft.lite.integration.jei.category.anvil;

import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import dev.anvilcraft.lite.integration.jei.util.JeiRecipeUtil;
import dev.anvilcraft.lite.integration.jei.util.JeiRenderHelper;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCrushRecipe;
import dev.anvilcraft.lite.util.RenderHelper;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
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
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockCrushCategory implements IRecipeCategory<RecipeHolder<BlockCrushRecipe>> {
    public static final int WIDTH = 162;
    public static final int HEIGHT = 64;

    private final IDrawable arrowDefault;
    private final IDrawable icon;
    private final Component title;
    private final ITickTimer timer;

    public BlockCrushCategory(IGuiHelper helper) {
        arrowDefault = JeiRenderHelper.getArrowDefault(helper);
        icon = helper.createDrawableItemStack(new ItemStack(Items.ANVIL));
        title = Component.translatable("gui.anvilcraft.category.block_crush");
        timer = helper.createTickTimer(30, 60, true);
    }

    @Override
    public IRecipeType<RecipeHolder<BlockCrushRecipe>> getRecipeType() {
        return AnvilCraftJeiPlugin.BLOCK_CRUSH;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<BlockCrushRecipe> recipeHolder, IFocusGroup focuses) {
        BlockCrushRecipe recipe = recipeHolder.value();
        builder.addInvisibleIngredients(RecipeIngredientRole.INPUT)
            .addItemStacks(recipe.getFirstInputBlock().getBlocks().stream().map(holder -> new ItemStack(holder.value())).toList());
        builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).add(new ItemStack(recipe.getFirstResultBlock().state().getBlock()));
    }

    @Override
    public void draw(
        RecipeHolder<BlockCrushRecipe> recipe,
        IRecipeSlotsView recipeSlotsView,
        GuiGraphics guiGraphics,
        double mouseX,
        double mouseY) {
        float anvilYOffset = JeiRenderHelper.getAnvilAnimationOffset(timer);
        arrowDefault.draw(guiGraphics, 73, 35);

        RenderHelper.renderBlock(
            guiGraphics,
            Blocks.ANVIL.defaultBlockState(),
            50,
            22 + anvilYOffset,
            20,
            12,
            RenderHelper.SINGLE_BLOCK);

        renderInput:
        {
            List<BlockState> input = recipe.value().getFirstInputBlock().constructStatesForRender();
            if (input.isEmpty()) break renderInput;
            BlockState renderedState = input.get((int) ((System.currentTimeMillis() / 1000) % input.size()));
            if (renderedState == null) break renderInput;
            RenderHelper.renderBlock(guiGraphics, renderedState, 50, 40, 10, 12, RenderHelper.SINGLE_BLOCK);
        }

        RenderHelper.renderBlock(
            guiGraphics, Blocks.ANVIL.defaultBlockState(), 110, 30, 10, 12, RenderHelper.SINGLE_BLOCK);
        RenderHelper.renderBlock(
            guiGraphics, recipe.value().getFirstResultBlock().state(), 110, 40, 0, 12, RenderHelper.SINGLE_BLOCK);
    }

    @Override
    public void getTooltip(
        ITooltipBuilder tooltip,
        RecipeHolder<BlockCrushRecipe> recipe,
        IRecipeSlotsView recipeSlotsView,
        double mouseX,
        double mouseY) {
        IRecipeCategory.super.getTooltip(tooltip, recipe, recipeSlotsView, mouseX, mouseY);
        if (mouseX >= 40 && mouseX <= 58) {
            if (mouseY >= 42 && mouseY <= 52) {
                tooltip.add(recipe.value().getFirstInputBlock().constructStatesForRender().getFirst().getBlock().getName());
            }
        }
        if (mouseX >= 100 && mouseX <= 120) {
            if (mouseY >= 42 && mouseY <= 52) {
                tooltip.add(recipe.value().getFirstResultBlock().state().getBlock().getName());
            }
        }
    }

    public static void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(
            AnvilCraftJeiPlugin.BLOCK_CRUSH,
            JeiRecipeUtil.getRecipeHoldersFromType(ModRecipeTypes.BLOCK_CRUSH_TYPE.get()));
    }

    public static void registerCraftingStations(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(AnvilCraftJeiPlugin.BLOCK_CRUSH, new ItemStack(Items.ANVIL));
    }
}
