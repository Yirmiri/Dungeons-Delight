package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import net.yirmiri.dungeonsdelight.core.init.DDTags;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//The code in this file is owned by Hecco.
//All rights are reserved by the original author.
//Explicit permission has been granted to Yirmiri for usage of this code in this software.

@Mixin(Gui.class)
public class EffectIconBackgroundMixin {

    @Shadow @Final
    private Minecraft minecraft;
    @Shadow @Final private static ResourceLocation EFFECT_BACKGROUND_AMBIENT_SPRITE;
    @Shadow @Final private static ResourceLocation EFFECT_BACKGROUND_SPRITE;
    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_TEXTURE = RunicLib.customid(
            DungeonsDelight.MOD_ID, "hud/monster_effect_background.png");
    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_AMBIENT_TEXTURE = RunicLib.customid(
            DungeonsDelight.MOD_ID, "hud/monster_effect_background_ambient.png");

    @ModifyArg(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"), index = 0)
    private ResourceLocation dungeonsdelight$renderEffects(ResourceLocation sprite, @Local MobEffectInstance effect) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get()) {
            Collection<MobEffectInstance> collection = this.minecraft.player.getActiveEffects();
            if (//collection.stream().map(MobEffectInstance::getEffect).collect(Collectors.toSet()).contains(
                    effect.getEffect().is(DDTags.EffectT.MONSTER_EFFECT)) {
                if (sprite.equals(EFFECT_BACKGROUND_AMBIENT_SPRITE)) {
                    return MONSTER_EFFECT_BACKGROUND_AMBIENT_TEXTURE;
                }
                if (sprite.equals(EFFECT_BACKGROUND_SPRITE)) {
                    return MONSTER_EFFECT_BACKGROUND_TEXTURE;
                }
            }
        }
        return sprite;
    }
}
