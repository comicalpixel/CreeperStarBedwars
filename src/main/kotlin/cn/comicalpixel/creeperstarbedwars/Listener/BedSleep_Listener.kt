package cn.comicalpixel.creeperstarbedwars.Listener

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class BedSleep_Listener : Listener {

    @EventHandler
    fun ClickBlockEvent(e: PlayerInteractEvent) {

        if (GameStats.get() != 2) {return}

        if (e.action != Action.RIGHT_CLICK_BLOCK) {return}

        if (e.clickedBlock != null && e.clickedBlock.type == Material.BED_BLOCK) {
            if (e.isBlockInHand && e.player.isSneaking) {
                e.isCancelled = false
            } else {
                e.isCancelled = true
                e.player.sendMessage(ConfigData.language_bed_sleep_chat)
            }
        }

    }

}