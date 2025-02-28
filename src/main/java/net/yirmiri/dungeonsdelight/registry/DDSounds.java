package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DungeonsDelight.MOD_ID);

    //CLEAVER
    public static final RegistryObject<SoundEvent> CLEAVER_HIT_BLOCK = SOUNDS.register("item.cleaver.hit_block", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "item.cleaver.hit_block")));
    public static final RegistryObject<SoundEvent> CLEAVER_HIT_ENTITY = SOUNDS.register("item.cleaver.hit_entity", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "item.cleaver.hit_entity")));
    public static final RegistryObject<SoundEvent> CLEAVER_FLYING = SOUNDS.register("item.cleaver.flying", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "item.cleaver.flying")));
    public static final RegistryObject<SoundEvent> CLEAVER_THROW = SOUNDS.register("item.cleaver.throw", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "item.cleaver.throw")));
    public static final RegistryObject<SoundEvent> CLEAVER_RICOCHET = SOUNDS.register("item.cleaver.ricochet", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "item.cleaver.ricochet")));
    public static final RegistryObject<SoundEvent> CLEAVER_SERRATED_STRIKE = SOUNDS.register("item.cleaver.serrated_strike", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "item.cleaver.serrated_strike")));

    //MONSTER YAM
    public static final RegistryObject<SoundEvent> MONSTER_YAM_AMBIENT = SOUNDS.register("entity.monster_yam.ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "entity.monster_yam.ambient")));
    public static final RegistryObject<SoundEvent> MONSTER_YAM_HURT = SOUNDS.register("entity.monster_yam.hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "entity.monster_yam.hurt")));
    public static final RegistryObject<SoundEvent> MONSTER_YAM_DEATH = SOUNDS.register("entity.monster_yam.death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "entity.monster_yam.death")));
    public static final RegistryObject<SoundEvent> MONSTER_YAM_STEP = SOUNDS.register("entity.monster_yam.step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "entity.monster_yam.step")));

    //EFFECT
    public static final RegistryObject<SoundEvent> DECISIVE_CRIT = SOUNDS.register("effect.decisive.crit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "effect.decisive.crit")));
}
