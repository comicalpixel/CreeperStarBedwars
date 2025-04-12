package cn.comicalpixel.creeperstarbedwars.Arena.SPEC;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpecManager {

    public static void setSpec(Player p) {
        GamePlayers.players.remove(p);
        GamePlayers.specs.add(p);
        TeamManager.player_teams.remove(p);
        PlayerUtils.clear_effects(p);
        PlayerUtils.reset(p);
        p.setGameMode(GameMode.ADVENTURE);
        PlayerUtils.setCollidesWithEntities(p, false);
        PlayerUtils.hidePlayer(p);
        p.setAllowFlight(true);
        p.setFlying(true);
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(21);
        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
        p.setFlying(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        p.teleport(GameData_cfg.spec_loc.clone());
    }

}
