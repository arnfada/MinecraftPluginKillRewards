package com.oneUp.killRewardsItems;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class KillRewardsItems extends JavaPlugin {
	FileConfiguration config = getConfig();
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() 
    {
    	config.addDefault("Test", 3);
    	config.options().copyDefaults();
    	saveConfig();

    	this.getCommand("killRewardsItem").setExecutor(new CmdKillRewardItem(this, config));
    	this.getCommand("killRewardsItem").setTabCompleter(new CmdKillRewardItemTabComplete());
    	this.getCommand("killRewardsHealth").setExecutor(new CmdKillRewardHealth(this, config));
    	this.getCommand("killRewardsHealth").setTabCompleter(new CmdKillRewardHealthTabComplete());
        getServer().getPluginManager().registerEvents(new KillListener(config), this);    	
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
    	saveConfig();
    }

}
