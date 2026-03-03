package cn.comicalpixel.creeperstarbedwars.api.Events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerEliminatedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player p;

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public PlayerEliminatedEvent(Player player) {
        super(true); // yb
        this.p = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return p;
    }

}
