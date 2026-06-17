package net.yirmiri.dungeonsdelight;

public class DungeonsDelightConfig { //todo comments whenever runiconfig switches to become a toml
    //CLIENT
    private boolean spawners_emit_living_flames = true;
    //Should Monster Spawners should emit living flame particles? (default: true)
    public boolean getSpawnersEmitLivingFlames() {return spawners_emit_living_flames;}

    private boolean monster_effect_background = true;
    //Should Monster Effects have a special background? Disable if you experience issues relating to effect backgrounds (default: true)
    public boolean getMonsterEffectBackground() {return monster_effect_background;}

    private boolean status_effect_tooltips = true;
    //Should items display their status effects? (default: true)
    public boolean getStatusEffectTooltips() {return status_effect_tooltips;}

    private boolean item_effect_tooltips = true;
    //Should items display their on use effects? (default: true)
    public boolean getItemEffectTooltips() {return item_effect_tooltips;}

    private boolean vanilla_status_effect_tooltips = true;
    //Should vanilla items display status effects? (default: true)
    public boolean getVanillaStatusEffectTooltips() {return vanilla_status_effect_tooltips;}

    private boolean vanilla_item_effect_tooltips = true;
    //Should vanilla items display their on use effects? Some are automatically disabled when Bountiful Fares is loaded (default: true)
    public boolean getVanillaItemEffectTooltips() {return vanilla_item_effect_tooltips;}

    private boolean show_chance_tooltips = true;
    //Should items display their chance for effects? (default: true)
    public boolean getShowChanceTooltips() {return show_chance_tooltips;}

    //CLEAVERS
    private float cleaver_attack_damage = 2.0F;
    //Sets the added attack damage of Cleavers (this value is added on top of tier attack damage, for example diamond tier has a base of 3.0 attack damage) (default: 2.0F)
    public float getCleaverAttackDamage() {return cleaver_attack_damage;}

    private float cleaver_attack_speed = -3.0F;
    //Sets the melee attack speed of Cleavers (default: -3.0F)
    public float getCleaverAttackSpeed() {return cleaver_attack_speed;}

    private float cleaver_piercing_damage_multiplier = 0.8F;
    //The amount of multiplied damage Cleavers should deal when piercing an entity (default 0.8F)
    public float getCleaverPiercingDamageMultiplier() {return cleaver_piercing_damage_multiplier;}

    private int cleaver_miss_cooldown_ticks = 50;
    //How long Cleavers should go on cooldown after hitting the ground without piercing an entity, fully charged Cleavers have this value halved (default: 50)
    public int getCleaverMissCooldownTicks() {return cleaver_miss_cooldown_ticks;}

    private float cleaver_darting_charge_multiplier = 0.12F;
    //The percentage amount the Darting enchantment should change Cleaver charging time per enchantment level, [do not set as 0] (default: 0.12F)
    public float getCleaverDartingChargeMultiplier() {return cleaver_darting_charge_multiplier;}

    private float cleaver_serrated_effect_damage = 1.0F;
    //The amount of damage the Serrated effect should deal to effected targets (default: 1.0F)
    public float getCleaverSerratedEffectDamage() {return cleaver_serrated_effect_damage;}

    private float cleaver_ricochet_damage_multiplier = 1.33F;
    //The amount of multiplied damage Cleavers should deal when ricocheting off a block with the Ricochet enchantment (default: 1.33F)
    public float getCleaverRicochetDamageMultiplier() {return cleaver_ricochet_damage_multiplier;}

    //BLOCKS
    private boolean item_grates_requires_sneaking_to_insert = false;
    //Should sneaking be required to place an item into an item grate (default: false)
    public boolean getItemGrateRequiresSneakingToInsert() {return item_grates_requires_sneaking_to_insert;}

    //ITEMS
    private int rock_candy_pickup_cooldown_ticks = 300;
    //The amount of time in ticks that rock candies should be on cooldown when imprisoning something (default: 300)
    public int getRockCandyPickupCooldownTicks() {return rock_candy_pickup_cooldown_ticks;}

    private int hollowing_ticks = 3600;
    //The amount of time in ticks that it should take to hollow an entity (default: 3600)
    public int getHollowingTicks() {return hollowing_ticks;}

    private int hollowing_max_random_ticks = 2400;
    //The max amount of random time added to hollowing conversions (default: 2400)
    public int getHollowingMaxRandomTicks() {return hollowing_max_random_ticks;}

    //EFFECTS
    private float putrid_scent_range = 32.0F;
    //The range that entities become hostile towards a user of Putrid Scent (default: 32.0F)
    public float getPutridScentRange() {return putrid_scent_range;}

    private int tenacity_interval = 6;
    //The rate that Tenacity heals the user, this is not any specific measure of time and increases the lower hunger the user has (default: 6)
    public int getTenacityInterval() {return tenacity_interval;}

    private float exudation_base_damage = 12.0F;
    //The base damage of Exudation's blast (default: 12.0F)
    public float getExudationBaseDamage() {return exudation_base_damage;}

    private float exudation_damage_multiplier = 1.5F;
    //The multiplied amount of damage that Exudation should inflict on the user (default: 1.5F)
    public float getExudationDamageMultiplier() {return exudation_damage_multiplier;}

    private boolean exudation_damage_multipler_while_hearts_active = true;
    //Should Exudation's multiplied damage only occur while the player has Exudation hearts? If true it is active at all times (default: true)
    public boolean getExudationDamageMultiplierWhileHeartsActive() {return exudation_damage_multipler_while_hearts_active;}

    private float exudation_base_range = 10.0F;
    //The base range of Exudation's blast (default: 10.0F)
    public float getExudationBaseRange() {return exudation_base_range;}

    private int exudation_invulnerability_ticks = 30;
    //The amount of ticks that the player should be invulnerable when taking damage with Exudation, default without Exudation is 20 ticks (default: 30)
    public int getExudationInvulnerabilityTicks() {return exudation_invulnerability_ticks;}

    //ENTITIES
    private boolean non_players_full_charge_cleavers = false;
    //Whether non players get the benefits of fully charging a cleaver (default: false)
    public boolean getNonPlayersFullChargeCleavers() {return non_players_full_charge_cleavers;}
}