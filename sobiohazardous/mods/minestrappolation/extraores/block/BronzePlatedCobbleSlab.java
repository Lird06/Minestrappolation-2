package sobiohazardous.mods.minestrappolation.extraores.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sobiohazardous.mods.minestrappolation.core.util.MAssetManager;
import sobiohazardous.mods.minestrappolation.extraores.lib.EOBlocks;

public class BronzePlatedCobbleSlab extends BlockSlab
{
	public static final String[] woodType = { "bronzePlatedCobble" };

	public BronzePlatedCobbleSlab(boolean par2)
	{
		super(par2, Material.rock);
		//setBurnProperties(this, 5, 20);
		useNeighborBrightness = true;
	}

	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(MAssetManager.getEOStonecutterTexture("Stone_Cobbled_0_Bronze"));
	}

    public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(EOBlocks.bronzePlatedCobbleSingleSlab);
	}

	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(EOBlocks.bronzePlatedCobbleSingleSlab, 2, par1 & 7);
	}

	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) 
	{
         if (par1 != Item.getItemFromBlock(EOBlocks.bronzePlatedCobbleDoubleSlab))
         {
        	 par3List.add(new ItemStack(par1, 1, 0));
         }
	}
	
	public String func_150002_b(int p_150002_1_)
    {
        if (p_150002_1_ < 0 || p_150002_1_ >= woodType.length)
        {
            p_150002_1_ = 0;
        }

        return super.getUnlocalizedName() + "." + woodType[p_150002_1_];
    }

}