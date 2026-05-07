package net.yirmiri.dungeonsdelight.common.entity.cleaver;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.*;

public class CleaverEntity extends AbstractArrow {
    public static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.ITEM_STACK);
    public ItemStack cleaverItem;
    private double damage = 1;
    public boolean canBypassCooldowns = false;
    public int despawnTime = 200;
    public boolean spinning = true;
    public boolean hasSetCooldown = false;
    public float ricochetsPitch = 1.0F;
    public int ricochetsLeft = 0;
    public int serratedLevel = 0;
    public int soundTickCounter = 0;
    public boolean fullyCharged = false;
    public boolean longCooldown;
    public Direction blockSide = null;
    public float embeddedRotOffset = 0;

    public CleaverEntity(EntityType<? extends CleaverEntity> type, Level level) {
        super(type, level);
    }

    public CleaverEntity(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
        super(DDEntities.CLEAVER.get(), shooter, level);
        cleaverItem = getCleaverStack();
        cleaverItem = getCleaverStack().copy();
        setOwner(shooter);
        this.entityData.set(ID_FOIL, pickupItemStack.hasFoil());
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    @Override
    public void playerTouch(Player entity) { //prevents picking up a cleaver itemstack

    }

    public void setItem(ItemStack stack) {
        this.getEntityData().set(DATA_ITEM_STACK, stack.copyWithCount(1));
    }

    protected ItemStack getItemRaw() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    public ItemStack getCleaverStack() {
        ItemStack stack = this.getItemRaw();
        return stack.isEmpty() ? new ItemStack(DDItems.FLINT_CLEAVER.get()) : stack;
    }

    @Override
    public ItemStack getPickupItem() {
        return getCleaverStack();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_FOIL, false);
        this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);
    }

    @Override
    protected void updateRotation() {
        this.setXRot(0);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            soundTickCounter++;
            if (soundTickCounter >= 4 + (this.tickCount / 10) && !this.inGround) {
                this.level().playSound(null, this, DDSounds.CLEAVER_FLYING.get(), SoundSource.PLAYERS, Math.max(2.0F - this.tickCount / 60F, 0), 1.0F - this.tickCount / 100F);
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
        damage = addedDamage;
    }

    @Override
    public double getBaseDamage() {
        return damage;
    }

    public int getSerratedLevel() {
        return serratedLevel;
    }

    public void setSerratedLevel(int newSerratedLevel) {
        serratedLevel += newSerratedLevel;
    }

    public void setFullyCharged(boolean newBoolean) {
        fullyCharged = newBoolean;
    }

    public boolean getFullyCharged() {
        return fullyCharged;
    }

    public void setLongCooldown(boolean newMissCooldown) {
        longCooldown = newMissCooldown;
    }

    public boolean getMissCooldown() {
        return longCooldown;
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        this.blockSide = hitResult.getDirection();
        embeddedRotOffset = random.nextFloat() * 45;
        if (ricochetsLeft <= 0) {
            Vec3 vec3 = hitResult.getLocation().subtract(this.getX(), this.getY(), this.getZ());
            this.setDeltaMovement(vec3);
            hasImpulse = true;
            Vec3 vec31 = vec3.normalize().scale(0.05);
            this.setPos(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);

            if (ricochetsLeft == 0) {
                this.inGround = true;
                this.shakeTime = 24;
                playSound(DDSounds.CLEAVER_HIT_BLOCK.get(), 1.7F, level().random.nextFloat() * 0.1F + 0.9F);
            }
        }

        if (getOwner() instanceof Player player) {
            if (ricochetsLeft > 0) {
                Vec3 reflected = new Vec3(getDeltaMovement().toVector3f().reflect(hitResult.getDirection().step())).scale(0.8F);
                setDeltaMovement(reflected);
                this.setPos(this.getX() + reflected.x, this.getY() + reflected.y, this.getZ() + reflected.z);
                hasImpulse = true;
                ((ServerLevel) level()).getChunkSource().broadcast(this, new ClientboundSetEntityMotionPacket(this.getId(), getDeltaMovement()));
                ricochetsLeft--;
                damage *= 1.33;
                playSound(DDSounds.CLEAVER_RICOCHET.get(), 1.0F, ricochetsPitch);
                ricochetsPitch = ricochetsPitch + 0.25F;
            }

            if (!player.getAbilities().instabuild && !canBypassCooldowns && !hasSetCooldown) {
                if (longCooldown) {
                    for (Holder<Item> item : BuiltInRegistries.ITEM.getTagOrEmpty(DDTags.ItemT.CLEAVERS)) {
                        player.getCooldowns().addCooldown(item.value(), 50);
                    }
                }
                if (!longCooldown) {
                    for (Holder<Item> item : BuiltInRegistries.ITEM.getTagOrEmpty(DDTags.ItemT.CLEAVERS)) {
                        player.getCooldowns().addCooldown(item.value(), 50);
                    }
                }
                hasSetCooldown = true;
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        Entity owner = getOwner();

        if (!(entity instanceof ItemEntity) && entity.hurt(new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DDDamageTypes.CLEAVER), this, owner == null ? this : owner), (float) damage)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity living) {
                if (owner instanceof LivingEntity livingOwner) {
                    if (level() instanceof ServerLevel serverLevel) {
//                        EnchantmentHelper.doPostHurtEffects(serverLevel, livingOwner, new DamageSource(this.level().registryAccess().registryOrThrow(
//                                Registries.DAMAGE_TYPE).getHolderOrThrow(DDDamageTypes.CLEAVER)), this.getWeaponItem());

                        EnchantmentHelper.doPostHurtEffects(living, owner);
                        EnchantmentHelper.doPostDamageEffects(livingOwner, living);
                    }

                    if (this.isOnFire()) {
                        entity.setRemainingFireTicks(this.getRemainingFireTicks());
                    }

                    if (getSerratedLevel() > 0 && !entity.isInvulnerable()) {
                        int duration = 40 + (getSerratedLevel() * 20);

                        if (getFullyCharged()) {
                            duration += 30;
                        }

                        if (living.hasEffect(DDEffects.SERRATED.get())) {
                            duration /= 2;
                            duration += living.getEffect(DDEffects.SERRATED.get()).getDuration();
                        }
                        living.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), duration, 0));
                        living.playSound(DDSounds.CLEAVER_SERRATED_STRIKE.get(), 1.7F, 1.0F);
                    }
                    damage *= 0.8; //This decreases damage by 20% when it pierces into another entity
                }
                doPostHurtEffects(living);
            }

            if (getSerratedLevel() <= 0 && !entity.isInvulnerable()) {
                entity.playSound(DDSounds.CLEAVER_HIT_ENTITY.get(), 1.7F, level().random.nextFloat() * 0.1F + 0.9F);
            }
        }

        if (owner instanceof Player player && entity != owner && !(entity instanceof CleaverEntity)) {
            canBypassCooldowns = true;
            player.getCooldowns().removeCooldown(getCleaverStack().getItem()); //This will remove cooldown when entity is hit with cleaver
        }
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return super.canHitEntity(entity) || entity.isAlive() && entity instanceof ItemEntity;
    }

    @Override
    protected float getWaterInertia() {
        return 0.75F;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("Cleaver", this.cleaverItem.save(new CompoundTag()));

        if (!this.getItemRaw().isEmpty()) {
            tag.put("Item", this.getItemRaw().save(new CompoundTag()));
        }
        if (tag.contains("BlockSide")) {
            this.blockSide = Direction.values()[tag.getInt("BlockSide")];
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Cleaver", 10)) {
            this.cleaverItem = ItemStack.of(tag.getCompound("Cleaver"));
        }
        this.setItem(ItemStack.of(tag.getCompound("Item")));
        if (blockSide != null) {
            tag.putInt("BlockSide", blockSide.ordinal());
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