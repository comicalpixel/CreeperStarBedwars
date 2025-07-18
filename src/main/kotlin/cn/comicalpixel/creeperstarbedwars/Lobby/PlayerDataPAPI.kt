package cn.comicalpixel.creeperstarbedwars.Lobby

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import cn.comicalpixel.creeperstarbedwars.data.GamePlayer
import cn.comicalpixel.creeperstarbedwars.data.GamePlayer.Companion
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer

class PlayerDataPAPI : PlaceholderExpansion() {

    override fun getIdentifier(): String {
        return "klpbw";
    }

    override fun getAuthor(): String {
        return "EnderCreeper Network";
    }

    override fun getVersion(): String {
        return CreeperStarBedwars.getPlugin().getDescription().getVersion();
    }

    override fun onRequest(player: OfflinePlayer, params:String): String? {

        if (CreeperStarBedwars.getPlugin().config.getString("data.type").equals("mongodb", ignoreCase = true)) {
            // MonggoDB
//            val gamePlayer = GamePlayer.get(player.uniqueId) ?: GamePlayer.create(player.uniqueId,player.name)
            val gamePlayer = GamePlayer.get(player.uniqueId)?.apply {
                refreshFromDatabase()
            } ?: GamePlayer.create(player.uniqueId, player.name)
            if (params.equals("plays", true)) {
                return "" + gamePlayer.plays
            }
            if (params.equals("wins", true)) {
                return "" + gamePlayer.wins
            }
            if (params.equals("lost", true)) {
                return "" + gamePlayer.loser
            }
            if (params.equals("kills", true)) {
                return "" + gamePlayer.kills
            }
            if (params.equals("fkills", true)) {
                return "" + gamePlayer.final_kills
            }
            if (params.equals("deaths", true)) {
                return "" + gamePlayer.deaths
            }
            if (params.equals("beds", true)) {
                return "" + gamePlayer.beds
            }
        } else {
            // Config Mode
            if (params.equals("plays", true)) {
                return CreeperStarBedwars.getPlugin().playerDataConfig.getInt(player.name + ".plays").toString()
            }
            if (params.equals("wins", true)) {
                return CreeperStarBedwars.getPlugin().playerDataConfig.getInt(player.name + ".wins").toString()
            }
            if (params.equals("lost", true)) {
                return CreeperStarBedwars.getPlugin().playerDataConfig.getInt(player.name + ".lost").toString()
            }
            if (params.equals("kills", true)) {
                return CreeperStarBedwars.getPlugin().playerDataConfig.getInt(player.name + ".kills").toString()
            }
            if (params.equals("fkills", true)) {
                return CreeperStarBedwars.getPlugin().playerDataConfig.getInt(player.name + ".fkills").toString()
            }
            if (params.equals("deaths", true)) {
                return CreeperStarBedwars.getPlugin().playerDataConfig.getInt(player.name + ".deaths").toString()
            }
            if (params.equals("beds", true)) {
                return CreeperStarBedwars.getPlugin().playerDataConfig.getInt(player.name + ".beds").toString()
            }
        }

        return "null"
    }

}