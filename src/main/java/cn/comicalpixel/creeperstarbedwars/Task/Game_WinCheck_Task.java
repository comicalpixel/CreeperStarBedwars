package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.GameStart;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Game_WinCheck_Task {

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
                        teams.add(TeamManager.player_teams.get(gp));
                    }
                }
                if (teams.size() == 1) {
                    GameStats.set(3);
                    win();
                }

            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);
    }

    public List<Player> wins = GamePlayers.players;
    public List<Player> specs = GamePlayers.specs;

    public void win() {
        wins = GamePlayers.players;
        specs = GamePlayers.specs;
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (Player p : wins) {
                NMSTitleUntils.Title.send(p, ConfigData.language_game_end_winner_title, ConfigData.language_game_end_winner_subtitle, 2, 70, 5);
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-end-winner");
            }
            for (Player p : specs) {
                NMSTitleUntils.Title.send(p, ConfigData.language_game_end_loser_title, ConfigData.language_game_end_loser_subtitle, 2, 70, 5);
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-end-spec");
            }
        },20);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (Player p : Bukkit.getOnlinePlayers()) {
                CreeperStarBedwars.bungeecord_SendServer(p, ConfigData.bungeecord_lobby);
            }
        },300);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigData.bungeecord_restart_command);
        },340);
    }

}
