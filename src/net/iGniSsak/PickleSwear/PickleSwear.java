package net.iGniSsak.PickleSwear;

import net.iGniSsak.PickleSwear.commands.Reload;
import net.iGniSsak.PickleSwear.listener.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


// Created by iGniSsak
public class PickleSwear extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().info("[Enabled] I like swears! :3");
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getConfig().options().copyDefaults(true);
        saveConfig();
        this.getCommand("pickleswear").setExecutor(new Reload());
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("[Disabled] RIP swears. :(");
    }
}
