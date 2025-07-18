package cn.comicalpixel.creeperstarbedwars.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class JoinPluginCheck implements Listener {

    public static List<String> plugins = new ArrayList<String>();

    public static void check() {

        plugins.clear();

        Plugin pl_protocalLib = Bukkit.getPluginManager().getPlugin("ProtocolLib");
        if (pl_protocalLib == null) {
            plugins.add("ProtocolLib");
        }

        Plugin pl_MyWorlds = Bukkit.getPluginManager().getPlugin("My_Worlds");
        if (pl_MyWorlds == null) {
            plugins.add("My_Worlds (依赖: BKCommonLib)");
        }

        Plugin pl_NameTagEdit = Bukkit.getPluginManager().getPlugin("NameTagEdit");
        if (pl_NameTagEdit == null) {
            plugins.add("NameTagEdit");
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (!plugins.isEmpty()) {
            if (p.isOp()) {

                String s = "";

                s = s + ChatColor.RED + "The room is in an abnormal state! Necessary pre-plugins are missing: \n \n";
                for (String pls : plugins) {
                    s = s + pls + "\n";
                }
                s = s + ChatColor.RED + "\n Tip: Multi-world plug-ins must use My_Worlds and no other multi-world plug-ins exist";

                p.sendMessage(s);
                p.kickPlayer(s);

            } else {
                p.sendMessage(ChatColor.RED + "The status of this room is abnormal, please report it to the administrator immediately! Cause: Lack of necessary preemption");
                p.kickPlayer(ChatColor.RED + "The room is in an abnormal state and has returned you to the lobby");
            }
        }

    }

}
