package com.xX_deadbush_Xx.witchcraftmod.api.crafting.serializers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xX_deadbush_Xx.witchcraftmod.common.recipes.SmallFusionRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SmallFusionSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SmallFusionRecipe> {
	
	@Override
	public SmallFusionRecipe read(ResourceLocation recipeId, JsonObject json) {
		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
		JsonArray ingredientsJson = JSONUtils.getJsonArray(json, "ingredients");
		boolean shapeless = JSONUtils.getBoolean(json, "shapeless");
		List<Ingredient> ingredients = new ArrayList<>();
		
		for (JsonElement e : ingredientsJson) {
			ingredients.add(Ingredient.deserialize(e));
		}
		
		return new SmallFusionRecipe(recipeId, output, shapeless, ingredients.stream().toArray(Ingredient[]::new));
	}

	@Override
	public SmallFusionRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		int size = buffer.readInt();
		
		List<Ingredient> inputs = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			inputs.add(Ingredient.read(buffer));
		}
		
		ItemStack output = buffer.readItemStack();
		boolean shapeless = buffer.readBoolean();
		return new SmallFusionRecipe(recipeId, output, shapeless, inputs.stream().toArray(Ingredient[]::new));
	}

	@Override
	public void write(PacketBuffer buffer, SmallFusionRecipe recipe) {
		buffer.writeInt(recipe.getIngredients().size());

		for (Ingredient input : recipe.getIngredients()) {
			input.write(buffer);
		}
		
		buffer.writeItemStack(recipe.getRecipeOutput(), false);
		buffer.writeBoolean(recipe.isShapeless());
	}
}
