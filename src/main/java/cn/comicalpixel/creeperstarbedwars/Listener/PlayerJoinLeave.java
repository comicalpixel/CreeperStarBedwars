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
import org.bukkit.inventory.meta.ItemMeta;
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

            // 0: §r§9§a§e§f§f
            // 1: §r§9§a§e§f§a
            // 2: §r§9§a§e§f§2
            // 3: §r§9§a§e§f§3
            // 4: §r§9§a§e§f§4
            // 5: §r§9§a§e§f§e
            // 6: §r§9§a§e§f§b
            // 7: §r§9§a§e§f§7
            // 8: §r§9§a§e§f§8

            if (ConfigData.LobbyItems_i0_enabled) {
                ItemStack item = ConfigData.LobbyItems_i0_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§f");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i0_permissions != null && p.hasPermission(ConfigData.LobbyItems_i0_permissions)) {
                    p.getInventory().setItem(0, item);
                } else if (ConfigData.LobbyItems_i0_permissions == null || ConfigData.LobbyItems_i0_permissions.isEmpty()) {
                    p.getInventory().setItem(0, item);
                }
            }
            if (ConfigData.LobbyItems_i1_enabled) {
                ItemStack item = ConfigData.LobbyItems_i1_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§a");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i1_permissions != null && p.hasPermission(ConfigData.LobbyItems_i1_permissions)) {
                    p.getInventory().setItem(1, item);
                } else if (ConfigData.LobbyItems_i1_permissions == null || ConfigData.LobbyItems_i1_permissions.isEmpty()) {
                    p.getInventory().setItem(1, item);
                }
            }
            if (ConfigData.LobbyItems_i2_enabled) {
                ItemStack item = ConfigData.LobbyItems_i2_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§2");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i2_permissions != null && p.hasPermission(ConfigData.LobbyItems_i2_permissions)) {
                    p.getInventory().setItem(2, item);
                } else if (ConfigData.LobbyItems_i2_permissions == null || ConfigData.LobbyItems_i2_permissions.isEmpty()) {
                    p.getInventory().setItem(2, item);
                }
            }
            if (ConfigData.LobbyItems_i3_enabled) {
                ItemStack item = ConfigData.LobbyItems_i3_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§3");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i3_permissions != null && p.hasPermission(ConfigData.LobbyItems_i3_permissions)) {
                    p.getInventory().setItem(3, item);
                } else if (ConfigData.LobbyItems_i3_permissions == null || ConfigData.LobbyItems_i3_permissions.isEmpty()) {
                    p.getInventory().setItem(3, item);
                }
            }
            if (ConfigData.LobbyItems_i4_enabled) {
                ItemStack item = ConfigData.LobbyItems_i4_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§4");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i4_permissions != null && p.hasPermission(ConfigData.LobbyItems_i4_permissions)) {
                    p.getInventory().setItem(4, item);
                } else if (ConfigData.LobbyItems_i4_permissions == null || ConfigData.LobbyItems_i4_permissions.isEmpty()) {
                    p.getInventory().setItem(4, item);
                }
            }
            if (ConfigData.LobbyItems_i5_enabled) {
                ItemStack item = ConfigData.LobbyItems_i5_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§e");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i5_permissions != null && p.hasPermission(ConfigData.LobbyItems_i5_permissions)) {
                    p.getInventory().setItem(5, item);
                } else if (ConfigData.LobbyItems_i5_permissions == null || ConfigData.LobbyItems_i5_permissions.isEmpty()) {
                    p.getInventory().setItem(5, item);
                }
            }
            if (ConfigData.LobbyItems_i6_enabled) {
                ItemStack item = ConfigData.LobbyItems_i6_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§b");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i6_permissions != null && p.hasPermission(ConfigData.LobbyItems_i6_permissions)) {
                    p.getInventory().setItem(6, item);
                } else if (ConfigData.LobbyItems_i6_permissions == null || ConfigData.LobbyItems_i6_permissions.isEmpty()) {
                    p.getInventory().setItem(6, item);
                }
            }
            if (ConfigData.LobbyItems_i7_enabled) {
                ItemStack item = ConfigData.LobbyItems_i7_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§7");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i7_permissions != null && p.hasPermission(ConfigData.LobbyItems_i7_permissions)) {
                    p.getInventory().setItem(7, item);
                } else if (ConfigData.LobbyItems_i7_permissions == null || ConfigData.LobbyItems_i7_permissions.isEmpty()) {
                    p.getInventory().setItem(7, item);
                }
            }
            if (ConfigData.LobbyItems_i8_enabled) {
                ItemStack item = ConfigData.LobbyItems_i8_item.clone();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§r§9§a§e§f§8");
                item.setItemMeta(meta);
                if (ConfigData.LobbyItems_i8_permissions != null && p.hasPermission(ConfigData.LobbyItems_i8_permissions)) {
                    p.getInventory().setItem(8, item);
                } else if (ConfigData.LobbyItems_i8_permissions == null || ConfigData.LobbyItems_i8_permissions.isEmpty()) {
                    p.getInventory().setItem(8, item);
                }
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

        PlayerUtils.leave_game(p);

        if (GameStats.get() == 1) {
            for (Player allgp : GamePlayers.players) {
                allgp.sendMessage(ConfigData.language_leavegame_chat.replace("{player}", p.getName()).replace("{players}", GamePlayers.players.size()+"").replace("{maxplayers}",GameData_cfg.maxPlayers+""));
            }
        }

    }


}
