package cn.comicalpixel.creeperstarbedwars.Shop.Item;

import cn.comicalpixel.creeperstarbedwars.Arena.GameTools;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Shop.Item.PlayerArmor.PlayerArmorManager;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
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

        for (String item_bh : items) {
            if (gui_item.getItemMeta().getDisplayName().endsWith("#" + item_bh + "§8) ")) {
                if (isEnough(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"))) {
                    ItemStack buy_item = ConfigUtils.getItemStack(shopConfig, "Items." + item_bh + ".buy-item");
                    ItemMeta buy_meta = buy_item.getItemMeta();
                    boolean is普通处理 = true;
                    if (  buy_meta.hasLore() && (
                            buy_meta.getLore().get(0).startsWith("§e§a§m§c§o§l§o§r") || // 队伍颜色data
                            buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§1") || // 锁链套
                            buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§2") || // 铁套
                            buy_meta.getLore().get(0).startsWith("§a§r§m§o§r§0§3") || // 钻套
                            buy_meta.getLore().get(0).startsWith("§o§o§l§d") || // 剪刀
                            buy_meta.getLore().get(0).startsWith("§o§o§l§c§a§e§1") || // 稿子l1
                            buy_meta.getLore().get(0).startsWith("§o§o§l§c§a§e§2") || // 稿子l2
                            buy_meta.getLore().get(0).startsWith("§o§o§l§c§a§e§3") || // 稿子l3
                            buy_meta.getLore().get(0).startsWith("§o§o§l§c§a§e§4") || // 稿子l4
                            buy_meta.getLore().get(0).startsWith("§o§o§l§a§e§1") || // 斧头l1
                            buy_meta.getLore().get(0).startsWith("§o§o§l§a§e§2") || // 斧头l2
                            buy_meta.getLore().get(0).startsWith("§o§o§l§a§e§3") || // 斧头l3
                            buy_meta.getLore().get(0).startsWith("§o§o§l§a§e§4") // 斧头l4
                    )) {
                        is普通处理 = false;
                    }

                    if (is普通处理) {
                        b_deduction(p, new ItemStack(Material.valueOf(shopConfig.getString("Items." + item_bh + ".cost.type"))), shopConfig.getInt("Items." + item_bh + ".cost.amount"), shopConfig.getInt("Items." + item_bh + ".cost.xp_level"));
                        p.sendMessage(ConfigData.language_shop_buy_yes.replace("{item}", shopConfig.getString("Items." + item_bh + ".name")));
                        ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-buy-yes");
                        p.getInventory().addItem(buy_item.clone());
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

    public static boolean isEnough(Player p, ItemStack type, int cost, int xp_level) {

        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
            PlayerInventory inventory = p.getInventory();
            int i = 0;
            for (ItemStack item : inventory.getContents()) {
                if (item != null && item.getType() == type.getType()) {
                    i += item.getAmount();
                }
            }
            if (i >= cost) {
                return true;
            }
        }
        if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
            if (p.getLevel() >= xp_level) {
                return true;
            }
        }

        return false;
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
