package com.hellos.examplemod.datagen;

import com.hellos.examplemod.block.ModBlocks;
import com.hellos.examplemod.datagen.custom.MaceratorRecipeBuilder;
import com.hellos.examplemod.datagen.custom.SmelterRecipeBuilder;
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

        new SmelterRecipeBuilder(ModItems.COBALT_DUST.get(), ModItems.COBALT_INGOT.get(), 1)
                .unlockedBy("has_cobalt_dust", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.COBALT_DUST.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModItems.RAW_COBALT.get(), ModItems.COBALT_INGOT.get(), 1)
                .unlockedBy("has_raw_cobalt", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RAW_COBALT.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModBlocks.DEEPSLATE_COBALT_ORE.get(), ModItems.COBALT_INGOT.get(), 1)
                .unlockedBy("has_deepslate_cobalt_ore", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DEEPSLATE_COBALT_ORE.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModBlocks.COBALT_ORE.get(), ModItems.COBALT_INGOT.get(), 1)
                .unlockedBy("has_cobalt_ore", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.COBALT_ORE.get()).build()))
                .save(pFinishedRecipeConsumer);


        new SmelterRecipeBuilder(ModItems.TIN_DUST.get(), ModItems.TIN_INGOT.get(), 1)
                .unlockedBy("has_tin_dust", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.TIN_DUST.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModItems.RAW_TIN.get(), ModItems.TIN_INGOT.get(), 1)
                .unlockedBy("has_raw_tin", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RAW_TIN.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModBlocks.DEEPSLATE_TIN_ORE.get(), ModItems.TIN_INGOT.get(), 1)
                .unlockedBy("has_deepslate_tin_ore", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DEEPSLATE_TIN_ORE.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModBlocks.TIN_ORE.get(), ModItems.TIN_INGOT.get(), 1)
                .unlockedBy("has_tin_ore", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.TIN_ORE.get()).build()))
                .save(pFinishedRecipeConsumer);


        new SmelterRecipeBuilder(ModItems.ALUMINUM_DUST.get(), ModItems.ALUMINUM_INGOT.get(), 1)
                .unlockedBy("has_aluminum_dust", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ALUMINUM_DUST.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModItems.RAW_ALUMINUM.get(), ModItems.ALUMINUM_INGOT.get(), 1)
                .unlockedBy("has_raw_aluminum", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RAW_ALUMINUM.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get(), ModItems.ALUMINUM_INGOT.get(), 1)
                .unlockedBy("has_deepslate_aluminum_ore", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.DEEPSLATE_ALUMINUM_ORE.get()).build()))
                .save(pFinishedRecipeConsumer);

        new SmelterRecipeBuilder(ModBlocks.ALUMINUM_ORE.get(), ModItems.ALUMINUM_INGOT.get(), 1)
                .unlockedBy("has_aluminum_ore", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.ALUMINUM_ORE.get()).build()))
                .save(pFinishedRecipeConsumer);


    }
}
