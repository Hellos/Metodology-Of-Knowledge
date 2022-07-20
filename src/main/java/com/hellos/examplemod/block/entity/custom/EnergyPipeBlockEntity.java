package com.hellos.examplemod.block.entity.custom;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.custom.EnergyPipeBlock;
import com.hellos.examplemod.block.custom.MaceratorBlock;
import com.hellos.examplemod.block.entity.ModBlocksEntities;
import com.hellos.examplemod.block.entity.util.MkEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nonnull;

public class EnergyPipeBlockEntity extends BlockEntity {
    protected final ContainerData data;

    public final MkEnergyStorage energyStorage;
    private int capacity = 16000, maxExtract = 64, maxReceive = 64;


    private LazyOptional<MkEnergyStorage> energy;

    public EnergyPipeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlocksEntities.ENERGY_PIPE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.energyStorage = createEnergyStorage();
        this.energy = LazyOptional.of(() -> this.energyStorage);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return EnergyPipeBlockEntity.this.getEnergy();
                    case 1: return EnergyPipeBlockEntity.this.energyStorage.getMaxEnergyStored();
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: EnergyPipeBlockEntity.this.energyStorage.setEnergy(value); break;
                    case 1: EnergyPipeBlockEntity.this.energyStorage.setMaxEnergy(value); break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY){
            return this.energy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        this.energy.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.putInt("energy_pipe.energy", getEnergy());

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.energyStorage.setEnergy(nbt.getInt("energy_pipe.energy"));
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, EnergyPipeBlockEntity pBlockEntity) {

        if(pBlockEntity.energyStorage.getEnergyStored() > 0) {
            pBlockEntity.outputEnergy();
        } else {

        }
    }

    public void outputEnergy(){
        if(this.energyStorage.getEnergyStored() >= this.maxExtract && this.energyStorage.canExtract()){
            for(final var direction: getDirections(level)){
                final BlockEntity blockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
                TutorialMod.LOGGER.info("{}", this.level.getBlockState(this.worldPosition.relative(direction)).getBlock().getRegistryName().toString());
                if(blockEntity == null){
                    continue;
                }
                blockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    if(blockEntity != this && storage.getEnergyStored() < storage.getMaxEnergyStored()){
                        final int toSend = EnergyPipeBlockEntity.this.energyStorage.extractEnergy(this.maxExtract, false);
                        final int received = storage.receiveEnergy(toSend, false);

                        EnergyPipeBlockEntity.this.energyStorage.setEnergy(
                                EnergyPipeBlockEntity.this.energyStorage.getEnergyStored() + toSend - received
                        );

                    }
                });
            }
        }
    }


    public Direction[] getDirections(Level pLevel){
        var facing = pLevel.getBlockState(worldPosition).getValue(EnergyPipeBlock.FACING);
        var axis = pLevel.getBlockState(worldPosition).getValue(EnergyPipeBlock.AXIS);
        switch (axis) {
            case Y:
            switch (facing) {
                case EAST:
                case WEST:
                    return new Direction[]{Direction.EAST, Direction.WEST};
                case SOUTH:
                case NORTH:
                default:
                    return new Direction[]{Direction.SOUTH, Direction.NORTH};
            }
            case X:
            case Z:
            default:
                return new Direction[]{Direction.UP, Direction.DOWN};
        }
    }


    private MkEnergyStorage createEnergyStorage(){
        return new MkEnergyStorage(this, capacity, this.maxReceive, this.maxExtract, 0);
    }

    public int getEnergy(){
        return this.energyStorage.getEnergyStored();
    }
}
