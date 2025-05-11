package cn.comicalpixel.creeperstarbedwars.Arena.Generator.Team.Manager;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TeamGenerator_GRAY {

    public static double spawnTimer_iron = 1.3;
    public static double spawnTimer_gold = 2.1;
    public static double spawnTimer_emerald = -1;

    public static BukkitTask ironTask;
    public static BukkitTask goldTask;
    public static BukkitTask emeraldTask;
    private static Random random = new Random();

    private static Location resourceLocation = GameData_cfg.team_gray_generator.clone().add(0, 1, 0); //GameData_cfg.team_red_generator.clone().add(0.5, 0.5, 0.5);

    public static void set(double iron_timer, double gold_timer, double emerald_timer) {
        cancelTasks();

        spawnTimer_iron = iron_timer;
        spawnTimer_gold = gold_timer;
        spawnTimer_emerald = emerald_timer;

        ironTask = startIronGeneration(iron_timer);
        goldTask = startGoldGeneration(gold_timer);
        emeraldTask = startEmeraldGeneration(emerald_timer);
    }

    private static void cancelTasks() {
        if (ironTask != null) ironTask.cancel();
        if (goldTask != null) goldTask.cancel();
        if (emeraldTask != null) emeraldTask.cancel();
    }



    private static BukkitTask startIronGeneration(double interval) {

        return new BukkitRunnable() {
            double i = interval;
            @Override
            public void run() {

                if (i <= 0 && interval > 0) {
                    List<Item> nearbyDiamonds = resourceLocation.getWorld().getNearbyEntities(resourceLocation, 2, 2, 2).stream()
                            .filter(entity -> entity instanceof Item)
                            .map(entity -> (Item) entity)
                            .filter(item -> item.getItemStack().getType() == Material.IRON_INGOT)
                            .collect(Collectors.toList());
                    int existingDiamonds = nearbyDiamonds.stream()
                            .mapToInt(item -> item.getItemStack().getAmount())
                            .sum();
                    if (existingDiamonds < ConfigData.resourcelimit_iron) {
                        ItemStack itemStack = new ItemStack(Material.IRON_INGOT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.WHITE + "[CreeperStarBedwars] IRON " + random.nextInt(25565) + 1000);
                        itemStack.setItemMeta(itemMeta);
                        Item item = resourceLocation.getWorld().dropItem(resourceLocation, itemStack);
                        item.setVelocity(resourceLocation.getWorld().getBlockAt(resourceLocation).getLocation().toVector().multiply(0)); // 设置速度为0，防止物品飞走
                        item.setPickupDelay(1);
                    }

                    i = interval;

                    return;
                }

                i = i - 0.05;

            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);


    }


    private static BukkitTask startGoldGeneration(double interval) {
        return new BukkitRunnable() {
            double i = interval;
            @Override
            public void run() {
                if (i <= 0 && interval > 0) {
                    List<Item> nearbyGold = resourceLocation.getWorld().getNearbyEntities(resourceLocation, 2, 2, 2).stream()
                            .filter(entity -> entity instanceof Item)
                            .map(entity -> (Item) entity)
                            .filter(item -> item.getItemStack().getType() == Material.GOLD_INGOT)
                            .collect(Collectors.toList());
                    int existingGold = nearbyGold.stream()
                            .mapToInt(item -> item.getItemStack().getAmount())
                            .sum();
                    if (existingGold < ConfigData.resourcelimit_gold) {
                        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.GOLD + "[CreeperStarBedwars] GOLD " + random.nextInt(25565) + 1000);
                        itemStack.setItemMeta(itemMeta);
                        Item item = resourceLocation.getWorld().dropItem(resourceLocation, itemStack);
                        item.setVelocity(resourceLocation.getWorld().getBlockAt(resourceLocation).getLocation().toVector().multiply(0));
                        item.setPickupDelay(1);
                    }

                    i = interval;
                    return;
                }
                i = i - 0.05;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0, 1);
    }

    private static BukkitTask startEmeraldGeneration(double interval) {
        return new BukkitRunnable() {
            double i = interval;
            @Override
            public void run() {
                if (i <= 0 && interval > 0) {
                    List<Item> nearbyEmeralds = resourceLocation.getWorld().getNearbyEntities(resourceLocation, 2, 2, 2).stream()
                            .filter(entity -> entity instanceof Item)
                            .map(entity -> (Item) entity)
                            .filter(item -> item.getItemStack().getType() == Material.EMERALD)
                            .collect(Collectors.toList());
                    int existingEmeralds = nearbyEmeralds.stream()
                            .mapToInt(item -> item.getItemStack().getAmount())
                            .sum();
                    if (existingEmeralds < ConfigData.resourcelimit_emerald) {
                        ItemStack itemStack = new ItemStack(Material.EMERALD);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.GREEN + "[CreeperStarBedwars] EMERALD " + random.nextInt(25565) + 1000);
                        itemStack.setItemMeta(itemMeta);
                        Item item = resourceLocation.getWorld().dropItem(resourceLocation, itemStack);
                        item.setVelocity(resourceLocation.getWorld().getBlockAt(resourceLocation).getLocation().toVector().multiply(0));
                        item.setPickupDelay(1);
                    }

                    i = interval;
                    return;
                }
                i = i - 0.05;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0, 1);
    }

}
