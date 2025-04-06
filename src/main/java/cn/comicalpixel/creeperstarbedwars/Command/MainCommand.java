package cn.comicalpixel.creeperstarbedwars.Command;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.GUI.TeamSel_GUI;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 1) {
                String arg1 = args[0];
                arg1 = arg1.toLowerCase();
                switch (arg1) {
                    case "teamsel":
                        if (GameStats.get() == 1) {
                            TeamSel_GUI.open(p);
                        } else {
                            sender.sendMessage(ChatColor.RED + "The game has already started! You cannot do this now.");
                        }
                        break;
                }
            } else {
                sender.sendMessage("§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getInstance().getDescription().getVersion());
                sender.sendMessage("§epowered by §aXiaol789zxc §f- §bComical§ePixel §fNetwork");
                sender.sendMessage("§f请使用 /bw help 查看指令列表");
            }
        } else {
            sender.sendMessage("§r\n§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getPlugin().getDescription().getVersion() + " \n" +
                    "§cThis command can only be executed by players!");
        }

        return false;
    }
}
