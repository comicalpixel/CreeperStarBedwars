package cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

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
                EnderDragon enderDragon = center_loc.getWorld().spawn(center_loc, EnderDragon.class);
                enderDragon.setCustomName(TeamManager.getTeamChatColor(team) + team);
                enderDragon.setCustomNameVisible(true);
            }
        }

    }

}
