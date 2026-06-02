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

    private static ResourceKey<DamageType> register(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(DungeonsDelight.MOD_ID, id));
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type));
    }
}