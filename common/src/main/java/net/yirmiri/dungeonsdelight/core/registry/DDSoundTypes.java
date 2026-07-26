package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class DDSoundTypes {
    public static SoundType STAINED_SCRAP = new SoundType(0.35F, 1.0F,
            DDSounds.STAINED_SCRAP_BREAK.get(),
            DDSounds.STAINED_SCRAP_STEP.get(),
            DDSounds.STAINED_SCRAP_PLACE.get(),
            DDSounds.STAINED_SCRAP_HIT.get(),
            DDSounds.STAINED_SCRAP_FALL.get()
    );

    public static SoundType SOFT_ROCK = new SoundType(1.0F, 1.0F,
            DDSounds.SOFT_ROCK_BREAK.get(),
            SoundEvents.STONE_STEP,
            DDSounds.SOFT_ROCK_PLACE.get(),
            DDSounds.SOFT_ROCK_HIT.get(),
            DDSounds.SOFT_ROCK_FALL.get()
    );
}