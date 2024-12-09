package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yirmiri.dungeonsdelight.DungeonsDelight;


public class DDSounds {
    //MISC
    public static final SoundEvent DUNGEON_STOVE_SOUL = registerVanillaID("particle.soul_escape");

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(DungeonsDelight.MOD_ID, id), SoundEvent.of(Identifier.of(DungeonsDelight.MOD_ID, id)));
    }

    private static SoundEvent registerVanillaID(String id) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(DungeonsDelight.MOD_ID, id), SoundEvent.of(Identifier.ofVanilla(id)));
    }

    public static void loadSounds() {
    }
}
