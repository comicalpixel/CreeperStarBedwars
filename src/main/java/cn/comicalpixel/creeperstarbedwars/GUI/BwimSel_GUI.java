package cn.comicalpixel.creeperstarbedwars.GUI;

import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BwimSel_GUI implements Listener {

    public static void open(Player p) {

        if (!ConfigData.bwimsel_enabled) return;

        ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.bwim-open");

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 3 * 9, ConfigUtils.getString(CreeperStarBedwars.getInstance().getConfig(), "select-bwim.gui-name"));

        ItemStack i_item = new ItemStack(Material.valueOf(ConfigData.bwimsel_gui_items_i_type), 1);
        ItemMeta i_meta = i_item.getItemMeta();
        i_meta.setDisplayName(ConfigData.bwimsel_gui_items_i_name);
        List<String> i_lore = new ArrayList<>();
        i_lore.addAll(ConfigData.bwimsel_gui_items_i_lore);
        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
            i_lore.add(ConfigData.bwimsel_gui_items_status_select);
        } else {
            i_lore.add(ConfigData.bwimsel_gui_items_status_noselect);
        }
        i_meta.setLore(i_lore);
        i_item.setItemMeta(i_meta);
        i_item.setDurability((short) ConfigData.bwimsel_gui_items_i_durability);
        gui.setItem(11, i_item);

        ItemStack xp_item = new ItemStack(Material.valueOf(ConfigData.bwimsel_gui_items_xp_type), 1);
        ItemMeta xp_meta = xp_item.getItemMeta();
        xp_meta.setDisplayName(ConfigData.bwimsel_gui_items_xp_name);
        List<String> xp_lore = new ArrayList<>();
        xp_lore.addAll(ConfigData.bwimsel_gui_items_xp_lore);
        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
            xp_lore.add(ConfigData.bwimsel_gui_items_status_select);
        } else {
            xp_lore.add(ConfigData.bwimsel_gui_items_status_noselect);
        }
        xp_meta.setLore(xp_lore);
        xp_item.setItemMeta(xp_meta);
        xp_item.setDurability((short) ConfigData.bwimsel_gui_items_xp_durability);
        gui.setItem(15, xp_item);


        p.openInventory(gui);

    }

}
