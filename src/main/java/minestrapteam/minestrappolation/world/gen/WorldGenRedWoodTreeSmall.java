package minestrapteam.minestrappolation.world.gen;

import java.util.Random;

import minestrapteam.minestrappolation.lib.MBlocks;

import net.minecraft.world.World;

public class WorldGenRedWoodTreeSmall extends WorldGenBaseTree
{
	protected int	leafStart	= 5;
	protected int	leafSpacing	= 2;
	
	public WorldGenRedWoodTreeSmall()
	{
		super(MBlocks.redwoodLog, 0, MBlocks.redwoodLeaves, 0, 13, 18, 1);
	}
	
	@Override
	public boolean genLeafStructure(World world, Random rand, int x, int y, int z)
	{
		this.setBlock(world, x, y + this.topHeight, z, this.leaves, this.leavesMetadata);
		
		double thickness = 4D;
		for (int r = this.leafStart; r < this.topHeight; r++)
		{
			if (r % this.leafSpacing == 0)
			{
				this.generateLeafCircles(world, rand, thickness, x, z, y + r);
				thickness -= 0.4D;
			}
		}
		return true;
	}
	
	@Override
	public boolean genExtras(World world, Random rand, int x, int y, int z)
	{
		return false;
	}
}
