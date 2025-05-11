package cn.comicalpixel.creeperstarbedwars.Shop.Item;

import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemShop_GUI implements Listener {

    public static ShopConfig shopConfig = CreeperStarBedwars.getPlugin().getShopConfig();

    public static void open(Player p, int page) {

        if (page == 0) {
            ItemShop_GUI.gui_op0(p);
        }

    }

    /*

        GUI PAGE : END WITH

        0:§2§e§b§k§a§5§e§0
        1:§2§e§b§k§a§5§e§a
        2:§2§e§b§k§a§5§e§2
        3:§2§e§b§k§a§5§e§e
        4:§2§e§b§k§a§5§e§4
        5:§2§e§b§k§a§5§e§5
        6:§2§e§b§k§a§5§e§6
        7:§2§e§b§k§a§5§e§7
        8:§2§e§b§k§a§5§e§8

    */

    public static void gui_op0(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-quickshop.title") + "§2§e§b§k§a§5§e§0");
        setGUIItems(gui, 0);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };



        ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-open");
        p.openInventory(gui);

    }

    public static void setGUIItems(Inventory gui, int page) {

        // 物品
        gui.setItem(0, ConfigUtils.getItemStack(shopConfig, "GUI.gui-quickshop.icon"));
        gui.setItem(1, ConfigUtils.getItemStack(shopConfig, "GUI.gui-1.icon"));
        gui.setItem(2, ConfigUtils.getItemStack(shopConfig, "GUI.gui-2.icon"));
        gui.setItem(3, ConfigUtils.getItemStack(shopConfig, "GUI.gui-3.icon"));
        gui.setItem(4, ConfigUtils.getItemStack(shopConfig, "GUI.gui-4.icon"));
        gui.setItem(5, ConfigUtils.getItemStack(shopConfig, "GUI.gui-5.icon"));
        gui.setItem(6, ConfigUtils.getItemStack(shopConfig, "GUI.gui-6.icon"));
        gui.setItem(7, ConfigUtils.getItemStack(shopConfig, "GUI.gui-7.icon"));
        gui.setItem(8, ConfigUtils.getItemStack(shopConfig, "GUI.gui-8.icon"));

        // 未设置的
        ItemStack notset = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta notset_meta = notset.getItemMeta();
        notset_meta.setDisplayName(ConfigUtils.getString(shopConfig, "GUI.glass-pane-settings.name"));
        notset_meta.setLore(ConfigUtils.getStringList(shopConfig, "GUI.glass-pane-settings.lore"));
        notset.setItemMeta(notset_meta);
        notset.setDurability((short) shopConfig.getInt("GUI.glass-pane-settings.not-selected-data"));
        // 已设置的
        ItemStack seted = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta seted_meta = seted.getItemMeta();
        seted_meta.setDisplayName(ConfigUtils.getString(shopConfig, "GUI.glass-pane-settings.name"));
        seted_meta.setLore(ConfigUtils.getStringList(shopConfig, "GUI.glass-pane-settings.lore"));
        seted.setItemMeta(seted_meta);
        seted.setDurability((short) shopConfig.getInt("GUI.glass-pane-settings.selected-data"));
        // 设置
        gui.setItem(0 +9, notset);
        gui.setItem(1+ 9, notset);
        gui.setItem(2 +9, notset);
        gui.setItem(3 +9, notset);
        gui.setItem(4 +9, notset);
        gui.setItem(5 +9, notset);
        gui.setItem(6 +9, notset);
        gui.setItem(7 +9, notset);
        gui.setItem(8 +9, notset);
        gui.setItem(page+9, seted);
        /**/
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getClickedInventory().getType() == InventoryType.CHEST && e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§0")) {

            e.setCancelled(true);

            /**/

        }

    }

}
