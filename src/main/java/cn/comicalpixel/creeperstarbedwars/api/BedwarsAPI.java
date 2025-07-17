package cn.comicalpixel.creeperstarbedwars.api;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent.GameTimerEvent_Main;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class BedwarsAPI {

    /**
     * 获取当前游戏状态
     * @return 游戏状态代码 (0=等待中, 1=准备中, 2=游戏中, 3=结束)
     */
    public static int getGameState() {
        return GameStats.get();
    }

    /**
     * 获取玩家所在队伍
     * @param player 玩家对象
     * @return 队伍名称，如果不在游戏中返回null
     */
    public static String getPlayerTeam(Player player) {
        return TeamManager.player_teams.get(player);
    }

    /**
     * 检查床是否还存在
     * @param teamName 队伍名称
     * @return 床是否还存在
     */
    public static boolean getTeamBedAlive(String teamName) {
        return TeamManager.getbed(teamName);
    }
    public static boolean getBedAlive(Player p) {
        return TeamManager.getbed(TeamManager.player_teams.get(p));
    }


    /**
     * 获取当前游戏中的玩家数量
     * @return 游戏玩家数量
     */
    public static List<Player> getGamePlayers() {
        return GamePlayers.players;
    }


    /**
     * 获取当前游戏中的旁观者数量
     * @return 游戏旁观者数量
     */
    public static List<Player> getGameSpecs() {
        return GamePlayers.specs;
    }


    /**
     * 方块保护范围
     * @return 获取方块保护范围
     */
    public static List<Block> getGameSafeRegions() {
        return PlayerBlocks.safeMap_blocks;
    }

    /**
     * 玩家放置的方块
     * @return 获取方块保护范围
     */
    public static List<Block> getPlayerBlocks() {
        return PlayerBlocks.player_blocks;
    }

    /**
     * 游戏时间
     * @return 返回现在的游戏时间的类
     */
    public static GameTimerEvent_Main getGameTimer() {
        return GameTimerEvent_Main.instance;
    }



}
