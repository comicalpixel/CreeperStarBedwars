package cn.comicalpixel.creeperstarbedwars.Items.DamageHoloTitle;

import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageHolo implements Listener {

    @EventHandler
    public void onEntityDamagebyEntity_attack(EntityDamageByEntityEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {return;}

//        if (!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Player)) {return;}
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        if (e.isCancelled()) {return;}

        Player p = (Player) e.getEntity();
        Player damager = (Player) e.getDamager();

        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK && ConfigData.damageholo_damage_enabled) {
            String title = ConfigData.damageholo_damage_title;
            String subtitle = ConfigData.damageholo_damage_subtitle;

            int damage = (int) e.getFinalDamage();

            title = title.replace("{player}", p.getName()).replace("{damage}", String.valueOf(damage));
            subtitle = subtitle.replace("{player}", p.getName()).replace("{damage}", String.valueOf(damage));

            NMSTitleUntils.Title.send(damager, title, subtitle, 5, 30, 5);
        }

    }


    @EventHandler
    public void onEntityDamagebyEntity_projectile(EntityDamageByEntityEvent e) {

        if (GameStats.get() != 2 && GameStats.get() != 3) {return;}

        if (e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;

        if (!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Arrow)) {return;}

        if (e.isCancelled()) {return;}

        Player p = (Player) e.getEntity();
        Arrow arrow = (Arrow) e.getDamager();
        Player damager = null;
        if (arrow.getShooter() instanceof Player) {
            damager = (Player) arrow.getShooter();
        } else {
            return;
        }


        if (ConfigData.damageholo_projectile_enabled) {

            String chat = ConfigData.damageholo_projectile_chat;

            int damage = (int) e.getFinalDamage();

            chat = chat.replace("{player}", p.getName()).replace("{damage}", String.valueOf(damage));

            damager.sendMessage(chat);
        }



    }

}
