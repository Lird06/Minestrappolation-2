package com.minestrappolation.block;

import com.minestrappolation.Minestrappolation;
import com.minestrappolation.tileentity.TileEntityBarrel;
import com.minestrappolation_core.util.MCAssetManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBarrel extends BlockFalling implements ITileEntityProvider
{
	protected IIcon	top;
	
	public BlockBarrel()
	{
		super(Material.wood);
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
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(MCAssetManager.getMachineTexture("barrel_side"));
		this.top = iconRegister.registerIcon(MCAssetManager.getMachineTexture("barrel_top"));
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		super.breakBlock(world, x, y, z, block, metadata);
		world.removeTileEntity(x, y, z);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			player.openGui(Minestrappolation.instance, 1, world, x, y, z);
		}
		return true;
	}
	
	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int data)
	{
		super.onBlockEventReceived(world, x, y, z, id, data);
		TileEntity te = world.getTileEntity(x, y, z);
		return te != null ? te.receiveClientEvent(id, data) : false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityBarrel();
	}
}