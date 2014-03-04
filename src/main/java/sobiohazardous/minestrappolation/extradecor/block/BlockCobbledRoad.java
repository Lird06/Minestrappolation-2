package sobiohazardous.minestrappolation.extradecor.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import sobiohazardous.minestrappolation.extradecor.ExtraDecor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCobbledRoad extends Block
{
	public Entity entity;
	public EntityLivingBase living;
	private IIcon top;
	private IIcon bottom;
	
    public BlockCobbledRoad()
    {
        super(Material.ground);
    }

    
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("Minestrappolation:block_CobbledRoadSide");
        this.top = par1IconRegister.registerIcon("Minestrappolation:block_CobbledRoadTop");
        this.bottom = par1IconRegister.registerIcon("Minestrappolation:block_CobbledRoadBottom");
    }
    
    public IIcon getIcon(int i, int j)
    {
    	if (i == 0)//bottom
            
            return bottom;
    	if (i == 1)//top
           
            return top;
   
    	if (i == 2) // side
           
            return blockIcon;
    	if (i == 3)//side 
           
            return blockIcon;
    	if (i == 4) //side
   
    		return blockIcon;
    	if (i == 5) //side
   
    		return blockIcon;

    	if (j ==1)
    	{
    		return blockIcon;
    	}
		return blockIcon;
    }
    
    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	par5Entity.motionX *= 1.4;
		par5Entity.motionZ *= 1.4;
    }
}