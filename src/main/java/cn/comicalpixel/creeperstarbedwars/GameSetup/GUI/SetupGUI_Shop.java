package cn.comicalpixel.creeperstarbedwars.GameSetup.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetupGUI_Shop implements Listener {

    public static void open(Player p) {

        p.playSound(p.getLocation(), Sound.CLICK, 10, 1f);

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 3 * 9, "§3[Setup] Game Shop " + "§c§e§b§o§1§3§a§c§m");

        List<String> lore_ = new ArrayList<String>();
        lore_.add("§3Click to Add! ");

        ItemStack item_itemShop = new ItemStack(Material.TNT);
        ItemMeta meta_itemShop = item_itemShop.getItemMeta();
        meta_itemShop.setDisplayName("§eItem Shop");
        meta_itemShop.setLore(lore_);
        item_itemShop.setItemMeta(meta_itemShop);
        gui.setItem(12 -1, item_itemShop);

        ItemStack item_teamShop = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta_teamShop = item_teamShop.getItemMeta();
        meta_teamShop.setDisplayName("§bUpdrade Shop");
        meta_teamShop.setLore(lore_);
        item_teamShop.setItemMeta(meta_teamShop);
        gui.setItem(16 -1, item_teamShop);

        p.openInventory(gui);

    }


    @EventHandler
    public void InventoryClick_MainSetupMenu(InventoryClickEvent e) {

        if (!e.getInventory().getTitle().endsWith("§c§e§b§o§1§3§a§c§m")) {
            return;
        }
        e.setCancelled(true);
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getRawSlot() >= (4 * 9) - 1) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }

        /**/
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        ItemMeta meta = item.getItemMeta();


        if (item.getType() == Material.TNT) {
            p.closeInventory();
            p.performCommand("setup addShop item");
        }

        if (item.getType() == Material.ENCHANTED_BOOK) {
            p.closeInventory();
            p.performCommand("setup addShop team");
        }

    }

}
