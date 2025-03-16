package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Supplier;

public class ExperiencePieBlock extends PieBlock {
    private final int experience;

    public ExperiencePieBlock(Properties properties, int experience, Supplier<Item> pieSlice) {
        super(properties, pieSlice);
        this.experience = experience;
    }

    @Override
    protected InteractionResult consumeBite(Level level, BlockPos pos, BlockState state, Player player) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            if (!level.isClientSide) {
                player.giveExperiencePoints(experience + player.level().random.nextInt((int) (experience * 1.33)));
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
            }
            return super.consumeBite(level, pos, state, player);
        }
    }
}
