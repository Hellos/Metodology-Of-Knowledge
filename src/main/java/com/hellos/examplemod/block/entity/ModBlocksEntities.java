package com.hellos.examplemod.block.entity;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.ModBlocks;
import com.hellos.examplemod.block.entity.custom.AssemblerBlockEntity;
import com.hellos.examplemod.block.entity.custom.MaceratorBlockEntity;
import com.hellos.examplemod.block.entity.custom.SmelterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocksEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TutorialMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MaceratorBlockEntity>> MACERATOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("macerator_block_entity", () ->
                    BlockEntityType.Builder.of(MaceratorBlockEntity::new, ModBlocks.MACERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<SmelterBlockEntity>> SMELTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("smelter_block_entity", () ->
                    BlockEntityType.Builder.of(SmelterBlockEntity::new, ModBlocks.SMELTER.get()).build(null));

    public static final RegistryObject<BlockEntityType<AssemblerBlockEntity>> ASSEMBLER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("assembler_block_entity", () ->
                    BlockEntityType.Builder.of(AssemblerBlockEntity::new, ModBlocks.ASSEMBLER_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
