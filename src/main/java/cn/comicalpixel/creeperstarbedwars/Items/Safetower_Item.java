package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Safetower_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.isCancelled()) return;

        if (e.getBlockPlaced() == null) return;

        if (e.getBlockPlaced().getType() != Material.CHEST) return;

        Player p = e.getPlayer();
        Block b = e.getBlockPlaced();

        // 检查冷却时间
        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_safetower_cooldown * 1000)) {
            int cooldms = (ConfigData.ItemsInGame_safetower_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_safetower_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

        e.setCancelled(true);

        tower(TeamManager.player_teams.get(p), b.getLocation(), p);

        // Github Issue#1
        ItemStack itemInHand = p.getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().setItemInHand(null);
        }

    }

    // 祖传代码 (?
    private static void tower(String team, Location loc, Player p) {

        ItemStack wool = new ItemStack(Material.WOOL);

        if (team.equalsIgnoreCase("RED")) {            wool.setDurability((short) 14);        }
        if (team.equalsIgnoreCase("BLUE")) {            wool.setDurability((short) 3);        }
        if (team.equalsIgnoreCase("GREEN")) {            wool.setDurability((short) 5);        }
        if (team.equalsIgnoreCase("YELLOW")) {            wool.setDurability((short) 4);        }
        if (team.equalsIgnoreCase("PINK")) {            wool.setDurability((short) 6);        }
        if (team.equalsIgnoreCase("AQUA")) {            wool.setDurability((short) 9);        }
        if (team.equalsIgnoreCase("GRAY")) {            wool.setDurability((short) 7);        }
        if (team.equalsIgnoreCase("WHITE")) {            wool.setDurability((short) 0);        }

        double rotation = (double)((p.getLocation().getYaw() - 90.0F) % 360.0F);

        if (45.0 <= rotation && rotation < 135.0) {
            // South

            List<String> relloc = new ArrayList();
            relloc.add("1, 0, 2");
            relloc.add("2, 0, 1");
            relloc.add("2, 0, 0");
            relloc.add("1, 0, -1");
            relloc.add("0, 0, -1");
            relloc.add("-1, 0, -1");
            relloc.add("-2, 0, 0");
            relloc.add("-2, 0, 1");
            relloc.add("-1, 0, 2");
            relloc.add("0, 0, 0, ladder3");
            relloc.add("1, 1, 2");
            relloc.add("2, 1, 1");
            relloc.add("2, 1, 0");
            relloc.add("1, 1, -1");
            relloc.add("0, 1, -1");
            relloc.add("-1, 1, -1");
            relloc.add("-2, 1, 0");
            relloc.add("-2, 1, 1");
            relloc.add("-1, 1, 2");
            relloc.add("0, 1, 0, ladder3");
            relloc.add("1, 2, 2");
            relloc.add("2, 2, 1");
            relloc.add("2, 2, 0");
            relloc.add("1, 2, -1");
            relloc.add("0, 2, -1");
            relloc.add("-1, 2, -1");
            relloc.add("-2, 2, 0");
            relloc.add("-2, 2, 1");
            relloc.add("-1, 2, 2");
            relloc.add("0, 2, 0, ladder3");
            relloc.add("0, 3, 2");
            relloc.add("1, 3, 2");
            relloc.add("2, 3, 1");
            relloc.add("2, 3, 0");
            relloc.add("1, 3, -1");
            relloc.add("0, 3, -1");
            relloc.add("-1, 3, -1");
            relloc.add("-2, 3, 0");
            relloc.add("-2, 3, 1");
            relloc.add("-1, 3, 2");
            relloc.add("0, 3, 0, ladder3");
            relloc.add("0, 4, 2");
            relloc.add("1, 4, 2");
            relloc.add("2, 4, 1");
            relloc.add("2, 4, 0");
            relloc.add("1, 4, -1");
            relloc.add("0, 4, -1");
            relloc.add("-1, 4, -1");
            relloc.add("-2, 4, 0");
            relloc.add("-2, 4, 1");
            relloc.add("-1, 4, 2");
            relloc.add("0, 4, 0, ladder3");
            relloc.add("2, 5, -1");
            relloc.add("2, 5, 0");
            relloc.add("2, 5, 1");
            relloc.add("2, 5, 2");
            relloc.add("1, 5, -1");
            relloc.add("1, 5, 0");
            relloc.add("1, 5, 1");
            relloc.add("1, 5, 2");
            relloc.add("0, 5, -1");
            relloc.add("0, 5, 1");
            relloc.add("0, 5, 2");
            relloc.add("-1, 5, -1");
            relloc.add("0, 5, 0, ladder3");
            relloc.add("-1, 5, 0");
            relloc.add("-1, 5, 1");
            relloc.add("-1, 5, 2");
            relloc.add("-2, 5, -1");
            relloc.add("-2, 5, 0");
            relloc.add("-2, 5, 1");
            relloc.add("-2, 5, 2");
            relloc.add("3, 5, 2");
            relloc.add("3, 6, 2");
            relloc.add("3, 7, 2");
            relloc.add("3, 6, 1");
            relloc.add("3, 6, 0");
            relloc.add("3, 5, -1");
            relloc.add("3, 6, -1");
            relloc.add("3, 7, -1");
            relloc.add("2, 5, -2");
            relloc.add("2, 6, -2");
            relloc.add("2, 7, -2");
            relloc.add("1, 6, -2");
            relloc.add("0, 5, -2");
            relloc.add("0, 6, -2");
            relloc.add("0, 7, -2");
            relloc.add("-1, 6, -2");
            relloc.add("-2, 5, -2");
            relloc.add("-2, 6, -2");
            relloc.add("-2, 7, -2");
            relloc.add("-3, 5, 2");
            relloc.add("-3, 6, 2");
            relloc.add("-3, 7, 2");
            relloc.add("-3, 6, 1");
            relloc.add("-3, 6, 0");
            relloc.add("-3, 5, -1");
            relloc.add("-3, 6, -1");
            relloc.add("-3, 7, -1");
            relloc.add("2, 5, 3");
            relloc.add("2, 6, 3");
            relloc.add("2, 7, 3");
            relloc.add("1, 6, 3");
            relloc.add("0, 5, 3");
            relloc.add("0, 6, 3");
            relloc.add("0, 7, 3");
            relloc.add("-1, 6, 3");
            relloc.add("-2, 5, 3");
            relloc.add("-2, 6, 3");
            relloc.add("-2, 7, 3");

            new BukkitRunnable() {

                int[] i = new int[]{0};

                @Override
                public void run() {

                    loc.getWorld().playSound(loc, Sound.CHICKEN_EGG_POP, 1.0F, 0.5F);

                    if (relloc.size() + 1 == i[0] + 1) {
                        cancel();
                    } else {
                        String c1 = (String)relloc.get(i[0]);
                        if (c1.contains("ladder")) {
                            int ldata = Integer.parseInt(c1.split("ladder")[1]);
//                            new NewPlaceBlock(chest, c1, color, p, true, ldata);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, true, ldata);
                        } else {
//                            new NewPlaceBlock(chest, c1, color, p, false, 0);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, false, 0);
                        }

                        if (relloc.size() + 1 == i[0] + 2) {
                            cancel();
                        } else {
                            String c2 = (String)relloc.get(i[0] + 1);
                            if (c2.contains("ladder")) {
                                int ldatax = Integer.parseInt(c2.split("ladder")[1]);
//                                new NewPlaceBlock(chest, c2, color, p, true, ldatax);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, true, ldatax);
                            } else {
                                // new NewPlaceBlock(chest, c2, color, p, false, 0);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, false, 0);
                            }

                            i[0] += 2;
                        }
                    }

                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

        } else if (225.0 <= rotation && rotation < 315.0) {
            // North

            List<String> relloc = new ArrayList();
            relloc.add("-1, 0, -2");
            relloc.add("-2, 0, -1");
            relloc.add("-2, 0, 0");
            relloc.add("-1, 0, 1");
            relloc.add("0, 0, 1");
            relloc.add("1, 0, 1");
            relloc.add("2, 0, 0");
            relloc.add("2, 0, -1");
            relloc.add("1, 0, -2");
            relloc.add("0, 0, 0, ladder2");
            relloc.add("-1, 1, -2");
            relloc.add("-2, 1, -1");
            relloc.add("-2, 1, 0");
            relloc.add("-1, 1, 1");
            relloc.add("0, 1, 1");
            relloc.add("1, 1, 1");
            relloc.add("2, 1, 0");
            relloc.add("2, 1, -1");
            relloc.add("1, 1, -2");
            relloc.add("0, 1, 0, ladder2");
            relloc.add("-1, 2, -2");
            relloc.add("-2, 2, -1");
            relloc.add("-2, 2, 0");
            relloc.add("-1, 2, 1");
            relloc.add("0, 2, 1");
            relloc.add("1, 2, 1");
            relloc.add("2, 2, 0");
            relloc.add("2, 2, -1");
            relloc.add("1, 2, -2");
            relloc.add("0, 2, 0, ladder2");
            relloc.add("0, 3, -2");
            relloc.add("-1, 3, -2");
            relloc.add("-2, 3, -1");
            relloc.add("-2, 3, 0");
            relloc.add("-1, 3, 1");
            relloc.add("0, 3, 1");
            relloc.add("1, 3, 1");
            relloc.add("2, 3, 0");
            relloc.add("2, 3, -1");
            relloc.add("1, 3, -2");
            relloc.add("0, 3, 0, ladder2");
            relloc.add("0, 4, -2");
            relloc.add("-1, 4, -2");
            relloc.add("-2, 4, -1");
            relloc.add("-2, 4, 0");
            relloc.add("-1, 4, 1");
            relloc.add("0, 4, 1");
            relloc.add("1, 4, 1");
            relloc.add("2, 4, 0");
            relloc.add("2, 4, -1");
            relloc.add("1, 4, -2");
            relloc.add("0, 4, 0, ladder2");
            relloc.add("-2, 5, 1");
            relloc.add("-2, 5, 0");
            relloc.add("-2, 5, -1");
            relloc.add("-2, 5, -2");
            relloc.add("-1, 5, 1");
            relloc.add("-1, 5, 0");
            relloc.add("-1, 5, -1");
            relloc.add("-1, 5, -2");
            relloc.add("0, 5, 1");
            relloc.add("0, 5, -1");
            relloc.add("0, 5, -2");
            relloc.add("1, 5, 1");
            relloc.add("0, 5, 0, ladder2");
            relloc.add("1, 5, 0");
            relloc.add("1, 5, -1");
            relloc.add("1, 5, -2");
            relloc.add("2, 5, 1");
            relloc.add("2, 5, 0");
            relloc.add("2, 5, -1");
            relloc.add("2, 5, -2");
            relloc.add("-3, 5, -2");
            relloc.add("-3, 6, -2");
            relloc.add("-3, 7, -2");
            relloc.add("-3, 6, -1");
            relloc.add("-3, 6, 0");
            relloc.add("-3, 5, 1");
            relloc.add("-3, 6, 1");
            relloc.add("-3, 7, 1");
            relloc.add("-2, 5, 2");
            relloc.add("-2, 6, 2");
            relloc.add("-2, 7, 2");
            relloc.add("-1, 6, 2");
            relloc.add("0, 5, 2");
            relloc.add("0, 6, 2");
            relloc.add("0, 7, 2");
            relloc.add("1, 6, 2");
            relloc.add("2, 5, 2");
            relloc.add("2, 6, 2");
            relloc.add("2, 7, 2");
            relloc.add("3, 5, -2");
            relloc.add("3, 6, -2");
            relloc.add("3, 7, -2");
            relloc.add("3, 6, -1");
            relloc.add("3, 6, 0");
            relloc.add("3, 5, 1");
            relloc.add("3, 6, 1");
            relloc.add("3, 7, 1");
            relloc.add("-2, 5, -3");
            relloc.add("-2, 6, -3");
            relloc.add("-2, 7, -3");
            relloc.add("-1, 6, -3");
            relloc.add("0, 5, -3");
            relloc.add("0, 6, -3");
            relloc.add("0, 7, -3");
            relloc.add("1, 6, -3");
            relloc.add("2, 5, -3");
            relloc.add("2, 6, -3");
            relloc.add("2, 7, -3");

            new BukkitRunnable() {

                int[] i = new int[]{0};

                @Override
                public void run() {

                    loc.getWorld().playSound(loc, Sound.CHICKEN_EGG_POP, 1.0F, 0.5F);

                    if (relloc.size() + 1 == i[0] + 1) {
                        cancel();
                    } else {
                        String c1 = (String)relloc.get(i[0]);
                        if (c1.contains("ladder")) {
                            int ldata = Integer.parseInt(c1.split("ladder")[1]);
//                            new NewPlaceBlock(chest, c1, color, p, true, ldata);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, true, ldata);
                        } else {
//                            new NewPlaceBlock(chest, c1, color, p, false, 0);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, false, 0);
                        }

                        if (relloc.size() + 1 == i[0] + 2) {
                            cancel();
                        } else {
                            String c2 = (String)relloc.get(i[0] + 1);
                            if (c2.contains("ladder")) {
                                int ldatax = Integer.parseInt(c2.split("ladder")[1]);
//                                new NewPlaceBlock(chest, c2, color, p, true, ldatax);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, true, ldatax);
                            } else {
                                // new NewPlaceBlock(chest, c2, color, p, false, 0);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, false, 0);
                            }

                            i[0] += 2;
                        }
                    }

                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

        } else if (135.0 <= rotation && rotation < 225.0) {
            // West

            List<String> relloc = new ArrayList();
            relloc.add("-2, 0, 1");
            relloc.add("-1, 0, 2");
            relloc.add("0, 0, 2");
            relloc.add("1, 0, 1");
            relloc.add("1, 0, 0");
            relloc.add("1, 0, -1");
            relloc.add("0, 0, -2");
            relloc.add("-1, 0, -2");
            relloc.add("-2, 0, -1");
            relloc.add("0, 0, 0, ladder4");
            relloc.add("-2, 1, 1");
            relloc.add("-1, 1, 2");
            relloc.add("0, 1, 2");
            relloc.add("1, 1, 1");
            relloc.add("1, 1, 0");
            relloc.add("1, 1, -1");
            relloc.add("0, 1, -2");
            relloc.add("-1, 1, -2");
            relloc.add("-2, 1, -1");
            relloc.add("0, 1, 0, ladder4");
            relloc.add("-2, 2, 1");
            relloc.add("-1, 2, 2");
            relloc.add("0, 2, 2");
            relloc.add("1, 2, 1");
            relloc.add("1, 2, 0");
            relloc.add("1, 2, -1");
            relloc.add("0, 2, -2");
            relloc.add("-1, 2, -2");
            relloc.add("-2, 2, -1");
            relloc.add("0, 2, 0, ladder4");
            relloc.add("-2, 3, 0");
            relloc.add("-2, 3, 1");
            relloc.add("-1, 3, 2");
            relloc.add("0, 3, 2");
            relloc.add("1, 3, 1");
            relloc.add("1, 3, 0");
            relloc.add("1, 3, -1");
            relloc.add("0, 3, -2");
            relloc.add("-1, 3, -2");
            relloc.add("-2, 3, -1");
            relloc.add("0, 3, 0, ladder4");
            relloc.add("-2, 4, 0");
            relloc.add("-2, 4, 1");
            relloc.add("-1, 4, 2");
            relloc.add("0, 4, 2");
            relloc.add("1, 4, 1");
            relloc.add("1, 4, 0");
            relloc.add("1, 4, -1");
            relloc.add("0, 4, -2");
            relloc.add("-1, 4, -2");
            relloc.add("-2, 4, -1");
            relloc.add("0, 4, 0, ladder4");
            relloc.add("1, 5, 2");
            relloc.add("0, 5, 2");
            relloc.add("-1, 5, 2");
            relloc.add("-2, 5, 2");
            relloc.add("1, 5, 1");
            relloc.add("0, 5, 1");
            relloc.add("-1, 5, 1");
            relloc.add("-2, 5, 1");
            relloc.add("1, 5, 0");
            relloc.add("-1, 5, 0");
            relloc.add("-2, 5, 0");
            relloc.add("1, 5, -1");
            relloc.add("0, 5, -1");
            relloc.add("-1, 5, -1");
            relloc.add("-2, 5, -1");
            relloc.add("0, 5, 0, ladder4");
            relloc.add("1, 5, -2");
            relloc.add("0, 5, -2");
            relloc.add("-1, 5, -2");
            relloc.add("-2, 5, -2");
            relloc.add("-2, 5, 3");
            relloc.add("-2, 6, 3");
            relloc.add("-2, 7, 3");
            relloc.add("-1, 6, 3");
            relloc.add("0, 6, 3");
            relloc.add("1, 5, 3");
            relloc.add("1, 6, 3");
            relloc.add("1, 7, 3");
            relloc.add("2, 5, 2");
            relloc.add("2, 6, 2");
            relloc.add("2, 7, 2");
            relloc.add("2, 6, 1");
            relloc.add("2, 5, 0");
            relloc.add("2, 6, 0");
            relloc.add("2, 7, 0");
            relloc.add("2, 6, -1");
            relloc.add("2, 5, -2");
            relloc.add("2, 6, -2");
            relloc.add("2, 7, -2");
            relloc.add("-2, 5, -3");
            relloc.add("-2, 6, -3");
            relloc.add("-2, 7, -3");
            relloc.add("-1, 6, -3");
            relloc.add("0, 6, -3");
            relloc.add("1, 5, -3");
            relloc.add("1, 6, -3");
            relloc.add("1, 7, -3");
            relloc.add("-3, 5, 2");
            relloc.add("-3, 6, 2");
            relloc.add("-3, 7, 2");
            relloc.add("-3, 6, 1");
            relloc.add("-3, 5, 0");
            relloc.add("-3, 6, 0");
            relloc.add("-3, 7, 0");
            relloc.add("-3, 6, -1");
            relloc.add("-3, 5, -2");
            relloc.add("-3, 6, -2");
            relloc.add("-3, 7, -2");

            new BukkitRunnable() {

                int[] i = new int[]{0};

                @Override
                public void run() {

                    loc.getWorld().playSound(loc, Sound.CHICKEN_EGG_POP, 1.0F, 0.5F);

                    if (relloc.size() + 1 == i[0] + 1) {
                        cancel();
                    } else {
                        String c1 = (String)relloc.get(i[0]);
                        if (c1.contains("ladder")) {
                            int ldata = Integer.parseInt(c1.split("ladder")[1]);
//                            new NewPlaceBlock(chest, c1, color, p, true, ldata);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, true, ldata);
                        } else {
//                            new NewPlaceBlock(chest, c1, color, p, false, 0);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, false, 0);
                        }

                        if (relloc.size() + 1 == i[0] + 2) {
                            cancel();
                        } else {
                            String c2 = (String)relloc.get(i[0] + 1);
                            if (c2.contains("ladder")) {
                                int ldatax = Integer.parseInt(c2.split("ladder")[1]);
//                                new NewPlaceBlock(chest, c2, color, p, true, ldatax);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, true, ldatax);
                            } else {
                                // new NewPlaceBlock(chest, c2, color, p, false, 0);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, false, 0);
                            }

                            i[0] += 2;
                        }
                    }

                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

        } else if (0.0 <= rotation && rotation < 45.0) {
            // East

            List<String> relloc = new ArrayList();
            relloc.add("2, 0, -1");
            relloc.add("1, 0, -2");
            relloc.add("0, 0, -2");
            relloc.add("-1, 0, -1");
            relloc.add("-1, 0, 0");
            relloc.add("-1, 0, 1");
            relloc.add("0, 0, 2");
            relloc.add("1, 0, 2");
            relloc.add("2, 0, 1");
            relloc.add("0, 0, 0, ladder5");
            relloc.add("2, 1, -1");
            relloc.add("1, 1, -2");
            relloc.add("0, 1, -2");
            relloc.add("-1, 1, -1");
            relloc.add("-1, 1, 0");
            relloc.add("-1, 1, 1");
            relloc.add("0, 1, 2");
            relloc.add("1, 1, 2");
            relloc.add("2, 1, 1");
            relloc.add("0, 1, 0, ladder5");
            relloc.add("2, 2, -1");
            relloc.add("1, 2, -2");
            relloc.add("0, 2, -2");
            relloc.add("-1, 2, -1");
            relloc.add("-1, 2, 0");
            relloc.add("-1, 2, 1");
            relloc.add("0, 2, 2");
            relloc.add("1, 2, 2");
            relloc.add("2, 2, 1");
            relloc.add("0, 2, 0, ladder5");
            relloc.add("2, 3, 0");
            relloc.add("2, 3, -1");
            relloc.add("1, 3, -2");
            relloc.add("0, 3, -2");
            relloc.add("-1, 3, -1");
            relloc.add("-1, 3, 0");
            relloc.add("-1, 3, 1");
            relloc.add("0, 3, 2");
            relloc.add("1, 3, 2");
            relloc.add("2, 3, 1");
            relloc.add("0, 3, 0, ladder5");
            relloc.add("2, 4, 0");
            relloc.add("2, 4, -1");
            relloc.add("1, 4, -2");
            relloc.add("0, 4, -2");
            relloc.add("-1, 4, -1");
            relloc.add("-1, 4, 0");
            relloc.add("-1, 4, 1");
            relloc.add("0, 4, 2");
            relloc.add("1, 4, 2");
            relloc.add("2, 4, 1");
            relloc.add("0, 4, 0, ladder5");
            relloc.add("-1, 5, -2");
            relloc.add("0, 5, -2");
            relloc.add("1, 5, -2");
            relloc.add("2, 5, -2");
            relloc.add("-1, 5, -1");
            relloc.add("0, 5, -1");
            relloc.add("1, 5, -1");
            relloc.add("2, 5, -1");
            relloc.add("-1, 5, 0");
            relloc.add("1, 5, 0");
            relloc.add("2, 5, 0");
            relloc.add("-1, 5, 1");
            relloc.add("0, 5, 0, ladder5");
            relloc.add("0, 5, 1");
            relloc.add("1, 5, 1");
            relloc.add("2, 5, 1");
            relloc.add("-1, 5, 2");
            relloc.add("0, 5, 2");
            relloc.add("1, 5, 2");
            relloc.add("2, 5, 2");
            relloc.add("2, 5, -3");
            relloc.add("2, 6, -3");
            relloc.add("2, 7, -3");
            relloc.add("1, 6, -3");
            relloc.add("0, 6, -3");
            relloc.add("-1, 5, -3");
            relloc.add("-1, 6, -3");
            relloc.add("-1, 7, -3");
            relloc.add("-2, 5, -2");
            relloc.add("-2, 6, -2");
            relloc.add("-2, 7, -2");
            relloc.add("-2, 6, -1");
            relloc.add("-2, 5, 0");
            relloc.add("-2, 6, 0");
            relloc.add("-2, 7, 0");
            relloc.add("-2, 6, 1");
            relloc.add("-2, 5, 2");
            relloc.add("-2, 6, 2");
            relloc.add("-2, 7, 2");
            relloc.add("2, 5, 3");
            relloc.add("2, 6, 3");
            relloc.add("2, 7, 3");
            relloc.add("1, 6, 3");
            relloc.add("0, 6, 3");
            relloc.add("-1, 5, 3");
            relloc.add("-1, 6, 3");
            relloc.add("-1, 7, 3");
            relloc.add("3, 5, -2");
            relloc.add("3, 6, -2");
            relloc.add("3, 7, -2");
            relloc.add("3, 6, -1");
            relloc.add("3, 5, 0");
            relloc.add("3, 6, 0");
            relloc.add("3, 7, 0");
            relloc.add("3, 6, 1");
            relloc.add("3, 5, 2");
            relloc.add("3, 6, 2");
            relloc.add("3, 7, 2");

            new BukkitRunnable() {

                int[] i = new int[]{0};

                @Override
                public void run() {

                    loc.getWorld().playSound(loc, Sound.CHICKEN_EGG_POP, 1.0F, 0.5F);

                    if (relloc.size() + 1 == i[0] + 1) {
                        cancel();
                    } else {
                        String c1 = (String)relloc.get(i[0]);
                        if (c1.contains("ladder")) {
                            int ldata = Integer.parseInt(c1.split("ladder")[1]);
//                            new NewPlaceBlock(chest, c1, color, p, true, ldata);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, true, ldata);
                        } else {
//                            new NewPlaceBlock(chest, c1, color, p, false, 0);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, false, 0);
                        }

                        if (relloc.size() + 1 == i[0] + 2) {
                            cancel();
                        } else {
                            String c2 = (String)relloc.get(i[0] + 1);
                            if (c2.contains("ladder")) {
                                int ldatax = Integer.parseInt(c2.split("ladder")[1]);
//                                new NewPlaceBlock(chest, c2, color, p, true, ldatax);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, true, ldatax);
                            } else {
                                // new NewPlaceBlock(chest, c2, color, p, false, 0);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, false, 0);
                            }

                            i[0] += 2;
                        }
                    }

                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

        } else if (315.0 <= rotation && rotation < 360.0) {
            // East

            List<String> relloc = new ArrayList();
            relloc.add("2, 0, -1");
            relloc.add("1, 0, -2");
            relloc.add("0, 0, -2");
            relloc.add("-1, 0, -1");
            relloc.add("-1, 0, 0");
            relloc.add("-1, 0, 1");
            relloc.add("0, 0, 2");
            relloc.add("1, 0, 2");
            relloc.add("2, 0, 1");
            relloc.add("0, 0, 0, ladder5");
            relloc.add("2, 1, -1");
            relloc.add("1, 1, -2");
            relloc.add("0, 1, -2");
            relloc.add("-1, 1, -1");
            relloc.add("-1, 1, 0");
            relloc.add("-1, 1, 1");
            relloc.add("0, 1, 2");
            relloc.add("1, 1, 2");
            relloc.add("2, 1, 1");
            relloc.add("0, 1, 0, ladder5");
            relloc.add("2, 2, -1");
            relloc.add("1, 2, -2");
            relloc.add("0, 2, -2");
            relloc.add("-1, 2, -1");
            relloc.add("-1, 2, 0");
            relloc.add("-1, 2, 1");
            relloc.add("0, 2, 2");
            relloc.add("1, 2, 2");
            relloc.add("2, 2, 1");
            relloc.add("0, 2, 0, ladder5");
            relloc.add("2, 3, 0");
            relloc.add("2, 3, -1");
            relloc.add("1, 3, -2");
            relloc.add("0, 3, -2");
            relloc.add("-1, 3, -1");
            relloc.add("-1, 3, 0");
            relloc.add("-1, 3, 1");
            relloc.add("0, 3, 2");
            relloc.add("1, 3, 2");
            relloc.add("2, 3, 1");
            relloc.add("0, 3, 0, ladder5");
            relloc.add("2, 4, 0");
            relloc.add("2, 4, -1");
            relloc.add("1, 4, -2");
            relloc.add("0, 4, -2");
            relloc.add("-1, 4, -1");
            relloc.add("-1, 4, 0");
            relloc.add("-1, 4, 1");
            relloc.add("0, 4, 2");
            relloc.add("1, 4, 2");
            relloc.add("2, 4, 1");
            relloc.add("0, 4, 0, ladder5");
            relloc.add("-1, 5, -2");
            relloc.add("0, 5, -2");
            relloc.add("1, 5, -2");
            relloc.add("2, 5, -2");
            relloc.add("-1, 5, -1");
            relloc.add("0, 5, -1");
            relloc.add("1, 5, -1");
            relloc.add("2, 5, -1");
            relloc.add("-1, 5, 0");
            relloc.add("1, 5, 0");
            relloc.add("2, 5, 0");
            relloc.add("-1, 5, 1");
            relloc.add("0, 5, 0, ladder5");
            relloc.add("0, 5, 1");
            relloc.add("1, 5, 1");
            relloc.add("2, 5, 1");
            relloc.add("-1, 5, 2");
            relloc.add("0, 5, 2");
            relloc.add("1, 5, 2");
            relloc.add("2, 5, 2");
            relloc.add("2, 5, -3");
            relloc.add("2, 6, -3");
            relloc.add("2, 7, -3");
            relloc.add("1, 6, -3");
            relloc.add("0, 6, -3");
            relloc.add("-1, 5, -3");
            relloc.add("-1, 6, -3");
            relloc.add("-1, 7, -3");
            relloc.add("-2, 5, -2");
            relloc.add("-2, 6, -2");
            relloc.add("-2, 7, -2");
            relloc.add("-2, 6, -1");
            relloc.add("-2, 5, 0");
            relloc.add("-2, 6, 0");
            relloc.add("-2, 7, 0");
            relloc.add("-2, 6, 1");
            relloc.add("-2, 5, 2");
            relloc.add("-2, 6, 2");
            relloc.add("-2, 7, 2");
            relloc.add("2, 5, 3");
            relloc.add("2, 6, 3");
            relloc.add("2, 7, 3");
            relloc.add("1, 6, 3");
            relloc.add("0, 6, 3");
            relloc.add("-1, 5, 3");
            relloc.add("-1, 6, 3");
            relloc.add("-1, 7, 3");
            relloc.add("3, 5, -2");
            relloc.add("3, 6, -2");
            relloc.add("3, 7, -2");
            relloc.add("3, 6, -1");
            relloc.add("3, 5, 0");
            relloc.add("3, 6, 0");
            relloc.add("3, 7, 0");
            relloc.add("3, 6, 1");
            relloc.add("3, 5, 2");
            relloc.add("3, 6, 2");
            relloc.add("3, 7, 2");

            new BukkitRunnable() {

                int[] i = new int[]{0};

                @Override
                public void run() {

                    loc.getWorld().playSound(loc, Sound.CHICKEN_EGG_POP, 1.0F, 0.5F);

                    if (relloc.size() + 1 == i[0] + 1) {
                        cancel();
                    } else {
                        String c1 = (String)relloc.get(i[0]);
                        if (c1.contains("ladder")) {
                            int ldata = Integer.parseInt(c1.split("ladder")[1]);
//                            new NewPlaceBlock(chest, c1, color, p, true, ldata);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, true, ldata);
                        } else {
//                            new NewPlaceBlock(chest, c1, color, p, false, 0);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, false, 0);
                        }

                        if (relloc.size() + 1 == i[0] + 2) {
                            cancel();
                        } else {
                            String c2 = (String)relloc.get(i[0] + 1);
                            if (c2.contains("ladder")) {
                                int ldatax = Integer.parseInt(c2.split("ladder")[1]);
//                                new NewPlaceBlock(chest, c2, color, p, true, ldatax);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, true, ldatax);
                            } else {
                                // new NewPlaceBlock(chest, c2, color, p, false, 0);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, false, 0);
                            }

                            i[0] += 2;
                        }
                    }

                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

        } else {

            List<String> relloc = new ArrayList();
            relloc.add("-1, 0, -2");
            relloc.add("-2, 0, -1");
            relloc.add("-2, 0, 0");
            relloc.add("-1, 0, 1");
            relloc.add("0, 0, 1");
            relloc.add("1, 0, 1");
            relloc.add("2, 0, 0");
            relloc.add("2, 0, -1");
            relloc.add("1, 0, -2");
            relloc.add("0, 0, 0, ladder2");
            relloc.add("-1, 1, -2");
            relloc.add("-2, 1, -1");
            relloc.add("-2, 1, 0");
            relloc.add("-1, 1, 1");
            relloc.add("0, 1, 1");
            relloc.add("1, 1, 1");
            relloc.add("2, 1, 0");
            relloc.add("2, 1, -1");
            relloc.add("1, 1, -2");
            relloc.add("0, 1, 0, ladder2");
            relloc.add("-1, 2, -2");
            relloc.add("-2, 2, -1");
            relloc.add("-2, 2, 0");
            relloc.add("-1, 2, 1");
            relloc.add("0, 2, 1");
            relloc.add("1, 2, 1");
            relloc.add("2, 2, 0");
            relloc.add("2, 2, -1");
            relloc.add("1, 2, -2");
            relloc.add("0, 2, 0, ladder2");
            relloc.add("0, 3, -2");
            relloc.add("-1, 3, -2");
            relloc.add("-2, 3, -1");
            relloc.add("-2, 3, 0");
            relloc.add("-1, 3, 1");
            relloc.add("0, 3, 1");
            relloc.add("1, 3, 1");
            relloc.add("2, 3, 0");
            relloc.add("2, 3, -1");
            relloc.add("1, 3, -2");
            relloc.add("0, 3, 0, ladder2");
            relloc.add("0, 4, -2");
            relloc.add("-1, 4, -2");
            relloc.add("-2, 4, -1");
            relloc.add("-2, 4, 0");
            relloc.add("-1, 4, 1");
            relloc.add("0, 4, 1");
            relloc.add("1, 4, 1");
            relloc.add("2, 4, 0");
            relloc.add("2, 4, -1");
            relloc.add("1, 4, -2");
            relloc.add("0, 4, 0, ladder2");
            relloc.add("-2, 5, 1");
            relloc.add("-2, 5, 0");
            relloc.add("-2, 5, -1");
            relloc.add("-2, 5, -2");
            relloc.add("-1, 5, 1");
            relloc.add("-1, 5, 0");
            relloc.add("-1, 5, -1");
            relloc.add("-1, 5, -2");
            relloc.add("0, 5, 1");
            relloc.add("0, 5, -1");
            relloc.add("0, 5, -2");
            relloc.add("1, 5, 1");
            relloc.add("0, 5, 0, ladder2");
            relloc.add("1, 5, 0");
            relloc.add("1, 5, -1");
            relloc.add("1, 5, -2");
            relloc.add("2, 5, 1");
            relloc.add("2, 5, 0");
            relloc.add("2, 5, -1");
            relloc.add("2, 5, -2");
            relloc.add("-3, 5, -2");
            relloc.add("-3, 6, -2");
            relloc.add("-3, 7, -2");
            relloc.add("-3, 6, -1");
            relloc.add("-3, 6, 0");
            relloc.add("-3, 5, 1");
            relloc.add("-3, 6, 1");
            relloc.add("-3, 7, 1");
            relloc.add("-2, 5, 2");
            relloc.add("-2, 6, 2");
            relloc.add("-2, 7, 2");
            relloc.add("-1, 6, 2");
            relloc.add("0, 5, 2");
            relloc.add("0, 6, 2");
            relloc.add("0, 7, 2");
            relloc.add("1, 6, 2");
            relloc.add("2, 5, 2");
            relloc.add("2, 6, 2");
            relloc.add("2, 7, 2");
            relloc.add("3, 5, -2");
            relloc.add("3, 6, -2");
            relloc.add("3, 7, -2");
            relloc.add("3, 6, -1");
            relloc.add("3, 6, 0");
            relloc.add("3, 5, 1");
            relloc.add("3, 6, 1");
            relloc.add("3, 7, 1");
            relloc.add("-2, 5, -3");
            relloc.add("-2, 6, -3");
            relloc.add("-2, 7, -3");
            relloc.add("-1, 6, -3");
            relloc.add("0, 5, -3");
            relloc.add("0, 6, -3");
            relloc.add("0, 7, -3");
            relloc.add("1, 6, -3");
            relloc.add("2, 5, -3");
            relloc.add("2, 6, -3");
            relloc.add("2, 7, -3");

            new BukkitRunnable() {

                int[] i = new int[]{0};

                @Override
                public void run() {

                    loc.getWorld().playSound(loc, Sound.CHICKEN_EGG_POP, 1.0F, 0.5F);

                    if (relloc.size() + 1 == i[0] + 1) {
                        cancel();
                    } else {
                        String c1 = (String)relloc.get(i[0]);
                        if (c1.contains("ladder")) {
                            int ldata = Integer.parseInt(c1.split("ladder")[1]);
//                            new NewPlaceBlock(chest, c1, color, p, true, ldata);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, true, ldata);
                        } else {
//                            new NewPlaceBlock(chest, c1, color, p, false, 0);
                            blockplace_utils(loc.getBlock(), c1, (int) wool.getDurability(), p, false, 0);
                        }

                        if (relloc.size() + 1 == i[0] + 2) {
                            cancel();
                        } else {
                            String c2 = (String)relloc.get(i[0] + 1);
                            if (c2.contains("ladder")) {
                                int ldatax = Integer.parseInt(c2.split("ladder")[1]);
//                                new NewPlaceBlock(chest, c2, color, p, true, ldatax);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, true, ldatax);
                            } else {
                                // new NewPlaceBlock(chest, c2, color, p, false, 0);
                                blockplace_utils(loc.getBlock(), c2, (int) wool.getDurability(), p, false, 0);
                            }

                            i[0] += 2;
                        }
                    }

                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,1);

        }

    }

    // 祖传代码的助手 (?
    private static void blockplace_utils(Block b, String xyz, int color_data, Player p, boolean ladder, int ladderdata) {

        int x = Integer.parseInt(xyz.split(", ")[0]);
        int y = Integer.parseInt(xyz.split(", ")[1]);
        int z = Integer.parseInt(xyz.split(", ")[2]);


        if (b.getRelative(x, y, z).getType().equals(Material.AIR)) {

            if (!ladder) {

                if (!PlayerBlocks.safeMap_blocks.contains(b)) {
                    b.getRelative(x, y, z).setType(Material.WOOL);
                    b.getRelative(x, y, z).setData((byte) color_data);
                    PlayerBlocks.player_blocks.add(b.getRelative(x, y, z));
                }

            } else {
                if (!PlayerBlocks.safeMap_blocks.contains(b)) {
                    b.getRelative(x, y, z).setType(Material.LADDER);
                    b.getRelative(x, y, z).setData((byte)ladderdata);
                    PlayerBlocks.player_blocks.add(b.getRelative(x, y, z));
                }

            }

            b.getRelative(x, y, z).getState().update();

        }

    }


}
