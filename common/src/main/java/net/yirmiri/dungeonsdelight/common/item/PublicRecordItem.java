package net.yirmiri.dungeonsdelight.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.RecordItem;

public class PublicRecordItem extends RecordItem {
    public PublicRecordItem(int analogOutput, SoundEvent sound, Properties properties, int lengthInSeconds) { super(analogOutput, sound, properties, lengthInSeconds); }
}
