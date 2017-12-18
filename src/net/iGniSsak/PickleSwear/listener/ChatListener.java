package net.iGniSsak.PickleSwear.listener;

import net.iGniSsak.PickleSwear.PickleSwear;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatListener implements Listener {

    private PickleSwear pickleSwear;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage().toLowerCase();
        List<String> badWords = pickleSwear.getConfig().getStringList("disabledwords");

        for (String word : badWords) {
            message = message.replace(" ", "");
            if (message.contains(word)) {
                if (e.getPlayer().hasPermission("pickleswear.bypass")) return;
                e.setCancelled(true);
                e.getPlayer().sendMessage(pickleSwear.getConfig().getString("messages.antiswear"));

                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("pickleswear.see")) {
                        all.sendMessage("§cPlayer " + e.getPlayer().getName() + " said bad word! ");
                        all.sendMessage("§cCensored word: " + word);
                        all.sendMessage("§cCensored sentence: " + e.getMessage());
                    }
                }
            }
        }
    }
}
