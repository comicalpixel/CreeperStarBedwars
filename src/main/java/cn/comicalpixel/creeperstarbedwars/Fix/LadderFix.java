package cn.comicalpixel.creeperstarbedwars.Fix;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

public class LadderFix implements Listener {

    @EventHandler
    public void PlaceLadder(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.LADDER) {
            Player p = e.getPlayer();
            Location p_loc = p.getLocation();
//            Location p_loc_1 = e.getBlock().getLocation().clone().add(0, 1, 0);
//            Location block_loc = e.getBlock().getLocation().clone().add(0, 0, 0);
//            p.sendMessage(" \np_loc: " + block_loc.distance(p_loc) + "\n" + "p_loc_1: " + p_loc.distance(p_loc_1) + "\ndata: " + e.getBlock().getData());
//
//            byte b = e.getBlock().getData();
//            Vector direction = p.getEyeLocation().getDirection();
//
//            if (b == 2) {
//
//            }

            Vector direction = p.getEyeLocation().getDirection();

            Location block = e.getBlock().getLocation().clone().add(direction);
            if (block.distance(p_loc) < 1.6) {
                e.setCancelled(true);
            }


        }
    }

}
