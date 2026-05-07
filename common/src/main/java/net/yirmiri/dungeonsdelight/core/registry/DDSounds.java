package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDSounds {
    //CLEAVER
    public static final Supplier<SoundEvent> CLEAVER_HIT_BLOCK = register("item.cleaver.hit_block");
    public static final Supplier<SoundEvent> CLEAVER_HIT_ENTITY = register("item.cleaver.hit_entity");
    public static final Supplier<SoundEvent> CLEAVER_READY = register("item.cleaver.ready");
    public static final Supplier<SoundEvent> CLEAVER_FLYING = register("item.cleaver.flying");
    public static final Supplier<SoundEvent> CLEAVER_THROW = register("item.cleaver.throw");
    public static final Supplier<SoundEvent> CLEAVER_RICOCHET = register("item.cleaver.ricochet");
    public static final Supplier<SoundEvent> CLEAVER_SERRATED_STRIKE = register("item.cleaver.serrated_strike");
    public static final Supplier<SoundEvent> CLEAVER_THROW_DULL = register("item.cleaver.throw.dull");

    //MUSIC
    public static final Supplier<SoundEvent> DISC_MALADY = register("disc.malady");
    public static final Supplier<SoundEvent> DISC_MALADY_B = register("disc.malady_b");

    private static Supplier<SoundEvent> register(String id) {
        return Services.REGISTRY.register(BuiltInRegistries.SOUND_EVENT, DungeonsDelight.MOD_ID, id, () ->
                SoundEvent.createVariableRangeEvent(RunicLib.customid(DungeonsDelight.MOD_ID, id))
        );
    }

    public static void load() {}
}
