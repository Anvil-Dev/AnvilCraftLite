package dev.anvilcraft.lite.integration.jei.category.anvil;

import dev.anvilcraft.lite.integration.jei.AnvilCraftJeiPlugin;
import dev.anvilcraft.lite.integration.jei.drawable.DrawableBlockStateIcon;
import dev.anvilcraft.lite.integration.jei.recipe.MeshRecipeGroup;
import dev.anvilcraft.lite.integration.jei.util.JeiRecipeUtil;
import dev.anvilcraft.lite.integration.jei.util.JeiRenderHelper;
import dev.anvilcraft.lite.util.render.RenderHelper;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MeshRecipeCategory implements IRecipeCategory<MeshRecipeGroup> {


    public static final int WIDTH = 162;
    public static final int ROW_START = 44;

    private final IDrawable slotDefault;
    private final IDrawable slotProbability;
    private final IDrawable icon;
    private final Component title;
    private final ITickTimer timer;

    private final IDrawable arrowIn;

    public MeshRecipeCategory(IGuiHelper helper) {
        this.slotDefault = JeiRenderHelper.getSlotDefault(helper);
        this.slotProbability = JeiRenderHelper.getSlotProbability(helper);
        this.icon =
            new DrawableBlockStateIcon(Blocks.ANVIL.defaultBlockState(), Blocks.SCAFFOLDING.defaultBlockState());
        this.title = Component.translatable("gui.anvilcraft.category.mesh");
        this.timer = helper.createTickTimer(30, 60, true);

        this.arrowIn = JeiRenderHelper.getArrowInput(helper);
    }

    @Override
    public IRecipeType<MeshRecipeGroup> getRecipeType() {
        return AnvilCraftJeiPlugin.MESH;
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
        return ROW_START + MeshRecipeGroup.maxRows * 18;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MeshRecipeGroup recipe, IFocusGroup focuses) {
        recipe.ingredient().items()
            .ifPresent(items -> builder.addSlot(RecipeIngredientRole.INPUT, 37, 14).add(Ingredient.of(items)));

        for (int i = 0; i < recipe.results().size(); i++) {
            MeshRecipeGroup.Result result = recipe.results().get(i);
            IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.OUTPUT, 1 + (i % 9) * 18, 1 + ROW_START + 18 * (i / 9))
                .add(result.item());
            JeiRecipeUtil.addTooltips(slot, result.item().getCount(), result.provider());
        }
    }

    @Override
    public void draw(
        MeshRecipeGroup recipe,
        IRecipeSlotsView recipeSlotsView,
        GuiGraphics guiGraphics,
        double mouseX,
        double mouseY
    ) {
        Rect2i area = AnvilCraftJeiPlugin.AREA_WHEN_DRAW.get();
        int left = area.getX() - 9;
        int top = area.getY() - 7;
        float anvilYOffset = JeiRenderHelper.getAnvilAnimationOffset(timer);
        RenderHelper.renderSingleBlock(
            guiGraphics,
            Blocks.SCAFFOLDING.defaultBlockState(),
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

        arrowIn.draw(guiGraphics, 55, 17);
        slotDefault.draw(guiGraphics, 36, 13);

        for (int row = 0; row < MeshRecipeGroup.maxRows; row++) {
            for (int column = 0; column < 9; column++) {
                slotProbability.draw(guiGraphics, column * 18, ROW_START + row * 18);
            }
        }
    }

    public static void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(AnvilCraftJeiPlugin.MESH, MeshRecipeGroup.getAllRecipesGrouped());
    }

    public static void registerCraftingStations(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(AnvilCraftJeiPlugin.MESH, new ItemStack(Items.ANVIL));
        registration.addCraftingStation(AnvilCraftJeiPlugin.MESH, new ItemStack(Items.SCAFFOLDING));
    }
}
