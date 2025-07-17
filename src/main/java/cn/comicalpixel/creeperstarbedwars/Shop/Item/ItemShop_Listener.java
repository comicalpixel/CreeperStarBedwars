package cn.comicalpixel.creeperstarbedwars.Shop.Item;

import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Shop.Item.QuickShop.QuickShop_Add_GUI;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.mongodb.type.ShopStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemShop_Listener implements Listener {

    // 打开商店的监听写在了NPC那里

    public static ShopConfig shopConfig = CreeperStarBedwars.getPlugin().getShopConfig();
    public static Random random = new Random();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }

        // 选择卡图标
        if (       e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§0")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§a")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§2")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§3")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§4")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§5")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§6")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§7")
                || e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§8")) {

            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();

            /**/
            if (e.getSlot() >= 0 && e.getSlot() < 9) {
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.shop-switch");
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§0")) {
                    ItemShop_GUI.gui_op0(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§a")) {
                    ItemShop_GUI.gui_op1(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§2")) {
                    ItemShop_GUI.gui_op2(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§3")) {
                    ItemShop_GUI.gui_op3(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§4")) {
                    ItemShop_GUI.gui_op4(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§5")) {
                    ItemShop_GUI.gui_op5(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§6")) {
                    ItemShop_GUI.gui_op6(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§7")) {
                    ItemShop_GUI.gui_op7(p);
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().endsWith("§2§e§b§8§a§5§e§8")) {
                    ItemShop_GUI.gui_op8(p);
                }
            }





            /**/

            List<Integer> buy_solts = new ArrayList<>();
            for (int i : new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 }) {
                buy_solts.add(i-1);
            }
            /* */

            if (buy_solts.contains(e.getSlot())) {

                /* 购买部分 */
                if (e.getAction() == InventoryAction.PICKUP_ALL || e.getAction() == InventoryAction.PICKUP_HALF) {
                    ItemShop_Manager.buy(((Player) e.getWhoClicked()).getPlayer(), e.getCurrentItem());

                    /* 刷新GUI */
                    /* 默认仅工具100%刷新 */
                    boolean refresh_gui = false;
                    if (random.nextInt(3) == 0) {
                        refresh_gui = true;
                    } else {
                        if (!e.getCurrentItem().getType().toString().endsWith("SHEARS") &&
                                !e.getCurrentItem().getType().toString().endsWith("_AXE") &&
                                !e.getCurrentItem().getType().toString().endsWith("_PICKAXE")) {
                            refresh_gui = true;
                        }
                    }
                    if (refresh_gui) {
                        if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§0")) {
                            ItemShop_GUI.gui_op0((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§a")) {
                            ItemShop_GUI.gui_op1((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§2")) {
                            ItemShop_GUI.gui_op2((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§3")) {
                            ItemShop_GUI.gui_op3((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§4")) {
                            ItemShop_GUI.gui_op4((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§5")) {
                            ItemShop_GUI.gui_op5((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§6")) {
                            ItemShop_GUI.gui_op6((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§7")) {
                            ItemShop_GUI.gui_op7((Player) e.getWhoClicked());
                        } else if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§8")) {
                            ItemShop_GUI.gui_op8((Player) e.getWhoClicked());
                        }
                    }

                    /**/

                }
                /**/


                /* 快捷购买部分 */
                if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                    if (e.getInventory().getTitle().endsWith("§2§e§b§k§a§5§e§0")) {

                        HashMap<Integer, Integer> quick_solts = new HashMap<>();
                        quick_solts.put(20, 1);
                        quick_solts.put(21, 2);
                        quick_solts.put(22, 3);
                        quick_solts.put(23, 4);
                        quick_solts.put(24, 5);
                        quick_solts.put(25, 6);
                        quick_solts.put(26, 7);

                        quick_solts.put(29, 8);
                        quick_solts.put(30, 9);
                        quick_solts.put(31, 10);
                        quick_solts.put(32, 11);
                        quick_solts.put(33, 12);
                        quick_solts.put(34, 13);
                        quick_solts.put(35, 14);

                        quick_solts.put(38, 15);
                        quick_solts.put(39, 16);
                        quick_solts.put(40, 17);
                        quick_solts.put(41, 18);
                        quick_solts.put(42, 19);
                        quick_solts.put(43, 20);
                        quick_solts.put(44, 21);

                        if (quick_solts.containsKey(e.getRawSlot()+1)) {
                            if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
                                ShopStats shopStats = CreeperStarBedwars.getPlugin().getMongoDBManager().shopStats;
                                shopStats.setSlot(p.getUniqueId(), (quick_solts.get(e.getRawSlot()+1)), "none");
                            } else {
                                CreeperStarBedwars.getInstance().getShopDataConfig().set(p.getName()+".solt"+(quick_solts.get(e.getRawSlot()+1)), "none");
                                CreeperStarBedwars.getInstance().getShopDataConfig().save();
                            }
                        }
                        ItemShop_GUI.gui_op0(p);

                    } else {
                        QuickShop_Add_GUI.open(p, e.getCurrentItem());
                    }
                }
                /**/

            }



        }

    }



}
