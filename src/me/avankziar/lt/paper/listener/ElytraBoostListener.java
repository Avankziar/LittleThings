package me.avankziar.lt.paper.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.avankziar.lt.paper.LT;

public class ElytraBoostListener implements Listener
{
	private LT plugin;
	private double boost = 10.0;
	
	public ElytraBoostListener(LT plugin)
	{
		this.plugin = plugin;
		boost = plugin.getYamlHandler().getConfig().getDouble("Feature.ElytraBoost.BoostFactor", 10.0);
	}
	
	@EventHandler
	public void onElytraBoost(PlayerInteractEvent event)
	{
		if(!event.getPlayer().isGliding())
		{
			return;
		}
		if(!plugin.getElytraBoostHandler().canElytraBoost(event.getPlayer().getUniqueId()))
		{
			return;
		}
		event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(boost));
	}
}