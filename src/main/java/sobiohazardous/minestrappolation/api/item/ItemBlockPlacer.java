package sobiohazardous.minestrappolation.api.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockPlacer extends Item 
{
	private Block blockID;
	private int meta;

	public ItemBlockPlacer(int par1, Block block) 
	{
		this.blockID = block;	
	}
	
	public ItemBlockPlacer(int par1, Block block, int meta) 
	{
		this.blockID = block;
		this.meta = meta;	
	}
	
	/*
	public void registerIcons(IIconRegister r)
	{
		itemIcon = r.registerIcon("Minestrappolation:" + this.getUnlocalizedName().substring(5));
	}
	*/
	//TODO test icons
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
	    {
	            --par5;
	    }

	    if (par7 == 1)
	    {
	        ++par5;
	    }

	    if (par7 == 2)
	    {
	        --par6;
	    }

	    if (par7 == 3)
	    {
	        ++par6;
	    }

	    if (par7 == 4)
	    {
	        --par4;
	    }

	    if (par7 == 5)
	    {
	        ++par4;
	    }		  
	    par3World.setBlock(par4, par5, par6, this.blockID,this.meta,2);
		par1ItemStack.stackSize--;		  
	    return true;	        
	}

}