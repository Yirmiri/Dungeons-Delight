package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.LanguageProvider;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.registry.DDBlocks;
import net.yirmiri.dungeonsdelight.registry.DDDamageTypes;
import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.registry.DDItems;
import net.yirmiri.dungeonsdelight.util.DDTags;

public class DDLangGen extends LanguageProvider {
    public DDLangGen(PackOutput output) {
        super(output, DungeonsDelight.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //MISC
        add("dungeonsdelight_tab", "Dungeon's Delight");
        add("farmersdelight.container.monster_pot", "Monster Pot");
        add("dungeonsdelight.jei.monster_cooking", "Monster Cooking");

        //BLOCKS
        add(DDBlocks.DUNGEON_STOVE.get(), "Dungeon Stove");
        add(DDBlocks.MONSTER_POT.get(), "Monster Pot");

        //ITEMS
        add(DDItems.LOGO_ITEM.get(), "Logo Item");
        add(DDItems.SLIME_NOODLES.get(), "Slime Noodles");
        add(DDItems.SLIME_SLAB.get(), "Slime Slab");
        add(DDItems.GHOULASH.get(), "Ghoulash");
        add(DDItems.AMETHYST_ROCK_CANDY.get(), "Amethyst Rock Candy");
        add(DDItems.CANDIED_SILVERFISH_SUCKER.get(), "Candied Silverfish Sucker");
        add(DDItems.CANDIED_VEX_SUCKER.get(), "Candied Vex Sucker");
        add(DDItems.SILVERFISH_THORAX.get(), "Silverfish Thorax");

        //EFFECTS
        add(DDEffects.BURROW_GUT.get(), "Burrow Gut");
        add(DDEffects.EXUDATION.get(), "Exudation");
        add(DDEffects.ROTGUT.get(), "Rotgut");
        add(DDEffects.POUNCING.get(), "Pouncing");
        add(DDEffects.VORACITY.get(), "Voracity");
        add(DDEffects.TENACITY.get(), "Tenacity");

        //DAMAGE
        addDamageType(DDDamageTypes.DUNGEON_STOVE_BURN, "%1$s was monstrously grilled to perfection", "%1$s was thrown on the grill by The Monstrous Chef %2$s");
        addDamageType(DDDamageTypes.SKULL_HEART_BLAST, "%1$s was melted by a monstrous blast", "%1$s was melted by the monstrous blast of %2$s");
    }

    private void addDamageType(ResourceKey<DamageType> type, String deathMsg, String killMsg) {
        add(type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey(), deathMsg);
        add("death.attack." + type.location().toLanguageKey() + ".player", killMsg);
    }
}
