package cn.comicalpixel.creeperstarbedwars.Arena.SPEC;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpecListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (GamePlayers.specs.contains(e.getPlayer())) {return;}
        e.setCancelled(true);
    }


    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (GamePlayers.specs.contains(((Player) e.getEntity()))) {return;}
            e.setCancelled(true);
        }
    }



}
