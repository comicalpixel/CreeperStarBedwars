package cn.comicalpixel.creeperstarbedwars.Arena;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import org.bukkit.entity.Player;

public class GameStart {

    public static void start() {
        GameStats.set(2);
        for (Player p : GamePlayers.players) {
            TeamManager.random_join(p);
        }
    }

}
