package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(value = Mob.class, priority = 2000)
public abstract class HollowingMixin {
    @Unique
    Mob mob = (Mob) (Object) this;

    @Unique
    private int hollowingTime = -1;

    @Unique
    protected boolean isHollowing = false;

    @Unique
    private UUID conversionStarter;

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (mob.level().isClientSide) return;

        if (stack.is(DDItems.BLACK_APPLE.get()) && mob.hasEffect(DDEffects.HOLLOWED.get()) && !this.isHollowing && mob.getType().is(DDTags.EntityT.CAN_HOLLOW)) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            player.swing(hand, true);
            startHollowing(player.getUUID(), mob);
            cir.setReturnValue(InteractionResult.CONSUME);
            cir.cancel();
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        if (mob.level().isClientSide) return;

        if (this.isHollowing) {
            this.hollowingTime--;
            if (this.hollowingTime <= 0) {
                ServerLevel serverLevel = (ServerLevel) mob.level();

                Mob convertedMob = convertMob(serverLevel, mob);

                if (convertedMob != null) {
                    if (this.conversionStarter != null) {
                        Player player = serverLevel.getPlayerByUUID(this.conversionStarter);
                        if (player instanceof ServerPlayer) {
                            //todo advancement/criteria trigger for conversionStarter
                            if (mob instanceof Villager villager) {
                                serverLevel.onReputationEvent(ReputationEventType.VILLAGER_KILLED, player, villager);
                            }
                        }
                    }

                    convertedMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
                    convertedMob.removeEffect(DDEffects.HOLLOWED.get());
                    convertedMob.playSound(SoundEvents.ZOMBIE_INFECT, 1.0F, 1.0F);
                }
                this.isHollowing = false;
            }
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        compound.putBoolean("IsHollowing", this.isHollowing);
        compound.putInt("HollowingTime", this.hollowingTime);
        if (this.conversionStarter != null) {
            compound.putUUID("ConversionPlayer", this.conversionStarter);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        this.isHollowing = compound.getBoolean("IsHollowing");
        this.hollowingTime = compound.getInt("HollowingTime");
        if (compound.hasUUID("ConversionPlayer")) {
            this.conversionStarter = compound.getUUID("ConversionPlayer");
        }
    }

    @Unique
    protected void startHollowing(UUID conversionStarter, Mob mob) {
        if (conversionStarter != null) {
            this.conversionStarter = conversionStarter;
        }
        this.isHollowing = true;
        this.hollowingTime = DungeonsDelight.CONFIG.getHollowingTicks() + mob.getRandom().nextInt(DungeonsDelight.CONFIG.getHollowingMaxRandomTicks());

        mob.level().broadcastEntityEvent(mob, (byte) 16);
        mob.playSound(DDSounds.GENERIC_MONSTERIZE.get(), 1.0F, 1.0F);
    }

    @Unique
    private static Mob convertMob(ServerLevel level, Mob mob) {
        //VILLAGER
        if (mob instanceof Villager villager) {
            ZombieVillager zombie = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);

            if (zombie != null) {
                zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                        MobSpawnType.CONVERSION, null, null);
            }
            return zombie;
        }

        //HORSE
        if (mob instanceof Horse horse) {
            UUID owner = horse.getOwnerUUID();
            boolean tamed = horse.isTamed();
            boolean saddle = horse.isSaddled();
            ItemStack armor = horse.getArmor().copy();
            boolean armorEquipped = horse.isWearingArmor();

            ZombieHorse zombie = horse.convertTo(EntityType.ZOMBIE_HORSE, true);

            if (zombie != null) {
                zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                        MobSpawnType.CONVERSION, null, null);

                zombie.getAttribute(Attributes.MAX_HEALTH).setBaseValue(horse.getAttributeBaseValue(Attributes.MAX_HEALTH));
                zombie.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(horse.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
                zombie.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue(horse.getAttributeBaseValue(Attributes.JUMP_STRENGTH));
                zombie.setHealth((float) Math.min(zombie.getHealth(), horse.getAttributeBaseValue(Attributes.MAX_HEALTH)));

                if (tamed) {
                    zombie.setTamed(true);
                    zombie.setOwnerUUID(owner);
                }
                if (saddle) {
                    zombie.getSlot(400).set(Items.SADDLE.getDefaultInstance());
                }
                //zombie.getSlot(401).set(armor); //maybe add armor slot and if so it will prevent burning in daylight
                if (armorEquipped) {
                    ItemEntity itemEntity = EntityType.ITEM.create(level);
                    itemEntity.setDefaultPickUpDelay();
                    itemEntity.setItem(armor);
                    itemEntity.moveTo(zombie.getX(), zombie.getY(), zombie.getZ());
                    level.addFreshEntity(itemEntity);
                }
            }
            return zombie;
        }

        //CAMEL
        if (mob instanceof Camel camel) {
            UUID owner = camel.getOwnerUUID();
            boolean tamed = camel.isTamed();
            boolean saddle = camel.isSaddled();

            CamelHuskEntity zombie = camel.convertTo(DDEntities.CAMEL_HUSK.get(), true);

            if (zombie != null) {
                zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                        MobSpawnType.CONVERSION, null, null);

                if (tamed) {
                    zombie.setTamed(true);
                    zombie.setOwnerUUID(owner);
                }
                if (saddle) {
                    zombie.getSlot(400).set(Items.SADDLE.getDefaultInstance());
                }
            }
            return zombie;
        }

        //PIGLIN
        if (mob instanceof Piglin piglin) {
            ZombifiedPiglin zombie = piglin.convertTo(EntityType.ZOMBIFIED_PIGLIN, true);

            if (zombie != null) {
                zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                        MobSpawnType.CONVERSION, null, null);
            }
            return zombie;
        }

        //HOGLIN
        if (mob instanceof Hoglin hoglin) {
            Zoglin zombie = hoglin.convertTo(EntityType.ZOGLIN, true);

            if (zombie != null) {
                zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                        MobSpawnType.CONVERSION, null, null);
            }
            return zombie;
        }

        //FALLBACK
        else {
            Zombie zombie = mob.convertTo(EntityType.ZOMBIE, true);

            if (zombie != null) {
                zombie.finalizeSpawn(level, level.getCurrentDifficultyAt(zombie.blockPosition()),
                        MobSpawnType.CONVERSION, null, null);
            }
            return zombie;
        }
    }
}