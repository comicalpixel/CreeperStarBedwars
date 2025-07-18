package cn.comicalpixel.creeperstarbedwars.Command;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent.GameTimerEvent_Main;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.GameStart;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.GUI.TeamSel_GUI;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.GUI.BwimSel_GUI;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Countdown_Task;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
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
                    case "help":
                        if (p.isOp() || p.hasPermission("bedwars.admin")) {
                            sender.sendMessage("§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getPlugin().getDescription().getVersion());
                            sender.sendMessage("§fpowered by §6Xiaol789zxc §e& §bTheGoodBoys §7- §5Ender§2Creeper §fNetwork ");
                            sender.sendMessage("§f/bw start §aShorten the game countdown");
                            sender.sendMessage("§f/bw setup §aSETUP Commands");
                            // sender.sendMessage("§f/bw debug §aTest game instructions (developer debugging)");
                        } else {
                            sender.sendMessage("§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getPlugin().getDescription().getVersion());
                            sender.sendMessage("§f/bw start §aShorten the game countdown");
                        }
                        break;
                    case "teamsel":
                        if (GameStats.get() == 1) {
                            TeamSel_GUI.open(p);
                        } else {
                            sender.sendMessage(ChatColor.RED + "The game has already started! You cannot do this now.");
                        }
                        break;
                    case "bwimsel":
                        if (ConfigData.teamsel_enabled) {
                            if (GameStats.get() == 1) {
                                BwimSel_GUI.open(p, true);
                            } else {
                                sender.sendMessage(ChatColor.RED + "The game has already started! You cannot do this now.");
                            }
                        }
                        break;
                    case "bwim":
                        if (p.hasPermission("bedwars.admin") || p.isOp()) {
                            if (args.length == 2) {
                                if (args[1].equalsIgnoreCase("0")) {
                                    BwimResItemManager.Companion.getPlayerMode().put(p, 0);
                                    sender.sendMessage("Your resource model has been updated!");
                                }
                                if (args[1].equalsIgnoreCase("1")) {
                                    BwimResItemManager.Companion.getPlayerMode().put(p, 1);
                                    sender.sendMessage("Your resource model has been updated!");
                                }
                            }
                        } else {
                            p.sendMessage(ConfigData.language_command_bwstart_nopermissions);
                        }
                        break;
                    case "leave":
                        PlayerUtils.leave_game(p);
                        break;
                    case "start":
                        // 检查游戏是否开始
                        if (GameStats.get() == 1) {

                            if (args.length == 1) {
                                if (p.hasPermission("bedwars.start")) {
                                    if (Game_Countdown_Task.countdown > 6) {
                                        p.sendMessage(ConfigData.language_command_bwstart_done);
                                        Game_Countdown_Task.countdown = 6;
                                        Game_Countdown_Task.countdown_running = true;
                                    }
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
                    case "debug":
                        if (p.isOp() || p.hasPermission("bedwars.admin")) {
                            if (args.length >= 3) {
                                switch (args[1]) {
                                    case "setGameTimer":
                                        if (args.length == 3) {
                                            GameTimerEvent_Main.gameNowTimer = Integer.parseInt(args[2]);
                                            sender.sendMessage("§a你已将游戏时间(倒计时)设置为 " + args[2] + " §a秒");
                                        } else {
                                            sender.sendMessage("§c错误的语法");
                                        }
                                        break;
                                    case "setGen":
                                        // 这玩意我不想写了 :(
                                        break;
                                    case "setBed":
                                        break;
                                    case "winCheck":
                                        break;
                                    case "setTeamGenL":
                                        break;
                                    default:
                                        sender.sendMessage("§c§lCreeperStarBedwars debug Commands §7v" + CreeperStarBedwars.getPlugin().getDescription().getVersion());
                                        sender.sendMessage("§fUnknown debug command. ");
                                        break;
                                }
                            } else {
                                sender.sendMessage("§c§lCreeperStarBedwars debug Commands §7v" + CreeperStarBedwars.getPlugin().getDescription().getVersion());
                                sender.sendMessage("§c/bw debug setGameTimer §e设置游戏时间");
                                sender.sendMessage("§c/bw debug setBed §e设置队伍床状态");
                                sender.sendMessage("§c/bw debug winCheck §e开关游戏胜利判断");
                                sender.sendMessage("§c/bw debug setTeamGenL §e设置队伍的资源点等级");
                                sender.sendMessage("§c/bw debug §e查看测试指令");
                            }
                        } else {
                            p.sendMessage(ConfigData.language_command_nopermissions);
                        }
                        break;
                    default:
                        sender.sendMessage("§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getInstance().getDescription().getVersion());
                        sender.sendMessage("§epowered by §aXiaol789zxc §f- §2Ender§5Creeper §fNetwork");
                        sender.sendMessage("§c未知指令! §f请使用 /bw help 查看指令列表");
                        break;
                }
            } else {
                sender.sendMessage("§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getInstance().getDescription().getVersion());
                sender.sendMessage("§epowered by §aXiaol789zxc §f- §2Ender§5Creeper §fNetwork");
                sender.sendMessage("§f请使用 /bw help 查看指令列表");
            }
        } else {
            sender.sendMessage("§r\n§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getPlugin().getDescription().getVersion() + " \n" +
                    "§cThis command can only be executed by players!");
        }

        return false;
    }
}
