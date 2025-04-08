package cn.comicalpixel.creeperstarbedwars.Listener

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPickupItemEvent

class BwimResItemManager : Listener {

    val player_iron = HashMap<Player, Int>()
    val player_gold = HashMap<Player, Int>()
    val player_emerald = HashMap<Player, Int>()
    val player_diamond = HashMap<Player, Int>()


    @EventHandler
    fun PickUp(e: PlayerPickupItemEvent) {
    }

}