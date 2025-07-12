package cn.comicalpixel.creeperstarbedwars.Lobby

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class LobbyCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender?, p1: Command?, p2: String?, args: Array<out String>?): Boolean {
//        TODO("Not yet implemented")

        sender!!.sendMessage("§aCreeper§eStar§fBedwars §7v" + CreeperStarBedwars.getPlugin().getDescription().getVersion())
        sender.sendMessage("§fpowered by §6Xiaol789zxc §e& §bTheGoodBoys §7- §5Ender§2Creeper §fNetwork ")
        sender.sendMessage("§cNone Command.")

        return true
    }

}