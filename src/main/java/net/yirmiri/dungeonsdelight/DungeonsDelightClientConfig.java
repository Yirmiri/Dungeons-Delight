package net.yirmiri.dungeonsdelight;

import net.minecraftforge.common.ForgeConfigSpec;

public class DungeonsDelightClientConfig {
    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    //CLIENT
    public static final ForgeConfigSpec.BooleanValue SPAWNERS_EMIT_GREEN_FLAMES;

    static {
        //CLIENT TOGGLES
        BUILDER.push("Dungeon's Delight Client Config"); //start of config

        BUILDER.push("Client Configuration").comment("Client Configuration Toggles"); //start of client configs

        SPAWNERS_EMIT_GREEN_FLAMES = BUILDER
                .comment("Should spawner blocks emit green flame particles? (default: true)")
                .define("spawnersEmitGreenFlames", true);

        BUILDER.pop(); //end of balance configs

        CLIENT = BUILDER.build(); //end of config
    }
}
