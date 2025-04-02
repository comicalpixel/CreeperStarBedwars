package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

public class Game_Countdown_Task {

    public Game_Countdown_Task() {
        timer_task();
    }

    public static int countdown = -1;
    public static boolean countdown_running = false;

    public static void timer_task() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (GameStats.get() == 0) {
                    cancel();
                    return;
                }
                if (countdown <= 0) {
                    countdown_running = true;
                    cancel();
                    return;
                }

                countdown--;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,20);
    }

}
