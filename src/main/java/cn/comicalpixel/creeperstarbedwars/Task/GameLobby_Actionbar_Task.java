package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ActionBarUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;

public class GameLobby_Actionbar_Task {
    public GameLobby_Actionbar_Task() {
        task();
    }

    public static void task() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (GameStats.get() == 1) {
                    String lobby_actionbar_meeage = ConfigData.language_game_lobby_actionbar;
                    lobby_actionbar_meeage = MessageVariableUtils.gameCountdown_p_s(lobby_actionbar_meeage);
                    lobby_actionbar_meeage = MessageVariableUtils.gameMapInformation_p_s(lobby_actionbar_meeage);

                    for (Player p : GamePlayers.players) {
                        ActionBarUtils.sendActionBar(p, MessageVariableUtils.gameMapInformation_p_s(lobby_actionbar_meeage));
                    }
                }

            }
        }, 0, 2L); // 参数分别为：延迟执行的时间（tick），重复执行的时间间隔（tick），20 ticks = 1秒
    }

}
