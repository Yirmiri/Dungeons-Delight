package net.yirmiri.dungeonsdelight.common.entity.misc.leftovers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;

public class LeftoversEntity extends Entity {
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(LeftoversEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(LeftoversEntity.class, EntityDataSerializers.INT);

    private int age;
    private int health;
    private final int maxAge;
    private MobEffect effect;
    private int initialDuration;
    private int effectDuration;
    private int effectAmplifier;
    private int effectMaxDuration;
    private int hunger;
    private float saturation;
    private float bounce = 0.5F;

    public LeftoversEntity(Level level, double x, double y, double z, int maxAge, MobEffect effect, int initialDuration, int effectDuration, int effectAmplifier, int effectMaxDuration, int hunger, float saturation) {
        this(level, x, y, z, maxAge, effect, initialDuration, effectDuration, effectAmplifier, effectMaxDuration, hunger, saturation, LeftoversType.GENERIC);
    }

    public LeftoversEntity(Level level, double x, double y, double z, int maxAge, MobEffect effect, int initialDuration, int effectDuration, int effectAmplifier, int effectMaxDuration, int hunger, float saturation, LeftoversType type) {
        super(DDEntities.LEFTOVERS.get(), level);
        this.setPos(x, y, z);
        this.setYRot((float)(this.random.nextDouble() * (double)360.0F));
        this.setDeltaMovement((this.random.nextDouble() * (double)0.2F - (double)0.1F) * (double)2.0F, this.random.nextDouble() * 0.2 * (double)2.0F, (this.random.nextDouble() * (double)0.2F - (double)0.1F) * (double)2.0F);
        this.health = 5;
        this.maxAge = maxAge;
        this.effect = effect;
        this.initialDuration = initialDuration;
        this.effectDuration = effectDuration;
        this.effectAmplifier = effectAmplifier;
        this.effectMaxDuration = effectMaxDuration;
        this.hunger = hunger;
        this.saturation = saturation;
        this.setLeftoversType(type);
        this.setVariant(this.random.nextInt(type.getMaxVariants() + 1));
    }

    public LeftoversEntity(EntityType<? extends LeftoversEntity> entityType, Level level) {
        super(entityType, level);
        this.health = 5;
        this.maxAge = 200;
        this.effect = null;
        this.effectDuration = 0;
        this.effectAmplifier = 0;
        this.effectMaxDuration = 0;
        this.hunger = 0;
        this.saturation = 0;
        this.setLeftoversType(LeftoversType.GENERIC);
        this.setVariant(0);
    }

    public LeftoversType getLeftoversType() {
        return LeftoversType.values()[Mth.clamp(this.entityData.get(TYPE), 0, LeftoversType.values().length - 1)];
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setLeftoversType(LeftoversType type) {
        this.entityData.set(TYPE, type.ordinal());
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, Mth.clamp(variant, 0, this.getLeftoversType().getMaxVariants()));
    }

    public int getAge() {
        return this.age;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    protected BlockPos getBlockPosBelowThatAffectsMyMovement() {
        return this.getOnPos(0.999999F);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (this.level().isClientSide()) {
            return true;
        } else {
            this.markHurt();
            this.health = (int)((float)this.health - amount);
            if (this.health <= 0) {
                this.discard();
            }
            return true;
        }
    }

    private void setUnderwaterMovement() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x * (double)0.99F, Math.min(vec3.y + (double)5.0E-4F, 0.06F), vec3.z * (double)0.99F);
    }

