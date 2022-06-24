package com.hellos.examplemod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModelWithDiffSides("macerator");
        ModelWithDiffSidesOn("macerator");
    }

    public BlockModelBuilder ModelWithDiffSides(String name){
        return this.cube(name,
                new ResourceLocation(modid,"block/"+name+"_bottom"),
                new ResourceLocation(modid,"block/"+name+"_top"),
                new ResourceLocation(modid,"block/"+name+"_face"),
                new ResourceLocation(modid,"block/"+name+"_back"),
                new ResourceLocation(modid,"block/"+name+"_lside"),
                new ResourceLocation(modid,"block/"+name+"_rside")
                );
    }

    public BlockModelBuilder ModelWithDiffSidesOn(String name){
        return this.cube(name+"_on",
                new ResourceLocation(modid,"block/"+name+"_bottom"),
                new ResourceLocation(modid,"block/"+name+"_top"),
                new ResourceLocation(modid,"block/"+name+"_face_on"),
                new ResourceLocation(modid,"block/"+name+"_back"),
                new ResourceLocation(modid,"block/"+name+"_lside"),
                new ResourceLocation(modid,"block/"+name+"_rside")
        );
    }

    @Override
    public BlockModelBuilder cube(String name, ResourceLocation down, ResourceLocation up, ResourceLocation north, ResourceLocation south, ResourceLocation east, ResourceLocation west) {
        return withExistingParent(name, "cube")
                .texture("down", down)
                .texture("up", up)
                .texture("north", north)
                .texture("south", south)
                .texture("east", east)
                .texture("west", west)
                .texture("particle", east);
    }
}
