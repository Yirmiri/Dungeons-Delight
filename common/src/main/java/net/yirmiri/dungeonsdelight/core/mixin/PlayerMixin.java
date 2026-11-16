package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDAttributes;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.core.registry.DDStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Unique Player player = (Player) (Object) this;

    @Inject(method = "createAttributes", at = @At("TAIL"))
    private static void dungeonsdelight$createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue()
                .add(DDAttributes.THROWING_RANGE.get())
        ;
    }

    @Inject(at = @At("HEAD"), method = "attack")
    private void dungeonsdelight$attack(Entity target, CallbackInfo ci) {
        ItemStack mainhand = player.getMainHandItem();
        if (mainhand.is(DDItems.AMETHYST_ROCK_CANDY.get())) {
            Item tryme = null;

            if (!player.getCooldowns().isOnCooldown(DDItems.AMETHYST_ROCK_CANDY.get())) {
                if (target instanceof Silverfish silverfish) {
                    tryme = DDItems.CANDIED_SILVERFISH_SUCKER.get();
                    player.awardStat(Stats.ITEM_USED.get(mainhand.getItem()));

                    silverfish.playSound(SoundEvents.SILVERFISH_DEATH, 1.0F, 1.0F);
                    silverfish.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
                    silverfish.remove(Entity.RemovalReason.DISCARDED);
                }

                if (target instanceof Endermite endermite) {
                    tryme = DDItems.CANDIED_ENDERMITE_SUCKER.get();
                    player.awardStat(Stats.ITEM_USED.get(mainhand.getItem()));

                    endermite.playSound(SoundEvents.ENDERMITE_DEATH, 1.0F, 1.0F);
                    endermite.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
                    endermite.remove(Entity.RemovalReason.DISCARDED);
                }

                if (target instanceof Vex vex) {
                    tryme = DDItems.CANDIED_VEX_SUCKER.get();
                    player.awardStat(Stats.ITEM_USED.get(mainhand.getItem()));

                    vex.playSound(SoundEvents.VEX_DEATH, 1.0F, 1.0F);
                    vex.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1.0F, 1.0F);
                    vex.remove(Entity.RemovalReason.DISCARDED);
                }

                if (tryme != null) {
                    if (!player.isCreative()) {
                        mainhand.shrink(1);
                        player.getCooldowns().addCooldown(DDItems.AMETHYST_ROCK_CANDY.get(), DungeonsDelight.CONFIG.rockCandyPickupCooldownTicks.getValue());
                        player.awardStat(DDStats.MOBS_ENCASED_WITH_ROCK_CANDY.get());
                    }

                    if (mainhand.isEmpty()) player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(tryme));
                    else if (!player.getInventory().add(new ItemStack(tryme))) player.drop(new ItemStack(tryme), false);
                }
            }
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"))
    private void dungeonsdelight$hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (player.hasEffect(DDEffects.HOMEWARD.get()) && !source.is(DDTags.DamageT.KEEPS_HOMEWARD)) {
            MobEffectInstance homewardEffect = player.getEffect(DDEffects.HOMEWARD.get());

            if (homewardEffect.getAmplifier() == 0) {
                player.playSound(SoundEvents.GLASS_BREAK, 0.8F, -1.0F); //todo arty sound
                player.removeEffect(DDEffects.HOMEWARD.get());
            } else {
                player.playSound(SoundEvents.GLASS_BREAK, 0.8F, -1.0F); //todo arty sound
                player.removeEffect(DDEffects.HOMEWARD.get());
                player.addEffect(new MobEffectInstance(DDEffects.HOMEWARD.get(), homewardEffect.getDuration(), homewardEffect.getAmplifier() - 1));
            }
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V"), method = "aiStep", cancellable = true)
    private void dungeonsdelight$canFoodHeal(CallbackInfo ci) {
        if (player.hasEffect(DDEffects.TENACITY.get())) {
            ci.cancel();
        }
    }
}
