package cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
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

public class Generators_Emerald {

    private static BukkitTask emeraldTask;
    private static Random random = new Random();
    public static int level = 1;
    public static int timer_message = 0;

    public static void set(int i) {
        switch (i) {
            case 1:
                level = 1;
                break;
            case 2:
                level = 2;
                break;
            case 3:
                level = 3;
                break;
            default:
                Bukkit.getLogger().warning("Error Emerald Level! 1/2/3");
                level = 1;
                break;
        }
        startResourceGeneration();
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
        switch (level) {
            case 1:
                interval = 60 * 20; // 60 seconds
                break;
            case 2:
                interval = 40 * 20; // 40 seconds
                break;
            case 3:
                interval = 20 * 20; // 20 seconds
                break;
            default:
                interval = 60 * 20; // Default to 60 seconds
                break;
        }

        emeraldTask = Bukkit.getScheduler().runTaskTimer(CreeperStarBedwars.getPlugin(), () -> {
            timer_message = interval / 20;
            generateEmeralds();
        }, 0, interval);
    }

    private static void generateEmeralds() {
        if (GameData_cfg.gameGenerator_emerald_locs.isEmpty()) {
            Bukkit.getLogger().info("[GodGold Bedwars][Error] Emerald generators size is 0 ! ");
            return;
        }

        for (Location location : ConfigUtils.getBlockStrLocationList(GameData_cfg.gameGenerator_emerald_locs)) {
            List<Item> nearbyEmeralds = location.getWorld().getNearbyEntities(location, 2, 2, 2).stream()
                    .filter(entity -> entity instanceof Item)
                    .map(entity -> (Item) entity)
                    .filter(item -> item.getItemStack().getType() == Material.EMERALD)
                    .collect(Collectors.toList());

            int existingEmeralds = nearbyEmeralds.stream()
                    .mapToInt(item -> item.getItemStack().getAmount())
                    .sum();

            if (existingEmeralds < 4 + 1) {
                ItemStack emerald = new ItemStack(Material.EMERALD, 1);
                ItemMeta emeraldMeta = emerald.getItemMeta();
                emeraldMeta.setDisplayName(ChatColor.WHITE + "[CreeperStarBedwars] Emerald " + random.nextInt(25565) + 1000);
                emerald.setItemMeta(emeraldMeta);
                Item item = location.getWorld().dropItem(location, emerald);
                item.setVelocity(location.getWorld().getBlockAt(location).getLocation().toVector().multiply(0)); // 设置速度为0，防止物品飞走
                item.setPickupDelay(1); // 设置物品的拾取延迟，防止立即被拾取
            }
        }
    }

    private static void cancelTasks() {
        if (emeraldTask != null) {
            emeraldTask.cancel();
            emeraldTask = null;
        }
    }

}
