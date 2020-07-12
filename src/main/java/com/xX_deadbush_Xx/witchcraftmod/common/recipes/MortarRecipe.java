package com.xX_deadbush_Xx.witchcraftmod.common.recipes;

import javax.annotation.Nullable;

import com.xX_deadbush_Xx.witchcraftmod.api.crafting.recipes.IMortarRecipe;
import com.xX_deadbush_Xx.witchcraftmod.api.crafting.recipes.ModRecipeTypes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class MortarRecipe implements IMortarRecipe {
	private final ResourceLocation id;
	private final ItemStack output;
	private Ingredient input;

	
	public MortarRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
		this.id = id;
		this.output = output;
		this.input = input;
	}
	
	@Override
	public boolean matches(RecipeWrapper inv, @Nullable World worldIn) {
		if(this.input.test(inv.getStackInSlot(0))) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		return this.output;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ModRecipeTypes.MORTAR_SERIALIZER.get();
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.from(null, this.input);
	}
	
	public Ingredient getInput() {
		return this.input;
	}
}