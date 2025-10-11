package cn.comicalpixel.creeperstarbedwars.Items.FastResChest;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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

        if (e.getAction() != Action.LEFT_CLICK_BLOCK) return;

        if (e.getItem() == null || e.getItem().getType() == Material.AIR) return;

        if (CreeperStarBedwars.getPlugin().getConfig().getBoolean("rapid-resource-placement.whitelist")) {
            if (!CreeperStarBedwars.getPlugin().getConfig().getStringList("rapid-resource-placement.items-whitelist").contains(e.getItem().getType().toString())) {
                return;
            }
        }

        if (e.getItem().getType().toString().endsWith("_SWORD")) {
            return;
        }

        Player p = e.getPlayer();

        if (e.getItem().getItemMeta().hasLore()) {
            if (e.getItem().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Fixed set items") ||
                    e.getItem().getType().toString().endsWith("_PICKAXE") || e.getItem().getType().toString().endsWith("_AXE") ||
                    e.getItem().getType() == Material.SHEARS) {
                return;
            }
        }

        /**/

        if (e.getClickedBlock().getType() == Material.CHEST && CreeperStarBedwars.getPlugin().getConfig().getBoolean("rapid-resource-placement.chest")
        && e.getClickedBlock().getLocation().distance(TeamSpawn.getLocation(p, TeamManager.player_teams.get(p))) <= GameData_cfg.teamChest_radius) {
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
                    // 先计算箱子剩余容量
                    int remainingSpace = calculateRemainingSpace(inventory, itemInHand);
                    int amountToAdd = Math.min(itemInHand.getAmount(), remainingSpace);

                    if (amountToAdd > 0) {
                        // 创建要放入的物品堆
                        org.bukkit.inventory.ItemStack toAdd = itemInHand.clone();
                        toAdd.setAmount(amountToAdd);

                        // 尝试放入物品
                        java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> remaining = inventory.addItem(toAdd);

                        // 计算实际放入的数量
                        int placedAmount = amountToAdd - (remaining.isEmpty() ? 0 : remaining.get(0).getAmount());

                        if (placedAmount > 0) {
                            if (e.getItem().getAmount() == placedAmount) {
                                p.getInventory().setItemInHand(null);
                            } else {
                                e.getItem().setAmount(e.getItem().getAmount() - placedAmount);
                            }
                            e.setCancelled(true);
//                            p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 10, 1);
                            // Sound
                            ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.chest-fast-placement-team");
                            // message
                            String type_name = null;
                            if (e.getItem().getItemMeta() != null && e.getItem().getItemMeta().hasDisplayName()) {
                                type_name = e.getItem().getItemMeta().getDisplayName();
                            } else {
                                type_name = e.getItem().getType().toString();
                            }
                            p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.chest-fast-placement-team-message").replace("{type}", type_name).replace("{amount}", e.getItem().getAmount()+""));
                        }
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
                    // 先计算箱子剩余容量
                    int remainingSpace = calculateRemainingSpace(enderInventory, itemInHand);
                    int amountToAdd = Math.min(itemInHand.getAmount(), remainingSpace);

                    if (amountToAdd > 0) {
                        // 创建要放入的物品堆
                        org.bukkit.inventory.ItemStack toAdd = itemInHand.clone();
                        toAdd.setAmount(amountToAdd);

                        // 尝试放入物品
                        java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> remaining = enderInventory.addItem(toAdd);

                        // 计算实际放入的数量
                        int placedAmount = amountToAdd - (remaining.isEmpty() ? 0 : remaining.get(0).getAmount());

                        if (placedAmount > 0) {
                            if (e.getItem().getAmount() == placedAmount) {
                                p.getInventory().setItemInHand(null);
                            } else {
                                e.getItem().setAmount(e.getItem().getAmount() - placedAmount);
                            }
                            e.setCancelled(true);
//                            p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 10, 1);
                            // Sound
                            ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.chest-fast-placement-ender");
                            // message
                            String type_name = null;
                            if (e.getItem().getItemMeta() != null && e.getItem().getItemMeta().hasDisplayName()) {
                                type_name = e.getItem().getItemMeta().getDisplayName();
                            } else {
                                type_name = e.getItem().getType().toString();
                            }
                            p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.chest-fast-placement-ender-message").replace("{type}", type_name).replace("{amount}", e.getItem().getAmount()+""));
                        }
                    }
                }
            }
        }


        /**/

    }

//    if (hasSameType || enderInventory.firstEmpty() != -1) {
//        java.util.HashMap<Integer, org.bukkit.inventory.ItemStack> remaining = enderInventory.addItem(itemInHand);
//
//        int placedAmount = itemInHand.getAmount() - (remaining.isEmpty() ? 0 : remaining.get(0).getAmount());
//
//        if (placedAmount > 0) {
//            if (e.getItem().getAmount() == placedAmount) {
//                p.getInventory().setItemInHand(null);
//            } else {
//                e.getItem().setAmount(e.getItem().getAmount() - placedAmount);
//            }
//            e.setCancelled(true);
//            p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 10, 1);
//        }
//    }

    public boolean isContainerFull(org.bukkit.inventory.Inventory inventory) {
        for (org.bukkit.inventory.ItemStack item : inventory.getContents()) {
            if (item == null || item.getAmount() < item.getMaxStackSize()) {
                return false;
            }
        }
        return true;
    }


    private int calculateRemainingSpace(org.bukkit.inventory.Inventory inventory, org.bukkit.inventory.ItemStack item) {
        int remainingSpace = 0;

        // 计算相同类型物品的剩余空间
        for (org.bukkit.inventory.ItemStack stack : inventory.getContents()) {
            if (stack != null && stack.isSimilar(item)) {
                remainingSpace += (stack.getMaxStackSize() - stack.getAmount());
            }
        }

        // 计算空位的剩余空间
        int emptySlots = 0;
        for (org.bukkit.inventory.ItemStack stack : inventory.getContents()) {
            if (stack == null) {
                emptySlots++;
            }
        }
        remainingSpace += emptySlots * item.getMaxStackSize();

        return remainingSpace;
    }


}
