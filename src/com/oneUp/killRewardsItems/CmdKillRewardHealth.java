package com.oneUp.killRewardsItems;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CmdKillRewardHealth implements CommandExecutor {
	
	private FileConfiguration config;
	private KillRewardsItems mainPlugin;

    public CmdKillRewardHealth(KillRewardsItems killRewardsItems, FileConfiguration config) {
		// TODO Auto-generated constructor stub
    	this.config = config;
    	this.mainPlugin = killRewardsItems;
	}

	// This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
    {
    	if(args.length <= 0)
    	{
    		return false;
    	}
    	
    	String cmd = args[0];
    	
    	if(cmd.equalsIgnoreCase("set") && args.length == 3)
    	{
    		Player player = Bukkit.getPlayerExact(args[1]);
    		if(player == null)
    		{
    			// Player offline or incorrect,
    			sender.sendMessage("Invalid player");
    			return false;
    		}
    		
    		try {
                //Third argument: The new health value
                Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                return false;
            }
    		
    		// Save to config
    		config.set("KillRewardsHealth." + player.getName(), args[2]);
    		mainPlugin.saveConfig();
    		return true;
    		
    	}
    	else if(cmd.equalsIgnoreCase("clear") && args.length == 2)
    	{
    		Player player = Bukkit.getPlayerExact(args[1]);
    		if(player == null)
    		{
    			// Player offline or incorrect,
    			sender.sendMessage("Invalid player");
    			return false;
    		}
    		

    		// Save to config
    		config.set("KillRewardsHealth." + player.getName(), "");
    		mainPlugin.saveConfig();
    		
    		return true;
    	}
    	
     
        
        return false;
    }
}
