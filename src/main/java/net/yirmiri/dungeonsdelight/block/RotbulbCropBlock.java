package net.yirmiri.dungeonsdelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.common.IPlantable;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.util.DDTags;

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
        if (entity instanceof LivingEntity living && living.getMobType().equals(MobType.UNDEAD)) {
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0));
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        if (!isLower(state)) {
            return super.canSurvive(state, reader, pos);
        } else {
            BlockPos below = pos.below();
            boolean isSoil = this.mayPlaceOn(reader.getBlockState(below), reader, below);
            if (state.getBlock() == this) {
                isSoil = reader.getBlockState(below).canSustainPlant(reader, below, Direction.UP, this);
            }
            return isSoil && sufficientLight(reader, pos) && (state.getValue(AGE) < 3 || isUpper(reader.getBlockState(pos.above())));
        }
    }

    private void spawnMonsterYam(ServerLevel level, BlockPos pos) {
        Husk monsterYam = EntityType.HUSK.create(level); //PLACEHOLDER MOB
        if (monsterYam != null) {
            monsterYam.moveTo(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
            level.addFreshEntity(monsterYam);
            monsterYam.spawnAnim();
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean b) {
        super.spawnAfterBreak(state, level, pos, stack, b);
        if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            this.spawnMonsterYam(level, pos);
        }
    }

    private static boolean canGrowInto(LevelReader reader, BlockPos pos) {
        BlockState blockstate = reader.getBlockState(pos);
        return blockstate.isAir() || blockstate.is(DDBlocks.ROTBULB_PLANT.get());
    }

    private static boolean sufficientLight(LevelReader reader, BlockPos pos) {
        return reader.getRawBrightness(pos, 0) <= 8 || !reader.canSeeSky(pos); //TODO: allow growing in dark
    }

    private static boolean isLower(BlockState state) {
        return state.is(DDBlocks.ROTBULB_PLANT.get()) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private static boolean isUpper(BlockState state) {
        return state.is(DDBlocks.ROTBULB_PLANT.get()) && state.getValue(HALF) == DoubleBlockHalf.UPPER;
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
        float f = getGrowthSpeed(this, level, pos);
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

    protected static float getGrowthSpeed(Block block, BlockGetter getter, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = getter.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.canSustainPlant(getter, blockpos.offset(i, 0, j), Direction.UP, (IPlantable)block)) {
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
        boolean flag = getter.getBlockState(blockpos3).is(block) || getter.getBlockState(blockpos4).is(block);
        boolean flag1 = getter.getBlockState(blockpos1).is(block) || getter.getBlockState(blockpos2).is(block);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = getter.getBlockState(blockpos3.north()).is(block) || getter.getBlockState(blockpos4.north()).is(block) || getter.getBlockState(blockpos4.south()).is(block) || getter.getBlockState(blockpos3.south()).is(block);
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
