package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Utils.FireballUtil;
import cn.comicalpixel.creeperstarbedwars.Utils.LocationUtil;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.*;

public class FIREBALL_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {return;}

        Player p = e.getPlayer();

        if (e.getItem() == null) return;
        if (e.getItem().getType() != Material.FIREBALL) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        e.setCancelled(true);

        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_fireball_cooldown * 1000L)) {
            int cooldms = (ConfigData.ItemsInGame_fireball_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_fireball_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis());

        ItemStack itemInHand = p.getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().remove(itemInHand);
        }


        Fireball f = (Fireball)p.launchProjectile(Fireball.class);
        Vector direction = p.getEyeLocation().getDirection();
        FireballUtil.setFireballDirection(f, direction);
        f.setVelocity(f.getDirection().multiply(5));
        f.setIsIncendiary(false);
        f.setShooter(p);
        f.setYield(ConfigData.ItemsInGame_fireball_radius);

    }


    @EventHandler
    public void EntityExplode(EntityExplodeEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return;
        if (e.getEntity() instanceof Fireball) {
            e.setCancelled(true);


            // 防爆玻璃 不好的算法:(
            Location explosionLoc = e.getLocation();
            List<Block> blocksToRemove = new ArrayList<>();
//            for (Block db : e.blockList()) {
//
//                boolean blocked = false;
//                Vector direction = db.getLocation().toVector().subtract(explosionLoc.toVector()).normalize();
//
//
//                for (double i = 0; i < db.getLocation().add(0.5, 0, 0.5).distance(explosionLoc.clone().add(0.5, 0, 0.5)); i += 0.2) {
//                    Location checkLoc = explosionLoc.clone().add(direction.clone().multiply(i));
//                    Block block = checkLoc.getBlock();
//                    if (block.getType() == Material.GLASS || block.getType() == Material.STAINED_GLASS) {
//                        blocked = true;
//                        break;
//                    }
//                }
//
//
//                if (!blocked && PlayerBlocks.player_blocks.contains(db) &&
//                        db.getType() != Material.GLASS && db.getType() != Material.STAINED_GLASS) {
//                    blocksToRemove.add(db);
//                }
//            }
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
//            for (Block db : e.blockList()) {
            for (Block db : blocksToRemove) {
                if (PlayerBlocks.player_blocks.contains(db) &&
                        db.getType() != Material.GLASS && db.getType() != Material.STAINED_GLASS
                ) {
                    db.setType(Material.AIR);
                }
            }


            // KB & DAMAGE
            for (Player p : GamePlayers.players) {
                if (p.getLocation().distance(e.getLocation()) < ConfigData.ItemsInGame_fireball_radius && Objects.equals(p.getLocation().getWorld().getName(), e.getLocation().getWorld().getName())) {
                    if (GameStats.get() == 2) {
                        p.damage(ConfigData.ItemsInGame_fireball_damage);
                    }
                    p.setLastDamageCause(new EntityDamageEvent(p, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, ConfigData.ItemsInGame_fireball_damage));
                    p.setVelocity(LocationUtil.getPosition(p.getLocation(), e.getLocation(), 0.9).multiply(ConfigData.ItemsInGame_fireball_vlocity_multiply).setY(ConfigData.ItemsInGame_fireball_vlocity_y));
                }
            }



            // 播放粒子效果
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playEffect(e.getLocation(), Effect.EXPLOSION_LARGE, 2);
            }


        }

    }

    @EventHandler
    public void onPlayerDamageforEntity(EntityDamageByEntityEvent e) {
        if (GameStats.get() != 2 && GameStats.get() != 3) return;
        if (e.getDamager() instanceof Fireball) {
            e.setCancelled(true);
        }
    }


}
