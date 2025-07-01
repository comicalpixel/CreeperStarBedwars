package cn.comicalpixel.creeperstarbedwars.Arena.SPEC;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class SpecListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!GamePlayers.specs.contains(e.getPlayer())) {return;}
        e.setCancelled(true);
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (!GamePlayers.specs.contains(((Player) e.getDamager()))) {return;}
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPickupItem(PlayerPickupItemEvent e) {
        if (!GamePlayers.specs.contains(e.getPlayer())) {return;}
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player) {
            if (!GamePlayers.specs.contains(((Player) e.getEntity()))) {return;}
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                e.setCancelled(true);
                e.getEntity().teleport(GameData_cfg.spec_loc);
                return;
            }
            e.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDrop(PlayerDropItemEvent e) {
        if (!GamePlayers.specs.contains(e.getPlayer())) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onClickInventory(InventoryClickEvent e) {
        if (!GamePlayers.specs.contains((Player) e.getWhoClicked())) return;
        e.setCancelled(true);
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void SpecItemClick(PlayerInteractEvent e) {
        if (!GamePlayers.specs.contains(e.getPlayer())) return;
        e.setCancelled(true);
        if (e.getItem().getItemMeta().getDisplayName().endsWith("§3§c§e§c§e§1")) {
            SpecManager.open_tplist_gui(e.getPlayer());
        }
        if (e.getItem().getItemMeta().getDisplayName().endsWith("§3§c§e§c§e§3")) {
            PlayerUtils.leave_game(e.getPlayer());
        }

    }

}
