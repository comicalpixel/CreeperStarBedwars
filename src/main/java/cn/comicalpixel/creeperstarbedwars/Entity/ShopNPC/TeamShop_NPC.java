package cn.comicalpixel.creeperstarbedwars.Entity.ShopNPC;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Shop.Updrade.TeamShop_GUI;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamShop_NPC implements Listener {

    public static void spawn(Location loc) {

        Chunk chunk = loc.getChunk();
        if (!chunk.isLoaded()) {
            chunk.load(true);
        }

        EntityVillagerBedWars npc = new EntityVillagerBedWars(((CraftWorld) Bukkit.getWorld(loc.getWorld().getName())).getHandle());
        npc.setHealth(20);
        npc.setCustomName(ConfigData.language_shopgui_update_NPCname + "§e§k§l§2");
//        npc.getBukkitEntity().setMetadata("ItemShop_NPC", new FixedMetadataValue(CreeperStarBedwars.getPlugin(), null));
        npc.setCustomNameVisible(true);
        EntityTypes.spawnEntity(npc,loc);
        npc.getBukkitEntity().teleport(loc);
        Villager villager = (Villager) npc.getBukkitEntity();
        villager.teleport(loc);

        new BukkitRunnable() {
            @Override
            public void run() {
                villager.getLocation().setYaw(loc.getYaw());
                villager.getLocation().setPitch(loc.getPitch());
                villager.teleport(loc);
                npc.getControllerLook().a(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                npc.setPositionRotation(loc.getYaw(), loc.getPitch());
                npc.teleportTo(loc, true);
            }
        }.runTaskLater(CreeperStarBedwars.getPlugin(), 20);

    }

    @EventHandler
    public void onPlayerClickVillager(PlayerInteractEntityEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return;

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.getRightClicked() == null) {
            return;
        }

        // 这里就不加判断是否是村民啦，可以方便开发者实现自定义，只要实体的CustomName最后包含"§e§k§l§2"就能打开啦

        // 这里防止利用名字的啦，不知道能不能卡名字bug反正就先加个判断在这里
        if (e.getRightClicked() instanceof Player) {
            if (((Player) e.getRightClicked()).isOnline()) { // 通常, 正常玩家是Online的而自定义PlayerNPC是!Online所以一般不会影响开发者的自定义NPC
                return;
            }
        }

        if (e.getRightClicked().getCustomName().endsWith("§e§k§l§2")) {
            e.setCancelled(true);
            TeamShop_GUI.open(e.getPlayer());
            ConfigUtils.playSound(e.getPlayer(),CreeperStarBedwars.getPlugin().getConfig(), "sound.update-open");
        }

    }

    @EventHandler
    public void EntityAntiDamage(EntityDamageEvent e) {
        if (e.getEntity() != null && e.getEntity() instanceof Villager && e.getEntity().getCustomName().endsWith("§e§k§l§2")) {
            e.setCancelled(true);
        }
    }

}
