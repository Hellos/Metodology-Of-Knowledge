package com.hellos.examplemod.item;

import com.hellos.examplemod.block.ModBlocks;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

public class ModCreativeModeTab {
    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab("tutorialtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.MACERATOR.get());
        }
    };
}
