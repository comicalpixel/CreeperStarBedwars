package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerDamage;
import cn.comicalpixel.creeperstarbedwars.Utils.LocationUtil;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireworkSit_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.getItem() == null) return;

        if (e.getItem().getType() != Material.FIREWORK) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();

        // 检查冷却时间
        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_firework_cooldown * 1000)) {
            int cooldms = (ConfigData.ItemsInGame_firework_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_firework_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

        ItemStack itemInHand = e.getPlayer().getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            e.getPlayer().getInventory().remove(itemInHand);
        }

        e.setCancelled(true);


        FireworkEffect effect = FireworkEffect.builder()
                .flicker(!new Boolean(false))
                .trail(!new Boolean(false))
                .withColor(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
                .build();
        spawnFirework(p.getLocation(), effect, 1, p);

        damagesafe_players.add(p);

    }


    private static void spawnFirework(Location location, FireworkEffect effect, int index, Player p) {
        location = location.clone().add(0,1,0);
        org.bukkit.entity.Firework firework = location.getWorld().spawn(location, org.bukkit.entity.Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.setPower(index); // Random power between 1 and 3
        meta.addEffects(effect);
        firework.setFireworkMeta(meta);

        firework.setVelocity(new Vector(0,0.01,0));

        firework.setPassenger(p);
        NMSTitleUntils.Title.send(p, " ", ConfigData.ItemsInGame_firework_subtitle, 10, 40, 10);


        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            if (firework.isDead()) {
                for (Player p1 : Bukkit.getOnlinePlayers()) {
                    p1.playSound(firework.getLocation(), Sound.EXPLODE, 10f, 0);
                    p1.playEffect(firework.getLocation(), Effect.EXPLOSION_HUGE, 1);
                }
                for (Player p2 : GamePlayers.players) {
                    if (p2.getLocation().distance(firework.getLocation()) < 5) {
                        if (p2 != p) {
                            p2.damage(7);
                        } else {
                            p2.damage(3);
                        }
                        p2.setLastDamageCause(new EntityDamageEvent(p, EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, ConfigData.ItemsInGame_tnt_damage));
                        PlayerDamage.Playerkillers.put(p2, p);
                        Vector v = LocationUtil.getPosition(p.getLocation(), firework.getLocation(), 0.5).multiply(0.5);
                        p2.setVelocity(v.setY(ConfigData.ItemsInGame_tnt_vlocity_y));
                    }
                }
            }
        },30);

    }


    private static List<Player> damagesafe_players = new ArrayList<>();

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();

            if (damagesafe_players.contains(p) && e.getCause() != null && (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
                e.setDamage(e.getDamage() - 10);
            }
            if (damagesafe_players.contains(p) && e.getCause() != null && (e.getCause() == EntityDamageEvent.DamageCause.FALL || e.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK)) {
                e.setDamage(1);
                damagesafe_players.remove(p);
            }

        }

    }

}
