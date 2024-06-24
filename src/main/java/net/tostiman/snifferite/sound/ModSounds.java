package net.tostiman.snifferite.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.tostiman.snifferite.SnifferiteMod;

public class ModSounds {
	
	public static final SoundEvent SPLASH_BERRY_EAT = registerSoundEvent("splash_berry_eat");
	
	public static SoundEvent registerSoundEvent(String name) {
		Identifier id = Identifier.of(SnifferiteMod.MODID, name);
		return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}
	
	public static void registerSounds() {
		
	}
}
