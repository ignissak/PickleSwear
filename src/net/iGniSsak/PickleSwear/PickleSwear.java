package net.iGniSsak.PickleSwear;

import net.iGniSsak.PickleSwear.commands.Reload;
import net.iGniSsak.PickleSwear.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


// Created by iGniSsak
public class PickleSwear extends JavaPlugin implements Listener {

    SettingsManager settings = SettingsManager.getInstance();


    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().info("[Enabled] I like swears! :3");
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        settings.setup();
        this.getCommand("pickleswear").setExecutor(new Reload());
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("[Disabled] RIP swears. :(");
    }

}
