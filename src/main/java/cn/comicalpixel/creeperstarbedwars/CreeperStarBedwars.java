package cn.comicalpixel.creeperstarbedwars;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.GUI.TeamSel_GUI;
import cn.comicalpixel.creeperstarbedwars.Command.MainCommand;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Config.GameConfig;
import cn.comicalpixel.creeperstarbedwars.Entity.AntiMobs;
import cn.comicalpixel.creeperstarbedwars.Fix.ComboFix;
import cn.comicalpixel.creeperstarbedwars.Fix.LadderFix;
import cn.comicalpixel.creeperstarbedwars.GUI.BwimSel_GUI;
import cn.comicalpixel.creeperstarbedwars.GameSetup.SetupCommand;
import cn.comicalpixel.creeperstarbedwars.GameSetup.SetupListener;
import cn.comicalpixel.creeperstarbedwars.Items.FIREBALL_Item;
import cn.comicalpixel.creeperstarbedwars.Listener.*;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Actionbar_Task;
import cn.comicalpixel.creeperstarbedwars.Task.Game_Countdown_Task;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

// git push -u origin main

public final class CreeperStarBedwars extends JavaPlugin {

    public static CreeperStarBedwars Instance;
    public static CreeperStarBedwars getInstance() {return Instance;}
    public static CreeperStarBedwars getPlugin() {return Instance;}

    private GameConfig gameConfig;
    public GameConfig getGameConfig() {return gameConfig;}

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;

        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info("\n" +
                "   ______                                  _____ __                ____           __                        \n" +
                "  / ____/_______  ___  ____  ___  _____   / ___// /_____ ______   / __ )___  ____/ /      ______ ___________\n" +
                " / /   / ___/ _ \\/ _ \\/ __ \\/ _ \\/ ___/   \\__ \\/ __/ __ `/ ___/  / __  / _ \\/ __  / | /| / / __ `/ ___/ ___/\n" +
                "/ /___/ /  /  __/  __/ /_/ /  __/ /      ___/ / /_/ /_/ / /     / /_/ /  __/ /_/ /| |/ |/ / /_/ / /  (__  ) \n" +
                "\\____/_/   \\___/\\___/ .___/\\___/_/      /____/\\__/\\__,_/_/     /_____/\\___/\\__,_/ |__/|__/\\__,_/_/  /____/  \n" +
                "                   /_/                                                                                      " +
                "\n " +
                "\n " +
                "CreeperStarBedwars Plugin.\n" +
                " Author: Xiaol789zxc\n" +
                " Version: " + this.getDescription().getVersion() + "\n" +
                "\n " +
                "Minecraft Version: " + Bukkit.getVersion() + "\n " +
                "\n " );
        // 检查是否非folia
        if (Bukkit.getVersion().contains("folia")) {
            Bukkit.getLogger().warning("CreeperStarBedwars does not support folia, please use version 1.8.8 of CraftBukkit or Spigot or Paper server kernel!");
            this.getPluginLoader().disablePlugin(this);
        }

        getServer().getMessenger().registerOutgoingPluginChannel(CreeperStarBedwars.getPlugin(), "BungeeCord");

//        Bukkit.getLogger().info(" ");
//        Bukkit.getLogger().info("CreeperStarBedwars Plugin.");
//        Bukkit.getLogger().info("Author: Xiaol789zxc");
//        Bukkit.getLogger().info("Version: " + this.getDescription().getVersion());
//        Bukkit.getLogger().info("");
//        Bukkit.getLogger().info("Minecraft Version: " + Bukkit.getVersion());
//        Bukkit.getLogger().info("");

        // 指令部分
        getCommand("bw").setExecutor(new MainCommand());
        getCommand("csbw").setExecutor(new MainCommand());
        getCommand("cbw").setExecutor(new MainCommand());
        getCommand("creeperstarbedwars").setExecutor(new MainCommand());

        // 检查必要的前置是否齐全
        // 不齐全会阻止加入且提示缺失哪些必要前置(仅op提示插件详情)
        JoinPluginCheck.check();
        getServer().getPluginManager().registerEvents(new JoinPluginCheck(), this);


        // 加载配置文件
        saveDefaultConfig();
        CreeperStarBedwars.Instance.load_config();

        // 加载游戏配置文件
        gameConfig = new GameConfig(this,"game.yml");
        startGameCfgRead();


        /**/
        /*kt*/

        // SETUP
        getServer().getPluginManager().registerEvents(new SetupListener(), this);
        getCommand("setup").setExecutor(new SetupCommand());


        // 游戏倒计时
        new Game_Countdown_Task();

        // Actionbar
        new Game_Actionbar_Task();

        // 修复
        getServer().getPluginManager().registerEvents(new ComboFix(), this);
        getServer().getPluginManager().registerEvents(new LadderFix(), this);


