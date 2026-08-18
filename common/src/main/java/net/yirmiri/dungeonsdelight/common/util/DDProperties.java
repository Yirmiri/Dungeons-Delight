package net.yirmiri.dungeonsdelight.common.util;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.yirmiri.dungeonsdelight.common.block.DungeonStoveBlock;
import net.yirmiri.dungeonsdelight.common.block.crops.SoulPeppersCropBlock;
import net.yirmiri.dungeonsdelight.core.init.DDRarities;
import net.yirmiri.dungeonsdelight.core.registry.DDSoundTypes;

public class DDProperties {
    public static class BlockP {
        //METAL DOOR + TRAPDOOR (replaces copper door copying from 1.21)
        private static final BlockBehaviour.Properties METAL_DOOR = BlockBehaviour.Properties.of()
                .mapColor(Blocks.IRON_BLOCK.defaultMapColor()).strength(3.0F, 6.0F).noOcclusion().requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY);
        private static final BlockBehaviour.Properties METAL_TRAPDOOR = BlockBehaviour.Properties.of()
                .mapColor(Blocks.IRON_BLOCK.defaultMapColor()).strength(3.0F, 6.0F).requiresCorrectToolForDrops().noOcclusion().isValidSpawn((state, getter, pos, type) -> false);

        //MISC
        public static final BlockBehaviour.Properties GENERIC = BlockBehaviour.Properties.copy(Blocks.STONE);
        public static final BlockBehaviour.Properties MONSTER_POT = BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(s -> 9).strength(1.0F, 0.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties CLEAVING_BOARD = BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASS).strength(1.0F);
        public static final BlockBehaviour.Properties BAMBOO_CLEAVING_BOARD = CLEAVING_BOARD.mapColor(MapColor.COLOR_YELLOW);
        public static final BlockBehaviour.Properties WAVY_GOO = BlockBehaviour.Properties.copy(Blocks.DIRT).sound(SoundType.HONEY_BLOCK);
        public static final BlockBehaviour.Properties SCULK_MAYO = BlockBehaviour.Properties.copy(Blocks.SCULK).sound(SoundType.HONEY_BLOCK);
        public static final BlockBehaviour.Properties SCULK_EGGS = BlockBehaviour.Properties.copy(Blocks.SCULK).strength(0.8F).randomTicks();
        public static final BlockBehaviour.Properties DUNGEON_STOVE = BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> (state.getValue(DungeonStoveBlock.LIT) ? 10 : 0)).noOcclusion();
        public static final BlockBehaviour.Properties GUNK = BlockBehaviour.Properties.copy(Blocks.SCULK_VEIN).sound(SoundType.HONEY_BLOCK);
        public static final BlockBehaviour.Properties SPAWNER = BlockBehaviour.Properties.copy(Blocks.SPAWNER).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties COBBLED = BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).strength(3.0F, 6.0F).sound(DDSoundTypes.SOFT_ROCK);
        public static final BlockBehaviour.Properties SPIKE_TRAP = BlockBehaviour.Properties.copy(Blocks.STONE).speedFactor(0.58F).jumpFactor(0.90F).sound(DDSoundTypes.STAINED_SCRAP).noCollission().strength(6.0F, 12.0F);
        public static final BlockBehaviour.Properties TELEPOTAGE = BlockBehaviour.Properties.copy(Blocks.STONE).sound(DDSoundTypes.STAINED_SCRAP);

        //CROPS
        public static final BlockBehaviour.Properties TERROR_PRETA = BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.ROOTED_DIRT);
        public static final BlockBehaviour.Properties BLEETS = BlockBehaviour.Properties.copy(Blocks.BEETROOTS);
        public static final BlockBehaviour.Properties MANALLIUMS = BlockBehaviour.Properties.copy(Blocks.CARROTS);
        public static final BlockBehaviour.Properties ENDELVES = BlockBehaviour.Properties.copy(Blocks.WHEAT);
        public static final BlockBehaviour.Properties SOUL_PEPPERS = BlockBehaviour.Properties.copy(Blocks.NETHER_WART).sound(SoundType.ROOTS).lightLevel(s -> SoulPeppersCropBlock.emitsLight(s) ? 0 : 7);
        public static final BlockBehaviour.Properties ROTBULB = BlockBehaviour.Properties.copy(Blocks.PITCHER_CROP);
        public static final BlockBehaviour.Properties WILD_ROTBULB = BlockBehaviour.Properties.copy(Blocks.PITCHER_PLANT);
        public static final BlockBehaviour.Properties ROTTEN_CROP = BlockBehaviour.Properties.copy(Blocks.WHEAT);
        public static final BlockBehaviour.Properties ROTTEN_CARROTS = BlockBehaviour.Properties.copy(Blocks.CARROTS);
        public static final BlockBehaviour.Properties ROTTEN_POTATOES = BlockBehaviour.Properties.copy(Blocks.POTATOES);
        public static final BlockBehaviour.Properties ROTTEN_BEETS = BlockBehaviour.Properties.copy(Blocks.BEETROOTS);
        public static final BlockBehaviour.Properties WILD_CROP = BlockBehaviour.Properties.copy(Blocks.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HANGING_ROOTS);

        //LIVING
        public static final BlockBehaviour.Properties LIVING_FIRE = BlockBehaviour.Properties.copy(Blocks.SOUL_FIRE).lightLevel(s -> 12).mapColor(DyeColor.YELLOW);
        public static final BlockBehaviour.Properties LIVING_CANDLE = BlockBehaviour.Properties.copy(Blocks.CANDLE).sound(SoundType.LANTERN).lightLevel(s -> s.getValue(BlockStateProperties.LIT) ? 9 : 0).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties LIVING_CAMPFIRE = BlockBehaviour.Properties.copy(Blocks.CAMPFIRE).requiresCorrectToolForDrops().lightLevel(s -> s.getValue(BlockStateProperties.LIT) ? 12 : 0).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties LIVING_TORCH = BlockBehaviour.Properties.copy(Blocks.TORCH).lightLevel(s -> 12).sound(SoundType.LANTERN);
        public static final BlockBehaviour.Properties LIVING_LANTERN = BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(s -> 12);

        //STAINED SCRAP
        public static final BlockBehaviour.Properties STAINED = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0F, 9.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_BARS = BlockBehaviour.Properties.copy(Blocks.IRON_BARS).strength(6.0F, 9.0F).sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_GRATE = BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(6.0F, 9.0F).noOcclusion().sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_DOOR = METAL_DOOR.sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_TRAPDOOR = METAL_TRAPDOOR.sound(DDSoundTypes.STAINED_SCRAP);
        public static final BlockBehaviour.Properties STAINED_SCRAP_CHAIN = BlockBehaviour.Properties.copy(Blocks.CHAIN);
        public static final BlockBehaviour.Properties ENAMELED_GLASS = BlockBehaviour.Properties.copy(Blocks.GLASS).strength(6.0F, 9.0F);

        //WORMWOOD
        public static final BlockBehaviour.Properties WORMROOT_TENDRILS = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(0.5F).mapColor(MapColor.TERRACOTTA_PURPLE).noOcclusion().noCollission();
        public static final BlockBehaviour.Properties WORMWOOD = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).strength(1.0F).explosionResistance(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMOUTH = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS).randomTicks().strength(9.0F).explosionResistance(9.0F).mapColor(MapColor.TERRACOTTA_PURPLE).pushReaction(PushReaction.IGNORE);
        public static final BlockBehaviour.Properties WORMWOOD_DOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_DOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_TRAPDOOR = BlockBehaviour.Properties.copy(Blocks.CRIMSON_TRAPDOOR).strength(1.5F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_BUTTON = BlockBehaviour.Properties.copy(Blocks.CRIMSON_BUTTON).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
        public static final BlockBehaviour.Properties WORMWOOD_PRESSURE_PLATE = BlockBehaviour.Properties.copy(Blocks.CRIMSON_PRESSURE_PLATE).strength(0.25F).mapColor(MapColor.TERRACOTTA_PURPLE);
    }

    public static class ItemP {
        //MISC
        public static final Item.Properties GENERIC = new Item.Properties();
        public static final Item.Properties GENERIC_MONSTER = new Item.Properties().rarity(DDRarities.MONSTER);
        public static final Item.Properties GENERIC_UNCOMMON = new Item.Properties().rarity(Rarity.UNCOMMON);
        public static final Item.Properties LOGO_ITEM = new Item.Properties().rarity(DDRarities.MONSTER)
                .food(new FoodProperties.Builder()
                        .nutrition(-4).saturationMod(0.0F).alwaysEat().fast()
                        .build())
                ;
        public static final Item.Properties GENERIC_MONSTER_1 = new Item.Properties().rarity(DDRarities.MONSTER).stacksTo(1);

        //TOOL
        public static final Item.Properties FLINT = new Item.Properties().durability(131);
        public static final Item.Properties GOLD = new Item.Properties().durability(32);
        public static final Item.Properties IRON = new Item.Properties().durability(250);
        public static final Item.Properties DIAMOND = new Item.Properties().durability(1561);
        public static final Item.Properties NETHERITE = new Item.Properties().durability(2031).fireResistant();
        public static final Item.Properties ROT_AND_STEEL = new Item.Properties().durability(64).rarity(Rarity.UNCOMMON);

        //INTEGRATION
        public static final Item.Properties WOLFRAM = new Item.Properties().durability(2031);
    }
}
