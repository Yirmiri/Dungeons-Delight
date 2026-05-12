package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.block.entity.WormouthBlockEntity;
import net.yirmiri.dungeonsdelight.common.resources.wormouth.WormouthMappings;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

//idk lets just make it waterlog for fun lol - artyrian
public class WormouthBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    protected static final Map<Direction, VoxelShape> SHAPE = Map.of(
            Direction.DOWN, Block.box(1, 2, 1, 15, 16, 15),
            Direction.UP, Block.box(1, 0, 1, 15, 14, 15),
            Direction.NORTH, Block.box(1, 1, 2, 15, 15, 16),
            Direction.EAST, Block.box(0, 1, 1, 14, 15, 15),
            Direction.SOUTH,  Block.box(1, 1, 0, 15, 15, 14),
            Direction.WEST, Block.box(2, 1, 1, 16, 15, 15)
    );

    public static final BooleanProperty EATING = BooleanProperty.create("full");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public WormouthBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.DOWN)
                .setValue(EATING, false)
        );
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        ResourceLocation loc = WormouthMappings.test(heldItem);

        if (loc != null && state.getBlock() instanceof WormouthBlock) {
            if (!level.isClientSide && level instanceof ServerLevel server) {
                LootParams lootparams = new LootParams.Builder(server).withParameter(LootContextParams.ORIGIN, pos.getCenter()).create(LootContextParamSets.CHEST);
                LootTable lootTable = level.getServer().getLootData().getLootTable(loc);
                List<ItemStack> list = lootTable.getRandomItems(lootparams);
                Direction rel = state.getValue(WormouthBlock.FACING);
                BlockPos goingto = pos.relative(rel, 2);

                for (ItemStack stack : list) {
                    ItemEntity itementity = new ItemEntity(
                            level,
                            pos.getX() + 0.5 + (rel.getStepX() * 0.8),
                            pos.getY() + 0.5 + (rel.getStepY() * 0.8),
                            pos.getZ() + 0.5 + (rel.getStepZ() * 0.8),
                            stack
                    );
                    double p0 = goingto.getX() - pos.getX();
                    double p1 = goingto.getY() - pos.getY();
                    double p2 = goingto.getZ() - pos.getZ();
                    double p3 = 0.1;
                    itementity.setDeltaMovement(p0 * p3, p1 * p3, p2 * p3);
                    level.addFreshEntity(itementity);
                }

                server.addFreshEntity(new ExperienceOrb(server, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, server.random.nextInt(4) + 1));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        Direction xd = state.getOptionalValue(FACING).orElse(Direction.DOWN);
        return SHAPE.get(xd);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER).setValue(FACING, context.getClickedFace());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING, EATING);
    }

    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new WormouthBlockEntity(pos, state); }

    @Override public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
