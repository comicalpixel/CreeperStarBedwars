package cn.comicalpixel.creeperstarbedwars.Listener;

import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EnderDragon_Block implements Listener {

    @EventHandler
    public void onBlockExplode(EntityExplodeEvent e) {

        if (e.getEntity() instanceof EnderDragon) {
            e.setCancelled(true);
        }

    }

}
