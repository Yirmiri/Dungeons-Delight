package net.yirmiri.dungeons_delight;

import net.fabricmc.api.ModInitializer;

import net.yirmiri.dungeons_delight.registry.DDEffects;
import net.yirmiri.dungeons_delight.registry.DDItemGroups;
import net.yirmiri.dungeons_delight.registry.DDItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsDelight implements ModInitializer {
	public static final String MOD_ID = "dungeonsdelight";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		DDItems.loadItems();
		DDItemGroups.loadItemGroups();
		DDEffects.loadEffects();
	}
}