package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSilverfish;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.isCancelled()) return;

        if (e.getItem() == null) return;

        if (e.getItem().getType() != Material.SNOW_BALL) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();

        // 检查冷却时间
        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_silverfish_cooldown * 1000)) {
            int cooldms = (ConfigData.ItemsInGame_silverfish_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_silverfish_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

        ItemStack itemInHand = e.getPlayer().getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            e.getPlayer().getInventory().remove(itemInHand);
        }

        Snowball snowball = p.launchProjectile(Snowball.class);
        snowball.setBounce(false);
        snowball.setShooter(p);
        snowball.setMetadata("EnderklpBW_Sliverfish", new FixedMetadataValue(CreeperStarBedwars.getPlugin(), null));

    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {

        if (event.getEntity() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getEntity();

            if (snowball.getShooter() != null && snowball.getShooter() instanceof Player) {

                Player player = (Player) snowball.getShooter();

                if (snowball.isDead() && snowball.hasMetadata("EnderklpBW_Sliverfish")) {
                    Location locAbove = snowball.getLocation().add(0, 1.0, 0);
                    CraftSilverfish craftSilverfish = (CraftSilverfish) locAbove.getWorld().spawnEntity(locAbove, EntityType.SILVERFISH);
                    entity_ai(player, TeamManager.player_teams.get(player), craftSilverfish);
                }

            }
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
    public void EntityDamage(EntityDamageByEntityEvent e) {

        if ((e.getEntity() instanceof CraftSilverfish ||e.getEntity() instanceof org.bukkit.entity.Silverfish) && e.getDamager() instanceof IronGolem) {
            if (GameStats.get() != 2) return;
            e.setDamage(0);
            e.setCancelled(true);
        }

    }

}
