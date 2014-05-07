package sobiohazardous.mods.minestrappolation.extramobdrops.tileentity;

import org.lwjgl.opengl.GL11;

import sobiohazardous.mods.minestrappolation.extramobdrops.entity.EntityHangGlider;
import sobiohazardous.mods.minestrappolation.extramobdrops.handler.EMDEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RenderHangGlider extends Render
{
	public ResourceLocation rl = new ResourceLocation("extramobdrops:textures/misc/hangGlider.png");
	
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		EntityHangGlider glider = (EntityHangGlider)var1;
		EntityPlayer player = glider.player;
		
		GL11.glPushMatrix();
		ModelHangGlider m = new ModelHangGlider();		
		Minecraft.getMinecraft().getTextureManager().bindTexture(rl);
		int tick = 0;
		tick++;
		float rotateYaw = EMDEventHandler.interpolateRotation(player.prevRotationYaw, player.rotationYaw, tick);
		GL11.glRotatef(rotateYaw, 0, -1, 0);
		GL11.glRotatef(180F, 0, 0, 1);
		GL11.glTranslatef(0, 0, -0.5F);				
		m.render(0.0625F);
		GL11.glPopMatrix();		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1)
	{
		return rl;
	}

}