package cn.comicalpixel.creeperstarbedwars.Fix;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

public class ComboFix implements Listener {

    private Map<Player, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (e.isCancelled())   return;
            if (cooldown.containsKey(player) && System.currentTimeMillis() - cooldown.get(player) < 450) {
                e.setCancelled(true);
                return;
            }
            cooldown.put(player, System.currentTimeMillis());
        }
    }

}
