package net.yirmiri.dungeonsdelight.core.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//The code in this file is owned by Hecco.
//All rights are reserved by the original author.
//Explicit permission has been granted to Yirmiri for usage of this code in this software.

//Reverted due to issues arising and restrictions on modification of code

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectIconBackgroundInventoryMixin {

    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_TEXTURE = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/effect/monster_mob_effect.png");

    //@Unique
    //private static final ResourceLocation INVENTORY_LOCATION = new ResourceLocation("textures/gui/container/inventory.png");

    @Inject(method = "renderBackgrounds", at = @At("TAIL"))
    private void renderBackgrounds(GuiGraphics graphics, int i1, int i2, Iterable<MobEffectInstance> instances, boolean b, CallbackInfo ci) {
        int i = ((AbstractContainerScreenMixin) this).getY();

        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get()) {
            for (MobEffectInstance mobeffectinstance : instances) {
                if (DDUtil.MONSTER_EFFECTS.contains(mobeffectinstance.getEffect())) {
                    if (b) {
                        graphics.blit(MONSTER_EFFECT_BACKGROUND_TEXTURE, i1, i, 32, 24, 120, 32);
                    } else {
                        graphics.blit(MONSTER_EFFECT_BACKGROUND_TEXTURE, i1, i, 0, 24, 32, 32);
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
