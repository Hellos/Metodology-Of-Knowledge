package com.hellos.examplemod.block.entity.custom;

import com.hellos.examplemod.block.custom.SmelterBlock;
import com.hellos.examplemod.block.entity.ModBlocksEntities;
import com.hellos.examplemod.block.entity.util.MkEnergyStorage;
import com.hellos.examplemod.recipe.SmelterRecipe;
import com.hellos.examplemod.screen.smelter.SmelterMenu;
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

import javax.annotation.Nonnull;
import java.util.Optional;

public class SmelterBlockEntity extends BlockEntity implements MenuProvider {

    public final MkEnergyStorage energyStorage;
    private int capacity = 256000, maxRecieve = 64;
    private LazyOptional<MkEnergyStorage> energy;

    private final ItemStackHandler itemHandler = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;

    public SmelterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlocksEntities.SMELTER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.energyStorage = createEnergyStorage();
        this.energy = LazyOptional.of(() -> this.energyStorage);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return SmelterBlockEntity.this.progress;
                    case 1: return SmelterBlockEntity.this.maxProgress;
                    case 2: return SmelterBlockEntity.this.getEnergy();
                    case 3: return SmelterBlockEntity.this.energyStorage.getMaxEnergyStored();
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: SmelterBlockEntity.this.progress = value; break;
                    case 1: SmelterBlockEntity.this.maxProgress = value; break;
                    case 2: SmelterBlockEntity.this.energyStorage.setEnergy(value); break;
                    case 3: SmelterBlockEntity.this.energyStorage.setMaxEnergy(value); break;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Smelter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new SmelterMenu(pContainerId, pInventory, this, this.data);
    }

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
        tag.putInt("smelter.progress", progress);
        tag.putInt("smelter.energy", getEnergy());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("smelter.progress");
        this.energyStorage.setEnergy(nbt.getInt("smelter.energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SmelterBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasEnergy(pBlockEntity)) {
            pBlockEntity.progress++;
            pBlockEntity.energyStorage.setEnergy(pBlockEntity.getEnergy()-32);
            pLevel.setBlock(pPos, pState.setValue(SmelterBlock.TURNED_ON, Boolean.TRUE), 3);
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pLevel.setBlock(pPos, pState.setValue(SmelterBlock.TURNED_ON, Boolean.FALSE), 3);
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(SmelterBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SmelterRecipe> match = level.getRecipeManager()
                .getRecipeFor(SmelterRecipe.Type.INSTANCE, inventory, level);

        return canInsertAmountIntoOutputSlot(inventory) && match.isPresent()
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasWaterInWaterSlot(entity) && hasToolsInToolSlot(entity);
    }

    private static boolean hasEnergy(SmelterBlockEntity entity){
        return entity.getEnergy() >  0;
    }

    private static boolean hasWaterInWaterSlot(SmelterBlockEntity entity) {
        return true;
    }

    private static boolean hasToolsInToolSlot(SmelterBlockEntity entity) {
        return true;
    }

    private static void craftItem(SmelterBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SmelterRecipe> match = level.getRecipeManager()
                .getRecipeFor(SmelterRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            entity.itemHandler.setStackInSlot(1, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(1).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(1).getItem() == output.getItem() || inventory.getItem(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(1).getMaxStackSize() > inventory.getItem(1).getCount();
    }

    private MkEnergyStorage createEnergyStorage(){
        return new MkEnergyStorage(this, capacity, this.maxRecieve, 0, 0);
    }

    public int getEnergy(){
        return this.energyStorage.getEnergyStored();
    }
}