    @Override
    protected void doWaterSplashEffect() {
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE, LeftoversType.GENERIC.ordinal());
        this.entityData.define(VARIANT, 0);
    }

    @Override
    public void tick() {
        super.tick();
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();

        if (this.isEyeInFluid(FluidTags.WATER)) {
            this.setUnderwaterMovement();
        } else if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0F, -0.03, 0.0F));
        }

        if (this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA)) {
            this.setDeltaMovement((this.random.nextFloat() - this.random.nextFloat()) * 0.2F, 0.2F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        }

        if (!this.level().noCollision(this.getBoundingBox())) {
            this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / (double)2.0F, this.getZ());
        }

        Vec3 movement = this.getDeltaMovement();
        this.move(MoverType.SELF, movement);

        Vec3 velocity = this.getDeltaMovement();
        boolean bounced = false;

        if (this.verticalCollision && Math.abs(movement.y) > 0.01D) {
            if (this.bounce > 0.05F) {
                velocity = new Vec3(velocity.x, Math.abs(movement.y) * this.bounce, velocity.z);
                this.bounce *= 0.65F;
                bounced = true;
            } else {
                velocity = new Vec3(velocity.x, 0.0D, velocity.z);
            }
        }

        if (this.horizontalCollision) {
            if (Math.abs(movement.x) > 0.01D && this.bounce > 0.05F) {
                velocity = new Vec3(-movement.x * this.bounce, velocity.y, velocity.z);
                this.bounce *= 0.65F;
                bounced = true;
            }

            if (Math.abs(movement.z) > 0.01D && this.bounce > 0.05F) {
                velocity = new Vec3(velocity.x, velocity.y, -movement.z * this.bounce);
                this.bounce *= 0.65F;
                bounced = true;
            }

            if (!bounced) {
                velocity = new Vec3(0.0D, velocity.y, 0.0D);
            }
        }

        this.setDeltaMovement(velocity);

        if (bounced) {
            this.setPos(this.getX() + velocity.x * 0.5D, this.getY() + velocity.y * 0.5D, this.getZ() + velocity.z * 0.5D);
            this.xo = this.getX();
            this.yo = this.getY();
            this.zo = this.getZ();
            this.hasImpulse = true;

            if (!this.level().isClientSide && this.level() instanceof ServerLevel serverLevel) {
                serverLevel.getChunkSource().broadcast(this, new ClientboundSetEntityMotionPacket(this.getId(), this.getDeltaMovement()));
            }
        }

        float f = 0.98F;
        if (this.onGround()) {
            f = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getBlock().getFriction() * 0.98F;
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(f, 0.98, f));

        if (this.onGround() && Math.abs(this.getDeltaMovement().y) < 0.01D) {
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0D, this.getDeltaMovement().z);
        }

        ++this.age;
        if (this.age >= this.maxAge) {
            this.discard();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putShort("Health", (short)this.health);
        compound.putShort("Age", (short)this.age);
        compound.putShort("Hunger", (short)this.hunger);
        compound.putFloat("Saturation", this.saturation);
        compound.putShort("EffectAmplifier", (short)this.effectAmplifier);
        compound.putInt("InitialDuration", this.initialDuration);
        compound.putInt("EffectDuration", this.effectDuration);
        compound.putInt("EffectMaxDuration", this.effectMaxDuration);
        compound.putFloat("Bounce", this.bounce);
        compound.putString("Type", this.getLeftoversType().name());
        compound.putInt("Variant", this.getVariant());

        if (this.effect != null) {
            ResourceLocation effectId = BuiltInRegistries.MOB_EFFECT.getKey(this.effect);
            if (effectId != null) {
                compound.putString("Effect", effectId.toString());
            }
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        this.health = compound.getShort("Health");
        this.age = compound.getShort("Age");
        this.hunger = compound.getShort("Hunger");
        this.saturation = compound.getFloat("Saturation");
        this.effectAmplifier = compound.getShort("EffectAmplifier");
        this.initialDuration = compound.getInt("InitialDuration");
        this.effectDuration = compound.getInt("EffectDuration");
        this.effectMaxDuration = compound.getInt("EffectMaxDuration");
        this.bounce = compound.getFloat("Bounce");

        if (compound.contains("Type")) {
            try {
                this.setLeftoversType(LeftoversType.valueOf(compound.getString("Type")));
            } catch (IllegalArgumentException exception) {
                this.setLeftoversType(LeftoversType.GENERIC);
            }
        } else {
            this.setLeftoversType(LeftoversType.GENERIC);
        }

        this.setVariant(compound.contains("Variant") ? compound.getInt("Variant") : 0);

        if (compound.contains("Effect")) {
            ResourceLocation effectId = ResourceLocation.tryParse(compound.getString("Effect"));
            if (effectId != null) {
                this.effect = BuiltInRegistries.MOB_EFFECT.get(effectId);
            }
        }
    }

    @Override
    public void playerTouch(Player entity) {
        if (!this.level().isClientSide && entity.takeXpDelay == 0) {
            entity.takeXpDelay = 2;
            entity.take(this, 1);

            if (this.effect != null && this.effectDuration > 0) {
                MobEffectInstance existingEffect = entity.getEffect(this.effect);
                if (existingEffect != null) {
                    int currentDuration = existingEffect.getDuration();
                    int newDuration = currentDuration + this.effectDuration;

                    if (this.effectMaxDuration > 0 && currentDuration < this.effectMaxDuration) {
                        newDuration = Math.min(newDuration, this.effectMaxDuration);
                    } else {
                        newDuration = currentDuration;
                    }

                    entity.addEffect(new MobEffectInstance(this.effect, newDuration, Math.max(existingEffect.getAmplifier(), this.effectAmplifier)));
                } else {
                    entity.addEffect(new MobEffectInstance(this.effect, this.initialDuration, this.effectAmplifier));
                }
            }

            if (this.hunger < 0 && entity.getFoodData().getFoodLevel() > 0) {
                entity.getFoodData().setFoodLevel(Math.max(0, entity.getFoodData().getFoodLevel() + this.hunger));
            } else if (this.hunger > 0 && entity.getFoodData().getFoodLevel() < 20) {
                entity.getFoodData().setFoodLevel(Math.min(20, entity.getFoodData().getFoodLevel() + this.hunger));
            }

            if (this.saturation < 0 && entity.getFoodData().getSaturationLevel() > 0) {
                entity.getFoodData().setSaturation(Math.max(0.0F, entity.getFoodData().getSaturationLevel() + this.saturation));
            } else if (this.saturation > 0 && entity.getFoodData().getSaturationLevel() < entity.getFoodData().getFoodLevel()) {
                entity.getFoodData().setSaturation(Math.min(entity.getFoodData().getFoodLevel(), entity.getFoodData().getSaturationLevel() + this.saturation));
            }

            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 1.0F, 1.0F);
            this.discard();
        }
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.AMBIENT;
    }

    public enum LeftoversType {
        GENERIC("generic", 2),
        GENERIC_FRIENDLY("generic_friendly", 3),
        UNDEAD("undead", 4),
        ARTHROPOD("arthropod", 5),
        ROTTEN("rotten", 4),
        SLIME("slime", 2),
        NETHER("nether", 1),
        FRUIT("fruit", 1),
        RARE("rare", 0);

        private final String folder;
        private final int maxVariants;

        LeftoversType(String folder, int maxVariants) {
            this.folder = folder;
            this.maxVariants = maxVariants;
        }

        public String getFolder() {
            return this.folder;
        }

        public int getMaxVariants() {
            return this.maxVariants;
        }
    }
}