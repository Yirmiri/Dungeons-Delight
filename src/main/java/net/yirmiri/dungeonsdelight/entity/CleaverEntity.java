package net.yirmiri.dungeonsdelight.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModSounds;

public class CleaverEntity extends ThrownTrident {
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(CleaverEntity.class, EntityDataSerializers.BOOLEAN);
    private ItemStack cleaverItem;
    private double damage = 0;
    private boolean hasPierced;

    public CleaverEntity(EntityType<? extends CleaverEntity> type, Level level) {
        super(type, level);
        cleaverItem = new ItemStack(DDItems.FLINT_CLEAVER.get());
    }

    public CleaverEntity(Level level, Player living, ItemStack stack) {
        super(level, living, stack);
        cleaverItem = stack;
        cleaverItem = stack.copy();
        this.entityData.set(ID_FOIL, stack.hasFoil());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_FOIL, false);
    }

    @Override
    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }

    @Override
    public void tick() {
        super.tick();
        this.level().addParticle(ParticleTypes.CRIT, position().x, position().y, position().z, 0.0, 0.0, 0.0);
        if (this.inGround) {
            discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        if (getOwner() instanceof Player player) {
            if (!player.getAbilities().instabuild && !hasPierced) {
                player.getCooldowns().addCooldown(cleaverItem.getItem(), 16);
            }
        }
        this.discard();
        playSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get(), 1.0F, -1.0F);
    }

    @Override
    public void setBaseDamage(double addedDamage) {
        damage = addedDamage * 1.5;
    }

    @Override
    public double getBaseDamage() {
        return damage;
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        Entity owner = getOwner();
        if (entity.hurt(damageSources().trident(this, owner == null ? this : owner), (float) damage)) {
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
        playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    @Override
    protected float getWaterInertia() {
        return 0.75F;
    }
}
