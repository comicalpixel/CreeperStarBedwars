package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayerFoodLevel implements Listener {

    private static Map<Player, Integer> pf = new HashMap<Player, Integer>();
    private int i = 0;

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {

        if (GameStats.get() == 1 || GameStats.get() == 3) {
            e.setCancelled(true);
            return;
        }

        Player p = (Player) e.getEntity();

        if (!pf.containsKey(p)) {
            pf.put(p, 0);
        }

        // 饱食度限速
        if (GameStats.get() == 2 && !ConfigData.lock_foodlevel_enabled) {
            if (pf.get(p) == 2) {
                pf.put(p, 0);
            } else {
                e.setCancelled(true);
                pf.put(p, pf.get(p) + 1);
            }
        } else {
            e.setCancelled(true);
        }

    }

}
