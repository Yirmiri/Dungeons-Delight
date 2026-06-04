package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.List;

public class PutridScentEffect extends PublicMobEffect {
    public PutridScentEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide && entity.tickCount % 20 == 0) {
            List<Monster> range = entity.level().getEntitiesOfClass(Monster.class, new AABB(
                    entity.blockPosition()).inflate(DungeonsDelight.CONFIG.getPutridScentRange() + (amplifier * 2)));
            for (Monster monster : range) {
                if (monster != entity && monster.getMobType() == MobType.UNDEAD) {
                    monster.getNavigation().moveTo(entity, 1.25F);
                    monster.setTarget(entity);
                }

                if (entity instanceof Mob mob && monster != entity) {
                    Vec3 awayVector = Vec3.ZERO;
                    awayVector = awayVector.add(entity.position().subtract(monster.position()).scale(1.0 / entity.position().subtract(monster.position()).length() + 0.01));
                    Vec3 normalized = awayVector.normalize();
                    Vec3 targetPos = entity.position().add(normalized.scale(4.0 + amplifier * 1.5));
                    mob.getNavigation().moveTo(targetPos.x, targetPos.y, targetPos.z, 1.25);
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}