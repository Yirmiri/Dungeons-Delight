package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.yirmiri.dungeonsdelight.common.item.StainedCleaverItem;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.*;

public class CleaverEntity extends AbstractArrow {
    public static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.ITEM_STACK);
    public ItemStack cleaverItem;
    private double damage = 0;
    public boolean canBypassCooldowns = false;
    public int despawnTime = 200;
    public boolean spinning = true;
    public boolean hasSetCooldown = false;
    public float ricochetsPitch = 1.0F;
    public int ricochetsLeft = 0;
    public int serratedLevel = 0;
    public int retractionLevel = 0;
    public int persistenceLevel = 0;
    public int soundTickCounter = 0;

    public CleaverEntity(EntityType<? extends CleaverEntity> type, Level level) {
        super(type, level);
    }

    public CleaverEntity(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
        super(DDEntities.CLEAVER.get(), shooter, level, pickupItemStack, null);
        cleaverItem = getItem();
        cleaverItem = getItem().copy();
        setOwner(shooter);
        this.entityData.set(ID_FOIL, pickupItemStack.hasFoil());
    }

    public void setItem(ItemStack stack) {
        this.getEntityData().set(DATA_ITEM_STACK, stack.copyWithCount(1));
    }

    protected ItemStack getItemRaw() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    public ItemStack getItem() {
        ItemStack stack = this.getItemRaw();
        return stack.isEmpty() ? new ItemStack(DDItems.FLINT_CLEAVER.get()) : stack;
    }

    @Override
    public ItemStack getPickupItem() {
        return getItem();
    }

    public ItemStack getWeaponItem() {
        return getItem();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return getItem();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_FOIL, false);
        builder.define(DATA_ITEM_STACK, ItemStack.EMPTY);
    }

    @Override
    protected void updateRotation() {
        this.setXRot(0);
    }

    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }

    @Override
    public void playerTouch(Player player) {
        if (persistenceLevel > 0 && this.inGround && this.ownedBy(player) || this.getOwner() == null && (player.getCooldowns().isOnCooldown(getItem().getItem()))) {
            player.playSound(SoundEvents.ARMOR_EQUIP_GENERIC.value(), 1.0F, 1.0F);
            player.getCooldowns().removeCooldown(getItem().getItem());
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            soundTickCounter++;
            if (soundTickCounter >= 4 && !this.inGround) {
                this.level().playSound(null, this, DDSounds.CLEAVER_FLYING.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                soundTickCounter = 0;
            }
        }

        if (this.inGroundTime > despawnTime) {
            this.discard();
        }

        if (this.shakeTime > 0) {
            --this.shakeTime;
        }

        if (!isInGround()) {
            this.setXRot(this.xRotO - 45);
        }
    }

    public boolean isInGround() {
        return this.inGround && ricochetsLeft <= 0;
    }

    @Override
    public void setBaseDamage(double addedDamage) {
        damage = addedDamage * 1.66;
    }

    @Override
    public double getBaseDamage() {
        return damage;
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        if (ricochetsLeft <= 0) {
            Vec3 vec3 = hitResult.getLocation().subtract(this.getX(), this.getY(), this.getZ());
            this.setDeltaMovement(vec3);
            hasImpulse = true;
            Vec3 vec31 = vec3.normalize().scale(0.05);
            this.setPos(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);

            if (ricochetsLeft == 0) {
                this.inGround = true;
                this.shakeTime = 24;
                playSound(DDSounds.CLEAVER_HIT_BLOCK.get(), 2.0F, level().random.nextFloat() * 0.1F + 0.9F);
            }
        }

        if (getOwner() instanceof Player player) {
            if (!player.getAbilities().instabuild && !canBypassCooldowns && !hasSetCooldown) {
                player.getCooldowns().addCooldown(getItem().getItem(), 50);
                if (ricochetsLeft == 0) {
                    hasSetCooldown = true;
                }
            }

            if (ricochetsLeft > 0) {
                Vec3 reflected = new Vec3(getDeltaMovement().toVector3f().reflect(hitResult.getDirection().step())).scale(0.8F);
                setDeltaMovement(reflected);
                this.setPos(this.getX() + reflected.x, this.getY() + reflected.y, this.getZ() + reflected.z);
                hasImpulse = true;
                ((ServerLevel) level()).getChunkSource().broadcast(this, new ClientboundSetEntityMotionPacket(this.getId(), getDeltaMovement()));
                ricochetsLeft--;
                damage = damage * 1.25;
                playSound(DDSounds.CLEAVER_RICOCHET.get(), 1.0F, ricochetsPitch);
                ricochetsPitch = ricochetsPitch + 0.25F;
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        Entity owner = getOwner();

        if (getItem().is(DDItems.STAINED_CLEAVER.get())) {
            if (this.getOwner() != null && this.getOwner() instanceof Player player && hitResult.getEntity() instanceof LivingEntity target) {
                if (player.getMainHandItem().getItem() instanceof StainedCleaverItem stainedCleaverItem) {
                    //stainedCleaverItem.stainedEffects(player.getMainHandItem(), target, player); //TODO STAINED CLEAVER
                }
            }
        }

        if (!(entity instanceof ItemEntity) && entity.hurt(new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DDDamageTypes.CLEAVER), this, owner == null ? this : owner), (float) damage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity living) {
                if (owner instanceof LivingEntity livingOwner) {
                    if (level() instanceof ServerLevel serverLevel) {
                        EnchantmentHelper.doPostAttackEffectsWithItemSource(serverLevel, livingOwner, new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DDDamageTypes.CLEAVER)), this.getWeaponItem());
                    }

                    if (this.isOnFire()) {
                        entity.setRemainingFireTicks(this.getRemainingFireTicks());
                    }
//pretty sure this code is useless (clean later if so)
//                    if (serratedStrikeLevel > 0) {
//                        int duration = 40 + (serratedStrikeLevel * 20);
//
//                        if (target.hasEffect(DDEffects.SERRATED.get())) {
//                            duration = duration / 2;
//                            duration += target.getEffect(DDEffects.SERRATED.get()).getDuration();
//                        }
//                        target.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), duration, 0));
//                        target.playSound(DDSounds.CLEAVER_SERRATED_STRIKE.get(), 2.0F, 1.0F);
//                    }

                    if (getSerratedLevel() > 0 && !entity.isInvulnerable()) {
                        int duration = 40 + (getSerratedLevel() * 20);

                        if (living.hasEffect(DDEffects.SERRATED)) {
                            duration = duration / 2;
                            duration += living.getEffect(DDEffects.SERRATED).getDuration();
                        }
                        living.addEffect(new MobEffectInstance(DDEffects.SERRATED, duration, 0));
                        living.playSound(DDSounds.CLEAVER_SERRATED_STRIKE.get(), 2.0F, 1.0F);
                    }

                    if (getPersistenceLevel() > 0) {
                        if (!living.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 + (getPersistenceLevel() * 20), 0));
                        }
                    }
                    damage = damage * 0.8; //This decreases damage by 20% when it pierces into another entity
                }
                doPostHurtEffects(living);

                if (retractionLevel > 0 && getOwner() != null) {
                    if (!(entity instanceof Ghast)) {
                        pullEntity(entity, 1.5F);
                    } else {
                        pullEntity(entity, 2.0F);
                    }
                }
            }

            if (getSerratedLevel() <= 0 && !entity.isInvulnerable()) {
                entity.playSound(DDSounds.CLEAVER_HIT_ENTITY.get(), 2.5F, level().random.nextFloat() * 0.1F + 0.9F);
            }
        }

        if (owner instanceof Player player && entity != owner) {
            canBypassCooldowns = true;
            player.getCooldowns().removeCooldown(getItem().getItem()); //This will remove cooldown when entity is hit with cleaver
        }

        if (retractionLevel > 0 && getOwner() != null) {
            if (entity instanceof ItemEntity) {
                pullEntity(entity, 2.0F);
            }
        }
    }

    public void pullEntity(Entity entity, float maxDistance) {
        if (retractionLevel > 0 && getOwner() != null) {
            Vec3 direction = getOwner().position().subtract(entity.position());
            double distance = direction.length();

            if (entity instanceof LivingEntity && distance <= 4.5) {
                return;
            }

            if (distance > 0.01) {
                Vec3 velocity = direction.normalize().scale(Math.min(maxDistance, distance * 0.25));
                entity.setDeltaMovement(entity.getDeltaMovement().add(velocity));
                entity.playSound(DDSounds.CLEAVER_FLYING.get(), 0.75F, -1.0F);
            }
            entity.hurtMarked = true;
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return super.canHitEntity(entity) || entity.isAlive() && entity instanceof ItemEntity;
    }

    public boolean isInCeiling() { //whjat was this even used for hecco
        if (this.noPhysics) {
            return false;
        } else {
            float f = 0.25F * 0.8F;
            BlockPos pos = BlockPos.containing(this.getEyePosition().add(0, 1.0E-6D, 0));
            BlockState blockstate = this.level().getBlockState(pos);
            return
                    !blockstate.isAir() && blockstate.isSuffocating(this.level(), pos) && Shapes.joinIsNotEmpty(blockstate.getCollisionShape(this.level(), pos).move(pos.getX(), pos.getY(), pos.getZ()), Shapes.create(AABB.ofSize(this.getEyePosition(), 0.1, 0.1, 0.1)), BooleanOp.AND
                    );
        }
    }

    public int getPersistenceLevel() {
        return persistenceLevel;
    }


    public int getSerratedLevel() {
        return serratedLevel;
    }

    public void setSerratedLevel(int additionalSerratedLevel) {
        serratedLevel = serratedLevel + additionalSerratedLevel;
    }

    @Override
    protected float getWaterInertia() {
        return 0.75F;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("cleaver", this.cleaverItem.save(this.registryAccess(), new CompoundTag()));
        tag.put("item", this.getItem().save(this.registryAccess(), new CompoundTag()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("cleaver", CompoundTag.TAG_COMPOUND)) {
            this.cleaverItem = ItemStack.parse(this.registryAccess(), tag.getCompound("cleaver")).orElse(DDItems.FLINT_CLEAVER.get().getDefaultInstance());
        }
        if (tag.contains("item", CompoundTag.TAG_COMPOUND)) {
            this.setItem(ItemStack.parse(this.registryAccess(), tag.getCompound("item")).orElse(DDItems.FLINT_CLEAVER.get().getDefaultInstance()));
        }
    }

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }
}
