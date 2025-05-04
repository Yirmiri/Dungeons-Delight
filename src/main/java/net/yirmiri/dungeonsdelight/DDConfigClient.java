package net.yirmiri.dungeonsdelight;

import net.minecraftforge.common.ForgeConfigSpec;

public class DDConfigClient {
    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    //CLIENT
    public static final ForgeConfigSpec.BooleanValue SPAWNERS_EMIT_GREEN_FLAMES;
    public static final ForgeConfigSpec.BooleanValue VORACITY_OVERLAY;
    public static final ForgeConfigSpec.BooleanValue VORACITY_TRANSPARENCY;
    public static final ForgeConfigSpec.BooleanValue RAVENOUS_RUSH_OVERLAY;

    static {
        //CLIENT TOGGLES
        BUILDER.push("Dungeon's Delight Client Config"); //start of config

        BUILDER.push("Client Configuration").comment("Client Configuration Toggles"); //start of client configs

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
