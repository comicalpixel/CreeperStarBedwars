package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.ScoreboardManager;

public class InvisibleUtil_Task {
    public InvisibleUtil_Task() {
        task();
    }


    static ScoreboardManager sbm;
    static String NoTagTeamName = "NoNameTagTeam";

    public static void task() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {

                if (GameStats.get() != 2 && GameStats.get() != 3) return ;

                for (Player p : GamePlayers.players) {
                    if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        hideEquip(p,true);
                        // PlayerConnection boundTo = ((CraftPlayer) p).getHandle().playerConnection;
                        // boundTo.player.setCustomNameVisible(true);
                    } else {
                        hideEquip(p,false);
                    }
                }

            }
        }, 0, 0L); // 参数分别为：延迟执行的时间（tick），重复执行的时间间隔（tick），20 ticks = 1秒

    }

    public static void hideEquip(Player gamePlayer, boolean hide) {
        PacketPlayOutEntityEquipment packetPlayOutEntityEquipment1 = new PacketPlayOutEntityEquipment(gamePlayer.getPlayer().getEntityId(), 4, CraftItemStack.asNMSCopy(hide ? new ItemStack(Material.AIR) : gamePlayer.getPlayer().getEquipment().getHelmet()));
        PacketPlayOutEntityEquipment packetPlayOutEntityEquipment2 = new PacketPlayOutEntityEquipment(gamePlayer.getPlayer().getEntityId(), 3, CraftItemStack.asNMSCopy(hide ? new ItemStack(Material.AIR) : gamePlayer.getPlayer().getEquipment().getChestplate()));
        PacketPlayOutEntityEquipment packetPlayOutEntityEquipment3 = new PacketPlayOutEntityEquipment(gamePlayer.getPlayer().getEntityId(), 2, CraftItemStack.asNMSCopy(hide ? new ItemStack(Material.AIR) : gamePlayer.getPlayer().getEquipment().getLeggings()));
        PacketPlayOutEntityEquipment packetPlayOutEntityEquipment4 = new PacketPlayOutEntityEquipment(gamePlayer.getPlayer().getEntityId(), 1, CraftItemStack.asNMSCopy(hide ? new ItemStack(Material.AIR) : gamePlayer.getPlayer().getEquipment().getBoots()));

        for (Player gameplayer1 : GamePlayers.players) {
            CraftPlayer craftPlayer = (CraftPlayer) gameplayer1;
            // EntityPlayer entityPlayer = craftPlayer.getHandle();
            if (craftPlayer != ((CraftPlayer) gamePlayer)) {
                craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutEntityEquipment1);
                craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutEntityEquipment2);
                craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutEntityEquipment3);
                craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutEntityEquipment4);
            }
        }
    }




}
