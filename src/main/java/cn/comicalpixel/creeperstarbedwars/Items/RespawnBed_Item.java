package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent.GameTimerEvent_Main;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.GameSetup.SetupListener;
import cn.comicalpixel.creeperstarbedwars.Utils.BedBlockUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RespawnBed_Item implements Listener {

    public List<String> used_teams = new ArrayList<String>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2) return;

        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if (e.getItem() == null) return;

        if (e.getItem().getType() != Material.BED) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        e.setCancelled(true);

        Player p = e.getPlayer();

        if (used_teams.contains(TeamManager.player_teams.get(p))) {
            p.sendMessage(ConfigData.ItemInGame_respawn_bed_l_used);
            return;
        }

        if (!TeamManager.getbed(TeamManager.player_teams.get(p))) {
            p.sendMessage(ConfigData.ItemInGame_respawn_bed_l_bedexist);
            return;
        }

        if (GameTimerEvent_Main.gameNowTimer < GameTimerEvent_Main.maxTimer - ConfigData.ItemInGame_respawn_bed_started) {
            p.sendMessage(ConfigData.ItemInGame_respawn_bed_l_expires_chat);
            return;
        }

        if (p.getLocation().getWorld().getName().equalsIgnoreCase(TeamSpawn.getLocation(p, TeamManager.player_teams.get(p)).getWorld().getName()) &&
            p.getLocation().distance(TeamSpawn.getLocation(p, TeamManager.player_teams.get(p))) > 9)
        {
            p.sendMessage(ConfigData.ItemInGame_respawn_bed_l_spawnloc_toofar);
            return;
        }

        switch (TeamManager.player_teams.get(p)) {
            case "RED":
                BedBlockUtils.send(GameData_cfg.team_red_bed_f, GameData_cfg.team_red_bed_b);
                TeamManager.team_red_bed = false;
                break;
            case "BLUE":
                BedBlockUtils.send(GameData_cfg.team_blue_bed_f, GameData_cfg.team_blue_bed_b);
                TeamManager.team_blue_bed = false;
                break;
            case "YELLOW":
                BedBlockUtils.send(GameData_cfg.team_yellow_bed_f, GameData_cfg.team_yellow_bed_b);
                TeamManager.team_yellow_bed = false;
                break;
            case "GREEN":
                BedBlockUtils.send(GameData_cfg.team_green_bed_f, GameData_cfg.team_green_bed_b);
                TeamManager.team_green_bed = false;
                break;
            case "PINK":
                BedBlockUtils.send(GameData_cfg.team_pink_bed_f, GameData_cfg.team_pink_bed_b);
                TeamManager.team_pink_bed = false;
                break;
            case "AQUA":
                BedBlockUtils.send(GameData_cfg.team_aqua_bed_f, GameData_cfg.team_aqua_bed_b);
                TeamManager.team_aqua_bed = false;
                break;
            case "GRAY":
                BedBlockUtils.send(GameData_cfg.team_gray_bed_f, GameData_cfg.team_gray_bed_b);
                TeamManager.team_gray_bed = false;
                break;
            case "WHITE":
                BedBlockUtils.send(GameData_cfg.team_white_bed_f, GameData_cfg.team_white_bed_b);
                TeamManager.team_white_bed = false;
                break;
            default:
                Bukkit.getLogger().warning("[CreeperStarBedwars][RespawnBed] TeamManager.player_teams.get(p) case == null !!");
                break;
        }

        ItemStack itemInHand = p.getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().remove(itemInHand);
        }

        String chat_message = ConfigData.language_bed_respawn_chat;
        chat_message = chat_message
                .replace("{TeamChatColor}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                .replace("{TeamName}", TeamManager.getTeamName(TeamManager.player_teams.get(p)))
        ;

        String team_title = ConfigData.language_bed_respawn_team_title;
        String team_subtitle = ConfigData.language_bed_respawn_team_subtitle;
        team_title = team_title
                .replace("{TeamChatColor}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                .replace("{TeamName}", TeamManager.getTeamName(TeamManager.player_teams.get(p)))
        ;
        team_subtitle = team_subtitle
                .replace("{TeamChatColor}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                .replace("{TeamName}", TeamManager.getTeamName(TeamManager.player_teams.get(p)))
        ;

        String all_title = ConfigData.language_bed_respawn_all_title;
        String all_subtitle = ConfigData.language_bed_respawn_all_subtitle;
        all_title = all_title
                .replace("{TeamChatColor}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                .replace("{TeamName}", TeamManager.getTeamName(TeamManager.player_teams.get(p)))
        ;
        all_subtitle = all_subtitle
                .replace("{TeamChatColor}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)))
                .replace("{TeamName}", TeamManager.getTeamName(TeamManager.player_teams.get(p)))
        ;

        for (Player gameplayer : GamePlayers.players) {
            if (Objects.equals(TeamManager.player_teams.get(gameplayer), TeamManager.player_teams.get(p))) {
                NMSTitleUntils.Title.send(gameplayer, team_title, team_subtitle, 7, 40, 10);
                ConfigUtils.playSound(gameplayer, CreeperStarBedwars.getPlugin().getConfig(), "sound.respawnbed-team");
            } else {
                NMSTitleUntils.Title.send(gameplayer, all_title, all_subtitle, 7, 40, 10);
                ConfigUtils.playSound(gameplayer, CreeperStarBedwars.getPlugin().getConfig(), "sound.respawnbed-all");
            }
        }
        for (Player oplayer : Bukkit.getOnlinePlayers()) {
            oplayer.sendMessage(chat_message);
        }

    }

}
