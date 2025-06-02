package cn.comicalpixel.creeperstarbedwars.Shop.Item;

import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Items.ToolsItem.ToolItemsManager;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
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

    public static void open(Player p) {

        ItemShop_GUI.gui_op0(p);

        ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-open");

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



        p.openInventory(gui);

    }
    public static void gui_op1(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-1.title") + "§2§e§b§k§a§5§e§a");
        setGUIItems(gui, 1);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
    public static void gui_op2(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-2.title") + "§2§e§b§k§a§5§e§2");
        setGUIItems(gui, 2);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-2.items");
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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
    public static void gui_op3(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-3.title") + "§2§e§b§k§a§5§e§3");
        setGUIItems(gui, 3);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-3.items");
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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
    public static void gui_op4(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-4.title") + "§2§e§b§k§a§5§e§4");
        setGUIItems(gui, 4);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-4.items");
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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
    public static void gui_op5(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-5.title") + "§2§e§b§k§a§5§e§5");
        setGUIItems(gui, 5);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-5.items");
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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
    public static void gui_op6(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-6.title") + "§2§e§b§k§a§5§e§6");
        setGUIItems(gui, 6);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-6.items");
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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
    public static void gui_op7(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-7.title") + "§2§e§b§k§a§5§e§7");
        setGUIItems(gui, 7);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-7.items");
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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
    public static void gui_op8(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-8.title") + "§2§e§b§k§a§5§e§8");
        setGUIItems(gui, 8);
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        List<String> items = ConfigUtils.getStringList(shopConfig, "GUI.gui-8.items");
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
                meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0101" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0201" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0202" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0203" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_稿子.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.pickaxe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0204" + "§8) ");
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
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0301" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 1) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L2"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0302" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) == 2) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L3"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0303" + "§8) ");
                }
                if (ToolItemsManager.players_toolLevel_斧头.get(p) >= 3) {
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.i", true).clone();
                    }
                    if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                        item = ConfigUtils.getItemStack(shopConfig, "Tool-Items.axe.L4"+".gui-item.xp", true).clone();
                    }
                    meta = item.getItemMeta();
                    meta.setDisplayName(meta.getDisplayName() + "§8 (#tools_0304" + "§8) ");
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
//            ItemStack item = new ItemStack(Material.BEDROCK);
//            ItemMeta meta = item.getItemMeta();
//            if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
//                item = ConfigUtils.getItemStack(shopConfig, "Items."+items.get(i)+".gui-item.i", true).clone();
//            }
//            if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
//                item = ConfigUtils.getItemStack(shopConfig, "Items."+items.get(i)+".gui-item.xp", true).clone();
//            }
//            meta = item.getItemMeta();
//            meta.setDisplayName(meta.getDisplayName() + "§8 (#" + items.get(i) + "§8) ");
//            item.setItemMeta(meta);
//
//            gui.setItem((solts[i]) - 1, item);
        }

        p.openInventory(gui);

    }


    public static void setGUIItems(Inventory gui, int page) {

        // 物品
        /*
            0:§2§e§b§8§a§5§e§0
            1:§2§e§b§8§a§5§e§a
            2:§2§e§b§8§a§5§e§2
            3:§2§e§b§8§a§5§e§e
            4:§2§e§b§8§a§5§e§4
            5:§2§e§b§8§a§5§e§5
            6:§2§e§b§8§a§5§e§6
            7:§2§e§b§8§a§5§e§7
            8:§2§e§b§8§a§5§e§8
        */
        ItemStack pi0 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-quickshop.icon", true);
        ItemStack pi1 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-1.icon", true);
        ItemStack pi2 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-2.icon", true);
        ItemStack pi3 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-3.icon", true);
        ItemStack pi4 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-4.icon", true);
        ItemStack pi5 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-5.icon", true);
        ItemStack pi6 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-6.icon", true);
        ItemStack pi7 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-7.icon", true);
        ItemStack pi8 = ConfigUtils.getItemStack(shopConfig, "GUI.gui-8.icon", true);
        ItemMeta pi0m = pi0.getItemMeta();
        ItemMeta pi1m = pi1.getItemMeta();
        ItemMeta pi2m = pi2.getItemMeta();
        ItemMeta pi3m = pi3.getItemMeta();
        ItemMeta pi4m = pi4.getItemMeta();
        ItemMeta pi5m = pi5.getItemMeta();
        ItemMeta pi6m = pi6.getItemMeta();
        ItemMeta pi7m = pi7.getItemMeta();
        ItemMeta pi8m = pi8.getItemMeta();
        pi0m.setDisplayName(pi0.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§0");
        pi1m.setDisplayName(pi1.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§a");
        pi2m.setDisplayName(pi2.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§2");
        pi3m.setDisplayName(pi3.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§3");
        pi4m.setDisplayName(pi4.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§4");
        pi5m.setDisplayName(pi5.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§5");
        pi6m.setDisplayName(pi6.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§6");
        pi7m.setDisplayName(pi7.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§7");
        pi8m.setDisplayName(pi8.getItemMeta().getDisplayName() + "§2§e§b§8§a§5§e§8");
        pi0.setItemMeta(pi0m);
        pi1.setItemMeta(pi1m);
        pi2.setItemMeta(pi2m);
        pi3.setItemMeta(pi3m);
        pi4.setItemMeta(pi4m);
        pi5.setItemMeta(pi5m);
        pi6.setItemMeta(pi6m);
        pi7.setItemMeta(pi7m);
        pi8.setItemMeta(pi8m);
        gui.setItem(0, pi0);
        gui.setItem(1, pi1);
        gui.setItem(2, pi2);
        gui.setItem(3, pi3);
        gui.setItem(4, pi4);
        gui.setItem(5, pi5);
        gui.setItem(6, pi6);
        gui.setItem(7, pi7);
        gui.setItem(8, pi8);

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



}
