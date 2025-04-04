package cn.comicalpixel.creeperstarbedwars.Utils;

import cn.comicalpixel.creeperstarbedwars.Task.Game_Countdown_Task;

public class MessageVariableUtils {

    public static String gameCountdown_p_s(String s) {
        return s.replace("{countdown}", Game_Countdown_Task.countdown + "");
    }

}
