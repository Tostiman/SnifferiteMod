package net.tostiman.snifferite;

import net.fabricmc.api.ModInitializer;
import net.tostiman.snifferite.block.ModBlocks;
import net.tostiman.snifferite.item.ModItems;
import net.tostiman.snifferite.sound.ModSounds;

public class SnifferiteMod implements ModInitializer {
	
	public static final String MODID = "snifferite";

	@Override
	public void onInitialize() {
		ModItems.register();
		ModBlocks.register();
		ModLootTableModifiers.modifyLootTables();
		ModSounds.registerSounds();
	}
}