package net.tostiman.snifferite;

import java.util.function.Consumer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.tostiman.snifferite.item.ModItems;

public class ModLootTableModifiers {
	
	private static final RegistryKey<LootTable> SNIFFER_DIGGING_ID = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "gameplay/sniffer_digging"));
	private static final RegistryKey<LootTable> OCEAN_RUIN_WARM_ID = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of("minecraft", "archaeology/ocean_ruin_warm"));
	
	public static void modifyLootTables() {
		LootTableEvents.MODIFY.register((id, tableBuilder, source) -> {
			if(source.isBuiltin()) {
				//Add sniffer drops
				if(SNIFFER_DIGGING_ID.equals(id)) {
					Consumer<LootPool.Builder> consumer = pool -> {
						pool.with(ItemEntry.builder(Items.TORCHFLOWER_SEEDS).weight(7))
							.with(ItemEntry.builder(Items.PITCHER_POD).weight(7))
							.with(ItemEntry.builder(ModItems.itemSplashBerries).weight(3))
							.with(ItemEntry.builder(ModItems.itemSnifferiteScrap).weight(1));
					};
					tableBuilder.modifyPools(consumer);
				}
				
				//Add upgrade template drop
				if(OCEAN_RUIN_WARM_ID.equals(id)) {
					Consumer<LootPool.Builder> consumer = pool -> {
						pool.with(ItemEntry.builder(ModItems.itemSnifferiteUpgradeTemplate));
					};
					tableBuilder.modifyPools(consumer);
				}
			}
		});
	}
}
