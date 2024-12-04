package net.yirmiri.dungeonsdelight.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow private int ticks;
    @Shadow @Final private Random random;

    //VANILLA
    private static final Identifier FOOD_EMPTY_TEXTURE = Identifier.ofVanilla("hud/food_empty");
    private static final Identifier FOOD_HALF_TEXTURE = Identifier.ofVanilla("hud/food_half");
    private static final Identifier FOOD_FULL_TEXTURE = Identifier.ofVanilla("hud/food_full");

    //BURROW GUT
    private static final Identifier FOOD_EMPTY_BURROW_GUT_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID, "hud/burrow_gut_empty");
    private static final Identifier FOOD_HALF_BURROW_GUT_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/burrow_gut_half");
    private static final Identifier FOOD_FULL_BURROW_GUT_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/burrow_gut_full");

    private static final Identifier FOOD_HALF_BURROW_GUT_HUNGER_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/burrow_gut_hunger_half");
    private static final Identifier FOOD_FULL_BURROW_GUT_HUNGER_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/burrow_gut_hunger_full");

    //VORACITY
    private static final Identifier FOOD_EMPTY_VORACITY_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID, "hud/voracity_empty");
    private static final Identifier FOOD_HALF_VORACITY_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/voracity_half");
    private static final Identifier FOOD_FULL_VORACITY_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/voracity_full");

    private static final Identifier FOOD_HALF_VORACITY_HUNGER_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/voracity_hunger_half");
    private static final Identifier FOOD_FULL_VORACITY_HUNGER_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID,"hud/voracity_hunger_full");

    @Inject(at = @At("HEAD"), method = "renderFood", cancellable = true)
    private void dungeonsdelight_renderFood(DrawContext ctx, PlayerEntity player, int top, int right, CallbackInfo ci) {
        HungerManager hungerManager = player.getHungerManager();
        int i = hungerManager.getFoodLevel();
        RenderSystem.enableBlend();
//TODO: SIMPLIFY && remove old hunger
        for (int j = 0; j < 10; ++j) {
            int k = top;
            Identifier emptyTexture;
            Identifier halfTexture;
            Identifier fullTexture;

            if (player.hasStatusEffect(DDEffects.BURROW_GUT) && !player.hasStatusEffect(StatusEffects.HUNGER)) {
                emptyTexture = FOOD_EMPTY_BURROW_GUT_TEXTURE;
                halfTexture = FOOD_HALF_BURROW_GUT_TEXTURE;
                fullTexture = FOOD_FULL_BURROW_GUT_TEXTURE;

                ci.cancel();
            } else if (player.hasStatusEffect(DDEffects.BURROW_GUT) && player.hasStatusEffect(StatusEffects.HUNGER)) {
                emptyTexture = FOOD_EMPTY_BURROW_GUT_TEXTURE;
                halfTexture = FOOD_HALF_BURROW_GUT_HUNGER_TEXTURE;
                fullTexture = FOOD_FULL_BURROW_GUT_HUNGER_TEXTURE;

                ci.cancel();
            } else if (player.hasStatusEffect(DDEffects.VORACITY) && !player.hasStatusEffect(StatusEffects.HUNGER)) {
                emptyTexture = FOOD_EMPTY_VORACITY_TEXTURE;
                halfTexture = FOOD_HALF_VORACITY_TEXTURE;
                fullTexture = FOOD_FULL_VORACITY_TEXTURE;

                ci.cancel();
            } else if (player.hasStatusEffect(DDEffects.VORACITY) && player.hasStatusEffect(StatusEffects.HUNGER)) {
                emptyTexture = FOOD_EMPTY_VORACITY_TEXTURE;
                halfTexture = FOOD_HALF_VORACITY_HUNGER_TEXTURE;
                fullTexture = FOOD_FULL_VORACITY_HUNGER_TEXTURE;

                ci.cancel();
            } else {
                emptyTexture = FOOD_EMPTY_TEXTURE;
                halfTexture = FOOD_HALF_TEXTURE;
                fullTexture = FOOD_FULL_TEXTURE;
            }

            if (player.getHungerManager().getSaturationLevel() <= 0.0F && this.ticks % (i * 3 + 1) == 0) {
                k += this.random.nextInt(3) - 1;
            }

            int l = right - j * 8 - 9;
            ctx.drawGuiTexture(emptyTexture, l, k, 9, 9);
            if (j * 2 + 1 < i) {
                ctx.drawGuiTexture(fullTexture, l, k, 9, 9);
            }

            if (j * 2 + 1 == i) {
                ctx.drawGuiTexture(halfTexture, l, k, 9, 9);
            }
        }
        RenderSystem.disableBlend();
    }
}