        // motd
        getServer().getPluginManager().registerEvents(new ServerMotdListener(), this);

        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(), this);

        getServer().getPluginManager().registerEvents(new AntiMobs(), this);
        AntiMobs.clear_task();

        getServer().getPluginManager().registerEvents(new TeamSel_GUI(), this);
        getServer().getPluginManager().registerEvents(new BwimSel_GUI(), this);

        getServer().getPluginManager().registerEvents(new PlayerDamage(), this);

        getServer().getPluginManager().registerEvents(new PlayerFoodLevel(), this);

        getServer().getPluginManager().registerEvents(new BwimResItemManager(), this);

        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        getServer().getPluginManager().registerEvents(new PlayerBlocks(), this);

        getServer().getPluginManager().registerEvents(new DeathMove(), this);


        getServer().getPluginManager().registerEvents(new FIREBALL_Item(), this);

    }

    public void startGameCfgRead() {
        GameStats.set(-1);
        new BukkitRunnable() {
            @Override
            public void run() {
                loadGameConfig();
            }
        }.runTaskLater(CreeperStarBedwars.getPlugin(), 10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info("CreeperStarBedwars Plugin Disable.");
        Bukkit.getLogger().info(" ");
    }

    public void load_config() {

        FileConfiguration config = this.getConfig();

        if (config.getString("author") == null || !config.getString("author").equals("Xiaol789zxc")) {
            Bukkit.getLogger().warning("Please set the author setting in the config.yml folder to \"Xiaol789zxc\" and restart the server after changing it.");
            this.getPluginLoader().disablePlugin(this);
        }

        ConfigData.bungeecord_lobby = ConfigUtils.getString(config, "bungeecord.lobby-server");
        ConfigData.bungeecord_motd_waiting = ConfigUtils.getString(config, "bungeecord.motd-waiting");
        ConfigData.bungeecord_motd_playing = ConfigUtils.getString(config, "bungeecord.motd-playing");
        ConfigData.bungeecord_motd_end = ConfigUtils.getString(config, "bungeecord.motd-end");
        ConfigData.bungeecord_restart_command = ConfigUtils.getString(config, "bungeecord.restart-command");

        ConfigData.nametag_health_enabled = ConfigUtils.getBoolean(config, "nametag-health");
        ConfigData.nametag_prefix = ConfigUtils.getString(config, "player-nametag.prefix");
        ConfigData.nametag_suffix = ConfigUtils.getString(config, "player-nametag.suffix");

        ConfigData.fast_respawn_enabled = ConfigUtils.getBoolean(config, "fast-respawn");

        ConfigData.timer_command_enabled = ConfigUtils.getBoolean(config, "timer-command.enable");
        ConfigData.timer_command_start = ConfigUtils.getStringList(config, "timer-command.game-start");
        ConfigData.timer_command_end = ConfigUtils.getStringList(config, "timer-command.game-end");

        if (config.get("timer-command.game-timer-bh") != null) {
            for (String cmds : ConfigUtils.getStringList(config, "timer-command.game-timer-bh")) {
                ConfigData.timer_command_cmds.put(ConfigUtils.getInt(config, "timer-command.game-timer."+cmds+".gametime") , ConfigUtils.getStringList(config, "timer-command.game-timer."+cmds+".commands"));
            }
            // JoinPluginCheck.plugins.add(ConfigData.timer_command_cmds.toString()); 仅用于测试请勿恢复这行代码!!
        }

        ConfigData.event_command_enabled = ConfigUtils.getBoolean(config, "event-command.enable");
        ConfigData.event_command_game_start = ConfigUtils.getStringList(config, "event-command.game-start");
        ConfigData.event_command_game_start = ConfigUtils.getStringList(config, "event-command.game-end");
        ConfigData.event_command_player_death = ConfigUtils.getStringList(config, "event-command.player-death");
        ConfigData.event_command_player_kill = ConfigUtils.getStringList(config, "event-command.player-kill");
        ConfigData.event_command_player_win = ConfigUtils.getStringList(config, "event-command.player-win");
        ConfigData.event_command_player_lost = ConfigUtils.getStringList(config, "event-command.player-lost");
        ConfigData.event_command_player_dbed = ConfigUtils.getStringList(config, "event-command.player-bed");

        ConfigData.whitelist_cmds = ConfigUtils.getStringList(config, "command-whitelist");

        ConfigData.teamsel_enabled = ConfigUtils.getBoolean(config, "select_team.enable");
        ConfigData.teamsel_gui_name = ConfigUtils.getString(config, "select_team.gui-name");
        ConfigData.teamsel_gui_items_status_select = ConfigUtils.getString(config, "select_team.status-select");
        ConfigData.teamsel_gui_items_status_inteam = ConfigUtils.getString(config, "select_team.status-inteam");
        ConfigData.teamsel_gui_items_status_full = ConfigUtils.getString(config, "select_team.status-full");
        ConfigData.teamsel_gui_items_wool_name = ConfigUtils.getString(config, "select_team.team-item.name");
        ConfigData.teamsel_gui_items_wool_lore_head = ConfigUtils.getStringList(config, "select_team.team-item.lore-head");
        ConfigData.teamsel_gui_items_wool_lore_foot = ConfigUtils.getStringList(config, "select_team.team-item.lore-foot");
        ConfigData.teamsel_gui_items_wool_lore_player = ConfigUtils.getString(config, "select_team.team-item.lore-player");
        ConfigData.teamsel_gui_items_leave_name = ConfigUtils.getString(config, "select_team.leave-item.name");
        ConfigData.teamsel_gui_items_leave_lore = ConfigUtils.getStringList(config, "select_team.leave-item.lore");
        ConfigData.teamsel_gui_items_close_name = ConfigUtils.getString(config, "select_team.close-item.name");
        ConfigData.teamsel_gui_items_close_lore = ConfigUtils.getStringList(config, "select_team.close-item.lore");

        ConfigData.bwimsel_enabled = ConfigUtils.getBoolean(config, "select-bwim.enable");
        ConfigData.bwimsel_default = ConfigUtils.getInt(config, "select-bwim.def");
        ConfigData.bwimsel_gui_items_i_type = ConfigUtils.getString(config, "select-bwim.item.i.type");
        ConfigData.bwimsel_gui_items_i_durability = ConfigUtils.getInt(config, "select-bwim.item.i.durability");
        ConfigData.bwimsel_gui_items_i_name = ConfigUtils.getString(config, "select-bwim.item.i.name");
        ConfigData.bwimsel_gui_items_i_lore = ConfigUtils.getStringList(config, "select-bwim.item.i.lore");
        ConfigData.bwimsel_gui_items_xp_type = ConfigUtils.getString(config, "select-bwim.item.xp.type");
        ConfigData.bwimsel_gui_items_xp_durability = ConfigUtils.getInt(config, "select-bwim.item.xp.durability");
        ConfigData.bwimsel_gui_items_xp_name = ConfigUtils.getString(config, "select-bwim.item.xp.name");
        ConfigData.bwimsel_gui_items_xp_lore = ConfigUtils.getStringList(config, "select-bwim.item.xp.lore");
        ConfigData.bwimsel_gui_items_status_select = ConfigUtils.getString(config, "select-bwim.status-select");
        ConfigData.bwimsel_gui_items_status_noselect = ConfigUtils.getString(config, "select-bwim.status-noselect");

        ConfigData.bwim_conversion_iron = ConfigUtils.getInt(config, "select-bwim.conversion.iron");
        ConfigData.bwim_conversion_gold = ConfigUtils.getInt(config, "select-bwim.conversion.gold");
        ConfigData.bwim_conversion_emerald = ConfigUtils.getInt(config, "select-bwim.conversion.emerald");
        ConfigData.bwim_conversion_diamond = ConfigUtils.getInt(config, "select-bwim.conversion.diamond");

        ConfigData.damageholo_damage_enabled = ConfigUtils.getBoolean(config, "damage-holo.damage.enable");
        ConfigData.damageholo_bow_enabled = ConfigUtils.getBoolean(config, "damage-holo.bow.enable");

        ConfigData.combo_fix_enabled = ConfigUtils.getBoolean(config, "combo-fix");

        ConfigData.ladder_fix_enabled = ConfigUtils.getBoolean(config, "ladder-fix");

        ConfigData.lock_foodlevel_enabled = ConfigUtils.getBoolean(config, "lock-foodlevel");

        ConfigData.gamestart_title_enabled = ConfigUtils.getBoolean(config, "start-title.enable");
        ConfigData.gamestart_title_title = ConfigUtils.getStringList(config, "start-title.title");
        ConfigData.gamestart_title_subtitle = ConfigUtils.getString(config, "start-title.subtitle");
        ConfigData.gamestart_title_countdown_enabled = ConfigUtils.getBoolean(config, "start-title.countdown-title.enable");
        ConfigData.gamestart_title_countdown_5s_title = ConfigUtils.getString(config, "start-title.countdown-5s.title");
        ConfigData.gamestart_title_countdown_5s_subtitle = ConfigUtils.getString(config, "start-title.countdown-5s.subtitle");
        ConfigData.gamestart_title_countdown_4s_title = ConfigUtils.getString(config, "start-title.countdown-4s.title");
        ConfigData.gamestart_title_countdown_4s_subtitle = ConfigUtils.getString(config, "start-title.countdown-4s.subtitle");
        ConfigData.gamestart_title_countdown_3s_title = ConfigUtils.getString(config, "start-title.countdown-3s.title");
        ConfigData.gamestart_title_countdown_3s_subtitle = ConfigUtils.getString(config, "start-title.countdown-3s.subtitle");
        ConfigData.gamestart_title_countdown_2s_title = ConfigUtils.getString(config, "start-title.countdown-2s.title");
        ConfigData.gamestart_title_countdown_2s_subtitle = ConfigUtils.getString(config, "start-title.countdown-2s.subtitle");
        ConfigData.gamestart_title_countdown_1s_title = ConfigUtils.getString(config, "start-title.countdown-1s.title");
        ConfigData.gamestart_title_countdown_1s_subtitle = ConfigUtils.getString(config, "start-title.countdown-1s.subtitle");

        ConfigData.xpbar_countdown = ConfigUtils.getBoolean(config, "countdown-xpbar");

        ConfigData.countdown_settings_seconds = ConfigUtils.getInt(config, "countdown-settings.seconds");
        ConfigData.countdown_settings_trigger_seconds = ConfigUtils.getIntList(config, "countdown-settings.trigger-seconds");
        // 这里先读取countdown_settings_trigger_seconds的，如果找不到自定义指定x秒的标题obj那就直接用other
        // ConfigData.countdown_seconds_message_paths = ConfigUtils.getStringList(config, "countdown-settings.trigger.seconds");
        // 优化了下, 希望别出问题qwq
        if (ConfigData.countdown_settings_trigger_seconds != null) {
            for (int i : ConfigData.countdown_settings_trigger_seconds) {
                if (config.get("countdown-settings.seconds-message."+i) != null) {
                    ConfigData.countdown_seconds_message_title.put(i, ConfigUtils.getString(config, "countdown-settings.seconds-message."+i+".title"));
                    ConfigData.countdown_seconds_message_subtitle.put(i, ConfigUtils.getString(config, "countdown-settings.seconds-message."+i+".subtitle"));
                }
            }
        }


        ConfigData.LobbyItems_i0_enabled = ConfigUtils.getBoolean(config, "lobby-items.i0.enable");
        ConfigData.LobbyItems_i0_command = ConfigUtils.getString(config, "lobby-items.i0.command");
        ConfigData.LobbyItems_i0_permissions = ConfigUtils.getString(config, "lobby-items.i0.permissions");
        ConfigData.LobbyItems_i0_item = ConfigUtils.getItemStack(config, "lobby-items.i0.item");
        ConfigData.LobbyItems_i1_enabled = ConfigUtils.getBoolean(config, "lobby-items.i1.enable");
        ConfigData.LobbyItems_i1_command = ConfigUtils.getString(config, "lobby-items.i1.command");
        ConfigData.LobbyItems_i1_permissions = ConfigUtils.getString(config, "lobby-items.i1.permissions");
        ConfigData.LobbyItems_i1_item = ConfigUtils.getItemStack(config, "lobby-items.i1.item");
        ConfigData.LobbyItems_i2_enabled = ConfigUtils.getBoolean(config, "lobby-items.i2.enable");
        ConfigData.LobbyItems_i2_command = ConfigUtils.getString(config, "lobby-items.i2.command");
        ConfigData.LobbyItems_i2_permissions = ConfigUtils.getString(config, "lobby-items.i2.permissions");
        ConfigData.LobbyItems_i2_item = ConfigUtils.getItemStack(config, "lobby-items.i2.item");
        ConfigData.LobbyItems_i3_enabled = ConfigUtils.getBoolean(config, "lobby-items.i3.enable");
        ConfigData.LobbyItems_i3_command = ConfigUtils.getString(config, "lobby-items.i3.command");
        ConfigData.LobbyItems_i3_permissions = ConfigUtils.getString(config, "lobby-items.i3.permissions");
        ConfigData.LobbyItems_i3_item = ConfigUtils.getItemStack(config, "lobby-items.i3.item");
        ConfigData.LobbyItems_i4_enabled = ConfigUtils.getBoolean(config, "lobby-items.i4.enable");
        ConfigData.LobbyItems_i4_command = ConfigUtils.getString(config, "lobby-items.i4.command");
        ConfigData.LobbyItems_i4_permissions = ConfigUtils.getString(config, "lobby-items.i4.permissions");
        ConfigData.LobbyItems_i4_item = ConfigUtils.getItemStack(config, "lobby-items.i4.item");
        ConfigData.LobbyItems_i5_enabled = ConfigUtils.getBoolean(config, "lobby-items.i5.enable");
        ConfigData.LobbyItems_i5_command = ConfigUtils.getString(config, "lobby-items.i5.command");
        ConfigData.LobbyItems_i5_permissions = ConfigUtils.getString(config, "lobby-items.i5.permissions");
        ConfigData.LobbyItems_i5_item = ConfigUtils.getItemStack(config, "lobby-items.i5.item");
        ConfigData.LobbyItems_i6_enabled = ConfigUtils.getBoolean(config, "lobby-items.i6.enable");
        ConfigData.LobbyItems_i6_command = ConfigUtils.getString(config, "lobby-items.i6.command");
        ConfigData.LobbyItems_i6_permissions = ConfigUtils.getString(config, "lobby-items.i6.permissions");
        ConfigData.LobbyItems_i6_item = ConfigUtils.getItemStack(config, "lobby-items.i6.item");
        ConfigData.LobbyItems_i7_enabled = ConfigUtils.getBoolean(config, "lobby-items.i7.enable");
        ConfigData.LobbyItems_i7_command = ConfigUtils.getString(config, "lobby-items.i7.command");
        ConfigData.LobbyItems_i7_permissions = ConfigUtils.getString(config, "lobby-items.i7.permissions");
        ConfigData.LobbyItems_i7_item = ConfigUtils.getItemStack(config, "lobby-items.i7.item");
        ConfigData.LobbyItems_i8_enabled = ConfigUtils.getBoolean(config, "lobby-items.i8.enable");
        ConfigData.LobbyItems_i8_command = ConfigUtils.getString(config, "lobby-items.i8.command");
        ConfigData.LobbyItems_i8_permissions = ConfigUtils.getString(config, "lobby-items.i8.permissions");
        ConfigData.LobbyItems_i8_item = ConfigUtils.getItemStack(config, "lobby-items.i8.item");


        ConfigData.generator_team_l0_iron = ConfigUtils.getDouble(config, "generator-level.team-0.iron");
        ConfigData.generator_team_l0_gold = ConfigUtils.getDouble(config, "generator-level.team-0.gold");
        ConfigData.generator_team_l0_emerald = ConfigUtils.getDouble(config, "generator-level.team-0.emerald");
        ConfigData.generator_team_l1_iron = ConfigUtils.getDouble(config, "generator-level.team-1.iron");
        ConfigData.generator_team_l1_gold = ConfigUtils.getDouble(config, "generator-level.team-1.gold");
        ConfigData.generator_team_l1_emerald = ConfigUtils.getDouble(config, "generator-level.team-1.emerald");
        ConfigData.generator_team_l2_iron = ConfigUtils.getDouble(config, "generator-level.team-2.iron");
        ConfigData.generator_team_l2_gold = ConfigUtils.getDouble(config, "generator-level.team-2.gold");
        ConfigData.generator_team_l2_emerald = ConfigUtils.getDouble(config, "generator-level.team-2.emerald");
        ConfigData.generator_team_l3_iron = ConfigUtils.getDouble(config, "generator-level.team-3.iron");
        ConfigData.generator_team_l3_gold = ConfigUtils.getDouble(config, "generator-level.team-3.gold");
        ConfigData.generator_team_l3_emerald = ConfigUtils.getDouble(config, "generator-level.team-3.emerald");
        ConfigData.generator_team_l4_iron = ConfigUtils.getDouble(config, "generator-level.team-4.iron");
        ConfigData.generator_team_l4_gold = ConfigUtils.getDouble(config, "generator-level.team-4.gold");
        ConfigData.generator_team_l4_emerald = ConfigUtils.getDouble(config, "generator-level.team-4.emerald");

        ConfigData.generator_diamond_l1 = ConfigUtils.getDouble(config, "generator-level.diamond-1");
        ConfigData.generator_diamond_l2 = ConfigUtils.getDouble(config, "generator-level.diamond-2");
        ConfigData.generator_diamond_l3 = ConfigUtils.getDouble(config, "generator-level.diamond-3");

        ConfigData.generator_emerald_l1 = ConfigUtils.getDouble(config, "generator-level.emerald-1");
        ConfigData.generator_emerald_l2 = ConfigUtils.getDouble(config, "generator-level.emerald-2");
        ConfigData.generator_emerald_l3 = ConfigUtils.getDouble(config, "generator-level.emerald-3");


        ConfigData.ItemsInGame_respawn_bed_enabled = ConfigUtils.getBoolean(config, "items.respawn-bed.enable");
        ConfigData.ItemInGame_respawn_bed_started = ConfigUtils.getInt(config, "items.respawn-bed.started");

        ConfigData.ItemInGame_respawn_bed_l_expires_chat = ConfigUtils.getString(config, "items.respawn-bed.expires-chat");
        ConfigData.ItemInGame_respawn_bed_l_spawnloc_toofar = ConfigUtils.getString(config, "items.respawn-bed.spawnloc-toofar");
        ConfigData.ItemInGame_respawn_bed_l_used = ConfigUtils.getString(config, "items.respawn-bed.used");
        ConfigData.ItemInGame_respawn_bed_l_bedexist = ConfigUtils.getString(config, "items.respawn-bed.bed-exist");

        ConfigData.ItemsInGame_fireball_enabled = ConfigUtils.getBoolean(config, "items.fireball.enable");
        ConfigData.ItemsInGame_fireball_cooldown = ConfigUtils.getInt(config, "items.fireball.cooldown");
        ConfigData.ItemsInGame_fireball_radius = ConfigUtils.getInt(config, "items.fireball.radius");
        ConfigData.ItemsInGame_fireball_damage = ConfigUtils.getInt(config, "items.fireball.damage");
        ConfigData.ItemsInGame_fireball_vlocity_multiply = ConfigUtils.getDouble(config, "items.fireball.vlocity-multiply");
        ConfigData.ItemsInGame_fireball_vlocity_y = ConfigUtils.getDouble(config, "items.fireball.vlocity-y");
        ConfigData.ItemsInGame_fireball_cooldown_chat = ConfigUtils.getString(config, "items.fireball.cooldown-chat");

        ConfigData.ItemsInGame_tnt_enabled = ConfigUtils.getBoolean(config, "items.tnt.enable");
        ConfigData.ItemsInGame_tnt_cooldown = ConfigUtils.getInt(config, "items.tnt.cooldown");
        ConfigData.ItemsInGame_tnt_radius = ConfigUtils.getInt(config, "items.tnt.radius");
        ConfigData.ItemsInGame_tnt_damage = ConfigUtils.getInt(config, "items.tnt.damage");
        ConfigData.ItemsInGame_tnt_vlocity_multiply = ConfigUtils.getDouble(config, "items.tnt.vlocity-multiply");
        ConfigData.ItemsInGame_tnt_cooldown_chat = ConfigUtils.getString(config, "items.tnt.cooldown-chat");

        ConfigData.ItemsInGame_compass_enabled = ConfigUtils.getBoolean(config, "items.Compass.enable");

        ConfigData.ItemsInGame_dreamguard_enabled = ConfigUtils.getBoolean(config, "items.dreamguard.enable");
        ConfigData.ItemsInGame_dreamguard_cooldown = ConfigUtils.getInt(config, "items.dreamguard.cooldown");
        ConfigData.ItemsInGame_dreamguard_cooldown_chat = ConfigUtils.getString(config, "items.dreamguard.cooldown-chat");

        ConfigData.ItemsInGame_silverfish_enabled = ConfigUtils.getBoolean(config, "items.silverfish.enable");
        ConfigData.ItemsInGame_silverfish_cooldown = ConfigUtils.getInt(config, "items.silverfish.cooldown");
        ConfigData.ItemsInGame_silverfish_cooldown_chat = ConfigUtils.getString(config, "items.silverfish.cooldown-chat");

        ConfigData.ItemsInGame_bridge_egg_enabled = ConfigUtils.getBoolean(config, "items.bridge-eggs.enable");
        ConfigData.ItemsInGame_bridge_egg_cooldown = ConfigUtils.getInt(config, "items.bridge-eggs.cooldown");
        ConfigData.ItemsInGame_bridge_egg_cooldown_chat = ConfigUtils.getString(config, "items.bridge-eggs.cooldown-chat");

        ConfigData.ItemsInGame_safetower_enabled = ConfigUtils.getBoolean(config, "items.safetower.enable");
        ConfigData.ItemsInGame_safetower_cooldown = ConfigUtils.getInt(config, "items.safetower.cooldown");
        ConfigData.ItemsInGame_safetower_cooldown_chat = ConfigUtils.getString(config, "items.safetower.cooldown-chat");

        ConfigData.ItemsInGame_warp_powder_enabled = ConfigUtils.getBoolean(config, "items.warp-powder.enable");
        ConfigData.ItemsInGame_warp_powder_tpcountdown = ConfigUtils.getInt(config, "items.warp-powder.tpcountdown");
        ConfigData.ItemsInGame_warp_powder_tpcountdown_chat = ConfigUtils.getString(config, "items.warp-powder.tpcountdown-chat");
        ConfigData.ItemsInGame_warp_powder_move_cancel_chat = ConfigUtils.getString(config, "items.warp-powder.move-cancel-chat");

        ConfigData.ItemsInGame_safewall_enabled = ConfigUtils.getBoolean(config, "items.safewall.enable");
        ConfigData.ItemsInGame_safewall_cooldown = ConfigUtils.getInt(config, "items.safewall.cooldown");
        ConfigData.ItemsInGame_safewall_cooldown_chat = ConfigUtils.getString(config, "items.safewall.cooldown-chat");

        ConfigData.ItemsInGame_rescue_platform_enabled = ConfigUtils.getBoolean(config, "items.rescue.rescue-platform.enable");
        ConfigData.ItemsInGame_rescue_platform_cooldown = ConfigUtils.getInt(config, "items.rescue.platform.cooldown");
        ConfigData.ItemsInGame_rescue_platform_disappear = ConfigUtils.getInt(config, "items.rescue.platform.disappear");
        ConfigData.ItemsInGame_rescue_platform_can_break = ConfigUtils.getBoolean(config, "items.rescue.platform.can-break");
        ConfigData.ItemsInGame_rescue_platform_cooldown_chat = ConfigUtils.getString(config, "items.rescue.platform.cooldown-chat");
        ConfigData.ItemsInGame_rescue_platform_insufficient_space_chat = ConfigUtils.getString(config, "items.rescue.platform.insufficient-space-chat");

        ConfigData.ItemsInGame_firework_enabled = ConfigUtils.getBoolean(config, "items.firework.enabled");
        ConfigData.ItemsInGame_firework_cooldown = ConfigUtils.getInt(config, "items.firework.cooldown");
        ConfigData.ItemsInGame_firework_cooldown_chat = ConfigUtils.getString(config, "items.firework.cooldown-chat");
        ConfigData.ItemsInGame_firework_subtitle = ConfigUtils.getString(config, "items.firework.subtitle");

        ConfigData.beddanger_enabled = ConfigUtils.getBoolean(config, "beddanger-title.enabled");
        ConfigData.beddanger_subtitle = ConfigUtils.getString(config, "beddanger-title.subtitle");

        ConfigData.bed_invincibility_enabled = ConfigUtils.getBoolean(config, "bed-invincibility.enabled");
        ConfigData.bed_invincibility_started_timer = ConfigUtils.getInt(config, "bed-invincibility.timer");


        // 语言部分!!
        ConfigData.language_date = ConfigUtils.getString(config, "language.date");

        ConfigData.language_joingame_chat = ConfigUtils.getString(config, "language.joingame-chat");
        ConfigData.language_leavegame_chat = ConfigUtils.getString(config, "language.leavegame-chat");

        // ConfigData.language_rejoin_chat = ConfigUtils.getString(config, "language.rejoin-chat");
        // rejoin打算写扩展

        ConfigData.damageholo_damage_title = ConfigUtils.getString(config, "language.damageholo-damage-title");
        ConfigData.damageholo_damage_subtitle = ConfigUtils.getString(config, "language.damageholo-damage-subtitle");
        ConfigData.damageholo_damage_chat = ConfigUtils.getString(config, "language.damageholo-damage-chat");
        ConfigData.damageholo_bow_title = ConfigUtils.getString(config, "language.damageholo-bow-title");
        ConfigData.damageholo_bow_subtitle = ConfigUtils.getString(config, "language.damageholo-bow-subtitle");
        ConfigData.damageholo_bow_chat = ConfigUtils.getString(config, "language.damageholo-bow-chat");

        ConfigData.language_game_start_chat = ConfigUtils.getStringList(config, "language.game-start-chat");

        ConfigData.language_game_end_chat = ConfigUtils.getStringList(config, "language.game-end-chat");

        ConfigData.language_generatorholo_diamond_list = ConfigUtils.getStringList(config, "language.generatorholo-diamond-list");
        ConfigData.language_generatorholo_emerald_list = ConfigUtils.getStringList(config, "language.generatorholo-emerald-list");
        ConfigData.language_generatorholo_team_list = ConfigUtils.getStringList(config, "language.generatorholo-team-list");
        ConfigData.language_generatorholo_ywz_all = ConfigUtils.getStringList(config, "language.generatorholo-ywz");

        ConfigData.language_bed_destroy_ismyteam_chat = ConfigUtils.getString(config, "language.bed-destroy-ismyteam-chat");
        ConfigData.language_bed_destroy_chat = ConfigUtils.getString(config, "language.bed-destroy-chat");
        ConfigData.language_bed_destroy_team_title = ConfigUtils.getString(config, "language.bed-destroy-team-title");
        ConfigData.language_bed_destroy_team_subtitle = ConfigUtils.getString(config, "language.bed-destroy-team-subtitle");
        ConfigData.language_bed_destroy_me_title = ConfigUtils.getString(config, "language.bed-destroy-me-title");
        ConfigData.language_bed_destroy_me_subtitle = ConfigUtils.getString(config, "language.bed-destroy-me-subtitle");
        ConfigData.language_bed_destroy_all_title = ConfigUtils.getString(config, "language.bed-destroy-all-title");
        ConfigData.language_bed_destroy_all_subtitle = ConfigUtils.getString(config, "language.bed-destroy-all-subtitle");

        ConfigData.language_bed_respawn_chat = ConfigUtils.getString(config, "language.bed-respawn-chat");
        ConfigData.language_bed_respawn_team_title = ConfigUtils.getString(config, "language.bed-respawn-team-title");
        ConfigData.language_bed_respawn_team_subtitle = ConfigUtils.getString(config, "language.bed-respawn-team-subtitle");
        ConfigData.language_bed_respawn_all_title = ConfigUtils.getString(config, "language.bed-respawn-all-title");
        ConfigData.language_bed_respawn_all_subtitle = ConfigUtils.getString(config, "language.bed-respawn-all-subtitle");

        ConfigData.language_team_annihilation_chat = ConfigUtils.getString(config, "language.team-annihilation-chat");

        ConfigData.language_bed_sleep_chat = ConfigUtils.getString(config, "language.bed-sleep");

        ConfigData.language_game_countdown_InsufficientPlayers_title = ConfigUtils.getString(config, "language.game-countdown-InsufficientPlayers-title");
        ConfigData.language_game_countdown_InsufficientPlayers_subtitle = ConfigUtils.getString(config, "language.game-countdown-InsufficientPlayers-subtitle");
        ConfigData.language_game_countdown_InsufficientPlayers_chat = ConfigUtils.getString(config, "language.game-countdown-InsufficientPlayers-chat");
        ConfigData.language_game_countdown_other_title = ConfigUtils.getString(config, "language.game-countdown-other-title");
        ConfigData.language_game_countdown_other_subtitle = ConfigUtils.getString(config, "language.game-countdown-other-subtitle");
        ConfigData.language_game_countdown_chat = ConfigUtils.getString(config, "language.game-countdown-chat");

        ConfigData.language_game_lobby_actionbar = ConfigUtils.getString(config, "language.game-lobby-actionbar");

        ConfigData.language_teamsel_join_full = ConfigUtils.getString(config, "language.teamsel-join-full");
        ConfigData.language_teamsel_join_done = ConfigUtils.getString(config, "language.teamsel-join-done");
        ConfigData.language_teamsel_leave = ConfigUtils.getString(config, "language.teamsel-leave");

        ConfigData.language_bwim_i_name = ConfigUtils.getString(config, "language.game-bwim-i");
        ConfigData.language_bwim_xp_name = ConfigUtils.getString(config, "language.game-bwim-xp");

        ConfigData.language_sidebarboard_team_ismyteam = ConfigUtils.getString(config, "language.sidebarboard.team-ismyteam");
        ConfigData.language_sidebarboard_team_hasbed = ConfigUtils.getString(config, "language.sidebarboard.team-hasbed");
        ConfigData.language_sidebarboard_team_notbed = ConfigUtils.getString(config, "language.sidebarboard.team-notbed");
        ConfigData.language_sidebarboard_team_message = ConfigUtils.getString(config, "language.sidebarboard.team-message");

        ConfigData.language_sidebarboard_list_lobby_waiting = ConfigUtils.getStringList(config, "language.sidebarboard.lobby.waiting");
        ConfigData.language_sidebarboard_list_lobby_countdown = ConfigUtils.getStringList(config, "language.sidebarboard.lobby.countdown");
        ConfigData.language_sidebarboard_list_playing_player = ConfigUtils.getStringList(config, "language.sidebarboard.game.player");
        ConfigData.language_sidebarboard_list_playing_spec = ConfigUtils.getStringList(config, "language.sidebarboard.game.spec");

        ConfigData.language_game_end_winner_title = ConfigUtils.getString(config, "language.game-end-winner-title");
        ConfigData.language_game_end_winner_subtitle = ConfigUtils.getString(config, "language.game-end-winner-subtitle");
        ConfigData.language_game_end_loser_title = ConfigUtils.getString(config, "language.game-end-loser-title");
        ConfigData.language_game_end_loser_subtitle = ConfigUtils.getString(config, "language.game-end-loser-subtitle");
        ConfigData.language_game_end_stalemate_title = ConfigUtils.getString(config, "language.game-end-stalemate-title");
        ConfigData.language_game_end_stalemate_subtitle = ConfigUtils.getString(config, "language.game-end-stalemate-subtitle");

        ConfigData.language_respawn_respawning_title = ConfigUtils.getString(config, "language.respawn-respawning-title");
        ConfigData.language_respawn_respawning_subtitle = ConfigUtils.getString(config, "language.respawn-respawning-subtitle");
        ConfigData.language_respawn_respawning_chat = ConfigUtils.getString(config, "language.respawn-respawning-chat");
        ConfigData.language_respawn_respawn_title = ConfigUtils.getString(config, "language.respawn-respawn-title");
        ConfigData.language_respawn_respawn_subtitle = ConfigUtils.getString(config, "language.respawn-respawn-subtitle");
        ConfigData.language_respawn_eliminated_title = ConfigUtils.getString(config, "language.respawn-eliminated-title");
        ConfigData.language_respawn_eliminated_subtitle = ConfigUtils.getString(config, "language.respawn-eliminated-subtitle");
        ConfigData.language_respawn_eliminated_chat = ConfigUtils.getString(config, "language.respawn-eliminated-chat");

        ConfigData.language_playerdie_byplayer = ConfigUtils.getString(config, "language.playerdie-player");
        ConfigData.language_playerdie_void = ConfigUtils.getString(config, "language.playerdie-void");
        ConfigData.language_playerdie_out = ConfigUtils.getString(config, "language.playerdie-out");
        ConfigData.language_playerdie_fall = ConfigUtils.getString(config, "language.playerdie-fall");
        ConfigData.language_playerdie_boom = ConfigUtils.getString(config, "language.playerdie-boom");
        ConfigData.language_playerdie_shoot = ConfigUtils.getString(config, "language.playerdie-shoot");
        ConfigData.language_playerdie_none = ConfigUtils.getString(config, "language.playerdie-null");
        ConfigData.language_playerdie_killer_ = ConfigUtils.getString(config, "language.playerdie-killer");
        ConfigData.language_playerdie_final_ = ConfigUtils.getString(config, "language.playerdie-final");

        ConfigData.language_game_start_tips_actionbar = ConfigUtils.getStringList(config, "language.game-start-tips");

        ConfigData.language_team_tracking_actionbar = ConfigUtils.getString(config, "language.team-tracking-actionbar");

        ConfigData.language_compass_tracking_actionbar = ConfigUtils.getString(config, "language.compass-actionbar");

        ConfigData.language_teamchest_notismyteam = ConfigUtils.getString(config, "language.team-chast-notismyteam");

        ConfigData.language_shopgui_item_name = ConfigUtils.getString(config, "language.shop-item-name");
        ConfigData.language_shopgui_update_name = ConfigUtils.getString(config, "language.shop-update-name");

        ConfigData.language_entity_silverfish_nametag = ConfigUtils.getString(config, "language.entity-silverfish-nametag");
        ConfigData.language_entity_dreamguard_nametag = ConfigUtils.getString(config, "language.entity-dreamguard-nametag");

        ConfigData.language_command_notin_whitelist = ConfigUtils.getString(config, "language.command-notin-whitelist");
        ConfigData.language_command_bwstart_done = ConfigUtils.getString(config, "language.command-bwstart-done");
        ConfigData.language_command_bwstart_nopermissions = ConfigUtils.getString(config, "language.command-bwstart-nopermissions");
        ConfigData.language_command_nopermissions = ConfigUtils.getString(config, "language.command-nopermissions");

        ConfigData.language_shop_buy_yes = ConfigUtils.getString(config, "language.shop-buy-yes");
        ConfigData.language_shop_buy_no = ConfigUtils.getString(config, "language.shop-buy-no");
        ConfigData.language_shop_buy_ed = ConfigUtils.getString(config, "language.shop-buy-ed");

        ConfigData.language_update_buy_me = ConfigUtils.getString(config, "language.update-buy-me");
        ConfigData.language_update_buy_team = ConfigUtils.getString(config, "language.update-buy-team");
        ConfigData.language_update_buy_no = ConfigUtils.getString(config, "language.update-buy-no");
        ConfigData.language_update_buy_max = ConfigUtils.getString(config, "language.update-buy-max");


        ConfigData.language_block_saferegion = ConfigUtils.getString(config, "language.block-saferegion");
        ConfigData.language_block_map_break = ConfigUtils.getString(config, "language.block-map-break");


        ConfigData.language_bed_invincibility_start_title = ConfigUtils.getString(config, "language.bed-invincibility-start-title");
        ConfigData.language_bed_invincibility_start_subtitle = ConfigUtils.getString(config, "language.bed-invincibility-start-subtitle");
        ConfigData.language_bed_invincibility_start_chat = ConfigUtils.getString(config, "language.bed-invincibility-start-chat");
        ConfigData.language_bed_invincibility_end_title = ConfigUtils.getString(config, "language.bed-invincibility-end-title");
        ConfigData.language_bed_invincibility_end_subtitle = ConfigUtils.getString(config, "language.bed-invincibility-end-subtitle");
        ConfigData.language_bed_invincibility_end_chat = ConfigUtils.getString(config, "language.bed-invincibility-end-chat");

        ConfigData.sound_respawn_enabled = ConfigUtils.getBoolean(config, "sound.respawn.enable");

    }
    public void loadGameConfig() {

        // Is Setup?
        if (gameConfig.getBoolean("setup")) {
            GameStats.set(0);
            Bukkit.getLogger().info("The current mode is detected as SETUP; only administrators can join and edit the game. ");
            Bukkit.getLogger().info("If you need to disable setup mode, please set the \"setup\" option in game.yml to \"false.\"");
            return;
        } GameStats.set(1);

        GameData_cfg.map_name = gameConfig.getString("map-name");
        GameData_cfg.map_author = gameConfig.getString("map-author");
        GameData_cfg.minPlayers = gameConfig.getInt("minplayers");
        GameData_cfg.maxPlayers = gameConfig.getInt("maxplayers");
        GameData_cfg.lobby_loc = ConfigUtils.getLocation(gameConfig, "lobby");
        GameData_cfg.spec_loc = ConfigUtils.getLocation(gameConfig, "spec");
        GameData_cfg.mapCenter_loc = ConfigUtils.getBlockLocation(gameConfig, "map-center");
        GameData_cfg.mapCenter_radius = gameConfig.getInt("map-radius");
        GameData_cfg.teamChest_radius = gameConfig.getInt("teamchest-radius");
        if (gameConfig.getStringList("generator.diamond") != null) {
            for (String s : ConfigUtils.getStringList(gameConfig, "generator.diamond")) {
                GameData_cfg.gameGenerator_diamond_locs.add(ConfigUtils.getBlockLocation(gameConfig, s));
            }
        } else {
            Bukkit.getLogger().warning("GameConfig game.yml, generator.diamond == null !!");
        }
        if (gameConfig.getStringList("generator.emerald") != null) {
            for (String s : ConfigUtils.getStringList(gameConfig, "generator.emerald")) {
                GameData_cfg.gameGenerator_emerald_locs.add(ConfigUtils.getBlockLocation(gameConfig, s));
            }
        } else {
            Bukkit.getLogger().warning("GameConfig game.yml, generator.emerald == null !!");
        }
        if (gameConfig.getStringList("teams") != null) {
            for (String s : ConfigUtils.getStringList(gameConfig, "teams")) {
                GameData_cfg.teams_list.add(s);
            }
        } else {
            Bukkit.getLogger().warning("GameConfig game.yml, teams == null !! NoneTeams!");
        }
        if (gameConfig.getStringList("teams").contains("RED")) {
            GameData_cfg.team_red_name = ConfigUtils.getString(gameConfig, "team-red.name");
            GameData_cfg.team_red_chatcolor = ConfigUtils.getString(gameConfig, "team-red.color");
            GameData_cfg.team_red_spawn = ConfigUtils.getLocation(gameConfig, "team-red.spawn");
            GameData_cfg.team_red_generator = ConfigUtils.getBlockLocation(gameConfig, "team-red.generator");
            GameData_cfg.team_red_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-red.bed-f");
            GameData_cfg.team_red_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-red.bed-b");
        }
        if (gameConfig.getStringList("teams").contains("BLUE")) {
            GameData_cfg.team_blue_name = ConfigUtils.getString(gameConfig, "team-blue.name");
            GameData_cfg.team_blue_chatcolor = ConfigUtils.getString(gameConfig, "team-blue.color");
            GameData_cfg.team_blue_spawn = ConfigUtils.getLocation(gameConfig, "team-blue.spawn");
            GameData_cfg.team_blue_generator = ConfigUtils.getBlockLocation(gameConfig, "team-blue.generator");
            GameData_cfg.team_blue_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-blue.bed-f");
            GameData_cfg.team_blue_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-blue.bed-b");
        }
        if (gameConfig.getStringList("teams").contains("GREEN")) {
            GameData_cfg.team_green_name = ConfigUtils.getString(gameConfig, "team-green.name");
            GameData_cfg.team_green_chatcolor = ConfigUtils.getString(gameConfig, "team-green.color");
            GameData_cfg.team_green_spawn = ConfigUtils.getLocation(gameConfig, "team-green.spawn");
            GameData_cfg.team_green_generator = ConfigUtils.getBlockLocation(gameConfig, "team-green.generator");
            GameData_cfg.team_green_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-green.bed-f");
            GameData_cfg.team_green_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-green.bed-b");
        }
        if (gameConfig.getStringList("teams").contains("YELLOW")) {
            GameData_cfg.team_yellow_name = ConfigUtils.getString(gameConfig, "team-yellow.name");
            GameData_cfg.team_yellow_chatcolor = ConfigUtils.getString(gameConfig, "team-yellow.color");
            GameData_cfg.team_yellow_spawn = ConfigUtils.getLocation(gameConfig, "team-yellow.spawn");
            GameData_cfg.team_yellow_generator = ConfigUtils.getBlockLocation(gameConfig, "team-yellow.generator");
            GameData_cfg.team_yellow_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-yellow.bed-f");
            GameData_cfg.team_yellow_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-yellow.bed-b");
        }
        if (gameConfig.getStringList("teams").contains("PINK")) {
            GameData_cfg.team_pink_name = ConfigUtils.getString(gameConfig, "team-pink.name");
            GameData_cfg.team_pink_chatcolor = ConfigUtils.getString(gameConfig, "team-pink.color");
            GameData_cfg.team_pink_spawn = ConfigUtils.getLocation(gameConfig, "team-pink.spawn");
            GameData_cfg.team_pink_generator = ConfigUtils.getBlockLocation(gameConfig, "team-pink.generator");
            GameData_cfg.team_pink_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-pink.bed-f");
            GameData_cfg.team_pink_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-pink.bed-b");
        }
        if (gameConfig.getStringList("teams").contains("AQUA")) {
            GameData_cfg.team_aqua_name = ConfigUtils.getString(gameConfig, "team-aqua.name");
            GameData_cfg.team_aqua_chatcolor = ConfigUtils.getString(gameConfig, "team-aqua.color");
            GameData_cfg.team_aqua_spawn = ConfigUtils.getLocation(gameConfig, "team-aqua.spawn");
            GameData_cfg.team_aqua_generator = ConfigUtils.getBlockLocation(gameConfig, "team-aqua.generator");
            GameData_cfg.team_aqua_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-aqua.bed-f");
            GameData_cfg.team_aqua_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-aqua.bed-b");
        }
        if (gameConfig.getStringList("teams").contains("GRAY")) {
            GameData_cfg.team_gray_name = ConfigUtils.getString(gameConfig, "team-gray.name");
            GameData_cfg.team_gray_chatcolor = ConfigUtils.getString(gameConfig, "team-gray.color");
            GameData_cfg.team_gray_spawn = ConfigUtils.getLocation(gameConfig, "team-gray.spawn");
            GameData_cfg.team_gray_generator = ConfigUtils.getBlockLocation(gameConfig, "team-gray.generator");
            GameData_cfg.team_gray_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-gray.bed-f");
            GameData_cfg.team_gray_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-gray.bed-b");
        }
        if (gameConfig.getStringList("teams").contains("WHITE")) {
            GameData_cfg.team_white_name = ConfigUtils.getString(gameConfig, "team-white.name");
            GameData_cfg.team_white_chatcolor = ConfigUtils.getString(gameConfig, "team-white.color");
            GameData_cfg.team_white_spawn = ConfigUtils.getLocation(gameConfig, "team-white.spawn");
            GameData_cfg.team_white_generator = ConfigUtils.getBlockLocation(gameConfig, "team-white.generator");
            GameData_cfg.team_white_bed_f = ConfigUtils.getBlockLocation(gameConfig, "team-white.bed-f");
            GameData_cfg.team_white_bed_b = ConfigUtils.getBlockLocation(gameConfig, "team-white.bed-b");
        }

    }


    public static boolean bungeecord_SendServer(Player player, String server) {
        try {
            if (server.length() == 0) {
                return false;
            } else {
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(byteArray);
                out.writeUTF("Connect");
                out.writeUTF(server);
                player.sendPluginMessage(CreeperStarBedwars.getPlugin(), "BungeeCord", byteArray.toByteArray());
                return true;
            }
        } catch (Exception var4) {
            Exception ex = var4;
            CreeperStarBedwars.getPlugin().getLogger().info(ex.getMessage());
            return false;
        }
    }

}
