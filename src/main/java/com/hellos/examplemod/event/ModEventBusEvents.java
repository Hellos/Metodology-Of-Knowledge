package com.hellos.examplemod.event;


import com.hellos.examplemod.TuturoialMod;
import com.hellos.examplemod.entity.armor.HellosArmorRenderer;
import com.hellos.examplemod.item.custom.HellosArmorItem;
import com.hellos.examplemod.recipe.MaceratorRecipe;
import com.hellos.examplemod.recipe.SmelterRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = TuturoialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event){
        Registry.register(Registry.RECIPE_TYPE, MaceratorRecipe.Type.ID, MaceratorRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, SmelterRecipe.Type.ID, SmelterRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event){
        GeoArmorRenderer.registerArmorRenderer(HellosArmorItem.class, new HellosArmorRenderer());
    }
}
