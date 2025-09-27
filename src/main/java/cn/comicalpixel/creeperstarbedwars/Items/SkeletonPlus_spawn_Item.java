package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class SkeletonPlus_spawn_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    public HashMap<CraftSkeleton, String> EntityTeam = new HashMap<CraftSkeleton, String>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if (e.isCancelled()) return;

        if (e.getItem() == null) return;

//        if (e.getItem().getType() != Material.BONE) return;
        if (!e.getItem().getType().toString().equalsIgnoreCase(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "items.skeleton-plus-spawn.item-type"))) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();
        Location loc = e.getClickedBlock().getLocation();

        e.setCancelled(true);

        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.skeleton-plus-spawn.cooldown") * 1000)) {
            int cooldms = (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.skeleton-plus-spawn.cooldown") * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(),"items.skeleton-plus-spawn.cooldown-chat").replace("{cooldown}", cooldmess));
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

        CraftSkeleton craftEntity = (CraftSkeleton) loc.getWorld().spawn(loc, Skeleton.class);
        craftEntity.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        craftEntity.setMetadata("CreeperStarBedwars", new FixedMetadataValue(CreeperStarBedwars.getPlugin(), null));

        entity(p, TeamManager.player_teams.get(p), craftEntity);

    }

    public void entity(Player p, String team, CraftSkeleton entity) {

        EntityTeam.put(entity, team);

        new BukkitRunnable() {
            int i = ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.skeleton-plus-spawn.entity-survival-time");
            @Override
            public void run() {
                if (i == 0) {
                    entity.remove();
                    cancel();
                    return;
                }
                if (entity.isDead()) {
                    cancel();
                    return;
                }
                entity.setCustomNameVisible(true);
                String s = ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.entity-skeleton-plus-nametag");
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
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()) {
                    cancel();
                    return;
                }
                if (entity.getTarget() == null) {
                    return;
                }

                Vector direction = entity.getTarget().getLocation().toVector()
                        .subtract(entity.getLocation().toVector())
                        .normalize();

                double speed = 2.5;

                Arrow arrow = entity.launchProjectile(Arrow.class);
                arrow.setVelocity(direction.multiply(speed));
                arrow.setShooter(entity);

                entity.getWorld().playSound(entity.getLocation(),
                        Sound.SHOOT_ARROW, 1.0f, 1.0f);
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0, 3);


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
    public void onEntityDeath(EntityDeathEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return;

        if (e.getEntity() instanceof Player) {
            return;
        }

        if (!(e.getEntity() instanceof Skeleton)) {
            return;
        }

        CraftSkeleton entity = (CraftSkeleton) e.getEntity();

        if (!EntityTeam.containsKey(entity)) {
            return;
        }

        e.setDroppedExp(0);
        e.getDrops().clear();

    }

}
