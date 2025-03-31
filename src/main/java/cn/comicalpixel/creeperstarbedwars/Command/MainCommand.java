package cn.comicalpixel.creeperstarbedwars.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

        if (sender instanceof Player) {
            sender.sendMessage("test");
        } else {

        }

        return false;
    }
}
