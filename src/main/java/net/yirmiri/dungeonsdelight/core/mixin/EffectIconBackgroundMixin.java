package net.yirmiri.dungeonsdelight.core.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
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
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
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

@Mixin(Gui.class)
public class EffectIconBackgroundMixin {

    @Shadow
    protected final Minecraft minecraft = Minecraft.getInstance();
    @Shadow
    protected int screenWidth;

    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_TEXTURE = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/effect/monster_mob_effect.png");

    @Inject(method = "renderEffects", at = @At("TAIL"), cancellable = true)
    public void dungeonsdelight$renderMonsterEffects(GuiGraphics graphics, CallbackInfo ci) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get()) {
            Collection<MobEffectInstance> collection = this.minecraft.player.getActiveEffects();
            if (!collection.isEmpty()) {
                Screen $$4 = this.minecraft.screen;
                if ($$4 instanceof EffectRenderingInventoryScreen) {
                    EffectRenderingInventoryScreen effectrenderinginventoryscreen = (EffectRenderingInventoryScreen) $$4;
                    if (effectrenderinginventoryscreen.canSeeEffects()) {
                        ci.cancel();
                    }
                }

                RenderSystem.enableBlend();
                int j1 = 0;
                int k1 = 0;
                MobEffectTextureManager mobeffecttexturemanager = this.minecraft.getMobEffectTextures();
                List<Runnable> list = Lists.newArrayListWithExpectedSize(collection.size());

                for (MobEffectInstance mobeffectinstance : Ordering.natural().reverse().sortedCopy(collection)) {
                    MobEffect mobeffect = mobeffectinstance.getEffect();
                    IClientMobEffectExtensions renderer = IClientMobEffectExtensions.of(mobeffectinstance);
                    if (renderer.isVisibleInGui(mobeffectinstance) && mobeffectinstance.showIcon()) {
                        int i = this.screenWidth;
                        int j = 1;
                        if (this.minecraft.isDemo()) {
                            j += 15;
                        }

                        if (mobeffect.isBeneficial()) {
                            ++j1;
                            i -= 25 * j1;
                        } else {
                            ++k1;
                            i -= 25 * k1;
                            j += 26;
                        }

                        float f;
                        if (DDUtil.MONSTER_EFFECTS.contains(mobeffectinstance.getEffect())) {
                            if (mobeffectinstance.isAmbient()) {
                                f = 1.0F;
                                graphics.blit(MONSTER_EFFECT_BACKGROUND_TEXTURE, i, j, 24, 0, 24, 24);
                            } else {
                                graphics.blit(MONSTER_EFFECT_BACKGROUND_TEXTURE, i, j, 0, 0, 24, 24);
                                if (mobeffectinstance.endsWithin(200)) {
                                    int k = mobeffectinstance.getDuration();
                                    int l = 10 - k / 20;
                                    f = Mth.clamp((float) k / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + Mth.cos((float) k * (float) Math.PI / 5.0F) * Mth.clamp((float) l / 10.0F * 0.25F, 0.0F, 0.25F);
                                } else {
                                    f = 1.0F;
                                }
                            }
                        } else {
                            if (mobeffectinstance.isAmbient()) {
                                f = 1.0F;
                                graphics.blit(AbstractContainerScreen.INVENTORY_LOCATION, i, j, 165, 166, 24, 24);
                            } else {
                                graphics.blit(AbstractContainerScreen.INVENTORY_LOCATION, i, j, 141, 166, 24, 24);
                                if (mobeffectinstance.endsWithin(200)) {
                                    int k = mobeffectinstance.getDuration();
                                    int l = 10 - k / 20;
                                    f = Mth.clamp((float) k / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + Mth.cos((float) k * (float) Math.PI / 5.0F) * Mth.clamp((float) l / 10.0F * 0.25F, 0.0F, 0.25F);
                                } else {
                                    f = 1.0F;
                                }
                            }
                        }
                        if (!renderer.renderGuiIcon(mobeffectinstance, (Gui) (Object) this, graphics, i, j, 0.0F, f)) {
                            TextureAtlasSprite textureatlassprite = mobeffecttexturemanager.get(mobeffect);
                            int finalI = i;
                            int finalJ = j;
                            list.add(() -> {
                                graphics.setColor(1.0F, 1.0F, 1.0F, f);
                                graphics.blit(finalI + 3, finalJ + 3, 0, 18, 18, textureatlassprite);
                                graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                            });
                        }
                    }
                }

                list.forEach(Runnable::run);
            }
        }
    }
}
