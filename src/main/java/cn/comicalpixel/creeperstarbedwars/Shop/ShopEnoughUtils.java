package cn.comicalpixel.creeperstarbedwars.Shop;

import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ShopEnoughUtils {

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

}
