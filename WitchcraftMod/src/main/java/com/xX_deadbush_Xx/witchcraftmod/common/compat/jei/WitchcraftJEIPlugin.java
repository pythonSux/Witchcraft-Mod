package com.xX_deadbush_Xx.witchcraftmod.common.compat.jei;

import java.util.List;
import java.util.stream.Collectors;

import com.xX_deadbush_Xx.witchcraftmod.api.crafting.recipes.ModRecipeTypes;
import com.xX_deadbush_Xx.witchcraftmod.common.compat.jei.categories.DryingRackRecipeCategory;
import com.xX_deadbush_Xx.witchcraftmod.common.register.ModBlocks;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class WitchcraftJEIPlugin implements IModPlugin{
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("witchcraftmod", "jei");
	}
	
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.DRYING_RACK.get()), DryingRackRecipeCategory.UID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
		registry.addRecipes(findRecipesByType(ModRecipeTypes.DRYING_RACK_TYPE), DryingRackRecipeCategory.UID);
    }

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(
				new DryingRackRecipeCategory(registry.getJeiHelpers().getGuiHelper())
		);
	}
	
	public static List<IRecipe<?>> findRecipesByType(IRecipeType<?> type) {
		return Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().filter(r -> r.getType() == type).collect(Collectors.toList());
	}
}