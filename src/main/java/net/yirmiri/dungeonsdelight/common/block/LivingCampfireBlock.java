package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LivingCampfireBlock extends CampfireBlock {
    public LivingCampfireBlock(Properties properties) {
        super(false, 1, properties);
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
