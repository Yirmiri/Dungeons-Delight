package net.yirmiri.dungeonsdelight.core.registry;

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

    //STAINED SCRAP
    public static final RegistryObject<SoundEvent> STAINED_SCRAP_PLACE = SOUNDS.register("block.stained_scrap.place", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "block.stained_scrap.place")));
    public static final RegistryObject<SoundEvent> STAINED_SCRAP_STEP = SOUNDS.register("block.stained_scrap.step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "block.stained_scrap.step")));
    public static final RegistryObject<SoundEvent> STAINED_SCRAP_BREAK = SOUNDS.register("block.stained_scrap.break", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "block.stained_scrap.break")));
    public static final RegistryObject<SoundEvent> STAINED_SCRAP_FALL = SOUNDS.register("block.stained_scrap.fall", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "block.stained_scrap.fall")));
    public static final RegistryObject<SoundEvent> STAINED_SCRAP_HIT = SOUNDS.register("block.stained_scrap.hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "block.stained_scrap.hit")));

    //EFFECT
    public static final RegistryObject<SoundEvent> DECISIVE_CRIT = SOUNDS.register("effect.decisive.crit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DungeonsDelight.MOD_ID, "effect.decisive.crit")));
}
