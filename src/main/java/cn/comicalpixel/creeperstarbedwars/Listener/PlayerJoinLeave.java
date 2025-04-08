package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class PlayerJoinLeave implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e) {

        if (GameStats.get() == 0) {
            return;
        }
        if (GameStats.get() == -1) {
            e.setJoinMessage(null);
            e.getPlayer().kickPlayer("§cThe room is loading, please re-join later!");
            return;
        }

        Player p = e.getPlayer();

        if (GameStats.get() == 1) {
            GamePlayers.players.add(p);

            p.setGameMode(GameMode.SPECTATOR);

            p.teleport(GameData_cfg.lobby_loc);
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.teleport(GameData_cfg.lobby_loc);
                }
            }.runTaskLater(CreeperStarBedwars.getPlugin(), 3);

            p.setGameMode(GameMode.ADVENTURE);

            p.setFlying(false);
            p.setAllowFlight(false);

            PlayerUtils.reset(p);
            PlayerUtils.clear_effects(p);

            for (Player allgp : GamePlayers.players) {
                allgp.sendMessage(ConfigData.language_joingame_chat.replace("{player}", p.getName()).replace("{players}", GamePlayers.players.size()+"").replace("{maxplayers}",GameData_cfg.maxPlayers+""));
                if (!Objects.equals(allgp.getName(), p.getName())) {
                    ConfigUtils.playSound(allgp, CreeperStarBedwars.getInstance().getConfig(), "sound.join-players");
                }
            }
            ConfigUtils.playSound(p, CreeperStarBedwars.getInstance().getConfig(), "sound.join-me");


            // 背包物品
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            p.getInventory().clear();

            if (ConfigData.LobbyItems_i0_enabled) {

            }
            /* ------ */


        } else if (GameStats.get() == 2) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
        } else if (GameStats.get() == 3) {
            e.getPlayer().kickPlayer(ChatColor.RED + "The game is over, and you can't join the spectators!");
        }

        e.setJoinMessage(null);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        e.setQuitMessage(null);

        if (GamePlayers.players.contains(p)) {
            GamePlayers.players.remove(p);
        }
        if (GamePlayers.specs.contains(p)) {
            GamePlayers.specs.remove(p);
        }

        if (GameStats.get() == 1) {
            for (Player allgp : GamePlayers.players) {
                allgp.sendMessage(ConfigData.language_leavegame_chat.replace("{player}", p.getName()).replace("{players}", GamePlayers.players.size()+"").replace("{maxplayers}",GameData_cfg.maxPlayers+""));
            }
        }

    }

}
