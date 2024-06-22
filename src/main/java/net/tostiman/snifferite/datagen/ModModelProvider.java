package net.tostiman.snifferite.datagen;

import java.util.stream.IntStream;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.tostiman.snifferite.block.ModBlocks;
import net.tostiman.snifferite.block.SplashBerryBushBlock;
import net.tostiman.snifferite.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

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
		
		itemModelGenerator.registerArmor((ArmorItem)ModItems.itemSnifferiteHelmet);
		itemModelGenerator.registerArmor((ArmorItem)ModItems.itemSnifferiteChestplate);
		itemModelGenerator.registerArmor((ArmorItem)ModItems.itemSnifferiteLeggings);
		itemModelGenerator.registerArmor((ArmorItem)ModItems.itemSnifferiteBoots);
	}

}
