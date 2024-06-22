package net.tostiman.snifferite.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.tostiman.snifferite.block.ModBlocks;
import net.tostiman.snifferite.block.SplashBerryBushBlock;
import net.tostiman.snifferite.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

	public ModLootTableProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}
	
	public LootTable.Builder berryDrops(Block drop, Item item){
		LootCondition.Builder IS_MAX_AGE = BlockStatePropertyLootCondition.builder(drop).properties(StatePredicate.Builder.create().exactMatch(SplashBerryBushBlock.AGE, SplashBerryBushBlock.MAX_AGE));
		LootCondition.Builder IS_NEAR_MAX_AGE = BlockStatePropertyLootCondition.builder(drop).properties(StatePredicate.Builder.create().exactMatch(SplashBerryBushBlock.AGE, SplashBerryBushBlock.MAX_AGE-1));
		
		LootTable.Builder builder = this.applyExplosionDecay(drop, LootTable.builder()
				//When max age, drop 2-3 + (fortune bonus) berries
				.pool(LootPool.builder().with(ItemEntry.builder(item) 
					.conditionally(IS_MAX_AGE)
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
					.apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
			    ))
				//When near max age, drop 1-2 + (fortune bonus) berries
				.pool(LootPool.builder().with(ItemEntry.builder(item) 
					.conditionally(IS_NEAR_MAX_AGE)
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
					.apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
				))
		);
		
		return builder;
	}
	
	@Override
	public void generate() {
		addDrop(ModBlocks.blockSnifferite);
		addDrop(ModBlocks.blockSplashBerryBush, berryDrops(ModBlocks.blockSplashBerryBush, ModItems.itemSplashBerries));
	}



}
