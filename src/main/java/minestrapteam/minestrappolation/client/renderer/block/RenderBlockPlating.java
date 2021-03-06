package minestrapteam.minestrappolation.client.renderer.block;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import minestrapteam.minestrappolation.common.MCommonProxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderBlockPlating implements ISimpleBlockRenderingHandler
{
	public RenderBlockPlating()
	{
	}
	
	@Override
	public int getRenderId()
	{
		return MCommonProxy.platingRenderID;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int metadata, RenderBlocks renderer)
	{
		// boolean n = world.isSideSolid(x, y, z + 1, SOUTH, false);
		// boolean e = world.isSideSolid(x + 1, y, z, WEST, false);
		// boolean s = world.isSideSolid(x, y, z - 1, NORTH, false);
		// boolean w = world.isSideSolid(x - 1, y, z, EAST, false);
		// boolean ne = n || e || (world.isSideSolid(x + 1, y, z + 1, SOUTH,
		// false) && world.isSideSolid(x + 1, y, z + 1, WEST, false));
		// boolean nw = n || w || (world.isSideSolid(x - 1, y, z + 1, SOUTH,
		// false) && world.isSideSolid(x - 1, y, z + 1, EAST, false));
		// boolean se = s || e || (world.isSideSolid(x + 1, y, z - 1, NORTH,
		// false) && world.isSideSolid(x + 1, y, z - 1, WEST, false));
		// boolean sw = s || w || (world.isSideSolid(x - 1, y, z - 1, NORTH,
		// false) && world.isSideSolid(x - 1, y, z - 1, EAST, false));
		//
		
		boolean xp = world.isSideSolid(x + 1, y, z, WEST, false);
		boolean xn = world.isSideSolid(x - 1, y, z, EAST, false);
		boolean zp = world.isSideSolid(x, y, z + 1, NORTH, false);
		boolean zn = world.isSideSolid(x, y, z - 1, SOUTH, false);
		
		Tessellator tessellator = Tessellator.instance;
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
		boolean flag = true;
		double x0 = x;
		double x1 = x + 1D;
		double z0 = z;
		double z1 = z + 1D;
		double y0 = y;
		double y1 = y + 1D;
		
		IIcon icon = block.getIcon(world, x, y, z, 1);
		float minU = icon.getMinU();
		float maxU = icon.getMaxU();
		float minV = icon.getMinV();
		float maxV = icon.getMaxV();
		
		if (xp && !xn)
		{
			drawFace(x0, x + 1D, z0, z1, y0, y0, y1, y1, minU, minV, maxU, maxV);
			flag = false;
		}
		if (!xp && xn)
		{
			drawFace(x0, x1, z0, z1, y1, y1, y0, y0, minU, minV, maxU, maxV);
			flag = false;
		}
		if (zp && !zn)
		{
			drawFace(x0, x1, z0, z1, y0, y1, y1, y0, minU, minV, maxU, maxV);
			flag = false;
		}
		if (!zp && zn)
		{
			drawFace(x0, x1, z0, z1, y1, y0, y0, y1, minU, minV, maxU, maxV);
			flag = false;
		}
		if (flag)
		{
			drawFace(x0, x1, z0, z1, y0, y0, y0, y0, minU, minV, maxU, maxV);
		}
		
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int metadata)
	{
		return false;
	}
	
	private static void drawFace(double x1, double x2, double z1, double z2, double y1, double y2, double y3, double y4, double u1, double v1, double u2, double v2)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.addVertexWithUV(x1, y1, z1, u1, v2);
		tessellator.addVertexWithUV(x1, y2, z2, u1, v1);
		tessellator.addVertexWithUV(x2, y3, z2, u2, v1);
		tessellator.addVertexWithUV(x2, y4, z1, u2, v2);
		tessellator.addVertexWithUV(x2, y4, z1, u2, v2);
		tessellator.addVertexWithUV(x2, y3, z2, u2, v1);
		tessellator.addVertexWithUV(x1, y2, z2, u1, v1);
		tessellator.addVertexWithUV(x1, y1, z1, u1, v2);
	}
}
