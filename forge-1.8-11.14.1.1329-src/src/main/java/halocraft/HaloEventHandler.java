package halocraft;

import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class HaloEventHandler extends Gui{
	private Minecraft mc;
	ResourceLocation res = new ResourceLocation("halocraft:textures/gui/HaloOverlay.png");
	ResourceLocation texture = new ResourceLocation("halocraft:textures/gui/HealthBar.png");
	 public HaloEventHandler(Minecraft mc){
	    super();
	    // We need this to invoke the render engine.
	    this.mc = mc;
	  }
	 /**
		 * Draws textured rectangles of sizes other than 256x256
		 * @param x The x value of the top-left corner point on the screen where drawing to starts 
		 * @param y The y value of the top-left corner point on the screen where drawing to starts
		 * @param u The u (x) value of top-left corner point of the texture to start drawing from
		 * @param v The v (y) value of top-left corner point of the texture to start drawing from
		 * @param width The width of the rectangle to draw on screen
		 * @param height The height of the rectangle to draw on screen
		 * @param textureWidth The width of the whole texture
		 * @param textureHeight The height of the whole texture
		 */
	 	protected void drawNonStandardTexturedRect(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight)
	    {
	        double f = 1F / (double)textureWidth;
	        double f1 = 1F / (double)textureHeight;
	        WorldRenderer tessellator = Tessellator.getInstance().getWorldRenderer();
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV(x, y + height, 0, u * f, (v + height) * f1);
	        tessellator.addVertexWithUV(x + width, y + height, 0, (u + width) * f, (v + height) * f1);
	        tessellator.addVertexWithUV(x + width, y, 0, (u + width) * f, v * f1);
	        tessellator.addVertexWithUV(x, y, 0, u * f, v * f1);
	        Tessellator.getInstance().draw();
	    }
	 	@SubscribeEvent(priority = EventPriority.NORMAL)
	 	public void onRenderGameOverlay(RenderGameOverlayEvent event)
	 	{
		  	if(event.isCancelable() || event.type != ElementType.EXPERIENCE){
		  		return;
	    	}
		  	ItemStack helmet = mc.thePlayer.inventory.armorItemInSlot(3);
		  	if(helmet != null){
	    	if(helmet.getItem() == halocraft.Main.SpartanHelmet || helmet.getItem() == halocraft.Main.GreenSpartanHelmet || helmet.getItem() == halocraft.Main.BlueSpartanHelmet || helmet.getItem() == halocraft.Main.RedSpartanHelmet){
	    		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	    		int xPos = (scaled.getScaledWidth() - 420) / 2;
	    		int yPos = (scaled.getScaledHeight() - 250) / 2;
	    		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    		GL11.glDisable(GL11.GL_LIGHTING);
	    		this.mc.renderEngine.bindTexture(res);
	    		this.drawNonStandardTexturedRect(xPos, yPos, 0, 0, 420, 250, 420, 250);
	    		
	    		int xPosHealth = xPos + 169;
	    		int yPosHealth = yPos + 14;
	    		this.mc.getTextureManager().bindTexture(texture);

	    		// Add this block of code before you draw the section of your texture containing transparency
	    		GL11.glEnable(GL11.GL_BLEND);
	    		GL11.glDisable(GL11.GL_DEPTH_TEST);
	    		GL11.glDepthMask(false);
	    		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	    		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    		GL11.glDisable(GL11.GL_ALPHA_TEST);
	    		// Here we draw the background bar which contains a transparent section; note the new size
	    		drawTexturedModalRect(xPosHealth, yPosHealth, 0, 0, 86, 13);
	    		// You can keep drawing without changing anything
	    		int manabarwidth = (int)(((float) mc.thePlayer.getHealth() / mc.thePlayer.getMaxHealth()) * 90);
	    		drawTexturedModalRect(xPosHealth + 1, yPosHealth + 1, 0, 13, manabarwidth, 11);

	    		GL11.glDisable(GL11.GL_BLEND);
	    		GL11.glEnable(GL11.GL_DEPTH_TEST);
	    		GL11.glDepthMask(true);
	    	}
		  	}
		 }
}
