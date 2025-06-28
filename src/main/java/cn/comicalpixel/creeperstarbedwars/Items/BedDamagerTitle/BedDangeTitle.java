package cn.comicalpixel.creeperstarbedwars.Items.BedDamagerTitle;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerDamage;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BedDangeTitle implements Listener {

    public List<Player> useing_player = new ArrayList<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!ConfigData.beddanger_enabled) return;

        Player p = e.getPlayer();

        if (p.isSneaking() && p.getLocation().getPitch() <= -88.8) {

            if (!GamePlayers.players.contains(e.getPlayer())) return;



            if (useing_player.contains(p)) return;
            useing_player.add(p);



            new BukkitRunnable() {
                int i = 7;
                String message = ConfigData.beddanger_subtitle;
                @Override
                public void run() {
                    message = message.replace("{team_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                            .replace("{player}", p.getName());
                    if (i <= 0) {
                        useing_player.remove(p);
                        cancel();
                        return;
                    }
                    if (i >= 5) {
                        for (Player dyp : GamePlayers.players) {
                            if (TeamManager.player_teams.get(dyp).equalsIgnoreCase(TeamManager.player_teams.get(p))) {
                                NMSTitleUntils.Title.send(dyp, "&r ", message, 1, 5, 1);
                                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.bed-danger-title");
                            }
                        }
                    }
                    i--;
                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,11);

        }

    }

}
