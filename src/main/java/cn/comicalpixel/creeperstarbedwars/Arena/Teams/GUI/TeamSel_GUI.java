package cn.comicalpixel.creeperstarbedwars.Arena.Teams.GUI;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
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
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
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

        int[] slots;
        int teamCount = TeamManager.teams.size();
        if (teamCount == 2) {
            slots = new int[]{11, 15};
            gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name"));
        } else if (teamCount == 4) {
            slots = new int[]{10, 12, 14, 16};
            gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name"));
        } else if (teamCount == 8) {
            slots = new int[]{10, 12, 14, 16, 28, 30, 32, 34};
            gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name"));
        } else {
            slots = new int[]{10, 11, 12, 13, 14, 15, 16, 19};
            gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, CreeperStarBedwars.getPlugin().getConfig().getString("select_team.gui-name"));
        }

        // 遍历启用的队伍并设置图标
        for (int i = 0; i < teamCount; i++) {
            String team = TeamManager.teams.get(i);

            ItemStack item = new ItemStack(Material.WOOL);
            ItemMeta meta = item.getItemMeta();

            String item_name = ConfigData.teamsel_gui_items_wool_name;
            item_name = MessageVariableUtils.teamNameColor_p_s(item_name, team);
            meta.setDisplayName(item_name);

            List<String> item_lore = new ArrayList<>();
            for (String s : ConfigData.teamsel_gui_items_wool_lore_head) {
                item_lore.add(MessageVariableUtils.teamNameColor_p_s(s, team).replace("{players}",TeamManager.getTeamPlayerSize(team)+"").replace("{maxplayers}", GameData_cfg.team_players+""));
            }
            for (Player n_p : GamePlayers.players) {
                if (!TeamManager.player_teams.isEmpty() && TeamManager.player_teams.get(n_p).equals(team)) {
                    item_lore.add(ConfigData.teamsel_gui_items_wool_lore_player.replace("{player_name}",n_p.getName()));
                }
            }
            for (String s : ConfigData.teamsel_gui_items_wool_lore_foot) {
                item_lore.add(MessageVariableUtils.teamNameColor_p_s(s, team).replace("{players}",TeamManager.getTeamPlayerSize(team)+"").replace("maxplayers", GameData_cfg.team_players+""));
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
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equalsIgnoreCase("/teamsel")) {
            open(e.getPlayer());
        }
    }

}
