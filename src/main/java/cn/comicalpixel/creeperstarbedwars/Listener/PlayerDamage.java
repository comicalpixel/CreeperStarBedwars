package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {

            Player p = (Player) e.getEntity();

            if (GameStats.get() == 1) {
                e.setCancelled(true);
            } else if (GameStats.get() == 2) {
                if (GamePlayers.players.size() <= 1) {
                    e.setCancelled(true);
                } else {

                    /* 其他代码 */

                    /**/

                }
            } else if (GameStats.get() == 3) {
                e.setCancelled(true);
            }

        }
    }

}
