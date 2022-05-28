package com.hellos.examplemod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import com.hellos.examplemod.TuturoialMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;



public class ModTags {
    public static class Blocks {
        public static final Tags.IOptionalNamedTag<Block> DOWSING_ROD_VALUABLES = tag("dowsing_rod_valuables");

        private static Tags.IOptionalNamedTag<Block> tag(String name){
            return BlockTags.createOptional(new ResourceLocation(TuturoialMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> forgeTag(String name){
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        private static Tags.IOptionalNamedTag<Item> tag(String name){
            return ItemTags.createOptional(new ResourceLocation(TuturoialMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> forgeTag(String name){
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
