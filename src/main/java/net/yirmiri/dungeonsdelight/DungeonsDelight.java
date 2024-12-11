package net.yirmiri.dungeonsdelight;

import net.fabricmc.api.ModInitializer;

import net.yirmiri.dungeonsdelight.block.entity.DungeonPotBlockEntity;
import net.yirmiri.dungeonsdelight.registry.*;
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
		DDBlocks.loadBlocks();
		DDStats.loadStats();
		DDParticles.loadParticles();
		DDSounds.loadSounds();
		DDBlockEntities.loadBlockEntities();
		DDScreenHandlers.loadScreenHandlers();
		DungeonPotBlockEntity.init();
		DDRecipeRegistries.RECIPE_TYPES.register();
		DDRecipeRegistries.RECIPE_SERIALIZERS.register();
		DDRecipeRegistries.loadRecipeRegistries();
	}
}