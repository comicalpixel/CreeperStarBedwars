package cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Generators_Diamond {

    private static BukkitTask diamondTask;
    private static Random random = new Random();
    public static int level = 1;
    public static int interval_ = 30;
    public static int timer_message = 0;


    public static void set(int interval, int level) {
        interval_ = interval;
        set_level(level);
        startResourceGeneration();
    }

    public static void set_level(int i) {
        level = i;
    }
    public static void set_interval(int i) {
//        switch (i) {
//            case 1:
//                level = 1;
//                break;
//            case 2:
//                level = 2;
//                break;
//            case 3:
//                level = 3;
//                break;
//            default:
//                Bukkit.getLogger().warning("Error Diamond Level! 1/2/3");
//                level = 1;
//                break;
//        }
        interval_ = i;
    }

    public static void start() {
        startResourceGeneration();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (timer_message < 1) {
                    return;
                }

                timer_message--;
            }
        }, 0, 20);
    }

    public static void startResourceGeneration() {
        cancelTasks();

        int interval;
        interval = interval_;

//        int interval;
//        switch (level) {
//            case 1:
//                interval = 30 * 20; // 30 seconds
//                break;
//            case 2:
//                interval = 20 * 20; // 20 seconds
//                break;
//            case 3:
//                interval = 10 * 20; // 10 seconds
//                break;
//            default:
//                interval = 30 * 20; // Default to 30 seconds
//                break;
//        }

        diamondTask = Bukkit.getScheduler().runTaskTimer(CreeperStarBedwars.getPlugin(), () -> {
            timer_message = interval / 20;
            generateDiamonds();
        }, 0, interval);
    }

    private static void generateDiamonds() {
        if (GameData_cfg.gameGenerator_diamond_locs.isEmpty()) {
            Bukkit.getLogger().info("[GodGold Bedwars][Error] Diamond generators size is 0 ! ");
            return;
        }

        for (Location location : ConfigUtils.getBlockStrLocationList(GameData_cfg.gameGenerator_diamond_locs)) {
            location = location.clone().add(0, 1, 0);
            List<Item> nearbyDiamonds = location.getWorld().getNearbyEntities(location, 2, 2, 2).stream()
                    .filter(entity -> entity instanceof Item)
                    .map(entity -> (Item) entity)
                    .filter(item -> item.getItemStack().getType() == Material.DIAMOND)
                    .collect(Collectors.toList());

            int existingDiamonds = nearbyDiamonds.stream()
                    .mapToInt(item -> item.getItemStack().getAmount())
                    .sum();

            if (existingDiamonds < ConfigData.resourcelimit_diamond + 1) {
                ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
                ItemMeta diamondMeta = diamond.getItemMeta();
                diamondMeta.setDisplayName(ChatColor.WHITE + "[CreeperStarBedwars] Diamond " + random.nextInt(25565) + 1000);
                diamond.setItemMeta(diamondMeta);
                Item item = location.getWorld().dropItem(location, diamond);
                item.setVelocity(location.getWorld().getBlockAt(location).getLocation().toVector().multiply(0)); // 设置速度为0，防止物品飞走
                item.setPickupDelay(1); // 设置物品的拾取延迟，防止立即被拾取
            }
        }
    }

    private static void cancelTasks() {
        if (diamondTask != null) {
            diamondTask.cancel();
            diamondTask = null;
        }
    }

}
