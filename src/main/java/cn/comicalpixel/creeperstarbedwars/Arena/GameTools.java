package cn.comicalpixel.creeperstarbedwars.Arena;

import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameTools {

    // 记录玩家的护甲模式 0:默认皮革 1:锁链 2:铁 3:钻石
    public static HashMap<Player, Integer> playerChestPlate = new HashMap<>();

    public static void InitializationInventory(Player p) {

        p.getInventory().clear();

        ItemStack sword_item = new ItemStack(Material.WOOD_SWORD);
        ItemMeta sword_meta = sword_item.getItemMeta();
        List<String> sword_lore = new ArrayList<>();
        sword_lore.add(" ");
        sword_lore.add(ChatColor.DARK_GRAY + "Fixed set items");
        sword_meta.setLore(sword_lore);
        sword_meta.spigot().setUnbreakable(true);
        sword_item.setItemMeta(sword_meta);
        p.getInventory().setItem(0, sword_item);


        if (!playerChestPlate.containsKey(p)) {
            playerChestPlate.put(p, 0);
        }

        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);

        if (TeamManager.player_teams.get(p) != null && TeamManager.teams.contains(TeamManager.player_teams.get(p))) {

            Color color = Color.fromRGB(255, 255, 255); // 冗余但是初始化:)

            ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
            ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta meta = (LeatherArmorMeta) new ItemStack(Material.LEATHER_HELMET).getItemMeta();
            meta.spigot().setUnbreakable(true);

            switch (TeamManager.player_teams.get(p)) {
                case "RED":
                    color = Color.RED;
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
                case "BLUE":
                    color = Color.BLUE;
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
                case "YELLOW":
                    color = Color.YELLOW;
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
                case "GREEN":
                    color = Color.LIME;
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
                case "PINK":
                    color = Color.fromRGB(255, 86, 255);
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
                case "AQUA":
                    color = Color.AQUA;
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
                case "GRAY":
                    color = Color.GRAY;
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
                case "WHITE":
                    color = Color.WHITE;
                    meta.setColor(color);
                    helmet.setItemMeta(meta);
                    chestplate.setItemMeta(meta);
                    p.getInventory().setHelmet(helmet);
                    p.getInventory().setChestplate(chestplate);
                    if (playerChestPlate.get(p) == 0) {
                        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                        leggings.setItemMeta(meta);
                        boots.setItemMeta(meta);
                        p.getInventory().setLeggings(leggings);
                        p.getInventory().setBoots(boots);
                    }
                    break;
            }


            if (playerChestPlate.get(p) == 1) {
                ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
                ItemMeta leggingMeta = leggings.getItemMeta();
                ItemMeta bootMeta = boots.getItemMeta();
                leggingMeta.spigot().setUnbreakable(true);
                bootMeta.spigot().setUnbreakable(true);
                leggings.setItemMeta(leggingMeta);
                boots.setItemMeta(bootMeta);
                p.getInventory().setHelmet(helmet);
                p.getInventory().setChestplate(chestplate);
            }
            if (playerChestPlate.get(p) == 2) {
                ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
                ItemStack boots = new ItemStack(Material.IRON_BOOTS);
                ItemMeta leggingMeta = leggings.getItemMeta();
                ItemMeta bootMeta = boots.getItemMeta();
                leggingMeta.spigot().setUnbreakable(true);
                bootMeta.spigot().setUnbreakable(true);
                leggings.setItemMeta(leggingMeta);
                boots.setItemMeta(bootMeta);
                p.getInventory().setHelmet(helmet);
                p.getInventory().setChestplate(chestplate);
            }
            if (playerChestPlate.get(p) == 3) {
                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
                ItemMeta leggingMeta = leggings.getItemMeta();
                ItemMeta bootMeta = boots.getItemMeta();
                leggingMeta.spigot().setUnbreakable(true);
                bootMeta.spigot().setUnbreakable(true);
                leggings.setItemMeta(leggingMeta);
                boots.setItemMeta(bootMeta);
                p.getInventory().setHelmet(helmet);
                p.getInventory().setChestplate(chestplate);
            }


        }

    }

}
