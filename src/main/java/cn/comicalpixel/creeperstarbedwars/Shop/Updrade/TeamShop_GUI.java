package cn.comicalpixel.creeperstarbedwars.Shop.Updrade;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.BwimResItemManager;
import cn.comicalpixel.creeperstarbedwars.Shop.Item.ItemShop_Manager;
import cn.comicalpixel.creeperstarbedwars.Shop.ShopEnoughUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamShop_GUI implements Listener {

    public static HashMap<String, Integer> team_swordSharpness = new HashMap<>(); // 储存队伍锋利附魔等级
    public static HashMap<String, Integer> team_armorProtection = new HashMap<>(); // 储存队伍保护附魔等级
    public static HashMap<String, Integer> team_fastDig = new HashMap<>(); // 储存队伍急迫(疯狂矿工)等级
    public static HashMap<String, Integer> team_GenResLevel = new HashMap<>(); // 储存队伍资源点等级
    public static HashMap<String, Integer> team_HealPool = new HashMap<>(); // 储存队伍治愈池
    public static HashMap<String, Boolean> team_EnderDragon = new HashMap<>(); // 龙增益
    public static HashMap<String, List<Integer>> team_Traps = new HashMap<>(); // 储存队伍所有的陷阱


    public static void gamestart_reset() {
        for (String team : TeamManager.teams) {
            team_swordSharpness.put(team, CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("updrade.sword_sharpness.def-level"));
            team_armorProtection.put(team, CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("updrade.sword_sharpness.def-level"));
            team_fastDig.put(team, CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("updrade.fast_dig.def-level"));
            team_GenResLevel.put(team, CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("updrade.team_resource.def-level"));
            team_HealPool.put(team, CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("updrade.heal_pool.def-level"));
            team_EnderDragon.put(team, false);

            team_Traps.put(team, new ArrayList<Integer>());
        }
    }

    public static void open(Player p) {

        Inventory gui = Bukkit.createInventory((InventoryHolder) null, 6 * 9, ConfigUtils.getString(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.title") + " §3§a§c§k§a§e§f");

        String prm = "i";
        if (ConfigData.bwimsel_enabled) {
            if (BwimResItemManager.Companion.getPlayerMode().get(p) == 0) {
                prm = "i";
            }
            if (BwimResItemManager.Companion.getPlayerMode().get(p) == 1) {
                prm = "xp";
            }
        } else {
            if (ConfigData.bwimsel_default == 0) {
                prm = "i";
            }
            if (ConfigData.bwimsel_default == 0) {
                prm = "xp";
            }
        }

        FileConfiguration updradeConfig = CreeperStarBedwars.getPlugin().getUpdradeConfig();

        /* GUI图标 */

        // 装饰玻璃板 frames
        ItemStack glass_item = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta glass_meta = glass_item.getItemMeta();
        glass_meta.setDisplayName(ConfigUtils.getString(updradeConfig, "GUI.frame-icon.name"));
        glass_meta.setLore(ConfigUtils.getStringList(updradeConfig, "GUI.frame-icon.lore"));
        glass_item.setItemMeta(glass_meta);
        glass_item.setDurability((short) ConfigUtils.getInt(updradeConfig, "GUI.frame-icon.damage"));
        for (int i : updradeConfig.getIntegerList("GUI.icon-solts.frames")) {
            gui.setItem(i, glass_item);
        }

        /**/



        // 武器锋利附魔 §a§d§a§3§e§0
        ItemStack icon_swordSharpness_item = new ItemStack(Material.BARRIER).clone();
        int swordSharpness_now_level = team_swordSharpness.get(TeamManager.player_teams.get(p));
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.sword_sharpness.level-" + swordSharpness_now_level + "." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = icon_swordSharpness_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop updrade-icon.sword_sharpness icon(" + prm + ") is not found!");
            icon_swordSharpness_item.setItemMeta(meta);
        } else {
            icon_swordSharpness_item = ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.sword_sharpness.level-" + swordSharpness_now_level + "." + prm, true);
            ItemMeta meta = icon_swordSharpness_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§3§e§0");
            icon_swordSharpness_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.updrade.sword_sharpness") ,icon_swordSharpness_item);



        // 保护附魔 §a§d§a§3§e§1
        ItemStack icon_armorProtection_item = new ItemStack(Material.BARRIER).clone();
        int armorProtection_now_level = team_armorProtection.get(TeamManager.player_teams.get(p));
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.armor_protection.level-" + armorProtection_now_level + "." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = icon_armorProtection_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop updrade-icon.armor_protection icon(" + prm + ") is not found!");
            icon_armorProtection_item.setItemMeta(meta);
        } else {
            icon_armorProtection_item = ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.armor_protection.level-" + armorProtection_now_level + "." + prm, true);
            ItemMeta meta = icon_armorProtection_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§3§e§1");
            icon_armorProtection_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.updrade.armor_protection") ,icon_armorProtection_item);



        // 疯狂矿工 §a§d§a§3§e§2
        ItemStack icon_fastDig_item = new ItemStack(Material.BARRIER).clone();
        int fastDig_now_Level = team_fastDig.get(TeamManager.player_teams.get(p));
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.fast_dig.level-" + fastDig_now_Level + "." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = icon_fastDig_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop updrade-icon.fast_dig icon(" + prm + ") is not found!");
            icon_fastDig_item.setItemMeta(meta);
        } else {
            icon_fastDig_item = ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.fast_dig.level-" + fastDig_now_Level + "." + prm, true);
            ItemMeta meta = icon_fastDig_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§3§e§2");
            icon_fastDig_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.updrade.fast_dig") ,icon_fastDig_item);



        // 锻造熔炉(炉子)  §a§d§a§3§e§3
        ItemStack icon_team_resource_item = new ItemStack(Material.BARRIER).clone();
        int team_resource_Level = team_GenResLevel.get(TeamManager.player_teams.get(p));
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.team_resource.level-" + team_resource_Level + "." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = icon_team_resource_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop updrade-icon.team_resource icon(" + prm + ") is not found!");
            icon_team_resource_item.setItemMeta(meta);
        } else {
            icon_team_resource_item = ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.team_resource.level-" + team_resource_Level + "." + prm, true);
            ItemMeta meta = icon_team_resource_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§3§e§3");
            icon_team_resource_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.updrade.team_resource") ,icon_team_resource_item);



        // 治愈池  §a§d§a§3§e§4
        ItemStack icon_HealPool_item = new ItemStack(Material.BARRIER).clone();
        int healPool_Level = team_HealPool.get(TeamManager.player_teams.get(p));
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.heal_pool.level-" + healPool_Level + "." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = icon_HealPool_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop GUI.updrade-icon.heal_pool icon(" + prm + ") is not found!");
            icon_HealPool_item.setItemMeta(meta);
        } else {
            icon_HealPool_item = ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.heal_pool.level-" + healPool_Level + "." + prm, true);
            ItemMeta meta = icon_HealPool_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§3§e§4");
            icon_HealPool_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.updrade.heal_pool") ,icon_HealPool_item);


        /**/

        // 龙增益
        ItemStack enderDragon_item = new ItemStack(Material.BARRIER).clone();
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.EnderDragon" + "." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = enderDragon_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop GUI.updrade-icon.EnderDragon icon(" + prm + ") is not found!");
            enderDragon_item.setItemMeta(meta);
        } else {
            enderDragon_item = ConfigUtils.getItemStack(updradeConfig, "GUI.updrade-icon.EnderDragon" + "." + prm, true);
            ItemMeta meta = enderDragon_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§3§e§5");
            enderDragon_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.updrade.EnderDragon") ,enderDragon_item);


        /**/



        /* 陷阱物品 */

        // 这是一个陷阱! §a§d§a§c§1
        ItemStack trap01_icon_item = new ItemStack(Material.BARRIER).clone();
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.ThisIsATrap." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = trap01_icon_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop GUI.trap-icon.ThisIsATrap icon(" + prm + ") is not found!");
            trap01_icon_item.setItemMeta(meta);
        } else {
            trap01_icon_item = ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.ThisIsATrap." + prm, true);
            ItemMeta meta = trap01_icon_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§c§1");
            trap01_icon_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.ThisIsATrap") ,trap01_icon_item);

        // UnAttack反击陷阱 §a§d§a§c§2
        ItemStack trap02_icon_item = new ItemStack(Material.BARRIER).clone();
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.UnAttack." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = trap02_icon_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop GUI.trap-icon.UnAttack icon(" + prm + ") is not found!");
            trap02_icon_item.setItemMeta(meta);
        } else {
            trap02_icon_item = ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.UnAttack." + prm, true);
            ItemMeta meta = trap02_icon_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§c§2");
            trap02_icon_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.UnAttack") ,trap02_icon_item);

        // Warning §a§d§a§c§3
        ItemStack trap03_icon_item = new ItemStack(Material.BARRIER).clone();
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.Warning." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = trap03_icon_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop GUI.trap-icon.Warning icon(" + prm + ") is not found!");
            trap03_icon_item.setItemMeta(meta);
        } else {
            trap03_icon_item = ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.Warning." + prm, true);
            ItemMeta meta = trap03_icon_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§c§3");
            trap03_icon_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.Warning") ,trap03_icon_item);

        // AntiBreak挖掘疲劳 §a§d§a§c§4
        ItemStack trap04_icon_item = new ItemStack(Material.BARRIER).clone();
        if (ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.AntiBreak." + prm, true).getType() == Material.BEDROCK) {
            ItemMeta meta = trap04_icon_item.getItemMeta();
            meta.setDisplayName("§c" + "UpdradeShop GUI.trap-icon.AntiBreak icon(" + prm + ") is not found!");
            trap04_icon_item.setItemMeta(meta);
        } else {
            trap04_icon_item = ConfigUtils.getItemStack(updradeConfig, "GUI.trap-icon.AntiBreak." + prm, true);
            ItemMeta meta = trap04_icon_item.getItemMeta();
            meta.setDisplayName(meta.getDisplayName() + "§a§d§a§c§4");
            trap04_icon_item.setItemMeta(meta);
        }
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.AntiBreak") ,trap04_icon_item);



        // 底下的陷阱槽位显示
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-01"), ConfigUtils.getItemStack(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.trap-icon.TrapSolt-01." + prm, true));
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-02"), ConfigUtils.getItemStack(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.trap-icon.TrapSolt-02." + prm, true));
        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-03"), ConfigUtils.getItemStack(CreeperStarBedwars.getPlugin().getUpdradeConfig(), "GUI.trap-icon.TrapSolt-03." + prm, true));
        /**/
        if (!team_Traps.get(TeamManager.player_teams.get(p)).isEmpty()) {
            if (team_Traps.get(TeamManager.player_teams.get(p)).size() > 0) {
                switch (team_Traps.get(TeamManager.player_teams.get(p)).get(0)) {
                    case 1: {
                        ItemStack trsi = trap01_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-01"), trsi);
                        break;
                    }
                    case 2: {
                        ItemStack trsi = trap02_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-01"), trsi);
                        break;
                    }
                    case 3: {
                        ItemStack trsi = trap03_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-01"), trsi);
                        break;
                    }
                    case 4: {
                        ItemStack trsi = trap04_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-01"), trsi);
                        break;
                    }
                }
            }
            if (team_Traps.get(TeamManager.player_teams.get(p)).size() > 1) {
                switch (team_Traps.get(TeamManager.player_teams.get(p)).get(1)) {
                    case 1: {
                        ItemStack trsi = trap01_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-02"), trsi);
                        break;
                    }
                    case 2: {
                        ItemStack trsi = trap02_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-02"), trsi);
                        break;
                    }
                    case 3: {
                        ItemStack trsi = trap03_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-02"), trsi);
                        break;
                    }
                    case 4: {
                        ItemStack trsi = trap04_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-02"), trsi);
                        break;
                    }
                }
            }
            if (team_Traps.get(TeamManager.player_teams.get(p)).size() > 2) {
                switch (team_Traps.get(TeamManager.player_teams.get(p)).get(2)) {
                    case 1: {
                        ItemStack trsi = trap01_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-03"), trsi);
                        break;
                    }
                    case 2: {
                        ItemStack trsi = trap02_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-03"), trsi);
                        break;
                    }
                    case 3: {
                        ItemStack trsi = trap03_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-03"), trsi);
                        break;
                    }
                    case 4: {
                        ItemStack trsi = trap04_icon_item.clone();
                        ItemMeta trsim = trsi.getItemMeta();
                        trsim.setDisplayName(trsim.getDisplayName() + "§m");
                        trsi.setItemMeta(trsim);
                        gui.setItem(CreeperStarBedwars.getPlugin().getUpdradeConfig().getInt("GUI.icon-solts.traps.TrapSolt-03"), trsi);
                        break;
                    }
                }
            }
        }


        /**/

        p.openInventory(gui);

    }

    @EventHandler
    public void onInventoryClick_teamUpdrade(InventoryClickEvent e) {

        // 这里的GUI物品单击是负责升级的 不是陷阱

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!e.getInventory().getTitle().endsWith("§3§a§c§k§a§e§f")) return;

        if (e.getInventory().getType() != InventoryType.CHEST) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;

        if (e.getRawSlot() > (6 * 9)-1) {
            return;
        }

        FileConfiguration updradeConfig = CreeperStarBedwars.getPlugin().getUpdradeConfig();

        Player p = (Player) e.getWhoClicked();

        ItemStack item = e.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        // 初始化要购买的资源
        ItemStack cost_type = new ItemStack(Material.BEDROCK).clone();
        int cost_amount = 25565;
        int cost_xpLevel = Integer.MAX_VALUE;

        /*
         *购买队伍升级的类型
         后面购买成功后好分辨
         0:锋利附魔
         1:保护附魔
         2:挖掘急迫效果
         3:队伍资源点(炉子) 重要的!!
         4:治愈池
         5:队伍龙加倍(多一个!) 写较简单 个人觉得没啥必要买的...
        */
        int buyed_type = -1;

        // 检查单击的物品是否是...
        if (meta.getDisplayName().endsWith("§a§d§a§3§e§0")) {
            if ((team_swordSharpness.get(TeamManager.player_teams.get(p))+1) > ConfigUtils.getInt(updradeConfig, "updrade.sword_sharpness.levels")) {
                p.sendMessage(ConfigData.language_update_buy_max.replace("{type}", ConfigUtils.getString(updradeConfig, "updrade.sword_sharpness.settings.level-"+(team_swordSharpness.get(TeamManager.player_teams.get(p)))+".name") ));
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
                return;
            } else {
                cost_type = new ItemStack(Material.getMaterial(ConfigUtils.getString(updradeConfig, "updrade.sword_sharpness.settings.level-" + (team_swordSharpness.get(TeamManager.player_teams.get(p))+1) + ".cost.type"))).clone();
                cost_amount = ConfigUtils.getInt(updradeConfig, "updrade.sword_sharpness.settings.level-" + (team_swordSharpness.get(TeamManager.player_teams.get(p))+1) + ".cost.amount");
                cost_xpLevel = ConfigUtils.getInt(updradeConfig, "updrade.sword_sharpness.settings.level-" + (team_swordSharpness.get(TeamManager.player_teams.get(p))+1) + ".cost.xp-level");
                buyed_type = 0;
            }
        }
        else if (meta.getDisplayName().endsWith("§a§d§a§3§e§1")) {
            if ((team_armorProtection.get(TeamManager.player_teams.get(p))+1) > ConfigUtils.getInt(updradeConfig, "updrade.armor_protection.levels")) {
                p.sendMessage(ConfigData.language_update_buy_max.replace("{type}", ConfigUtils.getString(updradeConfig, "updrade.armor_protection.settings.level-"+(team_armorProtection.get(TeamManager.player_teams.get(p)))+".name") ));
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
                return;
            } else {
                cost_type = new ItemStack(Material.getMaterial(ConfigUtils.getString(updradeConfig, "updrade.armor_protection.settings.level-" + (team_armorProtection.get(TeamManager.player_teams.get(p))+1) + ".cost.type"))).clone();
                cost_amount = ConfigUtils.getInt(updradeConfig, "updrade.armor_protection.settings.level-" + (team_armorProtection.get(TeamManager.player_teams.get(p))+1) + ".cost.amount");
                cost_xpLevel = ConfigUtils.getInt(updradeConfig, "updrade.armor_protection.settings.level-" + (team_armorProtection.get(TeamManager.player_teams.get(p))+1) + ".cost.xp-level");
                buyed_type = 1;
            }
        }
        else if (meta.getDisplayName().endsWith("§a§d§a§3§e§2")) {
            if ((team_fastDig.get(TeamManager.player_teams.get(p))+1) > ConfigUtils.getInt(updradeConfig, "updrade.fast_dig.levels")) {
                p.sendMessage(ConfigData.language_update_buy_max.replace("{type}", ConfigUtils.getString(updradeConfig, "updrade.fast_dig.settings.level-"+(team_fastDig.get(TeamManager.player_teams.get(p)))+".name") ));
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
                return;
            } else {
                cost_type = new ItemStack(Material.getMaterial(ConfigUtils.getString(updradeConfig, "updrade.fast_dig.settings.level-" + (team_fastDig.get(TeamManager.player_teams.get(p))+1) + ".cost.type"))).clone();
                cost_amount = ConfigUtils.getInt(updradeConfig, "updrade.fast_dig.settings.level-" + (team_fastDig.get(TeamManager.player_teams.get(p))+1) + ".cost.amount");
                cost_xpLevel = ConfigUtils.getInt(updradeConfig, "updrade.fast_dig.settings.level-" + (team_fastDig.get(TeamManager.player_teams.get(p))+1) + ".cost.xp-level");
                buyed_type = 2;
            }
        }
        else if (meta.getDisplayName().endsWith("§a§d§a§3§e§3")) {
            if ((team_GenResLevel.get(TeamManager.player_teams.get(p))+1) > ConfigUtils.getInt(updradeConfig, "updrade.team_resource.levels")) {
                p.sendMessage(ConfigData.language_update_buy_max.replace("{type}", ConfigUtils.getString(updradeConfig, "updrade.team_resource.settings.level-"+(team_GenResLevel.get(TeamManager.player_teams.get(p)))+".name") ));
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
                return;
            } else {
                cost_type = new ItemStack(Material.getMaterial(ConfigUtils.getString(updradeConfig, "updrade.team_resource.settings.level-" + (team_GenResLevel.get(TeamManager.player_teams.get(p))+1) + ".cost.type"))).clone();
                cost_amount = ConfigUtils.getInt(updradeConfig, "updrade.team_resource.settings.level-" + (team_GenResLevel.get(TeamManager.player_teams.get(p))+1) + ".cost.amount");
                cost_xpLevel = ConfigUtils.getInt(updradeConfig, "updrade.team_resource.settings.level-" + (team_GenResLevel.get(TeamManager.player_teams.get(p))+1) + ".cost.xp-level");
                buyed_type = 3;
            }
        }
        else if (meta.getDisplayName().endsWith("§a§d§a§3§e§4")) {
            if (team_HealPool.get(TeamManager.player_teams.get(p))+1 > ConfigUtils.getInt(updradeConfig, "updrade.heal_pool.levels")) {
                p.sendMessage(ConfigData.language_update_buy_max.replace("{type}", ConfigUtils.getString(updradeConfig, "updrade.heal_pool.settings.level-"+(team_HealPool.get(TeamManager.player_teams.get(p)))+".name") ));
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
                return;
            } else {
                cost_type = new ItemStack(Material.getMaterial(ConfigUtils.getString(updradeConfig, "updrade.heal_pool.settings.level-" + (team_HealPool.get(TeamManager.player_teams.get(p))+1) + ".cost.type"))).clone();
                cost_amount = ConfigUtils.getInt(updradeConfig, "updrade.heal_pool.settings.level-" + (team_HealPool.get(TeamManager.player_teams.get(p))+1) + ".cost.amount");
                cost_xpLevel = ConfigUtils.getInt(updradeConfig, "updrade.heal_pool.settings.level-" + (team_HealPool.get(TeamManager.player_teams.get(p))+1) + ".cost.xp-level");
                buyed_type = 4;
            }
        } else if (meta.getDisplayName().endsWith("§a§d§a§3§e§5")) {
            if (team_EnderDragon.get(TeamManager.player_teams.get(p))) {
                p.sendMessage(ConfigData.language_update_buy_max.replace("{type}", ConfigUtils.getString(updradeConfig, "updrade.EnderDragon.name") ));
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
                return;
            } else {
                cost_type = new ItemStack(Material.getMaterial(ConfigUtils.getString(updradeConfig, "updrade.EnderDragon.cost.type"))).clone();
                cost_amount = ConfigUtils.getInt(updradeConfig, "updrade.EnderDragon.cost.amount");
                cost_xpLevel = ConfigUtils.getInt(updradeConfig, "updrade.EnderDragon.cost.xp-level");
                buyed_type = 5;
            }
        } else {
            return;
        }


        // 处理购买资源部分的
        if (ShopEnoughUtils.isEnough(p, cost_type, cost_amount, cost_xpLevel)) {

            String type_message = "null";

            // 购买成功
            // 这里应该判断玩家购买的东西的类型啦!
            if (buyed_type == 0) {
                type_message = ConfigUtils.getString(updradeConfig, "updrade.sword_sharpness.settings.level-"+(team_swordSharpness.get(TeamManager.player_teams.get(p))+1)+".name");
                team_swordSharpness.put(TeamManager.player_teams.get(p), team_swordSharpness.get(TeamManager.player_teams.get(p))+1); // 添加 记得写在后面！
            }
            if (buyed_type == 1) {
                type_message = ConfigUtils.getString(updradeConfig, "updrade.armor_protection.settings.level-"+(team_armorProtection.get(TeamManager.player_teams.get(p))+1)+".name");
                team_armorProtection.put(TeamManager.player_teams.get(p), team_armorProtection.get(TeamManager.player_teams.get(p))+1); // 添加 记得写在后面！
            }
            if (buyed_type == 2) {
                type_message = ConfigUtils.getString(updradeConfig, "updrade.fast_dig.settings.level-"+(team_fastDig.get(TeamManager.player_teams.get(p))+1)+".name");
                team_fastDig.put(TeamManager.player_teams.get(p), team_fastDig.get(TeamManager.player_teams.get(p))+1); // 添加 记得写在后面！
            }
            if (buyed_type == 3) {
                type_message = ConfigUtils.getString(updradeConfig, "updrade.team_resource.settings.level-"+(team_GenResLevel.get(TeamManager.player_teams.get(p))+1)+".name");
                team_GenResLevel.put(TeamManager.player_teams.get(p), team_GenResLevel.get(TeamManager.player_teams.get(p))+1); // 添加 记得写在后面！
                TeamShop_Manager.setTeamLevel_updrade(team_GenResLevel.get(TeamManager.player_teams.get(p)), TeamManager.player_teams.get(p)); // 队伍资源点
            }
            if (buyed_type == 4) {
                type_message = ConfigUtils.getString(updradeConfig, "updrade.heal_pool.settings.level-"+(team_HealPool.get(TeamManager.player_teams.get(p))+1)+".name");
                team_HealPool.put(TeamManager.player_teams.get(p), team_HealPool.get(TeamManager.player_teams.get(p))+1); // 添加 记得写在后面！
            }
            if (buyed_type == 5) {
                type_message = ConfigUtils.getString(updradeConfig, "updrade.EnderDragon.name");
                team_EnderDragon.put(TeamManager.player_teams.get(p), true);
            }

            for (Player game_p : GamePlayers.players) {
                if (TeamManager.player_teams.get(game_p).equals(TeamManager.player_teams.get(p))) {
                    if (game_p == p) {
                        game_p.sendMessage(ConfigData.language_update_buy_me.replace("{type}", type_message).replace("{player}", p.getName()));
                        ConfigUtils.playSound(game_p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-me");
                    } else {
                        game_p.sendMessage(ConfigData.language_update_buy_team.replace("{type}", type_message).replace("{player}", p.getName()));
                        ConfigUtils.playSound(game_p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-team");
                    }
                }
            }

            ItemShop_Manager.b_deduction(p, cost_type, cost_amount, cost_xpLevel);

            open(p);
        } else {
            p.sendMessage(ConfigData.language_update_buy_no);
            ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
            open(p);
        }

    }


    @EventHandler
    public void onInventoryClick_teamTrap(InventoryClickEvent e) {

        // 这里的GUI物品单击是负责升级的 不是陷阱

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!e.getInventory().getTitle().endsWith("§3§a§c§k§a§e§f")) return;

        if (e.getInventory().getType() != InventoryType.CHEST) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;

        if (e.getRawSlot() > (6 * 9) - 1) {
            return;
        }

        FileConfiguration updradeConfig = CreeperStarBedwars.getPlugin().getUpdradeConfig();

        Player p = (Player) e.getWhoClicked();

        ItemStack item = e.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        // 初始化要购买的资源
        ItemStack cost_type = new ItemStack(Material.BEDROCK).clone();
        int cost_amount = 25565;
        int cost_xpLevel = Integer.MAX_VALUE;

        /*
         *购买队伍升级的类型
         后面购买成功后好分辨
         1:§a§d§a§c§1
         2:§a§d§a§c§2
         3:§a§d§a§c§3
         4:§a§d§a§c§4
        */
        int buyed_type = -1;

        // 检查单击的物品是否是...
        if (meta.getDisplayName().endsWith("§a§d§a§c§1")) {
            buyed_type = 1;
        }
        else if (meta.getDisplayName().endsWith("§a§d§a§c§2")) {
            buyed_type = 2;
        }
        else if (meta.getDisplayName().endsWith("§a§d§a§c§3")) {
            buyed_type = 3;
        }
        else if (meta.getDisplayName().endsWith("§a§d§a§c§4")) {
            buyed_type = 4;
        } else {
            return;
        }

        if (team_Traps.get(TeamManager.player_teams.get(p)).size() >= 3) {
            p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.update-buy-trap-max"));
            ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
            return;
        }

        if (team_Traps.get(TeamManager.player_teams.get(p)).isEmpty()) {
            cost_type = new ItemStack(Material.getMaterial(updradeConfig.getString("traps.settings.trap-1-cost.type"))).clone();
            cost_amount = updradeConfig.getInt("traps.settings.trap-1-cost.amount");
            cost_xpLevel = updradeConfig.getInt("traps.settings.trap-1-cost.xp-level");
        } else {
            switch (team_Traps.get(TeamManager.player_teams.get(p)).size()) {
                case 0:
                    cost_type = new ItemStack(Material.getMaterial(updradeConfig.getString("traps.settings.trap-1-cost.type"))).clone();
                    cost_amount = updradeConfig.getInt("traps.settings.trap-1-cost.amount");
                    cost_xpLevel = updradeConfig.getInt("traps.settings.trap-1-cost.xp-level");
                    break;
                case 1:
                    cost_type = new ItemStack(Material.getMaterial(updradeConfig.getString("traps.settings.trap-1-cost.type"))).clone();
                    cost_amount = updradeConfig.getInt("traps.settings.trap-2-cost.amount");
                    cost_xpLevel = updradeConfig.getInt("traps.settings.trap-2-cost.xp-level");
                    break;
                case 2:
                    cost_type = new ItemStack(Material.getMaterial(updradeConfig.getString("traps.settings.trap-1-cost.type"))).clone();
                    cost_amount = updradeConfig.getInt("traps.settings.trap-3-cost.amount");
                    cost_xpLevel = updradeConfig.getInt("traps.settings.trap-3-cost.xp-level");
                    break;
            }
        }


        // 处理购买资源部分的
        if (ShopEnoughUtils.isEnough(p, cost_type, cost_amount, cost_xpLevel)) {

            String type_message = "null";

            // 购买成功
            // 这里应该判断玩家购买的东西的类型啦!
            if (buyed_type == 1) {
                type_message = ConfigUtils.getString(updradeConfig, "traps.language.type-ThisIsATrap");
                team_Traps.get(TeamManager.player_teams.get(p)).add(1);
            }
            if (buyed_type == 2) {
                type_message = ConfigUtils.getString(updradeConfig, "traps.language.type-UnAttack");
                team_Traps.get(TeamManager.player_teams.get(p)).add(2);
            }
            if (buyed_type == 3) {
                type_message = ConfigUtils.getString(updradeConfig, "traps.language.type-Warning");
                team_Traps.get(TeamManager.player_teams.get(p)).add(3);
            }
            if (buyed_type == 4) {
                type_message = ConfigUtils.getString(updradeConfig, "traps.language.type-AntiBreak");
                team_Traps.get(TeamManager.player_teams.get(p)).add(4);
            }

            for (Player game_p : GamePlayers.players) {
                if (TeamManager.player_teams.get(game_p).equals(TeamManager.player_teams.get(p))) {
                    if (game_p == p) {
                        game_p.sendMessage(ConfigData.language_update_buy_me.replace("{type}", type_message).replace("{player}", p.getName()));
                        ConfigUtils.playSound(game_p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-me");
                    } else {
                        game_p.sendMessage(ConfigData.language_update_buy_team.replace("{type}", type_message).replace("{player}", p.getName()));
                        ConfigUtils.playSound(game_p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-team");
                    }
                }
            }

            ItemShop_Manager.b_deduction(p, cost_type, cost_amount, cost_xpLevel);

            open(p);

        } else {
            p.sendMessage(ConfigData.language_update_buy_no);
            ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.update-buy-no");
            open(p);
        }

    }

}
