package sobiohazardous.mods.minestrappolation.extraores.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sobiohazardous.mods.minestrappolation.core.item.MItemShovel;

public class ToriteToolShovel extends MItemShovel
{
	public ToriteToolShovel(ToolMaterial material, ToolMaterial plateMaterial)
	{
		super(material, plateMaterial);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.block;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}
}
