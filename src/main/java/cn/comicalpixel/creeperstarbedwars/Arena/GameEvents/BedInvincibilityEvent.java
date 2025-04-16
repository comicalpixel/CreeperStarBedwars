package cn.comicalpixel.creeperstarbedwars.Arena.GameEvents;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerDamage;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BedInvincibilityEvent {

    public static boolean canBreak = true;
    public static int timer = 120;


    public static void start() {

        if (!ConfigData.bed_invincibility_enabled) return;

        timer = ConfigData.bed_invincibility_started_timer;
        canBreak = false;

        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            for (Player p : GamePlayers.players) {
                NMSTitleUntils.Title.send(p, ConfigData.language_bed_invincibility_start_title, ConfigData.language_bed_invincibility_start_subtitle, 10, 40, 10);
                p.sendMessage(ConfigData.language_bed_invincibility_start_chat);
            }
        },130);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (timer <= 0) {

                    canBreak = true;
                    for (Player p : GamePlayers.players) {
                        NMSTitleUntils.Title.send(p, ConfigData.language_bed_invincibility_end_title, ConfigData.language_bed_invincibility_end_subtitle, 10, 40, 10);
                        p.sendMessage(ConfigData.language_bed_invincibility_end_chat);
                    }

                    cancel();
                    return;
                }
                timer--;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,20);

    }

}
