package net.iGniSsak.PickleSwear.commands;

import net.iGniSsak.PickleSwear.SettingsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {

    SettingsManager settings = SettingsManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("pickleswear")) { //jako teraz ten arg 0 funguje
            if (args.length > 0) { //AK JE ARGUMENT
                String arg1 = args[0];
                if (arg1.equalsIgnoreCase("reload")) {
                    settings.saveConfig();
                    p.sendMessage(settings.getConfig().getString("messages.reload"));
                    return true;
                }
            } else {
                p.sendMessage("/ignissio reload");
                return true;
            }
        } return true;
    }
}
