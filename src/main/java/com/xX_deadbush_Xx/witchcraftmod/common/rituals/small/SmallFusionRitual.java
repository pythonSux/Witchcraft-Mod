package com.xX_deadbush_Xx.witchcraftmod.common.rituals.small;

import java.util.Set;

import com.xX_deadbush_Xx.witchcraftmod.api.crafting.recipes.IFusionRecipe;
import com.xX_deadbush_Xx.witchcraftmod.api.crafting.recipes.ModRecipeTypes;
import com.xX_deadbush_Xx.witchcraftmod.api.inventory.SimpleItemHandler;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.ICraftingRitual;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.IStaticRitual;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.SmallRitual;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.activation.RitualActivationTaskHandler;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.config.ConfigType;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.config.RitualConfig;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.effect.BasicEffect;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.effect.RitualEffectHandler;
import com.xX_deadbush_Xx.witchcraftmod.api.util.helpers.CraftingHelper;
import com.xX_deadbush_Xx.witchcraftmod.client.effect.ClientParticleHandler.EffectType;
import com.xX_deadbush_Xx.witchcraftmod.common.blocks.blockstate.GlowType;
import com.xX_deadbush_Xx.witchcraftmod.common.network.WitchcraftPacketHandler;
import com.xX_deadbush_Xx.witchcraftmod.common.network.packets.client.WitchcraftParticlePacket;
import com.xX_deadbush_Xx.witchcraftmod.common.recipes.SmallFusionRecipe;
import com.xX_deadbush_Xx.witchcraftmod.common.register.ModBlocks;
import com.xX_deadbush_Xx.witchcraftmod.common.tile.AbstractRitualCore;
import com.xX_deadbush_Xx.witchcraftmod.common.tile.RitualPedestalTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;	
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class SmallFusionRitual extends SmallRitual implements ICraftingRitual, IStaticRitual {
	public static final RitualConfig config = new RitualConfig(ConfigType.SMALL).addAnchorBlocks(4, ModBlocks.RITUAL_PEDESTAL.get());
	
	public SmallFusionRitual(AbstractRitualCore tile, PlayerEntity player) {
		super(tile, player);
	}
	
	public static SmallFusionRitual create(AbstractRitualCore tile, PlayerEntity player) {
		return new SmallFusionRitual(tile, player);
	}
	
	public GlowType getGlowType() {
		return GlowType.PURPLE;
	}

	@Override
	public void init() {
		SmallFusionRecipe recipe = getRecipe();
		if(recipe != null) {
			tile.lastRecipe = recipe;
			this.effecthandler = getEffectHandler();
		} else {
			this.stopRitual(false);
		}
	}
	
	@Override
	public SmallFusionRecipe getRecipe() {
		ItemStack stack = this.tile.getItem();
		if(stack == null) return null;
		
		Set<IRecipe<?>> recipes = CraftingHelper.findRecipesByType(ModRecipeTypes.SMALL_FUSION_TYPE);
		for(IRecipe<?> r : recipes) {
			SmallFusionRecipe recipe = (SmallFusionRecipe) r;
			if(recipeComplete(recipe)) {
				return recipe;
			}
		}
		return null;
	}
	
	@Override
	public ItemStack[] getRecipeInputs() {
		ItemStack[] items = new ItemStack[5];
		items[0] = this.tile.getItem();

		RitualPedestalTile[] pedestals = getPedestals();
		for(int i = 1; i < 5; i++) {
			items[i] = pedestals[i-1].getItem();
		}
		return items;
	}
	
	private RitualPedestalTile[] getPedestals() {
		RitualPedestalTile[] pedestals = new RitualPedestalTile[4];
		for(int i = 0; i < 4; i++) {
			Direction direction = Direction.byHorizontalIndex(i);
			pedestals[i] =  (RitualPedestalTile) this.worldIn.getTileEntity(this.tile.getPos().offset(direction, 2).offset(direction.rotateY(), 2));
		}
		return pedestals;
	}
	
	private boolean recipeComplete(IRecipe<RecipeWrapper> recipe) {
		return recipe.matches(new RecipeWrapper(new SimpleItemHandler(5, getRecipeInputs())), this.worldIn);
	}

	@Override
	public RitualEffectHandler getEffectHandler() {
		return new RitualEffectHandler(this.tile, this.performingPlayer) {
			@Override
			public void init() {
				RitualEffectHandler handler = this;
				this.addEffect(new BasicEffect(player, ritualStone) {
					
					@Override
					public void execute() {
						BlockPos pos = tile.getPos();
						for(RitualPedestalTile pedestal : getPedestals()) {
							BlockPos pedestalpos = pedestal.getPos();
							WitchcraftPacketHandler.sendToNearby(tile.getWorld(), pos, new WitchcraftParticlePacket(EffectType.PEDESTAL_DISAPPEAR, pedestalpos.getX() + 0.5, pedestalpos.getY() + 1.4, pedestalpos.getZ() + 0.5));
						}
					} 
				}, 10);
				
				this.addEffect(new BasicEffect(player, ritualStone) {
					
					@Override
					public void execute() {
						if(!recipeComplete(tile.lastRecipe)) {
							stopRitual(true); 
							return;
						}
						
						BlockPos pos = tile.getPos();
						for(RitualPedestalTile pedestal : getPedestals()) {
							pedestal.useForCrafting();
						}
						
						WitchcraftPacketHandler.sendToNearby(tile.getWorld(), pos, new WitchcraftParticlePacket(EffectType.RITUAL_ITEM_CREATE, pos.getX() + 0.5, pos.getY() + 1.4, pos.getZ() + 0.5));
						tile.setItem(tile.lastRecipe.getRecipeOutput().copy());
						tile.markDirty();
						System.out.println("STAHP!" + this.tile.getWorld().isRemote);
						handler.stopEffect(true);
					}
				}, 80);
			}
		};
	}

	@Override
	public RitualConfig getConfig() {
		return this.config;
	}

	@Override
	public RitualActivationTaskHandler getActivationHandler() {
		return new RitualActivationTaskHandler(this.tile, this.performingPlayer) {

			@Override
			public void init() {
				this.consumeEnergy(((IFusionRecipe)tile.lastRecipe).getActivationCost());
			}
		};
	}
}