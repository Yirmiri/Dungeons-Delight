package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

public class TerrorPretaBlock extends Block {
    public static final int MAX_VALUE = 7;
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    public static final IntegerProperty SHADED = IntegerProperty.create("shaded", 0, MAX_VALUE);
    protected static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 15.0F, 16.0F);

    public TerrorPretaBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(MOISTURE, 0)
                .setValue(SHADED, 7)
        );
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.UP && !state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 1);
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos.above());
        return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Blocks.MUD.defaultBlockState() : super.getStateForPlacement(context);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            turnToBlock(Blocks.MUD, null, state, level, pos);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int moisture = state.getValue(MOISTURE);
        int shaded = state.getValue(SHADED);

        if (!maintainedByFluid(level, pos) && !level.isRainingAt(pos.above())) {
            if (moisture > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            } else if (!shouldMaintainMorbidMush(level, pos)) {
                turnToBlock(Blocks.MUD, null, state, level, pos);
            }
        } else if (moisture < MAX_VALUE) {
            level.setBlock(pos, state.setValue(MOISTURE, MAX_VALUE), 2);
        }

        if (level.getBrightness(LightLayer.SKY, pos) > 0 && level.isDay()) {
            if (shaded > 0) {
                level.setBlock(pos, state.setValue(SHADED, shaded - 1), 2);
            } else if (!shouldMaintainMorbidMush(level, pos)) {
                turnToBlock(Blocks.DIRT, null, state, level, pos);
                if (level.getBlockState(pos.above()).isAir()) {
                    level.setBlock(pos.above(), Blocks.FIRE.defaultBlockState(), 3);
                }
            }
        } else if (shaded < MAX_VALUE) {
            level.setBlock(pos, state.setValue(SHADED, MAX_VALUE), 2);
        }
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!level.isClientSide && level.random.nextFloat() < fallDistance - 0.5F && entity instanceof LivingEntity && (entity instanceof Player || level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F) {
            turnToBlock(Blocks.MUD, entity, state, level, pos);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    public static void turnToBlock(Block block, Entity entity, BlockState state, Level level, BlockPos pos) {
        BlockState blockstate = pushEntitiesUp(state, block.defaultBlockState(), level, pos);
        level.setBlockAndUpdate(pos, blockstate);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockstate));
    }

    private static boolean shouldMaintainMorbidMush(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    private static boolean maintainedByFluid(LevelReader level, BlockPos pos) {
        for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (level.getFluidState(blockpos).is(DDTags.FluidT.MAINTAINS_TERROR_PRETA)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MOISTURE, SHADED);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }
}
