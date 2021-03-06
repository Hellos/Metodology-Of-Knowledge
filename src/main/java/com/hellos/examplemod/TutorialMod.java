package com.hellos.examplemod;

import com.hellos.examplemod.block.ModBlocks;
import com.hellos.examplemod.block.entity.ModBlocksEntities;
import com.hellos.examplemod.item.ModItems;
import com.hellos.examplemod.recipe.ModRecipes;
import com.hellos.examplemod.screen.ModMenuTypes;
import com.hellos.examplemod.screen.energy_storage.EnergyStorageMenu;
import com.hellos.examplemod.screen.energy_storage.EnergyStorageScreen;
import com.hellos.examplemod.screen.macerator.MaceratorScreen;
import com.hellos.examplemod.screen.smelter.SmelterScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TutorialMod.MOD_ID)
public class TutorialMod
{
    public static final String MOD_ID = "tutorialmod";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public TutorialMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.regiser(eventBus);
        ModBlocks.register(eventBus);
        ModBlocksEntities.register(eventBus);

        ModMenuTypes.register(eventBus);

        ModRecipes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::serverSetup);

        GeckoLib.initialize();
        // Register the enqueueIMC method for modloading
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CUSTOM_GLASS.get(), RenderType.translucent());

        MenuScreens.register(ModMenuTypes.MACERATOR_MENU.get(), MaceratorScreen::new);
        MenuScreens.register(ModMenuTypes.SMELTER_MENU.get(), SmelterScreen::new);
        MenuScreens.register(ModMenuTypes.ENERGY_STORAGE_MENU.get(), EnergyStorageScreen::new);
    }

    private void serverSetup(FMLDedicatedServerSetupEvent event){

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
