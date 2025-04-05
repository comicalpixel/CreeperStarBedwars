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

}
