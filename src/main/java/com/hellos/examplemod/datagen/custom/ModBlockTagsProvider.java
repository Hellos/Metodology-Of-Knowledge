package com.hellos.examplemod.datagen.custom;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.ModBlocks;
import com.hellos.examplemod.block.ModMaterial;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;


import java.nio.file.Path;
import java.util.function.Supplier;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags() {
        registerPickaxeMineable();
    }

    @Override
    protected Path getPath(ResourceLocation id) {
        return super.getPath(id);
    }

    @Override
    public String getName() {
        return "Block Tags";
    }

    private void registerPickaxeMineable()
    {
        TagAppender<Block> tag = tag(BlockTags.MINEABLE_WITH_PICKAXE);
        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(b -> {
                    Material material = b.defaultBlockState().getMaterial();
                    return material==Material.STONE||material==Material.METAL||material==ModMaterial.EASY||material==ModMaterial.MEDIUM||material==ModMaterial.HARD;
                })
                .forEach(tag::add);

        tag = tag(BlockTags.NEEDS_STONE_TOOL);
        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(b -> {
                    Material material = b.defaultBlockState().getMaterial();
                    return material== ModMaterial.EASY;
                })
                .forEach(tag::add);

        tag = tag(BlockTags.NEEDS_IRON_TOOL);
        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(b -> {
                    Material material = b.defaultBlockState().getMaterial();
                    return material== ModMaterial.MEDIUM;
                })
                .forEach(tag::add);

        tag = tag(BlockTags.NEEDS_DIAMOND_TOOL);
        ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(b -> {
                    Material material = b.defaultBlockState().getMaterial();
                    return material== ModMaterial.HARD;
                })
                .forEach(tag::add);


    }


    private void setMiningLevel(Supplier<Block> block, Tiers level)
    {
        TagKey<Block> tag = switch(level)
                {
                    case STONE -> BlockTags.NEEDS_STONE_TOOL;
                    case IRON -> BlockTags.NEEDS_IRON_TOOL;
                    case DIAMOND -> BlockTags.NEEDS_DIAMOND_TOOL;
                    default -> throw new IllegalArgumentException("No tag available for "+level.name());
                };
        tag(tag).add(block.get());
    }
}
