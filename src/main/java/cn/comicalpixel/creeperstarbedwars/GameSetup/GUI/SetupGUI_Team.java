package cn.comicalpixel.creeperstarbedwars.GameSetup.GUI;

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetupGUI_Team implements Listener {

    public static void open(Player p) {

        p.playSound(p.getLocation(), Sound.CLICK, 10, 1f);

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, "§3Bedwars Setup Menu " + "§c§e§b§7");

        List<String> lore_enable = new ArrayList<String>();
        lore_enable.add("§eStat: §aenable ");
        List<String> lore_disable = new ArrayList<String>();
        lore_disable.add("§eStat: §cdisable ");

        List<String> teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");

        // dye shorts: 0:白(#) 1:橙 2:紫 3:蓝 4:黄(#) 5:绿(#) 6:粉(#) 7:灰(#) 8:灰 9:青(#) 10:紫 11:蓝(#) 12:? 13:绿 14:红(#) 15:BLACK 16+:错误

        ItemStack item_TeamRED = new ItemStack(Material.WOOL, 1, (short) 14);
        ItemMeta item_TeamREDMeta = item_TeamRED.getItemMeta();
        item_TeamREDMeta.setDisplayName("§cRED Team");
        if (teams.contains("RED")) {
            item_TeamREDMeta.setLore(lore_enable);
        } else {
            item_TeamREDMeta.setLore(lore_disable);
        }
        item_TeamRED.setItemMeta(item_TeamREDMeta);

        ItemStack item_TeamBLUE = new ItemStack(Material.WOOL, 1, (short) 11);
        ItemMeta item_TeamBLUEMeta = item_TeamBLUE.getItemMeta();
        item_TeamBLUEMeta.setDisplayName("§9BLUE Team");
        if (teams.contains("BLUE")) {
            item_TeamBLUEMeta.setLore(lore_enable);
        } else {
            item_TeamBLUEMeta.setLore(lore_disable);
        }
        item_TeamBLUE.setItemMeta(item_TeamBLUEMeta);

        ItemStack item_TeamGREEN = new ItemStack(Material.WOOL, 1, (short) 5);
        ItemMeta item_TeamGREENMeta = item_TeamGREEN.getItemMeta();
        item_TeamGREENMeta.setDisplayName("§aGREEN Team");
        if (teams.contains("GREEN")) {
            item_TeamGREENMeta.setLore(lore_enable);
        } else {
            item_TeamGREENMeta.setLore(lore_disable);
        }
        item_TeamGREEN.setItemMeta(item_TeamGREENMeta);

        ItemStack item_TeamYELLOW = new ItemStack(Material.WOOL, 1, (short) 4);
        ItemMeta item_TeamYELLOWMeta = item_TeamYELLOW.getItemMeta();
        item_TeamYELLOWMeta.setDisplayName("§eYELLOW Team");
        if (teams.contains("YELLOW")) {
            item_TeamYELLOWMeta.setLore(lore_enable);
        } else {
            item_TeamYELLOWMeta.setLore(lore_disable);
        }
        item_TeamYELLOW.setItemMeta(item_TeamYELLOWMeta);

        ItemStack item_TeamPINK = new ItemStack(Material.WOOL, 1, (short) 6);
        ItemMeta item_TeamPINKMeta = item_TeamPINK.getItemMeta();
        item_TeamPINKMeta.setDisplayName("§dPINK Team");
        if (teams.contains("PINK")) {
            item_TeamPINKMeta.setLore(lore_enable);
        } else {
            item_TeamPINKMeta.setLore(lore_disable);
        }
        item_TeamPINK.setItemMeta(item_TeamPINKMeta);

        ItemStack item_TeamAQUA = new ItemStack(Material.WOOL, 1, (short) 9);
        ItemMeta item_TeamAQUAMeta = item_TeamAQUA.getItemMeta();
        item_TeamAQUAMeta.setDisplayName("§3AQUA Team");
        if (teams.contains("AQUA")) {
            item_TeamAQUAMeta.setLore(lore_enable);
        } else {
            item_TeamAQUAMeta.setLore(lore_disable);
        }
        item_TeamAQUA.setItemMeta(item_TeamAQUAMeta);

        ItemStack item_TeamGRAY = new ItemStack(Material.WOOL, 1, (short) 7);
        ItemMeta item_TeamGRAYMeta = item_TeamGRAY.getItemMeta();
        item_TeamGRAYMeta.setDisplayName("§8GRAY Team");
        if (teams.contains("GRAY")) {
            item_TeamGRAYMeta.setLore(lore_enable);
        } else {
            item_TeamGRAYMeta.setLore(lore_disable);
        }
        item_TeamGRAY.setItemMeta(item_TeamGRAYMeta);

        ItemStack item_TeamWHITE = new ItemStack(Material.WOOL, 1, (short) 0);
        ItemMeta item_TeamWHITEMeta = item_TeamWHITE.getItemMeta();
        item_TeamWHITEMeta.setDisplayName("§fWHITE Team");
        if (teams.contains("WHITE")) {
            item_TeamWHITEMeta.setLore(lore_enable);
        } else {
            item_TeamWHITEMeta.setLore(lore_disable);
        }
        item_TeamWHITE.setItemMeta(item_TeamWHITEMeta);

        gui.setItem(10 -1, item_TeamRED);
        gui.setItem(11 -1, item_TeamBLUE);
        gui.setItem(12 -1, item_TeamGREEN);
        gui.setItem(13 -1, item_TeamYELLOW);
        gui.setItem(15 -1, item_TeamPINK);
        gui.setItem(16 -1, item_TeamAQUA);
        gui.setItem(17 -1, item_TeamGRAY);
        gui.setItem(18 -1, item_TeamWHITE);

        ItemStack item_setTeamName = new ItemStack(Material.NAME_TAG);
        ItemMeta meta_setTeamName = item_setTeamName.getItemMeta();
        meta_setTeamName.setDisplayName("§aSet Team Name");
        List<String> lore_setTeamName = new ArrayList<>();
        lore_setTeamName.add("§aClick to Set... ");
        meta_setTeamName.setLore(lore_setTeamName);
        item_setTeamName.setItemMeta(meta_setTeamName);
        gui.setItem(32-1, item_setTeamName);

        p.openInventory(gui);

    }


    @EventHandler
    public void InventoryClick_MainSetupMenu(InventoryClickEvent e) {

        if (!e.getInventory().getTitle().endsWith("§c§e§b§7")) {
            return;
        }
        e.setCancelled(true);
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getRawSlot() >= (4 * 9) - 1) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }

        List<String> teams = CreeperStarBedwars.getPlugin().getGameConfig().getStringList("teams");

        /**/
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        if (item.getType() == Material.WOOL) {
            if (meta.getDisplayName().contains("RED Team")) {
                if (teams.contains("RED")) {
                    teams.remove("RED");
                } else {
                    teams.add("RED");
                }
            } else if (meta.getDisplayName().contains("BLUE Team")) {
                if (teams.contains("BLUE")) {
                    teams.remove("BLUE");
                } else {
                    teams.add("BLUE");
                }
            } else if (meta.getDisplayName().contains("GREEN Team")) {
                if (teams.contains("GREEN")) {
                    teams.remove("GREEN");
                } else {
                    teams.add("GREEN");
                }
            } else if (meta.getDisplayName().contains("YELLOW Team")) {
                if (teams.contains("YELLOW")) {
                    teams.remove("YELLOW");
                } else {
                    teams.add("YELLOW");
                }
            } else if (meta.getDisplayName().contains("PINK Team")) {
                if (teams.contains("PINK")) {
                    teams.remove("PINK");
                } else {
                    teams.add("PINK");
                }
            } else if (meta.getDisplayName().contains("AQUA Team")) {
                if (teams.contains("AQUA")) {
                    teams.remove("AQUA");
                } else {
                    teams.add("AQUA");
                }
            } else if (meta.getDisplayName().contains("GRAY Team")) {
                if (teams.contains("GRAY")) {
                    teams.remove("GRAY");
                } else {
                    teams.add("GRAY");
                }
            } else if (meta.getDisplayName().contains("WHITE Team")) {
                if (teams.contains("WHITE")) {
                    teams.remove("WHITE");
                } else {
                    teams.add("WHITE");
                }
            }

            CreeperStarBedwars.getPlugin().getGameConfig().set("teams", teams);
            CreeperStarBedwars.getPlugin().getGameConfig().save();
            open(p);
        }

        if (item.getType() == Material.NAME_TAG && meta.getDisplayName().equalsIgnoreCase("§aSet Team Name")) {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 10f, 1);
            p.sendMessage("§a[SETUP] Use: §e/setup TeamName <Team> <TeamName>");
        }

    }


}
