package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Utils.LocationUtil;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BridgeEgg_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return;

        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && e.getItem() != null && e.getItem().getType() == Material.EGG ) {

            e.setCancelled(true);
            Player p = e.getPlayer();

            // 检查冷却时间
            if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_bridge_egg_cooldown * 1000)) {
                int cooldms = (ConfigData.ItemsInGame_bridge_egg_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
                int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
                String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
                e.setCancelled(true);
                p.sendMessage(ConfigData.ItemsInGame_bridge_egg_cooldown_chat.replace("{cooldown}", cooldmess));
                return;
            }

            cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

            ItemStack itemInHand = p.getItemInHand();
            if (itemInHand.getAmount() > 1) {
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            } else {
                p.getInventory().setItemInHand(null);
            }

            Egg egg = p.launchProjectile(Egg.class);
            egg.setBounce(false);
            egg.setShooter(p);

            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getInstance(),()->{
                setblock(egg,p);
            },2);


        }
    }

    public void setblock(Egg egg, Player player) {
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (!egg.isDead()) {
                    new BukkitRunnable() {
                        final Location location = egg.getLocation().add(0, -1, 0);

                        @Override
                        public void run() {
                            location.setX((int) location.getX());
                            location.setY((int) location.getY());
                            location.setZ((int) location.getZ());
                            List<Location> blocklocation = new ArrayList<>();
                            blocklocation.add(location);
                            Vector vector = egg.getVelocity();
                            double x = vector.getX() > 0 ? vector.getX() : -vector.getX();
                            double y = vector.getY() > 0 ? vector.getY() : -vector.getY();
                            double z = vector.getZ() > 0 ? vector.getZ() : -vector.getZ();
                            if (y < x || y < z) {
                                blocklocation.add(LocationUtil.getLocation(location, -1, 0, -1));
                                blocklocation.add(LocationUtil.getLocation(location, -1, 0, 0));
                                blocklocation.add(LocationUtil.getLocation(location, 0, 0, -1));
                            } else {
                                blocklocation.add(LocationUtil.getLocation(location, 0, 1, 0));
                                blocklocation.add(LocationUtil.getLocation(location, -1, 1, -1));
                                blocklocation.add(LocationUtil.getLocation(location, -1, 1, 0));
                                blocklocation.add(LocationUtil.getLocation(location, 0, 1, -1));
                                blocklocation.add(LocationUtil.getLocation(location, -1, 0, -1));
                                blocklocation.add(LocationUtil.getLocation(location, -1, 0, 0));
                                blocklocation.add(LocationUtil.getLocation(location, 0, 0, -1));
                            }
                            for (Location loc : blocklocation) {
                                Block block = loc.getBlock();
                                if (block.getType() == new ItemStack(Material.AIR).getType() && !block.equals(player.getLocation().getBlock()) && !block.equals(player.getLocation().clone().add(0, 1, 0).getBlock()) && !PlayerBlocks.safeMap_blocks.contains(loc.getBlock()) && i < 50) {
                                    block.setType(Material.WOOL);
                                    // loc.getBlock().setData(game.getPlayerTeam(player).getColor().getDyeColor().getWoolData());
                                    switch (TeamManager.player_teams.get(player)) {
                                        case "RED":
                                            block.setData((byte) 14);
                                            break;
                                        case "BLUE":
                                            block.setData((byte) 11);
                                            break;
                                        case "YELLOW":
                                            block.setData((byte) 4);
                                            break;
                                        case "GREEN":
                                            block.setData((byte) 5);
                                            break;
                                        case "PINK":
                                            block.setData((byte) 6);
                                            break;
                                        case "AQUA":
                                            block.setData((byte) 9);
                                            break;
                                        case "GRAY":
                                            block.setData((byte) 7);
                                            break;
                                        case "WHITE":
                                            block.setData((byte) 0);
                                            break;
                                        default:
                                            block.setData((byte) 0);
                                            break;
                                    }

                                    block.getState().update();

                                    for (Player oplayer : Bukkit.getOnlinePlayers()) {
                                        oplayer.playSound(block.getLocation(), Sound.CHICKEN_EGG_POP, 10f, 1);
                                    }

                                    i++;
//                                    game.getRegion().addPlacedBlock(loc.getBlock(), null);
                                    PlayerBlocks.player_blocks.add(loc.getBlock());
                                }
                            }
                        }
                    }.runTaskLater(CreeperStarBedwars.getInstance(), 5L);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(CreeperStarBedwars.getInstance(), 0L, 0L);
    }

}
