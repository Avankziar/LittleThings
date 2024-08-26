package me.avankziar.lt.paper.handler;

import java.util.UUID;

import me.avankziar.lt.general.objects.ram.PlayerDataRam;
import me.avankziar.lt.paper.LT;

public class ElytraBoostHandler extends FeatureHandler
{
	public ElytraBoostHandler(LT plugin)
	{
		super(plugin, "ElytraBoost");
		if(!isActive())
		{
			return;
		}
	}
	
	public boolean canElytraBoost(UUID uuid)
	{
		PlayerDataRam pdr = getPlugin().getPlayerDataRamHandler().getPlayerData(uuid);
		if(pdr == null)
		{
			return false;
		}
		return pdr.isElytraBoost();
	}
}
