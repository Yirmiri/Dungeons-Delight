package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.AncientEggEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.RancidReductionEntity;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;

import java.util.function.Supplier;

public class DDEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, DungeonsDelight.MOD_ID);

    public static final Supplier<EntityType<AncientEggEntity>> ANCIENT_EGG = ENTITIES.register("ancient_egg", () -> (
            EntityType.Builder.<AncientEggEntity>of(AncientEggEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("ancient_egg")));

//    public static final Supplier<EntityType<CleaverEntity>> CLEAVER = ENTITIES.register("cleaver", () -> (
//            EntityType.Builder.<CleaverEntity>of(CleaverEntity::new, MobCategory.MISC)
//                    .sized(0.75F, 0.5F).clientTrackingRange(4).updateInterval(10).build("cleaver")));

    public static final Supplier<EntityType<MonsterYamEntity>> MONSTER_YAM = ENTITIES.register("monster_yam", () -> (
            EntityType.Builder.of(MonsterYamEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 2.9F).clientTrackingRange(8).build("monster_yam")));

    public static final Supplier<EntityType<RancidReductionEntity>> RANCID_REDUCTION = ENTITIES.register("rancid_reduction", () -> (
            EntityType.Builder.<RancidReductionEntity>of(RancidReductionEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("rancid_reduction")));
}
