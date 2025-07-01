package cn.comicalpixel.creeperstarbedwars.Shop.Update;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.UpdradeConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamShop_GUI implements Listener {

    public static HashMap<String, Integer> team_swordSharpness = new HashMap<>(); // 储存队伍锋利附魔等级
    public static HashMap<String, Integer> team_armorProtection = new HashMap<>(); // 储存队伍保护附魔等级
    public static HashMap<String, Integer> team_fastDig = new HashMap<>(); // 储存队伍急迫(疯狂矿工)等级
    public static HashMap<String, Integer> team_GenResLevel = new HashMap<>(); // 储存队伍资源点等级
    public static HashMap<String, Integer> team_HealPool = new HashMap<>(); // 储存队伍治愈池
    public static HashMap<String, List<Integer>> team_Traps = new HashMap<>(); // 储存队伍所有的陷阱


    public static void gamestart_reset() {
        for (String team : TeamManager.teams) {
            team_swordSharpness.put(team, CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("updrade.sword_sharpness.def-level"));

            team_Traps.put(team, new ArrayList<Integer>());
        }
    }

    public static void open(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.title") + " §3§a§c§k§a§e§f");


        /* GUI图标 */

        // 装饰玻璃板 frames
        ItemStack glass_item = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glass_meta = glass_item.getItemMeta();
        glass_meta.setDisplayName(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.frame-icon.name"));
        glass_meta.setLore(ConfigUtils.getStringList(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.frame-icon.lore"));
        glass_item.setItemMeta(glass_meta);
        glass_item.setDurability((short) ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.frame-icon.damage"));
        for (int i : CreeperStarBedwars.getPlugin().getUpdradeConfig().getIntegerList("GUI.icon-solts.frames")) {
            gui.setItem(i, glass_item);
        }

        /**/

        // 武器锋利附魔
        ItemStack icon_swordSharpness_item = new ItemStack(Material.BARRIER);
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.updrade.sword_sharpness") ,icon_swordSharpness_item);

        /**/


        p.openInventory(gui);

        ConfigUtils.playSound(p,CreeperStarBedwars.getPlugin().getConfig(), "sound.update-open");

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!e.getInventory().getTitle().endsWith("§3§a§c§k§a§e§f")) return;

        if (e.getInventory().getType() != InventoryType.CHEST) return;

        e.setCancelled(true);

    }

}
