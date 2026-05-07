package net.yirmiri.dungeonsdelight.core.init;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;
import net.yirmiri.dungeonsdelight.core.registry.DDSoundTypes;

import java.util.Map;
import java.util.Set;

public class DDBlockSetTypes {

    public static final Set<WoodType> WT_VALUES = new ObjectArraySet<>();
    public static final Map<String, BlockSetType> BS_VALUES = new Object2ObjectArrayMap<>();

    //BLOCK SET TYPES
    public static BlockSetType WORMWOOD_BLOCKSET = reg(new BlockSetType(DungeonsDelight.MOD_ID + ":wormwood",
            true,
            SoundType.NETHER_WOOD,
            SoundEvents.NETHER_WOOD_DOOR_CLOSE, SoundEvents.NETHER_WOOD_DOOR_OPEN,
            SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
            SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON));

    public static BlockSetType STAINED_BLOCKSET = reg(new BlockSetType(DungeonsDelight.MOD_ID + ":stained",
            true,
            DDSoundTypes.STAINED_SCRAP,
            SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));

    //WOOD TYPES
    public static final WoodType WORMWOOD = reg(new WoodType(DungeonsDelight.MOD_ID + ":wormwood", WORMWOOD_BLOCKSET));

    //////////////////////////////////////////////////////

    private static WoodType reg(WoodType type) {
        WT_VALUES.add(type);
        return type;
    }

    private static BlockSetType reg(BlockSetType type) {
        BS_VALUES.put(type.name(), type);
        return type;
    }

    public static void init() {}
}