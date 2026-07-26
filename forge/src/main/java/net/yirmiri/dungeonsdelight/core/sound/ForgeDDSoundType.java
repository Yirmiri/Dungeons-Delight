package net.yirmiri.dungeonsdelight.core.sound;

import net.minecraftforge.common.util.ForgeSoundType;
import net.yirmiri.dungeonsdelight.core.registry.DDSoundTypes;
import net.yirmiri.dungeonsdelight.core.registry.DDSounds;

public class ForgeDDSoundType {
    public static void init() {
        DDSoundTypes.STAINED_SCRAP = new ForgeSoundType(0.35F, 1.0F,
                DDSounds.STAINED_SCRAP_BREAK,
                DDSounds.STAINED_SCRAP_STEP,
                DDSounds.STAINED_SCRAP_PLACE,
                DDSounds.STAINED_SCRAP_HIT,
                DDSounds.STAINED_SCRAP_FALL
        );

        DDSoundTypes.SOFT_ROCK = new ForgeSoundType(1.0F, 1.0F,
                DDSounds.SOFT_ROCK_BREAK,
                DDSounds.SOFT_ROCK_STEP,
                DDSounds.SOFT_ROCK_PLACE,
                DDSounds.SOFT_ROCK_HIT,
                DDSounds.SOFT_ROCK_FALL
        );
    }
}