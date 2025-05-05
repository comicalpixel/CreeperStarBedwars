package cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent;

import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Utils.BedBlockUtils;

public class TimerEvent_BedBreak {

    public TimerEvent_BedBreak() {

        for (String team : TeamManager.teams) {
            TeamManager.setBed(team, true);
            BedBlockUtils.clear_bed_BreakBedEvent();
        }

    }

}
