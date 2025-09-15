package net.yirmiri.dungeonsdelight.common.effect;

import net.azurune.runiclib.common.publicized.PublicMobEffect;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class PutridScentEffect extends PublicMobEffect {
    public PutridScentEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide && entity.tickCount % 20 == 0) {
            List<Monster> monstersNearby = entity.level().getEntitiesOfClass(Monster.class, new AABB(entity.blockPosition()).inflate(12.0 + (amplifier * 2)));
            for (Monster monster : monstersNearby) {
                if (monster != entity && monster.getType().is(EntityTypeTags.UNDEAD)) {
                    monster.getNavigation().moveTo(entity, 1.2F);
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
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}