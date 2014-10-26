package minestrapteam.minestrappolation.inventory;

import clashsoft.cslib.minecraft.inventory.ContainerInventory;
import minestrapteam.minestrappolation.tileentity.TileEntityBarrel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBarrel extends ContainerInventory
{
	public ContainerBarrel(EntityPlayer player, TileEntityBarrel barrel)
	{
		super(player, barrel);
		
		for (int j = 0; j < 4; ++j)
		{
			for (int k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new Slot(barrel, k + j * 9, 8 + k * 18, 19 + j * 18));
			}
		}
		this.addInventorySlots(0, 21);
	}
	
	@Override
	public int[] merge(EntityPlayer player, int slot, ItemStack stack)
	{
		return new int[] { 0, 36 };
	}
}