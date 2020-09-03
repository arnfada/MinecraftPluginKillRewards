package com.oneUp.killRewardsItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CmdKillRewardItemTabComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("killrewardsitem"))
		{
			if(args.length == 0 || args.length == 1)
			{
				return getOptions();
			}
			else if(args.length == 2)
			{
				return getOnlinePlayers(args[1]);
			}
			else if(args.length == 3 && args[0].equalsIgnoreCase("set"))
			{
				return getMaterials(args[2]);
			}
			else
			{
				return new ArrayList<String>();
			}
		}
		
		
		return null;
			
	}

	private List<String> getMaterials(String matName) {
		List<String> materialNames = new ArrayList<String>();
		for(Material mat : Material.values())
		{
			if(matName.length() == 0 || mat.name().toLowerCase().startsWith(matName.toLowerCase()))
			{
				materialNames.add(mat.name());
			}
		}
		return materialNames;
	}

	private List<String> getOnlinePlayers(String name) {
		Collection<? extends Player> playersColl = Bukkit.getServer().getOnlinePlayers();
		if(playersColl == null)
		{
			return null;
		}

		List<String> playerNames = new ArrayList<String>();
		Player[] players = playersColl.toArray(new Player[0]);
		for(int i = 0; i < players.length; i++)
		{
			String playerName = players[i].getName();
			if(name.length() == 0 || playerName.startsWith(name))
			{
				playerNames.add(players[i].getName());
			}
		}
		return playerNames;
	}

	private List<String> getOptions() {
		List<String> options = new ArrayList<String>();
		options.add("set");
		options.add("clear");
		return options;
	}

}
