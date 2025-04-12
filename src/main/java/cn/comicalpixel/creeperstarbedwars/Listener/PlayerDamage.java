package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();

            if (GameStats.get() == 1) {
                e.setCancelled(true);
            } else if (GameStats.get() == 2) {
                if (GamePlayers.players.size() <= 1) {
                    e.setCancelled(true);
                } else {

                    if (noDamagePlayers.contains(p)) {
                        e.setCancelled(true);
                    }
                    if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        e.setDamage(p.getHealth() + 21.8);
                        e.setCancelled(false);
                    }

                }
            } else if (GameStats.get() == 3) {
                e.setCancelled(true);
            }

        }
    }

    public static List<Player> noDamagePlayers = new ArrayList<>();
    public static void noDamageMode(Player p) {
        noDamagePlayers.add(p);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            noDamagePlayers.remove(p);
        },50);
    }
    @EventHandler
    public void PlayerHitplayer_cancelNoDamge(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            if (e.isCancelled()) {
                return;
            }
            Player p = (Player) e.getDamager();
            noDamagePlayers.remove(p);
        }
    }

}
