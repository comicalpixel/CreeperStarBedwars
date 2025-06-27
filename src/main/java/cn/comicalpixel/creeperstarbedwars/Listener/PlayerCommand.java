package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommand implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {

        if (e.getPlayer().isOp()) return;

        if (!ConfigData.whitelist_cmds.contains(e.getMessage())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ConfigData.language_command_nopermissions);
        }

    }

}
