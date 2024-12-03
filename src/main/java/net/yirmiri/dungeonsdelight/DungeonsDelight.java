package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ModInitializer;

import net.yirmiri.dungeonsdelight.registry.DDEffects;
import net.yirmiri.dungeonsdelight.registry.DDItemGroups;
import net.yirmiri.dungeonsdelight.registry.DDItems;
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