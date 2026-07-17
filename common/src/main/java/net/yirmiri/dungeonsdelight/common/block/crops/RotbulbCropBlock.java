package net.yirmiri.dungeonsdelight.common.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.*;

import java.util.List;

public class RotbulbCropBlock extends AgeRottenCropBlock implements BonemealableBlock {
    public static final int MAX_AGE = 9;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    private static final VoxelShape FULL_UPPER_SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 15.0F, 13.0F);
    private static final VoxelShape FULL_LOWER_SHAPE = Block.box(3.0F, -1.0F, 3.0F, 13.0F, 16.0F, 13.0F);
    private static final VoxelShape COLLISION_SHAPE_BULB = Block.box(5.0F, -1.0F, 5.0F, 11.0F, 3.0F, 11.0F);
    private static final VoxelShape COLLISION_SHAPE_CROP = Block.box(3.0F, -1.0F, 3.0F, 13.0F, 5.0F, 13.0F);
    private static final VoxelShape[] UPPER_SHAPE_BY_AGE = new VoxelShape[]{Block.box(3.0F, 0.0F, 3.0F, 13.0F, 11.0F, 13.0F), FULL_UPPER_SHAPE};
    private static final VoxelShape[] LOWER_SHAPE_BY_AGE = new VoxelShape[]{COLLISION_SHAPE_BULB, Block.box(3.0F, -1.0F, 3.0F, 13.0F, 14.0F, 13.0F), FULL_LOWER_SHAPE, FULL_LOWER_SHAPE, FULL_LOWER_SHAPE};

    public RotbulbCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(HALF, DoubleBlockHalf.LOWER)
        );
    }

    @Override
    public boolean isBonemealSuccess(Level world, net.minecraft.util.RandomSource random, BlockPos pos, BlockState state) {
        return DungeonsDelight.CONFIG.getBonemealableRotbulbs();
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
        RotbulbCropBlock.LowerHalfContext lowerHalfContext = getLowerHalfContext(world, pos, state);
        return lowerHalfContext != null && !isMature(lowerHalfContext.state) && DungeonsDelight.CONFIG.getBonemealableRotbulbs();
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        RotbulbCropBlock.LowerHalfContext lowerHalfContext = getLowerHalfContext(world, pos, state);
        if (lowerHalfContext != null && DungeonsDelight.CONFIG.getBonemealableRotbulbs()) {
            tryGrow(world, lowerHalfContext.state, lowerHalfContext.pos);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER
                ? UPPER_SHAPE_BY_AGE[Math.min(Math.abs(getMaxAge() - (state.getValue(AGE) + 1)), UPPER_SHAPE_BY_AGE.length - 1)]
                : LOWER_SHAPE_BY_AGE[Math.min(state.getValue(AGE), LOWER_SHAPE_BY_AGE.length - 1)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if (state.getValue(AGE) == 0 || state.getValue(AGE) == 1) {
            return COLLISION_SHAPE_BULB;
        } else {
            return state.getValue(HALF) == DoubleBlockHalf.LOWER ? COLLISION_SHAPE_CROP : super.getCollisionShape(state, level, pos, ctx);
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living && state.getValue(AGE) >= 8) {
            if (living.getMobType() == MobType.UNDEAD) {
                living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0));
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0));
                living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0));
            } else {
                living.addEffect(new MobEffectInstance(DDEffects.PUTRID_SCENT.get(), 400, 0));
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(20) == 0 && state.getValue(AGE) >= 8) {
            Vec3 center = Vec3.upFromBottomCenterOf(pos, 1).add(randomSource.nextFloat() - 0.5F, randomSource.nextFloat() * 0.5F + 0.2F, randomSource.nextFloat() - 0.5F);
            level.addParticle(DDParticles.FLY.get(), center.x, center.y, center.z, center.x, center.y, center.z);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, HALF);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return DDItems.ROTBULB_SEEDS.get();
    }

    public static boolean isMature(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && !isMature(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, 0);
    }

    private void spawnMonsterYam(ServerLevel level, BlockPos pos) {
        if (level.getLevel().getDifficulty() == Difficulty.PEACEFUL) {
            if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
                ResourceLocation lootTableId = new ResourceLocation(DungeonsDelight.MOD_ID, "entities/monster_yam");
                LootParams.Builder builder = new LootParams.Builder(level).withParameter(LootContextParams.BLOCK_STATE, level.getBlockState(pos));
                List<ItemStack> lootData = level.getServer().getLootData().getLootTable(lootTableId).getRandomItems(builder.create(LootContextParamSets.EMPTY));

                if (!lootData.isEmpty()) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY() - 0.6, pos.getZ(), lootData.get(level.random.nextInt(lootData.size())));
                }
            }
        } else {
            //MonsterYamEntity monsterYam = DDEntities.MONSTER_YAM.get().create(level);
            Husk monsterYam = EntityType.HUSK.create(level); //todo
            if (monsterYam != null) {
                monsterYam.moveTo(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
                level.addFreshEntity(monsterYam);
                monsterYam.spawnAnim();
                level.playSound(monsterYam, pos, DDSounds.MONSTER_YAM_AMBIENT.get(), SoundSource.HOSTILE, 2.0F, -1.0F);
            }
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity entity, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, entity, stack);
        if ((!level.isClientSide) && isMaxAge(state)) {
            this.spawnMonsterYam((ServerLevel) level, pos);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventCreativeDropFromBottomPart(level, pos, state, player);
            } else {
                dropResources(state, level, pos, null, player, player.getMainHandItem());
            }
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        if (!isLowerHalf(state)) {
            return world.getBlockState(pos.below()).is(this);
        } else {
            int age = state.getValue(AGE);

            if (age >= 6) {
                BlockState above = world.getBlockState(pos.above());
                return world.getBlockState(pos.below()).is(DDBlocks.TERROR_PRETA.get())
                        && isUpperHalf(above);
            }

            return world.getBlockState(pos.below()).is(DDBlocks.TERROR_PRETA.get());
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    protected static float getGrowthSpeed(Block block, BlockGetter level, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState state = level.getBlockState(blockpos.offset(i, 0, j));
                if (state.is(DDBlocks.TERROR_PRETA.get())) {
                    f1 = 1.0F;
                    if (state.getValue(TerrorPretaBlock.MOISTURE) > 0 && state.getValue(TerrorPretaBlock.SHADED) > 6) {
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
        boolean flag = level.getBlockState(blockpos3).is(block) || level.getBlockState(blockpos4).is(block);
        boolean flag1 = level.getBlockState(blockpos1).is(block) || level.getBlockState(blockpos2).is(block);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = level.getBlockState(blockpos3.north()).is(block) || level.getBlockState(blockpos4.north()).is(block) || level.getBlockState(blockpos4.south()).is(block) || level.getBlockState(blockpos3.south()).is(block);
            if (flag2) {
                f /= 2.0F;
            }
        }
        return f;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, net.minecraft.util.RandomSource random) {
        if (random.nextInt((int) (25.0F / getGrowthSpeed(this, world, pos)) + 1) == 0) {
            tryGrow(world, state, pos);
        }
    }

    private void tryGrow(ServerLevel world, BlockState state, BlockPos pos) {
        int i = Math.min(state.getValue(AGE) + 1, MAX_AGE);
        if (canGrow(world, pos, state, i)) {
            world.setBlock(pos, state.setValue(AGE, i), 2);
            if (i >= 3) {
                BlockPos blockPos = pos.above();
                world.setBlock(blockPos, defaultBlockState().setValue(AGE, i).setValue(HALF, DoubleBlockHalf.UPPER), 3);
            }
        }
    }

    private static boolean canGrowAt(LevelReader world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isAir() || blockState.is(DDBlocks.ROTBULB.get());
    }

    private static boolean isLowerHalf(BlockState state) {
        return state.is(DDBlocks.ROTBULB.get()) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    private static boolean isUpperHalf(BlockState state) {
        return state.is(DDBlocks.ROTBULB.get()) && state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    private boolean canGrow(LevelReader world, BlockPos pos, BlockState state, int age) {
        return !isMature(state) && (age < 3 || canGrowAt(world, pos.above()));
    }

    private RotbulbCropBlock.LowerHalfContext getLowerHalfContext(LevelReader world, BlockPos pos, BlockState state) {
        if (isLowerHalf(state)) {
            return new RotbulbCropBlock.LowerHalfContext(pos, state);
        } else {
            BlockPos blockPos = pos.below();
            BlockState blockState = world.getBlockState(blockPos);
            return isLowerHalf(blockState) ? new RotbulbCropBlock.LowerHalfContext(blockPos, blockState) : null;
        }
    }

    @Override
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }

    private record LowerHalfContext(BlockPos pos, BlockState state) {
    }
}
