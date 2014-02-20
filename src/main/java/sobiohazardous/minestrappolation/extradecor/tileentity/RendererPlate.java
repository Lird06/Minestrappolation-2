package sobiohazardous.minestrappolation.extradecor.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RendererPlate extends TileEntitySpecialRenderer {
	
	ResourceLocation texture = new ResourceLocation("minestrappolation:textures/blocks/block_Plate.png");

	private ModelPlate model;
	public RendererPlate() {
		this.model = new ModelPlate();
	}

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + .5F, (float) y + 1.5F, (float) z + 0.5F);
		this.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glRotatef(180, 0F, 0F, 1F);
		model.render(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();

	}

}