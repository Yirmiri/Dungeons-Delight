package net.yirmiri.dungeonsdelight;

import net.azurune.runiclib.core.library.runiconfig.*;

public class DungeonsDelightConfig {
    public final ConfigValue<Boolean> spawnersEmitLivingFlames;
    public final ConfigValue<Boolean> monsterEffectBackground;
    public final ConfigValue<Boolean> statusEffectTooltips;
    public final ConfigValue<Boolean> itemEffectTooltips;
    public final ConfigValue<Boolean> vanillaStatusEffectTooltips;
    public final ConfigValue<Boolean> vanillaItemEffectTooltips;
    public final ConfigValue<Boolean> showChanceTooltips;
    public final ConfigValue<Boolean> invisibilityDetectionRangeTooltip;

    //CLEAVERS
    public final NumberConfigValue<Float> cleaverAttackDamage;
    public final NumberConfigValue<Float> cleaverAttackSpeed;
    public final NumberConfigValue<Float> cleaverChargeMultiplier;
    public final NumberConfigValue<Float> cleaverPiercingDamageMultiplier;
    public final NumberConfigValue<Integer> cleaverMissCooldownTicks;
    public final NumberConfigValue<Float> cleaverDartingChargeMultiplier;
    public final NumberConfigValue<Integer> cleaverDartingRangeDivisor;
    public final NumberConfigValue<Float> cleaverSerratedEffectDamage;
    public final NumberConfigValue<Float> cleaverRicochetAssistRange;
    public final NumberConfigValue<Float> cleaverRicochetDamageMultiplier;

    //BLOCKS
    public final ConfigValue<Boolean> bonemealableRotbulbs;
    public final ConfigValue<Boolean> itemGratesRequiresSneakingToInsert;

    //ITEMS
    public final ConfigValue<Boolean> increasedVanillaMealStackSize;
    public final ConfigValue<Boolean> effectsOnVanillaMeals;
    public final ConfigValue<Integer> rockCandyPickupCooldownTicks;
    public final ConfigValue<Integer> cocktailCooldownTicks;
    public final ConfigValue<Float> vexingFangsDamage;
    public final ConfigValue<Integer> vexingFangsCount;
    public final ConfigValue<Integer> vexingFangsSpeed;
    public final ConfigValue<Double> vexingFangsDistance;
    public final ConfigValue<Integer> vexingFangsLifetimeTicks;
    public final ConfigValue<Integer> bubbleEyeTeaMaxAmplifier;
    public final ConfigValue<Integer> hollowingTicks;
    public final ConfigValue<Integer> hollowingMaxRandomTicks;

    //EFFECTS
    public final ConfigValue<Float> putridScentRange;
    public final ConfigValue<Integer> tenacityInterval;
    public final ConfigValue<Float> exudationBaseDamage;
    public final ConfigValue<Float> exudationDamageMultiplier;
    public final ConfigValue<Boolean> exudationDamageMultiplierWhileHeartsActive;
    public final ConfigValue<Float> exudationBaseRange;
    public final ConfigValue<Integer> exudationInvulnerabilityTicks;
    public final ConfigValue<Float> decisiveRange;
    public final ConfigValue<Float> decisiveBaseDamage;
    public final ConfigValue<Float> pouncingDistance;
    public final ConfigValue<Float> pouncingHeight;
    public final ConfigValue<Integer> pouncingCooldownTicks;
    public final ConfigValue<Integer> diverDownMaxLengthTicks;
    public final ConfigValue<Integer> pouncingRavenousCooldownTicks;
    public final ConfigValue<Integer> telepotageHomewardTicks;
    public final ConfigValue<Integer> candiedEndermiteHomewardTicks;
    public final ConfigValue<Boolean> homewardCrossDimensional;

    //STRUCTURES
    public final ConfigValue<Float> rottenMonsterRoomChance;
    public final ConfigValue<Float> swampRottenMonsterRoomChance;

