package net.yirmiri.dungeonsdelight.common.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDParticles;

public class WildRotbulbBlock extends DoublePlantBlock {
    public WildRotbulbBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living) {
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
    public boolean mayPlaceOn(BlockState state, BlockGetter getter, BlockPos pos) {
        return super.mayPlaceOn(state, getter, pos) || state.is(DDTags.BlockT.WILD_ROTBULB_GROWABLE_ON);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(20) == 0) {
            Vec3 center = Vec3.upFromBottomCenterOf(pos, 1).add(randomSource.nextFloat() - 0.5F, randomSource.nextFloat() * 0.5F + 0.2F, randomSource.nextFloat() - 0.5F);
            level.addParticle(DDParticles.FLY.get(), center.x, center.y, center.z, center.x, center.y, center.z);
        }
    }
}
