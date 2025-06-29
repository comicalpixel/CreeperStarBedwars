package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ActionBarUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Game_Actionbar_Task {
    public Game_Actionbar_Task() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (GameStats.get() == 2 || GameStats.get() == 3) {

                    for (Player p : GamePlayers.players) {
                        if (ConfigData.ItemsInGame_compass_enabled && p.getItemInHand().getType() != Material.COMPASS) {
                            send(p);
                        } else if (!ConfigData.ItemsInGame_compass_enabled) {
                            send(p);
                        }
                    }

                }
            }
        }, 0, 7L); // 参数分别为：延迟执行的时间（tick），重复执行的时间间隔（tick），20 ticks = 1秒
    }

    public void send (Player p) {
        String s = ConfigData.language_team_tracking_actionbar;
        s = s
                .replace("{team_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                .replace("{team_name}", TeamManager.getTeamName(TeamManager.player_teams.get(p)))
        ;
        if (Objects.equals(TeamSpawn.getLocation(p, TeamManager.player_teams.get(p)).getWorld().getName(), p.getLocation().getWorld().getName())) {
            s = s.replace("{tracking}", ((int) p.getLocation().distance(TeamSpawn.getLocation(p, TeamManager.player_teams.get(p))))+"");
        } else {
            s = s.replace("{tracking}", "-");
        }
        ActionBarUtils.sendActionBar(p, MessageVariableUtils.toPAPI(s, p));
    }

}
