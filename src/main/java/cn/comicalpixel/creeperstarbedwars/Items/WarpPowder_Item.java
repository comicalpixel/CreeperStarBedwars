package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
//import cn.comicalpixel.creeperstarbedwars.Utils.ParticleEffects;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpPowder_Item implements Listener {

    public List<Player> Useing_Players = new ArrayList<>();
    public HashMap<Player, ItemStack> usedItemMap = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.getItem() == null) return;

        if (e.getItem().getType() != Material.SULPHUR) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();

        if (Useing_Players.contains(p)) {
            // 手动取消的玩意
            Useing_Players.remove(p);
            ItemStack i2 = usedItemMap.get(p);
            i2.setAmount(1);
            p.getInventory().addItem(i2);
            usedItemMap.remove(p);
            p.sendMessage(ConfigData.ItemsInGame_warp_powder_cancel_chat);
            return;
        }

        Useing_Players.add(p);
        usedItemMap.put(p, e.getItem());

        ItemStack itemInHand = p.getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().remove(itemInHand);
        }


        new BukkitRunnable() {
            double resapwn = 0.1;
            @Override
            public void run() {
                if (resapwn >= 20.0) {
                    cancel();
                    return;
                }
                if (!Useing_Players.contains(p)) {
                    cancel();
                    return;
                }
                drawCircle(p.getLocation(), resapwn);
                drawCircle(TeamSpawn.getLocation(p, TeamManager.player_teams.get(p)).clone(), resapwn);
                resapwn = resapwn + 0.05;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,2);
        new BukkitRunnable() {
            int i = ConfigData.ItemsInGame_warp_powder_tpcountdown * 20;
            @Override
            public void run() {
                if (i <= 0) {
                    Useing_Players.remove(e.getPlayer());
                    TeamSpawn.send(p, TeamManager.player_teams.get(p));
                }
                if (!Useing_Players.contains(p)) {
                    cancel();
                    return;
                }
                String s = ConfigData.ItemsInGame_warp_powder_tpcountdown_chat;
                if (i % 20 == 0) {
                    p.sendMessage(s.replace("{countdown}", ((int) i / 20)+""));
                }
                i = i - 1;
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        if (!Useing_Players.contains(e.getPlayer())) {
            return;
        }

        if (e.getFrom().getX() == e.getTo().getX() && e.getFrom().getZ() == e.getTo().getZ()) {
            return;
        }

        Useing_Players.remove(e.getPlayer());
        e.getPlayer().sendMessage(ConfigData.ItemsInGame_warp_powder_move_cancel_chat);

        ItemStack i2 = usedItemMap.get(e.getPlayer());
        i2.setAmount(1);
        e.getPlayer().getInventory().addItem(i2);
        usedItemMap.remove(e.getPlayer());

    }


    private void drawCircle(Location location, double add) {

        double x = location.getX();
        double y = location.getY() + add;
        double z = location.getZ();

        for(int i = 0; i <= 360; i += 10) {

            double x1 = x + 1 * Math.sin((double)i * Math.PI / 180.0);
            double z1 = z + 1 * Math.cos((double)i * Math.PI / 180.0);

            Location ploc = new Location(location.getWorld(), x1, y+0.05, z1);



            PacketContainer packet = new PacketContainer(PacketType.Play.Server.WORLD_PARTICLES);
            packet.getParticles().write(0, EnumWrappers.Particle.FIREWORKS_SPARK);
            packet.getFloat().write(0, (float) ploc.getX());
            packet.getFloat().write(1, (float) ploc.getY());
            packet.getFloat().write(2, (float) ploc.getZ());
            packet.getFloat().write(3, 0f);
            packet.getFloat().write(4, 0f);
            packet.getFloat().write(5, 0f);
            packet.getFloat().write(6, 0f);
            packet.getIntegers().write(0, 1);



            for (Player p : Bukkit.getOnlinePlayers()) {
                try {
                    ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

}
