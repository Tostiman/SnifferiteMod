package net.tostiman.snifferite.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.tostiman.snifferite.SnifferiteMod;
import net.tostiman.snifferite.block.ModBlocks;
import net.tostiman.snifferite.item.ModItems;

public class ModRecipeProvider extends FabricRecipeProvider {

	public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}
	
	public static void offerSnifferiteSmithingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible input, Item output) {
		SmithingTransformRecipeJsonBuilder.create(
				Ingredient.ofItems(ModItems.itemSnifferiteUpgradeTemplate), 
				Ingredient.ofItems(input), 
				Ingredient.ofItems(ModItems.itemSnifferiteIngot), 
				category, output)
			.criterion(hasItem(ModItems.itemSnifferiteIngot), conditionsFromItem(ModItems.itemSnifferiteIngot))
			.offerTo(exporter, Identifier.of(SnifferiteMod.MODID, getRecipeName(output) + "_smithing"));
	}
	
	public static void offerToolRecipes(RecipeExporter exporter, RecipeCategory category) {
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_SWORD, ModItems.itemSnifferiteSword);
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_SHOVEL, ModItems.itemSnifferiteShovel);
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_PICKAXE, ModItems.itemSnifferitePickaxe);
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_AXE, ModItems.itemSnifferiteAxe);
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_HOE, ModItems.itemSnifferiteHoe);
	}
	
	public static void offerArmorRecipes(RecipeExporter exporter, RecipeCategory category) {
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_HELMET, ModItems.itemSnifferiteHelmet);
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_CHESTPLATE, ModItems.itemSnifferiteChestplate);
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_LEGGINGS, ModItems.itemSnifferiteLeggings);
		offerSnifferiteSmithingRecipe(exporter, category, Items.IRON_BOOTS, ModItems.itemSnifferiteBoots);
	}
	
	public static void offerReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, String reverseId) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9)
        	.input(compactItem)
        	.criterion(RecipeProvider.hasItem(compactItem), RecipeProvider.conditionsFromItem(compactItem))
        	.offerTo(exporter, Identifier.of(SnifferiteMod.MODID, reverseId + "_unpack"));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem)
        	.input(Character.valueOf('#'), baseItem)
        	.pattern("###")
        	.pattern("###")
        	.pattern("###")
        	.criterion(RecipeProvider.hasItem(baseItem), RecipeProvider.conditionsFromItem(baseItem))
        	.offerTo(exporter, Identifier.of(SnifferiteMod.MODID, compactingId + "_pack"));
    }
	
	@Override
	public void generate(RecipeExporter exporter) {
		ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.itemSnifferiteIngot)
    		.input(ModItems.itemSnifferiteScrap, 4)
    		.input(ModItems.itemSplashBerries, 4)
    		.criterion(hasItem(ModItems.itemSnifferiteScrap), conditionsFromItem(ModItems.itemSnifferiteScrap))
    		.offerTo(exporter, Identifier.of(SnifferiteMod.MODID, getRecipeName(ModItems.itemSnifferiteIngot)));
		
		offerReversibleCompactingRecipes(exporter, 
			RecipeCategory.MISC, ModItems.itemSnifferiteIngot, 
			RecipeCategory.BUILDING_BLOCKS, ModBlocks.blockSnifferite, 
			getRecipeName(ModBlocks.blockSnifferite), getRecipeName(ModItems.itemSnifferiteIngot));
		
		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.itemSnifferiteUpgradeTemplate, 2)
			.input(Character.valueOf('#'), ModItems.itemSplashBerries).input(Character.valueOf('C'), ModItems.itemSnifferiteScrap).input(Character.valueOf('S'), ModItems.itemSnifferiteUpgradeTemplate)
			.pattern("#S#")
			.pattern("#C#")
			.pattern("###")
			.criterion(hasItem(ModItems.itemSnifferiteUpgradeTemplate), conditionsFromItem(ModItems.itemSnifferiteUpgradeTemplate))
			.offerTo(exporter, Identifier.of(SnifferiteMod.MODID, getRecipeName(ModItems.itemSnifferiteUpgradeTemplate)));
	    
		
		offerToolRecipes(exporter, RecipeCategory.TOOLS);
		offerArmorRecipes(exporter, RecipeCategory.COMBAT);
	}
	
}
