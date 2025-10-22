package net.yirmiri.dungeonsdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public class DDConfigCommon {
    public static final ModConfigSpec COMMON;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    //MISC
    public static final ModConfigSpec.BooleanValue FORCE_ENABLE_INTEGRATION_FEATURES;
    public static final ModConfigSpec.BooleanValue TRIAL_SPAWNERS_EMIT_GREEN_FLAMES;
    public static final ModConfigSpec.BooleanValue VAULTS_EMIT_GREEN_FLAMES;

    static {
        //CONTENT TOGGLES
        BUILDER.push("Dungeon's Delight Config"); //start of config

        BUILDER.push("Gameplay Configurations").comment("Configuration for features that may impact gameplay in a large way");

        FORCE_ENABLE_INTEGRATION_FEATURES = BUILDER
                .comment("Force enable all mod integration? (even if the corresponding mods are not installed), mostly useful for modpacks wanting to repurpose the items (default: false)")
                .define("forceEnableIntegrationFeatures", false);

        TRIAL_SPAWNERS_EMIT_GREEN_FLAMES = BUILDER
                .comment("Should trial spawner blocks emit green flame particles? (default: true)")
                .define("trialSpawnersEmitGreenFlames", true);

        VAULTS_EMIT_GREEN_FLAMES = BUILDER
                .comment("Should vault blocks emit green flame particles? (default: true)")
                .define("vaultsEmitGreenFlames", true);

        BUILDER.pop();

        COMMON = BUILDER.build(); //end of config
    }
}
