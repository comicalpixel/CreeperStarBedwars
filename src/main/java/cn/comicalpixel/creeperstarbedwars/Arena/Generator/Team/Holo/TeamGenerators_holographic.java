package cn.comicalpixel.creeperstarbedwars.Arena.Generator.Team.Holo;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.HolographicAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TeamGenerators_holographic {
    private final List<HolographicAPI> ablocks;
    private final List<HolographicAPI> atitles;
    private final List<HolographicAPI> btitles;
    private final Map<String, HolographicAPI> pbtitles;
    private final HashMap<HolographicAPI, Location> armor_locations;
    private final HashMap<HolographicAPI, Boolean> armor_upward;
    private final HashMap<HolographicAPI, Integer> armor_algebra;

    public TeamGenerators_holographic() {
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
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 1L, 1L);
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
//        for (Location loc : Bedwars_Config.generators_diamond) {
//                HolographicAPI holo = new HolographicAPI(loc.clone().add(0, 1.5 - 1.0, 0), null);
//                Material material = (Material.AIR);
//                holo.setEquipment(Collections.singletonList(new ItemStack(material)));
//                Bukkit.getScheduler().runTaskLater(ComicalPixel_NewBedwars.getInstance(), () -> {
//                    for (Player player : Bukkit.getOnlinePlayers()) {
//                        holo.display(player.getPlayer());
//                    }
//                }, 20L);
//            ArrayList<String> titles = new ArrayList<>();
//            titles.add("§b附近方块人都能吸到捏 §6" + TemGenHD_message_task.message);
//            this.setArmorStandRun(holo, titles, new ItemStack(material), loc);
//        }

        List<Location> loc_list = new ArrayList<>();

        // 队伍
        if (TeamManager.teams.contains("RED")) {
            loc_list.add(GameData_cfg.team_red_generator.clone().add(-0.5, 0, -0.5));
        }
        if (TeamManager.teams.contains("BLUE")) {
            loc_list.add(GameData_cfg.team_blue_generator.clone().add(-0.5, 0, -0.5));
        }
        if (TeamManager.teams.contains("GREEN")) {
            loc_list.add(GameData_cfg.team_green_generator.clone().add(-0.5, 0, -0.5));
        }
        if (TeamManager.teams.contains("YELLOW")) {
            loc_list.add(GameData_cfg.team_yellow_generator.clone().add(-0.5, 0, -0.5));
        }
        if (TeamManager.teams.contains("PINK")) {
            loc_list.add(GameData_cfg.team_pink_generator.clone().add(-0.5, 0, -0.5));
        }
        if (TeamManager.teams.contains("AQUA")) {
            loc_list.add(GameData_cfg.team_aqua_generator.clone().add(-0.5, 0, -0.5));
        }
        if (TeamManager.teams.contains("GRAY")) {
            loc_list.add(GameData_cfg.team_gray_generator.clone().add(-0.5, 0, -0.5));
        }
        if (TeamManager.teams.contains("WHITE")) {
            loc_list.add(GameData_cfg.team_white_generator.clone().add(-0.5, 0, -0.5));
        }

           for (Location loc : loc_list) {
                HolographicAPI holo = new HolographicAPI(loc.clone().add(0.5, -1.5, 0.5), null);
                Material material = (Material.AIR);
                holo.setEquipment(Collections.singletonList(new ItemStack(material)));
                Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(), () -> {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        holo.display(player.getPlayer());
                    }
                }, 20L);
            ArrayList<String> titles = new ArrayList<>();
            titles.addAll(ConfigData.language_generatorholo_team_list);
            this.setArmorStandRun(holo, titles, new ItemStack(material), loc);
        }

    }

    private void setArmorStandRun(HolographicAPI holo, ArrayList<String> titles, ItemStack itemStack, Location resourcePoint) {
        Collections.reverse(titles);
        Location aslocation = holo.getLocation().clone().add(0, 1.7, 0);
        for (String title : titles) {
            this.setTitle(aslocation, title, itemStack, resourcePoint);
            aslocation.add(0, 0.0001, 0);
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
                    holo.setTitle(title.replace("{rywz}",ConfigData.language_generatorholo_ywz_all.get(new Random().nextInt(ConfigData.language_generatorholo_ywz_all.size()))));
                } else {
                    holo.remove();
                    atitles.remove(holo);
                    cancel();
                }
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 5L, 40L);
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
        if (algebra <= 30) {
            move_yaw += algebra * 0.62 * turn;
        } else {
            move_yaw += (59 - algebra) * 0.62 * turn;
        }
        if (algebra >= 9 && algebra <= 50) {
            move_y += 0.005 * turn;
        }
        location.setY(location.getY() + move_y);
        if (algebra >= 59) {
            armor_algebra.put(holo, 0);
            armor_upward.put(holo, !upward);
        }
        armor_algebra.put(holo, armor_algebra.get(holo) + 1);
        double yaw = location.getYaw();
        yaw += (move_yaw);
        yaw = yaw > 360 ? (yaw - 360) : yaw;
        yaw = yaw < -360 ? (yaw + 360) : yaw;
        location.setYaw((float) yaw);
        // holo.teleport(location);
    }
}
