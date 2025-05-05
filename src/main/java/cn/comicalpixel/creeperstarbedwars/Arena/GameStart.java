package cn.comicalpixel.creeperstarbedwars.Arena;

import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.BedInvincibilityEvent;
import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent.GameTimerEvent_Main;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.StartGenerator;
import cn.comicalpixel.creeperstarbedwars.Arena.SPEC.SpecManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameStart {

    public static void start() {
        GameStats.set(2);

        // 队伍分配与异常无队伍的玩家的处理
        for (Player p : GamePlayers.players) {
            TeamManager.random_join(p);
        }
        for (Player p : GamePlayers.players) {
            if (TeamManager.player_teams.get(p) == null) {
                SpecManager.setSpec(p);
            } else {
                TeamSpawn.send(p, TeamManager.player_teams.get(p));
            }
        }

        // 常规的玩家的操作
        for (Player p : GamePlayers.players) {

            PlayerUtils.reset(p);
            PlayerUtils.clear_effects(p);
            GameTools.InitializationInventory(p);
            p.setGameMode(GameMode.SURVIVAL);

            p.getEnderChest().clear();

            p.setLevel(0);
            p.setExp(0);

            p.setFlying(false);
            p.setAllowFlight(false);

        }

        // 床无敌
        BedInvincibilityEvent.start();

        // 启动资源点
        new StartGenerator();

        // 启动游戏事件
        new GameTimerEvent_Main();



    }

}
