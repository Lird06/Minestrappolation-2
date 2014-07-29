package minestrapteam.minestrappolation.nei;

import java.util.List;

import clashsoft.cslib.minecraft.lang.I18n;
import clashsoft.cslib.minecraft.stack.CSStacks;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapelessRecipeHandler;
import minestrapteam.minestrappolation.client.gui.GuiStonecutter;
import minestrapteam.minestrappolation.crafting.stonecutter.ISCRecipe;
import minestrapteam.minestrappolation.crafting.stonecutter.ShapelessSCRecipe;
import minestrapteam.minestrappolation.crafting.stonecutter.StonecutterCraftingManager;
import minestrapteam.minestrappolation.util.MAssetManager;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class NEIShapelessStonecutterManager extends ShapelessRecipeHandler
{
	public class CachedShapelessSCRecipe extends CachedShapelessRecipe
	{
		private PositionedStack	extraSlot;
		
		public CachedShapelessSCRecipe(ShapelessSCRecipe recipe)
		{
			this.setIngredients(recipe.recipeItems);
			this.setResult(recipe.recipeOutput);
			this.setExtraSlot(recipe.extraSlot);
		}
		
		public void setExtraSlot(ItemStack extra)
		{
			if (extra != null)
			{
				this.extraSlot = new PositionedStack(extra, 3, 24);
			}
		}
		
		public CachedShapelessSCRecipe(ItemStack extra, Object[] input, ItemStack output)
		{
			super(input, output);
			this.setExtraSlot(extra);
		}
		
		public CachedShapelessSCRecipe(ItemStack extra, List<?> input, ItemStack output)
		{
			super(input, output);
			this.setExtraSlot(extra);
		}
		
		@Override
		public void setIngredients(List<?> items)
		{
			this.ingredients.clear();
			for (int ingred = 0; ingred < items.size(); ingred++)
			{
				PositionedStack stack = new PositionedStack(items.get(ingred), 39 + NEIShapelessStonecutterManager.this.stackorder[ingred][0] * 18, 6 + NEIShapelessStonecutterManager.this.stackorder[ingred][1] * 18);
				stack.setMaxSize(1);
				this.ingredients.add(stack);
			}
		}
		
		@Override
		public PositionedStack getOtherStack()
		{
			return this.extraSlot;
		}
	}
	
	@Override
	public String getGuiTexture()
	{
		return MAssetManager.getTexture("textures/gui/container/stonecutter.png");
	}
	
	@Override
	public String getRecipeName()
	{
		return I18n.getString("recipe.stonecutter.shapeless");
	}
	
	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiStonecutter.class;
	}
	
	@Override
	public boolean hasOverlay(GuiContainer gui, Container container, int recipe)
	{
		return false;
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if ("stonecutter".equals(outputId))
		{
			for (ISCRecipe irecipe : StonecutterCraftingManager.instance.getRecipeList())
			{
				if (irecipe instanceof ShapelessSCRecipe)
				{
					NEIShapelessStonecutterManager manager = NEIMinestrappolationConfig.shapelessStonecutterManager;
					CachedShapelessSCRecipe recipe = manager.new CachedShapelessSCRecipe((ShapelessSCRecipe) irecipe);
					this.arecipes.add(recipe);
				}
			}
		}
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for (ISCRecipe irecipe : StonecutterCraftingManager.instance.getRecipeList())
		{
			if (irecipe instanceof ShapelessSCRecipe)
			{
				if (CSStacks.itemEquals(irecipe.getRecipeOutput(), result))
				{
					CachedShapelessSCRecipe recipe = new CachedShapelessSCRecipe((ShapelessSCRecipe) irecipe);
					this.arecipes.add(recipe);
				}
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		for (ISCRecipe irecipe : StonecutterCraftingManager.instance.getRecipeList())
		{
			if (irecipe instanceof ShapelessSCRecipe)
			{
				CachedShapelessRecipe recipe = new CachedShapelessSCRecipe((ShapelessSCRecipe) irecipe);
				
				if (recipe.contains(recipe.ingredients, ingredient))
				{
					recipe.setIngredientPermutation(recipe.ingredients, ingredient);
					this.arecipes.add(recipe);
				}
			}
		}
	}
}