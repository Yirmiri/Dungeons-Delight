package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.common.entity.living.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.entity.living.treasure_bug.TreasureBugEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.AncientEggEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.EchoBlastEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.RancidReductionEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.vexing_fangs.VexingFangsEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;

import java.util.function.Supplier;

public class DDEntities {
    //ROTTEN
    public static final Supplier<EntityType<MonsterYamEntity>> MONSTER_YAM = register("monster_yam", () -> (
            EntityType.Builder.of(MonsterYamEntity::new, MobCategory.MONSTER)
                    .sized(0.8F, 2.9F).clientTrackingRange(8).build("monster_yam")));

    //UNDEAD
    public static final Supplier<EntityType<CamelHuskEntity>> CAMEL_HUSK = register("camel_husk", () -> (
            EntityType.Builder.of(CamelHuskEntity::new, MobCategory.CREATURE)
                    .sized(1.7F, 2.375F).clientTrackingRange(10).build("camel_husk")));

    //LIVING
    public static final Supplier<EntityType<TreasureBugEntity>> TREASURE_BUG = register("treasure_bug", () -> (
            EntityType.Builder.of(TreasureBugEntity::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.3F).clientTrackingRange(8).build("treasure_bug")));

    //MISC
    public static final Supplier<EntityType<CleaverEntity>> CLEAVER = register("cleaver", () -> (
            EntityType.Builder.<CleaverEntity>of(CleaverEntity::new, MobCategory.MISC)
                    .sized(0.75F, 0.5F).clientTrackingRange(4).updateInterval(10).build("cleaver")));

    public static final Supplier<EntityType<AncientEggEntity>> ANCIENT_EGG = register("ancient_egg", () -> (
            EntityType.Builder.<AncientEggEntity>of(AncientEggEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("ancient_egg")));

    public static final Supplier<EntityType<EchoBlastEntity>> ECHO_BLAST = register("echo_blast", () -> (
            EntityType.Builder.<EchoBlastEntity>of(EchoBlastEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("echo_blast")));

    public static final Supplier<EntityType<VexingFangsEntity>> VEXING_FANGS = register("vexing_fangs", () -> (
            EntityType.Builder.<VexingFangsEntity>of(VexingFangsEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.8F).clientTrackingRange(6).updateInterval(2).build("vexing_fangs")));

    public static final Supplier<EntityType<RancidReductionEntity>> RANCID_REDUCTION = register("rancid_reduction", () -> (
            EntityType.Builder.<RancidReductionEntity>of(RancidReductionEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("rancid_reduction")));

    private static <T extends EntityType<?>> Supplier<T> register(String id, Supplier<T> supplier) {
        return Services.REGISTRY.registerEntityType(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}