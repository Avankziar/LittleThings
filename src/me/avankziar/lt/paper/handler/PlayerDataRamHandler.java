package me.avankziar.lt.paper.handler;

import java.util.LinkedHashMap;
import java.util.UUID;

import me.avankziar.lt.general.objects.ram.PlayerDataRam;

public class PlayerDataRamHandler 
{
	private LinkedHashMap<UUID, PlayerDataRam> playerMap = new LinkedHashMap<>();
	
	public void addPlayer(PlayerDataRam pdr)
	{
		playerMap.put(pdr.getPlayer().getUniqueId(), pdr);
	}
	
	public PlayerDataRam getPlayerData(UUID uuid)
	{
		return playerMap.get(uuid);
	}
	
	public void removePlayer(UUID uuid)
	{
		PlayerDataRam pdr = getPlayerData(uuid);
		if(pdr == null)
		{
			return;
		}
		pdr.cancelRunnableThroughQuit();
		playerMap.remove(uuid);
	}
}