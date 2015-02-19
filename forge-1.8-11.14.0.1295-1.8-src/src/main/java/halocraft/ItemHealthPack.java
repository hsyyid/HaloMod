package halocraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHealthPack extends Item {
	public ItemHealthPack(){
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("HealthPack");
		setMaxStackSize(1);
	}
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
		playerIn.addPotionEffect(new PotionEffect(Potion.heal.id, 500, 4));
		playerIn.inventory.consumeInventoryItem(halocraft.Main.itemHealthPack);
		return itemStackIn;
    }
}