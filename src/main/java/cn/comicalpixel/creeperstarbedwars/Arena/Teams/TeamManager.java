package cn.comicalpixel.creeperstarbedwars.Arena.Teams;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {

    public static List<String> teams = GameData_cfg.teams_list;

    // public static List<Player> team_red_players = new ArrayList<Player>();
    public static boolean team_red_bed = false;
    /**/
    // public static List<Player> team_blue_players = new ArrayList<Player>();
    public static boolean team_blue_bed = false;
    /**/
    // public static List<Player> team_green_players = new ArrayList<Player>();
    public static boolean team_green_bed = false;
    /**/
    // public static List<Player> team_yellow_players = new ArrayList<Player>();
    public static boolean team_yellow_bed = false;
    /**/
    // public static List<Player> team_pink_players = new ArrayList<Player>();
    public static boolean team_pink_bed = false;
    /**/
    // public static List<Player> team_aqua_players = new ArrayList<Player>();
    public static boolean team_aqua_bed = false;
    /**/
    // public static List<Player> team_gray_players = new ArrayList<Player>();
    public static boolean team_gray_bed = false;
    /**/
    // public static List<Player> team_white_players = new ArrayList<Player>();
    public static boolean team_white_bed = false;

    public static HashMap<Player, String> player_teams = new HashMap<>();

    public static void random_join(Player p) {
        if (player_teams.containsKey(p)) {
            return;
        }

        // 获取所有队伍当前的玩家数量
        HashMap<String, Integer> teamCounts = new HashMap<>();
        for (String team : teams) {
            int count = 0;
            for (String playerTeam : player_teams.values()) {
                if (playerTeam.equals(team)) {
                    count++;
                }
            }
            teamCounts.put(team, count);
        }

        // 找到玩家数量最少的队伍
        String minTeam = null;
        int minCount = Integer.MAX_VALUE;
        for (String team : teams) {
            int count = teamCounts.get(team);
            if (count < minCount && count < GameData_cfg.team_players) { // 每个队伍最多x人
                minTeam = team;
                minCount = count;
            }
        }

        // 如果有可加入的队伍，则加入
        if (minTeam != null) {
            player_teams.put(p, minTeam);
        } else {
            // 所有队伍都已满
            // p.sendMessage("所有队伍都已满，无法加入！");
        }
    }

    public static void join(Player p, String team) {
        // 检查队伍是否已启用
        if (!teams.contains(team)) {
            p.sendMessage("§cInvalid team! ");
            ConfigUtils.playSound(p, CreeperStarBedwars.getInstance().getConfig(), "sound.teamsel-full");
            return;
        }

        // 计算当前队伍的玩家数量
        int count = 0;
        for (String playerTeam : player_teams.values()) {
            if (playerTeam.equals(team)) {
                count++;
            }
        }

        if (count >= GameData_cfg.team_players) {
            // p.sendMessage("该队伍已满！");
            p.sendMessage(MessageVariableUtils.teamNameColor_p_s(ConfigData.language_teamsel_join_full, team));
            ConfigUtils.playSound(p, CreeperStarBedwars.getInstance().getConfig(), "sound.teamsel-full");
            return;
        }

        player_teams.put(p, team);
        // p.sendMessage("你已成功加入 " + team + " 队伍！");
        ConfigUtils.playSound(p, CreeperStarBedwars.getInstance().getConfig(), "sound.teamsel-set");
        p.sendMessage(MessageVariableUtils.teamNameColor_p_s(ConfigData.language_teamsel_join_done, team));
    }

    public static void clear(Player p) {
        player_teams.remove(p);
        p.sendMessage(ConfigData.language_teamsel_leave);
        ConfigUtils.playSound(p, CreeperStarBedwars.getInstance().getConfig(), "sound.teamsel-clear");
    }

    public static void addTeamPlayer(String team, Player p) {
        if (    team.equals("RED") ||
                team.equals("BLUE") ||
                team.equals("GREEN") ||
                team.equals("YELLOW") ||
                team.equals("PINK") ||
                team.equals("AQUA") ||
                team.equals("GRAY") ||
                team.equals("WHITE")
        ) {

            if (teams.contains(team)) {
                switch (team) {
                    case "RED":
                        player_teams.put(p, "RED");
                        break;
                    case "BLUE":
                        player_teams.put(p, "BLUE");
                        break;
                    case "GREEN":
                        player_teams.put(p, "GREEN");
                        break;
                    case "YELLOW":
                        player_teams.put(p, "YELLOW");
                        break;
                    case "PINK":
                        player_teams.put(p, "PINK");
                        break;
                    case "AQUA":
                        player_teams.put(p, "AQUA");
                        break;
                    case "GRAY":
                        player_teams.put(p, "GRAY");
                        break;
                    case "WHITE":
                        player_teams.put(p, "WHITE");
                        break;
                }
            }

        }
    }
    public static void removeTeamPlayer(String team, Player p) {
        if (    team.equals("RED") ||
                team.equals("BLUE") ||
                team.equals("GREEN") ||
                team.equals("YELLOW") ||
                team.equals("PINK") ||
                team.equals("AQUA") ||
                team.equals("GRAY") ||
                team.equals("WHITE")
        ) {

            if (teams.contains(team)) {
                switch (team) {
                    case "RED":
                        player_teams.remove(p, "RED");
                        break;
                    case "BLUE":
                        player_teams.remove(p, "BLUE");
                        break;
                    case "GREEN":
                        player_teams.remove(p, "GREEN");
                        break;
                    case "YELLOW":
                        player_teams.remove(p, "YELLOW");
                        break;
                    case "PINK":
                        player_teams.remove(p, "PINK");
                        break;
                    case "AQUA":
                        player_teams.remove(p, "AQUA");
                        break;
                    case "GRAY":
                        player_teams.remove(p, "GRAY");
                        break;
                    case "WHITE":
                        player_teams.remove(p, "WHITE");
                        break;
                }
            }

        }
    }

    public static void setBed(String team, boolean isBreak) {
        if (    team.equals("RED") ||
                team.equals("BLUE") ||
                team.equals("GREEN") ||
                team.equals("YELLOW") ||
                team.equals("PINK") ||
                team.equals("AQUA") ||
                team.equals("GRAY") ||
                team.equals("WHITE")
        ) {

            if (teams.contains(team)) {
                switch (team) {
                    case "RED":
                        team_red_bed = isBreak;
                        break;
                    case "BLUE":
                        team_blue_bed = isBreak;
                        break;
                    case "GREEN":
                        team_green_bed = isBreak;
                        break;
                    case "YELLOW":
                        team_yellow_bed = isBreak;
                        break;
                    case "PINK":
                        team_pink_bed = isBreak;
                        break;
                    case "AQUA":
                        team_aqua_bed = isBreak;
                        break;
                    case "GRAY":
                        team_gray_bed = isBreak;
                        break;
                    case "WHITE":
                        team_white_bed = isBreak;
                        break;
                }
            }

        }
    }

    public static String getTeamChatColor(String team) {
        if (    team.equals("RED") ||
                team.equals("BLUE") ||
                team.equals("GREEN") ||
                team.equals("YELLOW") ||
                team.equals("PINK") ||
                team.equals("AQUA") ||
                team.equals("GRAY") ||
                team.equals("WHITE")
        ) {

            if (teams.contains(team)) {
                switch (team) {
                    case "RED":
                        return GameData_cfg.team_red_chatcolor;
                    case "BLUE":
                        return GameData_cfg.team_blue_chatcolor;
                    case "GREEN":
                        return GameData_cfg.team_green_chatcolor;
                    case "YELLOW":
                        return GameData_cfg.team_yellow_chatcolor;
                    case "PINK":
                        return GameData_cfg.team_pink_chatcolor;
                    case "AQUA":
                        return GameData_cfg.team_aqua_chatcolor;
                    case "GRAY":
                        return GameData_cfg.team_gray_chatcolor;
                    case "WHITE":
                        return GameData_cfg.team_white_chatcolor;
                }
            }

        }
        return "§7";
    }

    public static boolean getbed(String team) {

        if (    team.equals("RED") ||
                team.equals("BLUE") ||
                team.equals("GREEN") ||
                team.equals("YELLOW") ||
                team.equals("PINK") ||
                team.equals("AQUA") ||
                team.equals("GRAY") ||
                team.equals("WHITE")
        ) {

            if (teams.contains(team)) {
                switch (team) {
                    case "RED":
                        return team_red_bed;
                    case "BLUE":
                        return team_blue_bed;
                    case "GREEN":
                        return team_green_bed;
                    case "YELLOW":
                        return team_yellow_bed;
                    case "PINK":
                        return team_pink_bed;
                    case "AQUA":
                        return team_aqua_bed;
                    case "GRAY":
                        return team_gray_bed;
                    case "WHITE":
                        return team_white_bed;
                }
            }

        }

        return false;
    }


    public static int getTeamPlayerSize(String team) {
        int i = 0;

        if (player_teams.isEmpty()) {
            return 0;
        }

        for (Player p : GamePlayers.players) {
            if (player_teams.get(p).equals(team)) {
                i++;
            }
        }

        return i;
    }



}
