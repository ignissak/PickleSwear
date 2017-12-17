package net.iGniSsak.PickleSwear;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


// Created by iGniSsak
public class Main extends JavaPlugin implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        for (String word : e.getMessage().split(" ")) {
            if (getConfig().getStringList("disabledwords").contains(word)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.DARK_RED + getConfig().getString("msg"));
            }
        }
    }
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
                    sender.sendMessage(ChatColor.DARK_RED + getConfig().getString("noperm"));
                    return true;
                }
                    reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Pickles were reloaded! :3");
                    return true;
            } return true;
        }
    }
