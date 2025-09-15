package net.yirmiri.dungeonsdelight.common.worldgen.feature.wormroot;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.yirmiri.dungeonsdelight.common.block.WormrootsStalkBlock;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.levelgen.feature.MultifaceGrowthFeature.placeGrowthIfPossible;
import static net.yirmiri.dungeonsdelight.common.block.WormrootsStalkBlock.*;

public class WormrootFeature extends Feature<MultifaceGrowthConfiguration> {
    public WormrootFeature(Codec<MultifaceGrowthConfiguration> ctx) {
        super(ctx);
    }

    private boolean canReplace(WorldGenLevel level, BlockPos pos) {
        return level.getBlockState(pos).isAir() || level.getBlockState(pos).canBeReplaced();
    }

    private void placeBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        if (canReplace(level, pos)) {
            if (state.is(DDBlocks.WORMROOT_STALK.get())) {
                setBlock(level, pos, state
                        .setValue(NORTH, level.getBlockState(pos.north()).is(DDBlocks.WORMROOT_STALK.get()))
                        .setValue(EAST, level.getBlockState(pos.east()).is(DDBlocks.WORMROOT_STALK.get()))
                        .setValue(SOUTH, level.getBlockState(pos.south()).is(DDBlocks.WORMROOT_STALK.get()))
                        .setValue(WEST, level.getBlockState(pos.west()).is(DDBlocks.WORMROOT_STALK.get()))
                        .setValue(UP, level.getBlockState(pos.above()).is(DDBlocks.WORMROOT_STALK.get()))
                        .setValue(DOWN, level.getBlockState(pos.below()).is(DDBlocks.WORMROOT_STALK.get())));
                for (Direction direction : Direction.values()) {
                    if (level.getBlockState(pos.relative(direction)).is(DDBlocks.WORMROOT_STALK.get())) {
                        setBlock(level, pos.relative(direction), level.getBlockState(pos.relative(direction)).setValue(DIRECTION_TO_PROPERTY.get(direction.getOpposite()), true));
                    }
                }
            } else {
                setBlock(level, pos, state);
            }
        }
    }

    private void replaceStonePlace(WorldGenLevel level, BlockPos pos, BlockState state) {
        if (level.getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD)) {
            setBlock(level, pos, state);
        }
    }

    private boolean placeMouthBranch(WorldGenLevel level, BlockPos pos, Direction direction, RandomSource random) {
        if (random.nextBoolean()) {
            if (canReplace(level, pos.relative(direction)) && canReplace(level, pos.relative(direction).below())) {
                placeBlock(level, pos.relative(direction), DDBlocks.WORMROOT_STALK.get().defaultBlockState());
                placeBlock(level, pos.relative(direction).below(), DDBlocks.WORMOUTH.get().defaultBlockState());
                return true;
            }
        } else {
            if (canReplace(level, pos.relative(direction)) && canReplace(level, pos.relative(direction, 2)) && canReplace(level, pos.relative(direction, 2).below())) {
                placeBlock(level, pos.relative(direction), DDBlocks.WORMROOT_STALK.get().defaultBlockState().setValue(BlockStateProperties.AXIS, direction.getAxis()));
                placeBlock(level, pos.relative(direction, 2), DDBlocks.WORMROOT_STALK.get().defaultBlockState());
                placeBlock(level, pos.relative(direction, 2).below(), DDBlocks.WORMOUTH.get().defaultBlockState());
                return true;
            }
        }
        return false;
    }

    private void placeEndMouth(WorldGenLevel level, BlockPos pos, RandomSource random) {
        placeBlock(level, pos, DDBlocks.WORMROOT_STALK.get().defaultBlockState());
        placeBlock(level, pos.above(), DDBlocks.WORMROOT_STALK.get().defaultBlockState());
        placeMouthBranch(level, pos.above(), Direction.Plane.HORIZONTAL.getRandomDirection(random), random);
    }

    @Override
    public boolean place(FeaturePlaceContext<MultifaceGrowthConfiguration> context) {
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin().above(2);
        Direction mainBranchDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int firstHeight = random.nextInt(3, 4);
        int secondHeight = random.nextInt(2, 3);

        float mouthBranchChance = 0f;

        BlockState upDownState = DDBlocks.WORMROOT_STALK.get().defaultBlockState();

        replaceStonePlace(level, context.origin().above().below(), DDBlocks.WORMROOTS_BLOCK.get().defaultBlockState());
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (random.nextBoolean()) {
                    replaceStonePlace(level, context.origin().above().offset(i, -1, j), DDBlocks.WORMROOTS_BLOCK.get().defaultBlockState());
                }
            }
        }
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (random.nextBoolean()) {
                    replaceStonePlace(level, context.origin().above().offset(i, -1, j), Blocks.COARSE_DIRT.defaultBlockState());
                }
            }
        }

        level.setBlock(context.origin().above(), upDownState, 2);

        for (int i = 0; i < firstHeight; i++) {
            placeBlock(level, pos.above(i), upDownState);
            if (random.nextFloat() < mouthBranchChance && i > 0) {
                List<Direction> dirs = new ArrayList<>(Direction.Plane.HORIZONTAL.stream().toList());
                dirs.remove(mainBranchDir);
                dirs.remove(mainBranchDir.getOpposite());
                Direction direction = dirs.get(random.nextInt(0, dirs.size()));

                if (placeMouthBranch(level, pos.above(i), direction, random)) {
                    mouthBranchChance = 0;
                }
            } else {
                mouthBranchChance += 0.3f;
            }
        }

        pos = pos.above(firstHeight-1);

        if (random.nextBoolean()) {
            //SMALL
            int firstOffset = random.nextInt(2, 3);
            for (int i = 1; i < firstOffset; i++) {
                placeBlock(level, pos.relative(mainBranchDir, i), upDownState.setValue(BlockStateProperties.AXIS, mainBranchDir.getAxis()));
            }
            pos = pos.relative(mainBranchDir, firstOffset);
            for (int i = 0; i < secondHeight; i++) {
                placeBlock(level, pos.above(i), upDownState);
                if (random.nextFloat() < mouthBranchChance) {
                    if (placeMouthBranch(level, pos.above(i), Direction.Plane.HORIZONTAL.getRandomDirection(random), random)) {
                        mouthBranchChance = 0;
                    }
                } else {
                    mouthBranchChance += 0.4f;
                }
            }
            placeEndMouth(level, pos.above(secondHeight), random);
        } else {
            //LARGE
            int firstOffset = random.nextInt(2, 3);
            for (int i = 1; i < firstOffset; i++) {
                placeBlock(level, pos.relative(mainBranchDir, i), upDownState.setValue(BlockStateProperties.AXIS, mainBranchDir.getAxis()));
            }

            BlockPos firstPos = pos.relative(mainBranchDir, firstOffset);
            for (int i = 0; i < secondHeight; i++) {
                placeBlock(level, firstPos.above(i), upDownState);
                if (random.nextFloat() < mouthBranchChance) {
                    if (placeMouthBranch(level, firstPos.above(i), Direction.Plane.HORIZONTAL.getRandomDirection(random), random)) {
                        mouthBranchChance = 0;
                    }
                } else {
                    mouthBranchChance += 0.4f;
                }
            }
            placeEndMouth(level, firstPos.above(secondHeight), random);

            boolean below = random.nextBoolean();
            int secondOffset = random.nextInt(2, 3);
            for (int i = 1; i < secondOffset; i++) {
                placeBlock(level, pos.relative(mainBranchDir.getOpposite(), i).below(below ? 2 : 1), upDownState.setValue(BlockStateProperties.AXIS, mainBranchDir.getAxis()));
            }

            BlockPos secondPos = pos.relative(mainBranchDir.getOpposite(), secondOffset).below(below ? 2 : 1);
            for (int i = 0; i < secondHeight; i++) {
                placeBlock(level, secondPos.above(i), upDownState);
                if (random.nextFloat() < mouthBranchChance) {
                    if (placeMouthBranch(level, secondPos.above(i), Direction.Plane.HORIZONTAL.getRandomDirection(random), random)) {
                        mouthBranchChance = 0;
                    }
                } else {
                    mouthBranchChance += 0.4f;
                }
            }
            placeEndMouth(level, secondPos.above(secondHeight), random);

        }

        for (int i = 0; i < 6; i++) {
            FeaturePlaceContext<MultifaceGrowthConfiguration> context1 = new FeaturePlaceContext<>(context.topFeature(), context.level(), context.chunkGenerator(), context.random(), context.origin().offset(random.nextInt(-2, 2), 0, random.nextInt(-2, 2)), context.config());
            placeTendrils(context1);
        }
        return true;
    }

    public boolean placeTendrils(FeaturePlaceContext<MultifaceGrowthConfiguration> p_225165_) {
        WorldGenLevel worldgenlevel = p_225165_.level();
        BlockPos blockpos = p_225165_.origin();
        RandomSource randomsource = p_225165_.random();
        MultifaceGrowthConfiguration multifacegrowthconfiguration = p_225165_.config();
        if (!isAirOrWater(worldgenlevel.getBlockState(blockpos))) {
            return false;
        } else {
            List<Direction> list = multifacegrowthconfiguration.getShuffledDirections(randomsource);
            if (placeGrowthIfPossible(worldgenlevel, blockpos, worldgenlevel.getBlockState(blockpos), multifacegrowthconfiguration, randomsource, list)) {
                return true;
            } else {
                BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();

                for(Direction direction : list) {
                    blockpos$mutableblockpos.set(blockpos);
                    List<Direction> list1 = multifacegrowthconfiguration.getShuffledDirectionsExcept(randomsource, direction.getOpposite());

                    for(int i = 0; i < multifacegrowthconfiguration.searchRange; ++i) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, direction);
                        BlockState blockstate = worldgenlevel.getBlockState(blockpos$mutableblockpos);
                        if (!isAirOrWater(blockstate) && !blockstate.is(multifacegrowthconfiguration.placeBlock)) {
                            break;
                        }

                        if (placeGrowthIfPossible(worldgenlevel, blockpos$mutableblockpos, blockstate, multifacegrowthconfiguration, randomsource, list1)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }

    private static boolean isAirOrWater(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER);
    }
}
