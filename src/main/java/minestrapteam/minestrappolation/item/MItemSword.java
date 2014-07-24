package minestrapteam.minestrappolation.item;

import java.util.Collections;

import clashsoft.cslib.minecraft.item.datatools.ItemDataSword;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MItemSword extends MItemTool
{
	public MItemSword(ToolMaterial material, boolean ignites)
	{
		super(ItemDataSword.baseDamage, material, Collections.EMPTY_SET, "sword", ignites);
		this.weapon = true;
	}
	
	public MItemSword(ToolMaterial material)
	{
		this(material, false);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase attacker)
	{
		if (this.ignites)
		{
			entity.setFire(10);
			stack.damageItem(2, attacker);
		}
		else
		{
			stack.damageItem(1, attacker);
		}
		return true;
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int metadata)
	{
		if (block == Blocks.web)
		{
			return 15.0F;
		}
		else
		{
			float f = ItemDataSword.isEfficientOnMaterial(block.getMaterial()) ? 1.5F : 1.0F;
			return super.getDigSpeed(stack, block, metadata) * f;
		}
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
	{
		if (block.getBlockHardness(world, x, y, z) != 0.0D)
		{
			stack.damageItem(2, living);
		}
		return true;
	}
	
	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		return block == Blocks.web;
	}
}