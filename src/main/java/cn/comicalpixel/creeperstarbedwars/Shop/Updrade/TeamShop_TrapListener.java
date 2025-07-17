package cn.comicalpixel.creeperstarbedwars.Shop.Updrade;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class TeamShop_TrapListener implements Listener {

    public FileConfiguration updradeConfig = CreeperStarBedwars.getPlugin().getUpdradeConfig();
    private Map<Player, Long> Teams_cooldownMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (GameStats.get() != 2) return;

        Player p = e.getPlayer();

        if (!GamePlayers.players.contains(p)) return;

        if (e.isCancelled()) return;


        String team = "NONE";
        if (Teams_cooldownMap.containsKey(p) && System.currentTimeMillis() - Teams_cooldownMap.get(p) < (updradeConfig.getInt("traps.cooldown") * 1000L)) {
            return;
        }

        if (TeamManager.teams.contains("RED") && p.getLocation().distance(GameData_cfg.team_red_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "RED";
        } else if (TeamManager.teams.contains("BLUE") && p.getLocation().distance(GameData_cfg.team_blue_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "BLUE";
        } else if (TeamManager.teams.contains("GREEN") && p.getLocation().distance(GameData_cfg.team_green_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "GREEN";
        } else if (TeamManager.teams.contains("YELLOW") && p.getLocation().distance(GameData_cfg.team_yellow_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "YELLOW";
        } else if (TeamManager.teams.contains("PINK") && p.getLocation().distance(GameData_cfg.team_pink_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "PINK";
        } else if (TeamManager.teams.contains("AQUA") && p.getLocation().distance(GameData_cfg.team_aqua_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "AQUA";
        } else if (TeamManager.teams.contains("GRAY") && p.getLocation().distance(GameData_cfg.team_gray_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "GRAY";
        } else if (TeamManager.teams.contains("WHITE") && p.getLocation().distance(GameData_cfg.team_white_spawn) <= updradeConfig.getInt("traps.radius")) {
            team = "WHITE";
        }




        if (team.equalsIgnoreCase("NONE")) {
            return;
        }
        if (TeamManager.player_teams.get(p).equalsIgnoreCase(team)) {
            return;
        }
        if (TeamShop_GUI.team_Traps.get(team).isEmpty()) {
            return;
        }
        switch (TeamShop_GUI.team_Traps.get(team).get(0)) {
            case 1:
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 320, 2), true);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 320, 1), true);
                for (Player gameplayer : GamePlayers.players) if (TeamManager.player_teams.get(gameplayer).equalsIgnoreCase(team))
                    NMSTitleUntils.Title.send(gameplayer, ConfigUtils.getString(updradeConfig, "traps.language.title"), ConfigUtils.getString(updradeConfig, "traps.language.subtitle").replace("{type}", ConfigUtils.getString(updradeConfig, "traps.language.type-ThisIsATrap")), 5, 40, 10);
                Teams_cooldownMap.put(p, System.currentTimeMillis());
                TeamShop_GUI.team_Traps.get(team).remove(0);
                break;
            case 2:
                for (Player gameplayer : GamePlayers.players) { if (TeamManager.player_teams.get(gameplayer).equalsIgnoreCase(team)){
                    gameplayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1), true);
                    gameplayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 1), true);
                    NMSTitleUntils.Title.send(gameplayer, ConfigUtils.getString(updradeConfig, "traps.language.title"), ConfigUtils.getString(updradeConfig, "traps.language.subtitle").replace("{type}", ConfigUtils.getString(updradeConfig, "traps.language.type-UnAttack")), 5, 40, 10);}}
                Teams_cooldownMap.put(p, System.currentTimeMillis());
                TeamShop_GUI.team_Traps.get(team).remove(0);
                break;
            case 3:
                if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) p.removePotionEffect(PotionEffectType.INVISIBILITY);
                for (Player gameplayer : GamePlayers.players) if (TeamManager.player_teams.get(gameplayer).equalsIgnoreCase(team))
                    NMSTitleUntils.Title.send(gameplayer, ConfigUtils.getString(updradeConfig, "traps.language.title"), ConfigUtils.getString(updradeConfig, "traps.language.subtitle").replace("{type}", ConfigUtils.getString(updradeConfig, "traps.language.type-Warning")), 5, 40, 10);
                Teams_cooldownMap.put(p, System.currentTimeMillis());
                TeamShop_GUI.team_Traps.get(team).remove(0);
                break;
            case 4:
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 2), true);
                for (Player gameplayer : GamePlayers.players) if (TeamManager.player_teams.get(gameplayer).equalsIgnoreCase(team))
                    NMSTitleUntils.Title.send(gameplayer, ConfigUtils.getString(updradeConfig, "traps.language.title"), ConfigUtils.getString(updradeConfig, "traps.language.subtitle").replace("{type}", ConfigUtils.getString(updradeConfig, "traps.language.type-AntiBreak")), 5, 40, 10);
                Teams_cooldownMap.put(p, System.currentTimeMillis());
                TeamShop_GUI.team_Traps.get(team).remove(0);
                break;
        }

    }

}
