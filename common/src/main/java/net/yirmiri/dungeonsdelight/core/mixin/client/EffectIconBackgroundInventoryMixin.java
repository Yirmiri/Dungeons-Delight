package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.effect.monster.MonsterEffect;
import net.yirmiri.dungeonsdelight.common.effect.RavenousRushEffect;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//The code in this file is owned by Hecco.
//All rights are reserved by the original author.
//Explicit permission has been granted to Yirmiri for usage of this code in this software.

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectIconBackgroundInventoryMixin {

    @Inject(method = "renderBackgrounds", at = @At("TAIL"))
    private void renderBackgrounds(GuiGraphics graphics, int i1, int i2, Iterable<MobEffectInstance> instances, boolean b, CallbackInfo ci) {
        int i = ((AbstractContainerScreenMixin) this).getY();

        if (DungeonsDelight.CONFIG.getMonsterEffectBackground()) {
            for (MobEffectInstance mobeffectinstance : instances) {
                if (mobeffectinstance.getEffect() instanceof MonsterEffect || mobeffectinstance.getEffect() instanceof RavenousRushEffect) {
                    if (b) {
                        graphics.blit(DDUtil.MONSTER_EFFECT_BG, i1, i, 32, 24, 120, 32);
                    } else {
                        graphics.blit(DDUtil.MONSTER_EFFECT_BG, i1, i, 0, 24, 32, 32);
                    }

                } else {
                    if (b) {
                        graphics.blit(new ResourceLocation("textures/gui/container/inventory.png"), i1, i, 0, 166, 120, 32);
                    } else {
                        graphics.blit(new ResourceLocation("textures/gui/container/inventory.png"), i1, i, 0, 198, 32, 32);
                    }

                }
                i += i2;
            }
        }
    }
}