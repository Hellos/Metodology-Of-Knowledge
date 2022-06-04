package com.hellos.examplemod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier INFINIUM = new ForgeTier(3,1812, 12f, 2f, 30,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(ModItems.INFINUM.get()));
}
