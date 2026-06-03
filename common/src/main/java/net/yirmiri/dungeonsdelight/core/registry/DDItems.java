package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.item.NoBreakCreativeItem;
import net.yirmiri.dungeonsdelight.common.item.PublicRecordItem;
import net.yirmiri.dungeonsdelight.common.item.food_type.DDFoodItem;
import net.yirmiri.dungeonsdelight.common.item.food_type.RawCreeperFoodItem;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.init.DDTiers;

import java.util.function.Supplier;

public class DDItems {
    //BLOCK ITEM
    public static final Supplier<Item> MONSTER_POT = register("monster_pot", () -> new BlockItem(DDBlocks.MONSTER_POT.get(), DDProperties.ItemP.GENERIC_UNCOMMON));

    //MISC
    public static final Supplier<Item> LOGO_ITEM = register("logo_item", () -> new Item(DDProperties.ItemP.LOGO_ITEM));
    //TODO: Music Discs will need datadrive in 1.21
    public static final Supplier<Item> MUSIC_DISC_MALADY = register("music_disc_malady", () -> new PublicRecordItem(6, DDSounds.MALADY.get(), DDProperties.ItemP.GENERIC_MONSTER_1, 382));
    public static final Supplier<Item> MUSIC_DISC_MALADY_B_SIDE = register("music_disc_malady_b_side", () -> new PublicRecordItem(13, DDSounds.MALADY_B.get(), DDProperties.ItemP.GENERIC_MONSTER_1, 396));

    //TOOLS
    public static final Supplier<Item> FLINT_CLEAVER = register("flint_cleaver", () -> new CleaverItem(0.66F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), DDTiers.FLINT, DDProperties.ItemP.FLINT));
    public static final Supplier<Item> IRON_CLEAVER = register("iron_cleaver", () -> new CleaverItem(0.75F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.IRON, DDProperties.ItemP.IRON));
    public static final Supplier<Item> GOLDEN_CLEAVER = register("golden_cleaver", () -> new CleaverItem(1.75F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.GOLD, DDProperties.ItemP.GOLD));
    public static final Supplier<Item> DIAMOND_CLEAVER = register("diamond_cleaver", () -> new CleaverItem(1.0F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.DIAMOND, DDProperties.ItemP.DIAMOND));
    public static final Supplier<Item> NETHERITE_CLEAVER = register("netherite_cleaver", () -> new CleaverItem(1.25F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.NETHERITE, DDProperties.ItemP.NETHERITE));

    //MATERIALS
    public static final Supplier<Item> STAINED_SCRAP = register("stained_scrap", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> STAINED_SCRAP_FRAGMENT = register("stained_scrap_fragment", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));

    //INGREDIENT FOODS
    public static final Supplier<Item> SPIDER_MEAT = register("spider_meat", () -> new DDFoodItem(true,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).meat()
                    .effect(new MobEffectInstance(MobEffects.POISON, 240, 0), 0.2F).build()))
    );
    public static final Supplier<Item> COOKED_SPIDER_MEAT = register("cooked_spider_meat", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).meat().build()))
    );
    public static final Supplier<Item> ROTTEN_TRIPE = register("rotten_tripe", () -> new DDFoodItem(true,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).meat().fast()
                    .effect(new MobEffectInstance(MobEffects.HUNGER, 200, 0), 0.2F).build()))
    );
    public static final Supplier<Item> CREEPERILLA = register("creeperilla", () -> new RawCreeperFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).build()))
    );
    public static final Supplier<Item> SLIME_NOODLES = register("slime_noodles", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build()))
    );
    public static final Supplier<Item> GHAST_TENTACLE = register("ghast_tentacle", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).meat().build()))
    );
    public static final Supplier<Item> SILVERFISH_ABDOMEN = register("silverfish_abdomen", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).fast().meat().build()))
    );
    public static final Supplier<Item> SNIFFER_SHANK = register("sniffer_shank", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).meat().build()))
    );
    public static final Supplier<Item> COOKED_SNIFFER_SHANK = register("cooked_sniffer_shank", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).meat().build()))
    );

    public static final Supplier<Item> BLEET = register("bleet", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).build()))
    );
    public static final Supplier<Item> BLEET_SEEDS = register("bleet_seeds", () -> new ItemNameBlockItem(DDBlocks.BLEETS.get(), DDProperties.ItemP.GENERIC));
    public static final Supplier<Item> ENDELVE = register("endelve", () -> new ItemNameBlockItem(DDBlocks.ENDELVES.get(),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).build()))
    );
    public static final Supplier<Item> MANALLIUM = register("manallium", () -> new ItemNameBlockItem(DDBlocks.MANALLIUMS.get(),
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build()))
    );

    //-------------------------NON-TIERED FOODS-------------------------
    public static final Supplier<Item> AMETHYST_ROCK_CANDY = register("amethyst_rock_candy", () -> new NoBreakCreativeItem(
            new Item.Properties()
                    .craftRemainder(Items.STICK).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(6).saturationMod(0.4F)
                            .build()))
    );

    //-------------------------TIER I FOODS (0:00-3:59)-------------------------

    //-------------------------TIER II FOODS (4:00-7:59 or slightly potent)-------------------------
    public static final Supplier<Item> CANDIED_SILVERFISH_SUCKER = register("candied_silverfish_sucker", () -> new DDFoodItem(true,
            new Item.Properties()
                    .rarity(DDRarities.MONSTER)
                    .craftRemainder(Items.STICK)
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8).saturationMod(0.6F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 2), 1.0F)
                            .build()))
    );
    public static final Supplier<Item> CANDIED_VEX_SUCKER = register("candied_vex_sucker", () -> new DDFoodItem(true,
            new Item.Properties()
                    .rarity(DDRarities.MONSTER)
                    .craftRemainder(Items.STICK)
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8).saturationMod(0.6F).alwaysEat()
                            .build()))
    );

    //-------------------------TIER III FOODS (8:00 and above or potent)-------------------------

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static void load() {
    }
}