    //ENTITIES
    public final ConfigValue<Integer> spiderProduceCooldownTicks;
    public final ConfigValue<Float> undeadJockeySpawnChance;
    public final ConfigValue<Float> undeadJockeyMinRegionalDifficulty;
    public final ConfigValue<Boolean> nonPlayersFullChargeCleavers;

    public DungeonsDelightConfig(Runiconfig config) {
        ConfigCategory client = config.category("Client");

        spawnersEmitLivingFlames = client.value("spawnersEmitLivingFlames",
                "Should Monster Spawners should emit living flame particles?",
                true);

        monsterEffectBackground = client.value("monsterEffectBackground",
                "Should Monster Effects have a special background? Disable if you experience issues relating to effect backgrounds.",
                true);

        statusEffectTooltips = client.value("statusEffectTooltips",
                "Should items display their status effects?",
                true);

        itemEffectTooltips = client.value("itemEffectTooltips",
                "Should items display their on use effects?",
                true);

        vanillaStatusEffectTooltips = client.value("vanillaStatusEffectTooltips",
                "Should vanilla items display status effects?",
                true);

        vanillaItemEffectTooltips = client.value("vanillaItemEffectTooltips",
                "Should vanilla items display their on use effects? Some are automatically disabled when Bountiful Fares is loaded.",
                true);

        showChanceTooltips = client.value("showChanceTooltips",
                "Should items display their chance for effects?",
                true);

        invisibilityDetectionRangeTooltip = client.value("invisibilityDetectionRangeTooltip",
                "Should items that grants invisibility display it's decreased detection range? Detection range is a hidden mechanic in vanilla gameplay this just makes it not hidden.",
                true);

        ConfigCategory cleavers = config.category("Cleavers");

        cleaverAttackDamage = cleavers.floatValue("cleaverAttackDamage",
                "Sets the attack damage of Cleavers (this value is added on top of tier attack damage, for example diamond tier has a base of 3.0 attack damage).",
                2.0F, -1000.0F, 1000.0F);

        cleaverAttackSpeed = cleavers.floatValue("cleaverAttackSpeed",
                "Sets the melee attack speed of Cleavers.",
                -3.1F, -100.0F, 100.0F);

        cleaverChargeMultiplier = cleavers.floatValue("cleaverChargeMultiplier",
                "Sets the charge multiplier for Cleavers.",
                1.0F, -100.0F, 100.0F);

        cleaverPiercingDamageMultiplier = cleavers.floatValue("cleaverPiercingDamageMultiplier",
                "The amount of multiplied damage Cleavers should deal when piercing an entity.",
                0.8F, -1.0F, 1.0F);

        cleaverMissCooldownTicks = cleavers.intValue("cleaverMissCooldownTicks",
                "Sets how long Cleavers should go on cooldown after hitting the ground without piercing an entity, fully charged Cleavers have this value halved.",
                50, 0, 6000);

        cleaverDartingChargeMultiplier = cleavers.floatValue("cleaverDartingChargeMultiplier",
                "The percentage amount the Darting enchantment should change Cleaver charging time per enchantment level.",
                0.12F, -1.0F, 1.0F);

        cleaverDartingRangeDivisor = cleavers.intValue("cleaverDartingRangeDivisor",
                "The number that the Darting enchantment level should be divided by and added on as increased Throwing Range.",
                7, 1, 100);

        cleaverSerratedEffectDamage = cleavers.floatValue("cleaverSerratedEffectDamage",
                "The amount of damage the Serrated effect should deal to effected targets.",
                1.0F, 0.0F, 1024.0F);

        cleaverRicochetAssistRange = cleavers.floatValue("cleaverRicochetAssistRange",
                "The range in blocks that ricocheted cleavers will turn in the direction of target entities.",
                3.5F, 0.0F, 64.0F);

        cleaverRicochetDamageMultiplier = cleavers.floatValue("cleaverRicochetDamageMultiplier",
                "The amount of multiplied damage Cleavers should deal when ricocheting off a block with the Ricochet enchantment.",
                1.09F, -100.0F, 100.0F);

        ConfigCategory blocks = config.category("Blocks");

        bonemealableRotbulbs = blocks.value("bonemealableRotbulbs",
                "Should Rotbulbs be bonemealable.",
                false);

        itemGratesRequiresSneakingToInsert = blocks.value("itemGratesRequiresSneakingToInsert",
                "Should sneaking be required to place an item into an item grate.",
                false);

        ConfigCategory items = config.category("Items");

        increasedVanillaMealStackSize = items.value("increasedVanillaMealStackSize",
                "Whether vanilla meals such as Mushroom Stew or anything tagged in #dungeonsdelight:has_meal_stack_size should stack to 16.",
                true);

        effectsOnVanillaMeals = items.value("effectsOnVanillaMeals",
                "Whether vanilla meals such as Mushroom Stew should grant Tenacity.",
                true);

        rockCandyPickupCooldownTicks = items.intValue("rockCandyPickupCooldownTicks",
                "The amount of time in ticks that rock candies should be on cooldown when imprisoning something.",
                300, 0, 6000);

        cocktailCooldownTicks = items.intValue("cocktailCooldownTicks",
                "The amount of time in ticks that cocktails should be on cooldown after throwing.",
                100, 0, 6000);

        vexingFangsDamage = items.floatValue("vexingFangsDamage",
                "The amount of damage that Vexing Fangs will deal to targets.",
                10.0F, 0.0F, 1024);

        vexingFangsCount = items.intValue("vexingFangsCount",
                "The amount of Vexing Fangs that conjure from consuming a Candied Vex Sucker.",
                12, 0, 32);

        vexingFangsSpeed = items.intValue("vexingFangsSpeed",
                "The speed Vexing Fangs conjured from consuming a Candied Vex Sucker will snap.",
                2, 0, 100);

        vexingFangsDistance = items.doubleValue("vexingFangsDistance",
                "The distance Vexing Fangs conjured from consuming a Candied Vex Sucker will go.",
                1.0D, 0.0D, 100.0D);

        vexingFangsLifetimeTicks = items.intValue("vexingFangsLifetimeTicks",
                "The lifetime in ticks that Vexing Fangs will last.",
                22, 0, 6000);

        bubbleEyeTeaMaxAmplifier = items.intValue("bubbleEyeTeaMaxAmplifier",
                "The max amplifier that Bubble Eye Tea can increase to, in game an effect's level is its amplifier + 1.",
                3, 0, 255);

        hollowingTicks = items.intValue("hollowingTicks",
                "The amount of time in ticks that it should take to hollow an entity.",
                3600, 0, 12000);

        hollowingMaxRandomTicks = items.intValue("hollowingMaxRandomTicks",
                "The max amount of random time added to hollowing conversions.",
                2400, 0, 12000);

        ConfigCategory effects = config.category("Effects");

        putridScentRange = effects.floatValue("putridScentRange",
                "The range that entities become hostile towards a user of Putrid Scent.",
                32.0F, 0.0F, 64.0F);

        tenacityInterval = effects.intValue("tenacityInterval",
                "The rate that Tenacity heals the user, this is not any specific measure of time and increases the lower hunger the user has.",
                6, 0, 16);

        exudationBaseDamage = effects.floatValue("exudationBaseDamage",
                "The base damage of Exudation's blast.",
                12.0F, 0.0F, 1024.0F);

        exudationDamageMultiplier = effects.floatValue("exudationDamageMultiplier",
                "The multiplied amount of damage that Exudation should inflict on the user.",
                1.5F, -100.0F, 100.0F);

        exudationDamageMultiplierWhileHeartsActive = effects.value("exudationDamageMultiplierWhileHeartsActive",
                "Should Exudation's multiplied damage only occur while the player has Exudation hearts? If true it is active at all times.",
                true);

        exudationBaseRange = effects.floatValue("exudationBaseRange",
                "The base range of Exudation's blast.",
                10.0F, 0.0F, 64.0F);

        exudationInvulnerabilityTicks = effects.intValue("exudationInvulnerabilityTicks",
                "The amount of ticks that the player should be invulnerable when taking damage with Exudation, default without Exudation is 20 ticks.",
                30, 0, 200);

        decisiveBaseDamage = effects.floatValue("decisiveBaseDamage",
                "The base damage of Decisive's blast.",
                8.0F, 0.0F, 1024.0F);

        decisiveRange = effects.floatValue("decisiveRange",
                "The range of Decisive's blast.",
                2.0F, 0.0F, 64.0F);

        pouncingDistance = effects.floatValue("pouncingDistance",
                "The distance that players will pounce with Pouncing, this is not measured in blocks.",
                1.2F, 0.0F, 64.0F);

        pouncingHeight = effects.floatValue("pouncingHeight",
                "The distance that players will pounce into the air with Pouncing, this is not measured in blocks.",
                0.75F, 0.0F, 64.0F);

        pouncingCooldownTicks = effects.intValue("pouncingCooldownTicks",
                "The amount of time in ticks that it should take to be able to pounce again with Pouncing.",
                40, 0, 6000);

        diverDownMaxLengthTicks = effects.intValue("diverDownMaxLengthTicks",
                "The amount of time a user of Diver Down can endure lava, for each level of Diver Down the duration is increased by 50%.",
                200, 0, 6000);

        pouncingRavenousCooldownTicks = effects.intValue("pouncingRavenousCooldownTicks",
                "The amount of time in ticks that it should take to be able to pounce again with Pouncing while Ravenous Rush is active.",
                20, 0, 6000);

        telepotageHomewardTicks = effects.intValue("telepotageHomewardTicks",
                "The amount of ticks it should take for Telepotage to homeward the player, sets ticks Homeward grants from Telepotage.",
                140, 0, 6000);

        candiedEndermiteHomewardTicks = effects.intValue("candiedEndermiteHomewardTicks",
                "The amount of ticks it should take for Candied Endermite Suckers to homeward the player, sets ticks Homeward grants from Candied Endermite Sucker.",
                900, 0, 6000);

        homewardCrossDimensional = effects.value("homewardCrossDimensional",
                "Whether the Homeward effect can teleport the player to their spawn point cross dimensions.",
                true);

        ConfigCategory structures = config.category("Structures");

        rottenMonsterRoomChance = structures.floatValue("rottenMonsterRoomChance",
                "Chance for Rotten Monster Rooms to fail generation in most biomes?",
                0.7F, 0.0F, 1.0F);

        swampRottenMonsterRoomChance = structures.floatValue("swampRottenMonsterRoomChance",
                "Chance for Rotten Monster Rooms to fail generation in Swamps?",
                0.5F, 0.0F, 1.0F);

        ConfigCategory entities = config.category("Entities");

        spiderProduceCooldownTicks = entities.intValue("spiderProduceCooldownTicks",
                "The amount of time in ticks that it should take for Spiders to be able to produce more Spider Extract.",
                900, 0, 6000);

        undeadJockeySpawnChance = entities.floatValue("undeadJockeySpawnChance",
                "The chance for an undead jockey to spawn.",
                0.02F, 0.0F, 1.0F);

        undeadJockeyMinRegionalDifficulty = entities.floatValue("undeadJockeyMinRegionalDifficulty",
                "Minimum regional difficulty required for an undead jockey to spawn, this value is unchanged on normal difficulty but is added/subtracted by 0.75 on hard/easy.",
                2.0F, 0.0F, 6.0F);

        nonPlayersFullChargeCleavers = entities.value("nonPlayersFullChargeCleavers",
                "Whether non players get the benefits of fully charging a cleaver.",
                false);
    }
}