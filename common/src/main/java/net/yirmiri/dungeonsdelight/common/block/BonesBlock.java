package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

public class BonesBlock extends FallingBlock {
    public BonesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
        return 0xf9f6d3;
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity fallingBlock)
    {
        Vec3 cenPos = fallingBlock.getBoundingBox().getCenter();

        if (fallingBlock.time < 8 && level.getBlockState(pos).isAir() && !fallingBlock.getBlockState().isAir()) {
            level.setBlock(pos, fallingBlock.getBlockState(), BonesBlock.UPDATE_ALL_IMMEDIATE);
            level.gameEvent(fallingBlock, GameEvent.BLOCK_PLACE, cenPos);
        } else {
            if (!fallingBlock.getBlockState().isAir() && level instanceof ServerLevel server) {
                Block.dropResources(fallingBlock.getBlockState(), level, pos, null, null, ItemStack.EMPTY);

                server.playSound(
                        null,
                        pos,
                        DDSounds.BONES_BLOCK_SMASH.get(),
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F + (0.25F * (level.random.nextFloat() - 0.5F))
                );

                server.sendParticles(
                        DDParticles.BONE.get(),
                        pos.getX() + 0.5F,
                        pos.getY() + 0.5F,
                        pos.getZ() + 0.5F,
                        15,
                        0.45F,
                        0.45F,
                        0.45F,
                        0.1F
                );
            }

            level.gameEvent(fallingBlock, GameEvent.BLOCK_DESTROY, cenPos);
        }
    }

    @Override
    protected void falling(FallingBlockEntity entity) {
        entity.disableDrop();
    }
}
//todo break when falling from a height greater than 1, spawn 1-3 bone meal
//todo crackling sound