package me.avankziar.lt.paper.handler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

import org.bukkit.GameMode;

import me.avankziar.lt.paper.LT;
import me.avankziar.lt.paper.ModifierValueEntry.Bypass;

public class GameModeHandler extends FeatureHandler
{
	public ArrayList<String> gamemodes = new ArrayList<>();
	public LinkedHashMap<String, GameMode> gamemodeMap = new LinkedHashMap<>();
	
	public GameModeHandler(LT plugin)
	{
		super(plugin, "GameMode");
		if(!isActive())
		{
			return;
		}
		CompletableFuture.runAsync(() ->
		{
			for(String v : plugin.getYamlHandler().getConfig().getStringList("Feature.GameMode.GameModes"))
			{
				String[] split = v.split(";");
				if(split.length != 2)
				{
					continue;
				}
				GameMode gm = null;
				try
				{
					gm = GameMode.valueOf(split[0]);
				} catch(Exception e) 
				{
					continue;
				}
				String value = split[1];
				gamemodes.add(value);
				gamemodeMap.put(value, gm);
			}
		});
	}
	
	public GameMode getGameMode(String value)
	{
		GameMode gm = gamemodeMap.get(value);
		return gm == null ? GameMode.SURVIVAL : gm;
	}
	
	public Bypass.Permission getGameModeByPassPermission(GameMode gm, boolean other)
	{
		switch(gm)
		{
		default:
		case SURVIVAL:
			return other ? Bypass.Permission.GAMEMODE_SURVIVAL_OTHER 
						 : Bypass.Permission.GAMEMODE_SURVIVAL;
		case CREATIVE:
			return other ? Bypass.Permission.GAMEMODE_CREATIVE_OTHER 
					 	 : Bypass.Permission.GAMEMODE_CREATIVE;
		case ADVENTURE:
			return other ? Bypass.Permission.GAMEMODE_ADVENTURE_OTHER 
					 	 : Bypass.Permission.GAMEMODE_ADVENTURE;
		case SPECTATOR:
			return other ? Bypass.Permission.GAMEMODE_SPECTATOR_OTHER 
					 	 : Bypass.Permission.GAMEMODE_SPECTATOR;
		}
	}
}