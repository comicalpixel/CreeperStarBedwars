package cn.comicalpixel.creeperstarbedwars.Arena.Teams;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeamChest implements Listener {

    @EventHandler
    public void onPlayerClickBlock(PlayerInteractEvent e) {

        if (GameStats.get() != 2) return;

        if (e.isCancelled()) return;

        if (e.getClickedBlock() == null) return;

        if (e.getClickedBlock().getType() != Material.CHEST) return;

        if (!TeamManager.player_teams.containsKey(e.getPlayer())) return;

        Location loc = e.getClickedBlock().getLocation();

        if (TeamManager.teams.contains("RED") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("RED") && TeamManager.getTeamPlayerSize("RED") > 0) {
            if (loc.distance(GameData_cfg.team_red_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }
        if (TeamManager.teams.contains("BLUE") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("BLUE") && TeamManager.getTeamPlayerSize("BLUE") > 0) {
            if (loc.distance(GameData_cfg.team_blue_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }
        if (TeamManager.teams.contains("GREEN") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("GREEN") && TeamManager.getTeamPlayerSize("GREEN") > 0) {
            if (loc.distance(GameData_cfg.team_green_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }
        if (TeamManager.teams.contains("YELLOW") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("YELLOW") && TeamManager.getTeamPlayerSize("YELLOW") > 0) {
            if (loc.distance(GameData_cfg.team_yellow_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }
        if (TeamManager.teams.contains("PINK") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("PINK") && TeamManager.getTeamPlayerSize("PINK") > 0) {
            if (loc.distance(GameData_cfg.team_pink_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }
        if (TeamManager.teams.contains("AQUA") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("AQUA") && TeamManager.getTeamPlayerSize("AQUA") > 0) {
            if (loc.distance(GameData_cfg.team_aqua_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }
        if (TeamManager.teams.contains("GRAY") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("GRAY") && TeamManager.getTeamPlayerSize("GRAY") > 0) {
            if (loc.distance(GameData_cfg.team_gray_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }
        if (TeamManager.teams.contains("WHITE") && !TeamManager.player_teams.get(e.getPlayer()).equalsIgnoreCase("WHITE") && TeamManager.getTeamPlayerSize("WHITE") > 0) {
            if (loc.distance(GameData_cfg.team_white_spawn) < GameData_cfg.teamChest_radius) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ConfigData.language_teamchest_notismyteam);
            }
        }

    }

}
