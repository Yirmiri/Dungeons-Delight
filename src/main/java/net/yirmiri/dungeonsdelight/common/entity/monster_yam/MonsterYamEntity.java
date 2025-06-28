package net.yirmiri.dungeonsdelight.common.entity.monster_yam;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;

import java.util.EnumSet;
import java.util.List;

public class MonsterYamEntity extends Monster {
    private int summonCooldown = 0;
    private int summonTimer = 0;
    private boolean isSummoning = false;

    public MonsterYamEntity(EntityType<? extends MonsterYamEntity> type, Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RotCropGoal(this, 1.0));
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
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.24)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.ARMOR, 2.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2);
    }

    public boolean isSummoning() {
        return this.isSummoning;
    }

    public void setSummoning(boolean summoning) {
        this.isSummoning = summoning;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (summonCooldown > 0) {
                summonCooldown--;
            }

            List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(6.0D), Entity::isAlive);
            for (LivingEntity livingEntity : list) {
                if (livingEntity.isAlive() && livingEntity.getMobType().equals(MobType.UNDEAD)) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 0));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0));
                }
            }

            if (this.getTarget() instanceof Player && summonCooldown == 0) {
                if (!isSummoning()) {
                    setSummoning(true);
                    summonTimer = 40;
                    this.setDeltaMovement(0, 0, 0);
                } else {
                    summonTimer--;
                    this.setDeltaMovement(0, 0, 0);

                    if (summonTimer <= 0) {
                        List<Zombie> nearbyZombies = this.level().getEntitiesOfClass(Zombie.class, this.getBoundingBox().inflate(24.0D));
                        if (nearbyZombies.isEmpty()) {
                            for (int i = 0; i < 3; i++) {
                                Zombie zombie = EntityType.ZOMBIE.create(this.level());
                                if (zombie != null) {
                                    BlockPos spawnPos = this.blockPosition().offset(
                                            (int) ((this.random.nextDouble() - 0.5) * 4),
                                            0,
                                            (int) ((this.random.nextDouble() - 0.5) * 4)
                                    );
                                    zombie.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, this.random.nextFloat() * 360F, 0);
                                    level().addFreshEntity(zombie);
                                }
                            }
                            ((ServerLevel) level()).sendParticles(ParticleTypes.POOF, this.getX(), this.getY() + 1, this.getZ(), 10, 0.5, 0.5, 0.5, 0.1);
                        }
                        setSummoning(false);
                        summonCooldown = 600;
                    }
                }
            } else {
                if (isSummoning()) {
                    setSummoning(false);
                    summonTimer = 0;
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean result = super.hurt(source, amount);
        if (result && isSummoning()) {
            setSummoning(false);
            summonTimer = 0;
            summonCooldown = 600;
        }
        return result;
    }

    @Override
    public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) {
        return false;
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

    public static class RotCropGoal extends Goal {
        private final MonsterYamEntity entity;
        private final double speed;
        private BlockPos targetCrop;

        public RotCropGoal(MonsterYamEntity entity, double speed) {
            this.entity = entity;
            this.speed = speed;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            BlockPos entityPos = entity.blockPosition();

            if (!shouldSearch()) {
                return false;
            }

            for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-5, -1, -5), entityPos.offset(5, 1, 5))) {
                BlockState state = entity.level().getBlockState(pos);
                if (canCropRot(state)) {
                    if (entity.getNavigation().createPath(pos, 0) != null) {
                        this.targetCrop = pos.immutable();
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return targetCrop != null && !entity.getNavigation().isDone();
        }

        @Override
        public void start() {
            if (targetCrop != null) {
                entity.getNavigation().moveTo(targetCrop.getX() + 0.5, targetCrop.getY(), targetCrop.getZ() + 0.5, speed);
            }
        }

        @Override
        public void tick() {
            if (targetCrop != null) {
                Vec3 targetVec = Vec3.atCenterOf(targetCrop);
                if (entity.distanceToSqr(targetVec) < 1.5 * 1.5) {
                    if (canCropRot(entity.level().getBlockState(targetCrop))) {
                        rotCrop(targetCrop);
                    }
                    targetCrop = null;
                }
            }
        }

        private boolean shouldSearch() {
            return entity.getRandom().nextInt(20) == 0;
        }

        private boolean canCropRot(BlockState state) {
            if (!(state.getBlock() instanceof CropBlock cropBlock)) {
                return false;
            }

            if (!cropBlock.isMaxAge(state)) {
                return false;
            }

            if (cropBlock instanceof TomatoVineBlock) {
                return !state.getValue(TomatoVineBlock.ROPELOGGED);
            }
            return true;
        }

        private void rotCrop(BlockPos pos) {
            Level level = entity.level();
            BlockState oldState = level.getBlockState(pos);
            BlockState newState = DDBlocks.ROTTEN_CROP.get().defaultBlockState();

            if (oldState.getBlock() instanceof PotatoBlock) {
                newState = DDBlocks.ROTTEN_POTATOES.get().defaultBlockState();
            } else if (oldState.getBlock() instanceof TomatoVineBlock) {
                newState = DDBlocks.ROTTEN_TOMATOES.get().defaultBlockState();
            }
            level.setBlockAndUpdate(pos, newState);

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, oldState), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 0.05);
            }
            level.playSound(null, pos, SoundEvents.CROP_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
