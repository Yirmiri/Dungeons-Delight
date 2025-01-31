package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.dungeonsdelight.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.datagen.DDDatagen;
import net.yirmiri.dungeonsdelight.registry.*;
import net.yirmiri.dungeonsdelight.registry.compat.DDCItems;
import net.yirmiri.dungeonsdelight.registry.compat.DDCTFKnives;
import net.yirmiri.dungeonsdelight.util.DDBlockSetTypes;
import net.yirmiri.dungeonsdelight.util.DDUtil;
import org.slf4j.Logger;

@Mod(DungeonsDelight.MOD_ID)
public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DungeonsDelight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DDBlocks.BLOCKS.register(modEventBus);
        DDItems.ITEMS.register(modEventBus);
        DDEffects.MOB_EFFECTS.register(modEventBus);
        DDParticles.PARTICLE_TYPES.register(modEventBus);
        DDBlockEntities.BE_TYPES.register(modEventBus);
        DDRecipeRegistries.RECIPE_SERIALIZERS.register(modEventBus);
        DDRecipeRegistries.RECIPE_TYPES.register(modEventBus);
        DDMenuTypes.MENU_TYPES.register(modEventBus);
        DDCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        if (isModLoaded(DDUtil.TF_ID)) {
            DDCTFKnives.register();
            DDCItems.register();
        }

        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(DDDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
        modEventBus.addListener(DDParticles::registerFactories);

        MinecraftForge.EVENT_BUS.register(this);
    }
    //TODO - Make wormwood flammable || balance monster cooking exp gain || check if always eat is on for FD foods || rewrite wormroot gen code

    //TODO (compat) - TF knightmetal knife ability || TF loot modifiers

    public void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new));

        Sheets.addWoodType(DDBlockSetTypes.WORMWOOD);

        //CUTOUT
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.MONSTER_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMROOTS.get(), RenderType.cutout());

        //CUTOUT MIPPED
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_DOOR.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(DDBlocks.WORMWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
