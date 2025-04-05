package cn.comicalpixel.creeperstarbedwars.Entity;

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class AntiMobs implements Listener {

    @EventHandler
    public void EntitySpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Creeper ||
            e.getEntity() instanceof Zombie ||
            e.getEntity() instanceof Spider ||
            e.getEntity() instanceof Skeleton ||
            e.getEntity() instanceof Enderman ||
            e.getEntity() instanceof Ghast ||
            e.getEntity() instanceof Witch ||
            e.getEntity() instanceof Slime) {
            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                Entity entity = e.getEntity();
                if (!entity.hasMetadata("game") && !entity.hasMetadata("bw") &&
                        !entity.hasMetadata("bedwars") && !entity.hasMetadata("CreeperStarBedwars")){
                    e.setCancelled(true);
                }
            },1);
        }
    }

    public static void clear_task() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (entity instanceof Creeper ||
                                entity instanceof Zombie ||
                                entity instanceof Spider ||
                                entity instanceof Skeleton ||
                                entity instanceof Enderman ||
                                entity instanceof Ghast ||
                                entity instanceof Witch ||
                                entity instanceof Slime) {

                            if (!entity.hasMetadata("game") && !entity.hasMetadata("bw") &&
                                    !entity.hasMetadata("bedwars") && !entity.hasMetadata("CreeperStarBedwars")){
                                entity.remove();
                            }

                        }
                    }
                }
            }
        }, 0, 2L); // 参数分别为：延迟执行的时间（tick），重复执行的时间间隔（tick），20 ticks = 1秒
    }

}
