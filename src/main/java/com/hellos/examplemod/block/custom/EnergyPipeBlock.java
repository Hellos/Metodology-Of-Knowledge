package com.hellos.examplemod.block.custom;

import com.hellos.examplemod.TutorialMod;
import com.hellos.examplemod.block.entity.ModBlocksEntities;
import com.hellos.examplemod.block.entity.custom.EnergyPipeBlockEntity;
import com.hellos.examplemod.block.entity.custom.MaceratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class EnergyPipeBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty TURNED_ON = BooleanProperty.create("turned_on");

    private static final VoxelShape X_AXIS_AABB = Block.box(5,0,4,11,16,13);
    private static final VoxelShape Y_AXIS_AABB = Block.box(0,4,5,16,13,11);
    private static final VoxelShape Y_AXIS_AABB_ALT = Block.box(5,4,0,11,13,16);
    private static final VoxelShape Z_AXIS_AABB = Block.box(4,0,5,13,16,11);


    public BlockState rotate(BlockState pState, Rotation pRot) {
        return rotatePillar(pState, pRot);
    }

    public static BlockState rotatePillar(BlockState pState, Rotation pRotation) {
        switch(pRotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch((Direction.Axis)pState.getValue(AXIS)) {
                    case X:
                        return pState.setValue(AXIS, Direction.Axis.Z);
                    case Z:
                        return pState.setValue(AXIS, Direction.Axis.X);
                    default:
                        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
                }
            default:
                return pState;
        }
    }

    public EnergyPipeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pState.getValue(EnergyPipeBlock.TURNED_ON) == Boolean.FALSE){
            pLevel.setBlock(pPos, pState.setValue(EnergyPipeBlock.TURNED_ON, Boolean.TRUE), 3);
        }
        else{
            pLevel.setBlock(pPos, pState.setValue(MaceratorBlock.TURNED_ON, Boolean.FALSE), 3);
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch((Direction.Axis)pState.getValue(AXIS)) {
            case X:
            default:
                return X_AXIS_AABB;
            case Z:
                return Z_AXIS_AABB;
            case Y:
                switch (pState.getValue(FACING)){
                    case NORTH:
                    case SOUTH:
                        return Y_AXIS_AABB_ALT;
                    default:
                        return Y_AXIS_AABB;
                }

        }
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        return super.getStateForPlacement(pContext)
                .setValue(WATERLOGGED, Boolean.valueOf(flag))
                .setValue(AXIS, pContext.getClickedFace().getAxis())
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(TURNED_ON, Boolean.FALSE);

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS).add(WATERLOGGED).add(FACING).add(TURNED_ON);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new EnergyPipeBlockEntity(pPos, pState);
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlocksEntities.ENERGY_PIPE_BLOCK_ENTITY.get(),
                EnergyPipeBlockEntity::tick);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}
