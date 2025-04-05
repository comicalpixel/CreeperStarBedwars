package cn.comicalpixel.creeperstarbedwars.GameSetup;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetupCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {

        if (sender instanceof Player) {
            if (sender.hasPermission("creeperstarbedwars.setup") && sender.isOp()) {
                if (GameStats.get() == 0) {

                    Player p = (Player) sender;

                    String loc_player_str_xyzyp = p.getWorld().getName() + ", " + p.getLocation().getX() + ", " + p.getLocation().getY() + ", " + p.getLocation().getZ() + ", " + p.getLocation().getYaw() + ", " + p.getLocation().getPitch();
                    String loc_player_str_xyz = p.getWorld().getName() + ", " + p.getLocation().getX() + ", " + p.getLocation().getY() + ", " + p.getLocation().getZ();

                    if (args.length >= 1) {
                        String arg1 = args[0];
                        switch (arg1) {

                            case "disable":
                                if (args.length == 2 && args[1].equalsIgnoreCase("confirm")) {
                                    sender.sendMessage("§c[SETUP] 正在更改设置... ");
                                    CreeperStarBedwars.getInstance().getGameConfig().set("setup", false);
                                    CreeperStarBedwars.getInstance().getGameConfig().save();
                                    sender.sendMessage("§c[SETUP] 即将重启服务器! ");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:stop");
                                } else {
                                    sender.sendMessage("§c[SETUP] 请使用/setup disable confirm二次确认是否关闭SETUP模式! ");
                                }
                                break;

                            case "setlobby":
                                CreeperStarBedwars.getPlugin().getGameConfig().set("lobby", loc_player_str_xyzyp);
                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                NMSTitleUntils.Title.send(p, "§r ", "§a已设置等待大厅位置", 5, 40, 5);
                                p.sendMessage("§a已成功设置§e等待大厅位置");
                                break;
                            case "setspec":
                                CreeperStarBedwars.getPlugin().getGameConfig().set("spec", loc_player_str_xyzyp);
                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                NMSTitleUntils.Title.send(p, "§r ", "§a已设置旁观者位置", 5, 40, 5);
                                p.sendMessage("§a已成功设置§e旁观者位置");
                                break;
                            case "setcenter":
                                CreeperStarBedwars.getPlugin().getGameConfig().set("map-center", loc_player_str_xyz);
                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                NMSTitleUntils.Title.send(p, "§r ", "§a已设置地图中心位置", 5, 40, 5);
                                p.sendMessage("§a已成功设置地图中心位置");
                                break;
                            case "setradius":
                                if (args.length == 2) {
                                    int map_radius = Integer.parseInt(args[1]);
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("map-radius", map_radius);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§a已设置地图范围半径为§e"+map_radius, 5, 40, 5);
                                    p.sendMessage("§a已设置地图范围半径为: §e"+map_radius);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "缺少了参数, arg2:int");
                                }
                                break;
                            case "setName":
                                if (args.length == 2) {
                                    String map_name = args[1];
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("map-name", map_name);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§a已设置地图名称为§e"+map_name, 5, 40, 5);
                                    p.sendMessage("§a已设置地图名称为: §e"+map_name);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "缺少了参数, arg2:String");
                                }
                                break;
                            case "setAuthor":
                                if (args.length == 2) {
                                    String auhtor = args[1];
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("map-author", auhtor);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§a已设置地图建筑师为§e"+auhtor, 5, 40, 5);
                                    p.sendMessage("§a已设置地图建筑师为: §e"+auhtor);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "缺少了参数, arg2:String");
                                }
                                break;
                            case "minplayers":
                                if (args.length == 2) {
                                    int minplayers = Integer.parseInt(args[1]);
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("minplayers", minplayers);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§a已设置游戏最少玩家为§e"+minplayers, 5, 40, 5);
                                    p.sendMessage("§a已设置游戏最少玩家为: §e"+minplayers);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "缺少了参数, arg2:int");
                                }
                                break;
                            case "maxplayers":
                                if (args.length == 2) {
                                    int maxplayers = Integer.parseInt(args[1]);
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("maxplayers", maxplayers);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§a已设置游戏玩家数量为§e"+maxplayers, 5, 40, 5);
                                    p.sendMessage("§a已设置游戏玩家数量为: §e"+maxplayers);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "缺少了参数, arg2:int");
                                }
                                break;
                            case "addteam":
                                List<String> at_teams = new ArrayList<>();
                                at_teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    if (    arg2.equalsIgnoreCase("RED") ||
                                            arg2.equalsIgnoreCase("BLUE")||
                                            arg2.equalsIgnoreCase("GREEN")||
                                            arg2.equalsIgnoreCase("YELLOW")||
                                            arg2.equalsIgnoreCase("PINK")||
                                            arg2.equalsIgnoreCase("AQUA")||
                                            arg2.equalsIgnoreCase("GRAY")||
                                            arg2.equalsIgnoreCase("WHITE")) {

                                        if (!at_teams.contains(arg2)) {
                                            switch (arg2) {
                                                case "red":
                                                    arg2 = "RED";
                                                    break;
                                                case "blue":
                                                    arg2 = "BLUE";
                                                    break;
                                                case "green":
                                                    arg2 = "GREEN";
                                                    break;
                                                case "yellow":
                                                    arg2 = "YELLOW";
                                                    break;
                                                case "pink":
                                                    arg2 = "PINK";
                                                    break;
                                                case "aqua":
                                                    arg2 = "AQUA";
                                                    break;
                                                case "gray":
                                                    arg2 = "GRAY";
                                                    break;
                                                case "white":
                                                    arg2 = "WHITE";
                                                    break;
                                            }
                                            at_teams.add(arg2);
                                            CreeperStarBedwars.getPlugin().getGameConfig().set("teams", at_teams);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§r ", "§a已添加队伍颜色§e"+arg2, 5, 40, 5);
                                            p.sendMessage("§a已添加队伍颜色 §e"+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2+".color")+arg2);
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§c这个队伍颜色已经创建过了( -  - )");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§c无效的队伍颜色");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "缺少了参数, arg2:String(TEAM)");
                                }
                                break;






                            default:
                                sender.sendMessage("§aCreeper§eStar§fBedwars §7" + CreeperStarBedwars.getPlugin().getDescription().getVersion() + " §c请谨慎操作!操作不支持undo和redo!!\n" +
                                        "§f/setup setlobby §e设置等待大厅位置 \n" +
                                        "§f/setup setspec §e设置旁观者位置 \n" +
                                        "§f/setup setcenter §e设置地图中心 \n" +
                                        "§f/setup setradius <int> §e设置地图半径 \n" +
                                        "§f/setup setName <message> §e设置地图名称 \n" +
                                        "§f/setup setAuthor <message> §e设置地图建筑师 \n" +
                                        "§f/setup minplayers <int> §e设置游戏需要的最少玩家量 \n" +
                                        "§f/setup maxplayers <int> §e设置游戏最大的玩家量(不要填错!!) \n" +
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
                                break;
                        }
                    } else {
                        sender.sendMessage("§aCreeper§eStar§fBedwars §7" + CreeperStarBedwars.getPlugin().getDescription().getVersion() + " §c请谨慎操作!操作不支持undo和redo!!\n" +
                                "§f/setup setlobby §e设置等待大厅位置 \n" +
                                "§f/setup setspec §e设置旁观者位置 \n" +
                                "§f/setup setcenter §e设置地图中心 \n" +
                                "§f/setup setradius <int> §e设置地图半径 \n" +
                                "§f/setup setName <message> §e设置地图名称 \n" +
                                "§f/setup setAuthor <message> §e设置地图建筑师 \n" +
                                "§f/setup minplayers <int> §e设置游戏需要的最少玩家量 \n" +
                                "§f/setup maxplayers <int> §e设置游戏最大的玩家量(不要填错!!) \n" +
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
