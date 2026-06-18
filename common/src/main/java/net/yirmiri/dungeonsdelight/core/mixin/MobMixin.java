package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin {

    @Unique
    Mob mob = (Mob) (Object) this;

    @Unique
    private int spiderRefillTime = 0;

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (mob.getType().is(DDTags.EntityT.PRODUCES_SPIDER_EXTRACT) && spiderRefillTime <= 0) {
            ItemStack itemstack = player.getItemInHand(hand);
            if ((mob instanceof Spider spider) && (spider.getLightLevelDependentMagicValue() >= 0.5F) ||
                    (mob instanceof NeutralMob neutralMob && !neutralMob.isAngry()) || mob.getType().is(DDTags.EntityT.HAS_POTENT_SPIDER_EXTRACT)) {
                if (itemstack.is(Items.GLASS_BOTTLE)) {
                    cir.setReturnValue(InteractionResultHolder.sidedSuccess(DDUtil.convertItem(player, SoundEvents.BOTTLE_FILL_DRAGONBREATH,
                            itemstack, new ItemStack(DDItems.SPIDER_EXTRACT.get())), mob.level().isClientSide()).getResult());
                    if (!mob.getType().is(DDTags.EntityT.HAS_POTENT_SPIDER_EXTRACT)) {
                        spiderRefillTime = DungeonsDelight.CONFIG.getSpiderProduceCooldownTicks();
                    }
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void dungeonsdelight$tick(CallbackInfo ci) {
        if (mob.level().isClientSide) return;
        if (mob.getType().is(DDTags.EntityT.PRODUCES_SPIDER_EXTRACT) && !(spiderRefillTime <= 0)
                && !mob.getType().is(DDTags.EntityT.HAS_POTENT_SPIDER_EXTRACT)) {
            this.spiderRefillTime--;
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$addAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (mob.getType().is(DDTags.EntityT.PRODUCES_SPIDER_EXTRACT) && !mob.getType().is(DDTags.EntityT.HAS_POTENT_SPIDER_EXTRACT)) {
            compound.putInt("SpiderRefillTime", this.spiderRefillTime);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void dungeonsdelight$readAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        if (mob.getType().is(DDTags.EntityT.PRODUCES_SPIDER_EXTRACT) && !mob.getType().is(DDTags.EntityT.HAS_POTENT_SPIDER_EXTRACT)) {
            this.spiderRefillTime = compound.getInt("SpiderRefillTime");
        }
    }

    @Inject(method = "isPersistenceRequired", at = @At("HEAD"), cancellable = true)
    private void dungeonsdelight$isPersistenceRequired(CallbackInfoReturnable<Boolean> cir) {
        if (mob instanceof ZombieHorse zombieHorse && !(zombieHorse.isTamed() || zombieHorse.isSaddled() || zombieHorse.hasCustomName())) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void dungeonsdelight$aiStep(CallbackInfo ci) {
//        if (mob instanceof ZombieHorse zombieHorse && zombieHorse.isAlive()) {
//            if (isSunBurnTickGasStation() && (!zombieHorse.isWearingArmor()) || !zombieHorse.isSaddled()) {
//                zombieHorse.setSecondsOnFire(8);
//            } //todo
//        }
    }

    protected boolean isSunBurnTickGasStation() {
        if (mob.level().isDay() && !mob.level().isClientSide) {
            float f = mob.getLightLevelDependentMagicValue();
            BlockPos blockpos = BlockPos.containing(mob.getX(), mob.getEyeY(), mob.getZ());
            boolean flag = mob.isInWaterRainOrBubble() || mob.isInPowderSnow || mob.wasInPowderSnow;
            return f > 0.5F && mob.getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && mob.level().canSeeSky(blockpos);
        }
        return false;
    }
}