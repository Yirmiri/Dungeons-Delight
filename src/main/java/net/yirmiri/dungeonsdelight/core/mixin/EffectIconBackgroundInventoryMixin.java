package net.yirmiri.dungeonsdelight.core.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.util.DDUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//The code in this file is owned by Hecco.
//All rights are reserved by the original author.

@Mixin(EffectRenderingInventoryScreen.class)
public class EffectIconBackgroundInventoryMixin {

    @Unique
    private static final ResourceLocation MONSTER_EFFECT_BACKGROUND_TEXTURE = new ResourceLocation(DungeonsDelight.MOD_ID, "textures/gui/sprites/effect/monster_mob_effect.png");

    @Unique
    private static final ResourceLocation INVENTORY_LOCATION = new ResourceLocation("textures/gui/container/inventory.png");

    @Inject(method = "renderBackgrounds", at = @At("HEAD"), cancellable = true)
    private void renderBackgrounds(GuiGraphics p_281540_, int p_282479_, int p_283680_, Iterable<MobEffectInstance> p_282013_, boolean p_283630_, CallbackInfo ci) {
        int i = ((AbstractContainerScreenMixin) this).getY();

        for(MobEffectInstance mobeffectinstance : p_282013_) {
            if (DDUtil.MONSTER_EFFECTS.contains(mobeffectinstance.getEffect())) {
                if (p_283630_) {
                    p_281540_.blit(MONSTER_EFFECT_BACKGROUND_TEXTURE, p_282479_, i, 32, 24, 120, 32);
                } else {
                    p_281540_.blit(MONSTER_EFFECT_BACKGROUND_TEXTURE, p_282479_, i, 0, 24, 32, 32);
                }

                i += p_283680_;
            } else {
                if (p_283630_) {
                    p_281540_.blit(INVENTORY_LOCATION, p_282479_, i, 0, 166, 120, 32);
                } else {
                    p_281540_.blit(INVENTORY_LOCATION, p_282479_, i, 0, 198, 32, 32);
                }

                i += p_283680_;
            }
        }
        ci.cancel();
    }
}
