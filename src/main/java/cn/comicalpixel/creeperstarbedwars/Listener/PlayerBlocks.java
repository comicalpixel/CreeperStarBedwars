package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Team.Holo.TeamGenerators_holographic;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerBlocks implements Listener {

    public static List<Block> player_blocks = new ArrayList<>();
    public static List<Block> safeMap_blocks = new ArrayList<>();

    public static void readSafeRegion() {

        // 游戏地图资源点
        for (Location loc : ConfigUtils.getBlockStrLocationList(GameData_cfg.gameGenerator_diamond_locs)) {

            // bz里面填写半径
            int bz = CreeperStarBedwars.getPlugin().getConfig().getInt("safe-region.map-generators");
            if (loc != null) {
                // 获取中心点坐标
                int centerX = loc.getBlockX();
                int centerY = loc.getBlockY();
                int centerZ = loc.getBlockZ();

                for (int x = centerX - bz; x <= centerX + bz; x++) {
                    for (int y = centerY - bz; y <= centerY + bz; y++) {
                        for (int z = centerZ - bz; z <= centerZ + bz; z++) {
                            // 半径
                            double distance = Math.sqrt(
                                    Math.pow(x - centerX, 2) +
                                            Math.pow(y - centerY, 2) +
                                            Math.pow(z - centerZ, 2)
                            );

                            if (distance <= bz) {
                                Location blockLoc = new Location(loc.getWorld(), x, y, z);
                                safeMap_blocks.add(blockLoc.getBlock());
                            }
                        }
                    }
                }
            }
        }
        for (Location loc : ConfigUtils.getBlockStrLocationList(GameData_cfg.gameGenerator_emerald_locs)) {

            // bz里面填写半径
            int bz = CreeperStarBedwars.getPlugin().getConfig().getInt("safe-region.map-generators");
            if (loc != null) {
                // 获取中心点坐标
                int centerX = loc.getBlockX();
                int centerY = loc.getBlockY();
                int centerZ = loc.getBlockZ();

                for (int x = centerX - bz; x <= centerX + bz; x++) {
                    for (int y = centerY - bz; y <= centerY + bz; y++) {
                        for (int z = centerZ - bz; z <= centerZ + bz; z++) {
                            // 半径
                            double distance = Math.sqrt(
                                    Math.pow(x - centerX, 2) +
                                            Math.pow(y - centerY, 2) +
                                            Math.pow(z - centerZ, 2)
                            );

                            if (distance <= bz) {
                                Location blockLoc = new Location(loc.getWorld(), x, y, z);
                                safeMap_blocks.add(blockLoc.getBlock());
                            }
                        }
                    }
                }
            }
        }



        // 队伍资源点
        List<Location> TeamGen_Locations = new ArrayList<>();

        if (TeamManager.teams.contains("RED")) {
            TeamGen_Locations.add(GameData_cfg.team_red_generator.clone());
        }
        if (TeamManager.teams.contains("BLUE")) {
            TeamGen_Locations.add(GameData_cfg.team_blue_generator.clone());
        }
        if (TeamManager.teams.contains("GREEN")) {
            TeamGen_Locations.add(GameData_cfg.team_green_generator.clone());
        }
        if (TeamManager.teams.contains("YELLOW")) {
            TeamGen_Locations.add(GameData_cfg.team_yellow_generator.clone());
        }
        if (TeamManager.teams.contains("PINK")) {
            TeamGen_Locations.add(GameData_cfg.team_pink_generator.clone());
        }
        if (TeamManager.teams.contains("AQUA")) {
            TeamGen_Locations.add(GameData_cfg.team_aqua_generator.clone());
        }
        if (TeamManager.teams.contains("GRAY")) {
            TeamGen_Locations.add(GameData_cfg.team_gray_generator.clone());
        }
        if (TeamManager.teams.contains("WHITE")) {
            TeamGen_Locations.add(GameData_cfg.team_white_generator.clone());
        }
        for (Location loc : TeamGen_Locations) {

            // bz里面填写半径
            int bz = CreeperStarBedwars.getPlugin().getConfig().getInt("safe-region.team-generators");
            if (loc != null) {
                // 获取中心点坐标
                int centerX = loc.getBlockX();
                int centerY = loc.getBlockY();
                int centerZ = loc.getBlockZ();

                for (int x = centerX - bz; x <= centerX + bz; x++) {
                    for (int y = centerY - bz; y <= centerY + bz; y++) {
                        for (int z = centerZ - bz; z <= centerZ + bz; z++) {
                            // 半径
                            double distance = Math.sqrt(
                                    Math.pow(x - centerX, 2) +
                                            Math.pow(y - centerY, 2) +
                                            Math.pow(z - centerZ, 2)
                            );

                            if (distance <= bz) {
                                Location blockLoc = new Location(loc.getWorld(), x, y, z);
                                safeMap_blocks.add(blockLoc.getBlock());
                            }
                        }
                    }
                }
            }
        }



        // 队伍出生点点
        List<Location> TeamSpawn_Locations = new ArrayList<>();

        if (TeamManager.teams.contains("RED")) {
            TeamSpawn_Locations.add(GameData_cfg.team_red_spawn.clone());
        }
        if (TeamManager.teams.contains("BLUE")) {
            TeamSpawn_Locations.add(GameData_cfg.team_blue_spawn.clone());
        }
        if (TeamManager.teams.contains("GREEN")) {
            TeamSpawn_Locations.add(GameData_cfg.team_green_spawn.clone());
        }
        if (TeamManager.teams.contains("YELLOW")) {
            TeamSpawn_Locations.add(GameData_cfg.team_yellow_spawn.clone());
        }
        if (TeamManager.teams.contains("PINK")) {
            TeamSpawn_Locations.add(GameData_cfg.team_pink_spawn.clone());
        }
        if (TeamManager.teams.contains("AQUA")) {
            TeamSpawn_Locations.add(GameData_cfg.team_aqua_spawn.clone());
        }
        if (TeamManager.teams.contains("GRAY")) {
            TeamSpawn_Locations.add(GameData_cfg.team_gray_spawn.clone());
        }
        if (TeamManager.teams.contains("WHITE")) {
            TeamSpawn_Locations.add(GameData_cfg.team_white_spawn.clone());
        }
        for (Location loc : TeamSpawn_Locations) {

            // bz里面填写半径
            int bz = CreeperStarBedwars.getPlugin().getConfig().getInt("safe-region.team-spawn");
            if (loc != null) {
                // 获取中心点坐标
                int centerX = loc.getBlockX();
                int centerY = loc.getBlockY();
                int centerZ = loc.getBlockZ();

                for (int x = centerX - bz; x <= centerX + bz; x++) {
                    for (int y = centerY - bz; y <= centerY + bz; y++) {
                        for (int z = centerZ - bz; z <= centerZ + bz; z++) {
                            // 半径
                            double distance = Math.sqrt(
                                    Math.pow(x - centerX, 2) +
                                            Math.pow(y - centerY, 2) +
                                            Math.pow(z - centerZ, 2)
                            );

                            if (distance <= bz) {
                                Location blockLoc = new Location(loc.getWorld(), x, y, z);
                                safeMap_blocks.add(blockLoc.getBlock());
                            }
                        }
                    }
                }
            }
        }


    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();

        if (e.isCancelled()) return;

        if (GameStats.get() == 1) {
            e.setCancelled(true);
        } else if (GameStats.get() == 2 || GameStats.get() == 3 ) {
            if (GamePlayers.players.contains(p) || e.getBlock().getType() == Material.BED_BLOCK) {
                if (!safeMap_blocks.contains(e.getBlock())) {
                    player_blocks.add(e.getBlock());
                } else {
                    p.sendMessage(ConfigData.language_block_saferegion);
                    e.setCancelled(true);
                }
            } else {
                e.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();

        if (e.isCancelled()) return;

        if (GameStats.get() == 1) {
            e.setCancelled(true);
        } else if ((GameStats.get() == 2 || GameStats.get() == 3) && e.getBlock().getType() != Material.BED_BLOCK) {
            if (GamePlayers.players.contains(p)) {
                if (player_blocks.contains(e.getBlock())) {
                    player_blocks.remove(e.getBlock());
                } else {
                    p.sendMessage(ConfigData.language_block_map_break);
                    e.setCancelled(true);
                }
            } else {
                e.setCancelled(true);
            }
        }

    }

}
