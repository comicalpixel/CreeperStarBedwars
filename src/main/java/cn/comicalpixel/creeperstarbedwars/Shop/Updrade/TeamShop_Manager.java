package cn.comicalpixel.creeperstarbedwars.Shop.Updrade;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Team.Manager.*;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.UpdradeConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class TeamShop_Manager {
    public TeamShop_Manager() {
        task();
    }

    public static void task() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (GameStats.get() != 2 && GameStats.get() != 3) {
                    return;
                }

                for (String s : TeamManager.teams) {
                    // 锋利附魔
                    if (TeamShop_GUI.team_swordSharpness.get(s) > 0) {
                        for (Player p : GamePlayers.players) {
                            if (TeamManager.player_teams.get(p).equals(s)) {
                                Inventory p_inv = p.getInventory();
                                for (int i = 0; i < p_inv.getSize(); i++) {
                                    ItemStack item = p_inv.getItem(i);
                                    if (item != null && item.getType().toString().endsWith("_SWORD")) {
                                        ItemMeta meta = item.getItemMeta();
                                        meta.addEnchant(Enchantment.DAMAGE_ALL, TeamShop_GUI.team_swordSharpness.get(s), true);
                                        item.setItemMeta(meta);
                                    }
                                }
                            }
                        }
                    }
                    if (TeamShop_GUI.team_armorProtection.get(s) > 0) {
                        for (Player p : GamePlayers.players) {
                            if (TeamManager.player_teams.get(p).equals(s)) {
                                PlayerInventory p_inv = p.getInventory();
                                ItemMeta hm = p_inv.getHelmet().getItemMeta();
                                ItemMeta cm = p_inv.getChestplate().getItemMeta();
                                ItemMeta lm = p_inv.getLeggings().getItemMeta();
                                ItemMeta bm = p_inv.getBoots().getItemMeta();
                                hm.addEnchant(Enchantment.WATER_WORKER, 1, true);
                                hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, TeamShop_GUI.team_armorProtection.get(s), true);
                                cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, TeamShop_GUI.team_armorProtection.get(s), true);
                                lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, TeamShop_GUI.team_armorProtection.get(s), true);
                                bm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, TeamShop_GUI.team_armorProtection.get(s), true);
                                p_inv.getHelmet().setItemMeta(hm);
                                p_inv.getChestplate().setItemMeta(cm);
                                p_inv.getLeggings().setItemMeta(lm);
                                p_inv.getBoots().setItemMeta(bm);
                            }
                        }
                    }


                    if (TeamShop_GUI.team_fastDig.get(s) > 0) {
                        for (Player p : GamePlayers.players) {
                            if (TeamManager.player_teams.get(p).equals(s)) {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, TeamShop_GUI.team_fastDig.get(s)-1), true);
                            }
                        }
                    }

                }


            }
        }, 0, 7L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (GameStats.get() != 2 && GameStats.get() != 3) {
                    return;
                }

                for (String s : TeamManager.teams) {
                    if (TeamShop_GUI.team_HealPool.get(s) > 0) {
                        for (Player p : GamePlayers.players) {
                            if (p.getLocation().distance(TeamSpawn.getLocation(p, TeamManager.player_teams.get(p))) <= ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "updrade.heal_pool.radius")) {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, TeamShop_GUI.team_HealPool.get(s)-1), true);
                            }
                        }
                    }
                }
            }
        }, 0, 30L);
    }


    public static void setTeamLevel_updrade(int i, String team) {

        double iron = ConfigUtils.getDouble(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "updrade.team_resource.settings.level-"+i+".res.iron");
        double gold = ConfigUtils.getDouble(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "updrade.team_resource.settings.level-"+i+".res.gold");
        double emerald = ConfigUtils.getDouble(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "updrade.team_resource.settings.level-"+i+".res.emerald");

        switch (team) {
            case "RED":
                TeamGenerator_RED.set(iron, gold, emerald);
                break;
            case "BLUE":
                TeamGenerator_BLUE.set(iron, gold, emerald);
                break;
            case "GREEN":
                TeamGenerator_GREEN.set(iron, gold, emerald);
                break;
            case "YELLOW":
                TeamGenerator_YELLOW.set(iron, gold, emerald);
                break;
            case "PINK":
                TeamGenerator_PINK.set(iron, gold, emerald);
                break;
            case "AQUA":
                TeamGenerator_AQUA.set(iron, gold, emerald);
                break;
            case "GRAY":
                TeamGenerator_GRAY.set(iron, gold, emerald);
                break;
            case "WHITE":
                TeamGenerator_WHITE.set(iron, gold, emerald);
                break;
            default:
                break;
        }


    }

}
