package com.xX_deadbush_Xx.witchcraftmod.api.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BottomlessBagSlot extends SlotItemHandler {

    public BottomlessBagSlot(BottomlessBagInventory itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }
    
    @Override
    public ItemStack getStack() {
        return this.getItemHandler().getStackInSlot(0);
    }


    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        return super.onTake(thePlayer, stack);
    }

    @Override
    public int getSlotStackLimit() {
        return Integer.MAX_VALUE;
    }
}