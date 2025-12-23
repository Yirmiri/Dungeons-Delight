package net.yirmiri.dungeonsdelight.common.worldgen.feature.rotten_monster_room;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.material.Fluids;
import net.yirmiri.dungeonsdelight.DDConfigCommon;
import net.yirmiri.dungeonsdelight.core.init.DDLootTables;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class RottenMonsterRoomFeature extends Feature<NoneFeatureConfiguration>
{
    private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();

    public RottenMonsterRoomFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        int rando = randomsource.nextIntBetweenInclusive(0, 100);
        BlockPos blockpos = context.origin();
        WorldGenLevel worldgenlevel = context.level();

        // Evil chain of requirements
        boolean isSwamp = worldgenlevel.getBiome(blockpos).is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS);
        boolean swampWeirdGenPass = (isSwamp && blockpos.getY() <= 32 && randomsource.nextInt(0, 49) == 0);
        boolean regularWeirdGenPass = (randomsource.nextInt(0, 29) == 0 && blockpos.getY() <= 12);
        boolean doWeirdPass = (swampWeirdGenPass || regularWeirdGenPass);
        boolean weirdFlag = false;
        int passValue = (isSwamp) ? DDConfigCommon.SWAMP_ROTTEN_MONSTER_ROOM_CHANCE.get() : DDConfigCommon.ROTTEN_MONSTER_ROOM_CHANCE.get();

        if (rando >= passValue) {
            Predicate<BlockState> predicate = Feature.isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE);
            int j = randomsource.nextInt(2) + 2;
            int k = -j - 1;
            int l = j + 1;
            int k1 = randomsource.nextInt(2) + 2;
            int l1 = -k1 - 1;
            int i2 = k1 + 1;
            int j2 = 0;

            int k3;
            int i4;
            int k4;
            BlockPos blockpos3;
            boolean floorMissing = false;
            for(k3 = k; k3 <= l; ++k3) {
                for(i4 = -1; i4 <= 4; ++i4) {
                    for(k4 = l1; k4 <= i2; ++k4) {
                        blockpos3 = blockpos.offset(k3, i4, k4);
                        boolean flag = worldgenlevel.getBlockState(blockpos3).isSolid();

                        if (i4 == -1 && !flag) {
                            weirdFlag = true;
                            floorMissing = !worldgenlevel.getBlockState(blockpos.below()).isSolid();
                            if (!doWeirdPass || floorMissing) return false;
                        }

                        if (i4 == 4 && !flag) {
                            weirdFlag = true;
                            floorMissing = !worldgenlevel.getBlockState(blockpos.below()).isSolid();
                            if (!doWeirdPass || floorMissing) return false;
                        }

                        if ((k3 == k || k3 == l || k4 == l1 || k4 == i2) && i4 == 0 && worldgenlevel.isEmptyBlock(blockpos3) && worldgenlevel.isEmptyBlock(blockpos3.above())) ++j2;
                    }
                }
            }

            if (j2 >= 1 && j2 <= 5) {
                // Placer target lists
                List<BlockPos> rotbulb_list = new ArrayList<>();
                List<BlockPos> mushroom_list = new ArrayList<>();

                // Room Creation
                for(k3 = k; k3 <= l; ++k3) {
                    for(i4 = 3; i4 >= -1; --i4) {
                        for(k4 = l1; k4 <= i2; ++k4) {
                            blockpos3 = blockpos.offset(k3, i4, k4);
                            BlockState blockstate = worldgenlevel.getBlockState(blockpos3);
                            if (k3 != k && i4 != -1 && k4 != l1 && k3 != l && i4 != 4 && k4 != i2) {
                                if (!blockstate.is(Blocks.CHEST) && !blockstate.is(Blocks.SPAWNER)) this.safeSetBlock(worldgenlevel, blockpos3, AIR, predicate);
                            }
                            else if (blockpos3.getY() >= worldgenlevel.getMinBuildHeight() && !worldgenlevel.getBlockState(blockpos3.below()).isSolid()) worldgenlevel.setBlock(blockpos3, AIR, 2);
                            else if (blockstate.isSolid() && !blockstate.is(Blocks.CHEST)) {
                                // Break: Where mud is attempted to be placed
                                if (i4 == -1 && randomsource.nextInt(4) != 0) {
                                    if (randomsource.nextBoolean()) {
                                        this.safeSetBlock(worldgenlevel, blockpos3, Blocks.MUD.defaultBlockState(), predicate);

                                        int toPlace = randomsource.nextInt(4);
                                        BlockPos up = blockpos3.above();
                                        BlockPos up2 = up.above();

                                        // Rotbulb
                                        if (toPlace == 1) {
                                            rotbulb_list.add(up);
                                        }
                                        // Brown Mushroom
                                        else if (toPlace == 2) {
                                            mushroom_list.add(up);
                                        }
                                    }
                                    else this.safeSetBlock(worldgenlevel, blockpos3, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), predicate);
                                }
                                else this.safeSetBlock(worldgenlevel, blockpos3, Blocks.COBBLESTONE.defaultBlockState(), predicate);
                            }
                        }
                    }
                }

                // Chest Placement
                for(k3 = 0; k3 < 2; ++k3) {
                    for(i4 = 0; i4 < 3; ++i4) {
                        k4 = blockpos.getX() + randomsource.nextInt(j * 2 + 1) - j;
                        int i5 = blockpos.getY();
                        int j5 = blockpos.getZ() + randomsource.nextInt(k1 * 2 + 1) - k1;
                        BlockPos blockpos2 = new BlockPos(k4, i5, j5);
                        if (worldgenlevel.isEmptyBlock(blockpos2)) {
                            int j3 = 0;
                            Iterator var23 = Direction.Plane.HORIZONTAL.iterator();

                            while (var23.hasNext()) {
                                Direction direction = (Direction)var23.next();
                                if (worldgenlevel.getBlockState(blockpos2.relative(direction)).isSolid() && worldgenlevel.getBlockState(blockpos2.below()).isSolid()) ++j3;
                            }

                            if (j3 == 1) {
                                this.safeSetBlock(worldgenlevel, blockpos2, StructurePiece.reorient(worldgenlevel, blockpos2, Blocks.CHEST.defaultBlockState()), predicate);
                                RandomizableContainer.setBlockEntityLootTable(worldgenlevel, randomsource, blockpos2, DDLootTables.ROTTEN_DUNGEON_CHEST);
                                break;
                            }
                        }
                    }
                }

                // Plant placers
                if (!rotbulb_list.isEmpty()) {
                    for (BlockPos up : rotbulb_list) {
                        BlockPos up2 = up.above();
                        if (worldgenlevel.getBlockState(up).isAir() && worldgenlevel.getBlockState(up2).isAir()) {
                            this.safeSetBlock(worldgenlevel, up, DDBlocks.ROTBULB_PLANT.get().defaultBlockState(), predicate);
                            this.safeSetBlock(worldgenlevel, up2, DDBlocks.ROTBULB_PLANT.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), predicate);
                        }
                    }
                }
                if (!mushroom_list.isEmpty()) {
                    for (BlockPos up : mushroom_list) {
                        if (worldgenlevel.getBlockState(up).isAir()) {
                            this.safeSetBlock(worldgenlevel, up, Blocks.BROWN_MUSHROOM.defaultBlockState(), predicate);
                        }
                    }
                }

                // Gunk Placement
                for(k3 = k; k3 <= l; ++k3) {
                    for(i4 = 3; i4 >= -1; --i4) {
                        for(k4 = l1; k4 <= i2; ++k4) {
                            blockpos3 = blockpos.offset(k3, i4, k4);
                            BlockState blockstate = worldgenlevel.getBlockState(blockpos3);

                            // This is very bad ignore this
                            if (blockstate.isAir() && randomsource.nextInt(3) == 0) {
                                boolean solidU = worldgenlevel.getBlockState(blockpos3.above()).isSolid() && !worldgenlevel.getBlockState(blockpos3.above()).is(DDBlocks.GUNK.get());
                                boolean solidD = worldgenlevel.getBlockState(blockpos3.below()).isSolid() && !worldgenlevel.getBlockState(blockpos3.below()).is(DDBlocks.GUNK.get());
                                boolean solidN = worldgenlevel.getBlockState(blockpos3.north()).isSolid() && !worldgenlevel.getBlockState(blockpos3.north()).is(DDBlocks.GUNK.get());
                                boolean solidE = worldgenlevel.getBlockState(blockpos3.east()).isSolid() && !worldgenlevel.getBlockState(blockpos3.east()).is(DDBlocks.GUNK.get());
                                boolean solidS = worldgenlevel.getBlockState(blockpos3.south()).isSolid() && !worldgenlevel.getBlockState(blockpos3.south()).is(DDBlocks.GUNK.get());
                                boolean solidW = worldgenlevel.getBlockState(blockpos3.west()).isSolid() && !worldgenlevel.getBlockState(blockpos3.west()).is(DDBlocks.GUNK.get());
                                boolean watery = worldgenlevel.getBlockState(blockpos3).getFluidState().is(Fluids.WATER);

                                if (solidU || solidD || solidN || solidE || solidS || solidW) {
                                    BlockState base = DDBlocks.GUNK.get().defaultBlockState()
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.UP), solidU)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), solidD)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.NORTH), solidN)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.EAST), solidE)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.SOUTH), solidS)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.WEST), solidW)
                                            .setValue(BlockStateProperties.WATERLOGGED, watery);

                                    this.safeSetBlock(worldgenlevel, blockpos3, base, predicate);
                                }
                            }
                        }
                    }
                }

                this.safeSetBlock(worldgenlevel, blockpos, DDBlocks.ROTTEN_SPAWNER.get().defaultBlockState(), predicate);

                return true;
            }
            else return false;
        }
        return false;
    }
}
