package com.hellos.examplemod.block.entity.client;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.entity.custom.AssemblerBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AssemblerBlockModel extends AnimatedGeoModel<AssemblerBlockEntity> {
    @Override
    public ResourceLocation getModelLocation(AssemblerBlockEntity object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "geo/assembler_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AssemblerBlockEntity object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "textures/block/assembler_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AssemblerBlockEntity animatable) {
        return new ResourceLocation(TutorialMod.MOD_ID, "animations/assembler_block.animation.json");
    }
}
