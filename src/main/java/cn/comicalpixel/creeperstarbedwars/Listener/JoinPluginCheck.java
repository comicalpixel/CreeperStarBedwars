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

                s = s + ChatColor.RED + "该房间状态异常! 缺少了必要的前置: \n \n";
                for (String pls : plugins) {
                    s = s + pls + "\n";
                }
                s = s + ChatColor.RED + "\n 提示: 多世界插件必须使用My_Worlds且不可存在其他多世界插件";

                p.sendMessage(s);
                p.kickPlayer(s);

            } else {
                p.sendMessage(ChatColor.RED + "该房间状态异常, 请立即报告给管理员! 原因: 缺少必要前置");
                p.kickPlayer(ChatColor.RED + "该房间状态异常, 已将你送回大厅");
            }
        }

    }

}
