package minestrapteam.minestrappolation.block;

import minestrapteam.minestrappolation.common.MCommonProxy;
import minestrapteam.minestrappolation.tileentity.TileEntityLocked;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class BlockLocked extends BlockContainer
{
	public BlockLocked()
	{
		super(Material.iron);
	}
	
	@Override
	public int getRenderType()
	{
		return MCommonProxy.lockedRenderID;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			this.addInfoMessage(world, x, y, z, player);
			return false;
		}
		return true;
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			this.addInfoMessage(world, x, y, z, player);
		}
	}
	
	private void addInfoMessage(World world, int x, int y, int z, EntityPlayer player)
	{
		TileEntityLocked locked = (TileEntityLocked) world.getTileEntity(x, y, z);
		String owner = locked.getOwner();
		if (owner == null || owner.isEmpty())
		{
			player.addChatMessage(new ChatComponentTranslation("lock.locked.no_owner"));
		}
		else
		{
			player.addChatMessage(new ChatComponentTranslation("lock.locked", locked.getOwner()));
		}
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean isLockable(Block block, int metadata)
	{
		return block.hasTileEntity(metadata) || block instanceof BlockDoor;
	}
	
	public boolean lock(String owner, World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (!this.isLockable(block, metadata))
		{
			return false;
		}
		
		world.setBlock(x, y, z, this, metadata, 3);
		
		TileEntityLocked locked = (TileEntityLocked) world.getTileEntity(x, y, z);
		locked.setOwner(owner);
		locked.setBlock(block);
		locked.setTileEntity(tileEntity);
		
		return true;
	}
	
	public boolean unlock(String owner, World world, int x, int y, int z)
	{
		TileEntityLocked locked = (TileEntityLocked) world.getTileEntity(x, y, z);
		if (owner != null && !locked.isOwner(owner))
		{
			return false;
		}
		
		Block block = locked.getBlock();
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity tileEntity = locked.getTileEntity();
		
		world.setBlock(x, y, z, block, metadata, 3);
		world.setTileEntity(x, y, z, tileEntity);
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityLocked();
	}
}
