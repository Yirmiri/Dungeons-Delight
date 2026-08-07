package net.yirmiri.dungeonsdelight.common.block.entity.fire;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDStats;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LivingFireBlock extends BaseFireBlock implements EntityBlock {
    public static final int MAX_AGE = 15;
    public static final IntegerProperty  AGE = BlockStateProperties.AGE_15;
    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;
    public static final BooleanProperty UP = PipeBlock.UP;
    private static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter(entry -> entry.getKey() != Direction.DOWN).collect(Util.toMap());
    private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private final Map<BlockState, VoxelShape> shapesCache;

    public LivingFireBlock(BlockBehaviour.Properties properties) {
        super(properties, 1.0F);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(AGE, 0)
                        .setValue(NORTH, false)
                        .setValue(EAST, false)
                        .setValue(SOUTH, false)
                        .setValue(WEST, false)
                        .setValue(UP, false)
        );
        this.shapesCache = ImmutableMap.copyOf((Map)this.stateDefinition.getPossibleStates().stream().filter((state) -> state.getValue(AGE) == 0).collect(Collectors.toMap(Function.identity(), this::calculateShape)));
    }

    private VoxelShape calculateShape(BlockState state) {
        VoxelShape shape = Shapes.empty();

        if (state.getValue(UP)) {
            shape = UP_AABB;
        }
        if (state.getValue(NORTH)) {
            shape = Shapes.or(shape, NORTH_AABB);
        }
        if (state.getValue(SOUTH)) {
            shape = Shapes.or(shape, SOUTH_AABB);
        }
        if (state.getValue(EAST)) {
            shape = Shapes.or(shape, EAST_AABB);
        }
        if (state.getValue(WEST)) {
            shape = Shapes.or(shape, WEST_AABB);
        }

        return shape.isEmpty() ? DOWN_AABB : shape;
    }

    public static BlockState getState(BlockGetter reader, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = reader.getBlockState(blockpos);

        return SpiritFireBlock.canSurviveOnBlock(blockstate) ? DDBlocks.SPIRIT_FIRE.get().defaultBlockState()
                : (DDBlocks.LIVING_FIRE).get().defaultBlockState();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, level, currentPos) ? state : Blocks.AIR.defaultBlockState();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shapesCache.get(state.setValue(AGE, 0));
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        int experienceStored = 8;
        if (entity instanceof Player player) {
            if (player.totalExperience > 0 && player.isAlive() && !player.getAbilities().instabuild) {
                if (level.getBlockEntity(pos) instanceof LivingFireBlockEntity blockEntity && blockEntity.canStoreExperience()) {
                    if (level.getGameTime() % 20 == 0 && !level.isClientSide) {
                        player.giveExperiencePoints(-experienceStored);
                        player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.75F, -1.0F);

                        blockEntity.addExperience(experienceStored);

                        player.hurt(DDDamageTypes.getDamageSource(level, DDDamageTypes.LIFE_STEAL), 1.0F);
                        player.awardStat(DDStats.EXPERIENCE_STORED.get(), experienceStored);
                    }
                }
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof LivingFireBlockEntity blockEntity) {
                if (level.isClientSide || blockEntity.getStoredExperience() <= 0) return;

                ExperienceOrb.award((ServerLevel) level, Vec3.atCenterOf(pos), (blockEntity.getStoredExperience() * 3) / 4);
                blockEntity.setStoredExperience(0);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LivingFireBlockEntity(pos, state);
    }

    @Override
    protected boolean canBurn(BlockState blockState) {
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
    }

    protected BlockState getStateForPlacement(BlockGetter level, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState belowState = level.getBlockState(below);

        if (!belowState.isFaceSturdy(level, below, Direction.UP)) {
            BlockState state = this.defaultBlockState();

//            for (Direction direction : Direction.values()) {
//                BooleanProperty prop = PROPERTY_BY_DIRECTION.get(direction);
//                if (prop != null) {
//                    state = state.setValue(prop,
//                            level.getBlockState(pos.relative(direction))
//                                    .isFaceSturdy(level, pos.relative(direction), direction.getOpposite()));
//                }
//            }
            return state;
        }

        return this.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        return level.getBlockState(below).isFaceSturdy(level, below, Direction.UP);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.scheduleTick(pos, this, getFireTickDelay(random));

        if (!state.canSurvive(level, pos)) {
            level.removeBlock(pos, false);
            return;
        }

        if (level.isRaining() && this.isNearRain(level, pos)) {
            level.removeBlock(pos, false);
            return;
        }

        int age = state.getValue(AGE);
        if (age < MAX_AGE) {
            state = state.setValue(AGE, age + 1);
            level.setBlock(pos, state, 4);
        }
    }

    protected boolean isNearRain(Level level, BlockPos pos) {
        return level.isRainingAt(pos)
                || level.isRainingAt(pos.west())
                || level.isRainingAt(pos.east())
                || level.isRainingAt(pos.north())
                || level.isRainingAt(pos.south());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, getFireTickDelay(level.random));
    }

    private static int getFireTickDelay(RandomSource random) {
        return 30 + random.nextInt(10);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, NORTH, EAST, SOUTH, WEST, UP);
    }
}