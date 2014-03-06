package sobiohazardous.minestrappolation.extradecor.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import sobiohazardous.minestrappolation.api.lib.MAPIReference;
import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import sobiohazardous.minestrappolation.extradecor.lib.EDBlockManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWoodPanel extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
    public static final String[] panelTextures = new String[] {MAPIReference.MODID_ED + ":panelOak", MAPIReference.MODID_ED + ":panelBirch", MAPIReference.MODID_ED + ":panelSpruce", MAPIReference.MODID_ED + ":panelJungle"};
    public static final String[] woodType = new String[] {"oak", "birch", "spruce", "jungle"};
    
    public BlockWoodPanel()
    {
        super(Material.wood);
        this.setCreativeTab(EDBlockManager.tabDecorBlocks);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.iconArray.length)
        {
            par2 = 0;
        }

        return this.iconArray[par2];
    }
    
    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	par3List.add(new ItemStack(par1, 1, 0));
    	par3List.add(new ItemStack(par1, 1, 1));
    	par3List.add(new ItemStack(par1, 1, 2));
    	par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.iconArray = new IIcon[panelTextures.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(panelTextures[i]);
        }
    }
    
    public int getFlammability(IBlockAccess world, int x, int y, int z,ForgeDirection face)
    {
    	if(world.getBlock(x, y, z) == EDBlockManager.woodPanel || world.getBlock(x, y, z) == EDBlockManager.woodBeveled)
    	{
    		if(face == ForgeDirection.UP || face == ForgeDirection.DOWN || face == ForgeDirection.NORTH || face == ForgeDirection.SOUTH || face == ForgeDirection.EAST || face == ForgeDirection.WEST)
    		{
                return 75;
    		}
    		else
    			return 0;
    	}
    	else
    		return 0;
    }
}
