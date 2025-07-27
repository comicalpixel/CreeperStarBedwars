package cn.comicalpixel.creeperstarbedwars.GameSetup;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.BedBlockUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.*;
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
                        arg1 = arg1.toLowerCase();
                        switch (arg1) {
                            case "disable":
                                if (args.length == 2 && args[1].equalsIgnoreCase("confirm")) {
                                    sender.sendMessage("§c[SETUP] Changing settings... ");
                                    CreeperStarBedwars.getInstance().getGameConfig().set("setup", false);
                                    CreeperStarBedwars.getInstance().getGameConfig().save();
                                    sender.sendMessage("§c[SETUP] Server restart! ");
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:stop");
                                } else {
                                    sender.sendMessage("§c[SETUP] Please use '/setup disable confirm' to confirm whether SETUP mode is turned off! ");
                                }
                                break;

                            case "setlobby":
                                CreeperStarBedwars.getPlugin().getGameConfig().set("lobby", loc_player_str_xyzyp);
                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                NMSTitleUntils.Title.send(p, "§r ", "§aThe waiting lobby location is set!", 5, 40, 5);
                                p.sendMessage("§aSuccessfully set §e waiting lobby location");
                                break;
                            case "setspec":
                                CreeperStarBedwars.getPlugin().getGameConfig().set("spec", loc_player_str_xyzyp);
                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                NMSTitleUntils.Title.send(p, "§r ", "§aThe SPECTATOR position is set!", 5, 40, 5);
                                p.sendMessage("§aSuccessfully set §eSPECTATOR location");
                                break;
                            case "setcenter":
                                CreeperStarBedwars.getPlugin().getGameConfig().set("map-center", loc_player_str_xyz);
                                CreeperStarBedwars.getPlugin().getGameConfig().save();
                                p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                NMSTitleUntils.Title.send(p, "§r ", "§aThe map center location is set!", 5, 40, 5);
                                p.sendMessage("§aThe map center location is set");
                                break;
                            case "setradius":
                                if (args.length == 2) {
                                    int map_radius = Integer.parseInt(args[1]);
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("map-radius", map_radius);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§aThe map range radius is set to §e"+map_radius, 5, 40, 5);
                                    p.sendMessage("§aThe map range radius is set to §e"+map_radius);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:int");
                                }
                                break;
                            case "setname":
                                if (args.length == 2) {
                                    String map_name = args[1];
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("map-name", map_name);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§aThe map name is set to §e"+map_name, 5, 40, 5);
                                    p.sendMessage("§aThe map name is set to §e"+map_name);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String");
                                }
                                break;
                            case "setauthor":
                                if (args.length == 2) {
                                    String auhtor = args[1];
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("map-author", auhtor);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§aThe map Author is set as §e"+auhtor, 5, 40, 5);
                                    p.sendMessage("§aThe map Author is set as §e"+auhtor);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String");
                                }
                                break;
                            case "minplayers":
                                if (args.length == 2) {
                                    int minplayers = Integer.parseInt(args[1]);
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("minplayers", minplayers);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§aThe minplayers for the game has been set §e"+minplayers, 5, 40, 5);
                                    p.sendMessage("§aThe minplayers for the game has been set: §e"+minplayers);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:int");
                                }
                                break;
                            case "maxplayers":
                                if (args.length == 2) {
                                    int maxplayers = Integer.parseInt(args[1]);
                                    CreeperStarBedwars.getPlugin().getGameConfig().set("maxplayers", maxplayers);
                                    CreeperStarBedwars.getPlugin().getGameConfig().save();
                                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                    NMSTitleUntils.Title.send(p, "§r ", "§aThe maxplayers for the game has been set §e"+maxplayers, 5, 40, 5);
                                    p.sendMessage("§aThe maxplayers for the game has been set: §e"+maxplayers);
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:int");
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
                                            NMSTitleUntils.Title.send(p, "§r ", "§aAdded team colors "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2, 5, 40, 5);
                                            p.sendMessage("§aAdded team colors "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2);
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§cThis team color has already been created! ( -  - )");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§cInvalid team colors! ");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String(TEAM)");
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
                                            NMSTitleUntils.Title.send(p, "§r ", "§aRemoved team colors "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2, 5, 40, 5);
                                            p.sendMessage("§aRemoved team colors "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2);
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§cThis team color does not exist ( -  - )");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§cInvalid team colors! ");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String(TEAM)");
                                }
                                break;
                            case "teamname":
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
                                            NMSTitleUntils.Title.send(p, "§r ", "§aThe team "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2 + " §aname has been set to " + arg3, 5, 40, 5);
                                            p.sendMessage("§aThe team "+CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color")+arg2 + " §aname has been set to " + arg3);
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§cThis team color does not exist( -  - )");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§cInvalid team colors! ");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String(TEAM), arg3:String(TeamName)");
                                }
                                break;
                            case "teamplayers":
                                if (args.length == 2) {
                                    int player_size = Integer.parseInt(args[1]);
                                    if (player_size >= 1) {
                                        CreeperStarBedwars.getPlugin().getGameConfig().set("team-players", player_size);
                                        CreeperStarBedwars.getPlugin().getGameConfig().save();
                                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                        NMSTitleUntils.Title.send(p, "§r ", "§aThe number of players in the team has been set to §e" + player_size + " §aplayers", 5, 40, 5);
                                        p.sendMessage("§aThe number of players in the team has been set to §e" + player_size+" §aplayers");
                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage(ChatColor.RED + "The number of players in the team is too small! int >= 1 !!");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:Int(players size)");
                                }
                                break;
                            case "teams":
                                sender.sendMessage("§aCreated teams List, number of players per team §e " + CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players") + " §a ");
                                for (String s : CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams")) {
                                    sender.sendMessage("§7▎ " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+s.toLowerCase()+".color") + s + " §7-§7 " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+s.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+s.toLowerCase()+".name"));                                }
                                break;
                            case "setbed":
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
                                                NMSTitleUntils.Title.send(p, "§f ", "§aThe team " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §abed is set", 7, 40, 7);
                                                p.sendMessage("§aThe team " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + "§a bed is set, the bed square has been refreshed, please check if the settings are wrong! ");
                                                BedBlockUtils.send(SetupListener.pos1, SetupListener.pos2);
                                            } else {
                                                sender.sendMessage("§a[SETUP] §cUse the diamond axe to set the head and end of the bed blocks (Pos1 and Pos2) first.");
                                            }

                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§cThis team color does not exist! ( -  - ) ");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§cInvalid team colors");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String(TEAM)");
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
                                            NMSTitleUntils.Title.send(p, "§f ", "§aA team is set up " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §aof spawn point", 7, 40, 7);
                                            p.sendMessage("§aA team is set up " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §aof spawn point");

                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§cThis team color does not exist( -  - )");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§cInvalid team colors! ");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String(TEAM)");
                                }
                                break;
                            case "setgenerator":
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
                                                NMSTitleUntils.Title.send(p, "§f ", "§aThe resource point of the team " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §ais set", 7, 40, 7);
                                                p.sendMessage("§aThe resource point of the team " + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".color") + CreeperStarBedwars.getPlugin().getGameConfig().getString("team-"+arg2.toLowerCase()+".name") + " §ais set");

                                            } else {
                                                sender.sendMessage("§cPlease use the diamond_pickaxe to select the block position first!");
                                            }
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            p.sendMessage("§cThis team color does not exist( -  - )");
                                        }

                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        p.sendMessage("§cInvalid team colors! ");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    p.sendMessage(ChatColor.RED + "The args are missing, arg2:String(TEAM)");
                                }
                                break;
                            case "addshop":
                                if (args.length == 2) {
                                    String arg2 = args[1];
                                    if (arg2.equalsIgnoreCase("item")) {
                                        List<String> shops = new ArrayList<>();
                                        shops = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("shop.item");
                                        shops.add(loc_player_str_xyzyp);
                                        CreeperStarBedwars.getPlugin().getGameConfig().set("shop.item", shops);
                                        CreeperStarBedwars.getPlugin().getGameConfig().save();
                                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                        NMSTitleUntils.Title.send(p, "§f ", "§aA Item Shop §a Spawn Points have been added!", 7, 40, 7);
                                        p.sendMessage("§aA Item Shop §a Spawn Points have been added");
                                    } else if (arg2.equalsIgnoreCase("team")) {
                                        List<String> shops = new ArrayList<>();
                                        shops = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("shop.team");
                                        shops.add(loc_player_str_xyzyp);
                                        CreeperStarBedwars.getPlugin().getGameConfig().set("shop.team", shops);
                                        CreeperStarBedwars.getPlugin().getGameConfig().save();
                                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                        NMSTitleUntils.Title.send(p, "§f ", "§aA Team Upgrade §a Spawn Points have been added!", 7, 40, 7);
                                        p.sendMessage("§aA Team Upgrade §a Spawn Points have been added");
                                    } else {
                                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                        sender.sendMessage("§cUnknown shop type!!");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    sender.sendMessage("§cThe args are missing, arg2:String(type;item/team)");
                                }
                                break;
                            case "addgenerator":
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
                                            NMSTitleUntils.Title.send(p, "§f ", "§aAdded §b Diamonds §a resource points", 7, 40, 7);
                                            p.sendMessage("§aAdded §b Diamonds §a resource points");
                                        } else if (arg2.equalsIgnoreCase("emerald")) {
                                            List<String> generators = new ArrayList<>();
                                            generators = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("generator.emerald");
                                            generators.add(SetupListener.block.getWorld().getName() + ", " + SetupListener.block.getX() + ", " + SetupListener.block.getY() + ", " + SetupListener.block.getZ());
                                            CreeperStarBedwars.getPlugin().getGameConfig().set("generator.emerald", generators);
                                            CreeperStarBedwars.getPlugin().getGameConfig().save();
                                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 2);
                                            NMSTitleUntils.Title.send(p, "§f ", "§aAdded §2Emerald §a resource points", 7, 40, 7);
                                            p.sendMessage("§aAdded §2Emerald §a resource points!");
                                        } else {
                                            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                            sender.sendMessage("§cUnknown game resource point type!");
                                        }
                                    } else {
                                        sender.sendMessage("§cPlease use the diamond_pickaxe to select the blocks first!");
                                    }
                                } else {
                                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                                    sender.sendMessage("§cThe args are missing, arg2:String(type;diamond/emerald)");
                                }
                                break;


                            // SETUP Tool (DEBUG)
                            case "toolgetitemstring":
                                if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
                                    p.sendMessage("§r \n§6" + p.getItemInHand().toString());
                                    Bukkit.getLogger().info(p.getName() + "'s ItemInHand(toString): \n" + p.getItemInHand().toString());
                                } else {
                                    p.sendMessage("§cPlease hold the item before using the command! ");
                                }
                                break;


                            default:
                                sender.sendMessage("§aCreeper§eStar§fBedwars §7" + CreeperStarBedwars.getPlugin().getDescription().getVersion() + " §cCaution! Undo/Redo not supported!!\n" +
                                        // "§f/setup toolGetItemString §eSETUP Config Tool: Get Item Config Format (Copy to Console) \n" +
                                        "§f/setup setlobby §eSet waiting lobby spawn \n" +
                                        "§f/setup setspec §eSet spectator spawn \n" +
                                        "§f/setup setcenter §eSet map center \n" +
                                        "§f/setup setradius <int> §eSet map radius \n" +
                                        "§f/setup setName <message> §eSet map name \n" +
                                        "§f/setup setAuthor <message> §eSet map author \n" +
                                        "§f/setup minplayers <int> §eSet minimum players required \n" +
                                        "§f/setup maxplayers <int> §eSet maximum players (Do not mistype!!) \n" +
                                        "§f/setup addteam §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eAdd team \n" +
                                        "§f/setup teamName §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §f<message> §eRename team \n" +
                                        "§f/setup delteam §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eDelete team \n" +
                                        "§f/setup teamplayers <int> §eSet team player count" +
                                        "§f/setup teams §eView all teams \n" +
                                        "§f/setup setBed §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eSet team bed (Select head and foot with axe first) \n" +
                                        "§f/setup setspawn §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eSet team spawn (Stand where you want to set) \n" +
                                        "§f/setup setGenerator §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eSet team generator (Select block with pickaxe first) \n" +
                                        "§f/setup addShop <item/team> §eAdd shop \n" +
                                        "§f/setup addGenerator §f<§bdiamond§f/§2emerald§f> §eAdd generator (Select block with pickaxe first) \n" +
                                        "§f/setup disable §eExit SETUP mode "
                                );
                                break;
                        }
                    } else {
                        sender.sendMessage("§aCreeper§eStar§fBedwars §7" + CreeperStarBedwars.getPlugin().getDescription().getVersion() + " §cCaution! Undo/Redo not supported!!\n" +
                                // "§f/setup toolGetItemString §eSETUP Config Tool: Get Item Config Format (Copy to Console) \n" +
                                "§f/setup setlobby §eSet waiting lobby spawn \n" +
                                "§f/setup setspec §eSet spectator spawn \n" +
                                "§f/setup setcenter §eSet map center \n" +
                                "§f/setup setradius <int> §eSet map radius \n" +
                                "§f/setup setName <message> §eSet map name \n" +
                                "§f/setup setAuthor <message> §eSet map author \n" +
                                "§f/setup minplayers <int> §eSet minimum players required \n" +
                                "§f/setup maxplayers <int> §eSet maximum players (Do not mistype!!) \n" +
                                "§f/setup addteam §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eAdd team \n" +
                                "§f/setup teamName §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §f<message> §eRename team \n" +
                                "§f/setup delteam §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eDelete team \n" +
                                "§f/setup teamplayers <int> §eSet team player count" +
                                "§f/setup teams §eView all teams \n" +
                                "§f/setup setBed §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eSet team bed (Select head and foot with axe first) \n" +
                                "§f/setup setspawn §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eSet team spawn (Stand where you want to set) \n" +
                                "§f/setup setGenerator §f<§cRED§f/§9BLUE§a/GREEN§f/§eYELLOW§f/§dPINK§f/§3AQUA§f/§8GRAY§f/§fWHITE§f> §eSet team generator (Select block with pickaxe first) \n" +
                                "§f/setup addShop <item/team> §eAdd shop \n" +
                                "§f/setup addGenerator §f<§bdiamond§f/§2emerald§f> §eAdd generator (Select block with pickaxe first) \n" +
                                "§f/setup disable §eExit SETUP mode "
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
                                    sender.sendMessage("§c[SETUP] Please use '/setup enable confirm' Check if SETUP mode is enabled for the second time! ");
                                }
                            } else {
                                sender.sendMessage("§c[SETUP] Please use '/setup enable confirm' Check if SETUP mode is enabled for the second time! ");
                            }
                        } else {
                            sender.sendMessage("§aCreeper§eStar§fBedwars §f");
                            sender.sendMessage("§e/setup enable §fEnable SETUP mode ");
                        }
                    } else {
                        sender.sendMessage("§aCreeper§eStar§fBedwars §f");
                        sender.sendMessage("§e/setup enable §fEnable SETUP mode ");
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
