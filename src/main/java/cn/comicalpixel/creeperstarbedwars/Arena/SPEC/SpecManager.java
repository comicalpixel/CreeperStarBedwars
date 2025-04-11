package cn.comicalpixel.creeperstarbedwars.Arena.SPEC;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class SpecManager {

    public static void setSpec(Player p) {
        GamePlayers.players.remove(p);
        GamePlayers.specs.add(p);
        PlayerUtils.clear_effects(p);
        PlayerUtils.reset(p);
        p.setGameMode(GameMode.SPECTATOR);
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(21);
        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
        p.teleport(GameData_cfg.spec_loc.clone());
    }

}
