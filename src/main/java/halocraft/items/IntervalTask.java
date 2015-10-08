package halocraft.items;

import halocraft.items.firearms.ItemSniperRifle;

import java.util.TimerTask;

public class IntervalTask extends TimerTask {
	public ItemSniperRifle item;
	
	public IntervalTask(ItemSniperRifle i)
	{
		this.item = i;
	}
	
	@Override
	public void run()
	{
		item.canShoot = true;
	}
	
}