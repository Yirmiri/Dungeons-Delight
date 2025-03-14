package net.yirmiri.dungeonsdelight.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.yirmiri.dungeonsdelight.DungeonsDelight;

public class DDBlockSetTypes {
    //BLOCK SET TYPES
    public static BlockSetType WORMWOOD_BLOCKSET = new BlockSetType(DungeonsDelight.MOD_ID + ":wormwood", true, 
            SoundType.NETHER_WOOD, SoundEvents.NETHER_WOOD_DOOR_CLOSE, 
            SoundEvents.NETHER_WOOD_DOOR_OPEN, SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE, 
            SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN, SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, 
            SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF, 
            SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON);

    //WOOD TYPES
    public static final WoodType WORMWOOD = WoodType.register(new WoodType(DungeonsDelight.MOD_ID + ":wormwood", WORMWOOD_BLOCKSET));
}
