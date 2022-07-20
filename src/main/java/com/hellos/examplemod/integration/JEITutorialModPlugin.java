package com.hellos.examplemod.integration;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.recipe.MaceratorRecipe;
import com.hellos.examplemod.recipe.SmelterRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.apache.http.cookie.SM;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEITutorialModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TutorialMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                MaceratorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                SmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<MaceratorRecipe> maceratorRecipeList = recipeManager.getAllRecipesFor(MaceratorRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(MaceratorRecipeCategory.UID, MaceratorRecipe.class), maceratorRecipeList);
        List<SmelterRecipe> smelterRecipeList = recipeManager.getAllRecipesFor(SmelterRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(SmelterRecipeCategory.UID, SmelterRecipe.class), smelterRecipeList);
    }
}
