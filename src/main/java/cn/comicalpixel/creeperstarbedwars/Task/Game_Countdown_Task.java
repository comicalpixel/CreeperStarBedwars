package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.GameStart;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Game_Countdown_Task {

    public Game_Countdown_Task() {
        timer_task();
    }

    public static int countdown = ConfigData.countdown_settings_seconds;
    public static boolean countdown_running = false;

    public static void timer_task() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (GameStats.get() != 1) {
                    return;
                }
                if (countdown <= 0) {
                    countdown_running = false;
                    cancel();
                    /*游戏开始的代码*/
                    GameStart.start();
                    /**/
                    return;
                }
                if (ConfigData.xpbar_countdown) {
                    for (Player p : GamePlayers.players) {
                        p.setLevel(countdown);
                        p.setExp(1.0F - ((1.0F/ConfigData.countdown_settings_seconds) * (ConfigData.countdown_settings_seconds - countdown)));
                    }
                }
                if (GamePlayers.players.size() < GameData_cfg.minPlayers) {
                    if (countdown_running) {
                        for (Player p : GamePlayers.players) {
                            NMSTitleUntils.Title.send(p, MessageVariableUtils.gameCountdown_p_s(ConfigData.language_game_countdown_InsufficientPlayers_title), MessageVariableUtils.gameCountdown_p_s(ConfigData.language_game_countdown_InsufficientPlayers_subtitle), 7, 40, 7);
                            p.sendMessage(MessageVariableUtils.gameCountdown_p_s(ConfigData.language_game_countdown_InsufficientPlayers_chat));
                        }
                    }
                    countdown_running = false;
                    countdown = ConfigData.countdown_settings_seconds;
                    return;
                }

                if (ConfigData.countdown_settings_trigger_seconds.contains(countdown)) {
                    if (CreeperStarBedwars.Instance.getConfig().get("countdown-settings.seconds-message."+countdown) != null) {
                        for (Player p : GamePlayers.players) {
                            NMSTitleUntils.Title.send(p, MessageVariableUtils.gameCountdown_p_s(ConfigData.countdown_seconds_message_title.get(countdown)), MessageVariableUtils.gameCountdown_p_s(ConfigData.countdown_seconds_message_subtitle.get(countdown)), 7, 40, 7);
                        }
                    } else {
                        for (Player p : GamePlayers.players) {
                            NMSTitleUntils.Title.send(p, MessageVariableUtils.gameCountdown_p_s(ConfigData.language_game_countdown_other_title), MessageVariableUtils.gameCountdown_p_s(ConfigData.language_game_countdown_other_subtitle), 7, 40, 7);
                        }
                    }
                    for (Player p : GamePlayers.players) {
                        p.sendMessage(MessageVariableUtils.gameCountdown_p_s(ConfigData.language_game_countdown_chat));
                        ConfigUtils.playSound(p, CreeperStarBedwars.getInstance().getConfig(), "sound.game-countdown");
                    }
                }

                countdown_running = true;
                countdown--;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,20);
    }

}
