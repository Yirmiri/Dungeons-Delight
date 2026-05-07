package net.yirmiri.dungeonsdelight.core.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDDamageTypes {
    public static final ResourceKey<DamageType> CLEAVER = register("cleaver");
    public static final ResourceKey<DamageType> SERRATED = register("serrated");

    private static ResourceKey<DamageType> register(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(DungeonsDelight.MOD_ID, id));
    }
}