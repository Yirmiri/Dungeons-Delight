package net.yirmiri.dungeonsdelight.core.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

//  made by ARMURIII
//  All Rights Reserved

@Mixin(Gui.class)
public class EffectIconBackgroundMixin {

    @Shadow
    protected final Minecraft minecraft = Minecraft.getInstance();

    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_TEXTURE = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/effect/monster_mob_effect.png");
    @ModifyArg(method = "renderEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"),index = 0)
    protected ResourceLocation dungeonsdelight$changeBackgroundTexture(ResourceLocation original, @Local MobEffectInstance mobEffectInstance) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get() && DDUtil.MONSTER_EFFECTS.contains(mobEffectInstance.getEffect()))
            return MONSTER_EFFECT_BACKGROUND_TEXTURE;
        return original;
    }

    @ModifyArg(method = "renderEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"),index = 3)
    protected int dungeonsdelight$changeBackgroundAmbientTexture(int original, @Local MobEffectInstance mobEffectInstance) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get() && DDUtil.MONSTER_EFFECTS.contains(mobEffectInstance.getEffect()))
            return mobEffectInstance.isAmbient() ? 24 : 0;
        return original;
    }

    @ModifyArg(method = "renderEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"),index = 4)
    protected int dungeonsdelight$changeBackgroundV(int original, @Local MobEffectInstance mobEffectInstance) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get() && DDUtil.MONSTER_EFFECTS.contains(mobEffectInstance.getEffect()))
            return 0;
        return original;
    }
}
