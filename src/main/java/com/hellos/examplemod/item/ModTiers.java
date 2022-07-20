package com.hellos.examplemod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier TIN = new ForgeTier(3,1812, 12f, 2f, 30,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.TIN_INGOT.get()));
}
