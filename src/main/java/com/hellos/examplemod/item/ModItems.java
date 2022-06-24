package com.hellos.examplemod.item;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.item.custom.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);


    //INGOTS, RAW, DUST VARIANTS

    //TIN

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));


    //COBALT

    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> COBALT_DUST = ITEMS.register("cobalt_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    //ALUMINUM

    public static final RegistryObject<Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> ALUMINUM_DUST = ITEMS.register("aluminum_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    //TOOLS

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB)).durability(64)));

    public static final RegistryObject<Item> INFINIUM_PICKAXE = ITEMS.register("infinium_pickaxe",
            () -> new SuperMegaSwordItem(ModTiers.TIN, 2, 3, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> ANALYTIC_PICKAXE = ITEMS.register("analytic_pickaxe",
            () -> new AnalyticPickaxeItem(ModTiers.TIN, 2, 3, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    //MISC

    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
            () -> new Item(new Item.Properties().food(ModFoods.CUCUMBER).tab((ModCreativeModeTab.TUTORIAL_TAB))));

    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke",
            () -> new CoalCokeItem(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB))));


    //Armor

    public static final RegistryObject<Item> HELLOS_BACKPACK = ITEMS.register("hellos_backpack",
            () -> new HellosArmorItem(ModArmorMaterials.TIN, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    //UNUSED CODE
//    public static final RegistryObject<Item> INFINUM = ITEMS.register("infinium",
//            () -> new Item(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB))));
//
//    public static final RegistryObject<Item> RAW_INFINUM = ITEMS.register("raw_infinium",
//            () -> new Item(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB))));

    public static void regiser(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
