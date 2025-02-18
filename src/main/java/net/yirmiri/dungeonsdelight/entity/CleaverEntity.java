package net.yirmiri.dungeonsdelight.entity;

import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.yirmiri.dungeonsdelight.registry.*;

public class CleaverEntity extends AbstractArrow {
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.ITEM_STACK);
    public ItemStack cleaverItem;
    private double damage = 0;
    public boolean hasPierced;
    public int ricochetsLeft = 0;

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

    @Override
    protected void doPostHurtEffects(LivingEntity living) {
        super.doPostHurtEffects(living); //TODO: bleed enchant
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
        if (this.ownedBy(player) || this.getOwner() == null && (player.getCooldowns().isOnCooldown(getItem().getItem()))) {
            player.getCooldowns().removeCooldown(getItem().getItem());
        }
    }

    @Override
    public void tick() {
        super.tick();
        //playSound(DDSounds.CLEAVER_FLYING.get(), 1.0F, 1.0F);

        if (this.inGroundTime > 100) {
            this.discard();
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
        damage = addedDamage * 1.75;
    }

    @Override
    public double getBaseDamage() {
        return damage;
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        playSound(DDSounds.CLEAVER_HIT_BLOCK.get(), 2.0F, 1.0F);

        if (ricochetsLeft <= 0) {
            Vec3 vec3 = hitResult.getLocation().subtract(this.getX(), this.getY(), this.getZ());
            this.setDeltaMovement(vec3);
            Vec3 vec31 = vec3.normalize().scale(0.05);
            this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
            this.inGround = true;
        }

        if (getOwner() instanceof Player player) {
            if (!player.getAbilities().instabuild && !hasPierced) {
                player.getCooldowns().addCooldown(getItem().getItem(), 30);
            }

            if (ricochetsLeft > 0) {
                setDeltaMovement(new Vec3 (getDeltaMovement().toVector3f().reflect(hitResult.getDirection().step())).scale(0.66F));
                ricochetsLeft = ricochetsLeft - 1;
                damage = damage * 1.1;
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
                }
                doPostHurtEffects(living);
            }
        }
        hasPierced = true;
        playSound(DDSounds.CLEAVER_HIT_ENTITY.get(), 2.0F, 1.0F);
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
