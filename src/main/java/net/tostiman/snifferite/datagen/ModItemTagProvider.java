package net.tostiman.snifferite.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.tostiman.snifferite.item.ModItems;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public ModItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(WrapperLookup arg) {
		getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
			.add(ModItems.itemSnifferiteHelmet, ModItems.itemSnifferiteChestplate, ModItems.itemSnifferiteLeggings, ModItems.itemSnifferiteBoots);
	}

}
