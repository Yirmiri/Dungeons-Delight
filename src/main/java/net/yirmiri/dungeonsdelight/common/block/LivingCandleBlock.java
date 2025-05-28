package net.yirmiri.dungeonsdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

import java.util.Collections;
import java.util.stream.Stream;

public class LivingCandleBlock extends AbstractCandleBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final MapCodec<LivingCandleBlock> CODEC = simpleCodec(LivingCandleBlock::new);

    @Override
    protected MapCodec<? extends AbstractCandleBlock> codec() {
        return CODEC;
    }

    public LivingCandleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LIT, false).setValue(WATERLOGGED, false));
    }

    @Override
    protected Iterable<Vec3> getParticleOffsets(BlockState state) {
        return Collections.singleton(new Vec3(0.5, 0.8, 0.5));
    }

    public InteractionResult use(BlockState p_152822_, Level p_152823_, BlockPos p_152824_, Player p_152825_, InteractionHand p_152826_, BlockHitResult p_152827_) {
        if (p_152825_.getAbilities().mayBuild && p_152825_.getItemInHand(p_152826_).isEmpty() && p_152822_.getValue(LIT)) {
            extinguish(p_152825_, p_152822_, p_152823_, p_152824_);
            return InteractionResult.sidedSuccess(p_152823_.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return Stream.of(
                Block.box(6, 3, 6, 10, 11, 10),
                Block.box(7, 1, 7, 9, 3, 9),
                Block.box(5, 0, 5, 11, 1, 11)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }

    @Override
    public void animateTick(BlockState p_220697_, Level p_220698_, BlockPos p_220699_, RandomSource p_220700_) {
        if (p_220697_.getValue(LIT)) {
            addParticlesAndSound(p_220698_, new Vec3(0.5, 0.85, 0.5).add(p_220699_.getX(), p_220699_.getY(), p_220699_.getZ()), p_220700_);
        }
    }

    private static void addParticlesAndSound(Level p_220688_, Vec3 p_220689_, RandomSource p_220690_) {
        float f = p_220690_.nextFloat();
        if (f < 0.3F) {
            p_220688_.addParticle(ParticleTypes.SMOKE, p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
            if (f < 0.17F) {
                p_220688_.playLocalSound(p_220689_.x + 0.5D, p_220689_.y + 0.5D, p_220689_.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + p_220690_.nextFloat(), p_220690_.nextFloat() * 0.7F + 0.3F, false);
            }
        }

        p_220688_.addParticle(DDParticles.LIVING_FLAME.get(), p_220689_.x, p_220689_.y, p_220689_.z, 0.0D, 0.0D, 0.0D);
    }

    public BlockState getStateForPlacement(BlockPlaceContext p_152803_) {
        FluidState fluidstate = p_152803_.getLevel().getFluidState(p_152803_.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        return super.getStateForPlacement(p_152803_).setValue(WATERLOGGED, Boolean.valueOf(flag));
    }

    public FluidState getFluidState(BlockState p_152844_) {
        return p_152844_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_152844_);
    }

    public boolean placeLiquid(LevelAccessor p_152805_, BlockPos p_152806_, BlockState p_152807_, FluidState p_152808_) {
        if (!p_152807_.getValue(WATERLOGGED) && p_152808_.getType() == Fluids.WATER) {
            BlockState blockstate = p_152807_.setValue(WATERLOGGED, Boolean.valueOf(true));
            if (p_152807_.getValue(LIT)) {
                extinguish(null, blockstate, p_152805_, p_152806_);
            } else {
                p_152805_.setBlock(p_152806_, blockstate, 3);
            }

            p_152805_.scheduleTick(p_152806_, p_152808_.getType(), p_152808_.getType().getTickDelay(p_152805_));
            return true;
        } else {
            return false;
        }
    }

    public static boolean canLight(BlockState p_152846_) {
        return p_152846_.is(BlockTags.CANDLES, (p_152810_) -> {
            return p_152810_.hasProperty(LIT) && p_152810_.hasProperty(WATERLOGGED);
        }) && !p_152846_.getValue(LIT) && !p_152846_.getValue(WATERLOGGED);
    }

    protected boolean canBeLit(BlockState p_152842_) {
        return !p_152842_.getValue(WATERLOGGED) && super.canBeLit(p_152842_);
    }

    public boolean canSurvive(BlockState p_152829_, LevelReader p_152830_, BlockPos p_152831_) {
        return Block.canSupportCenter(p_152830_, p_152831_.below(), Direction.UP);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(LIT, WATERLOGGED);
        super.createBlockStateDefinition(p_49915_);
    }
}
