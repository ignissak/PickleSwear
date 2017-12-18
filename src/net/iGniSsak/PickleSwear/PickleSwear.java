package net.iGniSsak.PickleSwear;

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
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("[Disabled] RIP swears. :(");
    }

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (command.getName().equalsIgnoreCase("picklereload")) {
                if(!sender.hasPermission("pickleswear.reload")) {
                    sender.sendMessage(getConfig().getString("messages.noperm"));
                    return true;
                }
                    reloadConfig();
                    sender.sendMessage(getConfig().getString("messages.reload"));
                    return true;
            } return true;
        }
    }
