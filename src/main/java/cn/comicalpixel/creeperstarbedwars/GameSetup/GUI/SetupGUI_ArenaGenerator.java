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

public class SetupGUI_ArenaGenerator implements Listener {

    public static void open(Player p) {

        p.playSound(p.getLocation(), Sound.CLICK, 10, 1f);

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, "§3[Setup] Arena Generator " + "§c§e§b§a§r§e§n§a§r§e§c");

        ItemStack item_DIAMOND = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta meta_diamond = item_DIAMOND.getItemMeta();
        meta_diamond.setDisplayName("§bAdd Diamond Generator §1");
        List<String> lore_diamond = new ArrayList<>();
        lore_diamond.add("§eClick to Add! ");
        meta_diamond.setLore(lore_diamond);
        item_DIAMOND.setItemMeta(meta_diamond);
        gui.setItem(12 -1, item_DIAMOND);

        ItemStack item_EMERALD = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta_emerald = item_EMERALD.getItemMeta();
        meta_emerald.setDisplayName("§2Add Emerald Generator §2");
        List<String> lore_emerald = new ArrayList<>();
        lore_emerald.add("§eClick to Add! ");
        meta_emerald.setLore(lore_emerald);
        item_EMERALD.setItemMeta(meta_emerald);
        gui.setItem(16 -1, item_EMERALD);

        ItemStack item_tips = new ItemStack(Material.EMPTY_MAP);
        ItemMeta meta_tips = item_tips.getItemMeta();
        meta_tips.setDisplayName("§aTips");
        List<String> lore_tips = new ArrayList<>();
        lore_tips.add("§ehe diamond_pickaxe to set");
        lore_tips.add("§ea Block before setting it! ");
        meta_tips.setLore(lore_tips);
        item_tips.setItemMeta(meta_tips);
        gui.setItem(32-1, item_tips);

        p.openInventory(gui);

    }


    @EventHandler
    public void InventoryClick_MainSetupMenu(InventoryClickEvent e) {

        if (!e.getInventory().getTitle().endsWith("§c§e§b§a§r§e§n§a§r§e§c")) {
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

        if (item.getType() == Material.DIAMOND_BLOCK && meta.getDisplayName().endsWith("§1")) {
            p.closeInventory();
            p.performCommand("setup addGenerator diamond");
        }

        if (item.getType() == Material.EMERALD_BLOCK && meta.getDisplayName().endsWith("§2")) {
            p.closeInventory();
            p.performCommand("setup addGenerator emerald");
        }

    }

}
