package net.yirmiri.dungeonsdelight.event;

import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.DungeonsDelightConfig;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDCommonEvents {
    @SubscribeEvent
    public static void missingMappingsEvent(MissingMappingsEvent event) {
        Map<ResourceLocation, Supplier<Item>> itemsMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Item>>()
                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "slime_slab"), DDItems.SLIME_BAR)
                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "glowberry_gelatin"), DDItems.GLOW_BERRY_GELATIN)
                .build();

        Map<ResourceLocation, Supplier<Block>> blocksMap = new ImmutableMap.Builder<ResourceLocation, Supplier<Block>>()
                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "dungeon_pot"), DDBlocks.MONSTER_POT)
                .put(new ResourceLocation(DungeonsDelight.MOD_ID, "glowberry_gelatin_block"), DDBlocks.GLOW_BERRY_GELATIN_BLOCK)
                .build();

        for (MissingMappingsEvent.Mapping<Item> itemMapping : event.getMappings(ForgeRegistries.Keys.ITEMS, DungeonsDelight.MOD_ID)) {
            if (itemsMap.get(itemMapping.getKey()) != null) {
                itemMapping.remap(Objects.requireNonNull(itemsMap.get(itemMapping.getKey())).get());
            }
        }

        for (MissingMappingsEvent.Mapping<Block> blockMapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, DungeonsDelight.MOD_ID)) {
            if (blocksMap.get(blockMapping.getKey()) != null) {
                blockMapping.remap(Objects.requireNonNull(blocksMap.get(blockMapping.getKey())).get());
            }
        }
    }

    @SubscribeEvent
    public static void handleAdditionalFoodEffects(LivingEntityUseItemEvent.Finish event) {
        if (DungeonsDelightConfig.FD_STICK_FOODS_GRANT_STRENGTH.get() && event.getItem().getItem().equals(ModItems.BARBECUE_STICK.get())) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0));
        }

        if (DungeonsDelightConfig.FD_GLOWING_FOODS_GRANT_PERCEPTION.get() && event.getItem().getItem().equals(ModItems.GLOW_BERRY_CUSTARD.get())) {
            event.getEntity().addEffect(new MobEffectInstance(DDEffects.PERCEPTION.get(), 1200, 0));
        }
    }

    @SubscribeEvent
    public static void feralBiteAttack(LivingHurtEvent event) { //TODO: test
        Random random = new Random();
        LivingEntity target = event.getEntity();

        if (!target.level().isClientSide() && event.getSource().getDirectEntity() instanceof LivingEntity living && living.hasEffect(DDEffects.FERAL_BITE.get())) {
            if (random.nextInt(4) == 0) {
                target.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), 160, 0));
                //TODO: feral bite sound
            }
        }
    }
}