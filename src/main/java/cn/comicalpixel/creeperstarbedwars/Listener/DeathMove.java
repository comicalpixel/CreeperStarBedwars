package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GameTools;
import cn.comicalpixel.creeperstarbedwars.Arena.SPEC.SpecManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Items.ToolsItem.ToolItemsManager;
import cn.comicalpixel.creeperstarbedwars.PlayerInGameData;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import cn.comicalpixel.creeperstarbedwars.api.Events.BedwarsGameStartEvent;
import cn.comicalpixel.creeperstarbedwars.api.Events.PlayerEliminatedEvent;
import cn.comicalpixel.creeperstarbedwars.api.Events.PlayerRespawnEvent;
import cn.comicalpixel.creeperstarbedwars.data.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class DeathMove implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();

        if (GameStats.get() != 2 && GameStats.get() != 3) return;

        e.setDeathMessage(null);

        e.setKeepLevel(true);
        e.setKeepInventory(true);


        // killer sound
        if (PlayerDamage.Playerkillers.get(p) != null && p != PlayerDamage.Playerkillers.get(p)) {
            Player killer = PlayerDamage.Playerkillers.get(p);
            List<String> sounds = ConfigUtils.getStringList(CreeperStarBedwars.getInstance().getConfig(), "sound.killer-sound");
            String[] sound = sounds.get(new Random().nextInt(sounds.size())).split(",");
            if (sound.length == 3) {
                killer.playSound(killer.getLocation(), Sound.valueOf(sound[0]), Integer.parseInt(sound[1]), (float) Double.parseDouble(sound[2]));
            }
        }


        // kill message
        // {player} {killer} {final}
        String killer_message = "";
        if (PlayerDamage.Playerkillers.get(p) != null && PlayerDamage.Playerkillers.get(p) != p) {
            killer_message = ConfigData.language_playerdie_killer_;
            killer_message = killer_message.replace("{player}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(PlayerDamage.Playerkillers.get(p))) + PlayerDamage.Playerkillers.get(p).getName());

            // Player kill data
            PlayerInGameData.Companion.getKills().put(PlayerDamage.getKiller(p), PlayerInGameData.Companion.getKills().getOrDefault(PlayerDamage.getKiller(p), 0) + 1);
        }
        String finalKill_message = "";
        if (TeamManager.getbed(TeamManager.player_teams.get(p))) {
            finalKill_message = ConfigData.language_playerdie_final_;

            // Player fkill data
            PlayerInGameData.Companion.getFkills().put(PlayerDamage.getKiller(p), PlayerInGameData.Companion.getFkills().getOrDefault(PlayerDamage.getKiller(p), 0) + 1);
        }

        if (p.getLastDamageCause().getCause() != null && p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID) {
            String s = ConfigData.language_playerdie_void;
            s = s.replace(TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + "{player}", p.getName()).replace("{killer}", killer_message).replace("{final}", finalKill_message);
            s = s.replace("{player}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + p.getName());
            for (Player allp : Bukkit.getOnlinePlayers()) {
                allp.sendMessage(s);
            }
        } else if (p.getLastDamageCause().getCause() != null && p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            String s = ConfigData.language_playerdie_shoot;
            s = s.replace(TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + "{player}", p.getName()).replace("{killer}", killer_message).replace("{final}", finalKill_message);
            s = s.replace("{player}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + p.getName());
            for (Player allp : Bukkit.getOnlinePlayers()) {
                allp.sendMessage(s);
            }
        } else if (p.getLastDamageCause().getCause() != null && (p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {

            String s = ConfigData.language_playerdie_boom;
            s = s.replace(TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + "{player}", p.getName()).replace("{killer}", killer_message).replace("{final}", finalKill_message);
            s = s.replace("{player}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + p.getName());


            for (Player allp : Bukkit.getOnlinePlayers()) {
                allp.sendMessage(s);
            }
        } else if (p.getLastDamageCause().getCause() != null && (p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL)) { // p.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK)
            String s = ConfigData.language_playerdie_fall;
            s = s.replace(TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + "{player}", p.getName()).replace("{killer}", killer_message).replace("{final}", finalKill_message);
            s = s.replace("{player}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + p.getName());
            for (Player allp : Bukkit.getOnlinePlayers()) {
                allp.sendMessage(s);
            }
        } else if (PlayerDamage.Playerkillers.get(p) != null && PlayerDamage.Playerkillers.get(p) instanceof Player) {
            String s = ConfigData.language_playerdie_byplayer;
            s = s.replace(TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + "{player}", p.getName()).replace("{killer}", killer_message).replace("{final}", finalKill_message);
            s = s.replace("{player}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + p.getName());
            for (Player allp : Bukkit.getOnlinePlayers()) {
                allp.sendMessage(s);
            }
        } else {
            String s = ConfigData.language_playerdie_none;
            s = s.replace(TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + "{player}", p.getName()).replace("{killer}", killer_message).replace("{final}", finalKill_message);
            s = s.replace("{player}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p)) + p.getName());
            for (Player allp : Bukkit.getOnlinePlayers()) {
                allp.sendMessage(s);
            }
        }


        if (PlayerDamage.Playerkillers.get(p) != null) {
            if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
                if (PlayerDamage.getKiller(p) == null) return;
                GamePlayer gamePlayer = GamePlayer.Companion.get(Objects.requireNonNull(PlayerDamage.getKiller(p)).getUniqueId());
                if (gamePlayer == null) return;
                gamePlayer.setKills(gamePlayer.getKills() + 1);
            } else {
                CreeperStarBedwars.getPlugin().getPlayerDataConfig().set(Objects.requireNonNull(PlayerDamage.getKiller(p)).getName() + ".kills", CreeperStarBedwars.getPlugin().getPlayerDataConfig().getInt(PlayerDamage.getKiller(p).getName() + ".kills") + 1);
            }

            if (!TeamManager.getbed(TeamManager.player_teams.get(p))) {
                if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
                    if (PlayerDamage.getKiller(p) == null) return;
                    GamePlayer gamePlayer = GamePlayer.Companion.get(Objects.requireNonNull(PlayerDamage.getKiller(p)).getUniqueId());
                    if (gamePlayer == null) return;
                    gamePlayer.setFinal_kills(gamePlayer.getFinal_kills() + 1);
                } else {
                    CreeperStarBedwars.getPlugin().getPlayerDataConfig().set(Objects.requireNonNull(PlayerDamage.getKiller(p)).getName() + ".fkills", CreeperStarBedwars.getPlugin().getPlayerDataConfig().getInt(PlayerDamage.getKiller(p).getName() + ".fkills") + 1);
                }
            }
        }
        if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
            GamePlayer gamePlayer = GamePlayer.Companion.get(p.getUniqueId());
            if (gamePlayer == null) return;
            gamePlayer.setDeaths(gamePlayer.getDeaths() + 1);
        } else {
            CreeperStarBedwars.getPlugin().getPlayerDataConfig().set(p.getName() + ".deaths", CreeperStarBedwars.getPlugin().getPlayerDataConfig().getInt(p.getName() + ".deaths") + 1);
            CreeperStarBedwars.getPlugin().getPlayerDataConfig().save();
        }


        // 是否开启快速重生
        if (ConfigData.fast_respawn_enabled) {
            p.setHealth(p.getMaxHealth());
            respawn(p);
        } else {
            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
                respawn(p);
            },10);
        }

    }

    public static void respawn(Player p) {
        p.spigot().respawn();
        p.spigot().respawn();
        PlayerUtils.reset(p);
        PlayerUtils.clear_effects(p);

        p.setLevel(0);
        p.setExp(0);
        GameTools.InitializationInventory(p);

        ToolItemsManager.giveItems(p);

        p.teleport(GameData_cfg.spec_loc);

        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            p.teleport(GameData_cfg.spec_loc);
            p.damage(0.1);
            sound.add(p.getUniqueId());
            if (ConfigData.sound_respawn_enabled) {
                if (sound.contains(p.getUniqueId())) {
                    respawn_sound_plays(p);
                    sound.remove(p.getUniqueId());
                    i = 5;
                }
            }
        },1);

        if (!TeamManager.getbed(TeamManager.player_teams.get(p))) {
            p.setGameMode(GameMode.SPECTATOR);
            // API
//            Bukkit.getPluginManager().callEvent(new cn.comicalpixel.creeperstarbedwars.api.Events.PlayerDeathEvent(p));
            new BukkitRunnable() {
                int resapwn = 5;
                @Override
                public void run() {
                    if (resapwn > 0) {
                        NMSTitleUntils.Title.send(p, ConfigData.language_respawn_respawning_title.replace("{time}", resapwn+"").replace("{timer}", resapwn+""), ConfigData.language_respawn_respawning_subtitle.replace("{time}", resapwn+"").replace("{timer}", resapwn+""), 2, 40, 7);
                        p.sendMessage(ConfigData.language_respawn_respawning_chat.replace("{time}", resapwn+"").replace("{timer}", resapwn+""));
                    }
                    if (resapwn <= 0) {
                        NMSTitleUntils.Title.send(p, ConfigData.language_respawn_respawn_title.replace("{time}", (resapwn+1)+"").replace("{timer}", (resapwn+1)+""), ConfigData.language_respawn_respawn_subtitle.replace("{time}", (resapwn+1)+"").replace("{timer}", (resapwn+1)+""), 7, 30, 10);
                        PlayerUtils.reset(p);
                        p.setFlying(false);
                        p.setAllowFlight(false);
                        p.setGameMode(GameMode.SURVIVAL);
                        TeamSpawn.send(p, TeamManager.player_teams.get(p));
                        p.setLevel(0);
                        p.setExp(0);
                        PlayerDamage.noDamageMode(p);
                        cancel();
                        // API
                        Bukkit.getPluginManager().callEvent(new PlayerRespawnEvent(p));
                    }
                    resapwn--;
                }
            }.runTaskTimer(CreeperStarBedwars.getPlugin(), 0,20);
        } else {
            SpecManager.setSpec(p);
            NMSTitleUntils.Title.send(p, ConfigData.language_respawn_eliminated_title, ConfigData.language_respawn_eliminated_subtitle, 2, 40, 10);
            p.sendMessage(ConfigData.language_respawn_eliminated_chat);
            // PlayerData
            if (CreeperStarBedwars.getPlugin().getConfig().getString("data.type").equalsIgnoreCase("mongodb")) {
                GamePlayer gamePlayer = GamePlayer.Companion.get(p.getUniqueId());
                if (gamePlayer == null) return;
                gamePlayer.setLoser(gamePlayer.getLoser() + 1);
            } else {
                CreeperStarBedwars.getPlugin().getPlayerDataConfig().set(p.getName() + ".lost",  CreeperStarBedwars.getPlugin().getPlayerDataConfig().getInt(p.getName() + ".lost") + 1);
                CreeperStarBedwars.getPlugin().getPlayerDataConfig().save();
            }
            // API
            Bukkit.getPluginManager().callEvent(new PlayerEliminatedEvent(p));
        }

    }



    private static final List<UUID> sound = new ArrayList<>();
    static int i = 5;

    public static void respawn_sound_plays(Player player) {
        for (float f = 1.7F; f < 2; f += 0.1F) {
            float finalf = f;
            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(), () -> {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, finalf);
            }, i * 2L);
            i++;
        }
        int finali = i;
        for (; i < finali + 7; i++) {
            Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(), () -> {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 2F);
            }, i * 2L);
        }
    }

}
