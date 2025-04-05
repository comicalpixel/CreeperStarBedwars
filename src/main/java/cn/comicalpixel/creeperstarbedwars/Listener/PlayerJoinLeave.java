package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        e.setJoinMessage(null);

        switch (GameStats.get()) {
            case 1:
                GamePlayers.players.add(p);

                p.teleport(GameData_cfg.lobby_loc);
                Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                    p.teleport(GameData_cfg.lobby_loc);
                },2);

                PlayerUtils.reset(p);
                PlayerUtils.clear_effects(p);



                break;
            case 2:
                GamePlayers.specs.add(p);
                break;
            case 3:
                e.getPlayer().kickPlayer(ChatColor.RED + "The game is over, and you can't join the spectators!");
                break;
        }


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        e.setQuitMessage(null);

        if (GamePlayers.players.contains(p)) {
            GamePlayers.players.remove(p);
        }

    }

}
