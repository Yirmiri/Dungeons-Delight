package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.cleaver.CleaverEntity;

import java.util.function.Supplier;

public class DDEntities {
    //MISC
    public static final Supplier<EntityType<CleaverEntity>> CLEAVER = register("cleaver", () -> (
            EntityType.Builder.<CleaverEntity>of(CleaverEntity::new, MobCategory.MISC)
                    .sized(0.75F, 0.5F).clientTrackingRange(4).updateInterval(10).build("cleaver")));

    private static <T extends EntityType<?>> Supplier<T> register(String id, Supplier<T> supplier) {
        return RLServices.REGISTRY.registerEntityType(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
