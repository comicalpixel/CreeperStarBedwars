package cn.comicalpixel.creeperstarbedwars.Utils;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
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


    public static void clear_bed_GameStart() {

        if (TeamManager.teams.contains("RED") && TeamManager.getTeamPlayerSize("RED") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_red_bed_f, GameData_cfg.team_red_bed_b);
        }
        if (TeamManager.teams.contains("BLUE") && TeamManager.getTeamPlayerSize("BLUE") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_blue_bed_f, GameData_cfg.team_blue_bed_b);
        }
        if (TeamManager.teams.contains("GREEN") && TeamManager.getTeamPlayerSize("GREEN") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_green_bed_f, GameData_cfg.team_green_bed_b);
        }
        if (TeamManager.teams.contains("YELLOW") && TeamManager.getTeamPlayerSize("YELLOW") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_yellow_bed_f, GameData_cfg.team_yellow_bed_b);
        }
        if (TeamManager.teams.contains("PINK") && TeamManager.getTeamPlayerSize("PINK") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_pink_bed_f, GameData_cfg.team_pink_bed_b);
        }
        if (TeamManager.teams.contains("AQUA") && TeamManager.getTeamPlayerSize("AQUA") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_aqua_bed_f, GameData_cfg.team_aqua_bed_b);
        }
        if (TeamManager.teams.contains("GRAY") && TeamManager.getTeamPlayerSize("GRAY") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_gray_bed_f, GameData_cfg.team_gray_bed_b);
        }
        if (TeamManager.teams.contains("WHITE") && TeamManager.getTeamPlayerSize("WHITE") <= 0) {
            BedBlockUtils.clear(GameData_cfg.team_white_bed_f, GameData_cfg.team_white_bed_b);
        }

    }

    public static void clear_bed_BreakBedEvent() {

        if (TeamManager.teams.contains("RED")) {
            BedBlockUtils.clear(GameData_cfg.team_red_bed_f, GameData_cfg.team_red_bed_b);
        }
        if (TeamManager.teams.contains("BLUE")) {
            BedBlockUtils.clear(GameData_cfg.team_blue_bed_f, GameData_cfg.team_blue_bed_b);
        }
        if (TeamManager.teams.contains("GREEN")) {
            BedBlockUtils.clear(GameData_cfg.team_green_bed_f, GameData_cfg.team_green_bed_b);
        }
        if (TeamManager.teams.contains("YELLOW")) {
            BedBlockUtils.clear(GameData_cfg.team_yellow_bed_f, GameData_cfg.team_yellow_bed_b);
        }
        if (TeamManager.teams.contains("PINK")) {
            BedBlockUtils.clear(GameData_cfg.team_pink_bed_f, GameData_cfg.team_pink_bed_b);
        }
        if (TeamManager.teams.contains("AQUA")) {
            BedBlockUtils.clear(GameData_cfg.team_aqua_bed_f, GameData_cfg.team_aqua_bed_b);
        }
        if (TeamManager.teams.contains("GRAY")) {
            BedBlockUtils.clear(GameData_cfg.team_gray_bed_f, GameData_cfg.team_gray_bed_b);
        }
        if (TeamManager.teams.contains("WHITE")) {
            BedBlockUtils.clear(GameData_cfg.team_white_bed_f, GameData_cfg.team_white_bed_b);
        }

    }

}
