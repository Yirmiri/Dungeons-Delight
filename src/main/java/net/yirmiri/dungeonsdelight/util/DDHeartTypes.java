package net.yirmiri.dungeonsdelight.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

@Environment(EnvType.CLIENT)
public enum DDHeartTypes {
    EXUDATION_CONTAINER(
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking"),
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking"),
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking"),
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty"),
            Identifier.of(DungeonsDelight.MOD_ID,"hud/heart/exudation_empty_blinking")),

    EXUDATION(
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_full"), 
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_full_blinking"),
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_half"),
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_half_blinking"),
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_full"),
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_full_blinking"),
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_half"),
            Identifier.of(DungeonsDelight.MOD_ID, "hud/heart/exudation_hardcore_half_blinking"));

    private final Identifier fullTexture;
    private final Identifier fullBlinkingTexture;
    private final Identifier halfTexture;
    private final Identifier halfBlinkingTexture;
    private final Identifier hardcoreFullTexture;
    private final Identifier hardcoreFullBlinkingTexture;
    private final Identifier hardcoreHalfTexture;
    private final Identifier hardcoreHalfBlinkingTexture;

    DDHeartTypes(final Identifier fullTexture, final Identifier fullBlinkingTexture, final Identifier halfTexture, final Identifier halfBlinkingTexture, final Identifier hardcoreFullTexture, final Identifier hardcoreFullBlinkingTexture, final Identifier hardcoreHalfTexture, final Identifier hardcoreHalfBlinkingTexture) {
        this.fullTexture = fullTexture;
        this.fullBlinkingTexture = fullBlinkingTexture;
        this.halfTexture = halfTexture;
        this.halfBlinkingTexture = halfBlinkingTexture;
        this.hardcoreFullTexture = hardcoreFullTexture;
        this.hardcoreFullBlinkingTexture = hardcoreFullBlinkingTexture;
        this.hardcoreHalfTexture = hardcoreHalfTexture;
        this.hardcoreHalfBlinkingTexture = hardcoreHalfBlinkingTexture;
    }

    public Identifier getTexture(boolean hardcore, boolean half, boolean blinking) {
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
