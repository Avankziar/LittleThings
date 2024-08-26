package me.avankziar.lt.paper.cmd;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avankziar.lt.general.assistance.ChatApi;
import me.avankziar.lt.general.cmdtree.CommandConstructor;
import me.avankziar.lt.general.cmdtree.CommandSuggest;
import me.avankziar.lt.paper.LT;
import me.avankziar.lt.paper.ModifierValueEntry.Bypass;
import me.avankziar.lt.paper.ModifierValueEntry.ModifierValueEntry;
import me.avankziar.lt.paper.handler.GameModeHandler;

public class GameModeCmdExecutor implements CommandExecutor
{
	private LT plugin;
	private static CommandConstructor cc;
	
	public GameModeCmdExecutor(LT plugin, CommandConstructor cc)
	{
		this.plugin = plugin;
		GameModeCmdExecutor.cc = cc;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) 
	{
		if(cc == null)
		{
			return false;
		}
		if (!(sender instanceof Player)) 
		{
			plugin.getLogger().info("Cmd is only for Player!");
			return false;
		}
		Player player = (Player) sender;
		GameModeHandler gmh = plugin.getGameModeHandler();
		if(args.length == 0)
		{
			player.sendMessage(ChatApi.click(plugin.getYamlHandler().getLang().getString("InputIsWrong"),
					"RUN_COMMAND", CommandSuggest.getCmdString(CommandSuggest.Type.GAMEMODE)));
			return false;
		} else if(args.length == 1)
		{
			GameMode gm = gmh.getGameMode(args[0]);
			Bypass.Permission p = gmh.getGameModeByPassPermission(gm, false);
			if(!ModifierValueEntry.hasPermission(player, p))
			{
				player.sendMessage(ChatApi.tl(plugin.getYamlHandler().getLang().getString("NoPermission")));
				return false;
			}
			player.setGameMode(gm);
			player.sendMessage(ChatApi.tl(plugin.getYamlHandler().getLang().getString("GameMode.Set."+gm.toString())
					.replace("%player%", player.getName())));
		} else
		{
			GameMode gm = gmh.getGameMode(args[0]);
			Bypass.Permission p = gmh.getGameModeByPassPermission(gm, true);
			if(!ModifierValueEntry.hasPermission(player, p))
			{
				player.sendMessage(ChatApi.tl(plugin.getYamlHandler().getLang().getString("NoPermission")));
				return false;
			}
			String othername = args[1];
			Player other = Bukkit.getPlayer(othername);
			if(other == null)
			{
				player.sendMessage(ChatApi.tl(plugin.getYamlHandler().getLang().getString("PlayerNotOnline")));
				return false;
			}
			other.setGameMode(gm);
			String msg = plugin.getYamlHandler().getLang().getString("GameMode.Set."+gm.toString())
					.replace("%player%", other.getName());
			player.sendMessage(ChatApi.tl(msg));
			other.sendMessage(ChatApi.tl(msg));
		}
		return false;
	}
}