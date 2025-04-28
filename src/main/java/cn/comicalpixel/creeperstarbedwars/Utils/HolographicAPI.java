package cn.comicalpixel.creeperstarbedwars.Utils;

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class HolographicAPI {

    private Map<UUID, Integer> ids;
    private Map<UUID, Object> packets;
    private List<UUID> players;
    private Location location;
    private String title;
    private BukkitTask task;
    private List<ItemStack> equipment;
    // @Getter
    public boolean removed;


    public HolographicAPI(Location loc, String title) {
        ids = new HashMap<UUID, Integer>();
        packets = new HashMap<UUID, Object>();
        players = new ArrayList<UUID>();
        equipment = new ArrayList<ItemStack>();
        location = loc.clone();
        this.title = title;
        removed = false;
        task = new BukkitRunnable() {
            @Override
            public void run() {
                List<UUID> list = new ArrayList<UUID>();
                for (UUID uuid : packets.keySet()) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player == null || !player.isOnline()) {
                        list.add(uuid);
                    } else {
                        Location loc2 = player.getLocation().clone();
                        loc2.setY(location.getY());
                        if (players.contains(uuid)) {
                            if (!loc2.getWorld().getName().equals(location.getWorld().getName()) || loc2.distanceSquared(location) >= Math.pow(64, 2)) {
                                NMSUtil.sendPacket(player, packets.get(player.getUniqueId()));
                                players.remove(uuid);
                            }
                        } else if (loc2.getWorld().getName().equals(location.getWorld().getName()) && loc2.distanceSquared(location) < Math.pow(64, 2)) {
                            display(player);
                        }
                    }
                }
                for (UUID uuid : list) {
                    ids.remove(uuid);
                    packets.remove(uuid);
                    players.remove(uuid);
                }
            }
        }.runTaskTimer(CreeperStarBedwars.getPlugin(), 1L, 1L);
    }

    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<Player>();
        packets.keySet().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                list.add(player);
            }
        });
        return list;
    }

    public void setEquipment(List<ItemStack> equipment) {
        if (removed) {
            return;
        }
        this.equipment = new ArrayList<ItemStack>();
        this.equipment.addAll(equipment);
    }

    public Location getLocation() {
        return location.clone();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (removed) {
            return;
        }
        if (this.title == null) {
            this.title = title;
            for (UUID uuid : packets.keySet()) {
                Player player = Bukkit.getPlayer(uuid);
                if (player != null && player.isOnline() && players.contains(uuid)) {
                    display(player);
                }
            }
        } else {
            this.title = title;
            for (UUID uuid : packets.keySet()) {
                Player player = Bukkit.getPlayer(uuid);
                if (player != null && player.isOnline()) {
                    Location loc = player.getLocation().clone();
                    loc.setY(location.getY());
                    if (loc.getWorld().getName().equals(location.getWorld().getName()) && loc.distanceSquared(location) < Math.pow(64, 2) && player != null && player.isOnline() && players.contains(uuid)) {
                        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
                        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
                        packet.getIntegers().write(0, ids.get(uuid));
                        packet.getWatchableCollectionModifier().write(0, Arrays.asList(new WrappedWatchableObject(2, title)));
                        try {
                            protocolManager.sendServerPacket(player, packet);
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void display(Player player) {
        if (removed) {
            return;
        }
        if (player == null || task == null) {
            return;
        }
        if (packets.containsKey(player.getUniqueId())) {
            NMSUtil.sendPacket(player, packets.get(player.getUniqueId()));
        }
        Object packet = getPacket(location);
        Object destroyPacket = null;
        NMSUtil.sendPacket(player, packet);
        try {
            Field declaredField = NMSUtil.getNMSClass("PacketPlayOutSpawnEntityLiving").getDeclaredField("a");
            declaredField.setAccessible(true);
            ids.put(player.getUniqueId(), (int) declaredField.get(packet));
            destroyPacket = getDestroyPacket((int) declaredField.get(packet));
        } catch (Exception e) {
        }
        if (equipment.size() > 0) {
            try {
                Field declaredField = NMSUtil.getNMSClass("PacketPlayOutSpawnEntityLiving").getDeclaredField("a");
                declaredField.setAccessible(true);
                int j = 5;
                for (ItemStack itemStack : equipment) {
                    j--;
                    if (j < 0) {
                        break;
                    }
                    PacketContainer equipmentPacket = this.getEquipmentPacket((int) declaredField.get(packet), j, itemStack);
                    ProtocolLibrary.getProtocolManager().sendServerPacket(player, equipmentPacket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        packets.put(player.getUniqueId(), destroyPacket);
        players.add(player.getUniqueId());
    }

    public void destroy(Player player) {
        if (removed) {
            return;
        }
        if (player != null && packets.containsKey(player.getUniqueId())) {
            NMSUtil.sendPacket(player, packets.get(player.getUniqueId()));
            ids.remove(player.getUniqueId());
            packets.remove(player.getUniqueId());
            players.remove(player.getUniqueId());
        }
    }

    public void redisplay() {
        if (removed) {
            return;
        }
        packets.keySet().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                display(player);
            }
        });
    }

    public void remove() {
        if (removed) {
            return;
        }
        task.cancel();
        for (UUID uuid : packets.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                NMSUtil.sendPacket(player, packets.get(uuid));
            }
        }
        ids = new HashMap<UUID, Integer>();
        packets = new HashMap<UUID, Object>();
        players = new ArrayList<UUID>();
        removed = true;
    }

    public void teleport(Location loc) {
        if (removed) {
            return;
        }
        if (!loc.getWorld().getName().equals(location.getWorld().getName())) {
            return;
        }
        for (UUID uuid : packets.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline() && players.contains(uuid)) {
                sendTeleportPacket(ids.get(uuid), loc, player);
            }
        }
        location = loc.clone();
    }

    private void sendTeleportPacket(int id, Location loc, Player player) {
        try {
            @SuppressWarnings("rawtypes")
            Constructor constructor = NMSUtil.getNMSClass("PacketPlayOutEntityTeleport").getConstructor(int.class, int.class, int.class, int.class, byte.class, byte.class, boolean.class);
            Method method = NMSUtil.getNMSClass("MathHelper").getMethod("floor", double.class);
            Object packet = constructor.newInstance(id, method.invoke(null, location.getX() * 32.0D), method.invoke(null, location.getY() * 32.0D), method.invoke(null, location.getZ() * 32.0D), (byte) (location.getYaw() * 256.0f / 360.0f), (byte) (location.getPitch() * 256.0f / 360.0f), true);
            NMSUtil.sendPacket(player, packet);
        } catch (Exception e) {
        }
    }

    private Object getPacket(Location location) {
        try {
            Object cast = NMSUtil.getClass("CraftWorld").cast(location.getWorld());
            Object instance = NMSUtil.getNMSClass("EntityArmorStand").getConstructor(NMSUtil.getNMSClass("World")).newInstance(cast.getClass().getMethod("getHandle", (Class<?>[]) new Class[0]).invoke(cast, new Object[0]));
            if (title != null) {
                instance.getClass().getMethod("setCustomName", String.class).invoke(instance, title);
                NMSUtil.getNMSClass("Entity").getMethod("setCustomNameVisible", Boolean.TYPE).invoke(instance, true);
            }
            try {
                instance.getClass().getMethod("setGravity", Boolean.TYPE).invoke(instance, false);
            } catch (Exception ex) {
                instance.getClass().getMethod("setNoGravity", Boolean.TYPE).invoke(instance, true);
            }
            instance.getClass().getMethod("setLocation", Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE).invoke(instance, location.getX(), location.getY(), location.getZ(), 0.0f, 0.0f);
            instance.getClass().getMethod("setBasePlate", Boolean.TYPE).invoke(instance, false);
            instance.getClass().getMethod("setInvisible", Boolean.TYPE).invoke(instance, true);
            return NMSUtil.getNMSClass("PacketPlayOutSpawnEntityLiving").getConstructor(NMSUtil.getNMSClass("EntityLiving")).newInstance(instance);
        } catch (Exception e) {
            return null;
        }
    }

    private PacketContainer getEquipmentPacket(int id, int slot, ItemStack item) throws Exception {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_EQUIPMENT);
        packet.getIntegers().write(0, id);
        slot = slot > 4 ? 4 : slot;
        slot = slot < 0 ? 0 : slot;
        if (packet.getIntegers().size() > 1) {
            packet.getIntegers().write(1, slot);
        } else {
            switch (slot) {
                case 1:
                    packet.getItemSlots().write(0, EnumWrappers.ItemSlot.FEET);
                    break;
                case 2:
                    packet.getItemSlots().write(0, EnumWrappers.ItemSlot.LEGS);
                    break;
                case 3:
                    packet.getItemSlots().write(0, EnumWrappers.ItemSlot.CHEST);
                    break;
                case 4:
                    packet.getItemSlots().write(0, EnumWrappers.ItemSlot.HEAD);
                    break;
                case 0:
                    packet.getItemSlots().write(0, EnumWrappers.ItemSlot.MAINHAND);
                    break;
                default:
                    break;
            }
        }
        packet.getItemModifier().write(0, item);
        return packet;
    }

    private Object getDestroyPacket(int... array) throws Exception {
        try {
            return NMSUtil.getNMSClass("PacketPlayOutEntityDestroy").getConstructor(int[].class).newInstance(array);
        } catch (Exception e) {
            return null;
        }
    }
}
