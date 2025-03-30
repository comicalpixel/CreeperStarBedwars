package cn.comicalpixel.creeperstarbedwars;

import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Listener.JoinPluginCheck;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CreeperStarBedwars extends JavaPlugin {

    public static CreeperStarBedwars Instance;

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
        if (Bukkit.getVersion().contains("folia")) {
            Bukkit.getLogger().warning("CreeperStarBedwars does not support folia, please use version 1.8.8 of CraftBukkit or Spigot or Paper server kernel!");
            this.getPluginLoader().disablePlugin(this);
        }
//        Bukkit.getLogger().info(" ");
//        Bukkit.getLogger().info("CreeperStarBedwars Plugin.");
//        Bukkit.getLogger().info("Author: Xiaol789zxc");
//        Bukkit.getLogger().info("Version: " + this.getDescription().getVersion());
//        Bukkit.getLogger().info("");
//        Bukkit.getLogger().info("Minecraft Version: " + Bukkit.getVersion());
//        Bukkit.getLogger().info("");


        // 检查必要的前置是否齐全
        // 不齐全会阻止加入且提示缺失哪些必要前置(仅op提示插件详情)
        JoinPluginCheck.check();
        getServer().getPluginManager().registerEvents(new JoinPluginCheck(), this);

        // 加载配置文件
        saveDefaultConfig();
        CreeperStarBedwars.Instance.load_config();

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
        ConfigData.teamsel_gui_items_close_name = ConfigUtils.getString(config, "select_team.leave-item.name");
        ConfigData.teamsel_gui_items_close_lore = ConfigUtils.getStringList(config, "select_team.leave-item.lore");

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

        ConfigData.damageholo_damage_enabled = ConfigUtils.getBoolean(config, "damage-holo.damage.enable");
        ConfigData.damageholo_bow_enabled = ConfigUtils.getBoolean(config, "damage-holo.bow.enable");

        ConfigData.combo_fix_enabled = ConfigUtils.getBoolean(config, "combo-fix.enable");

        ConfigData.lock_foodlevel_enabled = ConfigUtils.getBoolean(config, "lock-foodlevel.enable");

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
        ConfigData.ItemsInGame_fireball_vlocity_multiply = ConfigUtils.getInt(config, "items.fireball.vlocity-multiply");
        ConfigData.ItemsInGame_fireball_cooldown_chat = ConfigUtils.getString(config, "items.fireball.cooldown-chat");

        ConfigData.ItemsInGame_tnt_enabled = ConfigUtils.getBoolean(config, "items.tnt.enable");
        ConfigData.ItemsInGame_tnt_cooldown = ConfigUtils.getInt(config, "items.tnt.cooldown");
        ConfigData.ItemsInGame_tnt_radius = ConfigUtils.getInt(config, "items.tnt.radius");
        ConfigData.ItemsInGame_tnt_damage = ConfigUtils.getInt(config, "items.tnt.damage");
        ConfigData.ItemsInGame_tnt_vlocity_multiply = ConfigUtils.getInt(config, "items.tnt.vlocity-multiply");
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
        // ConfigData


    }

}
