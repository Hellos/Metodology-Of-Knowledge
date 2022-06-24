package com.hellos.examplemod.integration;


import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.ModBlocks;
import com.hellos.examplemod.recipe.MaceratorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class MaceratorRecipeCategory implements IRecipeCategory<MaceratorRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(TutorialMod.MOD_ID, "macerating");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(TutorialMod.MOD_ID, "textures/gui/macerator_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public MaceratorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModBlocks.MACERATOR.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends MaceratorRecipe> getRecipeClass() {
        return MaceratorRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Macerator");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull MaceratorRecipe recipe, @Nonnull IFocusGroup focusGroup) {

        builder.addSlot(RecipeIngredientRole.INPUT, 84, 11).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 84, 63).addItemStack(recipe.getResultItem());
    }
}
