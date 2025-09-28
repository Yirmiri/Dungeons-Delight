package net.yirmiri.dungeonsdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public class DDConfigCommon {
    public static final ModConfigSpec COMMON;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    //BALANCE
    public static final ModConfigSpec.BooleanValue FD_STICK_FOODS_GRANT_STRENGTH;
    public static final ModConfigSpec.BooleanValue FD_GLOWING_FOODS_GRANT_PERCEPTION;

    //MISC
    public static final ModConfigSpec.BooleanValue FORCE_ENABLE_COMPAT_ITEMS;
    public static final ModConfigSpec.BooleanValue DISABLE_CONTENT_INTEGRATION;
    public static final ModConfigSpec.BooleanValue TRIAL_SPAWNERS_EMIT_GREEN_FLAMES;
    public static final ModConfigSpec.BooleanValue VAULTS_EMIT_GREEN_FLAMES;

    static {
        //CONTENT TOGGLES
        BUILDER.push("Dungeon's Delight Config"); //start of config

        BUILDER.push("Balance Configuration").comment("Configuration for features that may impact gameplay in a large way"); //start of balance configs

        FD_STICK_FOODS_GRANT_STRENGTH = BUILDER
                .comment("Should Farmer's Delight stick foods grant strength? (default: true)")
                .define("fdStickFoodsGrantStrength", true);

        FD_GLOWING_FOODS_GRANT_PERCEPTION = BUILDER
                .comment("Should Farmer's Delight glowing foods grant perception? (default: true)")
                .define("fdGlowingFoodsGrantPerception", true);

        FORCE_ENABLE_COMPAT_ITEMS = BUILDER
                .comment("Force enable all mod integration? (even if the corresponding mods are not installed), mostly useful for modpacks wanting to repurpose the items (default: false)")
                .define("forceEnableCompatItems", false);

        DISABLE_CONTENT_INTEGRATION = BUILDER
                .comment("Disable all mod integration? (if this is set to false then some mods that are supported will feature new items to collaborate the features of both mods) (default: false)")
                .define("disableContentIntegration", false);

        TRIAL_SPAWNERS_EMIT_GREEN_FLAMES = BUILDER
                .comment("Should trial spawner blocks emit green flame particles? (default: true)")
                .define("trialSpawnersEmitGreenFlames", true);

        VAULTS_EMIT_GREEN_FLAMES = BUILDER
                .comment("Should vault blocks emit green flame particles? (default: true)")
                .define("vaultsEmitGreenFlames", true);

        BUILDER.pop(); //end of balance configs

        COMMON = BUILDER.build(); //end of config
    }
}
