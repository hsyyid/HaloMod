package halocraft.entities.render;

import halocraft.api.OBJModel;
import halocraft.entities.EntityMongoose;
import halocraft.entities.EntityWarthog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.google.common.base.Function;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.TRSRTransformation;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWarthogEntity extends Render
{
	private static final ModelResourceLocation warthogModel = new ModelResourceLocation("halocraft:entity/Warthog.obj");

	public RenderWarthogEntity(RenderManager renderManager)
	{
		super(renderManager);
		this.shadowSize = 0.5F;
	}
	Function<ResourceLocation, TextureAtlasSprite> textureGetter = new Function<ResourceLocation, TextureAtlasSprite>()
			{
		public TextureAtlasSprite apply(ResourceLocation location)
		{
			return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
		}
			};

			public void doRender(EntityWarthog warthogIn, double posX, double posY, double posZ, float yaw, float partialTicks) throws IOException
			{
				IModel warthogIModel = ModelLoaderRegistry.getModel(warthogModel);
				if(warthogIModel != null && warthogIModel instanceof OBJModel)
				{
					OBJModel warthog = (OBJModel) ModelLoaderRegistry.getModel(warthogModel);
//					if(warthogIn.motionX > 0.1 || warthogIn.motionZ > 0.1)
//					{
						float tireRotation = 5;//(-warthogIn.worldObj.getWorldTime() % 360 * 8) - partialTicks;
						Vector3f translation = new Vector3f(0f, 0f, 0f);
						Vector3f rotation = new Vector3f(tireRotation, 0F, 0F);
						Vector3f scale = new Vector3f(1f, 1f, 1f);
						Quat4f rotate = TRSRTransformation.quatFromYXZDegrees(rotation);
						TRSRTransformation transform = new TRSRTransformation(translation, rotate, scale, null);
						warthog.getMatLib().getGroups().get("the_node.000_tri_5178_geometry").setTransform(transform);
//						warthog.getMatLib().getGroups().get("the_node.001_tri_5178_geometry").setTransform(transform);
//						warthog.getMatLib().getGroups().get("the_node.002_tri_5178_geometry").setTransform(transform);
//						warthog.getMatLib().getGroups().get("the_node.010_tri_5178_geometry").setTransform(transform);
//					}
					IBakedModel bakedWarthog = warthog.bake((TRSRTransformation.identity()),  Attributes.DEFAULT_BAKED_FORMAT, textureGetter);
					World world = warthogIn.getWorldObj();
					BlockPos blockpos = new BlockPos(warthogIn);
					Tessellator tessellator = Tessellator.getInstance();
					WorldRenderer worldrenderer = tessellator.getWorldRenderer();
					GlStateManager.pushMatrix();
					GlStateManager.translate((float)posX + 0.65F, (float)posY + 0.75F, (float)posZ);

					float f2 = (float)warthogIn.getTimeSinceHit() - partialTicks;
					float f3 = warthogIn.getDamageTaken() - partialTicks;

					if (f3 < 0.0F)
					{
						f3 = 0.0F;
					}

					if (f2 > 0.0F)
					{
						GlStateManager.rotate(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)warthogIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
					}

					float f4 = 0.75F;
					GlStateManager.scale(f4, f4, f4);
					GlStateManager.scale(1.0F / f4, 1.0F / f4, 1.0F / f4);
					GlStateManager.scale(-1.0F, -1.0F, 1.0F);

					worldrenderer.startDrawingQuads();
					GlStateManager.rotate(180f, 0f, 0f, 1f);
					//Get Quads
					List<BakedQuad> generalQuads = bakedWarthog.getGeneralQuads();
					for (BakedQuad q : generalQuads) {
						int[] vd = q.getVertexData();
						worldrenderer.setVertexFormat(Attributes.DEFAULT_BAKED_FORMAT);
						worldrenderer.addVertexData(vd);
					}
					for (EnumFacing face : EnumFacing.values()) {
						List<BakedQuad> faceQuads = 
								bakedWarthog.getFaceQuads(face);
						for (BakedQuad q : faceQuads) {
							int[] vd = q.getVertexData();
							worldrenderer.setVertexFormat(Attributes.DEFAULT_BAKED_FORMAT);
							worldrenderer.addVertexData(vd);
						}
					}
					tessellator.draw();
					GlStateManager.popMatrix();
				}
				super.doRender(warthogIn, posX, posY, posZ, yaw, partialTicks);
			}

			protected ResourceLocation getEntityTexture(EntityWarthog entityWarthog)
			{
				return null;
			}

			protected ResourceLocation getEntityTexture(Entity entity)
			{
				return null;
			}

			public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks)
			{
				try {
					this.doRender((EntityWarthog)entity, x, y, z, yaw, partialTicks);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
}