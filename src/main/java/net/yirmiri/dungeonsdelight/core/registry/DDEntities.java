package net.yirmiri.dungeonsdelight.core.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.misc.AncientEggEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntity;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;

public class DDEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DungeonsDelight.MOD_ID);

    public static final RegistryObject<EntityType<AncientEggEntity>> ANCIENT_EGG = ENTITIES.register("ancient_egg", () -> (
            EntityType.Builder.<AncientEggEntity>of(AncientEggEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("ancient_egg")));

    public static final RegistryObject<EntityType<CleaverEntity>> CLEAVER = ENTITIES.register("cleaver", () -> (
            EntityType.Builder.<CleaverEntity>of(CleaverEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).build("cleaver")));

    public static final RegistryObject<EntityType<MonsterYamEntity>> MONSTER_YAM = ENTITIES.register("monster_yam", () -> (
            EntityType.Builder.of(MonsterYamEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 2.9F).clientTrackingRange(8).build("monster_yam")));

//    public static final RegistryObject<EntityType<GunkArrowEntity>> GUNK_ARROW = ENTITIES.register("gunk_arrow", () -> (
//            EntityType.Builder.of(GunkArrowEntity::new, MobCategory.MISC)
//                    .sized(0.5F, 0.5F).build("gunk_arrow")));
}
