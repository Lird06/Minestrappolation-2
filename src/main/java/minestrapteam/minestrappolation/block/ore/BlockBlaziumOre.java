package minestrapteam.minestrappolation.block.ore;

import java.util.Random;

import minestrapteam.minestrappolation.lib.MItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockBlaziumOre extends Block
{
	public BlockBlaziumOre()
	{
		super(Material.rock);
		this.setLightLevel(0.5F);
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return MathHelper.clamp_int(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 2 + random.nextInt(1);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return MItems.blazeShard;
	}
	
	@Override
	public int getExpDrop(IBlockAccess world, int metadata, int fortune)
	{
		return 1 + fortune;
	}
}
