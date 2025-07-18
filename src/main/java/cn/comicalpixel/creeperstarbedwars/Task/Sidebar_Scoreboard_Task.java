package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent.GameTimerEvent_Main;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.PlayerInGameData;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                        send_Inlobby(p);
                    }
                }
                if (GameStats.get() == 2 || GameStats.get() == 3) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        send_InGame(p);
                    }
                }

            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,10);

    }

    public void send_Inlobby(Player p) {
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
            s = MessageVariableUtils.toPAPI(s, p);


            if (s.length() > 40) {
                s = s.substring(0, 40);
            }

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

            s = MessageVariableUtils.getTeamMessageToSidebar(s, p);

            s = MessageVariableUtils.toPAPI(s, p);

            s = s
                    .replace("{events_name}", GameTimerEvent_Main.event_name)
                    .replace("{events_timer}",GameTimerEvent_Main.event_timerString)
            ;

            if (GamePlayers.players.contains(p)) {
                s = s
                        .replace("{kills}", PlayerInGameData.Companion.getKills().getOrDefault(p, 0)+"")
                        .replace("{fkills}", PlayerInGameData.Companion.getFkills().getOrDefault(p, 0)+"")
                        .replace("{beds}", PlayerInGameData.Companion.getBeds().getOrDefault(p, 0)+"")
                ;
                s = s
                        .replace("{myteam_name}", TeamManager.getTeamName(TeamManager.player_teams.get(p)))
                        .replace("{myteam_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                        .replace("{myteam}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + TeamManager.getTeamName(TeamManager.player_teams.get(p)))
                ;
            } else if (GamePlayers.specs.contains(p)) {

            }

            if (s.length() > 40) {
                s = s.substring(0, 40);
            }

            Score score = objective.getScore(s);
            score.setScore(message_list.size() - slot);
            slot++;
        }

        if (ConfigData.nametag_health_enabled) {
            Objective hp = scoreboard.registerNewObjective("NAME_HEALTH", "health");
            hp.setDisplaySlot(DisplaySlot.BELOW_NAME);
            hp.setDisplayName(ChatColor.RED + "❤");
            for (Player sb_health_allp : Bukkit.getOnlinePlayers()) {
                // 设置玩家的计分板
                p.setScoreboard(scoreboard);
                // 更新玩家血量显示
                hp.getScore(sb_health_allp).setScore((int) sb_health_allp.getHealth());
            }
        }

        p.setScoreboard(scoreboard);
    }


}
