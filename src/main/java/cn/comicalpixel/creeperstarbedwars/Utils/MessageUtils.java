package cn.comicalpixel.creeperstarbedwars.Utils;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;

import static cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager.player_teams;

public class MessageUtils {

    // 给全体玩家发送广播信息
    public static void broadcastMessage(String message) {
        GamePlayers.players.forEach(player -> player.sendMessage(message));
    }

    // 给指定队伍发送广播信息
    public static void broadcastTeamMessage(String gameTeam,String message) {
        GamePlayers.players.forEach(player -> {
            if (player_teams.get(player).equalsIgnoreCase(gameTeam)) {
                player.sendMessage(message);
            }
        });
    }

    // 给全体玩家发送ActionBar信息
    public static void broadcastActionBar(String message) {
        GamePlayers.players.forEach(player -> ActionBarUtils.sendActionBar(player, message));
    }

    // 给指定队伍发送ActionBar信息
    public static void broadcastActionBar(String gameTeam,String message) {
        GamePlayers.players.forEach(player -> {
           if (player_teams.get(player).equalsIgnoreCase(gameTeam)) {
               ActionBarUtils.sendActionBar(player, message);
           }
        });
    }

    // 给全体玩家发送title信息
    public static void broadcastTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        GamePlayers.players.forEach(player -> NMSTitleUntils.Title.send(player, title, subtitle, fadeIn, stay, fadeOut));
    }

    // 给指定队伍发送title信息
    public static void broadcastTitle(String gameTeam, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        GamePlayers.players.forEach(player -> {
            if (player_teams.get(player).equalsIgnoreCase(gameTeam)) {
                NMSTitleUntils.Title.send(player, title, subtitle, fadeIn, stay, fadeOut);
            }
        });
    }

}
