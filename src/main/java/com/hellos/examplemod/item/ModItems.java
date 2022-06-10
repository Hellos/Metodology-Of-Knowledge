package com.hellos.examplemod.item;

import com.hellos.examplemod.TuturoialMod;
import com.hellos.examplemod.item.custom.CoalCokeItem;
import com.hellos.examplemod.item.custom.DowsingRodItem;
import com.hellos.examplemod.item.custom.HellosArmorItem;
import com.hellos.examplemod.item.custom.SuperMegaSwordItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TuturoialMod.MOD_ID);


    //INGOTS AND RAW VARIANTS

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    //TOOLS

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB)).durability(64)));

    public static final RegistryObject<Item> INFINIUM_PICKAXE = ITEMS.register("infinium_pickaxe",
            () -> new SuperMegaSwordItem(ModTiers.TIN, 2, 3, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB))) ;

    //MISC

    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
            () -> new Item(new Item.Properties().food(ModFoods.CUCUMBER).tab((ModCreativeModeTab.TUTORIAL_TAB))));

    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke",
            () -> new CoalCokeItem(new Item.Properties().tab((ModCreativeModeTab.TUTORIAL_TAB))));






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
