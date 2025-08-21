package cn.comicalpixel.creeperstarbedwars.Listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftItemListener implements Listener {

    @EventHandler
    public void onItemCraft(CraftItemEvent e) {

        if (e.isCancelled()) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if (p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }

    }

}
