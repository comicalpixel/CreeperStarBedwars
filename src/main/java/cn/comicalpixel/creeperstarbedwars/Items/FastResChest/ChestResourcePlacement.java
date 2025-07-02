package cn.comicalpixel.creeperstarbedwars.Items.FastResChest;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestResourcePlacement implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR) return;

        if (!CreeperStarBedwars.getPlugin().getConfig().getStringList("rapid-resource-placement.items-list").contains(e.getItem().getType().toString())) {
            return;
        }

        Player p = e.getPlayer();



        /**/

        if (e.getClickedBlock().getType() == Material.CHEST && CreeperStarBedwars.getPlugin().getConfig().getBoolean("rapid-resource-placement.chest")) {
            org.bukkit.block.ContainerBlock container = (org.bukkit.block.ContainerBlock) e.getClickedBlock().getState();
            org.bukkit.inventory.Inventory inventory = container.getInventory();

            if (!isContainerFull(inventory)) {
                org.bukkit.inventory.ItemStack itemInHand = e.getItem().clone();

                boolean hasSameType = false;
                for (org.bukkit.inventory.ItemStack item : inventory.getContents()) {
                    if (item != null && item.isSimilar(itemInHand)) {
                        hasSameType = true;
                        break;
                    }
                }

                if (hasSameType || inventory.firstEmpty() != -1) {
                    java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> remaining = inventory.addItem(itemInHand);

                    int placedAmount = itemInHand.getAmount() - (remaining.isEmpty() ? 0 : remaining.get(0).getAmount());

                    if (placedAmount > 0) {
                        if (e.getItem().getAmount() == placedAmount) {
                            p.getInventory().setItemInHand(null);
                        } else {
                            e.getItem().setAmount(e.getItem().getAmount() - placedAmount);
                        }
                        e.setCancelled(true);
                        p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 10, 1);
                    }
                }
            }
        }


        //


        if (e.getClickedBlock().getType() == Material.ENDER_CHEST && CreeperStarBedwars.getPlugin().getConfig().getBoolean("rapid-resource-placement.ender-chest")) {
            org.bukkit.inventory.Inventory enderInventory = p.getEnderChest();

            if (!isContainerFull(enderInventory)) {
                org.bukkit.inventory.ItemStack itemInHand = e.getItem().clone();

                boolean hasSameType = false;
                for (org.bukkit.inventory.ItemStack item : enderInventory.getContents()) {
                    if (item != null && item.isSimilar(itemInHand)) {
                        hasSameType = true;
                        break;
                    }
                }

                if (hasSameType || enderInventory.firstEmpty() != -1) {
                    java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> remaining = enderInventory.addItem(itemInHand);

                    int placedAmount = itemInHand.getAmount() - (remaining.isEmpty() ? 0 : remaining.get(0).getAmount());

                    if (placedAmount > 0) {
                        if (e.getItem().getAmount() == placedAmount) {
                            p.getInventory().setItemInHand(null);
                        } else {
                            e.getItem().setAmount(e.getItem().getAmount() - placedAmount);
                        }
                        e.setCancelled(true);
                        p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 10, 1);
                    }
                }
            }
        }


        /**/

    }

    public boolean isContainerFull(org.bukkit.inventory.Inventory inventory) {
        for (org.bukkit.inventory.ItemStack item : inventory.getContents()) {
            if (item == null || item.getAmount() < item.getMaxStackSize()) {
                return false;
            }
        }
        return true;
    }


}
