package net.tostiman.snifferite.item;

import java.util.function.Supplier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.tostiman.snifferite.SnifferiteMod;

public enum ModArmorMaterial implements ArmorMaterial {
	
	SNIFFERITE("snifferite", 25, new int[] {2, 6, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0f, 0f, () -> Ingredient.ofItems(ModItems.itemSnifferiteIngot));

	private final String name;
	private final int durability;
	private final int[] protection;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngredient;
	
	private static final int[] BASE_DURABILITY = {11, 16, 15, 13};
	
	ModArmorMaterial(String name, int durability, int[] protection, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient){
		this.name = name;
		this.durability = durability;
		this.protection = protection;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient;
	}
	
	@Override
	public String getName() {
		return SnifferiteMod.MODID + ":" + this.name;
	}
	
	@Override
	public int getDurability(ArmorItem.Type type) {
		return BASE_DURABILITY[type.ordinal()] * this.durability;
	}

	@Override
	public int getProtection(ArmorItem.Type type) {
		return this.protection[type.ordinal()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
