package me.avankziar.lt.paper.handler;

import me.avankziar.lt.paper.LT;

public class FeatureHandler 
{
	private LT plugin;
	private boolean active;
	
	public FeatureHandler(LT plugin, String feature)
	{
		this.plugin = plugin;
		active = plugin.getYamlHandler().getConfig().getBoolean("Feature."+feature+".IsActive", false);
	}
	
	public LT getPlugin()
	{
		return plugin;
	}
	
	public boolean isActive()
	{
		return active;
	}
}