package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.living.camel_husk.CamelHuskEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.EchoBlastEntity;
import net.yirmiri.dungeonsdelight.common.entity.misc.cleaver.CleaverEntity;

import java.util.function.Supplier;

public class DDEntities {
    //UNDEAD
    public static final Supplier<EntityType<CamelHuskEntity>> CAMEL_HUSK = register("camel_husk", () -> (
            EntityType.Builder.of(CamelHuskEntity::new, MobCategory.CREATURE)
                    .sized(1.7F, 2.375F).clientTrackingRange(4).updateInterval(10).build("camel_husk")));

    //MISC
    public static final Supplier<EntityType<CleaverEntity>> CLEAVER = register("cleaver", () -> (
            EntityType.Builder.<CleaverEntity>of(CleaverEntity::new, MobCategory.MISC)
                    .sized(0.75F, 0.5F).clientTrackingRange(4).updateInterval(10).build("cleaver")));

    public static final Supplier<EntityType<EchoBlastEntity>> ECHO_BLAST = register("echo_blast", () -> (
            EntityType.Builder.<EchoBlastEntity>of(EchoBlastEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("echo_blast")));

    private static <T extends EntityType<?>> Supplier<T> register(String id, Supplier<T> supplier) {
        return Services.REGISTRY.registerEntityType(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
