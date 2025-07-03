package cn.comicalpixel.creeperstarbedwars.Shop.Item;

import cn.comicalpixel.creeperstarbedwars.Arena.GameTools;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Items.ToolsItem.ToolItemsManager;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Shop.Item.PlayerArmor.PlayerArmorManager;
import cn.comicalpixel.creeperstarbedwars.Shop.ShopEnoughUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemShop_Manager {

    public static ShopConfig shopConfig = CreeperStarBedwars.getPlugin().getShopConfig();

    public static void buy(Player p, ItemStack gui_item) {
        //  p.sendMessage("dbfjhfjk");
        List<String> items = new ArrayList<>();

        items.addAll(shopConfig.getStringList("GUI.gui-1.items"));
        items.addAll(shopConfig.getStringList("GUI.gui-2.items"));
        items.addAll(shopConfig.getStringList("GUI.gui-3.items"));
        items.addAll(shopConfig.getStringList("GUI.gui-4.items"));
        items.addAll(shopConfig.getStringList("GUI.gui-5.items"));
        items.addAll(shopConfig.getStringList("GUI.gui-6.items"));
        items.addAll(shopConfig.getStringList("GUI.gui-7.items"));
        items.addAll(shopConfig.getStringList("GUI.gui-8.items"));

        /**/

        /*
            所有特殊物品lore startswith处理类型

            §e§m§o§l§c§o§l§o§r 彩色羊毛

            §a§r§m§o§r§0§1 链套l1
            §a§r§m§o§r§0§2 铁套l2
            §a§r§m§o§r§0§3 钻套l3

            §o§o§l§d 剪刀
            §o§o§l§c§a§e 稿子
            §o§o§l§a§e 斧头

        */

        // 超级特殊:工具
        // 谁会帮我优化一下代码qwq
        if (
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0101§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0201§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0202§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0203§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0204§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0301§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0302§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0303§8) ") ||
                gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0304§8) ")

        ) { // 添加 §r§o§o§1§m§r§8 防止用户故意设置同样的编号导致冲突而出错!!

            String buy_type = "0000";

            ItemStack cost_type = new ItemStack(Material.AIR, Integer.MAX_VALUE);
            int cost_amount = 0;
            int cost_xplevel = 0;
            String buy_item_str = "null";

            int level_buy = -1;
            int level_player = 0;
//            int level_max = -2; 没啥实际意义 :(

            if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0101§8) ")) {
                buy_type = "0101";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.shears.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.shears.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.shears.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.shears.name");
                level_buy = 1;
                level_player = ToolItemsManager.players_toolLevel_剪刀.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0201§8) ")) {
                buy_type = "0201";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.pickaxe.L1.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.pickaxe.L1.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.pickaxe.L1.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.pickaxe.name");
                level_buy = 1;
                level_player = ToolItemsManager.players_toolLevel_稿子.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0202§8) ")) {
                buy_type = "0202";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.pickaxe.L2.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.pickaxe.L2.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.pickaxe.L2.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.pickaxe.name");
                level_buy = 2;
                level_player = ToolItemsManager.players_toolLevel_稿子.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0203§8) ")) {
                buy_type = "0203";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.pickaxe.L3.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.pickaxe.L3.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.pickaxe.L3.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.pickaxe.name");
                level_buy = 3;
                level_player = ToolItemsManager.players_toolLevel_稿子.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0204§8) ")) {
                buy_type = "0204";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.pickaxe.L4.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.pickaxe.L4.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.pickaxe.L4.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.pickaxe.name");
                level_buy = 4;
                level_player = ToolItemsManager.players_toolLevel_稿子.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0301§8) ")) {
                buy_type = "0301";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.axe.L1.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.axe.L1.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.axe.L1.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.axe.name");
                level_buy = 1;
                level_player = ToolItemsManager.players_toolLevel_斧头.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0302§8) ")) {
                buy_type = "0302";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.axe.L1.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.axe.L2.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.axe.L2.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.axe.name");
                level_buy = 2;
                level_player = ToolItemsManager.players_toolLevel_斧头.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0303§8) ")) {
                buy_type = "0303";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.axe.L1.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.axe.L3.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.axe.L3.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.axe.name");
                level_buy = 3;
                level_player = ToolItemsManager.players_toolLevel_斧头.get(p);
            } else if (gui_item.getItemMeta().getDisplayName().endsWith("#§r§o§o§1§m§r§8tools_0304§8) ")) {
                buy_type = "0304";
                cost_type = new ItemStack(Material.valueOf(shopConfig.getString("Tool-Items.axe.L1.cost.type")));
                cost_amount = shopConfig.getInt("Tool-Items.axe.L4.cost.amount");
                cost_xplevel = shopConfig.getInt("Tool-Items.axe.L4.cost.xp_level");
                buy_item_str = ConfigUtils.getString(shopConfig, "Tool-Items.axe.name");
                level_buy = 4;
                level_player = ToolItemsManager.players_toolLevel_斧头.get(p);
            } else {
                Bukkit.getLogger().warning("BedwarsError!! at ItemShop_manager.java:tools_**** tools_buy if_else=else ");
                return;
            }

            boolean isBuyed = false;

            if (!(level_player >= level_buy)) {
                if (ShopEnoughUtils.isEnough(p, cost_type, cost_amount, cost_xplevel)) {
                    isBuyed = true;
                    b_deduction(p, cost_type, cost_amount, cost_xplevel);
                    p.sendMessage(ConfigData.language_shop_buy_yes.replace("{item}", buy_item_str));
                    ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-yes");
                } else {
                    p.sendMessage(ConfigData.language_shop_buy_no);
                    ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-no");
                }
            } else {
                p.sendMessage(ConfigData.language_shop_buy_ed);
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-no");
            }

            if (isBuyed) {
                switch (buy_type) {
                    case "0101":
                        ToolItemsManager.players_toolLevel_剪刀.put(p, 1);
                        ToolItemsManager.buy_regive_01(p);
                        break;
                    case "0201":
                        ToolItemsManager.players_toolLevel_稿子.put(p, 1);
                        ToolItemsManager.buy_regive_02(p);
                        break;
                    case "0202":
                        ToolItemsManager.players_toolLevel_稿子.put(p, 2);
                        ToolItemsManager.buy_regive_02(p);
                        break;
                    case "0203":
                        ToolItemsManager.players_toolLevel_稿子.put(p, 3);
                        ToolItemsManager.buy_regive_02(p);
                        break;
                    case "0204":
                        ToolItemsManager.players_toolLevel_稿子.put(p, 4);
                        ToolItemsManager.buy_regive_02(p);
                        break;
                    case "0301":
                        ToolItemsManager.players_toolLevel_斧头.put(p, 1);
                        ToolItemsManager.buy_regive_03(p);
                        break;
                    case "0302":
                        ToolItemsManager.players_toolLevel_斧头.put(p, 2);
                        ToolItemsManager.buy_regive_03(p);
                        break;
                    case "0303":
                        ToolItemsManager.players_toolLevel_斧头.put(p, 3);
                        ToolItemsManager.buy_regive_03(p);
                        break;
                    case "0304":
                        ToolItemsManager.players_toolLevel_斧头.put(p, 4);
                        ToolItemsManager.buy_regive_03(p);
                        break;
                    default:
                        p.sendMessage(ChatColor.RED + "出现了严重性错误!! 请立即报告给开发者(错误信息见控制台, 请将此错误信息报告给开发者)");
                        Bukkit.getLogger().warning("BedwarsError!! at ItemShop_manager.java:tools_**** tools_buy(switch:case) = default ");
                        break;
                }
            }

            return;
        }

        /*

            非特殊性常规物品
            Shop.yml Items

         */
        for (String item_bh : items) {
            if (gui_item.getItemMeta().getDisplayName().endsWith("#" + item_bh + "§8) ")) {
                if (ShopEnoughUtils.isEnough(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"))) {
                    ItemStack buy_item = ConfigUtils.getItemStack(shopConfig, "Items." + item_bh + ".buy-item", false);
                    ItemMeta buy_meta = buy_item.getItemMeta();
                    boolean is普通处理 = true;
                    if (  buy_meta.hasLore() && (
                            buy_meta.getLore().get(0).startsWith("§e§a§m§c§o§l§o§r") || // 队伍颜色data
                            buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§1") || // 锁链套
                            buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§2") || // 铁套
                            buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§3") // 钻套
                    )) {
                        is普通处理 = false;
                    }


                    if (is普通处理) {
                        b_deduction(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"));
                        p.sendMessage(ConfigData.language_shop_buy_yes.replace("{item}", shopConfig.getString("Items." + item_bh + ".name")));
                        ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-yes");
                        p.getInventory().addItem(buy_item.clone());

                        if (buy_item.clone().getType().toString().endsWith("_SWORD")) {
                            if (p.getInventory().contains(Material.WOOD_SWORD)) {
                                p.getInventory().remove(Material.WOOD_SWORD);
                            }
                        }

                    } else {
                        if (buy_meta.getLore().get(0).startsWith("§e§a§m§c§o§l§o§r")) {
                            /* WOOL Item color data */
                            switch (TeamManager.player_teams.get(p)) {
                                case "RED":
                                    buy_item.setDurability((short) 14);
                                    break;
                                case "BLUE":
                                    buy_item.setDurability((short) 11);
                                    break;
                                case "GREEN":
                                    buy_item.setDurability((short) 5);
                                    break;
                                case "YELLOW":
                                    buy_item.setDurability((short) 4);
                                    break;
                                case "PINK":
                                    buy_item.setDurability((short) 6);
                                    break;
                                case "AQUA":
                                    buy_item.setDurability((short) 9);
                                    break;
                                case "GRAY":
                                    buy_item.setDurability((short) 7);
                                    break;
                                case "WHITE":
                                    buy_item.setDurability((short) 0);
                                    break;
                            }
                            buy_meta.getLore().remove(0);
                            buy_item.setItemMeta(buy_meta);

                            b_deduction(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"));
                            p.sendMessage(ConfigData.language_shop_buy_yes.replace("{item}", shopConfig.getString("Items." + item_bh + ".name")));
                            ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-yes");
                            p.getInventory().addItem(buy_item.clone());
                            /**/
                        } else if (buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§1")) {
                            if (PlayerArmorManager.get(p) < 1) {
                                PlayerArmorManager.set(p, 1);
                                GameTools.refresh_PlayerArmorInv(p);

                                b_deduction(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"));
                                p.sendMessage(ConfigData.language_shop_buy_yes.replace("{item}", shopConfig.getString("Items." + item_bh + ".name")));
                                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-yes");
                            } else {
                                p.sendMessage(ConfigData.language_shop_buy_ed);
                                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-no");
                            }
                        } else if (buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§2")) {
                            if (PlayerArmorManager.get(p) < 2) {
                                PlayerArmorManager.set(p, 2);
                                GameTools.refresh_PlayerArmorInv(p);

                                b_deduction(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"));
                                p.sendMessage(ConfigData.language_shop_buy_yes.replace("{item}", shopConfig.getString("Items." + item_bh + ".name")));
                                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-yes");
                            } else {
                                p.sendMessage(ConfigData.language_shop_buy_ed);
                                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-no");
                            }
                        } else if (buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§3")) {
                            if (PlayerArmorManager.get(p) < 3) {
                                PlayerArmorManager.set(p, 3);
                                GameTools.refresh_PlayerArmorInv(p);

                                b_deduction(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"));
                                p.sendMessage(ConfigData.language_shop_buy_yes.replace("{item}", shopConfig.getString("Items." + item_bh + ".name")));
                                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-yes");

                            } else {
                                p.sendMessage(ConfigData.language_shop_buy_ed);
                                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-no");
                            }
                        }
                    }

                } else {
                    p.sendMessage(ConfigData.language_shop_buy_no);
                    ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-no");
                }
            }
//            } else {
//                p.sendMessage("12123123");
//            }
        }

    }

    public static void b_deduction(Player p, ItemStack type, int amount, int xp_level) {
//        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
//            PlayerInventory inventory = p.getInventory();
//            int remaining = amount;
//            for (ItemStack item : inventory.getContents()) {
//                if (item != null && item.getType() == type.getType()) {
//                    int toRemove = Math.min(item.getAmount(), remaining);
//                    item.setAmount(item.getAmount() - toRemove);
//                    remaining -= toRemove;
//                    if (remaining <= 0) break;
//                }
//            }
//        }
        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
            PlayerInventory inventory = p.getInventory();
            int remaining = amount;
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventory.getItem(i);
                if (item != null && item.getType() == type.getType()) {
                    int toRemove = Math.min(item.getAmount(), remaining);
                    item.setAmount(item.getAmount() - toRemove);
                    remaining -= toRemove;
                    if (item.getAmount() <= 0) {
                        inventory.setItem(i, null); // 如果物品数量为0，移除该物品
                    }
                    if (remaining <= 0) break;
                }
            }
        }
        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
            p.setLevel(p.getLevel() - xp_level);
        }
    }

//    public static void b_deduction(Player p, ItemStack type, int amount, int xp_level) {
//        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
//           //  p.getInventory().addItem(new ItemStack(type.getType(), -amount));
//            PlayerInventory inventory = p.getInventory();
//            inventory.addItem(new ItemStack(type.getType(),-amount));
//        }
//        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
//            p.setLevel(p.getLevel() - xp_level);
//        }
//    }


}
