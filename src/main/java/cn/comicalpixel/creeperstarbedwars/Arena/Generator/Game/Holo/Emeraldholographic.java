package cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.Holo;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.Game.ItemSpawn.Generators_Emerald;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.HolographicAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Emeraldholographic {
    private final List<HolographicAPI> ablocks;
    private final List<HolographicAPI> atitles;
    private final List<HolographicAPI> btitles;
    private final Map<String, HolographicAPI> pbtitles;
    private final HashMap<HolographicAPI, Location> armor_locations;
    private final HashMap<HolographicAPI, Boolean> armor_upward;
    private final HashMap<HolographicAPI, Integer> armor_algebra;

    public Emeraldholographic() {
        ablocks = new ArrayList<>();
        atitles = new ArrayList<>();
        btitles = new ArrayList<>();
        pbtitles = new HashMap<>();
        armor_locations = new HashMap<>();
        armor_upward = new HashMap<>();
        armor_algebra = new HashMap<>();
        this.setArmorStand();

        new BukkitRunnable() {

            @Override
            public void run() {
//                if (game.gameStats != GameStats.PLAYING || GamePlayer.Companion.getOnlinePlayers().isEmpty()) {
//                    cancel();
//                    for (HolographicAPI holo : ablocks) {
//                        holo.remove();
//                    }
//                    for (HolographicAPI holo : atitles) {
//                        holo.remove();
//                    }
//                    for (HolographicAPI holo : btitles) {
//                        holo.remove();
//                    }
//                    for (HolographicAPI holo : pbtitles.values()) {
//                        holo.remove();
//                    }
//                }
                if (GameStats.get() != 2 && GameStats.get() != 3) {
                    cancel();
                    for (HolographicAPI holo : ablocks) {
                        holo.remove();
                    }
                    for (HolographicAPI holo : atitles) {
                        holo.remove();
                    }
                    for (HolographicAPI holo : btitles) {
                        holo.remove();
                    }
                    for (HolographicAPI holo : pbtitles.values()) {
                        holo.remove();
                    }
                }
            }
        }.runTaskTimer(CreeperStarBedwars.getInstance(), 1L, 1L);
    }


    private void setArmorStand() {
//        for (GameResource resourceType : game.getResource()) {
//            if (resourceType.getType() == ResourceType.DIAMOND) {
//                HolographicAPI holo = new HolographicAPI(resourceType.getSpawn().clone().add(0, 1.5 - 0.35, 0), null);
//                Material material = (Material.DIAMOND_BLOCK);
//                holo.setEquipment(Collections.singletonList(new ItemStack(material)));
//                Bukkit.getScheduler().runTaskLater(XGBedWars.instance, () -> {
//                    for (GamePlayer player : GamePlayer.Companion.getOnlinePlayers()) {
//                        holo.display(player.getPlayer());
//                    }
//                }, 20L);
//                ArrayList<String> titles = new ArrayList<>();
//                titles.add("§b钻石");
//                titles.add("§e等级: §c{Countdown}");
//                if (resourceType.getSpawn().getWorld().getNearbyEntities(resourceType.getSpawn(), 1.0, 1.0, 1.0).size() <= 8) {
//                    titles.add("§e将在 §c{Time} §e秒后刷新！");
//                }
//                titles.add("{ProgressBar}");
//                this.setArmorStandRun(holo, titles, new ItemStack(material), resourceType.getSpawn());
//            }
//        }
        for (Location loc : ConfigUtils.getBlockStrLocationList(GameData_cfg.gameGenerator_emerald_locs)) {
                HolographicAPI holo = new HolographicAPI(loc.clone().add(0, 2.1, 0), null);
                Material material = (Material.EMERALD_BLOCK);
                holo.setEquipment(Collections.singletonList(new ItemStack(material)));
                Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(), () -> {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        holo.display(player.getPlayer());
                    }
                }, 20L);
            ArrayList<String> titles = new ArrayList<>();
//            titles.add(" §e等级: §c{Level} ");
//            titles.add(" §b钻石 ");
//            titles.add(" {timer_message} ");
            for (String s1 : ConfigData.language_generatorholo_emerald_list) {
                titles.add(s1);
            }
            this.setArmorStandRun(holo, titles, new ItemStack(material), loc);
        }
    }

    private void setArmorStandRun(HolographicAPI holo, ArrayList<String> titles, ItemStack itemStack, Location resourcePoint) {
        Collections.reverse(titles);
        Location aslocation = holo.getLocation().clone().add(0, 0.23, 0);
        for (String title : titles) {
            this.setTitle(aslocation, title, itemStack, resourcePoint);
            aslocation.add(0, 0.365, 0);
        }
        ablocks.add(holo);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (GameStats.get() == 2 || GameStats.get() == 3) {
                    moveArmorStand(holo);
                } else {
                    armor_locations.remove(holo);
                    armor_upward.remove(holo);
                    armor_algebra.remove(holo);
                    holo.remove();
                    ablocks.remove(holo);
                    cancel();
                }
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 1L, 1L);
    }

    private void setTitle(Location location, String title, ItemStack itemStack, Location resourcePoint) {
        HolographicAPI holo = new HolographicAPI(location, " ");
        atitles.add(holo);
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                holo.display(player.getPlayer());
            }
        }, 20L);
        new BukkitRunnable() {
            final int totalTime = 60;
            @Override
            public void run() {
                if (GameStats.get() == 2 || GameStats.get() == 3) {
//                    int currentTime = DiamondRunnable.Companion.getTime();
//                    int progress = (totalTime - currentTime) * 10 / totalTime;
//                    StringBuilder progressBar = new StringBuilder();
//                    for (int i = 0; i < 10; i++) {
//                        if (i < progress) {
//                            progressBar.append("§a➤");
//                        } else {
//                            progressBar.append("§7➤");
//                        }
//                    }
//                    String customName = title.replace("{ProgressBar}", progressBar.toString());
//                    customName = customName.replace("{Time}", currentTime + "");
//                    customName = customName.replace("{Countdown}", game.getUpdateDiamond() + "");
                    String customName = title.replace("{Level}", "{l}" + Generators_Emerald.level);
                    customName = title.replace("{level}", "{l}" + Generators_Emerald.level);
                    customName = customName.replace("{l}1","I").replace("{l}2","II").replace("{l}3","III");
                    String timer = " " + ChatColor.YELLOW + Generators_Emerald.timer_message + "s ";

                    if (Generators_Emerald.level == 1) {
                        if (Generators_Emerald.timer_message <= 30 && Generators_Emerald.timer_message >= 24) {
                            timer = timer + "§a➤ §7➤ §7➤ §7➤ §7➤ §7➤ ";
                        }
                        else if (Generators_Emerald.timer_message <= 23 && Generators_Emerald.timer_message >= 18) {
                            timer = timer + "§a➤ §a➤ §7➤ §7➤ §7➤ §7➤ ";
                        }
                        else if (Generators_Emerald.timer_message <= 17 && Generators_Emerald.timer_message >= 11) {
                            timer = timer + "§a➤ §a➤ §a➤ §7➤ §7➤ §7➤ ";
                        }
                        else if (Generators_Emerald.timer_message <= 10 && Generators_Emerald.timer_message >= 5) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §7➤ §7➤ ";
                        }
                        else if (Generators_Emerald.timer_message <= 4 && Generators_Emerald.timer_message >= 1) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §a➤ §7➤ ";
                        }
                        else if (Generators_Emerald.timer_message == 0) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §a➤ §a➤ ";
                        }
                    }
                    if (Generators_Emerald.level == 2) {
                        if (Generators_Emerald.timer_message <= 20 && Generators_Emerald.timer_message >= 16) {
                            timer = timer + "§a➤ §7➤ §7➤ §7➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 15 && Generators_Emerald.timer_message >= 12) {
                            timer = timer + "§a➤ §a➤ §7➤ §7➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 11 && Generators_Emerald.timer_message >= 8) {
                            timer = timer + "§a➤ §a➤ §a➤ §7➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 7 && Generators_Emerald.timer_message >= 4) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 3 && Generators_Emerald.timer_message >= 1) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §a➤ §7➤ ";
                        }
                        else if (Generators_Emerald.timer_message == 0) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §a➤ §a➤ ";
                        }
                    }
                    if (Generators_Emerald.level == 3) {
                        if (Generators_Emerald.timer_message <= 10 && Generators_Emerald.timer_message >= 9) {
                            timer = timer + "§a➤ §7➤ §7➤ §7➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 8 && Generators_Emerald.timer_message >= 7) {
                            timer = timer + "§a➤ §a➤ §7➤ §7➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 6 && Generators_Emerald.timer_message >= 5) {
                            timer = timer + "§a➤ §a➤ §a➤ §7➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 4 && Generators_Emerald.timer_message >= 3) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §7➤ §7➤ ";
                        }
                        if (Generators_Emerald.timer_message <= 2 && Generators_Emerald.timer_message >= 1) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §a➤ §7➤ ";
                        }
                        else if (Generators_Emerald.timer_message == 0) {
                            timer = timer + "§a➤ §a➤ §a➤ §a➤ §a➤ §a➤ ";
                        }
                    }

                    customName = customName.replace("{timer}", Generators_Emerald.timer_message+"");

                    customName = customName.replace("{timer_message}",timer);
                    holo.setTitle(customName);
                } else {
                    holo.remove();
                    atitles.remove(holo);
                    cancel();
                }
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 5L, 5L);
    }


    public void remove() {
        for (HolographicAPI holo : ablocks) {
            holo.remove();
        }
        for (HolographicAPI holo : atitles) {
            holo.remove();
        }
        for (HolographicAPI holo : btitles) {
            holo.remove();
        }
        for (HolographicAPI holo : pbtitles.values()) {
            holo.remove();
        }
    }

