package cn.comicalpixel.creeperstarbedwars.Shop.Item.QuickShop;

import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Items.ToolsItem.ToolItemsManager;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class QuickShop_Add_GUI implements Listener {

    public static ShopConfig shopConfig = CreeperStarBedwars.getPlugin().getShopConfig();

    public static void open(Player p, ItemStack add_item) {

        // GUI名称后缀 §2§e§b§k§a§m§0§a
        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-quickshop.title") + "§2§e§b§k§a§m§0§a");
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        gui.setItem(4, add_item);

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-1.items");
        for (int i = 0; i < items.size(); i++) {
            // 特殊物品处理 如工具
            if (items.get(i).equalsIgnoreCase("shears_")) {
                ItemStack item = new ItemStack(Material.BEDROCK);
                ItemMeta meta = item.getItemMeta();
                if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                    item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.shears"+".gui-item.i", true).clone();
                }
                if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                    item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.shears"+".gui-item.xp", true).clone();
                }
                meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0101" + "§8) ");
                item.setItemMeta(meta);


                gui.setItem((solts[i]) - 1, item);

            } else if (items.get(i).equalsIgnoreCase("pickaxe_")) {
                ItemStack item = new ItemStack(Material.BEDROCK);
                ItemMeta meta = item.getItemMeta();
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 0) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) <= 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L1"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L1"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0201" + "§8) ");

                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0204" + "§8) ");
                }
                item.setItemMeta(meta);

                gui.setItem((solts[i]) - 1, item);

            } else if (items.get(i).equalsIgnoreCase("axe_")) {

                ItemStack item = new ItemStack(Material.BEDROCK);
                ItemMeta meta = item.getItemMeta();
                if (ToolItemsManager.players_toolLevel_斧头.get(p) <= 0) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) <= 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L1"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L1"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#§r§o§o§1§m§r§8tools_0304" + "§8) ");
                }
                item.setItemMeta(meta);

                gui.setItem((solts[i]) - 1, item);

            } else {

                // 常规物品处理


                ItemStack item = new ItemStack(Material.BEDROCK);
                ItemMeta meta = item.getItemMeta();
                if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                    item = ConfigUtils.getItemStack(shopConfig, "Items."+items.get(i)+".gui-item.i", true).clone();
                }
                if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                    item = ConfigUtils.getItemStack(shopConfig, "Items."+items.get(i)+".gui-item.xp", true).clone();
                }
                meta = item.getItemMeta();
                meta.setDisplayName(meta.getDisplayName() + "§8 (#" + items.get(i) + "§8) ");
                item.setItemMeta(meta);


                gui.setItem((solts[i]) - 1, item);

            }
        }

        p.openInventory(gui);

    }

}
