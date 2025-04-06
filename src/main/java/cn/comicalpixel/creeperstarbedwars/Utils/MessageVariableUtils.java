package cn.comicalpixel.creeperstarbedwars.Utils;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Countdown_Task;

public class MessageVariableUtils {

    public static String gameCountdown_p_s(String s) {
        return s.replace("{countdown}", Game_Countdown_Task.countdown + "");
    }

    public static String gameMapInformation_p_s(String s) {
        return s.replace("{map}", GameData_cfg.map_name)
                .replace("{author}", GameData_cfg.map_author)
                .replace("{players}", GamePlayers.players.size()+"")
                .replace("{maxplayers}",GameData_cfg.maxPlayers+"")
                ;
    }

    public static String teamNameColor_p_s(String s, String team) {
        String color = null;
        String name = null;
        switch (team) {
            case "RED":
                color = GameData_cfg.team_red_chatcolor;
                name = GameData_cfg.team_red_name;
                break;
            case "BLUE":
                color = GameData_cfg.team_blue_chatcolor;
                name = GameData_cfg.team_blue_name;
                break;
            case "GREEN":
                color = GameData_cfg.team_green_chatcolor;
                name = GameData_cfg.team_green_name;
                break;
            case "YELLOW":
                color = GameData_cfg.team_yellow_chatcolor;
                name = GameData_cfg.team_yellow_name;
                break;
            case "PINK":
                color = GameData_cfg.team_pink_chatcolor;
                name = GameData_cfg.team_pink_name;
                break;
            case "AQUA":
                color = GameData_cfg.team_aqua_chatcolor;
                name = GameData_cfg.team_aqua_name;
                break;
            case "GRAY":
                color = GameData_cfg.team_gray_chatcolor;
                name = GameData_cfg.team_gray_name;
                break;
            case "WHITE":
                color = GameData_cfg.team_white_chatcolor;
                name = GameData_cfg.team_white_name;
                break;
        }
        return s.replace("{team_color}", color).replace("{team_name}", name);
    }

}
