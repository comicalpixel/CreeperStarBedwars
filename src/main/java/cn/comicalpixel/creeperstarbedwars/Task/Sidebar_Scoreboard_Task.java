package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import org.bukkit.entity.Player;

public class Sidebar_Scoreboard_Task {

    public Sidebar_Scoreboard_Task() {
        task_main();
    }

    public void task_main() {

        if (GameStats.get() == 1) {
            for (Player p : GamePlayers.players) {
                
            }
        }

    }

}
