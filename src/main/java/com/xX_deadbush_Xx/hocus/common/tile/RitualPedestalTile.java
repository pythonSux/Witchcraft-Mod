package com.xX_deadbush_Xx.hocus.common.tile;

import com.xX_deadbush_Xx.hocus.api.tile.BasicItemHolderTile;
import com.xX_deadbush_Xx.hocus.api.util.ItemStackHelper;
import com.xX_deadbush_Xx.hocus.common.register.ModTileEntities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualPedestalTile extends BasicItemHolderTile {
	
	public RitualPedestalTile(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, 1);
	}
	
	public RitualPedestalTile() {
		this(ModTileEntities.RITUAL_PEDESTAL.get());
	}
	
	public void swapItems(World worldIn, BlockPos blockpos, PlayerEntity player) {
		ItemStack playerItems = player.getHeldItemMainhand().copy();
		ItemStack returnedItem = ItemStack.EMPTY;
		boolean empty = false;
		
		if (this.hasItem() || getStack().equals(playerItems)) {
			returnedItem = this.getStack().copy();
			empty = true;
		}
		
		if ((!getStack().equals(playerItems) && !empty || !this.hasItem()) && !playerItems.isEmpty()) {
			player.getHeldItemMainhand().shrink(1);
			playerItems.setCount(1);
			setStack(playerItems); 
			this.markDirty();
		}
		
		if(empty) {
			clearItem();
		}
		
		if(!returnedItem.isEmpty()) {
			if (playerItems.isEmpty())
				player.setHeldItem(Hand.MAIN_HAND, returnedItem);
			else if (!player.inventory.addItemStackToInventory(returnedItem)) {
				ItemStackHelper.spawnItem(worldIn, returnedItem, this.pos);
			}
		}
	}
	
	public ItemStack getStack() {
		return this.inventory.getStackInSlot(0);
	}
	
	public void setStack(ItemStack stack) {
		this.inventory.setStackInSlot(0, stack);
	}
	
	public boolean hasItem() {
		return !this.getStack().isEmpty() && !this.getStack().getItem().equals(Items.AIR);
	}
	
	public void useForCrafting() {
 		this.inventory.removeStackFromSlot(0);
		this.markDirty();
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("item", getStack().write(new CompoundNBT()));
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		CompoundNBT item = compound.getCompound("item");

		if(item != null && !item.isEmpty()) {
			setStack(ItemStack.read(item));
		}
	}

	public void clearItem() {
		this.inventory.clear();
		this.markDirty();
	}
}
