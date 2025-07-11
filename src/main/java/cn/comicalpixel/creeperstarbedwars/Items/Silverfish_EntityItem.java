package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSilverfish;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Silverfish_EntityItem implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        if (e.getEntity() instanceof Silverfish) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileHit(ProjectileHitEvent e) {
        Entity entity = e.getEntity();
        if (!(e.getEntity() instanceof Snowball)) {
            return;
        }
        Snowball snowball = (Snowball) entity;
        if (!(snowball.getShooter() instanceof Player)) {
            return;
        }
        Player player = (Player) snowball.getShooter();
        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }
        CraftSilverfish sf_entity = (CraftSilverfish) e.getEntity().getLocation().clone().getWorld().spawnEntity(e.getEntity().getLocation().clone().add(0, 1, 0), EntityType.SILVERFISH);
        entity_ai(player, TeamManager.player_teams.get(player), sf_entity);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpawn(CreatureSpawnEvent e) {
        if (e.getEntity() instanceof Silverfish) {
            e.setCancelled(false);
        }
    }

    public void entity_ai(Player player, String team, CraftSilverfish entity) {

        new BukkitRunnable() {
            int i = ConfigData.ItemsInGame_silverfish_survival_time;
            @Override
            public void run() {
                if (i == 0) {
                    entity.damage(entity.getMaxHealth() + 20);
                    cancel();
                    return;
                }
                if (entity.isDead()) {
                    cancel();
                    return;
                }
                entity.setCustomNameVisible(true);
                String s = ConfigData.language_entity_silverfish_nametag;
                s = s
                        .replace("{team_color}", TeamManager.getTeamChatColor(team))
                        .replace("{team_name}", TeamManager.getTeamName(team))
                        .replace("{timer_sec}", i+"")
                ;
                entity.setCustomName(s);
                i--;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,20);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()) {
                    cancel();
                    return;
                }
                entity.setTarget(getNearestPlayer(entity.getLocation(), 14, team));
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,0);

    }

    private static Player getNearestPlayer(Location location, int range, String team) {
        double nearestDistance = Double.MAX_VALUE;
        Player nearestPlayer = null;

        for (Player player : GamePlayers.players) {
            if (player.getGameMode() != GameMode.SPECTATOR && player.getGameMode() != GameMode.CREATIVE) {
                if (!TeamManager.player_teams.get(player).equals(team)) {
                    double distance = location.distance(player.getLocation());
                    if (distance <= range && distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestPlayer = player;
                    }
                }
            }
        }

        return nearestPlayer;
    }

    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent e) {

        if ((e.getEntity() instanceof CraftSilverfish ||e.getEntity() instanceof org.bukkit.entity.Silverfish) && e.getDamager() instanceof IronGolem) {
            if (GameStats.get() != 2) return;
            e.setDamage(0);
            e.setCancelled(true);
        }

    }

}
