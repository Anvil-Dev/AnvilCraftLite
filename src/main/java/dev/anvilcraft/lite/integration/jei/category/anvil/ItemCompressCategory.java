package dev.anvilcraft.lite.integration.jei.category.anvil;

import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import dev.anvilcraft.lite.integration.jei.drawable.DrawableBlockStateIcon;
import dev.anvilcraft.lite.integration.jei.util.JeiRecipeUtil;
import dev.anvilcraft.lite.integration.jei.util.JeiRenderHelper;
import dev.anvilcraft.lite.integration.jei.util.JeiSlotUtil;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCompressRecipe;
import dev.anvilcraft.lite.util.render.RenderHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemCompressCategory extends AbstractProgressCategory<ItemCompressRecipe> {
    public ItemCompressCategory(IGuiHelper helper) {
        super(
            helper,
            new DrawableBlockStateIcon(Blocks.ANVIL.defaultBlockState(), Blocks.CAULDRON.defaultBlockState()),
            Component.translatable("gui.anvilcraft.category.item_compress")
        );
    }

    @Override
    public IRecipeType<RecipeHolder<ItemCompressRecipe>> getRecipeType() {
        return AnvilCraftJeiPlugin.ITEM_COMPRESS;
    }

    @Override
    public void draw(
        RecipeHolder<ItemCompressRecipe> recipeHolder,
        IRecipeSlotsView recipeSlotsView,
        GuiGraphics guiGraphics,
        double mouseX,
        double mouseY
    ) {
        Rect2i area = AnvilCraftJeiPlugin.AREA_WHEN_DRAW.get();
        int left = area.getX() - 9;
        int top = area.getY() - 7;
        ItemCompressRecipe recipe = recipeHolder.value();
        float anvilYOffset = JeiRenderHelper.getAnvilAnimationOffset(timer);
        RenderHelper.renderSingleBlock(
            guiGraphics,
            Blocks.CAULDRON.defaultBlockState(),
            left + 81,
            top + 40,
            12
        );
        RenderHelper.renderSingleBlock(
            guiGraphics,
            Blocks.ANVIL.defaultBlockState(),
            left + 81,
            top + 22 + anvilYOffset,
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

    public static void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(
            AnvilCraftJeiPlugin.ITEM_COMPRESS, JeiRecipeUtil.getRecipeHoldersFromType(ModRecipeTypes.ITEM_COMPRESS_TYPE.get()));
    }

    public static void registerCraftingStations(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(AnvilCraftJeiPlugin.ITEM_COMPRESS, new ItemStack(Items.ANVIL));
        registration.addCraftingStation(AnvilCraftJeiPlugin.ITEM_COMPRESS, new ItemStack(Items.CAULDRON));
    }
}
