package cn.comicalpixel.creeperstarbedwars

import org.bukkit.entity.Player

class PlayerInGameData {

    companion object {

        var beds = mutableMapOf<Player, Int>()
        var kills = mutableMapOf<Player, Int>()
        var fkills = mutableMapOf<Player, Int>()

        // 统计的获取击杀榜
        fun getTop3Killers(): List<String> {

            val sortedPlayers = kills.keys.sortedByDescending { player ->
                val killCount = kills[player] ?: 0
                val fkillCount = fkills[player] ?: 0
                // 优先普通击杀，其次最终击杀
                killCount * 1000 + fkillCount
            }

            return (0 until 3).map { index ->
                if (index < sortedPlayers.size) {
                    val player = sortedPlayers[index]
                    "${player.name} - ${kills[player] ?: 0} ${fkills[player] ?: 0}"
                } else {
                    "NONE"
                }
            }
        }
        /**/

    }

}