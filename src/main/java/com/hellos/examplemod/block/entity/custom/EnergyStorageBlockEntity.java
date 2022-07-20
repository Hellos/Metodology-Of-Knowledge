package com.hellos.examplemod.block.entity.custom;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.custom.EnergyStorageBlock;
import com.hellos.examplemod.block.custom.MaceratorBlock;
import com.hellos.examplemod.block.entity.ModBlocksEntities;
import com.hellos.examplemod.block.entity.util.MkEnergyStorage;
import com.hellos.examplemod.recipe.MaceratorRecipe;
import com.hellos.examplemod.screen.energy_storage.EnergyStorageMenu;
import com.hellos.examplemod.screen.macerator.MaceratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.Optional;

public class EnergyStorageBlockEntity extends BlockEntity implements IAnimatable, MenuProvider {
    private AnimationFactory factory = new AnimationFactory(this);

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    public final MkEnergyStorage energyStorage;
    private int capacity = 1024000, maxExtract = 64;


    private LazyOptional<MkEnergyStorage> energy;

    public EnergyStorageBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlocksEntities.ENERGY_STORAGE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.energyStorage = createEnergyStorage();
        this.energy = LazyOptional.of(() -> this.energyStorage);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return EnergyStorageBlockEntity.this.getEnergy();
                    case 1: return EnergyStorageBlockEntity.this.energyStorage.getMaxEnergyStored();
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: EnergyStorageBlockEntity.this.energyStorage.setEnergy(value); break;
                    case 1: EnergyStorageBlockEntity.this.energyStorage.setMaxEnergy(value); break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(5){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        if(cap == CapabilityEnergy.ENERGY){
            return this.energy.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        this.energy.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("energy_storage.energy", getEnergy());
        
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.energyStorage.setEnergy(nbt.getInt("energy_storage.energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, EnergyStorageBlockEntity pBlockEntity) {

        if(pBlockEntity.energyStorage.getEnergyStored() > 0) {
            charge(pBlockEntity);
            pBlockEntity.outputEnergy();
        } else {

        }
    }

    public void outputEnergy(){
        if(this.energyStorage.getEnergyStored() >= this.maxExtract && this.energyStorage.canExtract()){
            for(final var direction: Direction.values()){
                final BlockEntity blockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if(blockEntity == null){
                    continue;
                }
                if(blockEntity instanceof EnergyPipeBlockEntity){
                    final var pipeDirection =  ((EnergyPipeBlockEntity) blockEntity).getDirections(level);
                    final BlockEntity firstPipeDirection = this.level.getBlockEntity(blockEntity.getBlockPos().relative(pipeDirection[0]));
                    final BlockEntity secondPipeDirection = this.level.getBlockEntity(blockEntity.getBlockPos().relative(pipeDirection[1]));
                    if(firstPipeDirection instanceof EnergyStorageBlockEntity){
                        TutorialMod.LOGGER.info("Everything should work!");
                    }
                    else if(secondPipeDirection instanceof EnergyStorageBlockEntity){
                        TutorialMod.LOGGER.info("Everything fine!");
                    }
                    else{
                        continue;
                    }
                }
                blockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    if(blockEntity != this && storage.getEnergyStored() < storage.getMaxEnergyStored()){
                        final int toSend = EnergyStorageBlockEntity.this.energyStorage.extractEnergy(this.maxExtract, false);
                        final int received = storage.receiveEnergy(toSend, false);

                        EnergyStorageBlockEntity.this.energyStorage.setEnergy(
                                EnergyStorageBlockEntity.this.energyStorage.getEnergyStored() + toSend - received
                        );

                    }
                });
            }
        }
    }

    private static void charge(EnergyStorageBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        for(int i = 0; i < inventory.getContainerSize(); i++){
            ItemStack itemStack = inventory.getItem(i);
            if(itemStack.isRepairable() && itemStack.isDamaged()){
                int damageValue = itemStack.getDamageValue();
                damageValue--;
                inventory.getItem(i).setDamageValue(damageValue);
            }
        }

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new EnergyStorageMenu(pContainerId, pInventory, this, this.data);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("EStorage");
    }



    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<EnergyStorageBlockEntity>
                (this, "controller", 0, this::predicate));
    }

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event){
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;
        controller.setAnimation(new AnimationBuilder().addAnimation("blinking", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private MkEnergyStorage createEnergyStorage(){
        return new MkEnergyStorage(this, capacity, 64, this.maxExtract, capacity);
    }

    public int getEnergy(){
        return this.energyStorage.getEnergyStored();
    }


}
