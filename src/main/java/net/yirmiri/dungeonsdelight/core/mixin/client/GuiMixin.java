package net.yirmiri.dungeonsdelight.core.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.azurune.runiclib.RunicLib;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.init.DDHeartTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEffects;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow
    private int tickCount;
    @Shadow
    @Final
    private RandomSource random;

    @Shadow
    @Nullable
    protected abstract Player getCameraPlayer();

    @Shadow protected abstract void renderTextureOverlay(GuiGraphics guiGraphics, ResourceLocation shaderLocation, float alpha);

    @Shadow @Final private Minecraft minecraft;
    //BURROW GUT
    private static final ResourceLocation FOOD_EMPTY_BURROW_GUT_TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "hud/hunger/burrow_gut_empty");
    private static final ResourceLocation FOOD_HALF_BURROW_GUT_TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "hud/hunger/burrow_gut_half");
    private static final ResourceLocation FOOD_FULL_BURROW_GUT_TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "hud/hunger/burrow_gut_full");
    //VORACITY
    private static final ResourceLocation FOOD_EMPTY_VORACITY_TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "hud/hunger/voracity_empty");
    private static final ResourceLocation FOOD_HALF_VORACITY_TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "hud/hunger/voracity_half");
    private static final ResourceLocation FOOD_FULL_VORACITY_TEXTURE = RunicLib.customid(DungeonsDelight.MOD_ID, "hud/hunger/voracity_full");

    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 6, argsOnly = true)
    private int dungeonsdelight$removeAbsorptionHearts(int j) {
        if (getCameraPlayer() != null && getCameraPlayer().hasEffect(DDEffects.EXUDATION)) {
            return 0;
        }
        return j;
    }

    @Inject(at = @At("HEAD"), method = "renderHearts")
    private void dungeonsdelight$renderHealthBar(GuiGraphics ctx, Player player, int x, int y, int lines,
                                                 int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption,
                                                 boolean blinking, CallbackInfo ci) {
        int absorption2 = Mth.ceil(player.getAbsorptionAmount());
        boolean hardcore = player.level().getLevelData().isHardcore();
        int maxHealthBars = Mth.ceil((double) maxHealth / (double) 2.0F);
        int maxAbsorptionBars = Mth.ceil((double) absorption2 / (double) 2.0F);
        int maxHp = maxHealthBars * 2;

        if (player.hasEffect(DDEffects.EXUDATION)) {
            for (int lastHealthPoint = maxHealthBars + maxAbsorptionBars - 1; lastHealthPoint >= 0; --lastHealthPoint) {
                int m = lastHealthPoint / 10;
                int n = lastHealthPoint % 10;
                int xPos = x + n * 8;
                int yPos = y - m * lines;

                if (lastHealthPoint < maxHealthBars && lastHealthPoint == regeneratingHeartIndex) {
                    yPos -= 2;
                }

                //heart movement
                yPos += random.nextInt(2);

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

    private void drawExudationHeart(GuiGraphics ctx, DDHeartTypes type, int x, int y, boolean hardcore,
                                    boolean blinking, boolean half) {
        RenderSystem.enableBlend();
        ctx.blitSprite(type.getTexture(hardcore, half, blinking), x, y, 9, 9);
        RenderSystem.disableBlend();
    }

    @Inject(at = @At("HEAD"), method = "renderFood", cancellable = true)
    private void dungeonsdelight$renderFood(GuiGraphics ctx, Player player, int top, int right, CallbackInfo ci) {
        FoodData hungerManager = player.getFoodData();
        int i = hungerManager.getFoodLevel();
        RenderSystem.enableBlend();

        for (int j = 0; j < 10; ++j) {
            int k = top;
            ResourceLocation emptyTexture = null;
            ResourceLocation halfTexture = null;
            ResourceLocation fullTexture = null;

            if (player.hasEffect(DDEffects.BURROW_GUT)) {
                emptyTexture = FOOD_EMPTY_BURROW_GUT_TEXTURE;
                halfTexture = FOOD_HALF_BURROW_GUT_TEXTURE;
                fullTexture = FOOD_FULL_BURROW_GUT_TEXTURE;

                ci.cancel();
            } else if (player.hasEffect(DDEffects.VORACITY)) {
                emptyTexture = FOOD_EMPTY_VORACITY_TEXTURE;
                halfTexture = FOOD_HALF_VORACITY_TEXTURE;
                fullTexture = FOOD_FULL_VORACITY_TEXTURE;

                ci.cancel();
            }

            if (player.getFoodData().getSaturationLevel() <= 0.0F && this.tickCount % (i * 3 + 1) == 0) {
                k += this.random.nextInt(3) - 1;
            }

            if (emptyTexture != null) {
                int l = right - j * 8 - 9;
                ctx.blitSprite(emptyTexture, l, k, 9, 9);
                if (j * 2 + 1 < i) {
                    ctx.blitSprite(fullTexture, l, k, 9, 9);
                }

                if (j * 2 + 1 == i) {
                    ctx.blitSprite(halfTexture, l, k, 9, 9);
                }
            }
        }
        RenderSystem.disableBlend();
    }

    @Inject(at = @At("TAIL"), method = "renderCameraOverlays")
    private void dungeonsdelight$renderCameraOverlays(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ItemStack itemstack = this.minecraft.player.getInventory().getArmor(3);
        if (this.minecraft.options.getCameraType().isFirstPerson()) {
            if (itemstack.is(DDBlocks.CARVED_ROTGOURD.get().asItem())) {
                this.renderTextureOverlay(guiGraphics, ResourceLocation.withDefaultNamespace("textures/misc/pumpkinblur.png"), 1.0F);
            }
        }
    }
}
