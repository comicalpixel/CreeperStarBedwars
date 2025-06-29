package cn.comicalpixel.creeperstarbedwars.NameTag;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamManager;
import cn.comicalpixel.creeperstarbedwars.Arena.Teams.TeamSpawn;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerDamage;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import com.nametagedit.plugin.NametagEdit;
import com.nametagedit.plugin.api.NametagAPI;
import com.nametagedit.plugin.api.data.Nametag;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class NameTagManager {

    public static void NameTagManager_Main() {
        new BukkitRunnable() {
            @Override
            public void run() {

                if (GameStats.get() == 1) {
                    if (CreeperStarBedwars.getPlugin().getConfig().getBoolean("nametag.lobby-nick")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            NametagEdit.getApi().setPrefix(p, "§k");
                        }
                    }
                }
                if (GameStats.get() == 2 || GameStats.get() == 3) {
                    for (Player p : GamePlayers.players) {

                        if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {

                            String prefix = MessageVariableUtils.toPAPI(ConfigData.nametag_player_prefix, p);
                            String suffix = MessageVariableUtils.toPAPI(ConfigData.nametag_player_suffix, p);

                            prefix = prefix.replace("{team_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p))).replace("{team_name}", TeamManager.getTeamName(TeamManager.player_teams.get(p)));
                            suffix = suffix.replace("{team_color}", TeamManager.getTeamChatColor(TeamManager.player_teams.get(p))).replace("{team_name}", TeamManager.getTeamName(TeamManager.player_teams.get(p)));

                            NametagEdit.getApi().setPrefix(p, prefix);
                            NametagEdit.getApi().setSuffix(p, suffix);

                        }

                    }
                    for (Player p_spec : GamePlayers.specs) {
                        NametagEdit.getApi().setPrefix(p_spec, MessageVariableUtils.toPAPI(ConfigData.nametag_spec_prefix, p_spec));
                        NametagEdit.getApi().setSuffix(p_spec, MessageVariableUtils.toPAPI(ConfigData.nametag_spec_suffix, p_spec));
                    }
                }

                for (Player p : Bukkit.getOnlinePlayers()) {
                    NametagEdit.getApi().applyTagToPlayer(p, true);
                }

            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 10,10);
    }

}
