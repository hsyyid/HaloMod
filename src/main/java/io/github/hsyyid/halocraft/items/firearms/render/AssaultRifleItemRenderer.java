package io.github.hsyyid.halocraft.items.firearms.render;

import com.arisux.airix.api.wavefrontapi.Part;

import io.github.hsyyid.halocraft.util.ItemRenderer;
import io.github.hsyyid.halocraft.util.Models;
import net.minecraft.client.renderer.GlStateManager;

public class AssaultRifleItemRenderer extends ItemRenderer
{
	public AssaultRifleItemRenderer()
	{
		super();
	}

	@Override
	public void renderThirdPerson()
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.rotate(180f, 0, 1, 0);

			for (Part p : Models.ASSAULT_RIFLE.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void renderFirstPerson()
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(-0.15, 0.3, 0.15);
			GlStateManager.rotate(180f, 0, 1, 0);

			for (Part p : Models.ASSAULT_RIFLE.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void renderInInventory()
	{
		GlStateManager.scale(1.1, 1.1, 1.1);

		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(0.1, 0, 0);
			GlStateManager.rotate(-90f, 0, 1, 0);
			GlStateManager.rotate(-45f, 1, 0, 0);

			for (Part p : Models.ASSAULT_RIFLE.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void renderOnGround()
	{
		GlStateManager.scale(0.75, 0.75, 0.75);

		GlStateManager.pushMatrix();
		{
			for (Part p : Models.ASSAULT_RIFLE.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void renderInFrame()
	{
		this.renderInInventory();
	}
}
