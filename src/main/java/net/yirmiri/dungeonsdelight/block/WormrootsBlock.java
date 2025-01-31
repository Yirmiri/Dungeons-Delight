package net.yirmiri.dungeonsdelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.util.DDTags;
import org.jetbrains.annotations.NotNull;

public class WormrootsBlock extends MultifaceBlock implements SimpleWaterloggedBlock {
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    public WormrootsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(WATERLOGGED);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        if (state.getValue(WATERLOGGED)) {
            accessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accessor));
        }

        return super.updateShape(state, direction, state1, accessor, pos, pos1);
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext ctx) {
        return !ctx.getItemInHand().is(DDBlocks.WORMROOTS.get().asItem()) || super.canBeReplaced(state, ctx);
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        RandomSource source = RandomSource.create();
        var heldItem = player.getItemInHand(hand);

        if (heldItem.is(DDTags.ItemT.MONSTER_FOODS)) {
            this.spreader.spreadFromRandomFaceTowardRandomDirection(state, level, pos, source);
            player.playSound(SoundEvents.CHORUS_FLOWER_GROW);
            level.levelEvent(player, 2001, pos, getId(state));
            heldItem.shrink(1);
        }
        return super.use(state, level, pos, player, hand, result);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }
}
