package net.yirmiri.dungeonsdelight.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.entity.AncientEggEntity;

public class DDEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DungeonsDelight.MOD_ID);

    public static final RegistryObject<EntityType<AncientEggEntity>> ANCIENT_EGG = ENTITIES.register("ancient_egg", () -> (
            EntityType.Builder.<AncientEggEntity>of(AncientEggEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("ancient_egg")));
}
