package cn.comicalpixel.creeperstarbedwars.Arena.Teams.GUI;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TeamSel_GUI implements Listener {

    public static void open(Player p) {

        if (!CreeperStarBedwars.getPlugin().getConfig().getBoolean("select_team.enable")) return;

        // dye shorts: 0:白(#) 1:橙 2:紫 3:蓝 4:黄(#) 5:绿(#) 6:粉(#) 7:灰(#) 8:灰 9:青(#) 10:紫 11:蓝(#) 12:? 13:绿 14:红(#) 15:BLACK 16+:错误

        ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.teamsel-open");

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 3 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name"));

//        int[] solts = {10,11,12,13,14,15,16,19};
        // 2队{11,15}, 4队{10, 12, 14, 16}, 8队{10, 12, 14, 16, 28, 30, 32, 34}

        // 菜单自带物品
        ItemStack leaveTeam_item = new ItemStack(Material.WOOL);
        ItemMeta leaveTeam_item_meta = leaveTeam_item.getItemMeta();
        leaveTeam_item.setDurability((short) 16);
        leaveTeam_item_meta.setDisplayName(ConfigData.teamsel_gui_items_leave_name);
        leaveTeam_item_meta.setLore(ConfigData.teamsel_gui_items_leave_lore);
        leaveTeam_item.setItemMeta(leaveTeam_item_meta);
        /**/
        ItemStack closeGUI_item = new ItemStack(Material.BARRIER);
        ItemMeta closeGUI_item_meta = closeGUI_item.getItemMeta();
        closeGUI_item_meta.setDisplayName(ConfigData.teamsel_gui_items_close_name);
        closeGUI_item_meta.setLore(ConfigData.teamsel_gui_items_close_lore);
        closeGUI_item.setItemMeta(closeGUI_item_meta);


        int[] slots;
        int teamCount = TeamManager.teams.size();
        if (teamCount == 2) {
            slots = new int[]{11, 15};
            gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name") + "§8§a§f§e§9§f§3§2");

            gui.setItem(30, leaveTeam_item);
            gui.setItem(32, closeGUI_item);
        } else if (teamCount == 4) {
            slots = new int[]{10, 12, 14, 16};
            gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name") + "§8§a§f§e§9§f§3§2");

            gui.setItem(30, leaveTeam_item);
            gui.setItem(32, closeGUI_item);
        } else if (teamCount == 8) {
            slots = new int[]{10, 12, 14, 16, 28, 30, 32, 34};
            gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name") + "§8§a§f§e§9§f§3§2");

            gui.setItem(48, leaveTeam_item);
            gui.setItem(50, closeGUI_item);
        } else {
            slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19};
            gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name") + "§8§a§f§e§9§f§3§2");

            gui.setItem(30, leaveTeam_item);
            gui.setItem(32, closeGUI_item);
        }


        for (int i = 0; i < teamCount; i++) {
            String team = TeamManager.teams.get(i);

            ItemStack item = new ItemStack(Material.WOOL);
            ItemMeta meta = item.getItemMeta();

            String item_name = ConfigData.teamsel_gui_items_wool_name;
            item_name = MessageVariableUtils.teamNameColor_p_s(item_name, team);
            meta.setDisplayName(item_name + "§8§a§f§e§a§3§7§a§f");

            List<String> item_lore = new ArrayList<>();
            for (String s : ConfigData.teamsel_gui_items_wool_lore_head) {
                item_lore.add(MessageVariableUtils.teamNameColor_p_s(s, team).replace("{players}",TeamManager.getTeamPlayerSize(team)+"").replace("{maxplayers}", GameData_cfg.team_players+""));
            }
            for (Player n_p : GamePlayers.players) {
                if (!TeamManager.player_teams.isEmpty() && TeamManager.player_teams.containsKey(n_p) && TeamManager.player_teams.get(n_p).equals(team)) {
                    item_lore.add(ConfigData.teamsel_gui_items_wool_lore_player.replace("{player_name}",n_p.getName()));
                }
            }
            for (String s : ConfigData.teamsel_gui_items_wool_lore_foot) {
                item_lore.add(MessageVariableUtils.teamNameColor_p_s(s, team).replace("{players}",TeamManager.getTeamPlayerSize(team)+"").replace("maxplayers", GameData_cfg.team_players+""));
            }

            String playerTeam = TeamManager.player_teams.get(p);
            if (!TeamManager.player_teams.isEmpty()) {
                if (playerTeam != null && TeamManager.player_teams.get(p).equals(team)) {
                    item_lore.add(ConfigData.teamsel_gui_items_status_inteam);
                } else if (playerTeam != null && !TeamManager.player_teams.get(p).equals(team) && TeamManager.getTeamPlayerSize(team) >= GameData_cfg.team_players) {
                    item_lore.add(ConfigData.teamsel_gui_items_status_full);
                } else {
                    item_lore.add(ConfigData.teamsel_gui_items_status_select);
                }
            } else {
                item_lore.add(ConfigData.teamsel_gui_items_status_select);
            }
            meta.setLore(item_lore);

            switch (team) {
                case "RED":
                    item.setDurability((short) 14);
                    break;
                case "BLUE":
                    item.setDurability((short) 11);
                    break;
                case "GREEN":
                    item.setDurability((short) 5);
                    break;
                case "YELLOW":
                    item.setDurability((short) 4);
                    break;
                case "PINK":
                    item.setDurability((short) 6);
                    break;
                case "AQUA":
                    item.setDurability((short) 9);
                    break;
                case "GRAY":
                    item.setDurability((short) 7);
                    break;
                case "WHITE":
                    item.setDurability((short) 0);
                    break;
            }

            item.setItemMeta(meta);

            gui.setItem(slots[i], item);
        }













        p.openInventory(gui);

    }

    @EventHandler
    public void onPlayerCLickInventory(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getType() == InventoryType.CHEST &&
                e.getInventory().getName().endsWith("§8§a§f§e§9§f§3§2") &&
                    GameStats.get() == 1 && ConfigData.teamsel_enabled
                && e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
            e.setCancelled(true);

            ItemStack item = e.getCurrentItem();
            ItemMeta meta = item.getItemMeta();

            if (item.getType() == Material.WOOL && meta.getDisplayName().endsWith("§8§a§f§e§a§3§7§a§f")) {
                if (item.getDurability() == 14) {
                    p.closeInventory();
                    TeamManager.join(p, "RED");
                }
                if (item.getDurability() == 11) {
                    p.closeInventory();
                    TeamManager.join(p, "BLUE");
                }
                if (item.getDurability() == 5) {
                    p.closeInventory();
                    TeamManager.join(p, "GREEN");
                }
                if (item.getDurability() == 4) {
                    p.closeInventory();
                    TeamManager.join(p, "YELLOW");
                }
                if (item.getDurability() == 6) {
                    p.closeInventory();
                    TeamManager.join(p, "PINK");
                }
                if (item.getDurability() == 9) {
                    p.closeInventory();
                    TeamManager.join(p, "AQUA");
                }
                if (item.getDurability() == 7) {
                    p.closeInventory();
                    TeamManager.join(p, "GRAY");
                }
                if (item.getDurability() == 0) {
                    p.closeInventory();
                    TeamManager.join(p, "WHITE");
                }
            }
            if (item.getType() == Material.WOOL) {
                if (item.getDurability() == 16 && meta.getDisplayName().equalsIgnoreCase(ConfigData.teamsel_gui_items_leave_name)) {
                    p.closeInventory();
                    TeamManager.clear(p);
                }
            }
            if (item.getType() == Material.BARRIER) {
                if (meta.getDisplayName().equalsIgnoreCase(ConfigData.teamsel_gui_items_close_name)) {
                    p.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void LeaveClear(PlayerQuitEvent e) {
        TeamManager.clear(e.getPlayer());
    }

}
