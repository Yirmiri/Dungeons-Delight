package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.runiconfig.Runiconfig;
import net.minecraft.world.inventory.RecipeBookType;
import net.yirmiri.dungeonsdelight.core.integration.DDIntegration;
import net.yirmiri.dungeonsdelight.core.registry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsDelight {
    public static final String MOD_ID = "dungeonsdelight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static DungeonsDelightConfig CONFIG;

    public static void init() {
        Runiconfig.registerConfig(MOD_ID, DungeonsDelightConfig.class, DungeonsDelightConfig::new);
        CONFIG = Runiconfig.getConfig(MOD_ID);

        // Call immediately to force mixin - Artyrian
        RecipeBookType.values();

        DDSounds.load();
        DDEffects.load();
        DDItems.load();
        DDBlocks.load();
        DDBlockEntities.load();
        DDFeatures.load();
        DDEntities.load();
        DDMenus.load();
        DDCreativeTabs.load();
        DDAttributes.load();
        DDEnchantments.load();
        DDParticles.load();
        DDRecipeTypes.load();
        DDCriteriaTriggers.load();
        DDStats.load();
        DDPotions.load();
        DDIntegration.load();
    }
}

//FIXES
//todo fix config sometimes randomly failing
//todo classic pack and override pack on fabric
//todo fix weird rendering bug on monster pots thru the light model on them

//CONTENT
//todo husk has rare chance to drop manallium instead of carrots/potato
//todo drowned has rare chance to drop endelve instead of carrots/potato
//todo add squib exploding when ticking
//todo a way to make wavy blocks turn off wavyness (gameplay wise)
//todo new advancements (zombify a mob with foul apple, getting spider milk, getting spider milk while stained scrap bars are near)

//DESIGN (not concrete/set to change)
//todo serendipity or monster serendipity increase loot table luck
//todo rework sculk blast so it doesnt suck with expensive foods
//todo biteable foods dont stack but can be refilled, after refilling variable amount of times with ingredients from the recipe that differ per refill itll basically have costed the recipe
//todo magmaroni in changelog (when diver down completed)
//todo ravager meat in changelog (when horde omen finished)
//todo common tags https://github.com/neoforged/NeoForge/tree/1.20.6/src/generated/resources/data/c/tags https://wiki.fabricmc.net/community:common_tags
//todo give hollowed effects
//todo add creeper food effect
//todo add changelog creeperilla effect
//todo serrated stacks up bleed per pierced entity instead of concurrent pierces, slower tick rate of damage per pierce maybe, maybe configurable

//EFFECTS
//todo serendipity in changelog + configs for it (note that ravenous increases luck by 1.25 per rr level)
//todo frequent mob spawning while putrid scent II+ active
//todo make hollowed persist on death and not removed with milk (can be cured with golden apple and weakness)

//CLEAVER
//todo dungeonsdelight enchantments on cleaver at enchantment table like darting and that other guy
//todo cleaver tech rework in changelog

//ENTITY
//todo cleaver animation on zombies (fix their arms being up)
//todo fix zombie horses being jesus h christ (not floating or sinking while ridden)
//todo spider does not implement neutral for some reason
//todo single bars should prevent spider climbing
//todo rotbulb run to targets should be a tag
//todo fix fucked up animations on camel husk
//todo give undead mounts special perks

//FARMING
//todo crop rotting datadriven in changelog
//todo save age state and add ages to rotten crops in rancid redct convert
//todo make rot version of vanilla crops not REQUIRE terror preta
//todo wild rotbulb generation
//todo wild crops
//todo add new crops to changelog

//INTEGRATION
//todo make recipes not load if x mod loaded or datapacks like hecco
//todo finish nirvana integration
//todo pass the nirvana advancement for nirvana creeperilla integration
//todo port malum gross_foods tag
//todo farmersdelight just farmersdelight