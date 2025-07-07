package cn.comicalpixel.creeperstarbedwars.Arena;

import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.BedInvincibilityEvent;
import cn.comicalpixel.creeperstarbedwars.Arena.GameEvents.TimerEvent.GameTimerEvent_Main;
import cn.comicalpixel.creeperstarbedwars.Arena.Generator.StartGenerator;
import cn.comicalpixel.creeperstarbedwars.Arena.SPEC.SpecManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Entity.ShopNPC.ShopNPC_Manager;
import cn.comicalpixel.creeperstarbedwars.Items.Compass_Item;
import cn.comicalpixel.creeperstarbedwars.Items.ToolsItem.ToolItemsManager;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Shop.Item.PlayerArmor.PlayerArmorManager;
import cn.comicalpixel.creeperstarbedwars.Shop.Updrade.TeamShop_GUI;
import cn.comicalpixel.creeperstarbedwars.Shop.Updrade.TeamShop_Manager;
import cn.comicalpixel.creeperstarbedwars.Task.GameTeamEliminated_Task;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Actionbar_Task;
import cn.comicalpixel.creeperstarbedwars.Task.InvisibleUtil_Task;
import cn.comicalpixel.creeperstarbedwars.Utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

            ToolItemsManager.reset_all(p);
            ToolItemsManager.giveItems(p);

            p.getEnderChest().clear();

            p.setLevel(0);
            p.setExp(0);

            p.setFlying(false);
            p.setAllowFlight(false);

            PlayerArmorManager.reset(p);

            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-countdown-start");
            },5);
            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.game-start");
            },7);

            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                startTitle(p);
            },3);

        }

        // 清除无效床
        BedBlockUtils.clear_bed_GameStart();

        // 床无敌
        BedInvincibilityEvent.start();

        // 启动资源点
        new StartGenerator();

        // 启动游戏事件
        new GameTimerEvent_Main();

        // 游戏开始chat

        for (String s : ConfigData.language_game_start_chat) {
            MessageUtils.broadcastMessage(s);
        }


        // 淘汰队伍检测Task
        new GameTeamEliminated_Task();

        // 小贴士Actionbar
        if (!ConfigData.language_game_start_tips_actionbar.isEmpty()) {
            for (Player p : GamePlayers.players) {
                ActionBarUtils.sendActionBar(p, ConfigData.language_game_start_tips_actionbar.get(new Random().nextInt(ConfigData.language_game_start_tips_actionbar.size())));
            }
        }
        // 启动游戏追踪Actionbar
        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            new Game_Actionbar_Task();
        },90);
        // 玩家追踪指南针
        new Compass_Item();

        // SafeRegion
        PlayerBlocks.readSafeRegion();

        // 商店NPC
        ShopNPC_Manager.spawn_all_ItemShop();
        ShopNPC_Manager.spawn_all_UpdateShop();

        // 初始化队伍升级部分
        TeamShop_GUI.gamestart_reset();

        // 队伍升级Task
        new TeamShop_Manager();

        // 隐身Task
        new InvisibleUtil_Task();

    }


    public static void startTitle(Player p) {

        if (!ConfigData.gamestart_title_enabled) return;

        FileConfiguration config = CreeperStarBedwars.getPlugin().getConfig();

        List<String> actions = ConfigData.gamestart_title_actions;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        for (int i = 0; i < actions.size(); i++) {
            int finalI = i;
            scheduler.schedule(() -> {
                if (finalI > actions.size()) {
                    scheduler.shutdown();
                    return;
                }
                NMSTitleUntils.Title.send(p, ConfigUtils.getString(config, "start-title.action."+actions.get(finalI)+".title"), ConfigUtils.getString(config,"start-title.action."+actions.get(finalI)+".subtitle"), 1, 40, 10);
            }, i * ConfigUtils.getInt(config, "start-title.timer-ms") + 1L, TimeUnit.MILLISECONDS);
        }

    }

}
