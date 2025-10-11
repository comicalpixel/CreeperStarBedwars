package cn.comicalpixel.creeperstarbedwars.Items.ToolsItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToolItemsManager {

    public static HashMap<Player, Integer> players_toolLevel_剪刀 = new HashMap<>();
    public static HashMap<Player, Integer> players_toolLevel_稿子 = new HashMap<>();
    public static HashMap<Player, Integer> players_toolLevel_斧头 = new HashMap<>();

    public static void reset_all(Player p) {
        players_toolLevel_剪刀.put(p, 0);
        players_toolLevel_稿子.put(p, 0);
        players_toolLevel_斧头.put(p, 0);
    }

    public static void giveItems(Player p) {

        ItemStack item_shears = new ItemStack(Material.SHEARS);
        ItemMeta meta_shears = item_shears.getItemMeta();
        meta_shears.spigot().setUnbreakable(true);
        item_shears.setItemMeta(meta_shears);

        if (players_toolLevel_剪刀.get(p) == 1) {
            p.getInventory().addItem(item_shears);
        }

        ItemStack item_pickaxe = new ItemStack(Material.WOOD_PICKAXE);
        ItemMeta meta_pickaxe = item_pickaxe.getItemMeta();
        meta_pickaxe.spigot().setUnbreakable(true);
        switch (players_toolLevel_稿子.get(p)) {
            case 1:
                item_pickaxe.setType(Material.WOOD_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 0, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().addItem(item_pickaxe);
                break;
            case 2:
                item_pickaxe.setType(Material.IRON_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 0, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().addItem(item_pickaxe);
                break;
            case 3:
                item_pickaxe.setType(Material.GOLD_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 3, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().addItem(item_pickaxe);
                break;
            case 4:
                item_pickaxe.setType(Material.DIAMOND_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 3, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().addItem(item_pickaxe);
                break;
            default:
                break;
        }


        ItemStack axe_item = new ItemStack(Material.WOOD_AXE);
        ItemMeta meta_axe = axe_item.getItemMeta();
        meta_axe.spigot().setUnbreakable(true);
        switch (players_toolLevel_斧头.get(p)) {
            case 1:
                axe_item.setType(Material.WOOD_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 1, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().addItem(axe_item);
                break;
            case 2:
                axe_item.setType(Material.IRON_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 1, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().addItem(axe_item);
                break;
            case 3:
                axe_item.setType(Material.GOLD_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 2, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().addItem(axe_item);
                break;
            case 4:
                axe_item.setType(Material.DIAMOND_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 3, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().addItem(axe_item);
                break;
            default:
                break;
        }



    }

    private static List<String> FixedItemsLore = new ArrayList<String>();

    public static void buy_regive_01(Player p) {
        p.getInventory().remove(Material.SHEARS);

        ItemStack item = new ItemStack(Material.SHEARS);
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        FixedItemsLore.add(ChatColor.DARK_GRAY + "Fixed set items");
        meta.setLore(FixedItemsLore);
        item.setItemMeta(meta);

        if (players_toolLevel_剪刀.get(p) == 1) {
            p.getInventory().addItem(item);
        }
    }

    public static void buy_regive_02(Player p) {

        ItemStack item_pickaxe = new ItemStack(Material.WOOD_PICKAXE);
        ItemMeta meta_pickaxe = item_pickaxe.getItemMeta();
        FixedItemsLore.add(ChatColor.DARK_GRAY + "Fixed set items");
        meta_pickaxe.setLore(FixedItemsLore);
        meta_pickaxe.spigot().setUnbreakable(true);
        switch (players_toolLevel_稿子.get(p)) {
            case 1:
                item_pickaxe.setType(Material.WOOD_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 0, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().remove(Material.WOOD_PICKAXE);
                p.getInventory().addItem(item_pickaxe);
                break;
            case 2:
                item_pickaxe.setType(Material.IRON_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 0, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().remove(Material.WOOD_PICKAXE);
                p.getInventory().addItem(item_pickaxe);
                break;
            case 3:
                item_pickaxe.setType(Material.GOLD_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 3, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().remove(Material.IRON_PICKAXE);
                p.getInventory().addItem(item_pickaxe);
                break;
            case 4:
                item_pickaxe.setType(Material.DIAMOND_PICKAXE);
                meta_pickaxe.addEnchant(Enchantment.DIG_SPEED, 3, true);
                item_pickaxe.setItemMeta(meta_pickaxe);
                p.getInventory().remove(Material.GOLD_PICKAXE);
                p.getInventory().addItem(item_pickaxe);
                break;
            default:
                break;
        }

    }

    public static void buy_regive_03(Player p) {

        ItemStack axe_item = new ItemStack(Material.WOOD_AXE);
        ItemMeta meta_axe = axe_item.getItemMeta();
        FixedItemsLore.add(ChatColor.DARK_GRAY + "Fixed set items");
        meta_axe.setLore(FixedItemsLore);
        meta_axe.spigot().setUnbreakable(true);
        switch (players_toolLevel_斧头.get(p)) {
            case 1:
                axe_item.setType(Material.WOOD_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 1, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().remove(Material.WOOD_AXE);
                p.getInventory().addItem(axe_item);
                break;
            case 2:
                axe_item.setType(Material.IRON_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 1, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().remove(Material.WOOD_AXE);
                p.getInventory().addItem(axe_item);
                break;
            case 3:
                axe_item.setType(Material.GOLD_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 2, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().remove(Material.IRON_AXE);
                p.getInventory().addItem(axe_item);
                break;
            case 4:
                axe_item.setType(Material.DIAMOND_AXE);
                meta_axe.addEnchant(Enchantment.DIG_SPEED, 3, true);
                axe_item.setItemMeta(meta_axe);
                p.getInventory().remove(Material.GOLD_AXE);
                p.getInventory().addItem(axe_item);
                break;
            default:
                break;
        }

    }

}
