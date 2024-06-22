package net.tostiman.snifferite.item;

import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.sound.SoundEvent;
import net.tostiman.snifferite.sound.ModSounds;

public class ItemSplashBerry extends AliasedBlockItem {

	public ItemSplashBerry(Block block, Settings settings) {
		super(block, settings);
	}
	
	@Override
	public SoundEvent getEatSound() {
        return ModSounds.SPLASH_BERRY_EAT;
    }

}
