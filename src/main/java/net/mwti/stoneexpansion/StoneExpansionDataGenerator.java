package net.mwti.stoneexpansion;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mwti.stoneexpansion.datagen.*;

public class StoneExpansionDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		System.out.println("Generating data for " + StoneExpansion.MOD_ID);
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTags::new);
		pack.addProvider(ModLootTables::new);
		pack.addProvider(ModModels::new);
		pack.addProvider(ModRecipes::new);
		pack.addProvider(ModItemTags::new);
	}
}
