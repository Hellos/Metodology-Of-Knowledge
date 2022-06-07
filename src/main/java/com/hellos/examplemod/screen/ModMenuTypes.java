package com.hellos.examplemod.screen;

import com.hellos.examplemod.TuturoialMod;
import com.hellos.examplemod.screen.macerator.MaceratorMenu;
import com.hellos.examplemod.screen.smelter.SmelterMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, TuturoialMod.MOD_ID);

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }

    public static final RegistryObject<MenuType<MaceratorMenu>> MACERATOR_MENU =
            registryMenuType(MaceratorMenu::new, "macerator_menu");

    public static final RegistryObject<MenuType<SmelterMenu>> SMELTER_MENU =
            registryMenuType(SmelterMenu::new, "smelter_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registryMenuType(IContainerFactory<T> factory,
                                                                                                 String name){
        return MENUS.register(name, ()-> IForgeMenuType.create(factory));
    }

}
