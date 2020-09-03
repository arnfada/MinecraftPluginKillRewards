package com.oneUp.killRewardsItems;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CmdKillRewardItem implements CommandExecutor {
	
	private FileConfiguration config;
	private KillRewardsItems mainPlugin;

    public CmdKillRewardItem(KillRewardsItems killRewardsItems, FileConfiguration config) {
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
    	
    	if(cmd.equalsIgnoreCase("set") && args.length == 4)
    	{
    		Player player = Bukkit.getPlayerExact(args[1]);
    		if(player == null)
    		{
    			// Player offline or incorrect,
    			sender.sendMessage("Invalid player");
    			return false;
    		}
    		
    		Material material = Material.getMaterial(args[2]);
    		if(material == null)
    		{
    			// Material not found
    			sender.sendMessage("Invalid item");
    			return false;
    		}
    		
    		try {
                //Third argument: The new health value
                Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                return false;
            }
    		
    		// Save to config
    		config.set("KillRewardsItem." + player.getName(), Arrays.asList(args[2], args[3]));
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
    		config.set("KillRewardsItem." + player.getName(), "");
    		mainPlugin.saveConfig();
    		
    		return true;
    	} 	
     
        
        return false;
    }
}
