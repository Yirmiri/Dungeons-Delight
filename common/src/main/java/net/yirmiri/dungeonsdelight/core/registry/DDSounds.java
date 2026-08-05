package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDSounds {
    //EFFECT
    public static final Supplier<SoundEvent> GENERIC_MONSTERIZE = register("effect.generic.monsterize");
    public static final Supplier<SoundEvent> ACIDIC_HISS = register("effect.acidic_hiss");

    //BLOCK MISC
    public static final Supplier<SoundEvent> SPIKE_TRAP_SHEATH = register("block.spike_trap.sheath");
    public static final Supplier<SoundEvent> SPIKE_TRAP_UNSHEATH = register("block.spike_trap.unsheath");

    //ITEM MISC
    public static final Supplier<SoundEvent> RANCID_REDUCTION = register("item.rancid_reduction.rot");
    public static final Supplier<SoundEvent> ROT_AND_STEEL_USE = register("item.rot_and_steel.use");

    //CLEAVER
    public static final Supplier<SoundEvent> CLEAVER_HIT_BLOCK = register("item.cleaver.hit_block");
    public static final Supplier<SoundEvent> CLEAVER_HIT_ENTITY = register("item.cleaver.hit_entity");
    public static final Supplier<SoundEvent> CLEAVER_READY = register("item.cleaver.ready");
    public static final Supplier<SoundEvent> CLEAVER_FLYING = register("item.cleaver.flying");
    public static final Supplier<SoundEvent> CLEAVER_THROW = register("item.cleaver.throw");
    public static final Supplier<SoundEvent> CLEAVER_RICOCHET = register("item.cleaver.ricochet");
    public static final Supplier<SoundEvent> CLEAVER_SERRATED_STRIKE = register("item.cleaver.serrated_strike");
    public static final Supplier<SoundEvent> CLEAVER_CLEAVE = register("item.cleaver.cleave");
    public static final Supplier<SoundEvent> CLEAVER_THROW_DULL = register("item.cleaver.throw.dull");

    //MONSTER YAM
    public static final Supplier<SoundEvent> MONSTER_YAM_AMBIENT = register("entity.monster_yam.ambient");
    public static final Supplier<SoundEvent> MONSTER_YAM_HURT = register("entity.monster_yam.hurt");
    public static final Supplier<SoundEvent> MONSTER_YAM_DEATH = register("entity.monster_yam.death");
    public static final Supplier<SoundEvent> MONSTER_YAM_STEP = register("entity.monster_yam.step");

    //STAINED SCRAP
    public static final Supplier<SoundEvent> STAINED_SCRAP_PLACE = register("block.stained_scrap.place");
    public static final Supplier<SoundEvent> STAINED_SCRAP_STEP = register("block.stained_scrap.step");
    public static final Supplier<SoundEvent> STAINED_SCRAP_BREAK = register("block.stained_scrap.break");
    public static final Supplier<SoundEvent> STAINED_SCRAP_FALL = register("block.stained_scrap.fall");
    public static final Supplier<SoundEvent> STAINED_SCRAP_HIT = register("block.stained_scrap.hit");

    //SOFT ROCK
    public static final Supplier<SoundEvent> SOFT_ROCK_PLACE = register("block.soft_rock.place");
    public static final Supplier<SoundEvent> SOFT_ROCK_STEP = register("block.soft_rock.step");
    public static final Supplier<SoundEvent> SOFT_ROCK_BREAK = register("block.soft_rock.break");
    public static final Supplier<SoundEvent> SOFT_ROCK_FALL = register("block.soft_rock.fall");
    public static final Supplier<SoundEvent> SOFT_ROCK_HIT = register("block.soft_rock.hit");

    //WORMOUTH
    public static final Supplier<SoundEvent> WORMOUTH_EAT = register("block.wormouth.eat");
    public static final Supplier<SoundEvent> WORMOUTH_OPEN = register("block.wormouth.open");
    public static final Supplier<SoundEvent> WORMOUTH_PANIC = register("block.wormouth.panic");
    public static final Supplier<SoundEvent> WORMOUTH_SHUT = register("block.wormouth.shut");
    public static final Supplier<SoundEvent> WORMOUTH_UNSHUT = register("block.wormouth.unshut");

    //MUSIC
    public static final Supplier<SoundEvent> MALADY = register("disc.malady");
    public static final Supplier<SoundEvent> MALADY_B = register("disc.malady_b");

    private static Supplier<SoundEvent> register(String id) {
        return Services.REGISTRY.register(BuiltInRegistries.SOUND_EVENT, DungeonsDelight.MOD_ID, id, () ->
                SoundEvent.createVariableRangeEvent(RunicLib.customid(DungeonsDelight.MOD_ID, id))
        );
    }

    public static void load() {}
}
