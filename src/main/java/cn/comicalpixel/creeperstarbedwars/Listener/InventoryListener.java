package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {

    // tips: 固定物品套装记得往lore里面加个"Fixed set items"

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        // p.sendMessage("Inv: " + e.getSlot());

        if (GameStats.get() == 1) {
            e.setCancelled(true);
        }
        if (e.getCurrentItem() == null) return;

        if (GameStats.get() == 2 || GameStats.get() == 3) {

            if (e.getCurrentItem().getType().toString().endsWith("_BOOTS") || e.getCurrentItem().getType().toString().endsWith("_LEGGINGS") || e.getCurrentItem().getType().toString().endsWith("_CHESTPLATE") || e.getCurrentItem().getType().toString().endsWith("_HELMET")) {
                e.setCancelled(true);
            }

        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {

        if (GameStats.get() == 1) {
            e.setCancelled(true);
        }

        if (e.getItemDrop().getType().toString().endsWith("_BOOTS") || e.getItemDrop().getType().toString().endsWith("_LEGGINGS") || e.getItemDrop().getType().toString().endsWith("_CHESTPLATE") || e.getItemDrop().getType().toString().endsWith("_HELMET")) {
            e.setCancelled(true);
        }

        ItemStack item = e.getItemDrop().getItemStack();
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            for (String s : meta.getLore()) {
                if (s.contains("Fixed set items")) {
                    e.setCancelled(true);
                }
            }
        }

        if (GameStats.get() == 2 || GameStats.get() == 3) {
            if (e.getItemDrop().getItemStack().getType().toString().endsWith("_PICKAXE")) {
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType().toString().endsWith("_AXE")) {
                e.setCancelled(true);
            }
            if (e.getItemDrop().getItemStack().getType() == Material.SHEARS) {
                e.setCancelled(true);
            }
        }

    }

}
