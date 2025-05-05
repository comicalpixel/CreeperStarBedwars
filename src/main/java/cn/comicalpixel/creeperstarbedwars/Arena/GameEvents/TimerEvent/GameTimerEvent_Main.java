package cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Diamond;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Emerald;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Task.Game_WinCheck_Task;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class GameTimerEvent_Main {

    public static int maxTimer = 3600;
    public static int gameNowTimer = -1;

    public static String event_name = "NONE_EVENT";
    public static String event_timerString = "00:00";
    public static int event_timer = -1;

    public GameTimerEvent_Main() {
        maxTimer = ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "timer-event.game-timer");
        gameNowTimer = maxTimer;
        new BukkitRunnable() {
            @Override
            public void run() {

                if (GameStats.get() != 2) {
                    cancel();
                    return;
                }


                event();
                event_timerString = formatTime(event_timer);

                // 平局检测
                if (gameNowTimer <= 0) {
                    Game_WinCheck_Task.notWinGameEnd();
                    cancel();
                }

                if (event_timer >= 1) {
                    event_timer--;
                }
                if (gameNowTimer >= 1) {
                    gameNowTimer--;
                }

            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,20);
    }

    public void event() {

        List<String> all_events = new ArrayList<>();
        all_events.addAll(ConfigUtils.getStringList(CreeperStarBedwars.getPlugin().getConfig(), "timer-event.events-list"));

        for (String s : all_events) {
            if (isNow(s)) {
                event_timer = ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "timer-event.events."+s+".countdown-timer");
                event_name = ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "timer-event.events."+s+".event-name");
                action_utils(s);
                // Bukkit.getLogger().info("timer-event.events."+s+".*");
            }
        }

    }

    public static boolean isNow(String event_name) {
        if (gameNowTimer == ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "timer-event.events."+event_name+".game-timer")) {
            return true;
        }
        return false;
    }


    public static void action_utils(String event_name) {
        List<String> all_actions = new ArrayList<>();
        all_actions.addAll(ConfigUtils.getStringList(CreeperStarBedwars.getPlugin().getConfig(), "timer-event.events."+event_name+".actions"));
        if (all_actions.isEmpty()) {
            return;
        }
        for (String s : all_actions) {
            if (s != null && !s.isEmpty()) {
                if (s.startsWith("[command]")) {
                    s = s.replace("[command]", "");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
                } else if (s.startsWith("[title]")) {
                    s = s.replace("[title]", "");
                    String[] args = s.split(";_;");
                    if (args.length == 5) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            NMSTitleUntils.Title.send(p, args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                        }
                    } else if (args.length == 2) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            NMSTitleUntils.Title.send(p, args[0], args[1], 20, 40, 20);
                        }
                    }
                } else if (s.startsWith("[sound]")) {
                    s = s.replace("[sound]", "");
                    String[] args = s.split(",");
                    if (args.length == 3) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            ConfigUtils.playSound(p, s);
                        }
                    }
                } else if (s.startsWith("[event]")) {
                    s = s.replace("[event]", "");
                    if (s.startsWith("bedbreak")) {
                        new TimerEvent_BedBreak();
                    } else if (s.startsWith("deathmode")) {
                        new TimerEvent_DeathMode();
                    }
                } else if (s.startsWith("[editgens]")) {
                    s = s.replace("[editgens]", "");
                    String[] args = s.split(":");
                    if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("diamond")) {
                            Generators_Diamond.set(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                        } else if (args[0].equalsIgnoreCase("emerald")) {
                            Generators_Emerald.set(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                        }
                    }
                } else if (s.startsWith("[chat]")) {
                    s = s.replace("[chat]", "");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(s);
                    }
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
                }
            }
        }
    }

    public static String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

}
