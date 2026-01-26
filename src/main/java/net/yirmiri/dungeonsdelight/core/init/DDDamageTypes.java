package net.yirmiri.dungeonsdelight.core.init;

import net.azurune.runiclib.RunicLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDDamageTypes {
    public static final ResourceKey<DamageType> DUNGEON_STOVE_BURN = register("dungeon_stove_burn");
    public static final ResourceKey<DamageType> SKULL_HEART_BLAST = register("skull_heart_blast");
    public static final ResourceKey<DamageType> ANCIENT_EGG = register("ancient_egg");
    public static final ResourceKey<DamageType> CLEAVER = register("cleaver");
    public static final ResourceKey<DamageType> SERRATED = register("serrated");
    public static final ResourceKey<DamageType> BLOODY_MARY = register("bloody_mary");
    public static final ResourceKey<DamageType> SHATTER = register("shatter");
    public static final ResourceKey<DamageType> RAW_CREEPER = register("raw_creeper");
    public static final ResourceKey<DamageType> ECHO_BLAST = register("echo_blast");
    public static final ResourceKey<DamageType> LIVING_ESSENCE = register("living_essence");

    private static ResourceKey<DamageType> register(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, RunicLib.customid(DungeonsDelight.MOD_ID, id));
    }
}
