package cn.comicalpixel.creeperstarbedwars.Utils;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Countdown_Task;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageVariableUtils {

    public static String gameCountdown_p_s(String s) {
        return s.replace("{countdown}", Game_Countdown_Task.countdown + "");
    }

    public static String gameMapInformation_p_s(String s) {
        return s.replace("{map}", GameData_cfg.map_name)
                .replace("{author}", GameData_cfg.map_author)
                .replace("{players}", GamePlayers.players.size()+"")
                .replace("{maxplayers}",GameData_cfg.maxPlayers+"")
                ;
    }

    public static String teamNameColor_p_s(String s, String team) {
        String color = null;
        String name = null;
        switch (team) {
            case "RED":
                color = GameData_cfg.team_red_chatcolor;
                name = GameData_cfg.team_red_name;
                break;
            case "BLUE":
                color = GameData_cfg.team_blue_chatcolor;
                name = GameData_cfg.team_blue_name;
                break;
            case "GREEN":
                color = GameData_cfg.team_green_chatcolor;
                name = GameData_cfg.team_green_name;
                break;
            case "YELLOW":
                color = GameData_cfg.team_yellow_chatcolor;
                name = GameData_cfg.team_yellow_name;
                break;
            case "PINK":
                color = GameData_cfg.team_pink_chatcolor;
                name = GameData_cfg.team_pink_name;
                break;
            case "AQUA":
                color = GameData_cfg.team_aqua_chatcolor;
                name = GameData_cfg.team_aqua_name;
                break;
            case "GRAY":
                color = GameData_cfg.team_gray_chatcolor;
                name = GameData_cfg.team_gray_name;
                break;
            case "WHITE":
                color = GameData_cfg.team_white_chatcolor;
                name = GameData_cfg.team_white_name;
                break;
        }
        if (color == null) color = "null";
        if (name == null) name = "null";
        return s.replace("{team_color}", color).replace("{team_name}", name);
    }

    public static int convertMillisecondsToSeconds(long milliseconds) {
        return (int) (milliseconds / 1000 + 1);//+1实现防出现0秒
    }

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConfigData.language_date);
        String date = dateFormat.format(new Date());
        return date;
    }


    public static String getTeamMessageToSidebar(String s, Player p) {
        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return s;
        }

        if (TeamManager.teams.contains("RED")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("RED")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("RED")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_red_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_red_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            }else if (!TeamManager.team_red_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("RED"))
                    .replace("{team_name}", GameData_cfg.team_red_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_red}", message);
        } else {
            s = s.replace("{team_red}", "");
        }
        if (TeamManager.teams.contains("BLUE")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("BLUE")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("BLUE")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_blue_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_blue_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            } else if (!TeamManager.team_blue_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("BLUE"))
                    .replace("{team_name}", GameData_cfg.team_blue_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_blue}", message);
        } else {
            s = s.replace("{team_blue}", "");
        }

        if (TeamManager.teams.contains("GREEN")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("GREEN")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("GREEN")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_green_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_green_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            } else if (!TeamManager.team_green_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("GREEN"))
                    .replace("{team_name}", GameData_cfg.team_green_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_green}", message);
        } else {
            s = s.replace("{team_green}", "");
        }

        if (TeamManager.teams.contains("YELLOW")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("YELLOW")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("YELLOW")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_yellow_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_yellow_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            } else if (!TeamManager.team_yellow_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("YELLOW"))
                    .replace("{team_name}", GameData_cfg.team_yellow_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_yellow}", message);
        } else {
            s = s.replace("{team_yellow}", "");
        }

        if (TeamManager.teams.contains("PINK")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("PINK")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("PINK")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_pink_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_pink_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            } else if (!TeamManager.team_pink_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("PINK"))
                    .replace("{team_name}", GameData_cfg.team_pink_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_pink}", message);
        } else {
            s = s.replace("{team_pink}", "");
        }

        if (TeamManager.teams.contains("AQUA")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("AQUA")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("AQUA")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_aqua_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_aqua_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            } else if (!TeamManager.team_aqua_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("AQUA"))
                    .replace("{team_name}", GameData_cfg.team_aqua_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_aqua}", message);
        } else {
            s = s.replace("{team_aqua}", "");
        }

        if (TeamManager.teams.contains("GRAY")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("GRAY")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("GRAY")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_gray_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_gray_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            } else if (!TeamManager.team_gray_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("GRAY"))
                    .replace("{team_name}", GameData_cfg.team_gray_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_gray}", message);
        } else {
            s = s.replace("{team_gray}", "");
        }

        if (TeamManager.teams.contains("WHITE")) {
            String message = ConfigData.language_sidebarboard_team_message;
            int size = 0;
            for (Player gp : GamePlayers.players) {
                if (TeamManager.player_teams.containsKey(gp) && TeamManager.player_teams.get(gp).equals("WHITE")) {
                    size++;
                }
            }
            String imt = "";
            if (TeamManager.player_teams.containsKey(p) && TeamManager.player_teams.get(p).equals("WHITE")) {
                imt = ConfigData.language_sidebarboard_team_ismyteam;
            }
            String bed = "?";
            if (TeamManager.team_white_bed) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            } else if (!TeamManager.team_white_bed && size > 0) {
                bed = ConfigData.language_sidebarboard_team_hasbed;
            } else if (!TeamManager.team_white_bed && size <= 0) {
                bed = ConfigData.language_sidebarboard_team_notbed;
            }
            message = message
                    .replace("{team_color}", TeamManager.getTeamChatColor("WHITE"))
                    .replace("{team_name}", GameData_cfg.team_white_name)
                    .replace("{size}", size+"")
                    .replace("{ismyteam}", imt)
                    .replace("{bed}", bed)
            ;
            s = s.replace("{team_white}", message);
        } else {
            s = s.replace("{team_white}", "");
        }

        return s;
    }


    public static String toPAPI(String s, Player p) {
        return PlaceholderAPI.setPlaceholders(p, s);
    }

}
