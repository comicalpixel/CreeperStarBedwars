package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Safewall_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void onPlayerPlaceBlockEvent(BlockPlaceEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.isCancelled()) return;

        if (e.isCancelled()) return;

        if (e.getBlockPlaced() == null) return;

        if (e.getBlockPlaced().getType() != Material.BRICK) return;

        Player p = e.getPlayer();

        // 检查冷却时间
        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_safewall_cooldown * 1000)) {
            int cooldms = (ConfigData.ItemsInGame_safewall_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_safewall_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间


        float yaw = p.getLocation().getYaw();
        BlockFace face = getBlockFace(yaw);
        Location loc = e.getBlockPlaced().getLocation();

        Wall(loc, face);

    }


    private BlockFace getBlockFace(float yaw) {
        if (yaw < 0) yaw += 360;
        if (yaw >= 315 || yaw < 45) return BlockFace.SOUTH;
        if (yaw >= 45 && yaw < 135) return BlockFace.WEST;
        if (yaw >= 135 && yaw < 225) return BlockFace.NORTH;
        if (yaw >= 225 && yaw < 315) return BlockFace.EAST;
        return BlockFace.SOUTH;
    }

    private void Wall(Location loc, BlockFace f) {
        Material wallMaterial = Material.SANDSTONE;
        loc.getBlock().setType(wallMaterial);
        switch (f) {
            case NORTH:
            case SOUTH:
                for (int x = -2; x <= 2; x++) {
                    for (int y = 0; y < 3; y++) {
                        Block block = loc.clone().add(x, y, 0).getBlock();
                        if (block.getType() == Material.AIR) {
                            block.setType(wallMaterial);
                            PlayerBlocks.player_blocks.add(block);
                        }
                    }
                }
                break;
            case EAST:
            case WEST:
                for (int z = -2; z <= 2; z++) {
                    for (int y = 0; y < 3; y++) {
                        Block block = loc.clone().add(0, y, z).getBlock();
                        if (block.getType() == Material.AIR) {
                            block.setType(wallMaterial);
                            PlayerBlocks.player_blocks.add(block);
                        }
                    }
                }
                break;
            default:
                for (int x = -2; x <= 2; x++) {
                    for (int y = 0; y < 3; y++) {
                        Block block = loc.clone().add(x, y, x).getBlock();
                        if (block.getType() == Material.AIR) {
                            block.setType(wallMaterial);
                            PlayerBlocks.player_blocks.add(block);
                        }
                    }
                }
                break;
        }
    }

}
