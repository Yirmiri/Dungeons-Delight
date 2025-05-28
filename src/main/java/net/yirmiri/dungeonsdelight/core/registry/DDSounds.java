package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.function.Supplier;

public class DDSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, DungeonsDelight.MOD_ID);

    //CLEAVER
    public static final Supplier<SoundEvent> CLEAVER_HIT_BLOCK = SOUNDS.register("item.cleaver.hit_block", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "item.cleaver.hit_block")));
    public static final Supplier<SoundEvent> CLEAVER_HIT_ENTITY = SOUNDS.register("item.cleaver.hit_entity", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "item.cleaver.hit_entity")));
    public static final Supplier<SoundEvent> CLEAVER_FLYING = SOUNDS.register("item.cleaver.flying", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "item.cleaver.flying")));
    public static final Supplier<SoundEvent> CLEAVER_THROW = SOUNDS.register("item.cleaver.throw", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "item.cleaver.throw")));
    public static final Supplier<SoundEvent> CLEAVER_RICOCHET = SOUNDS.register("item.cleaver.ricochet", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "item.cleaver.ricochet")));
    public static final Supplier<SoundEvent> CLEAVER_SERRATED_STRIKE = SOUNDS.register("item.cleaver.serrated_strike", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "item.cleaver.serrated_strike")));

    //MONSTER YAM
    public static final Supplier<SoundEvent> MONSTER_YAM_AMBIENT = SOUNDS.register("entity.monster_yam.ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "entity.monster_yam.ambient")));
    public static final Supplier<SoundEvent> MONSTER_YAM_HURT = SOUNDS.register("entity.monster_yam.hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "entity.monster_yam.hurt")));
    public static final Supplier<SoundEvent> MONSTER_YAM_DEATH = SOUNDS.register("entity.monster_yam.death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "entity.monster_yam.death")));
    public static final Supplier<SoundEvent> MONSTER_YAM_STEP = SOUNDS.register("entity.monster_yam.step", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "entity.monster_yam.step")));

    //STAINED SCRAP
    public static final Supplier<SoundEvent> STAINED_SCRAP_PLACE = SOUNDS.register("block.stained_scrap.place", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "block.stained_scrap.place")));
    public static final Supplier<SoundEvent> STAINED_SCRAP_STEP = SOUNDS.register("block.stained_scrap.step", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "block.stained_scrap.step")));
    public static final Supplier<SoundEvent> STAINED_SCRAP_BREAK = SOUNDS.register("block.stained_scrap.break", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "block.stained_scrap.break")));
    public static final Supplier<SoundEvent> STAINED_SCRAP_FALL = SOUNDS.register("block.stained_scrap.fall", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "block.stained_scrap.fall")));
    public static final Supplier<SoundEvent> STAINED_SCRAP_HIT = SOUNDS.register("block.stained_scrap.hit", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "block.stained_scrap.hit")));

    //EFFECT
    public static final Supplier<SoundEvent> DECISIVE_CRIT = SOUNDS.register("effect.decisive.crit", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DungeonsDelight.MOD_ID, "effect.decisive.crit")));
}
