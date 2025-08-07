package cn.comicalpixel.creeperstarbedwars.Arena.SPEC;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers;
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import cn.comicalpixel.creeperstarbedwars.Utils.ActionBarUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import cn.comicalpixel.creeperstarbedwars.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;

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
    public void onClickEntity(PlayerInteractEntityEvent e) {
        if (!GamePlayers.specs.contains((e.getPlayer()))) {return;}
        e.setCancelled(true);
        if (e.getRightClicked() instanceof Player) {
            Player target = (Player) e.getRightClicked();
            if (target.isOnline()) {
                e.getPlayer().setGameMode(GameMode.SPECTATOR);
                e.getPlayer().setSpectatorTarget(target);
                NMSTitleUntils.Title.send(e.getPlayer(), ConfigUtils.getString(CreeperStarBedwars.Instance.getConfig(), "language.spectator-first-person-enter-title").replace("{target}", target.getName()), ConfigUtils.getString(CreeperStarBedwars.Instance.getConfig(), "language.spectator-first-person-enter-subtitle").replace("{target}", target.getName()), 1, 30, 1);
            }
        }
    }

    @EventHandler
    public void onLeaveSpec(PlayerToggleSneakEvent e) {
        if (!GamePlayers.specs.contains((e.getPlayer()))) {return;}
        if (e.getPlayer().getGameMode() != GameMode.SPECTATOR && e.getPlayer().getSpectatorTarget() != null) return;
        e.setCancelled(true);
        e.getPlayer().setSpectatorTarget(null);
        e.getPlayer().setGameMode(GameMode.ADVENTURE);
        NMSTitleUntils.Title.send(e.getPlayer(), ConfigUtils.getString(CreeperStarBedwars.Instance.getConfig(), "language.spectator-first-person-quit-title"), ConfigUtils.getString(CreeperStarBedwars.Instance.getConfig(), "language.spectator-first-person-quit-subtitle"), 1, 30, 1);
        e.getPlayer().setAllowFlight(true);
        e.getPlayer().setFlying(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onClickInventory(InventoryClickEvent e) {
        if (!GamePlayers.specs.contains((Player) e.getWhoClicked())) return;
        e.setCancelled(true);
        // 传送菜单
        if (e.getInventory().getType() == InventoryType.CHEST && e.getInventory().getTitle().endsWith("§7§a§c§k§a§e§f")) {
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                Player target_p = Bukkit.getPlayer(((SkullMeta) e.getCurrentItem().getItemMeta()).getOwner());
                if (target_p.isOnline()) {
                    ((Player) e.getWhoClicked()).teleport(target_p);
                    e.getWhoClicked().closeInventory();
                }
            }
        }
    }

    public static HashMap<Player, Integer> spec_flySpeed = new HashMap<>();

    @EventHandler
    public void SpecItemClick(PlayerInteractEvent e) {

        try {
            if (GamePlayers.specs.isEmpty()) return;
            if (!GamePlayers.specs.contains(e.getPlayer())) return;
            e.setCancelled(true);
            if (e.getItem().getItemMeta().getDisplayName().endsWith("§3§c§e§c§e§1")) {
                SpecManager.open_tplist_gui(e.getPlayer());
            }
            if (e.getItem().getItemMeta().getDisplayName().endsWith("§3§c§e§c§e§2")) {
                Player p = e.getPlayer();
                switch (spec_flySpeed.get(p)) {
                    case 0:
                        SpecListener.spec_flySpeed.put(p, 1);
                        p.setFlySpeed(0.2f);
                        ActionBarUtils.sendActionBar(p, ConfigUtils.getString(CreeperStarBedwars.getInstance().getConfig(), "Spec-Settings.flySpeedSetting.settings.set-speed-1-actionbar"));
                        break;
                    case 1:
                        SpecListener.spec_flySpeed.put(p, 2);
                        p.setFlySpeed(0.3f);
                        ActionBarUtils.sendActionBar(p, ConfigUtils.getString(CreeperStarBedwars.getInstance().getConfig(), "Spec-Settings.flySpeedSetting.settings.set-speed-2-actionbar"));
                        break;
                    case 2:
                        SpecListener.spec_flySpeed.put(p, 3);
                        p.setFlySpeed(0.4f);
                        ActionBarUtils.sendActionBar(p, ConfigUtils.getString(CreeperStarBedwars.getInstance().getConfig(), "Spec-Settings.flySpeedSetting.settings.set-speed-3-actionbar"));
                        break;
                    case 3:
                        SpecListener.spec_flySpeed.put(p, 4);
                        p.setFlySpeed(0.5f);
                        ActionBarUtils.sendActionBar(p, ConfigUtils.getString(CreeperStarBedwars.getInstance().getConfig(), "Spec-Settings.flySpeedSetting.settings.set-speed-4-actionbar"));
                        break;
                    case 4:
                        SpecListener.spec_flySpeed.put(p, 0);
                        p.setFlySpeed(0.1f);
                        ActionBarUtils.sendActionBar(p, ConfigUtils.getString(CreeperStarBedwars.getInstance().getConfig(), "Spec-Settings.flySpeedSetting.settings.set-speed-0-actionbar"));
                        break;
                }
            }
            if (e.getItem().getItemMeta().getDisplayName().endsWith("§3§c§e§c§e§3")) {
                PlayerUtils.leave_game(e.getPlayer());
            }
        } catch (Exception ignored) {
        }

    }

}
