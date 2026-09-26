package net.yirmiri.dungeonsdelight.common.block;

import net.azurune.runiclib.core.register.RLEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import java.util.Map;

public class SpikeTrapBlock extends FaceAttachedHorizontalDirectionalBlock {
    private static final Map<Direction, VoxelShape> SHAPE_CLOSED = Map.of(
            Direction.DOWN, Shapes.join(Block.box(0, 14, 0, 16, 16, 16), Block.box(1, 12, 1, 15, 14, 15), BooleanOp.OR),
            Direction.UP, Shapes.join(Block.box(0, 0, 0, 16, 2, 16), Block.box(1, 2, 1, 15, 4, 15), BooleanOp.OR),
            Direction.NORTH, Shapes.join(Block.box(0, 0, 14, 16, 16, 16), Block.box(1, 1, 12, 15, 15, 14), BooleanOp.OR),
            Direction.EAST, Shapes.join(Block.box(0, 0, 0, 2, 16, 16), Block.box(2, 1, 1, 4, 15, 15), BooleanOp.OR),
            Direction.SOUTH,  Shapes.join(Block.box(0, 0, 0, 16, 16, 2), Block.box(1, 1, 2, 15, 15, 4), BooleanOp.OR),
            Direction.WEST, Shapes.join(Block.box(14, 0, 0, 16, 16, 16), Block.box(12, 1, 1, 14, 15, 15), BooleanOp.OR)
    );
    private static final Map<Direction, VoxelShape> SHAPE_OPEN = Map.of(
            Direction.DOWN, Shapes.join(Block.box(0, 14, 0, 16, 16, 16), Block.box(1, 8, 1, 15, 14, 15), BooleanOp.OR),
            Direction.UP, Shapes.join(Block.box(0, 0, 0, 16, 2, 16), Block.box(1, 2, 1, 15, 8, 15), BooleanOp.OR),
            Direction.NORTH, Shapes.join(Block.box(0, 0, 14, 16, 16, 16), Block.box(1, 1, 8, 15, 15, 14), BooleanOp.OR),
            Direction.EAST, Shapes.join(Block.box(0, 0, 0, 2, 16, 16), Block.box(2, 1, 1, 8, 15, 15), BooleanOp.OR),
            Direction.SOUTH,  Shapes.join(Block.box(0, 0, 0, 16, 16, 2), Block.box(1, 1, 2, 15, 15, 8), BooleanOp.OR),
            Direction.WEST, Shapes.join(Block.box(14, 0, 0, 16, 16, 16), Block.box(8, 1, 1, 14, 15, 15), BooleanOp.OR)
    );

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty ON = BooleanProperty.create("on");

    public SpikeTrapBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(ON, false)
                .setValue(POWERED, false)
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living && state.getValue(ON)) {
            living.hurt(DDDamageTypes.getDamageSource(entity.level(), DDDamageTypes.SPIKE_TRAP), 3);

            int seconds = 5;
            if (level.getDifficulty() == Difficulty.NORMAL) seconds = 8;
            if (level.getDifficulty() == Difficulty.HARD) seconds = 13;

            living.addEffect(new MobEffectInstance(RLEffects.BLEEDING.get(), seconds * 20, 0));
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE, ON, POWERED);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (oldState.getBlock() != state.getBlock() && level instanceof ServerLevel serverlevel) {
            checkAndFlip(state, serverlevel, pos);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (level instanceof ServerLevel serverlevel) {
            checkAndFlip(state, serverlevel, pos);
        }
    }

    public void checkAndFlip(BlockState state, ServerLevel level, BlockPos pos) {
        boolean flag = level.hasNeighborSignal(pos);
        if (flag != state.getValue(POWERED)) {
            BlockState blockstate = state;
            if (!(Boolean)state.getValue(POWERED)) {
                blockstate = state.cycle(ON);
                level.playSound(null, pos, blockstate.getValue(ON) ? DDSounds.SPIKE_TRAP_UNSHEATH.get() : DDSounds.SPIKE_TRAP_SHEATH.get(), SoundSource.BLOCKS);
            }
            level.setBlock(pos, blockstate.setValue(POWERED, flag), 3);
        }
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx)
    {
        boolean on = state.getOptionalValue(ON).orElse(false);
        AttachFace face = state.getOptionalValue(FACE).orElse(AttachFace.WALL);

        Direction dir;
        if (face == AttachFace.CEILING) dir = Direction.DOWN;
        else if (face == AttachFace.FLOOR) dir = Direction.UP;
        else dir = state.getOptionalValue(FACING).orElse(Direction.NORTH);

        return (on) ? SHAPE_OPEN.get(dir) : SHAPE_CLOSED.get(dir);
    }
}
