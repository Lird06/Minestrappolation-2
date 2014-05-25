package com.minestrappolation.client;

import com.minestrappolation.client.gui.GuiBarrel;
import com.minestrappolation.client.gui.GuiCrate;
import com.minestrappolation.client.gui.GuiMelter;
import com.minestrappolation.client.renderer.RenderGoblet;
import com.minestrappolation.client.renderer.RenderHangGlider;
import com.minestrappolation.client.renderer.RenderNukePrimed;
import com.minestrappolation.client.renderer.RenderPlate;
import com.minestrappolation.common.MCommonProxy;
import com.minestrappolation.entity.*;
import com.minestrappolation.lib.MItems;
import com.minestrappolation.tileentity.*;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MClientProxy extends MCommonProxy implements IGuiHandler
{
	@Override
	public void init(FMLInitializationEvent event)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoblet.class, new RenderGoblet());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlate.class, new RenderPlate());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHangGlider.class, new RenderHangGlider());
		RenderingRegistry.registerEntityRenderingHandler(EntityNukePrimed.class, new RenderNukePrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(MItems.grenade));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeImpact.class, new RenderSnowball(MItems.grenadeImpact));
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenadeSticky.class, new RenderSnowball(MItems.grenadeSticky));
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == 0)
		{
			return new GuiCrate(player, (TileEntityCrate) world.getTileEntity(x, y, z));
		}
		else if (id == 1)
		{
			return new GuiBarrel(player, (TileEntityBarrel) world.getTileEntity(x, y, z));
		}
		if (id == 2)
		{
			return new GuiMelter(player, (TileEntityMelter) world.getTileEntity(x, y, z));
		}
		return null;
	}
	
	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}