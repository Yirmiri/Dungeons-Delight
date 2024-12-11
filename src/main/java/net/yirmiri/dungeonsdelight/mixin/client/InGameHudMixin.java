package net.yirmiri.dungeonsdelight.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.util.DDHeartTypes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow private int ticks;
    @Shadow @Final private Random random;

    @Shadow @Nullable protected abstract PlayerEntity getCameraPlayer();

    //EXUDATION
    private static final Identifier EXUDATION_EMPTY_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_empty");
    private static final Identifier EXUDATION_EMPTY_BLINKING_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_empty_blinking");
    private static final Identifier EXUDATION_HALF_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_half");
    private static final Identifier EXUDATION_FULL_TEXTURE = Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_full");

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

    @ModifyVariable(method = "renderHealthBar", at = @At("HEAD"), ordinal = 6, argsOnly = true)
    private int dungeonsdelight_removeAbsorptionHearts(int j) {
        if (getCameraPlayer() != null && getCameraPlayer().hasStatusEffect(DDEffects.EXUDATION)) {
            return 0;
        }
        return j;
    }

    @Inject(at = @At("HEAD"), method = "renderHealthBar")
    private void dungeonsdelight_renderHealthBar(DrawContext ctx, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        int absorption2 = MathHelper.ceil(player.getAbsorptionAmount());
        boolean hardcore = player.getWorld().getLevelProperties().isHardcore();
        int maxHealthBars = MathHelper.ceil((double) maxHealth / (double) 2.0F);
        int maxAbsorptionBars = MathHelper.ceil((double) absorption2 / (double) 2.0F);
        int maxHp = maxHealthBars * 2;

        if (player.hasStatusEffect(DDEffects.EXUDATION)) {
            for (int lastHealthPoint = maxHealthBars + maxAbsorptionBars - 1; lastHealthPoint >= 0; --lastHealthPoint) {
                int m = lastHealthPoint / 10;
                int n = lastHealthPoint % 10;
                int xPos = x + n * 8;
                int yPos = y - m * lines;

                if (lastHealthPoint < maxHealthBars && lastHealthPoint == regeneratingHeartIndex) {
                    yPos -= 2;
                }
                int q = lastHealthPoint * 2;
                boolean hasAbsorptionHp = lastHealthPoint >= maxHealthBars;
                if (hasAbsorptionHp) {
                    int absorptionHp = q - maxHp;
                    if (absorptionHp < absorption2) {
                        drawExudationHeart(ctx, DDHeartTypes.EXUDATION_CONTAINER, xPos, yPos, hardcore, blinking, false);
                        boolean absorptionHearts = absorptionHp + 1 == absorption2;
                        drawExudationHeart(ctx, DDHeartTypes.EXUDATION, xPos, yPos, hardcore, false, absorptionHearts);
                    }
                }
            }
        }
    }

    private void drawExudationHeart(DrawContext ctx, DDHeartTypes type, int x, int y, boolean hardcore, boolean blinking, boolean half) {
        RenderSystem.enableBlend();
        ctx.drawGuiTexture(type.getTexture(hardcore, half, blinking), x, y, 9, 9);
        RenderSystem.disableBlend();
    }

    @Inject(at = @At("HEAD"), method = "renderFood", cancellable = true)
    private void dungeonsdelight_renderFood(DrawContext ctx, PlayerEntity player, int top, int right, CallbackInfo ci) {
        HungerManager hungerManager = player.getHungerManager();
        int i = hungerManager.getFoodLevel();
        RenderSystem.enableBlend();

        for (int j = 0; j < 10; ++j) {
            int k = top;
            Identifier emptyTexture = null;
            Identifier halfTexture = null;
            Identifier fullTexture = null;

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
            }

            if (player.getHungerManager().getSaturationLevel() <= 0.0F && this.ticks % (i * 3 + 1) == 0) {
                k += this.random.nextInt(3) - 1;
            }

            if (emptyTexture != null) {
                int l = right - j * 8 - 9;
                ctx.drawGuiTexture(emptyTexture, l, k, 9, 9);
                if (j * 2 + 1 < i) {
                    ctx.drawGuiTexture(fullTexture, l, k, 9, 9);
                }

                if (j * 2 + 1 == i) {
                    ctx.drawGuiTexture(halfTexture, l, k, 9, 9);
                }
            }
        }
        RenderSystem.disableBlend();
    }
}
