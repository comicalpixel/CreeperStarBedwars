package cn.comicalpixel.creeperstarbedwars.GameSetup;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {

        if (sender instanceof Player) {
            if (sender.hasPermission("creeperstarbedwars.setup") && sender.isOp()) {
                if (GameStats.get() == 0) {

                    if (args.length >= 1) {

                    } else {
                        sender.sendMessage("§aCreeper§eStar§fBedwars §7" + CreeperStarBedwars.getPlugin().getDescription().getVersion() + " §c请谨慎操作!操作不支持undo和redo!!\n" +
                                "§f/setup setlobby §e设置等待大厅位置 \n" +
                                "§f/setup setspec §e设置旁观者位置 \n" +
                                "§f/setup setcenter §e设置地图中心 \n" +
                                "§f/setup setradius §e设置地图半径 \n" +
                                "§f/setup setName §e设置地图名称 \n" +
                                "§f/setup setAuthor §e设置地图建筑师 \n" +
                                "§f/setup minplayers §e设置游戏需要的最少玩家量 \n" +
                                "§f/setup maxplayers §e设置游戏最大的玩家量(不要填错!!) \n" +
                                "§f/setup addteam §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §e添加队伍 \n" +
                                "§f/setup teamName §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §f<message> §e修改队伍名称 \n" +
                                "§f/setup delteam §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §e删除队伍 \n" +
                                "§f/setup teams §e查看所有队伍 \n" +
                                "§f/setup setBed §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §e设置队伍床(请先使用斧头选择床头床尾) \n" +
                                "§f/setup setspawn §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §e设置队伍出生点(站在要设置的位置) \n" +
                                "§f/setup setGenerator §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §e设置队伍资源点(请先使用稿子选择一个方块) \n" +
                                "§f/setup addShop <item/team> §e添加商店 \n" +
                                "§f/setup addGenerator §f<§bdiamond§f/§2emerald§f> §e添加资源点位置(请先使用稿子选择一个方块) \n" +
                                "§f/setup disable §e退出SETUP模式 "
                                );
                    }

                } else {
                    if (args.length >= 1) {
                        String s = args[0];
                        if (Objects.equals(s, "enable")) {
                            if (args.length == 2) {
                                String arg2 = args[1];
                                if (arg2.equals("confirm")) {
                                    sender.sendMessage("§c[SETUP] 正在更改设置... ");
                                    CreeperStarBedwars.getInstance().getGameConfig().set("setup", true);
                                    CreeperStarBedwars.getInstance().getGameConfig().save();
                                    sender.sendMessage("§c[SETUP] 即将重启服务器! ");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:stop");
                                } else {
                                    sender.sendMessage("§c[SETUP] 请使用/setup enable confirm二次确认是否启用SETUP模式! ");
                                }
                            } else {
                                sender.sendMessage("§c[SETUP] 请使用/setup enable confirm二次确认是否启用SETUP模式! ");
                            }
                        } else {
                            sender.sendMessage("§aCreeper§eStar§fBedwars §f");
                            sender.sendMessage("§e/setup enable §f启动SETUP模式 ");
                        }
                    } else {
                        sender.sendMessage("§aCreeper§eStar§fBedwars §f");
                        sender.sendMessage("§e/setup enable §f启动SETUP模式 ");
                    }
                }
            } else {
                sender.sendMessage(ConfigData.language_command_nopermissions);
            }
        } else {
            sender.sendMessage("§cThis command can only be executed by a player!");
        }

        return false;
    }
}
