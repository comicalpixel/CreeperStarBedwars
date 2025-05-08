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
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftIronGolem;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class DreamGuard_EntityItem implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void onDreamGuard(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if (e.isCancelled()) return;

        if (e.getItem() == null) return;

        if (e.getItem().getType() != Material.MONSTER_EGG) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();
        Location loc = e.getClickedBlock().getLocation();

        // 检查冷却时间
        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_dreamguard_cooldown * 1000)) {
            int cooldms = (ConfigData.ItemsInGame_dreamguard_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_dreamguard_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

        ItemStack itemInHand = e.getPlayer().getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            e.getPlayer().getInventory().remove(itemInHand);
        }

        loc = loc.clone().add(0.5, 1.3, 0.5);


        CraftIronGolem craftIronGolem = (CraftIronGolem) loc.getWorld().spawn(loc, IronGolem.class);
        craftIronGolem.setMaxHealth(ConfigData.ItemsInGame_dreamguard_maxHealth);
        entity(p, TeamManager.player_teams.get(p), craftIronGolem);

    }

    public void entity(Player p, String team, CraftIronGolem entity) {

        new BukkitRunnable() {
            int i = ConfigData.ItemsInGame_dreamguard_survival_time;
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
                String s = ConfigData.language_entity_dreamguard_nametag;
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

}
