package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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

            ItemStack item = e.getCurrentItem();
            if (item != null && (item.getType() == Material.WOOD_SWORD ||
                    item.getType() == Material.SHEARS ||
                    item.getType().toString().endsWith("_AXE") ||
                    item.getType().toString().endsWith("_PICKAXE"))) {

                if (e.getInventory().getType() == InventoryType.CHEST || e.getInventory().getType() == InventoryType.ENDER_CHEST) {
                    e.setCancelled(true);
                }
            }

        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDrop_gdwp(PlayerDropItemEvent e) {

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


    @EventHandler
    public void onDrop_sword(PlayerDropItemEvent e) {
        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }
        if (e.isCancelled()) return;

        if (e.getItemDrop() == null) return;

        if (e.getItemDrop().getItemStack().getType().toString().endsWith("_SWORD") && e.getItemDrop().getItemStack().getType() != Material.WOOD_SWORD) {
            e.setCancelled(true);
            ItemStack item = e.getItemDrop().getItemStack();
            ItemMeta meta = item.getItemMeta();
            if (meta.hasEnchant(Enchantment.DAMAGE_ALL)) {
                meta.removeEnchant(Enchantment.DAMAGE_ALL);
            }
            e.getItemDrop().getItemStack().setItemMeta(meta);
            e.setCancelled(false);
            // 如果背包里面没有剑了就给予玩家一把木剑
            int swords_size = 0;
            for (ItemStack items : e.getPlayer().getInventory().getContents()) {
                if (items != null && items.getType().toString().endsWith("_SWORD")) {
                    swords_size += 1;
                }
            }
            if (swords_size < 1 && !e.getPlayer().getInventory().contains(Material.WOOD_SWORD)) {
                ItemStack sword_item = new ItemStack(Material.WOOD_SWORD);
                ItemMeta sword_meta = sword_item.getItemMeta();
                List<String> sword_lore = new ArrayList<>();
                sword_lore.add(" ");
                sword_lore.add(ChatColor.DARK_GRAY + "Fixed set items");
                sword_meta.setLore(sword_lore);
                sword_meta.spigot().setUnbreakable(true);
                sword_item.setItemMeta(sword_meta);
                e.getPlayer().getInventory().addItem(sword_item);
            }
        }
    }
    @EventHandler
    public void onPickup_sword(PlayerPickupItemEvent e) {
        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }
        if (e.isCancelled()) return;

        if (e.getItem() == null) return;

        if (e.getItem().getItemStack().getType().toString().endsWith("_SWORD")) {
            e.getPlayer().getInventory().remove(Material.WOOD_SWORD);
        }
    }


    @EventHandler
    public void InvInteractLobbyItem(PlayerInteractEvent e) {
        if (GameStats.get() != 1) {
            return;
        }
        if (e.getItem() == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        ItemMeta meta = item.getItemMeta();
        e.setCancelled(true);

        // 0: §r§9§a§e§f§f
        // 1: §r§9§a§e§f§a
        // 2: §r§9§a§e§f§2
        // 3: §r§9§a§e§f§3
        // 4: §r§9§a§e§f§4
        // 5: §r§9§a§e§f§e
        // 6: §r§9§a§e§f§b
        // 7: §r§9§a§e§f§7
        // 8: §r§9§a§e§f§8

        if (meta.getDisplayName().endsWith("§r§9§a§e§f§f")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i0_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§a")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i1_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§2")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i2_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§3")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i3_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§4")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i4_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§e")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i5_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§b")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i6_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§7")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i7_command);
        }
        if (meta.getDisplayName().endsWith("§r§9§a§e§f§8")) {
            Bukkit.dispatchCommand(p, ConfigData.LobbyItems_i8_command);
        }

    }

}
