package cn.comicalpixel.creeperstarbedwars.Listener

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData
import cn.comicalpixel.creeperstarbedwars.Task.Game_Countdown_Task
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerListPingEvent;

class ServerMotdListener : Listener {

    @EventHandler
    fun ServerMotd(e:ServerListPingEvent) {

        if (GameStats.get() == 0) {
            return
        }

        e.maxPlayers = (GameData_cfg.maxPlayers)
        if (GameStats.get() == 1) {
            if (Game_Countdown_Task.countdown <= 5) {
                e.motd = ConfigData.bungeecord_motd_playing
            } else {
                e.motd = ConfigData.bungeecord_motd_waiting
            }
        }
        if (GameStats.get() == 2) {
            e.motd = ConfigData.bungeecord_motd_playing
        }
        if (GameStats.get() == 3) {
            e.motd = ConfigData.bungeecord_motd_end
        }

    }

}