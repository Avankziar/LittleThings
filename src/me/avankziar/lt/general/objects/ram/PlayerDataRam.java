package me.avankziar.lt.general.objects.ram;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.avankziar.lt.paper.LT;
import me.avankziar.lt.paper.ModifierValueEntry.Bypass;
import me.avankziar.lt.paper.ModifierValueEntry.ModifierValueEntry;

public class PlayerDataRam 
{
	private Player player;
	
	/**
	 * Only Features which are too consuming to check all time often times, are should call in a runnable
	 */
	private ArrayList<BukkitTask> runnables = new ArrayList<>();
	
	private boolean elytraBoost = false;
	
	public PlayerDataRam(Player player)
	{
		setPlayer(player);
	}

	public Player getPlayer() 
	{
		return player;
	}

	public void setPlayer(Player player) 
	{
		this.player = player;
	}

	public boolean isElytraBoost() 
	{
		return elytraBoost;
	}

	public PlayerDataRam setElytraBoost(boolean elytraBoost) 
	{
		this.elytraBoost = elytraBoost;
		return this;
	}
	
	public void cancelRunnableThroughQuit()
	{
		runnables.stream().forEach(x -> x.cancel());
	}
	
	public void checkElytraBoost()
	{
		runnables.add(new BukkitRunnable() 
		{
			@Override
			public void run() 
			{
				if(getPlayer() == null)
				{
					cancel();
					return;
				}
				elytraBoost = ModifierValueEntry.hasPermission(player, Bypass.Permission.ELYTRA_BOOST);
			}
		}.runTaskTimerAsynchronously(LT.getPlugin(), 0, 5*60*20L));
	}
}