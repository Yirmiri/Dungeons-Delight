package net.yirmiri.dungeonsdelight;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.networking.ForgeDDNetworking;
import net.yirmiri.dungeonsdelight.core.registry.DDRegistries;
import net.yirmiri.dungeonsdelight.event.DDCommonEvents;

@Mod(DungeonsDelight.MOD_ID)
public class ForgeDungeonsDelight {
    public ForgeDungeonsDelight() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // REMOVE FOR 1.21.1 - must be init before all else in 1.20
        // Technically could register with unique color here, but current code makes multiloader easier
        DDRarities.MONSTER = Rarity.create(DDRarities.MONSTER_STRING, ChatFormatting.LIGHT_PURPLE);
        DungeonsDelight.init();

        eventBus.addListener(DDCommonEvents::commonSetup);
        eventBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        DDRegistries.loadCompostables();
    }
}