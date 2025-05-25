package cn.comicalpixel.creeperstarbedwars.Shop.Item.PlayerArmor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerArmorManager {

    public static HashMap<Player, Integer> playersArmorLevel_HashMap = new HashMap<>();

    public static void reset(Player p) {
        playersArmorLevel_HashMap.put(p, 0);
    }

    public static void set(Player p, int level) {

        playersArmorLevel_HashMap.put(p, level);

    }

    public static int get(Player p) {
        return playersArmorLevel_HashMap.get(p);
    }

}
