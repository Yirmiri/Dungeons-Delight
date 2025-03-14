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
import net.yirmiri.dungeonsdelight.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.entity.misc.CleaverEntityRenderer;
import net.yirmiri.dungeonsdelight.entity.misc.AncientEggEntity;
import net.yirmiri.dungeonsdelight.entity.monster_yam.MonsterYamEntity;
import net.yirmiri.dungeonsdelight.entity.monster_yam.MonsterYamEntityModel;
import net.yirmiri.dungeonsdelight.entity.monster_yam.MonsterYamEntityRenderer;
import net.yirmiri.dungeonsdelight.init.DDLootFunctions;
import net.yirmiri.dungeonsdelight.registry.*;
import net.yirmiri.dungeonsdelight.registry.compat.DDCItems;
import net.yirmiri.dungeonsdelight.registry.compat.DDCTFKnives;
import net.yirmiri.dungeonsdelight.init.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.util.DDUtil;

import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

//TODO: move events to separate class (i have a migraine this is just a note for future self)
    public DungeonsDelight() {
        if (!isModLoaded("farmersdelight")) {
            LOGGER.atError().log("Yo you realize Dungeon's Delight is a Farmer's Delight addon?, Please download Farmer's Delight...");
        }

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

        if (isModLoaded(DDUtil.TF_ID)) {
            DDCTFKnives.register();
        }

        DDCItems.register();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(DungeonsDelightDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
        modEventBus.addListener(this::onEntityRendererRegister);
        modEventBus.addListener(this::addEntityAttributes);
        modEventBus.addListener(this::registerLayer);
        //modEventBus.addListener(DDCToolEvents::knightmetalKnifeAttack);

        MinecraftForge.EVENT_BUS.register(this);
    }
    //TODO - flammables || new wormroot gen code || burrow gut gives mine speed || exudation cooldown
    //TODO - add monster burger effects || composts, etc || double stacked monster burger (late game) || fix creative rock candy attack item give
    //TODO - fix client dsync with cleaver and ricochet hitting a block || fix pierce on dead entity || biteables request different ingredients
    //TODO - fix server sided particles? || add monster spawner green fire || monster effects transform base effects and keep their duration

    //TODO (compat) - TF knightmetal knife ability || rewrite compat especially || alex cave magnetic tag

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event) {
        registerDispenserBehaviors();
    }

    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new));

        Sheets.addWoodType(DDBlockSetTypes.WORMWOOD);

        //CUTOUT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.MONSTER_POT.get(), RenderType.cutout());

        //CUTOUT MIPPED
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_PLANT.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.ROTBULB_CROP.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.STAINED_SCRAP_BARS.get(), RenderType.cutoutMipped());

        //TRANSLUCENT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMROOTS.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(DDEntities.MONSTER_YAM.get(), MonsterYamEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void onEntityRendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DDEntities.ANCIENT_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DDEntities.CLEAVER.get(), CleaverEntityRenderer::new);
        event.registerEntityRenderer(DDEntities.MONSTER_YAM.get(), MonsterYamEntityRenderer::new);
    }

    @SubscribeEvent
    public void registerLayer(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MonsterYamEntityModel.LAYER_LOC, MonsterYamEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(DDItems.ANCIENT_EGG.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
                return new AncientEggEntity(level, position.x(), position.y(), position.z());
            }
        });
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
