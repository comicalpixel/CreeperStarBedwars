package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldTimer_Task {
    public WorldTimer_Task() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {

                    world.setTime(world.getTime() - 10);
                    world.setStorm(false);
                    world.setWeatherDuration(Integer.MAX_VALUE);

                }
            }
        }, 0, 10L);

    }
}
