package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Sidebar_Scoreboard_Task {

    public Sidebar_Scoreboard_Task() {
        task_main();
    }

    public void task_main() {

        new BukkitRunnable() {
            @Override
            public void run() {

                if (GameStats.get() == 1) {
                    for (Player p : GamePlayers.players) {
                        send_lobby(p);
                    }
                }

            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,10);

    }

    public void send_lobby(Player p) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = null;
        List<String> message_list = new ArrayList<>();
        if (Game_Countdown_Task.countdown_running) {
            message_list.addAll(ConfigData.language_sidebarboard_list_lobby_countdown);
            objective = scoreboard.registerNewObjective(ConfigData.language_sidebarboard_list_lobby_countdown.get(0)+" ", "dummy");
        } else if (!Game_Countdown_Task.countdown_running) {
            message_list.addAll(ConfigData.language_sidebarboard_list_lobby_waiting);
            objective = scoreboard.registerNewObjective(ConfigData.language_sidebarboard_list_lobby_waiting.get(0)+" ", "dummy");
        } else {
            objective = scoreboard.registerNewObjective("Error Lobby Sidebar Scoreboard", "dummy");
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int slot = 0;
        message_list.remove(0);
        for (String s : message_list) {
            s = s.replace("{date}", MessageVariableUtils.getDate());
            s = MessageVariableUtils.gameMapInformation_p_s(s);
            s = s.replace("{countdown}", Game_Countdown_Task.countdown+"");
            s = s.replace("{bwim}", BwimResItemManager.Companion.getModeName(p));
            Score score = objective.getScore(s);
            score.setScore(message_list.size() - slot);
            slot++;
        }

        p.setScoreboard(scoreboard);

    }


    public void send_InGame(Player p) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = null;
        List<String> message_list = new ArrayList<>();
        if (GamePlayers.players.contains(p)) {
            message_list.addAll(ConfigData.language_sidebarboard_list_playing_player);
            objective = scoreboard.registerNewObjective(ConfigData.language_sidebarboard_list_playing_player.get(0)+" ", "dummy");
        } else if (GamePlayers.specs.contains(p)) {
            message_list.addAll(ConfigData.language_sidebarboard_list_playing_spec);
            objective = scoreboard.registerNewObjective(ConfigData.language_sidebarboard_list_playing_spec.get(0)+" ", "dummy");
        } else {
            objective = scoreboard.registerNewObjective("Error Game Sidebar Scoreboard", "dummy");
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int slot = 0;
        message_list.remove(0);
        for (String s : message_list) {
            s = s.replace("{date}", MessageVariableUtils.getDate());
            s = MessageVariableUtils.gameMapInformation_p_s(s);
            s = s.replace("{bwim}", BwimResItemManager.Companion.getModeName(p));
            Score score = objective.getScore(s);
            score.setScore(message_list.size() - slot);
            slot++;
        }

        p.setScoreboard(scoreboard);
    }


}
