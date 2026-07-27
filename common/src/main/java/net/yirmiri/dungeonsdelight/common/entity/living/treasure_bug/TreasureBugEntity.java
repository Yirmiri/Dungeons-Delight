package net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug.goal.ClimbingFleePlayerGoal;

public class TreasureBugEntity extends Animal {
    private static final EntityDataAccessor<Byte> IS_CLIMBING = SynchedEntityData.defineId(TreasureBugEntity.class, EntityDataSerializers.BYTE);
    private ItemStack storedItem = ItemStack.EMPTY;

    public TreasureBugEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.xpReward = Enemy.XP_REWARD_MEDIUM;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ClimbingFleePlayerGoal(this, 1.2D, 7.0D));
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.ATTACK_DAMAGE, 1.0F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                ;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    public void setClimbing(boolean climbing) {
        byte climbingState = this.entityData.get(IS_CLIMBING);
        if (climbing) {
            climbingState = (byte) (climbingState | 1);
        } else {
            climbingState = (byte) (climbingState & -2);
        }
        this.entityData.set(IS_CLIMBING, climbingState);
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    public boolean isClimbing() {
        return (this.entityData.get(IS_CLIMBING) & 1) != 0;
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_CLIMBING, (byte) 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (!this.storedItem.isEmpty()) {
            tag.put("StoredItem", this.storedItem.save(new CompoundTag()));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("StoredItem")) {
            this.storedItem = ItemStack.of(tag.getCompound("StoredItem"));
        } else {
            this.storedItem = ItemStack.EMPTY;
        }
    }

    public void setStoredItem(ItemStack stack) {
        this.storedItem = stack.copy();
        storedItem.removeTagKey("TreasureBugInfested");
    }

    public ItemStack getStoredItem() {
        return this.storedItem;
    }

    @Override
    public void die(DamageSource source) {
        if (!this.level().isClientSide && !this.storedItem.isEmpty()) {
            this.spawnAtLocation(this.storedItem);
            this.storedItem = ItemStack.EMPTY;
        }
        super.die(source);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SILVERFISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SILVERFISH_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public double getMyRidingOffset() {
        return 0.1D;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 0.13F;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public boolean canFallInLove() {
        return false;
    }
}