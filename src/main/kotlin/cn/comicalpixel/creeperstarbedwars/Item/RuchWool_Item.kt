package cn.comicalpixel.creeperstarbedwars.Item

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

class RuchWool_Item : Listener {

    var cooldownMap: HashMap<Player, Long> = HashMap()

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent){

        if (GameStats.get() != 2 && GameStats.get() != 3) return

        if (e.isCancelled) return

        if (e.blockPlaced.type != Material.WOOL) {
            return
        }

        if (e.itemInHand.itemMeta.enchants.isEmpty()) {
            return
        }


        var p = e.player

        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap[p]!! < (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().config,"items.rush-wool.cooldown") * 1000L)) {
            val cooldms =
                (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().config,"items.rush-wool.cooldown") * 1000) - (System.currentTimeMillis() - cooldownMap[p]!!).toInt()
            val cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms.toLong())
            val cooldmess =
                cooldms_s.toString() // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.isCancelled = true
            p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().config,"items.rush-wool.cooldown-chat").replace("{cooldown}", cooldmess))
            return
        }

        cooldownMap.put(p, System.currentTimeMillis())

        val face = e.blockPlaced.getFace(e.blockAgainst)
        val vector = Vector(-face.modX, -face.modY, -face.modZ)

        object : BukkitRunnable() {
            var i: Int = ConfigUtils.getInt(CreeperStarBedwars.getPlugin().config, "items.rush-wool.blocks")
            var addi: Int = 1

            var loc: Location = e.block.location.clone().add(vector)

            override fun run() {
                if (i == 0) {
                    cancel()
                    return
                }
                if (loc.block.type != Material.AIR) {
                    cancel()
                    return
                }
                if (PlayerBlocks.safeMap_blocks.contains(loc.block)) {
                    cancel()
                    return
                }
                if (!isValidPlacement(loc)) {
                    cancel()
                    return
                }
                loc.block.type = Material.WOOL
                loc.block.data = e.block.data as Byte
                loc.block.state.update()

                p.playSound(loc, Sound.STEP_WOOL, 10f, 1f)

                PlayerBlocks.player_blocks.add(loc.block)

                loc.add(vector)
                addi++
                i--
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0, 3)

    }


    private fun isValidPlacement(targetLoc: Location): Boolean {
        val entities = targetLoc.world.getNearbyEntities(targetLoc.clone().add(0.5, 0.5, 0.5), 0.5, 0.5, 0.5)

        if (entities.isEmpty()) {
            return true
        } else {
            val var4: Iterator<*> = entities.iterator()

            var entity: Entity?
            do {
                if (!var4.hasNext()) {
                    return true
                }

                entity = var4.next() as Entity?
                if (entity is Player) {
                    val player = entity
                    if (player.gameMode == GameMode.SPECTATOR || !GamePlayers.players.contains(player)) {
                        return true
                    }
                }
            } while (entity is Item)

            return false
        }
    }

}