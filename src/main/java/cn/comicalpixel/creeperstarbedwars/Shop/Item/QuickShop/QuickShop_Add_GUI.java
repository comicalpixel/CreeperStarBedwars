package cn.comicalpixel.creeperstarbedwars.Shop.Item.QuickShop;

import cn.comicalpixel.creeperstarbedwars.Config.PlayerShopDataConfig;
import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Items.ToolsItem.ToolItemsManager;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Shop.Item.ItemShop_GUI;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Actionbar_Task;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.mongodb.type.ShopStats;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuickShop_Add_GUI implements Listener {

    public static ShopConfig shopConfig = CreeperStarBedwars.getPlugin().getShopConfig();
    public static PlayerShopDataConfig shopDataConfig = CreeperStarBedwars.getPlugin().getShopDataConfig();

    public static void pbuquan(Player p) {

        if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
            // MongoDB存储方式
            MongoDatabase database = CreeperStarBedwars.getPlugin().getMongoDBManager().database;
            Document filter = new Document("uuid", p.getUniqueId().toString());
            Document existing = database.getCollection("Shop_Stats").find(filter).first();
            
            if (existing != null) {
                return;
            }

            // 创建新文档
            Document document = new Document()
                .append("uuid", p.getUniqueId().toString())
                .append("name", p.getName())
                .append("solt1", shopConfig.getString("GUI.gui-quickshop.def-items.solt01"))
                .append("solt2", shopConfig.getString("GUI.gui-quickshop.def-items.solt02"))
                .append("solt3", shopConfig.getString("GUI.gui-quickshop.def-items.solt03"))
                .append("solt4", shopConfig.getString("GUI.gui-quickshop.def-items.solt04"))
                .append("solt5", shopConfig.getString("GUI.gui-quickshop.def-items.solt05"))
                .append("solt6", shopConfig.getString("GUI.gui-quickshop.def-items.solt06"))
                .append("solt7", shopConfig.getString("GUI.gui-quickshop.def-items.solt07"))
                .append("solt8", shopConfig.getString("GUI.gui-quickshop.def-items.solt08"))
                .append("solt9", shopConfig.getString("GUI.gui-quickshop.def-items.solt09"))
                .append("solt10", shopConfig.getString("GUI.gui-quickshop.def-items.solt10"))
                .append("solt11", shopConfig.getString("GUI.gui-quickshop.def-items.solt11"))
                .append("solt12", shopConfig.getString("GUI.gui-quickshop.def-items.solt12"))
                .append("solt13", shopConfig.getString("GUI.gui-quickshop.def-items.solt13"))
                .append("solt14", shopConfig.getString("GUI.gui-quickshop.def-items.solt14"))
                .append("solt15", shopConfig.getString("GUI.gui-quickshop.def-items.solt15"))
                .append("solt16", shopConfig.getString("GUI.gui-quickshop.def-items.solt16"))
                .append("solt17", shopConfig.getString("GUI.gui-quickshop.def-items.solt17"))
                .append("solt18", shopConfig.getString("GUI.gui-quickshop.def-items.solt18"))
                .append("solt19", shopConfig.getString("GUI.gui-quickshop.def-items.solt19"))
                .append("solt20", shopConfig.getString("GUI.gui-quickshop.def-items.solt20"))
                .append("solt21", shopConfig.getString("GUI.gui-quickshop.def-items.solt21"));
            
            database.getCollection("Shop_Stats").insertOne(document);
        } else {
            // 原有配置文件存储方式
            if (shopDataConfig.contains(p.getName())) {
                return;
            }
            shopDataConfig.set(p.getName() + ".solt1", shopConfig.getString("GUI.gui-quickshop.def-items.solt01"));
            shopDataConfig.set(p.getName() + ".solt2", shopConfig.getString("GUI.gui-quickshop.def-items.solt02"));
            shopDataConfig.set(p.getName() + ".solt3", shopConfig.getString("GUI.gui-quickshop.def-items.solt03"));
            shopDataConfig.set(p.getName() + ".solt4", shopConfig.getString("GUI.gui-quickshop.def-items.solt04"));
            shopDataConfig.set(p.getName() + ".solt5", shopConfig.getString("GUI.gui-quickshop.def-items.solt05"));
            shopDataConfig.set(p.getName() + ".solt6", shopConfig.getString("GUI.gui-quickshop.def-items.solt06"));
            shopDataConfig.set(p.getName() + ".solt7", shopConfig.getString("GUI.gui-quickshop.def-items.solt07"));
            shopDataConfig.set(p.getName() + ".solt8", shopConfig.getString("GUI.gui-quickshop.def-items.solt08"));
            shopDataConfig.set(p.getName() + ".solt9", shopConfig.getString("GUI.gui-quickshop.def-items.solt09"));
            shopDataConfig.set(p.getName() + ".solt10", shopConfig.getString("GUI.gui-quickshop.def-items.solt10"));
            shopDataConfig.set(p.getName() + ".solt11", shopConfig.getString("GUI.gui-quickshop.def-items.solt11"));
            shopDataConfig.set(p.getName() + ".solt12", shopConfig.getString("GUI.gui-quickshop.def-items.solt12"));
            shopDataConfig.set(p.getName() + ".solt13", shopConfig.getString("GUI.gui-quickshop.def-items.solt13"));
            shopDataConfig.set(p.getName() + ".solt14", shopConfig.getString("GUI.gui-quickshop.def-items.solt14"));
            shopDataConfig.set(p.getName() + ".solt15", shopConfig.getString("GUI.gui-quickshop.def-items.solt15"));
            shopDataConfig.set(p.getName() + ".solt16", shopConfig.getString("GUI.gui-quickshop.def-items.solt16"));
            shopDataConfig.set(p.getName() + ".solt17", shopConfig.getString("GUI.gui-quickshop.def-items.solt17"));
            shopDataConfig.set(p.getName() + ".solt18", shopConfig.getString("GUI.gui-quickshop.def-items.solt18"));
            shopDataConfig.set(p.getName() + ".solt19", shopConfig.getString("GUI.gui-quickshop.def-items.solt19"));
            shopDataConfig.set(p.getName() + ".solt20", shopConfig.getString("GUI.gui-quickshop.def-items.solt20"));
            shopDataConfig.set(p.getName() + ".solt21", shopConfig.getString("GUI.gui-quickshop.def-items.solt21"));
        }


        shopDataConfig.save();

    }

    public static void open(Player p, ItemStack add_item) {

        // GUI名称后缀 §2§e§b§k§a§m§0§a
        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(shopConfig, "GUI.gui-quickshop.title") + "§2§e§b§k§a§m§0§a");
        int[] solts = new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 };

        gui.setItem(4, add_item);

        List<String> items = new ArrayList<>();
        if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
            // MongoDB读取方式
            ShopStats shopStats = CreeperStarBedwars.getPlugin().getMongoDBManager().shopStats;
            for (int i = 1; i <= 21; i++) {
                items.add(shopStats.getSlot(p.getUniqueId(), i));
            }
        } else {
            // 原有配置文件读取方式
            for (int i = 1; i <= 21; i++) {
                items.add(CreeperStarBedwars.getPlugin().getShopDataConfig().getString(p.getName() + ".solt"+i));
            }
        }

        for (int i = 0; i < items.size(); i++) {
            // 特殊物品处理 如工具
            if (items.get(i).equalsIgnoreCase("none") || items.get(i) == null || items.get(i).isEmpty()) {
                ItemStack air_solt = ConfigUtils.getItemStack(shopConfig, "GUI.gui-quickshop.air-solt-item", true);
                gui.setItem((solts[i]) - 1, air_solt);
            }
            else if (items.get(i).equalsIgnoreCase("shears_")) {
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

    @EventHandler
    public void GuiClick(InventoryClickEvent e) {

        if (!e.getInventory().getTitle().endsWith("§2§e§b§k§a§m§0§a")) {
            return;
        }

        e.setCancelled(true);

        if (e.getCurrentItem()==null) return;

        if (e.getRawSlot() > e.getInventory().getSize()-1) {
            return;
        }

        if (e.getRawSlot() == 4) {
            ItemShop_GUI.gui_op0((Player) e.getWhoClicked());
            return;
        }

        HashMap<Integer, Integer> solts = new HashMap<>();
        solts.put(20, 1);
        solts.put(21, 2);
        solts.put(22, 3);
        solts.put(23, 4);
        solts.put(24, 5);
        solts.put(25, 6);
        solts.put(26, 7);

        solts.put(29, 8);
        solts.put(30, 9);
        solts.put(31, 10);
        solts.put(32, 11);
        solts.put(33, 12);
        solts.put(34, 13);
        solts.put(35, 14);

        solts.put(38, 15);
        solts.put(39, 16);
        solts.put(40, 17);
        solts.put(41, 18);
        solts.put(42, 19);
        solts.put(43, 20);
        solts.put(44, 21);

        if (solts.containsKey(e.getRawSlot()+1)) {

            ItemStack item = e.getInventory().getItem(4);

            String add_item = "none";

            /*添加至快捷购买*/
            /*
                工具类
            */
            if (
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0101§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0201§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0202§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0203§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0204§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0301§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0302§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0303§8) ") ||
                    item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0304§8) ")

            ) {

                if (item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0101§8) ")) {
                    add_item = "shears_";
                }

                else if (item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0201§8) ")||
                        item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0202§8) ")||
                        item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0203§8) ")||
                        item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0204§8) ")) {
                    add_item = "pickaxe_";
                }

                else if (item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0301§8) ")||
                        item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0302§8) ")||
                        item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0303§8) ")||
                        item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0304§8) ")) {
                    add_item = "axe_";
                }


            }
            /*
                普通的不能再普通的
            */
            else {

                List<String> items = new ArrayList<>();
                items.addAll(shopConfig.getStringList("GUI.gui-1.items"));
                items.addAll(shopConfig.getStringList("GUI.gui-2.items"));
                items.addAll(shopConfig.getStringList("GUI.gui-3.items"));
                items.addAll(shopConfig.getStringList("GUI.gui-4.items"));
                items.addAll(shopConfig.getStringList("GUI.gui-5.items"));
                items.addAll(shopConfig.getStringList("GUI.gui-6.items"));
                items.addAll(shopConfig.getStringList("GUI.gui-7.items"));
                items.addAll(shopConfig.getStringList("GUI.gui-8.items"));

                for (String item_bh : items) {
                    if (item.getItemMeta().getDisplayName().endsWith("#" + item_bh + "§8) ")) {
                        add_item = item_bh;
                    }
                }

            }
            Player p = (Player) e.getWhoClicked();

            if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
                ShopStats shopStats = CreeperStarBedwars.getPlugin().getMongoDBManager().shopStats;
                shopStats.setSlot(p.getUniqueId(), (solts.get(e.getRawSlot()+1)), add_item);
            } else {
                CreeperStarBedwars.getInstance().getShopDataConfig().set(p.getName()+".solt"+(solts.get(e.getRawSlot()+1)), add_item);
                CreeperStarBedwars.getInstance().getShopDataConfig().save();
            }

//            p.sendMessage("solt" + solts.get(e.getRawSlot()+1) + ": " + add_item);
            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                ItemShop_GUI.gui_op0(p);
            },1);
        }

    }

}
