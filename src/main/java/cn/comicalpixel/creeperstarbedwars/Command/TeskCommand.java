package cn.comicalpixel.creeperstarbedwars.Command;

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeskCommand extends Command {

    public TeskCommand() {
        super("taskball");
    }

    public void register() {
        MinecraftServer.getServer().server.getCommandMap().register(this.getName(), CreeperStarBedwars.getInstance().getName(),this);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            ArmorStand armorStand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
            armorStand.setPassenger(armorStand.getWorld().dropItem(armorStand.getLocation(),new ItemStack(Material.FIREBALL)));
            return true;
        }
        return false;
    }
}
