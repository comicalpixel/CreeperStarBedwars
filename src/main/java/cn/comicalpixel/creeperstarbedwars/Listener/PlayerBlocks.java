package cn.comicalpixel.creeperstarbedwars.Listener;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerBlocks implements Listener {

    public static List<Block> player_blocks = new ArrayList<>();
    public static List<Block> safeMap_blocks = new ArrayList<>();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        Player p = e.getPlayer();

        if (e.isCancelled()) return;

        if (GameStats.get() == 1) {
            e.setCancelled(true);
        } else if (GameStats.get() == 2 || GameStats.get() == 3) {
            if (GamePlayers.players.contains(p)) {
                if (!safeMap_blocks.contains(e.getBlock())) {
                    player_blocks.add(e.getBlock());
                } else {
                    p.sendMessage(ConfigData.language_block_saferegion);
                }
            } else {
                e.setCancelled(true);
            }
        }

    }

}
