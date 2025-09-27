package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class TNTZombie_spawn_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    public HashMap<CraftZombie, String> EntityTeam = new HashMap<CraftZombie, String>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if (e.isCancelled()) return;

        if (e.getItem() == null) return;

//        if (e.getItem().getType() != Material.FIREWORK_CHARGE) return;
        if (!e.getItem().getType().toString().equalsIgnoreCase(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.item-type"))) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();
        Location loc = e.getClickedBlock().getLocation();

        e.setCancelled(true);

        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.cooldown") * 1000)) {
            int cooldms = (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.cooldown") * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(),"items.tnt-zombie.cooldown-chat").replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

        ItemStack itemInHand = e.getPlayer().getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().setItemInHand(null);
        }

        loc = loc.clone().add(0.5, 1.3, 0.5);

        CraftZombie craftZombie = (CraftZombie) loc.getWorld().spawn(loc, Zombie.class);
        craftZombie.setBaby(true);
        craftZombie.getEquipment().setHelmet(new ItemStack(Material.TNT));
        craftZombie.setMetadata("CreeperStarBedwars", new FixedMetadataValue(CreeperStarBedwars.getPlugin(), null));

        entity(p, TeamManager.player_teams.get(p), craftZombie);

    }

    public void entity(Player p, String team, CraftZombie entity) {

        EntityTeam.put(entity, team);

        new BukkitRunnable() {
            int i = ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.FuseTicks");
            @Override
            public void run() {
                if (i == 0) {
                    entity.remove();
                    for (Player gp : GamePlayers.players) {
                        gp.playSound(entity.getLocation(), Sound.EXPLODE, 5f, 1f);
                        gp.playEffect(entity.getLocation(), Effect.EXPLOSION_LARGE, 2);
                        if (entity.getLocation().clone().distance(gp.getLocation().clone()) < 2) {
                            if (gp.getGameMode() != GameMode.SPECTATOR && gp.getGameMode() != GameMode.CREATIVE) {
                                gp.damage(ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.damage"));
                                gp.setLastDamageCause(new EntityDamageEvent(gp, EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.damage")));
                                gp.setVelocity(new Vector(0, 0.2, 0));
                            }
                        }
                    }
                    cancel();
                    return;
                }
                if (entity.isDead()) {
                    cancel();
                    return;
                }
                entity.setCustomNameVisible(true);
                String s = ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.entity-tnt-zombie-nametag");
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
                entity.setTarget(getNearestPlayer(entity.getLocation(), 16, team));
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

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
    public void onPlayerDamageByEntity_AntiZombieHitPlayer(EntityDamageByEntityEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }
        if (e.isCancelled()) {
            return;
        }

        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Zombie)) {
            return;
        }

        CraftZombie zombie = (CraftZombie) e.getDamager();
        if (EntityTeam.containsKey(zombie)) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerDamageByEntity_ZombieHitBoom(EntityDamageByEntityEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }
        if (e.isCancelled()) {
            return;
        }

        if (!(e.getEntity() instanceof Zombie)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        CraftZombie zombie = (CraftZombie) e.getDamager();
        if (!EntityTeam.containsKey(zombie)) {
            return;
        }

        zombie.remove();
        for (Player gp : GamePlayers.players) {
            gp.playSound(zombie.getLocation(), Sound.EXPLODE, 5f, 1f);
            gp.playEffect(zombie.getLocation(), Effect.EXPLOSION_LARGE, 2);
            if (zombie.getLocation().clone().distance(gp.getLocation().clone()) < 2) {
                if (gp.getGameMode() != GameMode.SPECTATOR && gp.getGameMode() != GameMode.CREATIVE) {
                    gp.damage(ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.damage"));
                    gp.setLastDamageCause(new EntityDamageEvent(gp, EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.tnt-zombie.damage")));
                    gp.setVelocity(new Vector(0, 0.2, 0));
                }
            }
        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return;

        if (e.getEntity() instanceof Player) {
            return;
        }

        if (!(e.getEntity() instanceof Zombie)) {
            return;
        }

        CraftZombie zombie = (CraftZombie) e.getEntity();

        if (!EntityTeam.containsKey(zombie)) {
            return;
        }

        e.setDroppedExp(0);
        e.getDrops().clear();

    }

}
