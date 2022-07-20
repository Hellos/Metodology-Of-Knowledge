package com.hellos.examplemod.item.client;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.item.custom.EnergyStorageBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EnergyStorageBlockItemModel extends AnimatedGeoModel<EnergyStorageBlockItem> {
    @Override
    public ResourceLocation getModelLocation(EnergyStorageBlockItem object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "geo/energy_storage_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EnergyStorageBlockItem object) {
        return new ResourceLocation(TutorialMod.MOD_ID, "textures/block/energy_storage_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EnergyStorageBlockItem animatable) {
        return new ResourceLocation(TutorialMod.MOD_ID, "animations/energy_storage_block.animation.json");
    }
}
