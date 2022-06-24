package com.hellos.examplemod.datagen;

import com.hellos.examplemod.block.ModBlocks;
import com.hellos.examplemod.datagen.custom.MaceratorRecipeBuilder;
import com.hellos.examplemod.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.ALUMINUM_BLOCK.get())
                .define('A', ModItems.ALUMINUM_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_aluminum_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINUM_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.TIN_BLOCK.get())
                .define('A', ModItems.TIN_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_tin_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TIN_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModBlocks.COBALT_BLOCK.get())
                .define('A', ModItems.COBALT_INGOT.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_cobalt_ingot", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.COBALT_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ModItems.ALUMINUM_INGOT.get())
                .requires(ModBlocks.ALUMINUM_BLOCK.get())
                .unlockedBy("has_aluminum_block", inventoryTrigger(ItemPredicate.Builder.item()
                .of(ModBlocks.ALUMINUM_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        new MaceratorRecipeBuilder(ModItems.RAW_ALUMINUM.get(), ModItems.ALUMINUM_DUST.get(), 2, 36)
                .unlockedBy("has_raw_aluminum", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RAW_ALUMINUM.get()).build()))
                .save(pFinishedRecipeConsumer);

        new MaceratorRecipeBuilder(ModItems.RAW_COBALT.get(), ModItems.COBALT_DUST.get(), 2, 72)
                .unlockedBy("has_raw_cobalt", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RAW_COBALT.get()).build()))
                .save(pFinishedRecipeConsumer);

        new MaceratorRecipeBuilder(ModItems.RAW_TIN.get(), ModItems.TIN_DUST.get(), 2, 36)
                .unlockedBy("has_raw_tin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RAW_TIN.get()).build()))
                .save(pFinishedRecipeConsumer);

        new MaceratorRecipeBuilder(ModBlocks.RAW_TIN_BLOCK.get(), ModItems.TIN_DUST.get(), 18, 36*8)
                .unlockedBy("has_raw_tin_block", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.RAW_TIN_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

        new MaceratorRecipeBuilder(ModBlocks.RAW_COBALT_BLOCK.get(), ModItems.COBALT_DUST.get(), 18, 72*8)
                .unlockedBy("has_raw_cobalt_block", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.RAW_COBALT_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);


        new MaceratorRecipeBuilder(ModBlocks.RAW_ALUMINUM_BLOCK.get(), ModItems.ALUMINUM_DUST.get(), 18, 36*8)
                .unlockedBy("has_raw_aluminum_block", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.RAW_ALUMINUM_BLOCK.get()).build()))
                .save(pFinishedRecipeConsumer);

    }
}
