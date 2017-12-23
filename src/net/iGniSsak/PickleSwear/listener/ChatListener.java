package net.iGniSsak.PickleSwear.listener;

import net.iGniSsak.PickleSwear.PickleSwear;
import net.iGniSsak.PickleSwear.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatListener implements Listener {

    SettingsManager settings = SettingsManager.getInstance();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage().toLowerCase();
        List<String> badWords = settings.getConfig().getStringList("disabledwords");

        for (String word : badWords) {
            message = message.replace(" ", "");
            if (message.contains(word)) {
                if (e.getPlayer().hasPermission("pickleswear.bypass")) return;
                e.setCancelled(true);
                e.getPlayer().sendMessage(settings.getConfig().getString("messages.antiswear"));

                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("pickleswear.see")) {
                        all.sendMessage(settings.getConfig().getString("announce-messages.first").replace("%player%", e.getPlayer().getName()));
                        all.sendMessage(settings.getConfig().getString("announce-messages.second").replace("%word%", word));
                        all.sendMessage(settings.getConfig().getString("announce-messages.third").replace("%sentence%", e.getMessage()));
                    }
                }
            }
        }
    }
}
