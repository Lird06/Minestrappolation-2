package minestrapteam.minestrappolation.tileentity;

import clashsoft.cslib.minecraft.stack.CSStacks;
import clashsoft.cslib.minecraft.tileentity.TileEntityInventory;
import minestrapteam.minestrappolation.block.BlockMelter;
import minestrapteam.minestrappolation.crafting.MelterRecipes;
import minestrapteam.minestrappolation.lib.MItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityMelter extends TileEntityInventory implements ISidedInventory
{
	public int					burnTime;
	public int					maxBurnTime;
	public int					meltTime;
	public int					front;
	
	public boolean				hasPower;
	
	private static final int[]	topInputSlot	= new int[] { 0 };
	private static final int[]	outputSlots		= new int[] { 2, 1 };
	private static final int[]	inputSlots		= new int[] { 1 };
	
	public TileEntityMelter()
	{
		super(4);
	}
	
	public void setFrontDirection(int f)
	{
		this.front = f;
	}
	
	public int getFrontDirection()
	{
		return this.front;
	}
	
	public final int getMaxMeltTime()
	{
		return this.hasPower ? 800 : 1600;
	}
	
	@Override
	public int getSizeInventory()
	{
		return 4;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		this.front = nbt.getInteger("FrontDirection");
		this.burnTime = nbt.getShort("BurnTime");
		this.meltTime = nbt.getShort("CookTime");
		this.maxBurnTime = getItemBurnTime(this.itemStacks[1]);
	}
	
	public boolean isPowered()
	{
		return this.hasPower;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger("FrontDirection", this.front);
		nbt.setShort("BurnTime", (short) this.burnTime);
		nbt.setShort("CookTime", (short) this.meltTime);
	}
	
	public int getProgressScaled(int scalar)
	{
		if (!this.hasPower)
		{
			return this.meltTime * scalar / this.getMaxMeltTime();
		}
		return this.meltTime * scalar / this.getMaxMeltTime();
	}
	
	public int getBurnTimeRemainingScaled(int scalar)
	{
		if (this.maxBurnTime == 0)
		{
			this.maxBurnTime = 200;
		}
		
		return this.burnTime * scalar / this.maxBurnTime;
	}
	
	public boolean isBurning()
	{
		return this.burnTime > 0;
	}
	
	@Override
	public void updateEntity()
	{
		this.hasPower = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
		boolean burning = this.burnTime > 0;
		
		if (burning)
		{
			if (this.hasPower)
			{
				this.burnTime -= 16;
			}
			else
			{
				this.burnTime -= 4;
			}
		}
		
		if (this.worldObj.isRemote)
		{
			return;
		}
		
		if (this.canSmelt())
		{
			if (this.burnTime == 0)
			{
				this.maxBurnTime = this.burnTime = getItemBurnTime(this.itemStacks[1]);
				if (this.burnTime > 0)
				{
					this.markDirty();
					if (this.itemStacks[1] != null)
					{
						--this.itemStacks[1].stackSize;
						if (this.itemStacks[1].stackSize == 0)
						{
							this.itemStacks[1] = this.itemStacks[1].getItem().getContainerItem(this.itemStacks[1]);
						}
					}
				}
			}
			else if (this.isBurning())
			{
				++this.meltTime;
				if (this.meltTime >= this.getMaxMeltTime())
				{
					this.meltTime = 0;
					this.smeltItem();
					this.markDirty();
				}
			}
			else
			{
				this.meltTime = 0;
			}
		}
		else
		{
			this.meltTime = 0;
		}
		
		if (burning != this.burnTime > 0)
		{
			this.markDirty();
			this.validate();
			BlockMelter.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
	}
	
	private boolean canSmelt()
	{
		if (this.itemStacks[0] != null)
		{
			ItemStack itemstack = MelterRecipes.instance.getResult(this.itemStacks[0]);
			if (itemstack == null)
			{
				return false;
			}
			ItemStack container = itemstack.getItem().getContainerItem(itemstack);
			if (container != null)
			{
				if (!CSStacks.itemEquals(container, this.itemStacks[3]))
				{
					return false;
				}
			}
			if (this.itemStacks[2] == null)
			{
				return true;
			}
			if (!this.itemStacks[2].isItemEqual(itemstack))
			{
				return false;
			}
			int result = this.itemStacks[2].stackSize + itemstack.stackSize;
			return result <= itemstack.getMaxStackSize();
		}
		return false;
	}
	
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = MelterRecipes.instance.getResult(this.itemStacks[0]);
			if (this.itemStacks[2] == null)
			{
				this.itemStacks[2] = itemstack.copy();
			}
			else if (this.itemStacks[2].isItemEqual(itemstack))
			{
				this.itemStacks[2].stackSize += itemstack.stackSize;
			}
			
			--this.itemStacks[0].stackSize;
			if (this.itemStacks[0].stackSize <= 0)
			{
				this.itemStacks[0] = null;
			}
			
			if (this.itemStacks[3] != null && CSStacks.equals(this.itemStacks[3], itemstack.getItem().getContainerItem(itemstack)))
			{
				--this.itemStacks[3].stackSize;
				if (this.itemStacks[3].stackSize <= 0)
				{
					this.itemStacks[3] = null;
				}
			}
		}
	}
	
	public boolean isItemFuel(ItemStack stack)
	{
		return getItemBurnTime(stack) > 0;
	}
	
	public static int getItemBurnTime(ItemStack stack)
	{
		if (stack == null)
		{
			return 0;
		}
		
		int i = TileEntityFurnace.getItemBurnTime(stack);
		if (i == 0)
		{
			Item item = stack.getItem();
			if (item == MItems.uranium)
			{
				i = 150000;
			}
			else if (item == MItems.plutonium)
			{
				i = 200000;
			}
		}
		
		return i;
	}
	
	@Override
	public boolean canInsertItem(int slotID, ItemStack stack, int side)
	{
		return this.isItemValidForSlot(slotID, stack);
	}
	
	@Override
	public boolean canExtractItem(int slotID, ItemStack stack, int side)
	{
		return side == 0 && slotID != 1 || stack.getItem() == Items.bucket;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return side == 0 ? outputSlots : side == 1 ? topInputSlot : inputSlots;
	}
}