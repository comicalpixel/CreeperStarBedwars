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

    public static Location lobby_loc = null;
    public static Location spec_loc = null;

    public static Location mapCenter_loc = null;
    public static Location mapCenter_radius = null;

    // 队伍箱子检测半径(中心点为队伍的出生点)
    public static int teamChest_radius = 15;

    // 游戏资源点
    public static List<Location> gameGenerator_diamond_locs = new ArrayList<>();
    public static List<Location> gameGenerator_emerald_locs = new ArrayList<>();

    // 所有队伍(里面填队伍颜色, 如：RED/BLUE/GREEN/YELLOW/PINK/AQUA/GRAY/WHITE)
    public static List<String> teams_list = new ArrayList<String>();

    // 队伍玩家数量
    public static int team_players = 1;

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

    

}
