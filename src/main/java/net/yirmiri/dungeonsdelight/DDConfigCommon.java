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
                .comment("Enables all compat items even if their mods aren't installed, mostly useful for modpacks wanting to repurpose the items (default: false)")
                .define("forceEnableCompatItems", false);

        BUILDER.pop(); //end of balance configs

        COMMON = BUILDER.build(); //end of config
    }
}
