package net.tostiman.snifferite;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.tostiman.snifferite.datagen.ModBlockTagProvider;
import net.tostiman.snifferite.datagen.ModItemTagProvider;
import net.tostiman.snifferite.datagen.ModLootTableProvider;
import net.tostiman.snifferite.datagen.ModModelProvider;
import net.tostiman.snifferite.datagen.ModRecipeProvider;

public class SnifferiteModDataGenerator implements DataGeneratorEntrypoint {
	
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
