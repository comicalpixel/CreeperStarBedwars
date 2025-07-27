package cn.comicalpixel.creeperstarbedwars.GameSetup;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.GameSetup.GUI.SetupGUI_Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetupListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (GameStats.get() != 0) {
            return;
        }
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        if (!p.isOp()) {
            e.getPlayer().kickPlayer(ChatColor.RED + "[SETUP] Setup is only allowed by admins! (You forgot to give yourself permissions?!) ");
            return;
        }

        ItemStack GUI_Item = new ItemStack(Material.WATCH);
        ItemMeta GUI_ItemMeta = GUI_Item.getItemMeta();
        GUI_ItemMeta.setDisplayName(ChatColor.RED + "[SETUP] Open Setup Menu §a§e§r§f§3§b");
        GUI_Item.setItemMeta(GUI_ItemMeta);
        GUI_Item.setItemMeta(GUI_ItemMeta);

        ItemStack AXE_item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta AXE_meta = AXE_item.getItemMeta();
        AXE_meta.setDisplayName(ChatColor.BLUE + "[SETUP] Regional Selection");
        List<String> AXE_Lore = new ArrayList<String>();
        AXE_Lore.add("§a§lLEFT-CLICK §eSelection Pos1  §r");
        AXE_Lore.add("§a§lRIGHT-CLICK §eSelection Pos2  §r");
        AXE_meta.setLore(AXE_Lore);
        AXE_item.setItemMeta(AXE_meta);

        ItemStack PICKAXE_item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta PICKAXE_meta = PICKAXE_item.getItemMeta();
        PICKAXE_meta.setDisplayName(ChatColor.BLUE + "[SETUP] Block Selection");
        List<String> PICKAXE_Lore = new ArrayList<>();
        PICKAXE_Lore.add("§a§lLEFT-CLICK §eSelection A Block §r");
        PICKAXE_meta.setLore(PICKAXE_Lore);
        PICKAXE_item.setItemMeta(PICKAXE_meta);

        p.getInventory().clear();
        p.getInventory().setItem(0, GUI_Item);
        p.getInventory().setItem(1, AXE_item);
        p.getInventory().setItem(2, PICKAXE_item);

        p.sendMessage("§aCreeper§eStar§fBedwars §7" + CreeperStarBedwars.Instance.getDescription().getVersion() + "\n \n");
        p.sendMessage("§c[SETUP] The current mode is SETUP. ");
        p.sendMessage("§c[SETUP] You Inventory is Cleared. ");
        p.sendMessage("§c[SETUP] Use \"/setup\" to view the instructions.");
        p.sendMessage("§c[SETUP] If you need to enable the game, please set 'setup' to false in the game.yml file.");
        p.sendMessage("§c[SETUP] Not configuring the game or misconfiguring it will cause the game to not work properly!");

        p.setGameMode(GameMode.CREATIVE);
        p.setAllowFlight(true);
        p.setFlying(true);

    }

    public static Location pos1 = null;
    public static Location pos2 = null;
    public static Location block = null;

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {
        if (GameStats.get() != 0) {
            return;
        }
        if (e.getItem() != null && e.getItem().getItemMeta() != null) {
            ItemStack item = e.getItem();
            if (item.getItemMeta().getDisplayName().endsWith("§a§e§r§f§3§b") && item.getType() == Material.WATCH) {
                SetupGUI_Main.open(e.getPlayer());
            }
            if (item.getItemMeta().getDisplayName().contains("[SETUP]") && item.getType() == Material.DIAMOND_PICKAXE) {
                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    e.setCancelled(true);
                    block = e.getClickedBlock().getLocation();
                    e.getPlayer().sendMessage("§a[SETUP] Selected A Block, §eLocation" + " x:" + block.getBlockX() + " y:" + block.getBlockY() + " z:" + block.getBlockZ());
                    sendBlockRender(e.getPlayer(), block.getBlock());
                }
            }
            if (item.getItemMeta().getDisplayName().contains("[SETUP]") && item.getType() == Material.DIAMOND_AXE) {
                if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    e.setCancelled(true);
                    pos1 = e.getClickedBlock().getLocation();
                    e.getPlayer().sendMessage("§a[SETUP] Selected Pos1,§e" + "Location x:" + block.getX() + " y:" + block.getY() + " z:" + block.getZ());
                    sendBlockRender(e.getPlayer(), pos1.getBlock());
                }
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    e.setCancelled(true);
                    pos2 = e.getClickedBlock().getLocation();
                    e.getPlayer().sendMessage("§a[SETUP] Selected Pos2,§e" + "Location x:" + block.getX() + " y:" + block.getY() + " z:" + block.getZ());
                    sendBlockRender(e.getPlayer(), pos2.getBlock());
                }
            }
        }
    }

    public void sendBlockRender(Player p, Block block) {
        Material mat = block.getType();
        int i = block.getData();
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            p.sendBlockChange(block.getLocation(), Material.SEA_LANTERN, (byte) 0);
        },1);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            p.sendBlockChange(block.getLocation(), mat, (byte) i);
        },10);
    }


}
