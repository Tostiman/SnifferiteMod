package net.tostiman.snifferite.datagen;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.tostiman.snifferite.block.ModBlocks;
import net.tostiman.snifferite.block.SplashBerryBushBlock;
import net.tostiman.snifferite.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

	private static final List<TrimMaterial> TRIM_MATERIALS = List.of(
			new TrimMaterial("quartz", 0.1f, Map.of()), 
			new TrimMaterial("iron", 0.2f, Map.of(ArmorMaterials.IRON, "iron_darker")), 
			new TrimMaterial("netherite", 0.3f, Map.of(ArmorMaterials.NETHERITE, "netherite_darker")),
			new TrimMaterial("redstone", 0.4f, Map.of()), 
			new TrimMaterial("copper", 0.5f, Map.of()), 
			new TrimMaterial("gold", 0.6f, Map.of(ArmorMaterials.GOLD, "gold_darker")), 
			new TrimMaterial("emerald", 0.7f, Map.of()), 
			new TrimMaterial("diamond", 0.8f, Map.of(ArmorMaterials.DIAMOND, "diamond_darker")), 
			new TrimMaterial("lapis", 0.9f, Map.of()), 
			new TrimMaterial("amethyst", 1.0f, Map.of()));
	
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	public final void registerBerryBush(BlockStateModelGenerator blockStateModelGenerator, Block crop, Property<Integer> ageProperty, int ... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        }
        Int2ObjectOpenHashMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<Identifier>();
        BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register(integer -> {
            int i = ageTextureIndices[integer];
            Identifier identifier = int2ObjectMap.computeIfAbsent(i, j -> blockStateModelGenerator.createSubModel(crop, "_stage" + i, Models.CROSS, TextureMap::cross));
            return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
        });
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap));
    }
	
	public final JsonObject createArmorJson(Identifier id, Map<TextureKey, Identifier> textures, RegistryEntry<ArmorMaterial> armorMaterial) {
        JsonObject jsonObject = Models.GENERATED_TWO_LAYERS.createJson(id, textures);
        JsonArray jsonArray = new JsonArray();
        for (TrimMaterial trimMaterial : TRIM_MATERIALS) {
            JsonObject jsonObject2 = new JsonObject();
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty("trim_type", Float.valueOf(trimMaterial.itemModelIndex()));
            jsonObject2.add("predicate", jsonObject3);
            jsonObject2.addProperty("model", id.withSuffixedPath("_" + trimMaterial.getAppliedName(armorMaterial) + "_trim").toString());
            
            jsonArray.add(jsonObject2);
        }
        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }
	
	public final void registerArmor(ItemModelGenerator itemModelGenerator, ArmorItem armor) {
        if (!armor.getType().isTrimmable()) return;
        Identifier identifier = ModelIds.getItemModelId(armor);
        Identifier identifier2 = TextureMap.getId(armor);
        
        Models.GENERATED.upload(identifier, TextureMap.layer0(identifier2), itemModelGenerator.writer, 
        		(id, textures) -> createArmorJson(id, textures, armor.getMaterial()));
        
        for (TrimMaterial trimMaterial : TRIM_MATERIALS) {
            String armorMaterialName = trimMaterial.getAppliedName(armor.getMaterial());
            Identifier title = identifier.withSuffixedPath("_" + armorMaterialName + "_trim");
            String armorPieceName = armor.getType().getName() + "_trim_" + armorMaterialName;
            Identifier trimLayer = Identifier.of(armorPieceName).withPrefixedPath("trims/items/");
            itemModelGenerator.uploadArmor(title, identifier2, trimLayer);
        }
    }
	
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerSingleton(ModBlocks.blockSnifferite, TexturedModel.CUBE_BOTTOM_TOP);
		registerBerryBush(blockStateModelGenerator, ModBlocks.blockSplashBerryBush, SplashBerryBushBlock.AGE, IntStream.rangeClosed(0,3).toArray());
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ModItems.itemSnifferiteScrap, Models.GENERATED);
		itemModelGenerator.register(ModItems.itemSnifferiteIngot, Models.GENERATED);
		itemModelGenerator.register(ModItems.itemSplashBerries, Models.GENERATED);
		itemModelGenerator.register(ModItems.itemSnifferiteUpgradeTemplate, Models.GENERATED);
		
		itemModelGenerator.register(ModItems.itemSnifferiteSword, Models.HANDHELD);
		itemModelGenerator.register(ModItems.itemSnifferitePickaxe, Models.HANDHELD);
		itemModelGenerator.register(ModItems.itemSnifferiteAxe, Models.HANDHELD);
		itemModelGenerator.register(ModItems.itemSnifferiteShovel, Models.HANDHELD);
		itemModelGenerator.register(ModItems.itemSnifferiteHoe, Models.HANDHELD);
		
		registerArmor(itemModelGenerator, (ArmorItem)ModItems.itemSnifferiteHelmet);
		registerArmor(itemModelGenerator, (ArmorItem)ModItems.itemSnifferiteChestplate);
		registerArmor(itemModelGenerator, (ArmorItem)ModItems.itemSnifferiteLeggings);
		registerArmor(itemModelGenerator, (ArmorItem)ModItems.itemSnifferiteBoots);
	}

	record TrimMaterial(String name, float itemModelIndex, Map<RegistryEntry<ArmorMaterial>, String> overrideArmorMaterials) {
        public String getAppliedName(RegistryEntry<ArmorMaterial> armorMaterial) {
            return this.overrideArmorMaterials.getOrDefault(armorMaterial, this.name);
        }
    }
}
