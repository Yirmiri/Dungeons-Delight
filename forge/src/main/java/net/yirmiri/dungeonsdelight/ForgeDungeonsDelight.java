package net.yirmiri.dungeonsdelight;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.yirmiri.dungeonsdelight.core.registry.DDRarity;

@Mod(DungeonsDelight.MOD_ID)
public class ForgeDungeonsDelight {
    public ForgeDungeonsDelight() {
        // REMOVE FOR 1.21.1 - must be init before all else in 1.20
        DDRarity.MONSTER = Rarity.create(DDRarity.MONSTER_STRING, ChatFormatting.LIGHT_PURPLE);

        DungeonsDelight.init();
    }
}