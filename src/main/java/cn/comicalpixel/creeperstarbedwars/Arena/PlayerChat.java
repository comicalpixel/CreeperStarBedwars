package cn.comicalpixel.creeperstarbedwars.Arena;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        if (!CreeperStarBedwars.getPlugin().getConfig().getBoolean("game-players-chat.enable")) {
            return;
        }

        if (e.isCancelled()) return;

        Player p = e.getPlayer();

        if (GameStats.get() == 1) {

            String message = ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.chat-lobby-waiting");

            message = message.replace("{player}", p.getName()).replace("{message}", e.getMessage());

            for (Player online_p : Bukkit.getOnlinePlayers()) {
                online_p.sendMessage(message);
            }

            e.setCancelled(true);
        }
        if (GameStats.get() == 2 || GameStats.get() == 3) {

            e.setCancelled(true);

            if (GamePlayers.players.contains(p)) {

                String message = ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.chat-playing-player-team");

                boolean isShout = false;

                // 检查是否含喊话前缀
                for (String shout_prefix : ConfigUtils.getStringList(CreeperStarBedwars.getPlugin().getConfig(), "game-players-chat.shout-prefix")) {
                    if (e.getMessage().startsWith(shout_prefix)) {
                        message = ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.chat-playing-player-shout");
                        e.setMessage(e.getMessage().replaceFirst(shout_prefix, ""));
                        isShout = true;
                        break;
                    }
                }

                message = message.replace("{player}", p.getName()).replace("{message}", e.getMessage())
                        .replace("{team_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p))).replace("{team_name}", TeamManager.getTeamName(TeamManager.player_teams.get(p)));

                // 判断是否为喊话
                if (isShout) {

                    for (Player online_p : Bukkit.getOnlinePlayers()) {
                        online_p.sendMessage(message);
                    }

                } else {

                    for (Player online_p : Bukkit.getOnlinePlayers()) {

                        if (GamePlayers.players.contains(online_p)) {
                            if (TeamManager.player_teams.containsKey(online_p) && TeamManager.player_teams.get(online_p).equals(TeamManager.player_teams.get(p))) {
                                online_p.sendMessage(message);
                            }
                        }

                    }

                }

            } else {

                String message = ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.chat-playing-spec");
                message = message.replace("{player}", p.getName()).replace("{message}", e.getMessage());

                if (CreeperStarBedwars.getPlugin().getConfig().getBoolean("game-players-chat.enable")) {

                    for (Player online_p : Bukkit.getOnlinePlayers()) {
                        online_p.sendMessage(message);
                    }

                } else {

                    for (Player specs : GamePlayers.specs) {
                        specs.sendMessage(message);
                    }

                }

            }
        }

    }

}
