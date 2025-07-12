package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.GameStart;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.PlayerInGameData;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game_WinCheck_Task {

    public static boolean isChecked = false;

    public Game_WinCheck_Task() {
        timer_task();
    }

    public void timer_task() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (GameStats.get() != 2) {
                    return;
                }

                List<String> teams = new ArrayList<>();

                for (Player gp : GamePlayers.players) {
                    if (gp.isOnline() && TeamManager.player_teams.get(gp) != null) {
                        if (!teams.contains(TeamManager.player_teams.get(gp))) {
                            teams.add(TeamManager.player_teams.get(gp));
                        }
                    }
                }
                if (teams.size() == 1) {
                    Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                        GameStats.set(3);
                    },1);
                    gameend_统计(true);
                    win();
                }

            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);
    }

    public static List<Player> wins = GamePlayers.players;
    public static List<Player> specs = GamePlayers.specs;

    public static void win() {

        if (isChecked) {
            return;
        }
        isChecked = true;

        wins = GamePlayers.players;
        specs = GamePlayers.specs;

        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (Player p : wins) {
                NMSTitleUntils.Title.send(p, ConfigData.language_game_end_winner_title, ConfigData.language_game_end_winner_subtitle, 5, 70, 5);
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-end-winner");
                // PlayerData
                CreeperStarBedwars.getPlugin().getPlayerDataConfig().set(p.getName() + ".wins",  CreeperStarBedwars.getPlugin().getPlayerDataConfig().getInt(p.getName() + ".wins") + 1);
                CreeperStarBedwars.getPlugin().getPlayerDataConfig().save();
            }
            for (Player p : specs) {
                NMSTitleUntils.Title.send(p, ConfigData.language_game_end_loser_title, ConfigData.language_game_end_loser_subtitle, 5, 70, 5);
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-end-spec");
            }
        },40);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (Player p : Bukkit.getOnlinePlayers()) {
                CreeperStarBedwars.bungeecord_SendServer(p, ConfigData.bungeecord_lobby);
            }
        },300);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigData.bungeecord_restart_command);
        },340);
    }

    public static void notWinGameEnd() {

        wins = GamePlayers.players;
        specs = GamePlayers.specs;

        gameend_统计(false);

        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (Player p : wins) {
                NMSTitleUntils.Title.send(p, MessageVariableUtils.toPAPI(ConfigData.language_game_end_stalemate_title, p), MessageVariableUtils.toPAPI(ConfigData.language_game_end_stalemate_subtitle,p ), 5, 70, 5);
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-end-nowin");
            }
            for (Player p : specs) {
                NMSTitleUntils.Title.send(p, MessageVariableUtils.toPAPI(ConfigData.language_game_end_stalemate_title, p), MessageVariableUtils.toPAPI(ConfigData.language_game_end_stalemate_subtitle, p), 5, 70, 5);
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-end-nowin");
            }
        },40);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (Player p : Bukkit.getOnlinePlayers()) {
                CreeperStarBedwars.bungeecord_SendServer(p, ConfigData.bungeecord_lobby);
            }
        },300);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigData.bungeecord_restart_command);
        },340);
    }


    public static void gameend_统计(boolean hasWin) {

        String win_team_name = "None";
        String win_team_color = "§7";
//        List<String> teams = new ArrayList<>();
//
//        for (Player gp : GamePlayers.players) {
//            if (gp.isOnline() && TeamManager.player_teams.get(gp) != null) {
//                teams.add(TeamManager.player_teams.get(gp));
//            }
//        }teams
        win_team_name = "";
        win_team_color = "§7";
        if (hasWin) {
            for (String team : TeamManager.teams) {
                if (TeamManager.getTeamPlayerSize(team) != 0 && TeamManager.getTeamPlayerSize(team) >= 1) {
                    win_team_color = win_team_color + TeamManager.getTeamChatColor(team);
                    win_team_name = win_team_name + TeamManager.getTeamName(team);
                }
            }
        } else {
            win_team_name = "None";
        }


        List<String> message_list = new ArrayList<>();
        message_list.addAll(ConfigData.language_game_end_chat);

        List<String> killers_top3 = PlayerInGameData.Companion.getTop3Killers();

        String finalWin_team_color = win_team_color;
        String finalWin_team_name = win_team_name;
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (String s : message_list) {
                s = s.replace("{team_color}", finalWin_team_color).replace("{team_name}", finalWin_team_name);
                s = s
                        .replace("{killer_1}", killers_top3.get(0))
                        .replace("{killer_2}", killers_top3.get(1))
                        .replace("{killer_3}", killers_top3.get(2))
                ;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(MessageVariableUtils.toPAPI(s, p));
                }
            }

        },30);

    }

}
