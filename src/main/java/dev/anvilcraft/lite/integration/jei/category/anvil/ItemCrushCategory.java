package dev.anvilcraft.lite.integration.jei.category.anvil;

import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import dev.anvilcraft.lite.integration.jei.drawable.DrawableBlockStateIcon;
import dev.anvilcraft.lite.integration.jei.util.JeiRecipeUtil;
import dev.anvilcraft.lite.integration.jei.util.JeiRenderHelper;
import dev.anvilcraft.lite.integration.jei.util.JeiSlotUtil;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCrushRecipe;
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
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.Half;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemCrushCategory extends AbstractProgressCategory<ItemCrushRecipe> {
    public ItemCrushCategory(IGuiHelper helper) {
        super(
            helper,
            new DrawableBlockStateIcon(
                Blocks.ANVIL.defaultBlockState(),
                Blocks.IRON_TRAPDOOR.defaultBlockState()
                    .setValue(TrapDoorBlock.HALF, Half.TOP)
            ),
            Component.translatable("gui.anvilcraft.category.item_crush")
        );
    }

    @Override
    public IRecipeType<RecipeHolder<ItemCrushRecipe>> getRecipeType() {
        return AnvilCraftJeiPlugin.ITEM_CRUSH;
    }

    @Override
    public void draw(
        RecipeHolder<ItemCrushRecipe> recipeHolder,
        IRecipeSlotsView recipeSlotsView,
        GuiGraphics guiGraphics,
        double mouseX,
        double mouseY
    ) {
        Rect2i area = AnvilCraftJeiPlugin.AREA_WHEN_DRAW.get();
        int left = area.getX() - 9;
        int top = area.getY() - 7;
        ItemCrushRecipe recipe = recipeHolder.value();
        float anvilYOffset = JeiRenderHelper.getAnvilAnimationOffset(timer);
        RenderHelper.renderSingleBlock(
            guiGraphics,
            Blocks.IRON_TRAPDOOR.defaultBlockState()
                .setValue(TrapDoorBlock.HALF, Half.TOP),
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
        arrowOutputFromBelow.draw(guiGraphics, 92, 29);

        JeiSlotUtil.drawInputSlots(guiGraphics, slotDefault, recipe.getInputItems().size());
        if (JeiRecipeUtil.isChance(recipe.getResultItems())) {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotProbability, recipe.getResultItems().size());
        } else {
            JeiSlotUtil.drawOutputSlots(guiGraphics, slotDefault, recipe.getResultItems().size());
        }
    }

    public static void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(
            AnvilCraftJeiPlugin.ITEM_CRUSH,
            JeiRecipeUtil.getRecipeHoldersFromType(ModRecipeTypes.ITEM_CRUSH_TYPE.get()));
    }

    public static void registerCraftingStations(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(AnvilCraftJeiPlugin.ITEM_CRUSH, new ItemStack(Items.ANVIL));
        registration.addCraftingStation(AnvilCraftJeiPlugin.ITEM_CRUSH, new ItemStack(Blocks.IRON_TRAPDOOR));
    }
}
