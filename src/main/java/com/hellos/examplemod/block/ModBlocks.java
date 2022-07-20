package com.hellos.examplemod.block;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.custom.*;
import com.hellos.examplemod.item.ModCreativeModeTab;
import com.hellos.examplemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);


    //ORES

    public static final RegistryObject<Block> COBALT_ORE = registerBlock("cobalt_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(ModMaterial.HARD)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(ModMaterial.HARD)
                    .strength(4.5f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(ModMaterial.EASY)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(ModMaterial.EASY)
                    .strength(4.5f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> ALUMINUM_ORE = registerBlock("aluminum_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(ModMaterial.EASY)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = registerBlock("deepslate_aluminum_ore",
            () -> new OreBlock(BlockBehaviour.Properties.of(ModMaterial.EASY)
                    .strength(4.5f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)), ModCreativeModeTab.TUTORIAL_TAB);

    //INGOTS AND RAW VARIANTS OF BLOCKS

    public static final RegistryObject<Block> COBALT_BLOCK = registerBlock("cobalt_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> RAW_COBALT_BLOCK = registerBlock("raw_cobalt_block",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE)
                    .strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(new SoundType(3.0F, 0.5F, SoundEvents.DEEPSLATE_BREAK, SoundEvents.DEEPSLATE_STEP, SoundEvents.DEEPSLATE_PLACE, SoundEvents.DEEPSLATE_HIT, SoundEvents.DEEPSLATE_FALL))),
            ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(new SoundType(3.0F, 0.5F, SoundEvents.DEEPSLATE_BREAK, SoundEvents.DEEPSLATE_STEP, SoundEvents.DEEPSLATE_PLACE, SoundEvents.DEEPSLATE_HIT, SoundEvents.DEEPSLATE_FALL))),
            ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerBlock("aluminum_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.COPPER)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = registerBlock("raw_aluminum_block",
            () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5.0f, 6.0f).requiresCorrectToolForDrops().sound(new SoundType(3.0F, 0.5F, SoundEvents.DEEPSLATE_BREAK, SoundEvents.DEEPSLATE_STEP, SoundEvents.DEEPSLATE_PLACE, SoundEvents.DEEPSLATE_HIT, SoundEvents.DEEPSLATE_FALL))),
            ModCreativeModeTab.TUTORIAL_TAB);

    //MACHINES
    public static final RegistryObject<Block> MACERATOR = registerBlock("macerator",
            () -> new MaceratorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(new SoundType(3.0F, 0.5F, SoundEvents.NETHERITE_BLOCK_BREAK, SoundEvents.NETHERITE_BLOCK_STEP, SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT, SoundEvents.NETHERITE_BLOCK_FALL))
                    .strength(5f).requiresCorrectToolForDrops()){
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    pEntity.hurt(DamageSource.FALL, 0.5f);
                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            }, ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> SMELTER = registerBlock("smelter",
            () -> new SmelterBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(new SoundType(3.0F, 0.5F, SoundEvents.NETHERITE_BLOCK_BREAK, SoundEvents.NETHERITE_BLOCK_STEP, SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT, SoundEvents.NETHERITE_BLOCK_FALL))
                    .strength(5f).requiresCorrectToolForDrops()){
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    pEntity.hurt(DamageSource.FALL, 0.5f);
                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            }, ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> ENERGY_STORAGE_BLOCK = registerBlockWithoutBlockItem("energy_storage_block",
            () -> new EnergyStorageBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(new SoundType(3.0F, 0.5F, SoundEvents.NETHERITE_BLOCK_BREAK, SoundEvents.NETHERITE_BLOCK_STEP, SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT, SoundEvents.NETHERITE_BLOCK_FALL))
                    .strength(5f).requiresCorrectToolForDrops().noOcclusion()),
            ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> ENERGY_PIPE_BLOCK = registerBlock("energy_pipe_block",
            () -> new EnergyPipeBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).sound(new SoundType(3.0F, 0.5F, SoundEvents.NETHERITE_BLOCK_BREAK, SoundEvents.NETHERITE_BLOCK_STEP, SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT, SoundEvents.NETHERITE_BLOCK_FALL))
                    .strength(5f).requiresCorrectToolForDrops().noOcclusion()),
            ModCreativeModeTab.TUTORIAL_TAB);


    //MISC
        public static final RegistryObject<Block> CUSTOM_GLASS = registerBlock("custom_glass",
            () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).noOcclusion().sound(SoundType.GLASS)
                    .strength(9f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }


    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
