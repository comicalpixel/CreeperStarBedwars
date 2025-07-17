package cn.comicalpixel.creeperstarbedwars.api.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BedwarsGameEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public BedwarsGameEndEvent() {
        super(true); // yb
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
