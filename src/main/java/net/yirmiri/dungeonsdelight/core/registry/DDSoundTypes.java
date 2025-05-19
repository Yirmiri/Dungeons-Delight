package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.world.level.block.SoundType;

public class DDSoundTypes {
    public static final SoundType STAINED_SCRAP = new SoundType(0.35F, 1.0F,
            DDSounds.STAINED_SCRAP_BREAK.get(),
            DDSounds.STAINED_SCRAP_STEP.get(),
            DDSounds.STAINED_SCRAP_PLACE.get(),
            DDSounds.STAINED_SCRAP_HIT.get(),
            DDSounds.STAINED_SCRAP_FALL.get()
    );
}