//    private void moveArmorStand(HolographicAPI holo) {
//        // 检查并初始化位置和角度，如果需要的话
//        if (!armor_locations.containsKey(holo)) {
//            armor_locations.put(holo, holo.getLocation().clone());
//        }
//        if (!armor_algebra.containsKey(holo)) {
//            armor_algebra.put(holo, 0); // 初始化角度
//        }
//
//        // 检查并初始化移动方向标志，如果需要的话
//        if (!armor_upward.containsKey(holo)) {
//            armor_upward.put(holo, true); // 初始状态为向上移动
//        }
//
//        Location location = armor_locations.get(holo);
//        boolean isUpward = armor_upward.get(holo); // 获取当前移动方向
//
//        // 定义上升和下降的位移
//        double moveDistance = 2; // 2格方块的高度
//
//        // 更新位置
//        if (isUpward) {
//            // 如果当前是向上移动
//            location.setY(location.getY() + moveDistance);
//        } else {
//            // 如果当前是向下移动
//            location.setY(location.getY() - moveDistance);
//        }
//
//        // 无论向上还是向下移动后，都切换移动方向
//        armor_upward.put(holo, !isUpward);
//
//        // 更新角度，这里假设角度变化与盔甲架移动无关
//        int algebra = armor_algebra.get(holo);
//        algebra = (algebra + 1) % 360; // 更新角度并保持在0-359之间
//        armor_algebra.put(holo, algebra);
//
//        // 更新盔甲架的位置和角度
//        holo.teleport(location); // 将盔甲架传送到新位置
//    }


