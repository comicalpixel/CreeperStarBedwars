package cn.comicalpixel.creeperstarbedwars.api.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BedwarsGameStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public BedwarsGameStartEvent() {
        super(true); // yb
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
