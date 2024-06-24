package net.tostiman.snifferite.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tostiman.snifferite.SnifferiteMod;
import net.tostiman.snifferite.block.ModBlocks;

public class ModItems {

	public static final Item itemSnifferiteScrap = registerItem("snifferite_scrap", new Item(new Item.Settings()));
	public static final Item itemSnifferiteIngot = registerItem("snifferite_ingot", new Item(new Item.Settings()));
	public static final Item itemSplashBerries = registerItem("splash_berries", (Item)new ItemSplashBerry(ModBlocks.blockSplashBerryBush, new Item.Settings().food(ModFoodComponents.SPLASH_BERRY)));
	public static final Item itemSnifferiteUpgradeTemplate = registerItem("snifferite_upgrade_template", new Item(new Item.Settings()));
	
	public static final Item itemSnifferiteSword = registerItem("snifferite_sword", new SwordItem(ModToolMaterial.SNIFFERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.SNIFFERITE, 3, 1.6f - 4.0f))));
	public static final Item itemSnifferitePickaxe = registerItem("snifferite_pickaxe", new PickaxeItem(ModToolMaterial.SNIFFERITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.SNIFFERITE, 1,  1.2f - 4.0f))));
	public static final Item itemSnifferiteAxe = registerItem("snifferite_axe", new AxeItem(ModToolMaterial.SNIFFERITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.SNIFFERITE, 6, 0.9f - 4.0f))));
	public static final Item itemSnifferiteShovel = registerItem("snifferite_shovel", new ShovelItem(ModToolMaterial.SNIFFERITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.SNIFFERITE, 1.5f, 1.0f - 4.0f))));
	public static final Item itemSnifferiteHoe = registerItem("snifferite_hoe", new HoeItem(ModToolMaterial.SNIFFERITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.SNIFFERITE, -2, 3.0f - 4.0f))));
	
	public static final Item itemSnifferiteHelmet = registerItem("snifferite_helmet", new ArmorItem(ModArmorMaterial.SNIFFERITE, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(25))));
	public static final Item itemSnifferiteChestplate = registerItem("snifferite_chestplate", new ArmorItem(ModArmorMaterial.SNIFFERITE, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(25))));
	public static final Item itemSnifferiteLeggings = registerItem("snifferite_leggings", new ArmorItem(ModArmorMaterial.SNIFFERITE, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(25))));
	public static final Item itemSnifferiteBoots = registerItem("snifferite_boots", new ArmorItem(ModArmorMaterial.SNIFFERITE, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(25))));
	
	private static void addItemsToIngredientsTab(FabricItemGroupEntries entries) {
		entries.add(itemSnifferiteScrap);
		entries.add(itemSnifferiteIngot);
		entries.add(itemSnifferiteUpgradeTemplate);
	}
	
	private static void addItemsToFoodAndDrinksAndNaturalTabs(FabricItemGroupEntries entries) {
		entries.add(itemSplashBerries);
	}
	
	private static void addItemsToToolsTab(FabricItemGroupEntries entries) {
		entries.add(itemSnifferiteShovel);
		entries.add(itemSnifferitePickaxe);
		entries.add(itemSnifferiteAxe);
		entries.add(itemSnifferiteHoe);
	}
	
	private static void addItemsToCombatTab(FabricItemGroupEntries entries) {
		entries.add(itemSnifferiteSword);
		entries.add(itemSnifferiteAxe);
		
		entries.add(itemSnifferiteHelmet);
		entries.add(itemSnifferiteChestplate);
		entries.add(itemSnifferiteLeggings);
		entries.add(itemSnifferiteBoots);
	}
	
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(SnifferiteMod.MODID, name), item);
	}
	
	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientsTab);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodAndDrinksAndNaturalTabs);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemsToFoodAndDrinksAndNaturalTabs);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsTab);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTab);
	}
}
