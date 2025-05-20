package net.yirmiri.dungeonsdelight.common.entity.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

public class CleaverEntity extends AbstractArrow {
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.ITEM_STACK);
    public ItemStack cleaverItem;
    private double damage = 0;
    public boolean canBypassCooldowns = false;
    public int ricochetsLeft = 0;
    public float ricochetsPitch = 1.0F;
    public int serratedLevel = 0;
    public int persistenceLevel = 0;
    public int despawnTime = 200;
    public boolean spinning = true;

    public CleaverEntity(EntityType<? extends CleaverEntity> type, Level level) {
        super(type, level);
    }

    public CleaverEntity(Level level, Player living, ItemStack stack) {
        super(DDEntities.CLEAVER.get(), living, level);
        cleaverItem = getItem();
        cleaverItem = getItem().copy();
        this.entityData.set(ID_FOIL, stack.hasFoil());
    }

    public CleaverEntity(Level level, Position pos, ItemStack stack) {
        super(DDEntities.CLEAVER.get(), level);
        cleaverItem = getItem();
        cleaverItem = getItem().copy();
        this.entityData.set(ID_FOIL, stack.hasFoil());
    }

    public CleaverEntity(Level level, double x, double y, double z) {
        super(DDEntities.CLEAVER.get(), x, y, z, level);
    }

    public void setItem(ItemStack stack) {
        if (stack.hasTag()) {
            this.getEntityData().set(DATA_ITEM_STACK, stack.copyWithCount(1));
        }
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

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_FOIL, false);
        this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);
    }

    @Override
    protected void updateRotation() {
        this.setXRot(0);
    }

    public boolean isFoil() { //TODO: RENDER ENCHANT
        return this.entityData.get(ID_FOIL);
    }

    @Override
    public void playerTouch(Player player) {
        if (persistenceLevel > 0 && this.inGround && this.ownedBy(player) || this.getOwner() == null && (player.getCooldowns().isOnCooldown(getItem().getItem()))) {
            player.getCooldowns().removeCooldown(getItem().getItem());
            player.playSound(SoundEvents.ARMOR_EQUIP_GENERIC, 1.0F, 1.0F);
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        //playSound(DDSounds.CLEAVER_FLYING.get(), 1.0F, 1.0F);

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
                playSound(DDSounds.CLEAVER_HIT_BLOCK.get(), 2.0F, 1.0F);
            }
        }

        if (getOwner() instanceof Player player) {
            if (!player.getAbilities().instabuild && !canBypassCooldowns) {
                player.getCooldowns().addCooldown(getItem().getItem(), 50);
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

        if (entity.hurt(new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DDDamageTypes.CLEAVER), this, owner == null ? this : owner), (float) damage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity living) {
                if (owner instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(living, owner);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) owner, living);

                    if (this.isOnFire()) {
                        entity.setSecondsOnFire(this.getRemainingFireTicks());
                    }

                    if (getSerratedLevel() > 0 && !entity.isInvulnerable()) {
                        int duration = 80 + getSerratedLevel();

                        if (living.hasEffect(DDEffects.SERRATED.get())) {
                            duration += living.getEffect(DDEffects.SERRATED.get()).getDuration();
                        }
                        living.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), duration, 0));
                        living.playSound(DDSounds.CLEAVER_SERRATED_STRIKE.get(), 2.0F, 1.0F);
                    }

                    if (getPersistenceLevel() > 0) {
                        if (!living.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                            living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40 + (getPersistenceLevel() * 20), 0));
                        }
                    }

                    if (owner instanceof Player player && entity != owner) {
                        canBypassCooldowns = true;
                        player.getCooldowns().removeCooldown(getItem().getItem()); //remove cooldown when entity is hit with cleaver
                    }
                    damage = damage * 0.85; //15% of damage is lost upon pierces into another entity
                }
                doPostHurtEffects(living);
            }
        }

        if (getSerratedLevel() <= 0 && !entity.isInvulnerable()) {
            entity.playSound(DDSounds.CLEAVER_HIT_ENTITY.get(), 2.0F, 1.0F);
        }
    }

    public boolean isInCeiling() {
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

    public void setPersistenceLevel(int additionalPersistenceLevel) {
        persistenceLevel = persistenceLevel + additionalPersistenceLevel;
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
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Cleaver", 10)) {
            this.cleaverItem = ItemStack.of(tag.getCompound("Cleaver"));
        }
        this.setItem(ItemStack.of(tag.getCompound("Item")));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("Cleaver", this.cleaverItem.save(new CompoundTag()));

        if (!this.getItemRaw().isEmpty()) {
            tag.put("Item", this.getItemRaw().save(new CompoundTag()));
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
