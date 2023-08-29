package net.mwti.stoneexpansion;

import net.fabricmc.api.ModInitializer;

import net.mwti.stoneexpansion.block.ModBlocks;
import net.mwti.stoneexpansion.item.ModItemGroups;
import net.mwti.stoneexpansion.item.ModItems;
import net.mwti.stoneexpansion.util.ModTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoneExpansion implements ModInitializer {

	public static final String MOD_ID = "stoneexpansion";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModTags.registerModTags();
	}
}