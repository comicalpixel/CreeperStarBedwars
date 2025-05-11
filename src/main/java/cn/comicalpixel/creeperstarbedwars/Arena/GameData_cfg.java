package cn.comicalpixel.creeperstarbedwars.Arena;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class GameData_cfg {

    /* 游戏基本设置 */

    public static String map_name = "MapName";
    public static String map_author = "Public";

    public static int minPlayers = 2;
    public static int maxPlayers = 2;

    public static Location lobby_loc;
    public static Location spec_loc;

    public static Location mapCenter_loc;
    public static int mapCenter_radius = 300;

    // 队伍箱子检测半径(中心点为队伍的出生点)
    public static int teamChest_radius = 15;

    // 游戏资源点
    public static List<String> gameGenerator_diamond_locs = new ArrayList<>();
    public static List<String> gameGenerator_emerald_locs = new ArrayList<>();

    // 所有队伍(里面填队伍颜色, 如：RED/BLUE/GREEN/YELLOW/PINK/AQUA/GRAY/WHITE)
    public static List<String> teams_list = new ArrayList<String>();

    // 队伍玩家数量
    public static int team_players = 1;

    // 商店NPC 获取Location用ConfigUtil
    public static List<String> shopNPC_item_locs = new ArrayList<>();
    public static List<String> shopNPC_team_locs = new ArrayList<>();

    /*
        所有的队伍的设置
        如果不在teams_list那可以忽略
        记得没启用的队伍不要读取!! 不然容易报错
    */
    public static String team_red_name = "RED";
    public static String team_red_chatcolor = "§c";
    public static Location team_red_spawn = null;
    public static Location team_red_generator = null;
    public static Location team_red_bed_f = null;
    public static Location team_red_bed_b = null;

    public static String team_blue_name = "BLUE";
    public static String team_blue_chatcolor = "§9";
    public static Location team_blue_spawn = null;
    public static Location team_blue_generator = null;
    public static Location team_blue_bed_f = null;
    public static Location team_blue_bed_b = null;

    public static String team_green_name = "GREEN";
    public static String team_green_chatcolor = "§a";
    public static Location team_green_spawn = null;
    public static Location team_green_generator = null;
    public static Location team_green_bed_f = null;
    public static Location team_green_bed_b = null;

    public static String team_yellow_name = "GREEN";
    public static String team_yellow_chatcolor = "§a";
    public static Location team_yellow_spawn = null;
    public static Location team_yellow_generator = null;
    public static Location team_yellow_bed_f = null;
    public static Location team_yellow_bed_b = null;

    public static String team_gray_name = "GRAY";
    public static String team_gray_chatcolor = "§8";
    public static Location team_gray_spawn = null;
    public static Location team_gray_generator = null;
    public static Location team_gray_bed_f = null;
    public static Location team_gray_bed_b = null;

    public static String team_pink_name = "PINK";
    public static String team_pink_chatcolor = "§d";
    public static Location team_pink_spawn = null;
    public static Location team_pink_generator = null;
    public static Location team_pink_bed_f = null;
    public static Location team_pink_bed_b = null;

    public static String team_aqua_name = "AQUA";
    public static String team_aqua_chatcolor = "§3";
    public static Location team_aqua_spawn = null;
    public static Location team_aqua_generator = null;
    public static Location team_aqua_bed_f = null;
    public static Location team_aqua_bed_b = null;

    public static String team_white_name = "WHITE";
    public static String team_white_chatcolor = "§f";
    public static Location team_white_spawn = null;
    public static Location team_white_generator = null;
    public static Location team_white_bed_f = null;
    public static Location team_white_bed_b = null;

}
