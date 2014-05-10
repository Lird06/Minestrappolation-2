package sobiohazardous.mods.minestrappolation.extraores.block;

import java.util.Random;

import sobiohazardous.mods.minestrappolation.core.util.MAssetManager;
import sobiohazardous.mods.minestrappolation.core.util.MUtil;
import sobiohazardous.mods.minestrappolation.extraores.lib.EOConfig;
import sobiohazardous.mods.minestrappolation.extraores.lib.EOItems;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockUraniumOre extends BlockRadiation
{
	public BlockUraniumOre(Material material)
	{
		super(material);
	}
	
	public void registerIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(MAssetManager.getEOTexture("oreUranium"));
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return EOItems.Uranium;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1 + random.nextInt(2);
	}
	
	@Override
	public void addPotionEffect(EntityLivingBase living)
	{
		if (EOConfig.shouldOresEffect)
		{
			if (living instanceof EntityZombie)
			{
				living.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 180, 1, false));
				living.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 180, 0, false));
			}
			else
			{
				living.addPotionEffect(new PotionEffect(Potion.poison.getId(), 120, 1, false));
			}
		}
	}
	
	public void spawnParticle(World world, int x, int y, int z)
	{
		MUtil.spawnParticle2(world, x, y, z, world.rand, "smoke");
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata)
	{
		this.dropXpOnBlockBreak(world, x, y, z, world.rand.nextInt(6) + 1);
	}
}