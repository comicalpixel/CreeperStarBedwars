package cn.comicalpixel.creeperstarbedwars.GameSetup.GUI;

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetupGUI_Main implements Listener {

    public static void open(Player p) {

        p.playSound(p.getLocation(), Sound.CLICK, 10, 1f);

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, "§3Bedwars Setup Menu " + "§c§e§b§3");

        ItemStack item_mapName  = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta meta_mapName = item_mapName.getItemMeta();
        meta_mapName.setDisplayName("§eMap Name §c§e§b§r§c§1");
        List<String> lore_mapName = new ArrayList<String>();
        lore_mapName.add("§bSelected: §a" + CreeperStarBedwars.getPlugin().getGameConfig().getString("map-name"));
        meta_mapName.setLore(lore_mapName);
        item_mapName.setItemMeta(meta_mapName);
        gui.setItem(11 -1, item_mapName);

        ItemStack item_Author = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta meta_Author = item_Author.getItemMeta();
        meta_Author.setDisplayName("§6Author §c§e§b§r§c§2");
        List<String> lore_Author = new ArrayList<>();
        lore_Author.add("§bSelected: §c" + CreeperStarBedwars.getPlugin().getGameConfig().getString("map-author"));
        meta_Author.setLore(lore_Author);
        item_Author.setItemMeta(meta_Author);
        gui.setItem(12 -1, item_Author);

        ItemStack item_setMinPlayers = new ItemStack(Material.WOOD_SWORD, 1);
        ItemMeta meta_setMinPlayers = item_setMinPlayers.getItemMeta();
        meta_setMinPlayers.setDisplayName("§6MinPlayers §c§e§b§r§c§3");
        List<String> lore_setMinPlayers = new ArrayList<>();
        lore_setMinPlayers.add("§f" + CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers") + "§b Players");
        lore_setMinPlayers.add(" ");
        lore_setMinPlayers.add("§eLeft-click Add! ");
        lore_setMinPlayers.add("§eRight-click to reduce! ");
        meta_setMinPlayers.setLore(lore_setMinPlayers);
        item_setMinPlayers.setItemMeta(meta_setMinPlayers);
        gui.setItem(13 -1, item_setMinPlayers);

        ItemStack item_setMaxPlayers = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta_setMaxPlayers = item_setMaxPlayers.getItemMeta();
        meta_setMaxPlayers.setDisplayName("§4Game Players §c§e§b§r§c§4");
        List<String> lore_setMaxPlayers = new ArrayList<>();
        lore_setMaxPlayers.add("§f" + CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers") + "§b Players");
        lore_setMaxPlayers.add(" ");
        lore_setMaxPlayers.add("§cIf the setting is wrong, an error will be reported!! ");
        lore_setMaxPlayers.add("§cPlease re-check if there is a mistake after setting up the team! ");
        lore_setMaxPlayers.add(" ");
        lore_setMaxPlayers.add("§eLeft-click Add! ");
        lore_setMaxPlayers.add("§eRight-click to reduce! ");
        meta_setMaxPlayers.setLore(lore_setMaxPlayers);
        item_setMaxPlayers.setItemMeta(meta_setMaxPlayers);
        gui.setItem(14 -1, item_setMaxPlayers);

        ItemStack item_setTeamPlayer = new ItemStack(Material.BOW, 1);
        ItemMeta meta_setTeamPlayer = item_setTeamPlayer.getItemMeta();
        meta_setTeamPlayer.setDisplayName("§2Team Players §c§e§b§r§c§5");
        List<String> lore_setTeamPlayer = new ArrayList<>();
        lore_setTeamPlayer.add("§f" + CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players") + "§b Players");
        lore_setTeamPlayer.add(" ");
        lore_setTeamPlayer.add("§eLeft-click Add! ");
        lore_setTeamPlayer.add("§eRight-click to reduce! ");
        meta_setTeamPlayer.setLore(lore_setTeamPlayer);
        item_setTeamPlayer.setItemMeta(meta_setTeamPlayer);
        gui.setItem(15 -1, item_setTeamPlayer);



        ItemStack item_setLobby = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta meta_setLobby = item_setLobby.getItemMeta();
        meta_setLobby.setDisplayName("§aSet Lobby §c§e§b§r§c§6");
        List<String> lore_setLobby = new ArrayList<>();
        lore_setLobby.add("§bSelected: §c");
        lore_setLobby.add("§c" + CreeperStarBedwars.getPlugin().getGameConfig().getString("lobby"));
        lore_setLobby.add(" ");
        lore_setLobby.add("§eClick to Select! ");
        meta_setLobby.setLore(lore_setLobby);
        item_setLobby.setItemMeta(meta_setLobby);
        gui.setItem(16 -1, item_setLobby);

        ItemStack item_setSpec = new ItemStack(Material.REDSTONE_LAMP_OFF);
        ItemMeta meta_setSpec = item_setSpec.getItemMeta();
        meta_setSpec.setDisplayName("§eSet Spectator Location §c§e§b§r§c§7");
        List<String> lore_setSpec = new ArrayList<>();
        lore_setSpec.add("§bSelected: §c");
        lore_setSpec.add("§c" + CreeperStarBedwars.getPlugin().getGameConfig().getString("spec"));
        lore_setSpec.add(" ");
        lore_setSpec.add("§eClick to Select! ");
        meta_setSpec.setLore(lore_setSpec);
        item_setSpec.setItemMeta(meta_setSpec);
        gui.setItem(17 -1, item_setSpec);

        ItemStack item_setCenter = new ItemStack(Material.COMPASS);
        ItemMeta meta_setCenter = item_setCenter.getItemMeta();
        meta_setCenter.setDisplayName("§6Set GameMap Center §c§e§b§r§c§8");
        List<String> lore_setCenter = new ArrayList<>();
        lore_setCenter.add("§bSelected: §c");
        lore_setCenter.add("§c" + CreeperStarBedwars.getPlugin().getGameConfig().getString("map-center"));
        lore_setCenter.add(" ");
        lore_setCenter.add("§eClick to Select! ");
        meta_setCenter.setLore(lore_setCenter);
        item_setCenter.setItemMeta(meta_setCenter);
        gui.setItem(20 -1, item_setCenter);

        ItemStack item_teamManager = new ItemStack(Material.WOOL, 1, (short) 8);
        ItemMeta meta_teamManager = item_teamManager.getItemMeta();
        meta_teamManager.setDisplayName("§cT§9e§ea§am §dMa§3na§8ge§fr §c§e§b§r§c§9");
        List<String> lore_teamManager = new ArrayList<>();
        lore_teamManager.add("§eClick to Open! ");
        meta_teamManager.setLore(lore_teamManager);
        item_teamManager.setItemMeta(meta_teamManager);
        gui.setItem(21 -1, item_teamManager);

        ItemStack item_teamBed = new ItemStack(Material.BED);
        ItemMeta meta_teamBed = item_teamBed.getItemMeta();
        meta_teamBed.setDisplayName("§cSet Team Bed_Block §c§e§b§r§c§m§1");
        List<String> lore_teamBed = new ArrayList<>();
        lore_teamBed.add("§9Tips: Please use the diamond_axe to set");
        lore_teamBed.add("§9the Bed_Blocks before setting it! ");
        lore_teamBed.add(" ");
        lore_teamBed.add("§eClick to Open! ");
        meta_teamBed.setLore(lore_teamBed);
        item_teamBed.setItemMeta(meta_teamBed);
        gui.setItem(22 -1, item_teamBed);

        ItemStack item_teamRes = new ItemStack(Material.IRON_BLOCK);
        ItemMeta meta_teamRes = item_teamRes.getItemMeta();
        meta_teamRes.setDisplayName("§6Set Team Generator §c§e§b§r§c§m§2");
        List<String> lore_teamRes = new ArrayList<>();
        lore_teamRes.add("§9Tips: Please use the diamond_pickaxe to set");
        lore_teamRes.add("§9a Block before setting it! ");
        lore_teamRes.add(" ");
        lore_teamRes.add("§eClick to Open! ");
        meta_teamRes.setLore(lore_teamRes);
        item_teamRes.setItemMeta(meta_teamRes);
        gui.setItem(23 -1, item_teamRes);

        ItemStack item_teamSpawn = new ItemStack(Material.BEACON);
        ItemMeta meta_teamSpawn = item_teamSpawn.getItemMeta();
        meta_teamSpawn.setDisplayName("§5Set Team Spawn §c§e§b§r§c§m§5");
        List<String> lore_teamSpawn = new ArrayList<>();
        lore_teamSpawn.add("§eClick to Open! ");
        meta_teamSpawn.setLore(lore_teamSpawn);
        item_teamSpawn.setItemMeta(meta_teamSpawn);
        gui.setItem(24 -1, item_teamSpawn);

        ItemStack item_arenaRes = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta_arenaRes = item_arenaRes.getItemMeta();
        meta_arenaRes.setDisplayName("§bSet Arena Generator §c§e§b§r§c§m§3");
        List<String> lore_arenaRes = new ArrayList<>();
        lore_arenaRes.add("§9Tips: Please use the diamond_pickaxe to set");
        lore_arenaRes.add("§9a Block before setting it! ");
        lore_arenaRes.add(" ");
        lore_arenaRes.add("§eClick to Open! ");
        meta_arenaRes.setLore(lore_arenaRes);
        item_arenaRes.setItemMeta(meta_arenaRes);
        gui.setItem(25 -1, item_arenaRes);

        ItemStack item_shop = new ItemStack(Material.FIREBALL);
        ItemMeta meta_shop = item_shop.getItemMeta();
        meta_shop.setDisplayName("§bAdd Game Shop §c§e§b§r§c§m§4");
        List<String> lore_shop = new ArrayList<>();
        lore_shop.add("§eClick to Open! ");
        meta_shop.setLore(lore_shop);
        item_shop.setItemMeta(meta_shop);
        gui.setItem(26 -1, item_shop);


        p.openInventory(gui);

    }

    @EventHandler
    public void InventoryClick_MainSetupMenu(InventoryClickEvent e) {

        if (!e.getInventory().getTitle().endsWith("§c§e§b§3")) {
            return;
        }
        e.setCancelled(true);
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getRawSlot() >= (4 * 9)-1) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        /**/
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        if (item.getType() == Material.NAME_TAG && meta.getDisplayName().endsWith("§c§e§b§r§c§1")) {
            p.sendMessage("§a[SETUP] Use: §e/setup setName <message>");
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 0.1f);
            p.closeInventory();
        }
        if (item.getType() == Material.SKULL_ITEM && meta.getDisplayName().endsWith("§c§e§b§r§c§2")) {
            p.sendMessage("§a[SETUP] Use: §e/setup setAuthor <author_Name>");
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 0.1f);
            p.closeInventory();
        }
        if (item.getType() == Material.WOOD_SWORD && meta.getDisplayName().endsWith("§c§e§b§r§c§3")) {
            if (e.getAction() == InventoryAction.PICKUP_ALL) {
                // 左键
                if ((CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers") +1) > CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers")) {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                } else {
                    // CreeperStarBedwars.getPlugin().getGameConfig().set("minplayers", CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers")+1);
                    p.performCommand("setup minplayers " + (CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers")+1));
                }
                open(p);
            }
            else if (e.getAction() == InventoryAction.PICKUP_HALF) {
                // 右键
                if (CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers") <= 2) {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                } else {
                    // CreeperStarBedwars.getPlugin().getGameConfig().set("minplayers", CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers")-1);
                    p.performCommand("setup minplayers " + (CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers")-1));
                }
                open(p);
            }
        }
        if (item.getType() == Material.IRON_SWORD && meta.getDisplayName().endsWith("§c§e§b§r§c§4")) {
            if (e.getAction() == InventoryAction.PICKUP_ALL) {
                // 左键
                // CreeperStarBedwars.getPlugin().getGameConfig().set("maxplayers", CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers")+1);
                p.performCommand("setup maxplayers " + (CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers")+1));
                open(p);
            }
            else if (e.getAction() == InventoryAction.PICKUP_HALF) {
                // 右键
                if (CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers") <= 2) {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                } else if ((CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers")-1) < CreeperStarBedwars.getPlugin().getGameConfig().getInt("minplayers")) {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                } else {
                    // CreeperStarBedwars.getPlugin().getGameConfig().set("maxplayers", CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers")-1);
                    p.performCommand("setup maxplayers " + (CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers")-1));
                }
                open(p);
            }
        }
        if (item.getType() == Material.BOW && meta.getDisplayName().endsWith("§c§e§b§r§c§5")) {
            if (e.getAction() == InventoryAction.PICKUP_ALL) {
                // 左键
                if (CreeperStarBedwars.getPlugin().getGameConfig().getInt("maxplayers") <= CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players")) {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                } else  {
                    // CreeperStarBedwars.getPlugin().getGameConfig().set("team-players", CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players")+1);
                    p.performCommand("setup teamplayers " + (CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players")+1));
                    open(p);
                }
            } else if (e.getAction() == InventoryAction.PICKUP_HALF) {
                // 右键
                if ((CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players")-1) <= 0) {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                } else  {
                    // CreeperStarBedwars.getPlugin().getGameConfig().set("team-players", CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players")-1);
                    p.performCommand("setup teamplayers " + (CreeperStarBedwars.getPlugin().getGameConfig().getInt("team-players")-1));
                    open(p);
                }
            }
        }


        if (item.getType() == Material.DIAMOND_BLOCK && meta.getDisplayName().endsWith("§c§e§b§r§c§6")) {
            p.performCommand("setup setlobby");
            p.closeInventory();
        }
        if (item.getType() == Material.REDSTONE_LAMP_OFF && meta.getDisplayName().endsWith("§c§e§b§r§c§7")) {
            p.performCommand("setup setspec");
            p.closeInventory();
        }
        if (item.getType() == Material.COMPASS && meta.getDisplayName().endsWith("§c§e§b§r§c§8")) {
            p.performCommand("setup setcenter");
            p.closeInventory();
        }
        if (item.getType() == Material.WOOL && meta.getDisplayName().endsWith("§c§e§b§r§c§9")) {
            SetupGUI_Team.open(p);
        }
        if (item.getType() == Material.BED && meta.getDisplayName().endsWith("§c§e§b§r§c§m§1")) {
            SetupGUI_TeamBed.open(p);
        }
        if (item.getType() == Material.IRON_BLOCK && meta.getDisplayName().endsWith("§c§e§b§r§c§m§2")) {
            SetupGUI_TeamGenerator.open(p);
        }
        if (item.getType() == Material.GOLD_BLOCK && meta.getDisplayName().endsWith("§c§e§b§r§c§m§3")) {
            SetupGUI_ArenaGenerator.open(p);
        }
        if (item.getType() == Material.FIREBALL && meta.getDisplayName().endsWith("§c§e§b§r§c§m§4")) {
            SetupGUI_Shop.open(p);
        }
        if (item.getType() == Material.BEACON && meta.getDisplayName().endsWith("§c§e§b§r§c§m§5")) {
            SetupGUI_TeamSpawn.open(p);
        }


    }

}
