package com.hellos.examplemod.datagen;


import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.datagen.custom.ModBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new ModRecipeProvider(generator));
        generator.addProvider(new ModLootTableProvider(generator));
        generator.addProvider(new ModBlockTagsProvider(generator, existingFileHelper));
        generator.addProvider(new ModBlockModelProvider(generator, TutorialMod.MOD_ID, existingFileHelper));
    }
}
