package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDamage implements Listener {

    public static Map<Player, Player> Playerkillers = new HashMap<Player, Player>();


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

                    if (p.getGameMode() == GameMode.SPECTATOR) {
                        e.setCancelled(true);
                    }

                    if (noDamagePlayers.contains(p)) {
                        e.setCancelled(true);
                    }
                    if (e.getCause() == EntityDamageEvent.DamageCause.VOID && p.getGameMode() != GameMode.SPECTATOR) {
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
        if (e.isCancelled()) {
            return;
        }
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            noDamagePlayers.remove(p);
        }
    }
    @EventHandler
    public void setkiller_playerHitPlayer(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player && e.getEntity().getLastDamageCause() != null &&  e.getEntity().getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            Player p = (Player) e.getEntity();
            Player killer = (Player) e.getDamager();
            Playerkillers.put(p, killer);
        }
    }
    @EventHandler
    public void resetkiller_PlayerDeath(PlayerDeathEvent e) {
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            Playerkillers.remove(e.getEntity());
        },10);
    }
    public static Player getKiller(Player p) {
        if (Playerkillers.containsKey(p) && Playerkillers.get(p) != null) {
            return Playerkillers.get(p);
        }
        return null;
    }

}
