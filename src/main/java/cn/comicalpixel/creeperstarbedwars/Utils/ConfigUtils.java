package cn.comicalpixel.creeperstarbedwars.Utils;

import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ConfigUtils {

    // 获取物品
    public static ItemStack getItemStack(FileConfiguration config, String path) {
        List<?> itemList = config.getList(path);
        if (itemList != null) {
            for (Object obj : itemList) {
                if (obj instanceof ItemStack) {
                    ItemStack item = (ItemStack) obj;
                    return item;
                }
            }
        }
        return new ItemStack(Material.BEDROCK);
    }

    // 播放声音
    public static void playSound(Player player, FileConfiguration config, String type_path, int volume_path, float pitch_path) {
        player.playSound(player.getLocation(), Sound.valueOf(config.getString(type_path)), volume_path, pitch_path);
    }
    public static void playSound(Player player, FileConfiguration config, String path) {
        player.playSound(player.getLocation(), Sound.valueOf(config.getString(path + ".type")), config.getInt(path + ".volume"), (float) config.getDouble(path + ".pitch"));
    }
    public static void playSound(Player player, String splitstr_sound) {
        String[] sound_split = splitstr_sound.split(",");
        if (sound_split.length == 3) {
            player.playSound(player.getLocation(), Sound.valueOf(sound_split[0]), Integer.parseInt(sound_split[1]), (float) Double.parseDouble(sound_split[2]));
        }
    }
    public static void playSound(Location loc, FileConfiguration config, String type_path, int volume_path, float pitch_path) {
        loc.getWorld().playSound(loc, Sound.valueOf(config.getString(type_path)), volume_path, pitch_path);
    }
    public static void playSound(Location loc, FileConfiguration config, String path) {
        loc.getWorld().playSound(loc, Sound.valueOf(config.getString(path+".type")), config.getInt(path+".volume"), (float) config.getDouble(path+".pitch"));
    }
    public static void playSound(Location loc, String splitstr_sound) {
        String[] sound_split = splitstr_sound.split(", ");
        if (sound_split.length == 3) {
            loc.getWorld().playSound(loc, Sound.valueOf(sound_split[0]), Integer.parseInt(sound_split[1]), (float) Double.parseDouble(sound_split[2]));
        }
    }


    // 更好的读取配置文件
    public static String getString(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getString(path);
        } else {
            config.set(path, path + " not found");
        }
        return path+" not found!";
    }
    public static int getInt(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getInt(path);
        } else {
            config.set(path, 0);
        }
        return 0;
    }
    public static double getDouble(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getDouble(path);
        } else {
            config.set(path, 1.0);
        }
        return 0.0;
    }
    public static boolean getBoolean(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getBoolean(path);
        } else {
            config.set(path, false);
        }
        return false;
    }
    public static float getFloat(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getFloat(path);
        } else {
            config.set(path, 0.0f);
        }
        return 0.1f;
    }
    public static List<?> getList(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getList(path);
        } else {
            config.set(path, new ArrayList<>().add(null));
        }
        return new ArrayList<>(config.getList(path));
    }
    public static List<String> getStringList(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getStringList(path);
        } else {
            config.set(path, new ArrayList<>().add(null));
        }
        List<String> NL = new ArrayList<>();
        NL.add(null);
        return NL;
    }
    public static List<Integer> getIntList(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getIntegerList(path);
        } else {
            config.set(path, new ArrayList<>().add(1145));
        }
        List<Integer> NL = new ArrayList<>();
        NL.add(null);
        return NL;
    }
    public static List<Double> getDoubleList(FileConfiguration config, String path) {
        if (config.get(path) != null) {
            return config.getDoubleList(path);
        } else {
            config.set(path, new ArrayList<>().add(0.01));
        }
        List<Double> NL = new ArrayList<>();
        NL.add(null);
        return NL;
    }

    // 获取Bukkit的Location, 格式: world, x, y, z, yaw, pitch
    public static Location getLocation(FileConfiguration config, String path) {
        if (config.getString(path) != null) {
            String locationString = config.getString(path);
            World world = Bukkit.getWorld(locationString.split(", ")[0]);
            double x = Double.parseDouble(locationString.split(", ")[1]);
            double y = Double.parseDouble(locationString.split(", ")[2]);
            double z = Double.parseDouble(locationString.split(", ")[3]);
            double yaw = Double.parseDouble(locationString.split(", ")[4]);
            double pitch = Double.parseDouble(locationString.split(", ")[5]);
            return new Location(world, x, y, z, (float) yaw, (float) pitch);
        }
        return null;
    }
    public static Location getLocation(String loc_str) {
        World world = Bukkit.getWorld(loc_str.split(", ")[0]);
        double x = Double.parseDouble(loc_str.split(", ")[1]);
        double y = Double.parseDouble(loc_str.split(", ")[2]);
        double z = Double.parseDouble(loc_str.split(", ")[3]);
        double yaw = Double.parseDouble(loc_str.split(", ")[4]);
        double pitch = Double.parseDouble(loc_str.split(", ")[5]);
        return new Location(world, x, y, z, (float) yaw, (float) pitch);
    }

    // 获取Block的Location, 没有方向
    // 给xy轴添加0.5实现出现原版的位置偏差(不加0.5就是方块的边缘的角落, 加了0.5就是在方块的中心)
    public static Location getBlockLocation(FileConfiguration config, String path) {
        if (config.getString(path) != null) {
            String locationString = config.getString(path);
            World world = Bukkit.getWorld(locationString.split(", ")[0]);
            int x = (int) Double.parseDouble(locationString.split(", ")[1]);
            int y = (int) Double.parseDouble(locationString.split(", ")[2]);
            int z = (int) Double.parseDouble(locationString.split(", ")[3]);
            return new Location(world, x +0.5, y, z +0.5); /**/
        }
        return null;
    }
    public static Location getBlockLocation(String message_loc) {

        World world = Bukkit.getWorld(message_loc.split(", ")[0]);
        int x = (int) Double.parseDouble(message_loc.split(", ")[1]);
        int y = (int) Double.parseDouble(message_loc.split(", ")[2]);
        int z = (int) Double.parseDouble(message_loc.split(", ")[3]);
        return new Location(world, x +0.5, y, z +0.5); /**/

    }

    public static List<Location> getBlockStrLocationList(List<String> list) {

        if (list.isEmpty()) return null;

        List<Location> locs = new ArrayList<>();

        for (String s : list) {
            locs.add(getBlockLocation(s));
        }

        return locs;
    }


}
