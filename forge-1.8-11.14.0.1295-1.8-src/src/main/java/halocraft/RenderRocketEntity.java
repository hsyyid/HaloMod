package halocraft;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderRocketEntity extends Render {
		private static final ResourceLocation rocketTextures = new ResourceLocation("halocraft:textures/entities/RocketRender.png");
		public RenderRocketEntity(RenderManager rendermanager) {
			super(rendermanager);
			// TODO Auto-generated constructor stub
		}
		protected ResourceLocation getEntityTexture(EntityBullet entity){
			return rocketTextures;
		}
		protected ResourceLocation getEntityTexture(Entity entity){
			return this.getEntityTexture((EntityBullet)entity);	

		}
}