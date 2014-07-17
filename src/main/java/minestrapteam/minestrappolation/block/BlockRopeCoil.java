package minestrapteam.minestrappolation.block;

import minestrapteam.mcore.util.MCAssetManager;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockRopeCoil extends BlockFalling
{
	private IIcon	top;
	
	public BlockRopeCoil()
	{
		super(Material.cloth);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(MCAssetManager.getTexture("rope_side"));
		this.top = iconRegister.registerIcon(MCAssetManager.getTexture("rope_top"));
	}
	
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		if (side == 0 || side == 1)
		{
			return this.top;
		}
		
		return this.blockIcon;
	}
	

	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity)
	{
		return true;
	}
}