//    private void moveArmorStand(HolographicAPI holo) {
//        if (!armor_locations.containsKey(holo)) {
//            armor_locations.put(holo, holo.getLocation().clone());
//        }
//        if (!armor_upward.containsKey(holo)) {
//            armor_upward.put(holo, true);
//        }
//        if (!armor_algebra.containsKey(holo)) {
//            armor_algebra.put(holo, 0);
//        }
//        Location location = armor_locations.get(holo);
//        Integer algebra = armor_algebra.get(holo);
//        boolean upward = armor_upward.get(holo);
//        double turn = 1;
//        if (!armor_upward.get(holo)) {
//            turn = -turn;
//        }
//        double move_yaw = 0;
//        double move_y = 0;
//        if (algebra <= 30) {
//            move_yaw += algebra * 0.62 * turn;
//        } else {
//            move_yaw += (59 - algebra) * 0.62 * turn;
//        }
//        if (algebra >= 9 && algebra <= 50) {
//            move_y += 0.005 * turn;
//        }
//        location.setY(location.getY() + move_y);
//        if (algebra >= 59) {
//            armor_algebra.put(holo, 0);
//            armor_upward.put(holo, !upward);
//        }
//        armor_algebra.put(holo, armor_algebra.get(holo) + 1);
//        double yaw = location.getYaw();
//        yaw += (move_yaw);
//        yaw = yaw > 360 ? (yaw - 360) : yaw;
//        yaw = yaw < -360 ? (yaw + 360) : yaw;
//        location.setYaw((float) yaw);
//        holo.teleport(location);
//    }

    private void moveArmorStand(HolographicAPI holo) {
        if (!armor_locations.containsKey(holo)) {
            armor_locations.put(holo, holo.getLocation().clone());
        }
        if (!armor_upward.containsKey(holo)) {
            armor_upward.put(holo, true);
        }
        if (!armor_algebra.containsKey(holo)) {
            armor_algebra.put(holo, 0);
        }
        Location location = armor_locations.get(holo);
        Integer algebra = armor_algebra.get(holo);
        boolean upward = armor_upward.get(holo);
        double turn = 1;
        if (!armor_upward.get(holo)) {
            turn = -turn;
        }
        double move_yaw = 0;
        double move_y = 0;

        if (algebra <= 2) {
            move_yaw += 0.5 * turn;
        }
        else if (algebra >= 3 && algebra <= 5+1) {
            move_yaw += 1.3 * turn;
        }
        else if (algebra >= 7 && algebra <= 15) {
            move_yaw += 1.9 * turn;
        }
        else if (algebra >= 16 && algebra <= 20) {
            move_yaw += 2.8 * turn;
        }
        else if (algebra >= 21 && algebra <= 25) {
            move_yaw += 3.5 * turn;
        }
        else if (algebra >= 26 && algebra <= 30) {
            move_yaw += 4.7 * turn;
        }
        else if (algebra >= 251 && algebra <= 255) {
            move_yaw += 4.7 * turn;
        }
        else if (algebra >= 256 && algebra <= 259) {
            move_yaw += 3.2 * turn;
        }
        else if (algebra >= 260 && algebra <= 269) {
            move_yaw += 2.5 * turn;
        }
        else if (algebra >= 270 && algebra <= 279) {
            move_yaw += 1.8 * turn;
        }
        else if (algebra >= 280 && algebra <= 285) {
            move_yaw += 1.3 * turn;
        }
        else if (algebra >= 286 && algebra <= 289) {
            move_yaw += 0.7 * turn;
        }
        else if (algebra >= 290) {
            move_yaw += 0.1 * turn;
        }

        else if (algebra >= 170 && algebra <= 210) {
            move_yaw += 12 * turn;
        }
        else {
            move_yaw += 7.7 * turn;
        }

        if (algebra >= 9 && algebra <= 280) {
            move_y += 0.0002 * turn;
        }
        location.setY(location.getY() + move_y);
        if (algebra >= 300) {
            armor_algebra.put(holo, 0);
            armor_upward.put(holo, !upward);
        }
        armor_algebra.put(holo, armor_algebra.get(holo) + 1);
        double yaw = location.getYaw();
        yaw += (move_yaw);
        yaw = yaw > 360 ? (yaw - 360) : yaw;
        yaw = yaw < -360 ? (yaw + 360) : yaw;
        location.setYaw((float) yaw);
        holo.teleport(location);
    }

}
