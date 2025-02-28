package net.yirmiri.dungeonsdelight.entity.monster_yam;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.registry.DDSounds;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class MonsterYamEntity extends Monster {
    public AnimationState risingState = new AnimationState();

    public MonsterYamEntity(EntityType<? extends MonsterYamEntity> type, Level level) {
        super(type, level);
    }

    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, this.hasPose(Pose.EMERGING) ? 1 : 0);
    }

    public void recreateFromPacket(ClientboundAddEntityPacket clientboundAddEntityPacket) {
        super.recreateFromPacket(clientboundAddEntityPacket);
        if (clientboundAddEntityPacket.getData() == 1) {
            this.setPose(Pose.EMERGING);
        }
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

        if (this.getPose().equals(Pose.EMERGING)) {
            this.clientDiggingParticles(this.risingState);
        }
    }

    private void clientDiggingParticles(AnimationState animState) {
        if ((float)animState.getAccumulatedTime() < 4500.0F) {
            if (this.getBlockStateOn().getRenderShape() != RenderShape.INVISIBLE) {
                for (int i = 0; i < 30; ++i) {
                    double x = this.getX() + Mth.randomBetween(this.getRandom(), -0.3F, 0.3F);
                    double y = this.getY();
                    double z = this.getZ() + Mth.randomBetween(this.getRandom(), -0.3F, 0.3F);
                    this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockStateOn()), x, y, z, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        if (DATA_POSE.equals(accessor) && this.getPose().equals(Pose.EMERGING)) {
            this.risingState.start(this.tickCount);
        }
        super.onSyncedDataUpdated(accessor);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance instance, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        this.getBrain().setMemoryWithExpiry(MemoryModuleType.DIG_COOLDOWN, Unit.INSTANCE, 1200L);
        this.setPose(Pose.EMERGING);
        this.getBrain().setMemoryWithExpiry(MemoryModuleType.IS_EMERGING, Unit.INSTANCE, Mth.ceil(64.0F));
        this.playSound(SoundEvents.WARDEN_EMERGE, 2.0F, 1.0F);
        return super.finalizeSpawn(accessor, instance, type, data, tag);
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
