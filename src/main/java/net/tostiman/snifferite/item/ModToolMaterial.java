package net.tostiman.snifferite.item;

import java.util.function.Supplier;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterial implements ToolMaterial {
	
	SNIFFERITE(750, 7.0f, 2.5f, MiningLevels.IRON, 15, () -> Ingredient.ofItems(ModItems.itemSnifferiteIngot));

	private final int durability;
	private final float miningSpeedMultiplier;
	private final float attackDamage;
	private final int miningLevel;
	private final int enchantability;
	private final Supplier<Ingredient> repairIngredient;
	
	ModToolMaterial(int durability, float miningSpeedMultiplier, float attackDamage, int miningLevel, int enchantability, Supplier<Ingredient> repairIngredient){
		this.durability = durability;
		this.miningSpeedMultiplier = miningSpeedMultiplier;
		this.attackDamage = attackDamage;
		this.miningLevel = miningLevel;
		this.enchantability = enchantability;
		this.repairIngredient = repairIngredient;
	}
	
	@Override
	public int getDurability() {
		return this.durability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeedMultiplier;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getMiningLevel() {
		return this.miningLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
	
}
