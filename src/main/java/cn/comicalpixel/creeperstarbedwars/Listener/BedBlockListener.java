package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.BedInvincibilityEvent;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.PlayerInGameData;
import cn.comicalpixel.creeperstarbedwars.Utils.BedBlockUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

public class BedBlockListener implements Listener {

    @EventHandler
    public void BedBlockBreak(BlockBreakEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) return;
        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if (e.isCancelled()) return;
        if (e.getBlock().getType() != Material.BED_BLOCK) return;

        Location loc = e.getBlock().getLocation();
        Block block = loc.getBlock();

        Player p = e.getPlayer();

        String bed_TEAM = null;
        String bed_team_chatColor = "";
        String bed_team_name = "";
        String player_name = "";
        String player_color = TeamManager.getTeamChatColor(TeamManager.player_teams.get(p));

        player_name = p.getName();

        // 阻止挖掘(清除方块另外写)
        e.setCancelled(true);


        /*
            床无敌
         */
        if (ConfigData.bed_invincibility_enabled && !BedInvincibilityEvent.canBreak) {
            p.sendMessage(ConfigData.language_bed_invincibility_breakC);
            return;
        }

        /*
            拆床判定
         */
        if (GameData_cfg.team_red_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("RED")) {
            bed_TEAM = "RED";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_red_bed || TeamManager.getTeamPlayerSize("RED") <= 0 || !TeamManager.teams.contains("RED")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_red_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("RED");
            bed_team_name = GameData_cfg.team_red_name;
        }

        else if (GameData_cfg.team_blue_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("BLUE")) {
            bed_TEAM = "BLUE";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_blue_bed || TeamManager.getTeamPlayerSize("BLUE") <= 0 || !TeamManager.teams.contains("BLUE")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_blue_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("BLUE");
            bed_team_name = GameData_cfg.team_blue_name;
        }

        else if (GameData_cfg.team_green_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("GREEN")) {
            bed_TEAM = "GREEN";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_green_bed || TeamManager.getTeamPlayerSize("GREEN") <= 0 || !TeamManager.teams.contains("GREEN")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_green_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("GREEN");
            bed_team_name = GameData_cfg.team_green_name;
        }

        else if (GameData_cfg.team_yellow_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("YELLOW")) {
            bed_TEAM = "YELLOW";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_yellow_bed || TeamManager.getTeamPlayerSize("YELLOW") <= 0 || !TeamManager.teams.contains("YELLOW")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_yellow_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("YELLOW");
            bed_team_name = GameData_cfg.team_yellow_name;
        }

        else if (GameData_cfg.team_pink_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("PINK")) {
            bed_TEAM = "PINK";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_pink_bed || TeamManager.getTeamPlayerSize("PINK") <= 0 || !TeamManager.teams.contains("PINK")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_pink_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("PINK");
            bed_team_name = GameData_cfg.team_pink_name;
        }

        else if (GameData_cfg.team_aqua_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("AQUA")) {
            bed_TEAM = "AQUA";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_aqua_bed || TeamManager.getTeamPlayerSize("AQUA") <= 0 || !TeamManager.teams.contains("AQUA")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_aqua_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("AQUA");
            bed_team_name = GameData_cfg.team_aqua_name;
        }

        else if (GameData_cfg.team_gray_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("GRAY")) {
            bed_TEAM = "GRAY";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_gray_bed || TeamManager.getTeamPlayerSize("GRAY") <= 0 || !TeamManager.teams.contains("GRAY")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_gray_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("GRAY");
            bed_team_name = GameData_cfg.team_gray_name;
        }

