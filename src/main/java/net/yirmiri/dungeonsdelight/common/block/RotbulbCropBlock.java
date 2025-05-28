package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.common.util.TriState;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

import javax.annotation.Nullable;

public class RotbulbCropBlock extends PitcherCropBlock implements BonemealableBlock {
    public RotbulbCropBlock(Properties properties) {
        super(properties);
    }

    private boolean isMaxAge(BlockState state) {
        return state.getValue(AGE) >= 4;
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(DDTags.BlockT.ROTBULB_GROWABLE_ON);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living && living.getType().is(EntityTypeTags.UNDEAD)) {
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0));
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        TriState soilDecision = level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), Direction.UP, state);
        return isLower(state) && !sufficientLight(level, pos) ? soilDecision.isTrue() : super.canSurvive(state, level, pos);
    }

    private void spawnMonsterYam(ServerLevel level, BlockPos pos) {
        MonsterYamEntity monsterYam = DDEntities.MONSTER_YAM.get().create(level);
        if (monsterYam != null) {
            monsterYam.moveTo(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
            level.addFreshEntity(monsterYam);
            monsterYam.spawnAnim();
            level.playSound(monsterYam, pos, DDSounds.MONSTER_YAM_AMBIENT.get(), SoundSource.HOSTILE, 2.0F, -1.0F);
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, entity, stack);
        if ((!level.isClientSide) && isMaxAge(state) && level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            this.spawnMonsterYam((ServerLevel) level, pos);
        }
    }

    private static boolean canGrowInto(LevelReader reader, BlockPos pos) {
        BlockState blockstate = reader.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(DDBlocks.ROTBULB_CROP.get());
    }

    private static boolean sufficientLight(LevelReader reader, BlockPos pos) {
        return reader.getRawBrightness(pos, 0) <= 8 || !reader.canSeeSky(pos);
    }

    private static boolean isLower(BlockState state) {
        return state.is(DDBlocks.ROTBULB_CROP.get()) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private static boolean isUpper(BlockState state) {
        return state.is(DDBlocks.ROTBULB_CROP.get()) && state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    private boolean canGrow(LevelReader reader, BlockPos pos, BlockState state, int i) {
        return !this.isMaxAge(state) && sufficientLight(reader, pos) && (i < 3 || canGrowInto(reader, pos.above()));
    }

    @Nullable
    private PosAndState getLowerHalf(LevelReader reader, BlockPos pos, BlockState state) {
        if (isLower(state)) {
            return new PosAndState(pos, state);
        } else {
            BlockPos blockpos = pos.below();
            BlockState blockstate = reader.getBlockState(blockpos);
            return isLower(blockstate) ? new PosAndState(blockpos, blockstate) : null;
        }
    }

    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state, boolean b) {
        PosAndState cropStatePos = this.getLowerHalf(reader, pos, state);
        return cropStatePos != null && this.canGrow(reader, cropStatePos.pos, cropStatePos.state, cropStatePos.state.getValue(AGE) + 1);
    }

    public boolean isBonemealSuccess(Level level, RandomSource source, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        PosAndState cropStatePos = this.getLowerHalf(level, pos, state);
        if (cropStatePos != null) {
            this.grow(level, cropStatePos.state, cropStatePos.pos, 1);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        float f = getGrowthSpeed(state, level, pos);
        boolean flag = source.nextInt((int)(25.0F / f) + 1) == 0;
        if (flag) {
            this.grow(level, state, pos, 1);
        }
    }

    private void grow(ServerLevel level, BlockState state, BlockPos pos, int i1) {
        int i = Math.min(state.getValue(AGE) + i1, 4);
        if (this.canGrow(level, pos, state, i)) {
            level.setBlock(pos, state.setValue(AGE, i), 2);
            if (i >= 3) {
                BlockPos blockpos = pos.above();
                level.setBlock(blockpos, copyWaterloggedFrom(level, pos, this.defaultBlockState().setValue(AGE, i).setValue(HALF, DoubleBlockHalf.UPPER)), 3);
            }
        }
    }

    public static float getGrowthSpeed(BlockState blockState, BlockGetter getter, BlockPos pos) {
        Block p_52273_ = blockState.getBlock();
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1;
                label71: {
                    f1 = 0.0F;
                    BlockState blockstate = getter.getBlockState(blockpos.offset(i, 0, j));
                    TriState soilDecision = blockstate.canSustainPlant(getter, blockpos.offset(i, 0, j), Direction.UP, blockState);
                    if (soilDecision.isDefault()) {
                        if (!(blockstate.getBlock() instanceof FarmBlock)) {
                            break label71;
                        }
                    } else if (!soilDecision.isTrue()) {
                        break label71;
                    }

                    f1 = 1.0F;
                    if (blockstate.isFertile(getter, pos.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = getter.getBlockState(blockpos3).is(p_52273_) || getter.getBlockState(blockpos4).is(p_52273_);
        boolean flag1 = getter.getBlockState(blockpos1).is(p_52273_) || getter.getBlockState(blockpos2).is(p_52273_);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = getter.getBlockState(blockpos3.north()).is(p_52273_) || getter.getBlockState(blockpos4.north()).is(p_52273_) || getter.getBlockState(blockpos4.south()).is(p_52273_) || getter.getBlockState(blockpos3.south()).is(p_52273_);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    record PosAndState(BlockPos pos, BlockState state) {
        PosAndState(BlockPos pos, BlockState state) {
            this.pos = pos;
            this.state = state;
        }

        public BlockPos pos() {
            return this.pos;
        }

        public BlockState state() {
            return this.state;
        }
    }
}
