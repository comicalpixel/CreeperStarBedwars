package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ActionBarUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Compass_Item {
    public Compass_Item() {
        if (!ConfigData.ItemsInGame_compass_enabled) {
            return;
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (GameStats.get() == 2 || GameStats.get() == 3) {

                    for (Player p : GamePlayers.players) {
                        if (ConfigData.ItemsInGame_compass_enabled && p.getItemInHand().getType() == Material.COMPASS) {
                            send(p);
                        }
                    }

                }
            }
        }, 0, 7L); // 参数分别为：延迟执行的时间（tick），重复执行的时间间隔（tick），20 ticks = 1秒
    }

    public void send(Player p) {

        Player near_Player = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Player gameplayer : GamePlayers.players) {
            if (gameplayer != p) {
                if (gameplayer.getGameMode() != GameMode.SPECTATOR) {
                    if (!Objects.equals(TeamManager.getTeamName(TeamManager.player_teams.get(p)), TeamManager.getTeamChatColor(TeamManager.player_teams.get(gameplayer)))) {
                        double distance = p.getLocation().distance(gameplayer.getLocation());
                        if (distance < nearestDistance) {
                            nearestDistance = distance;
                            near_Player = gameplayer;
                        }
                    }
                }
            }
        }

        if (near_Player != null) {

            int i2 = (int) nearestDistance;

            String s = ConfigData.language_compass_tracking_actionbar;

            s = s
                    .replace("{team_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(near_Player)))
                    .replace("{team_name}", TeamManager.getTeamName(TeamManager.player_teams.get(near_Player)))
                    .replace("{player}", near_Player.getName())
                    .replace("{tracking}", i2+"")
            ;

            p.setCompassTarget(near_Player.getLocation());

            ActionBarUtils.sendActionBar(p, s);

        }

    }

}