        else if (GameData_cfg.team_white_bed_f.distance(loc) < 2.5 && GameData_cfg.teams_list.contains("WHITE")) {
            bed_TEAM = "WHITE";
            if (TeamManager.player_teams.get(p).equals(bed_TEAM)) {
                p.sendMessage(ConfigData.language_bed_destroy_ismyteam_chat);
                return;
            }
            if (TeamManager.team_white_bed || TeamManager.getTeamPlayerSize("WHITE") <= 0 || !TeamManager.teams.contains("WHITE")) {
                p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "language.bed-destroy-unknow-team"));
                return;
            }
            TeamManager.team_white_bed = true;
            bed_team_chatColor = TeamManager.getTeamChatColor("WHITE");
            bed_team_name = GameData_cfg.team_white_name;
        } else {
            if (!PlayerBlocks.player_blocks.contains(block)) {
                p.sendMessage(ConfigData.language_block_saferegion);
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
            return;
        }


        // Player beds data
        PlayerInGameData.Companion.getBeds().put(p, PlayerInGameData.Companion.getBeds().getOrDefault(p, 0) + 1);


        // 变量
        // {bed_color}床的队伍ChatColor
        // {bed_team}床的队伍的名称
        // {player_color}拆床的玩家的队伍ChatColor
        // {player_name}拆床的玩家的名字

        for (Player gameplayer : GamePlayers.players) {
            if (TeamManager.player_teams.get(gameplayer) != null && !Objects.equals(TeamManager.player_teams.get(gameplayer), bed_TEAM) && p != gameplayer) {
                // 游戏其他玩家显示的
                ConfigUtils.playSound(gameplayer, CreeperStarBedwars.getPlugin().getConfig(), "sound.bed-destroy-all");
                String title = ConfigData.language_bed_destroy_all_title
                        .replace("{bed_color}", bed_team_chatColor)
                        .replace("{bed_name}", bed_team_name)
                        .replace("{player_color}", player_color)
                        .replace("{player_name}", player_name);
                String subtitle = ConfigData.language_bed_destroy_all_subtitle
                        .replace("{bed_color}", bed_team_chatColor)
                        .replace("{bed_name}", bed_team_name)
                        .replace("{player_color}", player_color)
                        .replace("{player_name}", player_name);
                NMSTitleUntils.Title.send(gameplayer, title, subtitle, 7, 40, 10);
            } else if (TeamManager.player_teams.get(gameplayer) != null && !Objects.equals(TeamManager.player_teams.get(gameplayer), bed_TEAM) && p == gameplayer) {
                // 拆床的玩家显示的
                ConfigUtils.playSound(gameplayer, CreeperStarBedwars.getPlugin().getConfig(), "sound.bed-destroy-me");
                String title = ConfigData.language_bed_destroy_me_title
                        .replace("{bed_color}", bed_team_chatColor)
                        .replace("{bed_name}", bed_team_name)
                        .replace("{player_color}", player_color)
                        .replace("{player_name}", player_name);;
                String subtitle = ConfigData.language_bed_destroy_me_subtitle
                        .replace("{bed_color}", bed_team_chatColor)
                        .replace("{bed_name}", bed_team_name)
                        .replace("{player_color}", player_color)
                        .replace("{player_name}", player_name);
                NMSTitleUntils.Title.send(gameplayer, title, subtitle, 7, 40, 10);
            } else if (TeamManager.player_teams.get(gameplayer) != null && Objects.equals(TeamManager.player_teams.get(gameplayer), bed_TEAM) && p != gameplayer) {
                // 被拆床的队伍的玩家显示的
                ConfigUtils.playSound(gameplayer, CreeperStarBedwars.getPlugin().getConfig(), "sound.bed-destroy-team");
                String title = ConfigData.language_bed_destroy_team_title
                        .replace("{bed_color}", bed_team_chatColor)
                        .replace("{bed_name}", bed_team_name)
                        .replace("{player_color}", player_color)
                        .replace("{player_name}", player_name);
                String subtitle = ConfigData.language_bed_destroy_team_subtitle
                        .replace("{bed_color}", bed_team_chatColor)
                        .replace("{bed_name}", bed_team_name)
                        .replace("{player_color}", player_color)
                        .replace("{player_name}", player_name);
                NMSTitleUntils.Title.send(gameplayer, title, subtitle, 7, 40, 10);
            }
        }

        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            String message = ConfigData.language_bed_destroy_chat;
            message = message
                    .replace("{bed_color}", bed_team_chatColor)
                    .replace("{bed_name}", bed_team_name)
                    .replace("{player_color}", player_color)
                    .replace("{player_name}", player_name);
            onlinePlayers.sendMessage(message);
        }


        switch (bed_TEAM) {
            case "RED":
                BedBlockUtils.clear(GameData_cfg.team_red_bed_f, GameData_cfg.team_red_bed_b);
                break;
            case "BLUE":
                BedBlockUtils.clear(GameData_cfg.team_blue_bed_f, GameData_cfg.team_blue_bed_b);
                break;
            case "GREEN":
                BedBlockUtils.clear(GameData_cfg.team_green_bed_f, GameData_cfg.team_green_bed_b);
                break;
            case "YELLOW":
                BedBlockUtils.clear(GameData_cfg.team_yellow_bed_f, GameData_cfg.team_yellow_bed_b);
                break;
            case "PINK":
                BedBlockUtils.clear(GameData_cfg.team_pink_bed_f, GameData_cfg.team_pink_bed_b);
                break;
            case "GRAY":
                BedBlockUtils.clear(GameData_cfg.team_gray_bed_f, GameData_cfg.team_gray_bed_b);
                break;
            case "WHITE":
                BedBlockUtils.clear(GameData_cfg.team_white_bed_f, GameData_cfg.team_white_bed_b);
                break;
        }

    }

}
