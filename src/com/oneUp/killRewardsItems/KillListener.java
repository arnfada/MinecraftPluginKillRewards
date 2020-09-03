package com.oneUp.killRewardsItems;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class KillListener implements Listener
{	 
	 private FileConfiguration config;

	public KillListener(FileConfiguration config) {
		// TODO Auto-generated constructor stub
		 this.config = config;
	}

	@EventHandler
	 public void onKillEntity(EntityDeathEvent e)
	 {
		 if(e.getEntity() instanceof Player)
		 {
			 Player deadPlayer = (Player)e.getEntity();
			 if(deadPlayer.getKiller() instanceof Player)
			 {
				 try
				 {
					 Player player = (Player)deadPlayer.getKiller();
					 
					 List<String> configEntry = config.getStringList("KillRewardsItem." + player.getName());
					 if(configEntry != null && configEntry.toArray().length > 0)
					 {
						 String[] reward = configEntry.toArray(new String[0]);
					 	if(reward.length != 2)
						{
					 		return;
						}
					 	Material material = Material.getMaterial(reward[0]);
						if(material == null)
						{
							return;
						}
		
						int amount = 0;
						try {
						    //Third argument: The new health value
						    amount = Integer.parseInt(reward[1]);
						} catch (NumberFormatException ex) {
						    return;
						}
	 
						 player.getInventory().addItem(new ItemStack(material, amount));
						 Bukkit.broadcastMessage(ChatColor.WHITE + player.getName() + " is rewarded " + amount + " " + material.name() + " for slaying " + deadPlayer.getName());
					 }
					 
					 String health = config.getString("KillRewardsHealth." + player.getName());
					 if(health != null)
					 {
							double amount = 0.0;
							try {
							    //Third argument: The new health value
							    amount = Double.parseDouble(health) * 2;
							} catch (NumberFormatException ex) {
							    return;
							}
							double targetHealth =player.getHealth() + amount;
							if(targetHealth > 20.0) targetHealth = 20.0;
							if(targetHealth < 0.0) targetHealth = 0.0;
							player.setHealth(targetHealth);
							 Bukkit.broadcastMessage(ChatColor.WHITE + player.getName() + " gains " + amount/2 + " hearts for slaying " + deadPlayer.getName());
					 }
				 }
				 catch (Exception ex2)
				 {
					 return;
				 }
			 } 
		 }
	 }
}
