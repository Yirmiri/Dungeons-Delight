package net.yirmiri.dungeonsdelight.core.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.yirmiri.dungeonsdelight.DDConfigClient;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

//The code in this file is owned by Hecco.
//All rights are reserved by the original author.

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectIconBackgroundInventoryMixin {

    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_TEXTURE = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/effect/monster_mob_effect.png");

    //@Unique
    //private static final ResourceLocation INVENTORY_LOCATION = new ResourceLocation("textures/gui/container/inventory.png");

    @ModifyArg(method = "renderBackgrounds",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"),index = 0)
    private ResourceLocation dungeonsdelight$changeBackgroundTexture(ResourceLocation original, @Local MobEffectInstance mobEffectInstance) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get() && DDUtil.MONSTER_EFFECTS.contains(mobEffectInstance.getEffect())) {
            return MONSTER_EFFECT_BACKGROUND_TEXTURE;
        }
        return original;
    }
    @ModifyArg(method = "renderBackgrounds",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"),index = 3)
    private int dungeonsdelight$changeBackgroundWideTexture(int original, @Local(argsOnly = true) boolean b, @Local MobEffectInstance mobEffectInstance) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get() && DDUtil.MONSTER_EFFECTS.contains(mobEffectInstance.getEffect())) {
            return b ? 32 : 0;
        }
        return original;
    }
    @ModifyArg(method = "renderBackgrounds",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"),index = 4)
    private int dungeonsdelight$changeBackgroundTextureV(int original, @Local(argsOnly = true) boolean b, @Local MobEffectInstance mobEffectInstance) {
        if (DDConfigClient.MONSTER_EFFECT_BACKGROUND.get() && DDUtil.MONSTER_EFFECTS.contains(mobEffectInstance.getEffect())) {
            return 24;
        }
        return original;
    }


    /*@Inject(method = "renderBackgrounds", at = @At("TAIL"))
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
    }*/
}
