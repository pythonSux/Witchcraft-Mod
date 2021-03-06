package com.xX_deadbush_Xx.hocus.common.recipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.xX_deadbush_Xx.hocus.Hocus;
import com.xX_deadbush_Xx.hocus.api.crafting.recipes.IFusionRecipe;
import com.xX_deadbush_Xx.hocus.api.crafting.recipes.ModRecipeTypes;
import com.xX_deadbush_Xx.hocus.api.util.CraftingHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class LargeFusionRecipe implements IFusionRecipe {
	private final ResourceLocation id;
	private final ItemStack output;
	private NonNullList<Ingredient> inputs;
	private final boolean shapeless;
	private int activationcost;
	public static final ResourceLocation TYPE_ID = new ResourceLocation(Hocus.MOD_ID, "large_fusion");

	
	public LargeFusionRecipe(ResourceLocation id, ItemStack output, boolean shapeless, int activationcost, Ingredient... inputs) {
		this.id = id;
		this.activationcost = activationcost;
		this.output = output;
		this.inputs = NonNullList.create();
		this.shapeless = shapeless;
		for(Ingredient input : inputs) {
			this.inputs.add(input);
		}
	}
	
	@Override
	public boolean matches(RecipeWrapper inv, @Nullable World worldIn) {		
		if(shapeless) {
			return CraftingHelper.checkMatchUnordered(this.inputs, CraftingHelper.asList(inv), (ingred, stack) -> ingred.test(stack));
		} else {
			return CraftingHelper.checkMatchOrderedRotational(this.inputs, CraftingHelper.asList(inv), (ingred, stack) -> ingred.test(stack));
		}
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
		return ModRecipeTypes.MEDIUM_FUSION_SERIALIZER.get();
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.inputs;
	}

	public boolean isShapeless() {
		return this.shapeless;
	}

	@Nonnull
	@Override
	public IRecipeType<?> getType() {
		return Registry.RECIPE_TYPE.getValue(TYPE_ID).get();
	}

	@Override
	public int getActivationCost() {
		return this.activationcost;
	}
}
