package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.monster.MonsterEffect;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

//The code in this file is owned by Hecco.
//All rights are reserved by the original author.
//Explicit permission has been granted to Yirmiri for usage of this code in this software.

@Mixin(Gui.class)
public class EffectIconBackgroundMixin {
    @Shadow
    private final Minecraft minecraft = Minecraft.getInstance();

    @Shadow
    private int screenWidth;

    @Inject(method = "renderEffects", at = @At("TAIL"), cancellable = true)
    public void dungeonsdelight$renderMonsterEffects(GuiGraphics graphics, CallbackInfo ci) {
        if (DungeonsDelight.CONFIG.getMonsterEffectBackground()) {
            Collection<MobEffectInstance> collection = this.minecraft.player.getActiveEffects();

            if (!collection.isEmpty()) {
                Screen screen = this.minecraft.screen;

                if (screen instanceof EffectRenderingInventoryScreen effectScreen) {
                    if (effectScreen.canSeeEffects()) {
                        ci.cancel();
                    }
                }

                RenderSystem.enableBlend();

                int beneficialCount = 0;
                int harmfulCount = 0;

                MobEffectTextureManager textureManager = this.minecraft.getMobEffectTextures();
                List<Runnable> list = Lists.newArrayListWithExpectedSize(collection.size());

                for (MobEffectInstance effectInstance : Ordering.natural().reverse().sortedCopy(collection)) {
                    MobEffect effect = effectInstance.getEffect();

                    if (effectInstance.showIcon()) {
                        int x = this.screenWidth;
                        int y = 1;

                        if (this.minecraft.isDemo()) {
                            y += 15;
                        }
                        if (effect.isBeneficial()) {
                            ++beneficialCount;
                            x -= 25 * beneficialCount;
                        } else {
                            ++harmfulCount;
                            x -= 25 * harmfulCount;
                            y += 26;
                        }

                        float alpha;

                        if (effect instanceof MonsterEffect) {
                            if (effectInstance.isAmbient()) {
                                alpha = 1.0F;
                                graphics.blit(DDUtil.MONSTER_EFFECT_BG, x, y, 24, 0, 24, 24);
                            } else {
                                graphics.blit(DDUtil.MONSTER_EFFECT_BG, x, y, 0, 0, 24, 24);

                                if (effectInstance.endsWithin(200)) {
                                    int duration = effectInstance.getDuration();
                                    int blink = 10 - duration / 20;

                                    alpha = Mth.clamp((float) duration / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + Mth.cos((float) duration *
                                            (float) Math.PI / 5.0F) * Mth.clamp((float) blink / 10.0F * 0.25F, 0.0F, 0.25F);
                                } else {
                                    alpha = 1.0F;
                                }
                            }
                        } else {
                            if (effectInstance.isAmbient()) {
                                alpha = 1.0F;

                                graphics.blit(AbstractContainerScreen.INVENTORY_LOCATION, x, y, 165, 166, 24, 24);
                            } else {
                                graphics.blit(AbstractContainerScreen.INVENTORY_LOCATION, x, y, 141, 166, 24, 24);

                                if (effectInstance.endsWithin(200)) {
                                    int duration = effectInstance.getDuration();
                                    int blink = 10 - duration / 20;

                                    alpha = Mth.clamp((float) duration / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + Mth.cos((float) duration *
                                            (float) Math.PI / 5.0F) * Mth.clamp((float) blink / 10.0F * 0.25F, 0.0F, 0.25F);
                                } else {
                                    alpha = 1.0F;
                                }
                            }
                        }

                        TextureAtlasSprite sprite = textureManager.get(effect);

                        int finalX = x;
                        int finalY = y;

                        list.add(() -> {
                            graphics.setColor(1.0F, 1.0F, 1.0F, alpha);
                            graphics.blit(finalX + 3, finalY + 3, 0, 18, 18, sprite);
                            graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                        });
                    }
                }
                list.forEach(Runnable::run);
            }
        }
    }
}