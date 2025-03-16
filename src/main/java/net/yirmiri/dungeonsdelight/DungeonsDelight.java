package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.dungeonsdelight.common.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.common.entity.misc.AncientEggEntity;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityModel;
import net.yirmiri.dungeonsdelight.common.entity.monster_yam.MonsterYamEntityRenderer;
import net.yirmiri.dungeonsdelight.common.event.DDClientEvents;
import net.yirmiri.dungeonsdelight.common.event.DDCommonEvents;
import net.yirmiri.dungeonsdelight.core.registry.*;
import net.yirmiri.dungeonsdelight.core.init.DDBlockSetTypes;

import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DungeonsDelightConfig.COMMON, "dungeonsdelight-config.toml");

        DDBlocks.BLOCKS.register(modEventBus);
        DDItems.ITEMS.register(modEventBus);
        DDEffects.MOB_EFFECTS.register(modEventBus);
        DDParticles.PARTICLE_TYPES.register(modEventBus);
        DDBlockEntities.BE_TYPES.register(modEventBus);
        DDRecipeRegistries.RECIPE_SERIALIZERS.register(modEventBus);
        DDRecipeRegistries.RECIPE_TYPES.register(modEventBus);
        DDMenuTypes.MENU_TYPES.register(modEventBus);
        DDCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        DDEntities.ENTITIES.register(modEventBus);
        DDLootFunctions.LOOT_FUNCTIONS.register(modEventBus);
        DDSounds.SOUNDS.register(modEventBus);
        DDEnchantments.ENCHANTMENTS.register(modEventBus);

        modEventBus.addListener(DDCommonEvents::commonSetup);
        modEventBus.addListener(DDClientEvents::clientSetup);
        modEventBus.addListener(DDClientEvents::onEntityRendererRegister);
        modEventBus.addListener(DDCommonEvents::addEntityAttributes);
        modEventBus.addListener(DDClientEvents::onEntityRendererLayerRegister);
        modEventBus.addListener(DungeonsDelightDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
