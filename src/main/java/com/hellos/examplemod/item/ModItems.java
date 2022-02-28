package com.hellos.examplemod.item;

import com.hellos.examplemod.TuturoialMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TuturoialMod.MOD_ID);

    public static final RegistryObject<Item> INFINUM = ITEMS.register("infinium",
            () -> new Item(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB))));

    public static void regiser(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
