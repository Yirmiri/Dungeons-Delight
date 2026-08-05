package net.yirmiri.dungeonsdelight.core.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDDamageTypes {
    public static final ResourceKey<DamageType> CLEAVER = register("cleaver");
    public static final ResourceKey<DamageType> SERRATED = register("serrated");
    public static final ResourceKey<DamageType> RAW_CREEPER = register("raw_creeper");
    public static final ResourceKey<DamageType> EXUDATION_BLAST = register("exudation_blast");
    public static final ResourceKey<DamageType> TRAMPLED = register("trampled");
    public static final ResourceKey<DamageType> HORSE_TRAMPLED = register("horse_trampled");
    public static final ResourceKey<DamageType> DONKEY_TRAMPLED = register("donkey_trampled");
    public static final ResourceKey<DamageType> ECHO_BLAST = register("echo_blast");
    public static final ResourceKey<DamageType> ANCIENT_EGG = register("ancient_egg");
    public static final ResourceKey<DamageType> DUNGEON_STOVE_BURN = register("dungeon_stove_burn");
    public static final ResourceKey<DamageType> VEXING_FANGS = register("vexing_fangs");
    public static final ResourceKey<DamageType> RANCID_REDUCTION = register("rancid_reduction");
    public static final ResourceKey<DamageType> SPIKE_TRAP = register("spike_trap");
    public static final ResourceKey<DamageType> IN_LIVING_FIRE = register("in_living_fire");

    private static ResourceKey<DamageType> register(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(DungeonsDelight.MOD_ID, id));
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type));
    }
}