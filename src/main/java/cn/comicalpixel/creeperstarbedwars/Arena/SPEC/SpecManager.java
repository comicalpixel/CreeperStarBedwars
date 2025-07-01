package cn.comicalpixel.creeperstarbedwars.Arena.SPEC;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpecManager {

    public static void setSpec(Player p) {
        GamePlayers.players.remove(p);
        GamePlayers.specs.add(p);
        TeamManager.player_teams.remove(p);
        PlayerUtils.clear_effects(p);
        PlayerUtils.reset(p);
        p.setGameMode(GameMode.ADVENTURE);
        PlayerUtils.setCollidesWithEntities(p, false);
        PlayerUtils.hidePlayer(p);
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(21);
        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
        p.setFlying(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        p.teleport(GameData_cfg.spec_loc.clone());

        ItemStack tplist_item = ConfigUtils.getItemStack(CreeperStarBedwars.getPlugin().getConfig(), "Spec-Settings.tplist.inventory.item", true).clone();
        ItemMeta tplist_item_meta = tplist_item.getItemMeta().clone();
        tplist_item_meta.setDisplayName(tplist_item_meta.getDisplayName() + "§3§c§e§c§e§1");
        tplist_item.setItemMeta(tplist_item_meta);
        p.getInventory().setItem(ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "Spec-Settings.tplist.inventory.solt"), tplist_item);

        ItemStack leave_item = ConfigUtils.getItemStack(CreeperStarBedwars.getPlugin().getConfig(), "Spec-Settings.leave.inventory.item", true).clone();
        ItemMeta leave_item_meta = tplist_item.getItemMeta().clone();
        leave_item_meta.setDisplayName(leave_item_meta.getDisplayName() + "§3§c§e§c§e§3");
        leave_item.setItemMeta(leave_item_meta);
        p.getInventory().setItem(ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "Spec-Settings.leave.inventory.solt"), leave_item);

    }



    public static void open_tplist_gui(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 4 * 9, ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "Spec-Settings.tplist.gui.title") + " §3§a§c§k§a§e§f");

        for (int i = 0; i < gui.getSize()-1 && i < GamePlayers.players.size(); i++) {
            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3).clone();
            SkullMeta meta = (SkullMeta) skull.getItemMeta().clone();
            meta.setOwner(GamePlayers.players.get(i).getName());
            meta.setDisplayName(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "Spec-Settings.tplist.gui.item-name").replace("{team_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get( GamePlayers.players.get(i) ))).replace("{team_name}", TeamManager.getTeamName(TeamManager.player_teams.get( GamePlayers.players.get(i) ))).replace("{player}", GamePlayers.players.get(i).getName()).replace("{player_name}", GamePlayers.players.get(i).getName()));
            meta.setLore(ConfigUtils.getStringList(CreeperStarBedwars.getPlugin().getConfig(), "Spec-Settings.tplist.gui.item-lore"));
            skull.setItemMeta(meta);
            gui.setItem(i, skull);
        }

        p.openInventory(gui);

    }

}
