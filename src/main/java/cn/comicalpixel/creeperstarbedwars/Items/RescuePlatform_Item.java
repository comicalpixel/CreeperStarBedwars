package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Listener.PlayerBlocks;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class RescuePlatform_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) {
            return;
        }

        if (e.getItem() == null) return;

        if (e.getItem().getType() != Material.BLAZE_ROD) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player p = e.getPlayer();

        // 检查冷却时间
        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigData.ItemsInGame_rescue_platform_cooldown * 1000)) {
            int cooldms = (ConfigData.ItemsInGame_rescue_platform_cooldown * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigData.ItemsInGame_rescue_platform_cooldown_chat.replace("{cooldown}", cooldmess));
            return;
        }

        if (p.getLocation().add(0,-1,0).getBlock().getType() != Material.AIR) {
            p.sendMessage(ConfigData.ItemsInGame_rescue_platform_insufficient_space_chat);
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

        ItemStack itemInHand = e.getPlayer().getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().setItemInHand(null);
        }


        Location block0 = p.getLocation().add(0,-1,0);

        Location block1 = p.getLocation().add(1,-1,0);
        Location block2 = p.getLocation().add(-1,-1,0);
        Location block3 = p.getLocation().add(0,-1,1);
        Location block4 = p.getLocation().add(0,-1,-1);

        Location block5 = p.getLocation().add(1,-1,-1);
        Location block6 = p.getLocation().add(1,-1,1);
        Location block7 = p.getLocation().add(-1,-1,1);
        Location block8 = p.getLocation().add(-1,-1,-1);

        Location block9 = p.getLocation().add(2,-1,-1);
        Location block10 = p.getLocation().add(2,-1,1);
        Location block11 = p.getLocation().add(-2,-1,-1);
        Location block12 = p.getLocation().add(-2,-1,1);

        Location block13 = p.getLocation().add(1,-1,-2);
        Location block14 = p.getLocation().add(-1,-1,-2);
        Location block15 = p.getLocation().add(1,-1,2);
        Location block16 = p.getLocation().add(-1,-1,2);


        sendBlock(block0);
        sendBlock(block1);
        sendBlock(block2);
        sendBlock(block3);
        sendBlock(block4);
        sendBlock(block5);
        sendBlock(block6);
        sendBlock(block7);
        sendBlock(block8);
        sendBlock(block9);
        sendBlock(block10);
        sendBlock(block11);
        sendBlock(block12);
        sendBlock(block13);
        sendBlock(block14);
        sendBlock(block15);
        sendBlock(block16);

        Location tp_loc = block0.clone();
        tp_loc = tp_loc.clone().add(0, 1, 0);
        tp_loc.setYaw(p.getLocation().getYaw());
        tp_loc.setPitch(p.getLocation().getPitch());
        p.teleport(tp_loc);

    }

    public void sendBlock(Location l) {

        Block b = l.getBlock();

        if (b.getType() == Material.AIR) {

            if (PlayerBlocks.safeMap_blocks.contains(b)) return;
            b.setType(Material.SLIME_BLOCK);

            if (ConfigData.ItemsInGame_rescue_platform_can_break) {
                PlayerBlocks.player_blocks.add(b);
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (b.getType() == Material.SLIME_BLOCK) {
                        b.setType(Material.AIR);
                    }
                }
            }.runTaskLater(CreeperStarBedwars.getPlugin(), (ConfigData.ItemsInGame_rescue_platform_disappear * 20));

        }

    }

}
