package halocraft.armor;

import halocraft.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class CovenantArmor extends ItemArmor{

	public CovenantArmor(ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		setCreativeTab(halocraft.Main.haloCreativeTab);
	}
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer){
		if(stack.getItem().equals(halocraft.Main.CovenantHelmet)|| stack.getItem().equals(halocraft.Main.CovenantChestplate)|| stack.getItem().equals(halocraft.Main.CovenantBoots)){
			return "halocraft:textures/armor/CovenantArmor_layer_1.png";
		}
		
		if(stack.getItem().equals(halocraft.Main.CovenantLeggings)){
			return "halocraft:textures/armor/CovenantArmor_layer_2.png";
		}
		
		else return null;
	}
}
