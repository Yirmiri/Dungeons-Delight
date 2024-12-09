package net.yirmiri.dungeonsdelight.util;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDDamageTypes {
    public static final RegistryKey<DamageType> DUNGEON_STOVE_HEAT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(DungeonsDelight.MOD_ID, "dungeon_stove_heat"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
