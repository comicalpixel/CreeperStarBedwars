package cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Shop.Updrade.TeamShop_GUI;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

import java.util.Random;

public class TimerEvent_DeathMode {

    public boolean spawned = false;

    public TimerEvent_DeathMode() {

        if (spawned) {
            return;
        }
        spawned = true;

        Location center_loc = GameData_cfg.mapCenter_loc.clone().add(0, -5, 0);

        for (String team : TeamManager.teams) {
            if (TeamManager.getTeamPlayerSize(team) >= 1) {
                EnderDragon enderDragon = center_loc.getWorld().spawn(center_loc.clone().add(0, new Random().nextInt(10), 0), EnderDragon.class);
                enderDragon.setCustomName(TeamManager.getTeamChatColor(team) + team);
                enderDragon.setCustomNameVisible(true);
                // 龙增益
                if (TeamShop_GUI.team_EnderDragon.get(team)) {
                    EnderDragon enderDragon2 = center_loc.getWorld().spawn(center_loc.clone().add(0, new Random().nextInt(10), 0), EnderDragon.class);
                    enderDragon2.setCustomName(TeamManager.getTeamChatColor(team) + team);
                    enderDragon2.setCustomNameVisible(true);
                }
            }
        }

    }

}
