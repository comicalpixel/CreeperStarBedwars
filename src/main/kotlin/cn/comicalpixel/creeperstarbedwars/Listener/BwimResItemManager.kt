package cn.comicalpixel.creeperstarbedwars.Listener

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerPickupItemEvent

class BwimResItemManager : Listener {

    companion object {
        var playerMode = HashMap<Player, Int>()
    }

//    本来是想用来写击杀给予资源的...
//    val player_iron = HashMap<Player, Int>()
//    val player_gold = HashMap<Player, Int>()
//    val player_emerald = HashMap<Player, Int>()
//    val player_diamond = HashMap<Player, Int>()
//
//    val player_xplevel = HashMap<Player, Int>()


    @EventHandler
    fun JoinGame(e: PlayerJoinEvent) {

        val p = e.player

        BwimResItemManager.playerMode.put(p, 0)

    }

    @EventHandler
    fun PickUp(e: PlayerPickupItemEvent) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return

        if (!GamePlayers.players.contains(e.player)) return

        if (e.item.itemStack.type != Material.IRON_INGOT && e.item.itemStack.type != Material.GOLD_INGOT && e.item.itemStack.type != Material.DIAMOND && e.item.itemStack.type != Material.EMERALD) {
            return
        }

        if (e.isCancelled) return

        val p = e.player


        /**/

        if (ConfigData.bwimsel_enabled) {

            if (BwimResItemManager.playerMode.get(p) == 0) {

                e.isCancelled = false
                e.item.remove()
                val meta = e.item.itemStack.itemMeta
                meta.displayName = null
                e.item.itemStack.setItemMeta(meta)

                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim0")

            }
            else if (BwimResItemManager.playerMode.get(p) == 1) {

                e.isCancelled = true
                var xp = 0
                val amount = e.item.itemStack.amount
                if (e.item.itemStack.type == Material.IRON_INGOT) {
                    xp += amount * ConfigData.bwim_conversion_iron
                    e.isCancelled = true
                    e.item.remove()
                }
                if (e.item.itemStack.type == Material.GOLD_INGOT) {
                    xp += amount * ConfigData.bwim_conversion_gold
                    e.isCancelled = true
                    e.item.remove()
                }
                if (e.item.itemStack.type == Material.EMERALD) {
                    xp += amount * ConfigData.bwim_conversion_emerald
                    e.isCancelled = true
                    e.item.remove()
                }
                if (e.item.itemStack.type == Material.DIAMOND) {
                    xp += amount * ConfigData.bwim_conversion_diamond
                    e.isCancelled = true
                    e.item.remove()
                }

                p.level = p.level + xp

                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim1")

            }

        } else {
            if (ConfigData.bwimsel_default == 0) {

                e.isCancelled = false
                e.item.remove()
                val meta = e.item.itemStack.itemMeta
                meta.displayName = null
                e.item.itemStack.setItemMeta(meta)

                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim0")

            } else if (ConfigData.bwimsel_default == 1) {

                e.isCancelled = true
                var xp = 0
                val amount = e.item.itemStack.amount
                if (e.item.itemStack.type == Material.IRON_INGOT) {
                    xp += amount * 1
                    e.isCancelled = true
                    e.item.remove()
                }
                if (e.item.itemStack.type == Material.GOLD_INGOT) {
                    xp += amount * 10
                    e.isCancelled = true
                    e.item.remove()
                }
                if (e.item.itemStack.type == Material.EMERALD) {
                    xp += amount * 100
                    e.isCancelled = true
                    e.item.remove()
                }
                if (e.item.itemStack.type == Material.DIAMOND) {
                    xp += amount * 100
                    e.isCancelled = true
                    e.item.remove()
                }

                p.level = p.level + xp

                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim1")

            }
        }

    }

}