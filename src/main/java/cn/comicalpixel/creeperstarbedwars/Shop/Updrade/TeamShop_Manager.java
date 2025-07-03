package cn.comicalpixel.creeperstarbedwars.Shop.Updrade;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
                                        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, TeamShop_GUI.team_swordSharpness.get(s));
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }, 0, 7L);
    }

}
