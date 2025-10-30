package net.yirmiri.dungeonsdelight.common.entity.zombified_dryad;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.yirmiri.dungeonsdelight.core.init.DDDamageTypes;
import net.yirmiri.dungeonsdelight.core.registry.*;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;

public class ZombifiedDryadEntity extends Zombie {
    public ZombifiedDryadEntity(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ARMOR, 6.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            if (entity instanceof LivingEntity living) {
                int duration = 4;
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    duration = 6;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    duration = 8;
                }
                living.addEffect(new MobEffectInstance(DDEffects.PUTRID_SCENT, duration * 20, 0), this);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!hasEffect(DDEffects.RAVENOUS_RUSH) && this.getItemBySlot(EquipmentSlot.HEAD).is(DDItems.LIVING_JACK_O_LANTERN.get())) {
            addEffect(new MobEffectInstance(DDEffects.ROTGUT, 20, 0));
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player && player.getMainHandItem().is(ModTags.KNIVES) || source.is(DDDamageTypes.CLEAVER)) {
            if (this.getItemBySlot(EquipmentSlot.HEAD).is(DDItems.LIVING_JACK_O_LANTERN.get()) && !this.level().isClientSide) {
                setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                playSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get());
                flingDatSlice(DDItems.ROTGOURD_SLICE.get().getDefaultInstance(), -0.5F, 1F, -0.5F, true, false);
                flingDatSlice(DDItems.ROTGOURD_SLICE.get().getDefaultInstance(), -0.5F, 1F, -0.5F, true, true);
                flingDatSlice(DDItems.ROTGOURD_SLICE.get().getDefaultInstance(), 0.5F, 1F, 0.5F, false, false);
                flingDatSlice(DDItems.ROTGOURD_SLICE.get().getDefaultInstance(), 0.5F, 1F, 0.5F, false, true);
                int expOutput = 3 + this.level().random.nextInt(5) + this.level().random.nextInt(5);
                ExperienceOrb.award((ServerLevel) this.level(), this.position(), expOutput);
                ((ServerLevel) this.level()).sendParticles(DDParticles.SPIRIT.get(), this.getX(), this.getY() + 1.0, this.getZ(), 12, 0.25, 0.25, 0.25, 0.1);
                this.level().broadcastEntityEvent(this, (byte) 3);

                if (source.getEntity() instanceof ServerPlayer player) {
                    DDCriteriaTriggers.FREE_DRYAD.get().trigger(player.connection.getPlayer());
                }
            }
        }
        return super.hurt(source, amount);
    }

    @Override
    public void handleEntityEvent(byte id) {
        ItemStack entityStack = new ItemStack(DDItems.ROTGOURD_SLICE.get());
        if (id == 3) {
            for (int i = 0; i < 12; ++i) {
                this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, entityStack), this.getX(), this.getY(), this.getZ(),
                        (this.random.nextFloat() * 2.0 - 1.0) * 0.1, (this.random.nextFloat() * 2.0 - 1.0)
                                * 0.1 + 0.1, (this.random.nextFloat() * 2.0 - 1.0) * 0.1);
            }
        }
    }

    @Nullable
    public ItemEntity flingDatSlice(ItemStack stack, float offsetX, float offsetY, float offsetZ, boolean reverseX, boolean reverseZ) {
        if (stack.isEmpty()) {
            return null;
        } else if (this.level().isClientSide) {
            return null;
        } else {
            ItemEntity itementity = new ItemEntity(this.level(), this.getX() + (double) offsetX, this.getY() + (double) offsetY, this.getZ() + (double) offsetZ, stack);
            itementity.setDefaultPickUpDelay();

            double velocity = 0.15D;
            itementity.setDeltaMovement(reverseX ? -velocity : velocity, 0.0D, reverseZ ? -velocity : velocity);

            if (this.captureDrops() != null) {
                this.captureDrops().add(itementity);
            } else {
                this.level().addFreshEntity(itementity);
            }
            return itementity;
        }
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, SpawnGroupData data) {
        data = super.finalizeSpawn(accessor, difficultyInstance, spawnType, data);
        if (this.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty() && accessor.getRandom().nextFloat() < 0.03F) {
            this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(DDItems.ROTBULB.get()));
            this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
        }
        if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && accessor.getRandom().nextFloat() < 0.25F) {
            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(DDItems.LIVING_JACK_O_LANTERN.get()));
        }
        return data;
    }

    @Override
    protected boolean canReplaceCurrentItem(ItemStack stack, ItemStack stack1) {
        if (stack1.is(DDItems.ROTBULB.get())) {
            return false;
        } else {
            return super.canReplaceCurrentItem(stack, stack1);
        }
    }

    @Override
    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }

    protected SoundEvent getAmbientSound() {
        return DDSounds.ZOMBIFIED_DRYAD_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return DDSounds.ZOMBIFIED_DRYAD_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return DDSounds.ZOMBIFIED_DRYAD_DEATH.get();
    }
}
