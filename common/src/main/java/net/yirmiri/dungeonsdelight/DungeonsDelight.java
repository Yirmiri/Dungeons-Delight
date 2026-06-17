package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.runiconfig.Runiconfig;
import net.yirmiri.dungeonsdelight.core.registry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static DungeonsDelightConfig CONFIG;

    public static void init() {
        DDSounds.load();
        DDEffects.load();
        DDItems.load();
        DDBlocks.load();
        DDBlockEntities.load();
        DDFeatures.load();
        DDEntities.load();
        DDCreativeTabs.load();
        DDAttributes.load();
        DDEnchantments.load();
        DDParticles.load();
        DDRecipeTypes.load();

        Runiconfig.registerConfig(MOD_ID, DungeonsDelightConfig.class, DungeonsDelightConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);
    }
}
//todo cleaver tech rework in changelog
//todo add new crops to changelog
//todo wild crops
//todo husk has rare chance to drop manallium instead of carrots/potato
//todo drowned has rare chance to drop endelve instead of carrots/potato
//todo serrated stacks up bleed per pierced entity instead of concurrent pierces, slower tick rate of damage per pierce maybe, maybe configurable
//todo classic pack and override pack on fabric
//todo make suckers a biteable food
//todo dungeonsdelight enchantments on cleaver at enchantment table like darting and that other guy
//todo add food recipes whenever that freaking monster pot is added
//todo add creeper food effect
//todo add changelog creeperilla effect
//todo add squib exploding when ticking
//todo black apple recipe when rancid is added
//todo make hollowed unremovable with milk (must eat golden apple while weakened to remove)
//todo fix config sometimes randomly failing
//todo give undead mobs special perks
//todo give hollowed effects
//todo fix fucked up animations on camel husk
//todo allow basic entities like zombies capable of using cleavers