package cn.comicalpixel.creeperstarbedwars.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;

public class BedBlockUtils {

// 使用方法
//    public static void refresh_bed(String team) {
//
//        Location hl = ComicalPixel_NewBedwars.getInstance().getGameConfig().getBlockLocation("team." + team + ".bed.foot");
//        Location fl = ComicalPixel_NewBedwars.getInstance().getGameConfig().getBlockLocation("team." + team + ".bed.head");
//
//        send(hl, fl);
//
//    }

    public static void send(Location loc, Location locf) {
        BlockFace face = locf.getBlock().getFace(loc.getBlock());

        if (face == BlockFace.NORTH) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.SOUTH).getState();
            bedFoot.setType(Material.BED_BLOCK);
            bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 0);
            bedHead.setRawData((byte) 8);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        } else if (face == BlockFace.EAST) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.WEST).getState();
            bedFoot.setType(Material.BED_BLOCK);
            bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 1);
            bedHead.setRawData((byte) 9);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        } else if (face == BlockFace.SOUTH) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.NORTH).getState();
            bedFoot.setType(Material.BED_BLOCK);
            bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 2);
            bedHead.setRawData((byte) 10);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        } else if (face == BlockFace.WEST) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.EAST).getState();
            bedFoot.setType(Material.BED_BLOCK);
            bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 3);
            bedHead.setRawData((byte) 11);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        }
    }

    public static void clear(Location loc, Location locf) {
        BlockFace face = locf.getBlock().getFace(loc.getBlock());
        if (face == BlockFace.NORTH) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.SOUTH).getState();
            bedFoot.setType(Material.AIR);
            bedHead.setType(Material.AIR);
            bedFoot.setRawData((byte) 0);
            bedHead.setRawData((byte) 8);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        } else if (face == BlockFace.EAST) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.WEST).getState();
            bedFoot.setType(Material.AIR);
            bedHead.setType(Material.AIR);
            bedFoot.setRawData((byte) 1);
            bedHead.setRawData((byte) 9);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        } else if (face == BlockFace.SOUTH) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.NORTH).getState();
            bedFoot.setType(Material.AIR);
            bedHead.setType(Material.AIR);
            bedFoot.setRawData((byte) 2);
            bedHead.setRawData((byte) 10);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        } else if (face == BlockFace.WEST) {
            Location l = loc.getBlock().getLocation();
            l.getBlock().setType(Material.AIR);
            //l.getBlock().setType(Material.BED_BLOCK);
            Block block = loc.getBlock();
            BlockState bedFoot = block.getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.EAST).getState();
            bedFoot.setType(Material.AIR);
            bedHead.setType(Material.AIR);
            bedFoot.setRawData((byte) 3);
            bedHead.setRawData((byte) 11);
            bedFoot.update(true, false);
            bedHead.update(true, true);
        }
    }


}
