package com.hellos.examplemod.item;

import com.hellos.examplemod.TuturoialMod;
import com.hellos.examplemod.item.custom.DowsingRodItem;
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

    public static final RegistryObject<Item> RAW_INFINUM = ITEMS.register("raw_infinium",
            () -> new Item(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB))));

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB)).durability(64)));

    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
            () -> new Item(new Item.Properties().food(ModFoods.CUCUMBER).tab((ModCreativeModeTab.TUTORIAL_TAB))));

    public static void regiser(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
