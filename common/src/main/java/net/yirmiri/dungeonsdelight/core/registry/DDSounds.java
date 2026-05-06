package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDSounds {
    public static final Supplier<SoundEvent> DISC_MALADY = registerSoundEvent("disc.malady");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        return Services.REGISTRY.register(BuiltInRegistries.SOUND_EVENT, DungeonsDelight.MOD_ID, name, () ->
                SoundEvent.createVariableRangeEvent(RunicLib.customid(DungeonsDelight.MOD_ID, name))
        );
    }

    public static void load() {}
}
