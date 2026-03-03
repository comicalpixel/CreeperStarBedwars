package cn.comicalpixel.creeperstarbedwars.Utils;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;
import org.bukkit.potion.PotionEffectType;

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
        boolean isInvisible = target.hasPotionEffect(PotionEffectType.INVISIBILITY);

        for (Player viewer : Bukkit.getOnlinePlayers()) {
            Scoreboard viewerScoreboard = viewer.getScoreboard();
            String teamName = "vnt-" + target.getUniqueId().toString().substring(0, 10);

            Team team = viewerScoreboard.getTeam(teamName);
            if (team == null) {
                team = viewerScoreboard.registerNewTeam(teamName);
            }

            team.setPrefix(prefix);
            team.setSuffix(suffix);
            team.setNameTagVisibility(isInvisible ? NameTagVisibility.NEVER : NameTagVisibility.ALWAYS);
            team.addEntry(target.getName());
        }
    }

    public static void updateAllNameTags() {
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

        for (Player target : onlinePlayers) {
            String prefix = playerPrefixes.getOrDefault(target.getUniqueId(), "");
            String suffix = playerSuffixes.getOrDefault(target.getUniqueId(), "");
            boolean isInvisible = target.hasPotionEffect(PotionEffectType.INVISIBILITY);

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
                team.setNameTagVisibility(isInvisible ? NameTagVisibility.NEVER : NameTagVisibility.ALWAYS);
                team.addEntry(target.getName());
            }
        }
    }

    public static void hidePlayerNameTag(Player player) {
        String prefix = playerPrefixes.getOrDefault(player.getUniqueId(), "");
        String suffix = playerSuffixes.getOrDefault(player.getUniqueId(), "");
        
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            Scoreboard viewerScoreboard = viewer.getScoreboard();
            String teamName = "vnt-" + player.getUniqueId().toString().substring(0, 10);

            Team team = viewerScoreboard.getTeam(teamName);
            if (team == null) {
                team = viewerScoreboard.registerNewTeam(teamName);
            }

            team.setPrefix(prefix);
            team.setSuffix(suffix);
            team.setNameTagVisibility(NameTagVisibility.NEVER);
            team.addEntry(player.getName());
        }
    }

    public static void showPlayerNameTag(Player player) {
        String prefix = playerPrefixes.getOrDefault(player.getUniqueId(), "");
        String suffix = playerSuffixes.getOrDefault(player.getUniqueId(), "");
        
        for (Player viewer : Bukkit.getOnlinePlayers()) {
            Scoreboard viewerScoreboard = viewer.getScoreboard();
            String teamName = "vnt-" + player.getUniqueId().toString().substring(0, 10);

            Team team = viewerScoreboard.getTeam(teamName);
            if (team == null) {
                team = viewerScoreboard.registerNewTeam(teamName);
            }

            team.setPrefix(prefix);
            team.setSuffix(suffix);
            team.setNameTagVisibility(NameTagVisibility.ALWAYS);
            team.addEntry(player.getName());
        }
    }

}
