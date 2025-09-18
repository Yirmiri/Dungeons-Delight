package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.resources.ResourceLocation;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public enum DDHeartTypes {
    EXUDATION_CONTAINER(
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking"),
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking"),
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking"),
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            RunicLib.customid(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking")),

    EXUDATION(
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_full"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_full_blinking"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_half"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_half_blinking"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_full"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_full_blinking"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_half"),
            RunicLib.customid(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_half_blinking"));

    private final ResourceLocation fullTexture;
    private final ResourceLocation fullBlinkingTexture;
    private final ResourceLocation halfTexture;
    private final ResourceLocation halfBlinkingTexture;
    private final ResourceLocation hardcoreFullTexture;
    private final ResourceLocation hardcoreFullBlinkingTexture;
    private final ResourceLocation hardcoreHalfTexture;
    private final ResourceLocation hardcoreHalfBlinkingTexture;

    DDHeartTypes(final ResourceLocation fullTexture,
                 final ResourceLocation fullBlinkingTexture,
                 final ResourceLocation halfTexture,
                 final ResourceLocation halfBlinkingTexture,
                 final ResourceLocation hardcoreFullTexture,
                 final ResourceLocation hardcoreFullBlinkingTexture,
                 final ResourceLocation hardcoreHalfTexture,
                 final ResourceLocation hardcoreHalfBlinkingTexture) {
        this.fullTexture = fullTexture;
        this.fullBlinkingTexture = fullBlinkingTexture;
        this.halfTexture = halfTexture;
        this.halfBlinkingTexture = halfBlinkingTexture;
        this.hardcoreFullTexture = hardcoreFullTexture;
        this.hardcoreFullBlinkingTexture = hardcoreFullBlinkingTexture;
        this.hardcoreHalfTexture = hardcoreHalfTexture;
        this.hardcoreHalfBlinkingTexture = hardcoreHalfBlinkingTexture;
    }

    public ResourceLocation getTexture(boolean hardcore, boolean half, boolean blinking) {
        if (!hardcore) {
            if (half) {
                return blinking ? this.halfBlinkingTexture : this.halfTexture;
            } else {
                return blinking ? this.fullBlinkingTexture : this.fullTexture;
            }
        } else if (half) {
            return blinking ? this.hardcoreHalfBlinkingTexture : this.hardcoreHalfTexture;
        } else {
            return blinking ? this.hardcoreFullBlinkingTexture : this.hardcoreFullTexture;
        }
    }
}
