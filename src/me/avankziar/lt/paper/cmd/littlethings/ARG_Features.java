package me.avankziar.lt.paper.cmd.littlethings;

import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import me.avankziar.lt.general.assistance.ChatApi;
import me.avankziar.lt.general.cmdtree.ArgumentConstructor;
import me.avankziar.lt.paper.LT;
import me.avankziar.lt.paper.cmdtree.ArgumentModule;

public class ARG_Features extends ArgumentModule
{
	private LT plugin;
	
	public ARG_Features(LT plugin, ArgumentConstructor argumentConstructor)
	{
		super(argumentConstructor);
		this.plugin = plugin;
	}
	
	@Override
	public void run(CommandSender sender, String[] args) throws IOException
	{
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{
				doAsync(sender);
			}
		}.runTaskAsynchronously(plugin);
	}
	
	private void doAsync(CommandSender sender)
	{
		sender.sendMessage(ChatApi.tl(plugin.getYamlHandler().getLang().getString("Headline")));
		plugin.getYamlHandler().getLang().getStringList("Features.").stream().forEach(
				x -> sender.sendMessage(ChatApi.tl(x
						.replace("%gamemode%", isBoo(plugin.getGameModeHandler().isActive()))
						//ADDME
						)));
	}
	
	private String isBoo(boolean boo)
	{
		return boo ? plugin.getYamlHandler().getLang().getString("IsTrue") : plugin.getYamlHandler().getLang().getString("IsFalse");
	}
}