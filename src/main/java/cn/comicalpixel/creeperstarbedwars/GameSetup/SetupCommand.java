package cn.comicalpixel.creeperstarbedwars.GameSetup;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.BedBlockUtils;
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
                                    if (    arg2.equals("RED") ||
                                            arg2.equals("BLUE")||
                                            arg2.equals("GREEN")||
                                            arg2.equals("YELLOW")||
                                            arg2.equals("PINK")||
                                            arg2.equals("AQUA")||
                                            arg2.equals("GRAY")||
                                            arg2.equals("WHITE")) {

                                        if (!at_teams.contains(arg2)) {
                                            at_teams.add(arg2);
                                            CreeperStarBedwars.getPlugin().getGameConfig().set("teams", at_teams);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§r ", "§a已添加队伍颜色 "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2, 5, 40, 5);
                                            p.sendMessage("§a已添加队伍颜色 "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2);
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
                            case "delteam":
                                List<String> del_teams = new ArrayList<>();
                                del_teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    if (    arg2.equals("RED") ||
                                            arg2.equals("BLUE")||
                                            arg2.equals("GREEN")||
                                            arg2.equals("YELLOW")||
                                            arg2.equals("PINK")||
                                            arg2.equals("AQUA")||
                                            arg2.equals("GRAY")||
                                            arg2.equals("WHITE")) {

                                        if (del_teams.contains(arg2)) {
                                            del_teams.remove(arg2);
                                            CreeperStarBedwars.getPlugin().getGameConfig().set("teams", del_teams);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§r ", "§a已移除队伍颜色 "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2, 5, 40, 5);
                                            p.sendMessage("§a已移除队伍颜色 "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2);
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§c这个队伍颜色不存在( -  - )");
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
                            case "teamName":
                                if (args.length == 3) {
                                    String arg2 = args[1];
                                    String arg3 = args[2];
                                    List<String> sn_teams = new ArrayList<>();
                                    sn_teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");
                                    if (    arg2.equals("RED") ||
                                            arg2.equals("BLUE")||
                                            arg2.equals("GREEN")||
                                            arg2.equals("YELLOW")||
                                            arg2.equals("PINK")||
                                            arg2.equals("AQUA")||
                                            arg2.equals("GRAY")||
                                            arg2.equals("WHITE")) {

                                        if (sn_teams.contains(arg2)) {
                                            CreeperStarBedwars.getPlugin().getGameConfig().set("team-"+arg2.toLowerCase()+".name", arg3);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§r ", "§a已将队伍 "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2 + " §a名称设置为 " + arg3, 5, 40, 5);
                                            p.sendMessage("§a已将队伍 "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2 + " §a名称设置为 " + arg3);
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§c这个队伍颜色不存在( -  - )");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§c无效的队伍颜色");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "缺少了参数, arg2:String(TEAM), arg3:String(TeamName)");
                                }
                                break;
                            case "teams":
                                sender.sendMessage("§a已创建的队伍: ");
                                for (String s : CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams")) {
                                    sender.sendMessage("§7▎ " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+s.toLowerCase()+".color") + s + " §7-§7 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+s.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+s.toLowerCase()+".name"));                                }
                                break;
                            case "setBed":
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    List<String> setbed_teams = new ArrayList<>();
                                    setbed_teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");
                                    if (    arg2.equals("RED") ||
                                            arg2.equals("BLUE")||
                                            arg2.equals("GREEN")||
                                            arg2.equals("YELLOW")||
                                            arg2.equals("PINK")||
                                            arg2.equals("AQUA")||
                                            arg2.equals("GRAY")||
                                            arg2.equals("WHITE")) {

                                        if (setbed_teams.contains(arg2)) {

                                            if ((SetupListener.pos1 != null && SetupListener.pos2 != null) && (SetupListener.pos1.distance(SetupListener.pos2) <= 1.2)) {
                                                String bed_h = SetupListener.pos1.getWorld().getName() + ", " + SetupListener.pos1.getBlockX() + ", " + SetupListener.pos1.getBlockY() + ", " + SetupListener.pos1.getBlockZ();
                                                String bed_b = SetupListener.pos2.getWorld().getName() + ", " + SetupListener.pos2.getBlockX() + ", " + SetupListener.pos2.getBlockY() + ", " + SetupListener.pos2.getBlockZ();
                                                CreeperStarBedwars.getPlugin().getGameConfig().set("team-"+arg2.toLowerCase() + ".bed-f", bed_h);
                                                CreeperStarBedwars.getPlugin().getGameConfig().set("team-"+arg2.toLowerCase() + ".bed-b", bed_b);
                                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                                // CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")
                                                NMSTitleUntils.Title.send(p, "§f ", "§a已设置队伍 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §a的床!", 7, 40, 7);
                                                p.sendMessage("§a已设置队伍 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §a的床, 床方块已刷新请检查设置是否有误");
                                                BedBlockUtils.send(SetupListener.pos1, SetupListener.pos2);
                                            } else {
                                                sender.sendMessage("§c请先正确地使用斧头选择床的床头和床尾");
                                            }

                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§c这个队伍颜色不存在( -  - )");
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
                            case "setspawn":
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    List<String> setspawn_teams = new ArrayList<>();
                                    setspawn_teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");
                                    if (    arg2.equals("RED") ||
                                            arg2.equals("BLUE")||
                                            arg2.equals("GREEN")||
                                            arg2.equals("YELLOW")||
                                            arg2.equals("PINK")||
                                            arg2.equals("AQUA")||
                                            arg2.equals("GRAY")||
                                            arg2.equals("WHITE")) {

                                        if (setspawn_teams.contains(arg2)) {

                                            CreeperStarBedwars.getPlugin().getGameConfig().set("team-"+arg2.toLowerCase()+".spawn", loc_player_str_xyzyp);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§f ", "§a已设置队伍 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §a的出生点", 7, 40, 7);
                                            p.sendMessage("§a已设置队伍 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §a的出生点");

                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§c这个队伍颜色不存在( -  - )");
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
                            case "setGenerator":
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    List<String> setspawn_teams = new ArrayList<>();
                                    setspawn_teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");
                                    if (    arg2.equals("RED") ||
                                            arg2.equals("BLUE")||
                                            arg2.equals("GREEN")||
                                            arg2.equals("YELLOW")||
                                            arg2.equals("PINK")||
                                            arg2.equals("AQUA")||
                                            arg2.equals("GRAY")||
                                            arg2.equals("WHITE")) {

                                        if (setspawn_teams.contains(arg2)) {

                                            if (SetupListener.block != null) {

                                                String loc_str = SetupListener.block.getWorld().getName() + ", " + SetupListener.block.getX() + ", " + SetupListener.block.getY() + ", " + SetupListener.block.getZ();
                                                CreeperStarBedwars.getPlugin().getGameConfig().set("team-"+arg2.toLowerCase()+".generator", loc_str);
                                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                                NMSTitleUntils.Title.send(p, "§f ", "§a已设置队伍 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §a的资源点", 7, 40, 7);
                                                p.sendMessage("§a已设置队伍 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §a的资源点");

                                            } else {
                                                sender.sendMessage("§c请先使用稿子选择方块位置");
                                            }
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§c这个队伍颜色不存在( -  - )");
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
                            case "addShop":
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    if (arg2.equalsIgnoreCase("item")) {
                                        List<String> shops = new ArrayList<>();
                                        shops = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("shop.item");
                                        shops.add(loc_player_str_xyzyp);
                                        CreeperStarBedwars.getPlugin().getGameConfig().set("shop.item", shops);
                                        CreeperStarBedwars.getPlugin().getGameConfig().save();
                                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                        NMSTitleUntils.Title.send(p, "§f ", "§a已添加§e物品商店§a生成点", 7, 40, 7);
                                        p.sendMessage("§a已添加§e物品商店§a生成点");
                                    } else if (arg2.equalsIgnoreCase("team")) {
                                        List<String> shops = new ArrayList<>();
                                        shops = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("shop.team");
                                        shops.add(loc_player_str_xyzyp);
                                        CreeperStarBedwars.getPlugin().getGameConfig().set("shop.team", shops);
                                        CreeperStarBedwars.getPlugin().getGameConfig().save();
                                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                        NMSTitleUntils.Title.send(p, "§f ", "§a已添加§e队伍升级§a生成点", 7, 40, 7);
                                        p.sendMessage("§a已添加§e队伍升级§a生成点");
                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        sender.sendMessage("§c未知的商店类型!");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    sender.sendMessage("§c缺少了参数, arg2:String(type;item/team)");
                                }
                                break;
                            case "addGenerator":
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    if (SetupListener.block != null) {
                                        if (arg2.equalsIgnoreCase("diamond")) {
                                            List<String> generators = new ArrayList<>();
                                            generators = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("generator.diamond");
                                            generators.add(SetupListener.block.getWorld().getName() + ", " + SetupListener.block.getX() + ", " + SetupListener.block.getY() + ", " + SetupListener.block.getZ());
                                            CreeperStarBedwars.getPlugin().getGameConfig().set("generator.diamond", generators);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§f ", "§a已添加§b钻石§a资源点", 7, 40, 7);
                                            p.sendMessage("§a已添加§b钻石§a资源点!");
                                        } else if (arg2.equalsIgnoreCase("emerald")) {
                                            List<String> generators = new ArrayList<>();
                                            generators = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("generator.emerald");
                                            generators.add(SetupListener.block.getWorld().getName() + ", " + SetupListener.block.getX() + ", " + SetupListener.block.getY() + ", " + SetupListener.block.getZ());
                                            CreeperStarBedwars.getPlugin().getGameConfig().set("generator.emerald", generators);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§f ", "§a已添加§2绿宝石§a资源点", 7, 40, 7);
                                            p.sendMessage("§a已添加§2绿宝石§a资源点!");
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            sender.sendMessage("§c未知的游戏资源点类型!");
                                        }
                                    } else {
                                        sender.sendMessage("§c请先使用稿子选择方块!");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    sender.sendMessage("§c缺少了参数, arg2:String(type;diamond/emerald)");
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
