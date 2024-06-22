package net.tostiman.snifferite.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.tostiman.snifferite.SnifferiteMod;

public class ModBlocks {

	public static final Block blockSnifferite = registerBlock("snifferite_block", new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
	public static final Block blockSplashBerryBush = registerBlock("splash_berry_bush", new SplashBerryBushBlock(FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));
	
	private static void addItemsToBuildingBlocksTab(FabricItemGroupEntries entries) {
		entries.add(blockSnifferite);
	}
	
	private static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(SnifferiteMod.MODID, name), block);
	}
	
	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registries.ITEM, new Identifier(SnifferiteMod.MODID, name), new BlockItem(block, new FabricItemSettings()));
	}
	
	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModBlocks::addItemsToBuildingBlocksTab);
	}
	
}
