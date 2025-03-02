package net.yirmiri.dungeonsdelight;

import net.minecraftforge.common.ForgeConfigSpec;

public class DungeonsDelightConfig {
    public static final ForgeConfigSpec COMMON;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    //BALANCE
    public static final ForgeConfigSpec.BooleanValue FD_STICK_FOODS_GRANT_STRENGTH;
    public static final ForgeConfigSpec.BooleanValue FD_GLOWING_FOODS_GRANT_PERCEPTION;

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

        BUILDER.pop(); //end of balance configs

        COMMON = BUILDER.build(); //end of config
    }
}
