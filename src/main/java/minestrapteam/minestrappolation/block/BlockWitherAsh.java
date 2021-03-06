package minestrapteam.minestrappolation.block;

import java.util.Random;

import minestrapteam.minestrappolation.lib.MItems;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWitherAsh extends BlockFalling
{
	public BlockWitherAsh()
	{
		super(Material.craftedSnow);
		this.setTickRandomly(true);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return MItems.witherAsh;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 4;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 11)
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
	{
		return true;
	}
}