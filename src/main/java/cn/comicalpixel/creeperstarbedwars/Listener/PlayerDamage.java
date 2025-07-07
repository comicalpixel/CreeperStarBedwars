package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDamage implements Listener {

    public static Map<Player, Player> Playerkillers = new HashMap<Player, Player>();


    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if (e.isCancelled()) return;

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
            if (!GamePlayers.players.contains(killer)) {return;}
            Playerkillers.put(p, killer);
            // 收到伤害就移除隐身效果
            if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
        }
    }
    @EventHandler
    public void setkiller_playerHitPlayer_projectile(EntityDamageByEntityEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {return;}

        if (e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;

        if (!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Arrow)) {return;}

        if (e.isCancelled()) {return;}

        Player p = (Player) e.getEntity();
        Arrow arrow = (Arrow) e.getDamager();
        Player damager = null;
        if (arrow.getShooter() instanceof Player) {
            damager = (Player) arrow.getShooter();
            // 收到伤害就移除隐身效果
            if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
        } else {
            return;
        }

        Playerkillers.put(p, damager);
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
