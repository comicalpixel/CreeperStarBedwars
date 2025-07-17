package cn.comicalpixel.creeperstarbedwars.Fix;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnderDragonTargetFix implements Listener {

    @EventHandler
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {

        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        if (!(e.getDamager() instanceof EnderDragon)) {
            return;
        }

        if (e.isCancelled()) {
            return;
        }

        Player p = (Player) e.getEntity();

        if (!GamePlayers.players.contains(p)) {
            e.setCancelled(true);
            return;
        }

        String team = TeamManager.player_teams.get(p);

        if (e.getDamager().getCustomName().equalsIgnoreCase(TeamManager.getTeamChatColor(team) + team)) {
            e.setCancelled(true);
        }

    }

}
