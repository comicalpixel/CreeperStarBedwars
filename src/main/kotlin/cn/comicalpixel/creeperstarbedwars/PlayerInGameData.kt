package cn.comicalpixel.creeperstarbedwars

import org.bukkit.entity.Player

class PlayerInGameData {

    companion object {

        var beds = mutableMapOf<Player, Int>()
        var kills = mutableMapOf<Player, Int>()
        var fkills = mutableMapOf<Player, Int>()

    }

}