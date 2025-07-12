package cn.comicalpixel.creeperstarbedwars.Config;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigData {
    // BC设置
    public static String bungeecord_lobby;
    public static String bungeecord_motd_waiting;
    public static String bungeecord_motd_waiting_max;
    public static String bungeecord_motd_playing;
    public static String bungeecord_motd_end;
    // isLobbyMode
    public static boolean lobby_mode = false;
    // 重启指令
    public static String bungeecord_restart_command = "stop";
    // 血量显示
    public static boolean nametag_health_enabled;
    // 玩家NameTag
    public static String nametag_player_prefix;
    public static String nametag_player_suffix;
    public static String nametag_spec_prefix;
    public static String nametag_spec_suffix;
    // 快速重生
    public static boolean fast_respawn_enabled;
//    // 定时任务：指令
//    public static boolean timer_command_enabled;
//    public static List<String> timer_command_start = new ArrayList<>();
//    public static List<String> timer_command_end = new ArrayList<>();
//    public static HashMap<Integer, List<String>> timer_command_cmds = new HashMap<>();
//    // 事件触发指令
//    public static boolean event_command_enabled;
//    public static List<String> event_command_game_start = new ArrayList<>();
//    public static List<String> event_command_game_end = new ArrayList<>();
//    public static List<String> event_command_player_death = new ArrayList<>();
//    public static List<String> event_command_player_kill = new ArrayList<>();
//    public static List<String> event_command_player_win = new ArrayList<>();
//    public static List<String> event_command_player_lost = new ArrayList<>();
//    public static List<String> event_command_player_dbed = new ArrayList<>();
    // 白名单指令(仅限制玩家不限制op)
    public static List<String> whitelist_cmds = new ArrayList<>();
    // 队伍选择器
    public static boolean teamsel_enabled;
    public static String teamsel_gui_name;
    public static String teamsel_gui_items_status_select;
    public static String teamsel_gui_items_status_inteam;
    public static String teamsel_gui_items_status_full;
    public static String teamsel_gui_items_wool_name;
    public static List<String> teamsel_gui_items_wool_lore_head = new ArrayList<>();
    public static List<String> teamsel_gui_items_wool_lore_foot = new ArrayList<>();
    public static String teamsel_gui_items_wool_lore_player;
    public static String teamsel_gui_items_leave_name;
    public static List<String> teamsel_gui_items_leave_lore = new ArrayList<>();
    public static String teamsel_gui_items_close_name;
    public static List<String> teamsel_gui_items_close_lore = new ArrayList<>();
    // 资源模式选择器
    public static boolean bwimsel_enabled;
    public static int bwimsel_default;
    public static String bwimsel_gui_name;
    public static String bwimsel_gui_items_status_select;
    public static String bwimsel_gui_items_status_noselect;
    public static String bwimsel_gui_items_i_type;
    public static int bwimsel_gui_items_i_durability;
    public static String bwimsel_gui_items_i_name;
    public static List<String> bwimsel_gui_items_i_lore = new ArrayList<>();
    public static String bwimsel_gui_items_xp_type;
    public static int bwimsel_gui_items_xp_durability;
    public static String bwimsel_gui_items_xp_name;
    public static List<String> bwimsel_gui_items_xp_lore = new ArrayList<>();
    public static String bwimsel_set_i_message;
    public static String bwimsel_set_xp_message;
    // 资源倍率转换
    public static int bwim_conversion_iron = 1;
    public static int bwim_conversion_gold = 10;
    public static int bwim_conversion_emerald = 100;
    public static int bwim_conversion_diamond = 100;
    // 掉线重连 -> 暂时没写
    // 伤害全息
    public static boolean damageholo_damage_enabled;
    public static String damageholo_damage_title;
    public static String damageholo_damage_subtitle;
    public static boolean damageholo_projectile_enabled;
    public static String damageholo_projectile_chat;
    // 连击修复
    public static boolean combo_fix_enabled = true;
    // 梯子修复
    public static boolean ladder_fix_enabled = false;
    // 锁定饱食度
    public static boolean lock_foodlevel_enabled = false;
    // 游戏开始标题
    public static boolean gamestart_title_enabled;
    public static List<String> gamestart_title_actions = new ArrayList<>();
//    public static List<String> gamestart_title_title;
//    public static String gamestart_title_subtitle;
//    public static boolean gamestart_title_countdown_enabled;
//    public static String gamestart_title_countdown_5s_title;
//    public static String gamestart_title_countdown_5s_subtitle;
//    public static String gamestart_title_countdown_4s_title;
//    public static String gamestart_title_countdown_4s_subtitle;
//    public static String gamestart_title_countdown_3s_title;
//    public static String gamestart_title_countdown_3s_subtitle;
//    public static String gamestart_title_countdown_2s_title;
//    public static String gamestart_title_countdown_2s_subtitle;
//    public static String gamestart_title_countdown_1s_title;
//    public static String gamestart_title_countdown_1s_subtitle;
    // 经验条倒计时
    public static boolean xpbar_countdown;
    // 保护范围
    public static boolean saferegion_enabled;
    public static int saferegion_map_generators;
    public static int saferegion_team_generators;
    public static int saferegion_team_spawn;
    // 倒计时设置
    public static int countdown_settings_seconds = 120;
    public static List<Integer> countdown_settings_trigger_seconds = new ArrayList<>();
    //    public static List<String>  countdown_seconds_message_paths;
    public static HashMap<Integer, String> countdown_seconds_message_title = new HashMap<>();
    public static HashMap<Integer, String> countdown_seconds_message_subtitle = new HashMap<>();
    // 等待大厅背包物品(0-8对应1-8格)
    public static boolean LobbyItems_i0_enabled;
    public static String LobbyItems_i0_command;
    public static String LobbyItems_i0_permissions;
    public static ItemStack LobbyItems_i0_item;

    public static boolean LobbyItems_i1_enabled;
    public static String LobbyItems_i1_command;
    public static String LobbyItems_i1_permissions;
    public static ItemStack LobbyItems_i1_item;

    public static boolean LobbyItems_i2_enabled;
    public static String LobbyItems_i2_command;
    public static String LobbyItems_i2_permissions;
    public static ItemStack LobbyItems_i2_item;

    public static boolean LobbyItems_i3_enabled;
    public static String LobbyItems_i3_command;
    public static String LobbyItems_i3_permissions;
    public static ItemStack LobbyItems_i3_item;

    public static boolean LobbyItems_i4_enabled;
    public static String LobbyItems_i4_command;
    public static String LobbyItems_i4_permissions;
    public static ItemStack LobbyItems_i4_item;

    public static boolean LobbyItems_i5_enabled;
    public static String LobbyItems_i5_command;
    public static String LobbyItems_i5_permissions;
    public static ItemStack LobbyItems_i5_item;

    public static boolean LobbyItems_i6_enabled;
    public static String LobbyItems_i6_command;
    public static String LobbyItems_i6_permissions;
    public static ItemStack LobbyItems_i6_item;

    public static boolean LobbyItems_i7_enabled;
    public static String LobbyItems_i7_command;
    public static String LobbyItems_i7_permissions;
    public static ItemStack LobbyItems_i7_item;

    public static boolean LobbyItems_i8_enabled;
    public static String LobbyItems_i8_command;
    public static String LobbyItems_i8_permissions;
    public static ItemStack LobbyItems_i8_item;


    // 资源点生成时间设置
    // 队伍部分
    public static double generator_team_l0_iron = -1;
    public static double generator_team_l0_gold = -1;
    public static double generator_team_l0_emerald = -1;
    // 游戏资源点
    public static int generator_game_diamond = 30;
    public static int generator_game_emerald = 70;
//    public static double generator_diamond_l1 = -1;
//    public static double generator_diamond_l2 = -1;
//    public static double generator_diamond_l3 = -1;
//    public static double generator_emerald_l1 = -1;
//    public static double generator_emerald_l2 = -1;
//    public static double generator_emerald_l3 = -1;
    public static int resourcelimit_iron = 64;
    public static int resourcelimit_gold = 16;
    public static int resourcelimit_diamond = 8;
    public static int resourcelimit_emerald = 4;


    // 游戏道具物品设置
    // 回春床
    public static boolean ItemsInGame_respawn_bed_enabled;
    public static int ItemInGame_respawn_bed_started;
    public static String ItemInGame_respawn_bed_l_expires_chat;
    public static String ItemInGame_respawn_bed_l_spawnloc_toofar;
    public static String ItemInGame_respawn_bed_l_used;
    public static String ItemInGame_respawn_bed_l_bedexist;
    // 火焰弹
    public static boolean ItemsInGame_fireball_enabled;
    public static int ItemsInGame_fireball_cooldown;
    public static int ItemsInGame_fireball_radius;
    public static int ItemsInGame_fireball_damage;
    public static double ItemsInGame_fireball_vlocity_multiply;
    public static double ItemsInGame_fireball_vlocity_y;
    public static String ItemsInGame_fireball_cooldown_chat;
    // TNT
    public static boolean ItemsInGame_tnt_enabled;
    public static int ItemsInGame_tnt_cooldown;
    public static int ItemsInGame_tnt_fticks;
    public static int ItemsInGame_tnt_radius;
    public static int ItemsInGame_tnt_damage;
    public static double ItemsInGame_tnt_vlocity_multiply;
    public static double ItemsInGame_tnt_vlocity_y;
    public static String  ItemsInGame_tnt_cooldown_chat;
    // 追踪指南针
    public static boolean ItemsInGame_compass_enabled;
    // 梦幻守卫
    public static boolean ItemsInGame_dreamguard_enabled;
    public static int ItemsInGame_dreamguard_cooldown;
    public static String  ItemsInGame_dreamguard_cooldown_chat;
    public static int ItemsInGame_dreamguard_survival_time;
    public static int ItemsInGame_dreamguard_maxHealth;
    // 蠹虫
    public static boolean ItemsInGame_silverfish_enabled;
    public static int ItemsInGame_silverfish_cooldown;
    public static String  ItemsInGame_silverfish_cooldown_chat;
    public static int ItemsInGame_silverfish_survival_time;
//    public static int ItemsInGame_silverfish_maxHealth; 弃用
    // 搭桥蛋
    public static boolean ItemsInGame_bridge_egg_enabled;
    public static int ItemsInGame_bridge_egg_cooldown;
    public static String  ItemsInGame_bridge_egg_cooldown_chat;
    // 紧凑型防御塔
    public static boolean ItemsInGame_safetower_enabled;
    public static int ItemsInGame_safetower_cooldown;
    public static String  ItemsInGame_safetower_cooldown_chat;
    // 回城卷轴
    public static boolean ItemsInGame_warp_powder_enabled;
    public static int ItemsInGame_warp_powder_tpcountdown;
    public static String  ItemsInGame_warp_powder_tpcountdown_chat;
    public static String  ItemsInGame_warp_powder_move_cancel_chat;
    public static String ItemsInGame_warp_powder_cancel_chat;
    // 防护墙
    public static boolean ItemsInGame_safewall_enabled;
    public static int ItemsInGame_safewall_cooldown;
    public static String  ItemsInGame_safewall_cooldown_chat;
    // 救援平台
    public static boolean ItemsInGame_rescue_platform_enabled;
    public static boolean ItemsInGame_rescue_platform_can_break;
    public static int ItemsInGame_rescue_platform_cooldown;
    public static String  ItemsInGame_rescue_platform_cooldown_chat;
    public static int ItemsInGame_rescue_platform_disappear;
    public static String  ItemsInGame_rescue_platform_insufficient_space_chat;
    // 屁眼烟花
    public static boolean ItemsInGame_firework_enabled;
    public static int ItemsInGame_firework_cooldown;
    public static String  ItemsInGame_firework_cooldown_chat;
    public static String ItemsInGame_firework_subtitle;

    // 队友床危险警告
    // 触发方式: 抬头90°且潜行一下
    public static boolean beddanger_enabled;
    public static String beddanger_subtitle;

    // 床无敌时间(语言不在这里)
    public static boolean bed_invincibility_enabled;
    public static int bed_invincibility_started_timer;

    // 语言设置(部分语言不在这里awa)
    // language.
    public static String language_date = "MM/dd/yyyy";

    public static String language_joingame_chat;
    public static String language_leavegame_chat;

    public static String language_rejoin_chat;

    //    public static String language_damageholo; 写在了damageholo部分那里

    public static List<String> language_game_start_chat = new ArrayList<>();
    public static List<String> language_game_end_chat = new ArrayList<>();

    public static List<String> language_generatorholo_diamond_list = new ArrayList<>();
    public static List<String> language_generatorholo_emerald_list = new ArrayList<>();
    public static List<String> language_generatorholo_team_list = new ArrayList<>();
    public static List<String> language_generatorholo_ywz_all = new ArrayList<>();

    public static String language_bed_destroy_ismyteam_chat;
    public static String language_bed_destroy_chat;
    public static String language_bed_destroy_team_title;
    public static String language_bed_destroy_team_subtitle;
    public static String language_bed_destroy_me_title;
    public static String language_bed_destroy_me_subtitle;
    public static String language_bed_destroy_all_title;
    public static String language_bed_destroy_all_subtitle;

    public static String language_bed_respawn_chat;
    public static String language_bed_respawn_team_title;
    public static String language_bed_respawn_team_subtitle;
    public static String language_bed_respawn_all_title;
    public static String language_bed_respawn_all_subtitle;

    public static String language_team_annihilation_chat;

    public static String language_bed_sleep_chat = "§c睡你麻痹起来嗨! ";

    public static String language_game_countdown_InsufficientPlayers_title;
    public static String language_game_countdown_InsufficientPlayers_subtitle;
    public static String language_game_countdown_InsufficientPlayers_chat;
    public static String language_game_countdown_other_title;
    public static String language_game_countdown_other_subtitle;
    public static String language_game_countdown_chat;

    public static String language_game_lobby_actionbar;

    public static String language_teamsel_join_full;
    public static String language_teamsel_join_done;
    public static String language_teamsel_leave;
    public static String language_teamsel_join_unbalanced;

    public static String language_bwim_i_name = "普通";
    public static String language_bwim_xp_name = "经验";

    public static String language_sidebarboard_team_ismyteam = "";
    public static String language_sidebarboard_team_hasbed = "";
    public static String language_sidebarboard_team_notbed = "";
    public static String language_sidebarboard_team_message = "";
    public static List<String> language_sidebarboard_list_lobby_waiting = new ArrayList<>();
    public static List<String> language_sidebarboard_list_lobby_countdown = new ArrayList<>();
    public static List<String> language_sidebarboard_list_playing_player = new ArrayList<>();
    public static List<String> language_sidebarboard_list_playing_spec = new ArrayList<>();

    public static String language_game_end_winner_title;
    public static String language_game_end_winner_subtitle;
    public static String language_game_end_loser_title;
    public static String language_game_end_loser_subtitle;
    public static String language_game_end_stalemate_title;
    public static String language_game_end_stalemate_subtitle;

    public static String language_respawn_respawning_title;
    public static String language_respawn_respawning_subtitle;
    public static String language_respawn_respawning_chat;
    public static String language_respawn_respawn_title;
    public static String language_respawn_respawn_subtitle;
    public static String language_respawn_eliminated_title;
    public static String language_respawn_eliminated_subtitle;
    public static String language_respawn_eliminated_chat;

    public static String language_playerdie_byplayer;
    public static String language_playerdie_void;
    public static String language_playerdie_out;
    public static String language_playerdie_fall;
    public static String language_playerdie_boom;
    public static String language_playerdie_shoot;
    public static String language_playerdie_none;
    public static String language_playerdie_killme;
    public static String language_playerdie_killer_;
    public static String language_playerdie_final_;

    public static List<String> language_game_start_tips_actionbar = new ArrayList<>();

    public static String language_team_tracking_actionbar;

    public static String language_compass_tracking_actionbar;

    public static String language_teamchest_notismyteam = "§c该队伍未被淘汰, 你不能打开这个队伍的箱子! ";

    public static String language_shopgui_item_NPCname;
    public static String language_shopgui_update_NPCname;

    public static String language_entity_silverfish_nametag;
    public static String language_entity_dreamguard_nametag;

    public static String language_command_notin_whitelist;
    public static String language_command_bwstart_done;
    public static String language_command_bwstart_nopermissions;
    public static String language_command_nopermissions;

    public static String language_shop_buy_yes;
    public static String language_shop_buy_no;
    public static String language_shop_buy_ed;

    public static String language_update_buy_me;
    public static String language_update_buy_team;
    public static String language_update_buy_no;
    public static String language_update_buy_max;

    public static String language_block_saferegion = "§c你不能在这里放置方块! ";
    public static String language_block_map_break = "§c你只能破坏玩家放置的方块! ";

    public static String language_bed_invincibility_start_chat;
    public static String language_bed_invincibility_start_title;
    public static String language_bed_invincibility_start_subtitle;
    public static String language_bed_invincibility_end_chat;
    public static String language_bed_invincibility_end_title;
    public static String language_bed_invincibility_end_subtitle;
    public static String language_bed_invincibility_breakC;



    // 声音设置
    public static boolean sound_respawn_enabled = false;
    // 音乐都用utils直接去读取了并且播放了
    // 不打算在这里写了 (

}
