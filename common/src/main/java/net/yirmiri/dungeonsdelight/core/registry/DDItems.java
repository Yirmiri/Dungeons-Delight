package net.yirmiri.dungeonsdelight.core.registry;

import net.azurune.runiclib.core.platform.Services;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.common.item.AncientEggItem;
import net.yirmiri.dungeonsdelight.common.item.CleaverItem;
import net.yirmiri.dungeonsdelight.common.item.NoDestroyCreativeItem;
import net.yirmiri.dungeonsdelight.common.item.PublicRecordItem;
import net.yirmiri.dungeonsdelight.common.item.food_type.*;
import net.yirmiri.dungeonsdelight.common.item.foods.BubbleEyeTeaItem;
import net.yirmiri.dungeonsdelight.common.item.foods.EggnogItem;
import net.yirmiri.dungeonsdelight.common.item.foods.HordeFoodItem;
import net.yirmiri.dungeonsdelight.common.item.foods.TelepotageItem;
import net.yirmiri.dungeonsdelight.common.util.DDProperties;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.init.DDTiers;

import java.util.function.Supplier;

public class DDItems {
    //BLOCK ITEM
    public static final Supplier<Item> MONSTER_POT = register("monster_pot", () -> new BlockItem(DDBlocks.MONSTER_POT.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> DUNGEON_STOVE = register("dungeon_stove", () -> new BlockItem(DDBlocks.DUNGEON_STOVE.get(), DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> TELEPOTAGE_BLOCK = register("telepotage_block", () -> new BlockItem(DDBlocks.TELEPOTAGE_BLOCK.get(), DDProperties.ItemP.GENERIC_MONSTER));
    public static final Supplier<Item> LIVING_TORCH = register("living_torch", () -> new StandingAndWallBlockItem(DDBlocks.LIVING_TORCH.get(), DDBlocks.WALL_LIVING_TORCH.get(), DDProperties.ItemP.GENERIC_UNCOMMON, Direction.DOWN));

    //MISC
    public static final Supplier<Item> LOGO_ITEM = register("logo_item", () -> new Item(DDProperties.ItemP.LOGO_ITEM));
    //TODO: Music Discs will need datadrive in 1.21
    public static final Supplier<Item> MUSIC_DISC_MALADY = register("music_disc_malady", () -> new PublicRecordItem(6, DDSounds.MALADY.get(), DDProperties.ItemP.GENERIC_MONSTER_1, 382));
    public static final Supplier<Item> MUSIC_DISC_MALADY_B_SIDE = register("music_disc_malady_b_side", () -> new PublicRecordItem(13, DDSounds.MALADY_B.get(), DDProperties.ItemP.GENERIC_MONSTER_1, 396));

    //SPAWN EGGS
    public static final Supplier<Item> CAMEL_HUSK_SPAWN_EGG = register("camel_husk_spawn_egg", () -> spawnEggItem(DDEntities.CAMEL_HUSK, 0x29241f, 0x625644));

    //TOOLS
    public static final Supplier<Item> FLINT_CLEAVER = register("flint_cleaver", () -> new CleaverItem(0.66F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), DDTiers.FLINT, DDProperties.ItemP.FLINT));
    public static final Supplier<Item> IRON_CLEAVER = register("iron_cleaver", () -> new CleaverItem(0.75F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.IRON, DDProperties.ItemP.IRON));
    public static final Supplier<Item> GOLDEN_CLEAVER = register("golden_cleaver", () -> new CleaverItem(1.75F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.GOLD, DDProperties.ItemP.GOLD));
    public static final Supplier<Item> DIAMOND_CLEAVER = register("diamond_cleaver", () -> new CleaverItem(1.0F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.DIAMOND, DDProperties.ItemP.DIAMOND));
    public static final Supplier<Item> NETHERITE_CLEAVER = register("netherite_cleaver", () -> new CleaverItem(1.25F, DungeonsDelight.CONFIG.getCleaverAttackDamage(), DungeonsDelight.CONFIG.getCleaverAttackSpeed(), Tiers.NETHERITE, DDProperties.ItemP.NETHERITE));

    //MATERIALS
    public static final Supplier<Item> STAINED_SCRAP = register("stained_scrap", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));
    public static final Supplier<Item> STAINED_SCRAP_FRAGMENT = register("stained_scrap_fragment", () -> new Item(DDProperties.ItemP.GENERIC_UNCOMMON));

    //INGREDIENTS
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
    public static final Supplier<Item> CREEPERILLA_SQUIB = register("creeperilla_squib", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).build()))
    );
    public static final Supplier<Item> SLIME_NOODLES = register("slime_noodles", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build()))
    );
    public static final Supplier<Item> MAGMARONI = register("magmaroni", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build()))
    );
    public static final Supplier<Item> GHAST_TENTACLE = register("ghast_tentacle", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).meat().build()))
    );
    public static final Supplier<Item> GHAST_CALAMARI = register("ghast_calamari", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).meat().fast().build()))
    );
    public static final Supplier<Item> COOKED_GHAST_CALAMARI = register("cooked_ghast_calamari", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).meat().fast().build()))
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
    public static final Supplier<Item> RAVAGER_HAUNCH = register("ravager_haunch", () -> new HordeFoodItem(true, 48,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(1.0F).meat()
                    .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0), 1.0F).build()))
    );
    public static final Supplier<Item> SCULK_POLYP = register("sculk_polyp", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANCIENT_EGG = register("ancient_egg", () -> new AncientEggItem(new Item.Properties()));

    public static final Supplier<Item> CLEAVED_ANCIENT_EGG = register("cleaved_ancient_egg", () -> new SculkFoodItem(1, 0.1F,
            false, new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).build()))
    );
    public static final Supplier<Item> SCULK_MAYONNAISE = register("sculk_mayonnaise", () -> new DDFoodItem(false, SoundEvents.HONEY_DRINK, UseAnim.DRINK, 40,
            new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(new FoodProperties.Builder().build()))
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
    public static final Supplier<Item> ROTBULB = register("rotbulb", () -> new Item(DDProperties.ItemP.GENERIC_MONSTER));
    public static final Supplier<Item> GUNK = register("gunk", () -> new ItemNameBlockItem(DDBlocks.GUNK.get(), DDProperties.ItemP.GENERIC_MONSTER));

    public static final Supplier<Item> SLICORICE = register("slicorice", () -> new DDFoodItem(false,
            new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.2F).fast().build()))
    );

    public static final Supplier<Item> SPIDER_EXTRACT = register("spider_extract", () -> new DDFoodItem(
            true, SoundEvents.GENERIC_DRINK, UseAnim.DRINK, new Item.Properties()
                    .craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)
                    .food(new FoodProperties.Builder().alwaysEat()
                            .effect(new MobEffectInstance(MobEffects.POISON, 240, 1), 1.0F)
                            .build()))
    );

    //-------------------------NON-TIERED FOODS-------------------------
    public static final Supplier<Item> AMETHYST_ROCK_CANDY = register("amethyst_rock_candy", () -> new NoDestroyCreativeItem(
            new Item.Properties()
                    .craftRemainder(Items.STICK).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(6).saturationMod(0.4F)
                            .build()))
    );

    public static final Supplier<Item> BLACK_APPLE = register("black_apple", () -> new DDFoodItem(
            true, new Item.Properties()
                    .rarity(DDRarities.MONSTER)
                    .food(new FoodProperties.Builder()
                            .nutrition(4).saturationMod(1.2F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.DEBRIDEMENT.get(), 100, 1), 1.0F)
                            .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 2400, 0), 1.0F)
                    .build()))
    );

    public static final Supplier<Item> SCULK_APPLE = register("sculk_apple", () -> new SculkFoodItem(1, 0.2F,
            false, 16, new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(4).saturationMod(0.3F).alwaysEat()
                    .build()))
    );

    //-------------------------TIER I FOODS (0:00-3:59)-------------------------
    public static final Supplier<Item> GHOULASH = register("ghoulash", () -> new SlimeFoodItem(
            0.24F, true, new Item.Properties()
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.BOWL).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8).saturationMod(0.6F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 4200, 0), 1.0F)
                            .build()))
    );

    public static final Supplier<Item> FOUL_SKEWER = register("foul_skewer", () -> new DDFoodItem(
            true, new Item.Properties()
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.BONE).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8).saturationMod(0.6F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 3000, 0), 1.0F)
                            .build()))
    );

    public static final Supplier<Item> SPIDER_TANGHULU = register("spider_tanghulu", () -> new DDFoodItem(
            true, new Item.Properties()
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.BONE).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(6).saturationMod(0.8F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 2400, 0), 1.0F)
                            .build()))
    );

    public static final Supplier<Item> SPIDER_PIE = register("spider_pie", () -> new DDFoodItem(
            true, new Item.Properties()
            .rarity(DDRarities.MONSTER)
            .food(new FoodProperties.Builder()
                    .nutrition(8).saturationMod(0.3F).alwaysEat()
                    .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 3000, 0), 1.0F)
                    .build()))
    );

    public static final Supplier<Item> GHAST_ROLL = register("ghast_roll", () -> new DDFoodItem(
            true, new Item.Properties()
            .rarity(DDRarities.MONSTER)
            .food(new FoodProperties.Builder()
                    .nutrition(6).saturationMod(0.6F).alwaysEat()
                    .effect(new MobEffectInstance(DDEffects.DEBRIDEMENT.get(), 2400, 0), 1.0F)
                    .effect(new MobEffectInstance(DDEffects.VORACITY.get(), 2400, 0), 1.0F)
                    .build()))
    );

    //-------------------------TIER II FOODS (4:00-7:59 or slightly potent)-------------------------
    public static final Supplier<Item> CANDIED_SILVERFISH_SUCKER = register("candied_silverfish_sucker", () -> new DDFoodItem(
            true, new Item.Properties() //todo biteable
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.STICK).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8).saturationMod(0.6F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3600, 2), 1.0F)
                            .build()))
    );

    public static final Supplier<Item> CANDIED_VEX_SUCKER = register("candied_vex_sucker", () -> new DDFoodItem(
            true, new Item.Properties() //todo biteable and concept what it will do
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.STICK).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8).saturationMod(0.6F).alwaysEat()
                            .build()))
    );

    public static final Supplier<Item> SALMAGUNDI = register("salmagundi", () -> new DDFoodItem(
            true, new Item.Properties()
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.BOWL).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(7).saturationMod(0.9F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 6000, 0), 1.0F)
                            .build()))
    );

    public static final Supplier<Item> SILVERFISH_FRIED_RICE = register("silverfish_fried_rice", () -> new DDFoodItem(
            true, new Item.Properties()
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.BOWL).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(10).saturationMod(0.8F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 6000, 0), 1.0F)
                            .build()))
    );

    public static final Supplier<Item> GUNPOWDER_BAKED_ARACHNID = register("gunpowder_baked_arachnid", () -> new CreeperFoodItem(
            true, new Item.Properties()
                    .rarity(DDRarities.MONSTER).craftRemainder(Items.BOWL).stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(8).saturationMod(0.6F).alwaysEat()
                            .effect(new MobEffectInstance(DDEffects.POUNCING.get(), 2400, 0), 1.0F)
                            .build()))
    );

    public static final Supplier<Item> DYNAMITE_ROLL = register("dynamite_roll", () -> new CreeperFoodItem(
            true, new Item.Properties()
            .rarity(DDRarities.MONSTER).stacksTo(16)
            .food(new FoodProperties.Builder()
                    .nutrition(6).saturationMod(0.6F).alwaysEat()
                    .effect(new MobEffectInstance(DDEffects.BURROW_GUT.get(), 3000, 0), 1.0F)
                    .build()))
    );

    public static final Supplier<Item> TARO_MILK_TEA = register("taro_milk_tea", () -> new DDFoodItem(
            true, new Item.Properties()
            .rarity(DDRarities.MONSTER).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)
            .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .effect(new MobEffectInstance(DDEffects.EXUDATION.get(), 2400, 2), 1.0F)
                    .build()))
    );

    public static final Supplier<Item> BUBBLE_EYE_TEA = register("bubble_eye_tea", () -> new BubbleEyeTeaItem(
            false, new Item.Properties()
            .rarity(DDRarities.MONSTER).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)
            .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .build()))
    );

    public static final Supplier<Item> EGGNOG = register("eggnog", () -> new EggnogItem(
            false, new Item.Properties()
            .rarity(DDRarities.MONSTER).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)
            .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .build()))
    );

    //-------------------------TIER III FOODS (8:00 and above or potent)-------------------------
    public static final Supplier<Item> TELEPOTAGE = register("telepotage", () -> new TelepotageItem(
            true, new Item.Properties()
            .rarity(DDRarities.MONSTER).craftRemainder(Items.BOWL).stacksTo(16)
            .food(new FoodProperties.Builder()
                    .nutrition(6).saturationMod(0.7F).alwaysEat()
                    .effect(new MobEffectInstance(DDEffects.HOMEWARD.get(), DungeonsDelight.CONFIG.getHomewardTicks(), 0), 1.0F)
                    .build()))
    );

    public static Supplier<Item> register(String id, Supplier<Item> supplier) {
        return Services.REGISTRY.registerItem(DungeonsDelight.MOD_ID, id, supplier);
    }

    public static <T extends Mob> SpawnEggItem spawnEggItem(Supplier<EntityType<T>> entity, int mainColor, int highlightColor) {
        return Services.REGISTRY.registerSpawnEgg(entity, mainColor, highlightColor);
    }

    public static void load() {
    }
}
