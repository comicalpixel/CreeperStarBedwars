package cn.comicalpixel.creeperstarbedwars.data

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

class GamePlayer(val uuid: UUID,val name: String) {

    var kills = CreeperStarBedwars.getInstance().playerStats.getStats(uuid,"kills")
    var final_kills = CreeperStarBedwars.getInstance().playerStats.getStats(uuid,"final_kills")
    var beds = CreeperStarBedwars.getInstance().playerStats.getStats(uuid,"beds")
    var wins = CreeperStarBedwars.getInstance().playerStats.getStats(uuid,"wins")
    var loser = CreeperStarBedwars.getInstance().playerStats.getStats(uuid,"loser")
    var deaths = CreeperStarBedwars.getInstance().playerStats.getStats(uuid,"deaths")
    var plays = CreeperStarBedwars.getInstance().playerStats.getStats(uuid,"plays")
    var bwim_resmode = CreeperStarBedwars.getInstance().playerStats.getBoolean(uuid,"bwim")

    companion object{
        val gamePlayers = mutableMapOf<UUID,GamePlayer>()

        fun create(uuid: UUID, name: String): GamePlayer {
            var gamePlayer = get(uuid)
            if (gamePlayer != null) {
                return gamePlayer
            }
            gamePlayer = GamePlayer(uuid, name)
            gamePlayers[uuid] = gamePlayer
            return gamePlayer
        }

        fun get(uuid: UUID): GamePlayer? {
            for (gamePlayer in gamePlayers.values) {
                if (gamePlayer.uuid == uuid) {
                    return gamePlayer
                }
            }
            return null
        }

        fun getOnlinePlayers(): List<GamePlayer> {
            val onlinePlayers: MutableList<GamePlayer> = ArrayList()
            for (gamePlayer in gamePlayers.values) {
                if (gamePlayer.isOnline()) {
                    onlinePlayers.add(gamePlayer)
                }
            }
            return onlinePlayers
        }
    }

    fun isOnline(): Boolean {
        return Bukkit.getPlayer(uuid)!= null
    }

    fun getPlayer(): Player {
        return Bukkit.getPlayer(uuid)
    }

    fun refreshFromDatabase() {
        val stats = CreeperStarBedwars.getInstance().playerStats.getPlayerStats(this.uuid)
        this.plays = stats?.getInteger("plays") ?: 0
        this.wins = stats?.getInteger("wins") ?: 0
        this.loser = stats?.getInteger("loser") ?: 0
        this.kills = stats?.getInteger("kills") ?: 0
        this.final_kills = stats?.getInteger("final_kills") ?: 0
        this.deaths = stats?.getInteger("deaths") ?: 0
        this.beds = stats?.getInteger("beds") ?: 0
    }

}