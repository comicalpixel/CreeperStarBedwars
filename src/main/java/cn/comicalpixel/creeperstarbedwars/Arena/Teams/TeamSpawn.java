package cn.comicalpixel.creeperstarbedwars.Arena.Teams;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeamSpawn {

    public static void send(Player p, String team) {

        if (!GamePlayers.players.contains(p)) {
            return;
        }

        if (!TeamManager.teams.contains(team)) {
            return;
        }

        switch (team) {
            case "RED":
                p.teleport(GameData_cfg.team_red_spawn);
                break;
            case "BLUE":
                p.teleport(GameData_cfg.team_blue_spawn);
                break;
            case "YELLOW":
                p.teleport(GameData_cfg.team_yellow_spawn);
                break;
            case "GREEN":
                p.teleport(GameData_cfg.team_green_spawn);
                break;
            case "PINK":
                p.teleport(GameData_cfg.team_pink_spawn);
                break;
            case "AQUA":
                p.teleport(GameData_cfg.team_aqua_spawn);
                break;
            case "GRAY":
                p.teleport(GameData_cfg.team_gray_spawn);
                break;
            case "WHITE":
                p.teleport(GameData_cfg.team_white_spawn);
                break;
        }

    }

    public static Location getLocation(Player p, String team) {

        if (!GamePlayers.players.contains(p)) {
            return null;
        }

        if (!TeamManager.teams.contains(team)) {
            return null;
        }

        switch (team) {
            case "RED":
                return GameData_cfg.team_red_spawn;
            case "BLUE":
                return GameData_cfg.team_blue_spawn;
            case "YELLOW":
                return GameData_cfg.team_yellow_spawn;
            case "GREEN":
                return GameData_cfg.team_green_spawn;
            case "PINK":
                return GameData_cfg.team_pink_spawn;
            case "AQUA":
                return GameData_cfg.team_aqua_spawn;
            case "GRAY":
                return GameData_cfg.team_gray_spawn;
            case "WHITE":
                return GameData_cfg.team_white_spawn;
        }
        return new Location(Bukkit.getWorld("null"), -1, -1, -1);
    }

}
