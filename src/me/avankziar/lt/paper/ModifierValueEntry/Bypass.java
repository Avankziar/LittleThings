package me.avankziar.lt.paper.ModifierValueEntry;

import java.util.LinkedHashMap;

import me.avankziar.lt.paper.LT;

public class Bypass
{
	public enum Permission
	{
		//Here Condition and BypassPermission.
		GAMEMODE_SURVIVAL,
		GAMEMODE_CREATIVE,
		GAMEMODE_ADVENTURE,
		GAMEMODE_SPECTATOR,
		GAMEMODE_SURVIVAL_OTHER, //To set other in survival etc.
		GAMEMODE_CREATIVE_OTHER,
		GAMEMODE_ADVENTURE_OTHER,
		GAMEMODE_SPECTATOR_OTHER,
		
		ELYTRA_BOOST;
		
		public String getValueLable()
		{
			return LT.pluginname.toLowerCase()+"-"+this.toString().toLowerCase();
		}
	}
	
	private static LinkedHashMap<Bypass.Permission, String> mapPerm = new LinkedHashMap<>();
	
	public static void set(Bypass.Permission bypass, String perm)
	{
		mapPerm.put(bypass, perm);
	}
	
	public static String get(Bypass.Permission bypass)
	{
		return mapPerm.get(bypass);
	}
	
	public enum Counter
	{
		//Here BonusMalus and CountPermission Things
		BASE(true);
		
		private boolean forPermission;
		
		Counter()
		{
			this.forPermission = true;
		}
		
		Counter(boolean forPermission)
		{
			this.forPermission = forPermission;
		}
	
		public boolean forPermission()
		{
			return this.forPermission;
		}
		
		public String getModification()
		{
			return LT.pluginname.toLowerCase()+"-"+this.toString().toLowerCase();
		}
	}
	
	private static LinkedHashMap<Bypass.Counter, String> mapCount = new LinkedHashMap<>();
	
	public static void set(Bypass.Counter bypass, String perm)
	{
		mapCount.put(bypass, perm);
	}
	
	public static String get(Bypass.Counter bypass)
	{
		return mapCount.get(bypass);
	}
}