package com.hellos.examplemod.entity.armor;

import com.hellos.examplemod.TuturoialMod;
import com.hellos.examplemod.item.custom.HellosArmorItem;
import net.minecraft.client.gui.components.toasts.TutorialToast;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HellosArmorModel extends AnimatedGeoModel<HellosArmorItem> {
    @Override
    public ResourceLocation getModelLocation(HellosArmorItem object) {
        return new ResourceLocation(TuturoialMod.MOD_ID, "geo/hellos_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HellosArmorItem object) {
        return new ResourceLocation(TuturoialMod.MOD_ID, "textures/models/armor/hellos_armor_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HellosArmorItem animatable) {
        return new ResourceLocation(TuturoialMod.MOD_ID, "animations/armor_animation.json");
    }
}
