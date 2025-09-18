package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.List;

//The code in this file is owned by Hecco.
//All rights are reserved by the original author.
//Explicit permission has been granted to Yirmiri for usage of this code in this software.

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectIconBackgroundInventoryMixin {
    @Shadow private static final ResourceLocation EFFECT_BACKGROUND_LARGE_SPRITE = ResourceLocation.withDefaultNamespace("container/inventory/effect_background_large");
    @Shadow private static final ResourceLocation EFFECT_BACKGROUND_SMALL_SPRITE = ResourceLocation.withDefaultNamespace("container/inventory/effect_background_small");
    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_SMALL_TEXTURE = RunicLib.customid(
            DungeonsDelight.MOD_ID, "container/inventory/monster_effect_background_small");
    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_LARGE_TEXTURE = RunicLib.customid(
            DungeonsDelight.MOD_ID, "container/inventory/monster_effect_background_large");

    @ModifyArg(method = "renderBackgrounds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"), index = 0)
    private ResourceLocation dungeonsdelight$renderBackgrounds(ResourceLocation sprite, @Local MobEffectInstance effect, @Local(argsOnly = true) Iterable<MobEffectInstance> effects) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get()) {
            List<Holder<MobEffect>> effects2 = new ArrayList<>();
            for (MobEffectInstance instance : effects) {
                effects2.add(instance.getEffect());
            }
            if (effect.getEffect().is(DDTags.EffectT.MONSTER_EFFECT)) {
                if (sprite.equals(EFFECT_BACKGROUND_LARGE_SPRITE)) {
                    return MONSTER_EFFECT_BACKGROUND_LARGE_TEXTURE;
                }
                if (sprite.equals(EFFECT_BACKGROUND_SMALL_SPRITE)) {
                    return MONSTER_EFFECT_BACKGROUND_SMALL_TEXTURE;
                }
            }
        }
        return sprite;
    }
}
