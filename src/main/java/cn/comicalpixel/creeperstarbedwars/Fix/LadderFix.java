package cn.comicalpixel.creeperstarbedwars.Fix;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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

//            Location p_loc = p.getLocation();
//            Vector direction = p.getEyeLocation().getDirection();
//
//            Location block = e.getBlock().getLocation().clone().add(direction);
//            if (block.distance(p_loc) < 1.6) {
//                e.setCancelled(true);
//            }


            Location p_loc = p.getLocation();
            Block block = e.getBlock();

            byte b = block.getData();

            //p.sendMessage("data: " + b);

            Location loc = e.getBlock().getLocation();
            loc = loc.add(0.5, 0, 0.5);

            if (b == 2) {
                loc = loc.add(0,0,1);
                //p.sendMessage("distance: " + p_loc.distance(loc));
                if (p_loc.distance(loc) < 0.9) {
                    e.setCancelled(true);
                }
            }
            if (b == 3) {
                loc = loc.add(0,0,-1);
                //p.sendMessage("distance: " + p_loc.distance(loc));
                if (p_loc.distance(loc) < 0.9) {
                    e.setCancelled(true);
                }
            }
            if (b == 4) {
                loc = loc.add(1,0,0);
                //p.sendMessage("distance: " + p_loc.distance(loc));
                if (p_loc.distance(loc) < 0.9) {
                    e.setCancelled(true);
                }
            }
            if (b == 5) {
                loc = loc.add(-1,0,0);;
                //p.sendMessage("distance: " + p_loc.distance(loc));
                if (p_loc.distance(loc) < 0.9) {
                    e.setCancelled(true);
                }
            }

            //p.sendMessage("loc: " + loc);

        }
    }

}
