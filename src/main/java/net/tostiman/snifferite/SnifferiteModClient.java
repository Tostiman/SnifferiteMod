package net.tostiman.snifferite;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.tostiman.snifferite.block.ModBlocks;

public class SnifferiteModClient implements ClientModInitializer{

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.blockSplashBerryBush, RenderLayer.getCutout());
	}
}
