package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.goal.CleaverAttackGoal;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {
    public ZombieMixin(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void dungeonsdelight$registerGoals(CallbackInfo ci) {
        goalSelector.addGoal(4, new CleaverAttackGoal<>((Zombie) (Object) this, 30, 30, 300));
    }

    @Inject(method = "finalizeSpawn", at = @At("TAIL"))
    private void dungeonsdelight$finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, SpawnGroupData spawnData, CompoundTag compoundTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        Zombie zombie = (Zombie) (Object) this;

        if (!(level instanceof ServerLevel serverLevel)) return;
        if (reason != MobSpawnType.NATURAL) return;
        if (difficulty.getEffectiveDifficulty() < DungeonsDelight.CONFIG.getUndeadJockeyMinRegionalDifficulty())
            return;

        float undeadJockeyChance = DungeonsDelight.CONFIG.getUndeadJockeySpawnChance();

        if (difficulty.getDifficulty() == Difficulty.EASY) {
            undeadJockeyChance =- 0.75F;
        }

        if (difficulty.getDifficulty() == Difficulty.HARD) {
            undeadJockeyChance += 0.75F;
        }

        if (serverLevel.random.nextFloat() >= undeadJockeyChance) return;

        //ZOMBIE
        if (zombie.getType() == EntityType.ZOMBIE) {
            if (random.nextInt(4) == 0) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(DDItems.FLINT_CLEAVER.get()));
            } else {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(DDItems.IRON_CLEAVER.get()));
            }
            zombie.setDropChance(EquipmentSlot.MAINHAND, 0.33F);

            ZombieHorse zombieHorse = EntityType.ZOMBIE_HORSE.create(serverLevel);
            if (zombieHorse == null) return;

            zombieHorse.moveTo(zombie.getX(), zombie.getY(), zombie.getZ(), zombie.getYRot(), zombie.getXRot());
            serverLevel.addFreshEntity(zombieHorse);

            zombie.startRiding(zombieHorse, true);
        }

        //HUSK
        if (zombie.getType() == EntityType.HUSK) {
            if (random.nextInt(3) == 0) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(DDItems.GOLDEN_CLEAVER.get()));
            } else {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(DDItems.IRON_CLEAVER.get()));
            }
            zombie.setDropChance(EquipmentSlot.MAINHAND, 0.33F);

            CamelHuskEntity camelHusk = DDEntities.CAMEL_HUSK.get().create(serverLevel);
            if (camelHusk == null) return;

            camelHusk.moveTo(zombie.getX(), zombie.getY(), zombie.getZ(), zombie.getYRot(), zombie.getXRot());
            serverLevel.addFreshEntity(camelHusk);

            zombie.startRiding(camelHusk, true);

            if (serverLevel.random.nextFloat() < 0.25F) {
                Husk passenger = EntityType.HUSK.create(serverLevel);

                if (passenger != null) {
                    if (random.nextInt() == 0) {
                        passenger.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                    }
                    passenger.setDropChance(EquipmentSlot.MAINHAND, 0.66F);

                    passenger.moveTo(zombie.getX(), zombie.getY(), zombie.getZ(), zombie.getYRot(), zombie.getXRot());
                    serverLevel.addFreshEntity(passenger);
                    passenger.startRiding(camelHusk, true);
                }
            }
        }
    }
}