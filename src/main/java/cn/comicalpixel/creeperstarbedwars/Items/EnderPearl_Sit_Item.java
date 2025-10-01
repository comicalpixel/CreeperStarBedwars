package cn.comicalpixel.creeperstarbedwars.Items;

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.MessageVariableUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EnderPearl_Sit_Item implements Listener {

    private Map<Player, Long> cooldownMap = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {
            return;
        }

        if (!GamePlayers.players.contains(e.getPlayer())) return;

        if (e.isCancelled()) return;

        if (e.getItem() == null) return;

        if (e.getItem().getType() != Material.ENDER_PEARL) return;

        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        e.setCancelled(true);

        Player p = e.getPlayer();

        // 检查冷却时间
        if (cooldownMap.containsKey(p) && System.currentTimeMillis() - cooldownMap.get(p) < (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.enderpearl-sit.cooldown") * 1000)) {
            int cooldms = (ConfigUtils.getInt(CreeperStarBedwars.getPlugin().getConfig(), "items.enderpearl-sit.cooldown") * 1000) - (int) (System.currentTimeMillis() - cooldownMap.get(p));
            int cooldms_s = MessageVariableUtils.convertMillisecondsToSeconds(cooldms);
            String cooldmess = String.valueOf(cooldms_s); // String.valueOf(cooldownMap.containsKey(player)); //System.currentTimeMillis() -
            e.setCancelled(true);
            p.sendMessage(ConfigUtils.getString(CreeperStarBedwars.getPlugin().getConfig(), "items.enderpearl-sit.cooldown-chat").replace("{cooldown}", cooldmess));
            return;
        }

        cooldownMap.put(p, System.currentTimeMillis()); // 设置当前玩家的冷却时间

        ItemStack itemInHand = e.getPlayer().getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else {
            p.getInventory().setItemInHand(null);
        }

        e.setCancelled(true);

        CraftEnderPearl craftEnderPearl = p.launchProjectile(CraftEnderPearl.class);
        craftEnderPearl.setShooter(p);
        craftEnderPearl.setPassenger(p);
        craftEnderPearl.getLocation().getWorld().playSound(craftEnderPearl.getLocation(), Sound.ENDERMAN_TELEPORT, 0.7f, 1f);

    }


}
