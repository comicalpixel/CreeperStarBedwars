package cn.comicalpixel.creeperstarbedwars.Task;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ActionBarUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GameTeamEliminated_Task {

    public boolean enableSendMessage = false;

    public boolean red = true;
    public boolean blue = true;
    public boolean yellow = true;
    public boolean green = true;
    public boolean pink = true;
    public boolean aqua = true;
    public boolean gray = true;
    public boolean white = true;

    public GameTeamEliminated_Task() {

        if (GameStats.get() != 2) {
            return;
        }

        for (String team : TeamManager.teams) {
            switch (team) {
                case "RED":
                    red = false;
                    break;
                case "BLUE":
                    blue = false;
                    break;
                case "YELLOW":
                    yellow = false;
                    break;
                case "GREEN":
                    green = false;
                    break;
                case "PINK":
                    pink = false;
                    break;
                case "AQUA":
                    aqua = false;
                    break;
                case "GRAY":
                    gray = false;
                    break;
                case "WHITE":
                    white = false;
                    break;
            }

        }

        Bukkit.getScheduler().runTaskLater(CreeperStarBedwars.getPlugin(),()->{
            enableSendMessage = true;
        },20);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(CreeperStarBedwars.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (GameStats.get() == 2) {

                    String s = ConfigData.language_team_annihilation_chat;

                    int ns = 0;

                    if (TeamManager.teams.contains("RED") && TeamManager.getTeamPlayerSize("RED") <= 0 && !red) {
                        red = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("RED")).replace("{TeamName}", TeamManager.getTeamName("RED"));
                        ns++;
                    }
                    if (TeamManager.teams.contains("BLUE") && TeamManager.getTeamPlayerSize("BLUE") <= 0 && !blue) {
                        blue = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("BLUE")).replace("{TeamName}", TeamManager.getTeamName("BLUE"));
                        ns++;
                    }
                    if (TeamManager.teams.contains("YELLOW") && TeamManager.getTeamPlayerSize("YELLOW") <= 0 && !yellow) {
                        yellow = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("YELLOW")).replace("{TeamName}", TeamManager.getTeamName("YELLOW"));
                        ns++;
                    }
                    if (TeamManager.teams.contains("GREEN") && TeamManager.getTeamPlayerSize("GREEN") <= 0 && !green) {
                        green = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("GREEN")).replace("{TeamName}", TeamManager.getTeamName("GREEN"));
                        ns++;
                    }
                    if (TeamManager.teams.contains("PINK") && TeamManager.getTeamPlayerSize("PINK") <= 0 && !pink) {
                        pink = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("PINK")).replace("{TeamName}", TeamManager.getTeamName("PINK"));
                        ns++;
                    }
                    if (TeamManager.teams.contains("AQUA") && TeamManager.getTeamPlayerSize("AQUA") <= 0 && !aqua) {
                        aqua = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("AQUA")).replace("{TeamName}", TeamManager.getTeamName("AQUA"));
                        ns++;
                    }
                    if (TeamManager.teams.contains("GRAY") && TeamManager.getTeamPlayerSize("GRAY") <= 0 && !gray) {
                        gray = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("GRAY")).replace("{TeamName}", TeamManager.getTeamName("GRAY"));
                        ns++;
                    }
                    if (TeamManager.teams.contains("WHITE") && TeamManager.getTeamPlayerSize("WHITE") <= 0 && !white) {
                        white = true;
                        s = s.replace("{TeamChatColor}", TeamManager.getTeamChatColor("WHITE")).replace("{TeamName}", TeamManager.getTeamName("WHITE"));
                        ns++;
                    }


                    if (enableSendMessage && ns != 0) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(s);
                            ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().getConfig(), "sound.team-annihilation");
                        }
                    }

                }
            }
        }, 0, 1L); // 参数分别为：延迟执行的时间（tick），重复执行的时间间隔（tick），20 ticks = 1秒

    }

}
