package cn.comicalpixel.creeperstarbedwars.Items

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks
import cn.comicalpixel.creeperstarbedwars.Utils.FireballUtil
import cn.comicalpixel.creeperstarbedwars.Utils.LocationUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Effect
import org.bukkit.Material
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.player.PlayerInteractEvent

class FIREBALL_Item : Listener {

    private val cooldownMap: MutableMap<Player, Long> = HashMap()

    @EventHandler
    fun onPlayerInteract(e: PlayerInteractEvent) {
        val p = e.player

        if (e.item != null) {
            if (e.item.type == Material.FIREBALL) {
                if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {

                    if (GameStats.get() != 2 && GameStats.get() != 3) return

                    e.isCancelled = true

                    // 检查冷却时间
                    if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap[p]!! < (ConfigData.ItemsInGame_fireball_cooldown * 1000)) {
                        val cooldms = (ConfigData.ItemsInGame_fireball_cooldown * 1000) - (System.currentTimeMillis() - cooldownMap[p]!!).toInt()
                        val cooldms_s = convertMillisecondsToSeconds(cooldms.toLong())
                        val cooldmess =
                            cooldms_s.toString() // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
                        e.isCancelled = true
                        p.sendMessage(ConfigData.ItemsInGame_fireball_cooldown_chat.replace("{cooldown}", cooldmess))
                        return
                    }

                    cooldownMap[p] = System.currentTimeMillis() // 设置当前玩家的冷却时间

                    val itemInHand = p.itemInHand
                    if (itemInHand.amount > 1) {
                        itemInHand.amount = itemInHand.amount - 1
                    } else {
                        p.inventory.remove(itemInHand)
                    }

                    //                    Fireball fireball = (Fireball) p.launchProjectile(Fireball.class); //.getLocation().add(0,0.5,0);
//                    // Fireball fireball = p.getWorld().spawn(p.getLocation().add(0,1,0), Fireball.class);
//                    fireball.setBounce(false); // 设置火焰弹不反弹
//                    fireball.setShooter(p);
//                    fireball.setYield(1); // 设置火焰弹的爆炸威力
//                    Vector direction = p.getLocation().getDirection().multiply(0.5);
//                    fireball.setVelocity(direction);// 设置发射者为玩家
                    val f = p.launchProjectile(Fireball::class.java) as Fireball
                    val direction = p.eyeLocation.direction
                    FireballUtil.setFireballDirection(f, direction)
                    f.velocity = f.direction.multiply(5)
                    f.setIsIncendiary(false)
                    f.shooter = p
                    f.yield = 3f

                    // 获取玩家的视线方向
                    // Vector direction = p.getLocation().getDirection();
                    //Vector direction = p.getLocation().getDirection().multiply(0.1);

// 设置火焰弹的飞行方向
                    // fireball.setDirection(direction);
                }
            }
        }
    }

    @EventHandler
    fun onEntityExplode(e: EntityExplodeEvent) {
        if (e.entity is Fireball) {
            // 取消事件，防止生成火焰和破坏方块
            e.isCancelled = true

            // 获取爆炸位置
            val location = e.location
            // 获取世界
            val world = location.world

            for (b in e.blockList()) {
                if (PlayerBlocks.player_blocks.contains(b) && (b.type != Material.GLASS || b.type != Material.STAINED_GLASS)) {
                    b.type = Material.AIR
                }
            }

            for (p in world.players) {
                if (p.location.distance(e.location) < 3) {
                    if (GameStats.get() == 2) {
                        p.damage(ConfigData.ItemsInGame_fireball_damage - 0.1)
                    }
                    p.lastDamageCause = EntityDamageEvent(p, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, 3)
                    p.velocity = LocationUtil.getPosition(p.location, e.location, 0.9).multiply(ConfigData.ItemsInGame_fireball_vlocity_multiply)
                }
            }

            for (p in Bukkit.getOnlinePlayers()) {
                p.playEffect(e.location, Effect.EXPLOSION_LARGE, 2)
            }
        }
    }

    @EventHandler
    fun onPlayerDamageforEntity(e: EntityDamageByEntityEvent) {
        if (e.damager is Fireball) {
            e.isCancelled = true
        }
    }


    fun convertMillisecondsToSeconds(milliseconds: Long): Int {
        return (milliseconds / 1000 + 1).toInt() //+1实现防出现0秒
    }

}