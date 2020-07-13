package com.xX_deadbush_Xx.witchcraftmod.common.container;

import com.xX_deadbush_Xx.witchcraftmod.api.inventory.BottomlessBagInventory;
import com.xX_deadbush_Xx.witchcraftmod.api.inventory.BottomlessBagSlot;
import com.xX_deadbush_Xx.witchcraftmod.common.register.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class BottomLessBagContainer extends Container {

    private BottomlessBagInventory bagInventory;

    public BottomLessBagContainer(final int windowId, final PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        this(windowId, playerInventory, new BottomlessBagInventory(playerInventory.getCurrentItem()), packetBuffer.readVarInt());
    }

    public BottomLessBagContainer(int windowId, PlayerInventory playerInventory, BottomlessBagInventory inventory, int selectedSlot) {
        super(ModContainers.BOTTOM_LESS_BAG.get(), windowId);
        this.bagInventory = inventory;

        this.addSlot(new BottomlessBagSlot(bagInventory, 0, 81, 35));

        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
        }
    }

    public ItemStack getStoredItem() {
        return bagInventory.getStack();
    }

    public int getAmount() {
        return bagInventory.getActuallyStack();
    }

    @Override
    public void detectAndSendChanges() {
    } // for now I will leave this empty because the super method doesnt work here and I dont know if we even need this method

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            bagInventory.setStackInSlot(0, itemstack1.copy());
            if (index < 1) {

                if (!this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    public void putStackInSlot(int slotID, ItemStack stack) {
        bagInventory.insertItem(0, stack, false);
    }

    //Return the item who is draged
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickType, PlayerEntity player) {
        //This is what needs to be worked on next. Determine what items should be given to the player and what should remain in the slot for every clickType. Im not sure what the method is supposed to return.

        if(slotId == 0) {
            BottomlessBagSlot slot = (BottomlessBagSlot) this.inventorySlots.get(slotId);
            if(slot.getHasStack()) {
                ItemStack dragged = super.slotClick(slotId, dragType, clickType, player);
                if(dragged.isEmpty()) {
                    return bagInventory.extractItem(slotId, 64, false);
                } else {
                    return bagInventory.insertItem(slotId, dragged, false);
                }
                /*int amount = bagInventory.getActuallyStack();
                if(amount > 64) {
                    ItemStack res = this.bagInventory.extractItem(slotId, 64, false);
                    return this.bagInventory.extractItem(slotId, 64, false);
                }*/

            }
            System.out.println("DEBUG_3");
            //slot.onSlotChange(ItemStack.EMPTY, super.slotClick(slotId, dragType, clickType, player));
            //return ItemStack.EMPTY;
        }
        /*if (slotId == 0) {
            Slot slot = this.inventorySlots.get(slotId);
            ItemStack currentStack = slot.getStack(); //Stack IN slot
            ItemStack clicked = super.slotClick(slotId, dragType, clickType, player); //Clicked Stack
            if(clickType == ClickType.PICKUP) {
                if(clicked.isEmpty()) {
                    System.out.println("DEBUG_1");
                } else {
                    System.out.println("DEBUG_2");
                }
                //System.out.println("DEBUG_3: " + super.slotClick(slotId, dragType, clickType, player));
                //System.out.println("DEBUG_4: " + clicked.toString());
                return ItemStack.EMPTY;
            }
        } else {
            if(clickType == ClickType.PICKUP) {
                //Pickup auserhalb des Slots
                ItemStack clicked = super.slotClick(slotId, dragType, clickType, player);
                System.out.println("CLICKED: " + clicked.toString());
                return clicked;
            }
        }*/
        return super.slotClick(slotId, dragType, clickType, player);
    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}