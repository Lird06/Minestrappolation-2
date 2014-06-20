package minestrapteam.minestrap_core;

import minestrapteam.minestrap_core.common.MCCommonProxy;
import minestrapteam.minestrap_core.lib.MCBlocks;
import minestrapteam.minestrap_core.lib.MCItems;
import minestrapteam.minestrap_core.lib.MCRecipes;
import minestrapteam.minestrap_core.lib.MCReference;
import minestrapteam.minestrap_core.tileentity.TileEntityStonecutter;

import net.minecraft.init.Blocks;

import clashsoft.brewingapi.BrewingAPI;
import clashsoft.cslib.config.CSConfig;
import clashsoft.cslib.minecraft.init.BaseMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MCReference.CORE_MODID, name = MCReference.CORE_NAME, version = MCReference.CORE_VERSION, dependencies = "required-after:" + BrewingAPI.MODID)
public class MinestrappolationCore extends BaseMod
{
	@Instance(MCReference.CORE_MODID)
	public static MinestrappolationCore	instance;
	
	@SidedProxy(modId = MCReference.CORE_MODID, clientSide = "minestrapteam.minestrap_core.client.MCClientProxy", serverSide = "minestrapteam.minestrap_core.common.MCCommonProxy")
	public static MCCommonProxy			proxy;
	
	public static boolean				showDurability;
	
	public MinestrappolationCore()
	{
		super(proxy, MCReference.CORE_MODID, MCReference.CORE_NAME, MCReference.CORE_ACRONYM, MCReference.CORE_VERSION);
		this.authors = MCReference.AUTHORS;
	}
	
	@Override
	public void readConfig()
	{
		showDurability = CSConfig.getBool("misc", "Show Durability", true);
	}
	
	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		super.proxy = proxy;
		
		super.preInit(event);
		
		MCBlocks.init();
		MCItems.init();
		MCRecipes.init();
	}
	
	@Override
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		
		Blocks.bedrock.setHardness(80F);
		GameRegistry.registerTileEntity(TileEntityStonecutter.class, "StoneCutter");
	}
	
	@Override
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
}
