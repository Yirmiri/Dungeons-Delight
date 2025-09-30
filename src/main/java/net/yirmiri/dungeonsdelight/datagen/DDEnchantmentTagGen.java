package net.yirmiri.dungeonsdelight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

import java.util.concurrent.CompletableFuture;

public class DDEnchantmentTagGen extends EnchantmentTagsProvider {
    public DDEnchantmentTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DungeonsDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        appendNonTreasure();
        appendTreasure();
        appendTooltipOrder();
        appendTradeable();
    }

    private void appendTooltipOrder() { //ARE WE SERIOUS RIGHT NOW?, TOOLTIP ORDERING IS CONTROLLED BY A F#$!ING TAG?
        tag(EnchantmentTags.TOOLTIP_ORDER)
                .add(DDEnchantments.RICOCHET)
                .add(DDEnchantments.SERRATED_STRIKE)
                .add(DDEnchantments.LIFE_GRASP)
        ;
    }

    private void appendNonTreasure() {
        tag(EnchantmentTags.NON_TREASURE)
                .add(DDEnchantments.SERRATED_STRIKE)
        ;
    }

    private void appendTreasure() {
        tag(EnchantmentTags.TREASURE)
                .add(DDEnchantments.RICOCHET)
                //.add(DDEnchantments.LIFE_GRASP)
        ;
    }

    private void appendTradeable() {
        tag(EnchantmentTags.TRADEABLE)
                .add(DDEnchantments.RICOCHET)
                .add(DDEnchantments.SERRATED_STRIKE)
        ;
    }
}
