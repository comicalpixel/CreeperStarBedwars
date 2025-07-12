package cn.comicalpixel.creeperstarbedwars.Lobby

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import me.clip.placeholderapi.PlaceholderAPI
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer

class LobbyPDataPAPI : PlaceholderExpansion() {

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

        return null
    }

}