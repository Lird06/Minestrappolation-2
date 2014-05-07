package sobiohazardous.mods.minestrappolation.core.item;

import sobiohazardous.mods.minestrappolation.core.lib.MReference;
import sobiohazardous.mods.minestrappolation.core.util.MAssetManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MItemArmor extends ItemArmor
{
	private String			armorPrefix;
	
	private IIcon			overlayIcon;
	
	private ArmorMaterial	material;
	
	private ArmorMaterial	norm;
	private ArmorMaterial	bronzePlateMat;
	
	public MItemArmor(ArmorMaterial par2EnumArmorMaterial, ArmorMaterial bronzeMaterial, int par3, int par4, String armorPrefix)
	{
		super(par2EnumArmorMaterial, par3, par4);
		this.material = par2EnumArmorMaterial;
		this.setCreativeTab(null);
		this.armorPrefix = armorPrefix;
		this.norm = par2EnumArmorMaterial;
		this.bronzePlateMat = bronzeMaterial;
	}
	
	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.getBoolean("bronzePlated"))
		{
			this.material = this.bronzePlateMat;
		}
		else
		{
			this.material = this.norm;
		}
	}
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if (par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.getBoolean("bronzePlated"))
		{
			this.material = this.bronzePlateMat;
		}
		else
		{
			this.material = this.norm;
		}
	}
	
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(this.getIconString());
		
		if (this.renderIndex == 0)
		{
			this.overlayIcon = par1IconRegister.registerIcon(MAssetManager.getEOTexture("overlayArmorHelmBronze"));
		}
		else if (this.renderIndex == 1)
		{
			this.overlayIcon = par1IconRegister.registerIcon(MAssetManager.getEOTexture("overlayArmorChestBronze"));
		}
		else if (this.renderIndex == 2)
		{
			this.overlayIcon = par1IconRegister.registerIcon(MAssetManager.getEOTexture("overlayArmorLegsBronze"));
		}
		else
		{
			this.overlayIcon = par1IconRegister.registerIcon(MAssetManager.getEOTexture("overlayArmorBootsBronze"));
		}
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int renderPass)
	{
		if (renderPass == 0)
		{
			return this.itemIcon;
		}
		else if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("bronzePlated"))
		{
			return this.overlayIcon;
		}
		else
		{
			return this.itemIcon;
		}
	}
	
	private int	colorA	= 421010;
	
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int renderPass)
	{
		if (renderPass == 0)
		{
			return this.colorA;
		}
		return 16777215;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		String layer = "1";
		
		String material = this.armorPrefix;
		
		if (slot == 2)
		{
			layer = "2";
		}
		return MReference.MODID_EO + ":textures/armor/" + material + layer + ".png";
	}
}
