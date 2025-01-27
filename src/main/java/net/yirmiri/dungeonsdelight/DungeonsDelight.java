package net.yirmiri.dungeonsdelight;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.dungeonsdelight.block.entity.container.MonsterPotScreen;
import net.yirmiri.dungeonsdelight.datagen.DDDatagen;
import net.yirmiri.dungeonsdelight.registry.*;
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

        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(DDDatagen::gatherData);
        modEventBus.addListener(DDCreativeTabs::buildCreativeTabs);
        modEventBus.addListener(DDParticles::registerFactories);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(DDMenuTypes.MONSTER_POT.get(), MonsterPotScreen::new));
    }
}
