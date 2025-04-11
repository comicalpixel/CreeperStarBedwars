package cn.comicalpixel.creeperstarbedwars.Utils;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerUtils {


    public static void reset(Player p) {

        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(24);
        p.setFireTicks(0);
        p.setRemainingAir(p.getMaximumAir());
        p.setBedSpawnLocation(null);

    }

    public static void clear_effects(Player p) {

        for (PotionEffect effect : p.getActivePotionEffects()) {
            // player.addPotionEffect(new PotionEffect(effect.getType(), 1, 1, false, false));
            if (p.hasPotionEffect(effect.getType())) {
                p.removePotionEffect(effect.getType());
                // player.sendMessage("clear effects!");
            }
        }

    }


    public static void refresh_players_entity(Player p) {

        for (Player allp : Bukkit.getOnlinePlayers()) {
            p.showPlayer(p);
            allp.showPlayer(p);
            p.showPlayer(allp);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.showPlayer(p);
                    allp.showPlayer(p);
                    p.showPlayer(allp);
                    cancel();
                }
            }.runTaskLater(CreeperStarBedwars.getPlugin(), 10);
        }

    }

    public static void leave_game(Player p) {

        GamePlayers.players.remove(p);
        GamePlayers.specs.remove(p);

        p.setGameMode(GameMode.SPECTATOR);

        CreeperStarBedwars.bungeecord_SendServer(p, ConfigData.bungeecord_lobby);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            if (p.isOnline()) {
                p.kickPlayer("You have already left the game, but it seems you have not been automatically transferred back to the lobby.");
            }
        },(1+1+1+1+1+1+1+1+1+1)*2);

    }

}
