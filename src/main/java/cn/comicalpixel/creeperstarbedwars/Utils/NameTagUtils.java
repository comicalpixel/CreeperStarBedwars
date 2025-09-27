package cn.comicalpixel.creeperstarbedwars.Utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class NameTagUtils {

    private static final HashMap<UUID, String> playerPrefixes = new HashMap<>();
    private static final HashMap<UUID, String> playerSuffixes = new HashMap<>();

    public static void setPrefix(Player player, String prefix) {
        playerPrefixes.put(player.getUniqueId(), prefix);
        updateNameTagForAllViewers(player);
    }

    public static void setSuffix(Player player, String suffix) {
        playerSuffixes.put(player.getUniqueId(), suffix);
        updateNameTagForAllViewers(player);
    }

    public static void updateNameTagForAllViewers(Player target) {
        String prefix = playerPrefixes.getOrDefault(target.getUniqueId(), "");
        String suffix = playerSuffixes.getOrDefault(target.getUniqueId(), "");

        for (Player viewer : Bukkit.getOnlinePlayers()) {
            Scoreboard viewerScoreboard = viewer.getScoreboard();
            String teamName = "vnt-" + target.getUniqueId().toString().substring(0, 10);

            Team team = viewerScoreboard.getTeam(teamName);
            if (team == null) {
                team = viewerScoreboard.registerNewTeam(teamName);
            }

            team.setPrefix(prefix);
            team.setSuffix(suffix);
            team.addEntry(target.getName());
        }
    }

    public static void updateAllNameTags() {
        // 获取所有在线玩家
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

        // 为每个玩家更新所有其他玩家的名称标签
        for (Player target : onlinePlayers) {
            String prefix = playerPrefixes.getOrDefault(target.getUniqueId(), "");
            String suffix = playerSuffixes.getOrDefault(target.getUniqueId(), "");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                prefix = MessageVariableUtils.toPAPI(prefix, target);
                suffix = MessageVariableUtils.toPAPI(suffix, target);
            }

            for (Player viewer : onlinePlayers) {
                Scoreboard viewerScoreboard = viewer.getScoreboard();
                String teamName = "vnt-" + target.getUniqueId().toString().substring(0, 10);

                Team team = viewerScoreboard.getTeam(teamName);
                if (team == null) {
                    team = viewerScoreboard.registerNewTeam(teamName);
                }

                team.setPrefix(prefix);
                team.setSuffix(suffix);
                team.addEntry(target.getName());
            }
        }
    }

}
