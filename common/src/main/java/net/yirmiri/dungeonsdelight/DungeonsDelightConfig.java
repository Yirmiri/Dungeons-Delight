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
    //The percentage amount the Darting enchantment should change Cleaver charging time per enchantment level. Do not set as 0 (default: 0.12F)
    public float getCleaverDartingChargeMultiplier() {return cleaver_darting_charge_multiplier;}

    private float cleaver_serrated_effect_damage = 1.0F;
    //The amount of damage the Serrated effect should deal to effected targets (default: 1.0F)
    public float getCleaverSerratedEffectDamage() {return cleaver_serrated_effect_damage;}

    private float cleaver_ricochet_damage_multiplier = 1.33F;
    //The amount of multiplied damage Cleavers should deal when ricocheting off a block with the Ricochet enchantment (default: 1.33F)
    public float getCleaverRicochetDamageMultiplier() {return cleaver_ricochet_damage_multiplier;}
}