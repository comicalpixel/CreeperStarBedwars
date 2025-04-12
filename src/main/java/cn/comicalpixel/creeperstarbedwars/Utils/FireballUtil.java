package cn.comicalpixel.creeperstarbedwars.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Fireball;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FireballUtil extends JavaPlugin implements Listener {
    private static Field fieldFireballDirX;
    private static Field fieldFireballDirY;
    private static Field fieldFireballDirZ;
    private static Method craftFireballHandle;

    static {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String nmsFireball = "net.minecraft.server." + version + "EntityFireball";
        String craftFireball = "org.bukkit.craftbukkit." + version + "entity.CraftFireball";

        try {
            Class<?> fireballClass = Class.forName(nmsFireball);
            fieldFireballDirX = fireballClass.getDeclaredField("dirX");
            fieldFireballDirY = fireballClass.getDeclaredField("dirY");
            fieldFireballDirZ = fireballClass.getDeclaredField("dirZ");
            craftFireballHandle = Class.forName(craftFireball).getDeclaredMethod("getHandle");
            fieldFireballDirX.setAccessible(true);
            fieldFireballDirY.setAccessible(true);
            fieldFireballDirZ.setAccessible(true);
            craftFireballHandle.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
            Bukkit.shutdown();
        }
    }

    public static void setFireballDirection(Fireball fireball, Vector direction) {
        try {
            Object handle = craftFireballHandle.invoke(fireball);
            fieldFireballDirX.set(handle, direction.getX() * 0.1);
            fieldFireballDirY.set(handle, direction.getY() * 0.1);
            fieldFireballDirZ.set(handle, direction.getZ() * 0.1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
