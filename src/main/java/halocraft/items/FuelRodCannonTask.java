package halocraft.items;

import java.util.TimerTask;

public class FuelRodCannonTask extends TimerTask {
	public ItemFuelRodCannon item;
	
	public FuelRodCannonTask(ItemFuelRodCannon i)
	{
		this.item = i;
	}
	
	@Override
	public void run()
	{
		item.canShoot = true;
	}
	
}