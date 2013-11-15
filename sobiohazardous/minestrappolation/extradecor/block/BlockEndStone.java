package sobiohazardous.minestrappolation.extradecor.block;

import java.util.List;

import sobiohazardous.minestrappolation.extradecor.lib.EDBlockManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockEndStone extends Block {

	public static final String[] bType = new String[] {"brick","smooth","refined"};

	private Icon refined;
	private Icon smooth;
	private Icon brick;
	public BlockEndStone(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(EDBlockManager.tabDecorBlocks);
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.brick = par1IconRegister
				.registerIcon("Minestrappolation:block_EndstoneBrick");
		this.refined = par1IconRegister
				.registerIcon("Minestrappolation:block_EndstoneRefined");
		this.smooth = par1IconRegister
				.registerIcon("Minestrappolation:block_EndstoneSmooth");

	}
	
	public int damageDropped(int par1)
    {
        return par1;
    }
	
	 @SideOnly(Side.CLIENT)
		public Icon getIcon(int i, int j) {
			switch (j) {
			case 0:
				return brick;
			case 1:
				return refined;
			case 2:
				return smooth;
			}
			return refined;

		}
	    @SideOnly(Side.CLIENT)

		/**
		 * returns a list of blocks with the same ID, but different meta (eg: wood
		 * returns 4 blocks)
		 */
		public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
				List par3List) {
			par3List.add(new ItemStack(par1, 1, 0));
			par3List.add(new ItemStack(par1, 1, 1));
			par3List.add(new ItemStack(par1, 1, 2));
		}

}