package net.yirmiri.dungeonsdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

public class LivingFireBlock extends BaseFireBlock {
    public static final MapCodec<LivingFireBlock> CODEC = simpleCodec(LivingFireBlock::new);

    public LivingFireBlock(BlockBehaviour.Properties properties) {
        super(properties, 1.0F);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        return this.canSurvive(state, accessor, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return canSurviveOnBlock(reader.getBlockState(pos.below()));
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(DDTags.BlockT.LIVING_FIRE_BASE_BLOCKS);
    }

    @Override
    protected MapCodec<? extends BaseFireBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return false;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!entity.fireImmune()) {
            if (entity instanceof Player player && player.totalExperience > 0 && player.hurtTime == 0 && player.isAlive() && !player.getAbilities().instabuild) {
                player.giveExperiencePoints(-3);
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.75F, -1.0F);
            }
        }
        super.entityInside(state, level, pos, entity);
    }
}
