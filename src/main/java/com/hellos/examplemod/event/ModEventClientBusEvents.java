package com.hellos.examplemod.event;


import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.entity.ModBlocksEntities;
//import com.hellos.examplemod.block.entity.client.AssemblerBlockRenderer;
import com.hellos.examplemod.block.entity.client.EnergyStorageBlockRenderer;
import com.hellos.examplemod.entity.armor.HellosArmorRenderer;
import com.hellos.examplemod.item.custom.HellosArmorItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event){
        GeoArmorRenderer.registerArmorRenderer(HellosArmorItem.class, new HellosArmorRenderer());
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event){
//        event.registerBlockEntityRenderer(ModBlocksEntities.ASSEMBLER_BLOCK_ENTITY.get(), AssemblerBlockRenderer::new);
        event.registerBlockEntityRenderer(ModBlocksEntities.ENERGY_STORAGE_BLOCK_ENTITY.get(), EnergyStorageBlockRenderer::new);
    }
}
