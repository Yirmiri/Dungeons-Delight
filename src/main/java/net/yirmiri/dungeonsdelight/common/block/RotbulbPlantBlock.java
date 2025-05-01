package net.yirmiri.dungeonsdelight.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.init.DDTags;

public class RotbulbPlantBlock extends DoublePlantBlock {
    public RotbulbPlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living && living.getMobType().equals(MobType.UNDEAD)) {
            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0));
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0));
        }
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.is(DDTags.BlockT.ROTBULB_GROWABLE_ON);
    }
}
