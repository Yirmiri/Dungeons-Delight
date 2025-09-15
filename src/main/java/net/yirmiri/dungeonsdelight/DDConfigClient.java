package net.yirmiri.dungeonsdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public class DDConfigClient {
    public static final ModConfigSpec CLIENT;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    //CLIENT
    public static final ModConfigSpec.BooleanValue SPAWNERS_EMIT_GREEN_FLAMES;
    public static final ModConfigSpec.BooleanValue MONSTER_EFFECT_BACKGROUND;
    public static final ModConfigSpec.BooleanValue VORACITY_OVERLAY;
    public static final ModConfigSpec.BooleanValue VORACITY_TRANSPARENCY;
    public static final ModConfigSpec.BooleanValue RAVENOUS_RUSH_OVERLAY;

    static {
        //CLIENT TOGGLES
        BUILDER.push("Dungeon's Delight Client Config"); //start of config

        BUILDER.push("Client Configuration").comment("Client Configuration Toggles"); //start of client configs

        MONSTER_EFFECT_BACKGROUND = BUILDER
                .comment("Should monster effects have special background textures? (default: true)")
                .define("monsterEffectBackground", true);

        SPAWNERS_EMIT_GREEN_FLAMES = BUILDER
                .comment("Should spawner blocks emit green flame particles? (default: true)")
                .define("spawnersEmitGreenFlames", true);

        VORACITY_OVERLAY = BUILDER
                .comment("Should the Voracity status effect overlay a texture? (default: true)")
                .define("voracityOverlay", true);

        VORACITY_TRANSPARENCY = BUILDER
                .comment("Should the Voracity overlay be half transparent for easier visibility? (default: false)")
                .define("voracityTransparency", false);

        RAVENOUS_RUSH_OVERLAY = BUILDER
                .comment("Should the Ravenous Rush status effect overlay a texture? (default: true)")
                .define("ravenousRushOverlay", true);

        BUILDER.pop(); //end of balance configs

        CLIENT = BUILDER.build(); //end of config
    }
}
