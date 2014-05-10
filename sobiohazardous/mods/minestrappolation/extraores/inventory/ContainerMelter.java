package sobiohazardous.mods.minestrappolation.extraores.inventory;

import java.util.List;

import sobiohazardous.mods.minestrappolation.extraores.crafting.MelterRecipes;
import sobiohazardous.mods.minestrappolation.extraores.tileentity.TileEntityMelter;
import clashsoft.cslib.minecraft.inventory.ContainerInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMelter extends ContainerInventory
{
	private TileEntityMelter	melter;
	private int					lastMeltTime;
	private int					lastBurnTime;
	private int					lastMaxBurnTime;
	
	public ContainerMelter(EntityPlayer player, TileEntityMelter melter)
	{
		super(player, melter);
		
		this.melter = melter;
		this.lastMeltTime = 0;
		this.lastBurnTime = 0;
		this.lastMaxBurnTime = 0;
		// burn
		this.addSlotToContainer(new Slot(melter, 1, 47, 53));
		// fuel
		this.addSlotToContainer(new Slot(melter, 0, 56, 17));
		// result
		this.addSlotToContainer(new SlotMelter(player, melter, 2, 116, 35));
		// bucket
		this.addSlotToContainer(new Slot(melter, 3, 66, 53));
		
		this.addInventorySlots();
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (ICrafting crafting : (List<ICrafting>) this.crafters)
		{
			if (this.lastMeltTime != this.melter.meltTime)
			{
				crafting.sendProgressBarUpdate(this, 0, this.melter.meltTime);
			}
			if (this.lastBurnTime != this.melter.burnTime)
			{
				crafting.sendProgressBarUpdate(this, 1, this.melter.burnTime);
			}
			if (this.lastMaxBurnTime != this.melter.maxBurnTime)
			{
				crafting.sendProgressBarUpdate(this, 2, this.melter.maxBurnTime);
			}
		}
		this.lastMeltTime = this.melter.meltTime;
		this.lastBurnTime = this.melter.burnTime;
		this.lastMaxBurnTime = this.melter.maxBurnTime;
	}
	
	@Override
	public void updateProgressBar(int id, int time)
	{
		if (id == 0)
		{
			this.melter.meltTime = time;
		}
		
		if (id == 1)
		{
			this.melter.burnTime = time;
		}
		
		if (id == 2)
		{
			this.melter.maxBurnTime = time;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.melter.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (slotID == 1 || slotID == 0)
			{
				if (!this.mergeItemStack(itemstack1, 3, 30, true))
				{
					return null;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (slotID == 1 || slotID == 0)
			{
				if (!this.mergeItemStack(itemstack1, 3, 30, false))
				{
					return null;
				}
			}
			else if (MelterRecipes.instance.getResult(itemstack1) != null)
			{
				if (!this.mergeItemStack(itemstack1, 1, 2, false))
				{
					return null;
				}
			}
			else if (this.melter.isItemFuel(itemstack1))
			{
				if (!this.mergeItemStack(itemstack1, 0, 1, false))
				{
					return null;
				}
			}
			
			else if (slotID == 2 || slotID == 1 || slotID == 0)
			{
				if (!this.mergeItemStack(itemstack1, 3, 39, false))
				{
					return null;
				}
			}
			else if (slotID >= 3 && slotID < 30)
			{
				if (!this.mergeItemStack(itemstack1, 30, 39, false))
				{
					return null;
				}
			}
			else if (slotID >= 30 && slotID < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
			{
				return null;
			}
			
			if (itemstack1.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
			
			slot.onPickupFromSlot(player, itemstack);
		}
		
		return itemstack;
	}
}