package cn.comicalpixel.creeperstarbedwars.Shop.Item;

import cn.comicalpixel.creeperstarbedwars.Config.ShopConfig;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemShop_Listener implements Listener {

    // 打开商店的监听写在了NPC那里

    public static ShopConfig shopConfig = CreeperStarBedwars.getPlugin().getShopConfig();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getClickedInventory().getType() != InventoryType.CHEST) {
            return;
        }

        // 选择卡图标
        if (       e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§0")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§a")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§2")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§3")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§4")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§5")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§6")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§7")
                || e.getClickedInventory().getTitle().endsWith("§2§e§b§k§a§5§e§8")) {

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
        }

        /**/

        List<Integer> buy_solts = new ArrayList<>();
        for (int i : new int[]{20,21,22,23,24,25,26  ,29,30,31,32,33,34,35   ,38,39,40,41,42,43,44 }) {
            buy_solts.add(i-1);
        }
        /* */

        if (buy_solts.contains(e.getSlot())) {
            if (e.getAction() == InventoryAction.PICKUP_ALL || e.getAction() == InventoryAction.PICKUP_HALF) {
                ItemShop_Manager.buy(((Player) e.getWhoClicked()).getPlayer(), e.getCurrentItem());
            }
        }

    }



}
