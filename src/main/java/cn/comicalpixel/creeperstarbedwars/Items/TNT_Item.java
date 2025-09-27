package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerDamage;
import cn.comicalpixel.creeperstarbedwars.Utils.LocationUtil;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.*;

public class TNT_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();


    @EventHandler
    public void PlaceTNTBlock(BlockPlaceEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {return;}

        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if (e.isCancelled()) return;

        Player p = e.getPlayer();

        if (e.getBlock().getType() == null) return;
        if (e.getBlock().getType() != Material.TNT) return;

        e.setCancelled(true);

        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_tnt_cooldown * 1000L)) {
            int cooldms = (ConfigData.ItemsInGame_tnt_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_tnt_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis());

        ItemStack itemInHand = p.getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().setItemInHand(null);
        }


        TNTPrimed tnt = e.getBlock().getLocation().getWorld().spawn(e.getBlock().getLocation().clone().add(0.5, 0.2, 0.5), TNTPrimed.class);
        tnt.setFuseTicks(ConfigData.ItemsInGame_tnt_fticks);
        tnt.setYield(ConfigData.ItemsInGame_tnt_radius);

        tnt_isWho.put(tnt, p);

    }

    public static HashMap<TNTPrimed, Player> tnt_isWho = new HashMap<>();

    @EventHandler
    public void EntityExplode(EntityExplodeEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return;

        if (!(e.getEntity() instanceof TNTPrimed)) {
            return;
        }

        if (e.getEntity() instanceof TNTPrimed) {
            e.setCancelled(true);
        }

        TNTPrimed tnt = (TNTPrimed) e.getEntity();

        // 防爆玻璃 不好的算法:(
        Location explosionLoc = e.getLocation();
        List<Block> blocksToRemove = new ArrayList<>();
        for (Block db : e.blockList()) {

            boolean blocked = false;
            Location blockLoc = db.getLocation().clone().add(0.5, 0.5, 0.5);
            Vector direction = blockLoc.toVector().subtract(explosionLoc.toVector()).normalize();

            for (double i = 0; i < blockLoc.distance(explosionLoc); i += 0.2) {
                Location checkLoc = explosionLoc.clone().add(direction.clone().multiply(i));

                for (double x = -0.5; x <= 0.5; x += 0.5) {
                    for (double y = -0.5; y <= 0.5; y += 0.5) {
                        for (double z = -0.5; z <= 0.5; z += 0.5) {
                            Block block = checkLoc.clone().add(x, y, z).getBlock();
                            if (block.getType() == Material.GLASS || block.getType() == Material.STAINED_GLASS) {
                                blocked = true;
                                break;
                            }
                        }
                        if (blocked) break;
                    }
                    if (blocked) break;
                }
                if (blocked) break;
            }

            if (!blocked && PlayerBlocks.player_blocks.contains(db) &&
                    db.getType() != Material.GLASS && db.getType() != Material.STAINED_GLASS) {
                blocksToRemove.add(db);
            }
        }


        // 防炸地图方块
        for (Block db : blocksToRemove) {
            if (PlayerBlocks.player_blocks.contains(db) &&
                    db.getType() != Material.GLASS && db.getType() != Material.STAINED_GLASS
            ) {
                db.setType(Material.AIR);
            }
        }

        for (Entity entity : explosionLoc.getWorld().getEntities()) {
            if (entity.getLocation().distance(e.getLocation()) < ConfigData.ItemsInGame_tnt_radius && Objects.equals(entity.getLocation().getWorld().getName(), e.getLocation().getWorld().getName())) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    if (GamePlayers.players.contains(p) && p.getGameMode() != GameMode.SPECTATOR) {
                        if (GameStats.get() == 2) {
                            PlayerDamage.Playerkillers.put(p, tnt_isWho.get(tnt));
                            // 格挡
                            if (p.isBlocking()) {
                                p.damage(ConfigData.ItemsInGame_tnt_damage * 0.5);
                            } else {
                                p.damage(ConfigData.ItemsInGame_tnt_damage);
                            }
                            p.setLastDamageCause(new EntityDamageEvent(p, EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, ConfigData.ItemsInGame_tnt_damage));
                        }
                        Vector v = LocationUtil.getPosition(p.getLocation(), e.getLocation(), ConfigData.ItemsInGame_tnt_vlocity_y).multiply(ConfigData.ItemsInGame_tnt_vlocity_multiply);
                        p.setVelocity(v.setY(ConfigData.ItemsInGame_tnt_vlocity_y));
                    }
                } else {
                    if (!(entity instanceof ArmorStand) && !(entity instanceof Villager) && !(entity instanceof Fireball) && !(entity instanceof TNTPrimed) && !(entity instanceof Firework)) {
                        Damageable damageable = (Damageable) entity;
                        damageable.damage(ConfigData.ItemsInGame_tnt_damage * 0.9);
                        Vector v = LocationUtil.getPosition(damageable.getLocation(), e.getLocation(), ConfigData.ItemsInGame_tnt_vlocity_y * 0.7).multiply(ConfigData.ItemsInGame_tnt_vlocity_multiply * 0.7);
                        damageable.setVelocity(v.setY(ConfigData.ItemsInGame_tnt_vlocity_y * 0.7));
                    }
                }
            }
        }
//        for (Player p : GamePlayers.players) {
//            if (p.getLocation().distance(e.getLocation()) < ConfigData.ItemsInGame_tnt_radius && Objects.equals(p.getLocation().getWorld().getName(), e.getLocation().getWorld().getName())) {
//                if (GameStats.get() == 2) {
//                    PlayerDamage.Playerkillers.put(p, tnt_isWho.get(tnt));
//                    // 格挡
//                    if (p.isBlocking()) {
//                        p.damage(ConfigData.ItemsInGame_tnt_damage * 0.5);
//                    } else {
//                        p.damage(ConfigData.ItemsInGame_tnt_damage);
//                    }
//                    p.setLastDamageCause(new EntityDamageEvent(p, EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, ConfigData.ItemsInGame_tnt_damage));
//                }
//                Vector v = LocationUtil.getPosition(p.getLocation(), e.getLocation(), ConfigData.ItemsInGame_tnt_vlocity_y).multiply(ConfigData.ItemsInGame_tnt_vlocity_multiply);
//                p.setVelocity(v.setY(ConfigData.ItemsInGame_tnt_vlocity_y));
//            }
//        }


        // 播放粒子效果
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playEffect(e.getLocation(), Effect.EXPLOSION_HUGE, 2);
        }

    }

    @EventHandler
    public void onPlayerDamageforEntity(EntityDamageByEntityEvent e) {
        if (GameStats.get() != 2 && GameStats.get() != 3) return;
        if (e.getDamager() instanceof TNTPrimed) {
            e.setCancelled(true);
            e.getEntity().setLastDamageCause(new EntityDamageEvent(e.getEntity(), EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, ConfigData.ItemsInGame_tnt_damage));
        }
    }


}
