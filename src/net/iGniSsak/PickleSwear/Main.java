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
public class Main extends JavaPlugin implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        java.util.List<String> badwords = getConfig().getStringList("disabledwords");
        for (String word : badwords) {
            if (message.contains(word)) {
                if (e.getPlayer().hasPermission("pickleswear.bypass")) return;
                e.setCancelled(true);
                e.getPlayer().sendMessage(getConfig().getString("messages.antiswear"));
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("pickleswear.see")) {
                        all.sendMessage("§cPlayer " + e.getPlayer().getName() + " said bad word! ");
                        all.sendMessage("§cCensored word: " + word);
                    }
                }
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
                    sender.sendMessage(getConfig().getString("messages.noperm"));
                    return true;
                }
                    reloadConfig();
                    sender.sendMessage(getConfig().getString("messages.reload"));
                    return true;
            } return true;
        }
    }
