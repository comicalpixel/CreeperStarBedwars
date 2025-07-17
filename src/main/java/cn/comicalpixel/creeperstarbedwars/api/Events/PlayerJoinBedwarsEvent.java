package cn.comicalpixel.creeperstarbedwars.api.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinBedwarsEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private static Player p = null;

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public PlayerJoinBedwarsEvent(Player player) {
        super(true); // yb
        p = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
