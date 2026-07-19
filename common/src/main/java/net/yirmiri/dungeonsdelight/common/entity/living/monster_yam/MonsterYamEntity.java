package net.yirmiri.dungeonsdelight.common.entity.living.monster_yam;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.init.DDMobTypes;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

public class MonsterYamEntity extends Monster {
    private static final EntityDataAccessor<Integer> SUMMON_TIMER = SynchedEntityData.defineId(MonsterYamEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SUMMON_COOLDOWN = SynchedEntityData.defineId(MonsterYamEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_SUMMONING = SynchedEntityData.defineId(MonsterYamEntity.class, EntityDataSerializers.BOOLEAN);

    public MonsterYamEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 250.0)
                .add(Attributes.ARMOR, 12.0)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.275)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.FOLLOW_RANGE, 64.0)
                ;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(SUMMON_TIMER, 0);
        entityData.define(SUMMON_COOLDOWN, 0);
        entityData.define(IS_SUMMONING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("SummonTimer", this.entityData.get(SUMMON_TIMER));
        tag.putInt("SummonCooldown", this.entityData.get(SUMMON_COOLDOWN));
        tag.putBoolean("IsSummoning", this.entityData.get(IS_SUMMONING));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(SUMMON_TIMER, tag.getInt("SummonTimer"));
        this.entityData.set(SUMMON_COOLDOWN, tag.getInt("SummonCooldown"));
        this.entityData.set(IS_SUMMONING, tag.getBoolean("IsSummoning"));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (getSummonCooldown() > 0) {
            setSummonCooldown(getSummonCooldown() - 1);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (getIsSummoning()) {
            setIsSummoning(false);
            setSummonTimer(0);
            setSummonCooldown(400);
        }
        if (source.getEntity() instanceof Player player && (
                player.getMainHandItem().is(ItemTags.HOES) || player.getMainHandItem().is(DDTags.ItemT.CLEAVERS)
                        || source.is(DDDamageTypes.CLEAVER) || source.is(DamageTypeTags.IS_FIRE)
        )) {
            amount *= 1.33F;
        }

        if (source.is(DamageTypeTags.WITHER_IMMUNE_TO)) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            if (entity instanceof LivingEntity living && random.nextFloat() < 0.25F) {
                int duration = 12;
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    duration = 16;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    duration = 24;
                }
                living.addEffect(new MobEffectInstance(DDEffects.PUTRID_SCENT.get(), duration * 20, 1), this);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MobType getMobType() {
        return DDMobTypes.ROTTEN;
    }

    public int getSummonTimer() {
        return this.entityData.get(SUMMON_TIMER);
    }

    public void setSummonTimer(int value) {
        this.entityData.set(SUMMON_TIMER, value);
    }

    public int getSummonCooldown() {
        return this.entityData.get(SUMMON_COOLDOWN);
    }

    public void setSummonCooldown(int value) {
        this.entityData.set(SUMMON_COOLDOWN, value);
    }

    public boolean getIsSummoning() {
        return this.entityData.get(IS_SUMMONING);
    }

    public void setIsSummoning(boolean value) {
        this.entityData.set(IS_SUMMONING, value);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return DDSounds.MONSTER_YAM_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return DDSounds.MONSTER_YAM_HURT.get();
    }

    @Override
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
