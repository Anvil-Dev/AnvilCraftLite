package dev.anvilcraft.lite.integration.jei.category.anvil;

import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import dev.anvilcraft.lite.integration.jei.drawable.DrawableBlockStateIcon;
import dev.anvilcraft.lite.integration.jei.util.JeiRecipeUtil;
import dev.anvilcraft.lite.integration.jei.util.JeiRenderHelper;
import dev.anvilcraft.lite.integration.jei.util.JeiSlotUtil;
import dev.anvilcraft.lite.recipe.anvil.wrap.StampingRecipe;
import dev.anvilcraft.lite.util.RenderHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonBaseBlock;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class StampingCategory extends AbstractProgressCategory<StampingRecipe> {
    public StampingCategory(IGuiHelper helper) {
        super(
            helper,
            new DrawableBlockStateIcon(
                Blocks.ANVIL.defaultBlockState(), Blocks.PISTON.defaultBlockState().setValue(PistonBaseBlock.FACING, Direction.UP)),
            Component.translatable("gui.anvilcraft.category.stamping")
        );
    }

    @Override
    public IRecipeType<RecipeHolder<StampingRecipe>> getRecipeType() {
        return AnvilCraftJeiPlugin.STAMPING;
    }

    @Override
    public void draw(
        RecipeHolder<StampingRecipe> recipeHolder,
        IRecipeSlotsView recipeSlotsView,
        GuiGraphics guiGraphics,
        double mouseX,
        double mouseY
    ) {
        StampingRecipe recipe = recipeHolder.value();
        float anvilYOffset = JeiRenderHelper.getAnvilAnimationOffset(timer);
        RenderHelper.renderBlock(
            guiGraphics,
            Blocks.ANVIL.defaultBlockState(),
            81,
            22 + anvilYOffset,
            20,
            12,
            RenderHelper.SINGLE_BLOCK
        );
        RenderHelper.renderBlock(
            guiGraphics,
            Blocks.PISTON.defaultBlockState().setValue(PistonBaseBlock.FACING, Direction.UP),
            81,
            40,
            0,
            12,
            RenderHelper.SINGLE_BLOCK
        );

        arrowIn.draw(guiGraphics, 54, 30);
        arrowOutputFromBelow.draw(guiGraphics, 92, 29);

        // TODO: 等待重构StampingUniqueItemsRecipe（目前仅多合一模板使用），重构后直接取消注释并修复import即可
//        if (recipe instanceof StampingUniqueItemsRecipe) {
//            ItemStack input = recipe.getItemIngredients().getFirst()
//                .getItems()[(int) System.currentTimeMillis() / 1000 % recipe.getItemIngredients().size()];
//            JeiSlotUtil.drawInputSlots(guiGraphics, slot, input.getCount());
//        } else {
        JeiSlotUtil.drawInputSlots(guiGraphics, slotDefault, recipe.getInputItems().size());
//        }

        if (JeiRecipeUtil.isChance(recipe.getResultItems())) {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotProbability, recipe.getResultItems().size());
        } else {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotDefault, recipe.getResultItems().size());
        }
    }

    public static void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(
            AnvilCraftJeiPlugin.STAMPING,
            JeiRecipeUtil.getRecipeHoldersFromType(ModRecipeTypes.STAMPING_TYPE.get())
        );
    }

    public static void registerCraftingStations(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(AnvilCraftJeiPlugin.STAMPING, new ItemStack(Items.ANVIL));
        registration.addCraftingStation(AnvilCraftJeiPlugin.STAMPING, new ItemStack(Blocks.PISTON));
    }
}
