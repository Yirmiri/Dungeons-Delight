package net.yirmiri.dungeonsdelight.entity.monster_yam;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.registry.DDSounds;

import java.util.List;

public class MonsterYamEntity extends Monster {
    public MonsterYamEntity(EntityType<? extends MonsterYamEntity> type, Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 24.0)
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.24)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.ARMOR, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2);
    }

    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(6.0D), Entity::isAlive);
            for (LivingEntity livingEntity : list) {
                if (livingEntity.isAlive() && livingEntity.getMobType().equals(MobType.UNDEAD)) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 0));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0));
                }
            }
        }
    }

    protected SoundEvent getAmbientSound() {
        return DDSounds.MONSTER_YAM_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return DDSounds.MONSTER_YAM_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return DDSounds.MONSTER_YAM_DEATH.get();
    }

    protected SoundEvent getStepSound() {
        return DDSounds.MONSTER_YAM_STEP.get();
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
}
