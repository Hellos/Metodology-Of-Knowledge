package com.hellos.examplemod.block.entity.client;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.entity.custom.EnergyStorageBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EnergyStorageBlockModel extends AnimatedGeoModel<EnergyStorageBlockEntity> {

    @Override
    public ResourceLocation getModelLocation(EnergyStorageBlockEntity object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "geo/energy_storage_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EnergyStorageBlockEntity object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "textures/block/energy_storage_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EnergyStorageBlockEntity animatable) {
        return new ResourceLocation(TutorialMod.MOD_ID, "animations/energy_storage_block.animation.json");
    }
}
