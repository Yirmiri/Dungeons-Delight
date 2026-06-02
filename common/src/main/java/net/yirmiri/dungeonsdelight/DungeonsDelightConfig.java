package net.yirmiri.dungeonsdelight;

public class DungeonsDelightConfig { //todo comments whenever runiconfig switches to become a toml
    //CLIENT
    private boolean spawners_emit_living_flames = true;
    //Whether Monster Spawners should emit living flame particles (default: true)
    public boolean getSpawnersEmitLivingFlames() {return spawners_emit_living_flames;}

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

    //EFFECTS
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
}