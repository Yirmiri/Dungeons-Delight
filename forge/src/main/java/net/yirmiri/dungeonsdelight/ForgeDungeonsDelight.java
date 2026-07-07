package net.yirmiri.dungeonsdelight;

import net.minecraft.ChatFormatting;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.init.DDRecipeBookTypes;
import net.yirmiri.dungeonsdelight.core.sound.ForgeDDSoundType;
import net.yirmiri.dungeonsdelight.event.DDCommonEvents;

@Mod(DungeonsDelight.MOD_ID)
public class ForgeDungeonsDelight {
    public ForgeDungeonsDelight() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //REMOVE ALL OF THESE FOR 1.21.1 - must be init before all else in 1.20
        //Technically could register with unique color here, but current code makes multiloader easier
        DDRarities.MONSTER = Rarity.create(DDRarities.MONSTER_STRING, ChatFormatting.LIGHT_PURPLE);

        //Recipe stuff
        DDRecipeBookTypes.DD_MONSTERPOT = RecipeBookType.create(DDRecipeBookTypes.DD_MP_ID);
        RecipeBookSettings.addTagsForType(DDRecipeBookTypes.DD_MONSTERPOT, DDRecipeBookTypes.DD_MP_OPEN, DDRecipeBookTypes.DD_MP_FILTERING);

        //Init before all else because of some funky SoundType issues
        ForgeDDSoundType.init();
        DungeonsDelight.init();

        eventBus.addListener(DDCommonEvents::commonSetup);
    }
}