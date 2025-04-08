package cn.comicalpixel.creeperstarbedwars.Command;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.GameStart;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.GUI.TeamSel_GUI;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Countdown_Task;
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
                    case "start":
                        // 检查游戏是否开始
                        if (GameStats.get() == 1) {

                            if (args.length == 1) {
                                if (p.hasPermission("bedwars.start")) {
                                    p.sendMessage(ConfigData.language_command_bwstart_done);
                                    Game_Countdown_Task.countdown = 6;
                                    Game_Countdown_Task.countdown_running = true;
                                } else {
                                    p.sendMessage(ConfigData.language_command_bwstart_nopermissions);
                                }
                            } else if (args.length == 2) {
                                if (args[1].equalsIgnoreCase("debug")) {
                                    if (p.isOp() || p.hasPermission("bedwars.admin")) {
                                        GameStart.start();
                                        Game_Countdown_Task.countdown_running = false;
                                        Game_Countdown_Task.countdown = 0;
                                    }
                                }
                            }

                        } else {
                            sender.sendMessage(ChatColor.RED + "The game has already started! You cannot do this now.");
                        }
                        break;
                    case "setup":
                        if (p.isOp() || p.hasPermission("bedwars.admin")) {
                            sender.sendMessage("§7Please use the \"/setup\" command.");
                        } else {
                            p.sendMessage(ConfigData.language_command_nopermissions);
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
