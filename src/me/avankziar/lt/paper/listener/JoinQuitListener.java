package me.avankziar.lt.paper.listener;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.avankziar.lt.general.objects.ram.PlayerDataRam;
import me.avankziar.lt.paper.LT;

public class JoinQuitListener implements Listener
{
	private LT plugin;
	
	public JoinQuitListener(LT plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		final UUID uuid = player.getUniqueId();
		plugin.getPlayerDataRamHandler().addPlayer(new PlayerDataRam(player));
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{
				PlayerDataRam pdr = plugin.getPlayerDataRamHandler().getPlayerData(uuid);
				if(pdr == null)
				{
					return;
				}
				if(plugin.getElytraBoostHandler().isActive())
				{
					pdr.checkElytraBoost();
				}
				//ADDME Here call runnables
			}
		}.runTaskLaterAsynchronously(plugin, 2*20L);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		final UUID uuid = event.getPlayer().getUniqueId();
		plugin.getPlayerDataRamHandler().removePlayer(uuid);
	}
}
