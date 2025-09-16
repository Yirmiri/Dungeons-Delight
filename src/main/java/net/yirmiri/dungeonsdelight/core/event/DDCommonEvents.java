package net.yirmiri.dungeonsdelight.core.event;

import net.azurune.runiclib.core.platform.services.RLRegistryHelper;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.entity.rotten_zombie.RottenZombieEntity;
import net.yirmiri.dungeonsdelight.core.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.core.registry.DDEntities;
import net.yirmiri.dungeonsdelight.core.registry.DDItems;
import net.yirmiri.dungeonsdelight.integration.twilightforest.TFItems;

//@EventBusSubscriber(modid = DungeonsDelight.MOD_ID)
public class DDCommonEvents {

//    @SubscribeEvent
//    public static void handleAdditionalFoodEffects(LivingEntityUseItemEvent.Finish event) {
//        if (DDConfigCommon.FD_STICK_FOODS_GRANT_STRENGTH.get() && event.getItem().getItem().equals(ModItems.BARBECUE_STICK.get())) {
//            event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0));
//        }
//
//        if (DDConfigCommon.FD_STICK_FOODS_GRANT_STRENGTH.get() && event.getItem().getItem().equals(ModItems.MELON_POPSICLE.get())) {
//            event.getEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0));
//        }
//
//        if (DDConfigCommon.FD_GLOWING_FOODS_GRANT_PERCEPTION.get() && event.getItem().getItem().equals(ModItems.GLOW_BERRY_CUSTARD.get())) {
//            event.getEntity().addEffect(new MobEffectInstance(RLMobEffects.PERCEPTION, 1200, 0));
//        }
//    }

    //    @SubscribeEvent
//    public static void feralBiteAttack(LivingHurtEvent event) {
//        Random random = new Random();
//        LivingEntity target = event.getEntity();
//
//        if (!target.level().isClientSide() && event.getSource().getDirectEntity() instanceof LivingEntity living && living.hasEffect(DDEffects.FERAL_BITE.get())) {
//            if (random.nextInt(4) == 0) {
//                target.addEffect(new MobEffectInstance(DDEffects.SERRATED.get(), 160, 0));
//                //TODO: feral bite sound
//            }
//        }
//    }
}