package cn.comicalpixel.creeperstarbedwars.Utils;

import com.google.common.base.Charsets;
import org.bukkit.*;
import org.json.simple.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

public class NMSTitleUntils {
   private static Class<?> nmsChatSerializer;
   private static Class<?> nmsIChatBaseComponent;
   private static Class<?> packetType;
   private static Class<?> packetActions;
   private static Class<?> packetTitle;
   private static Method getHandle;
   private static String version;
   private static Field playerConnection;
   private static Method sendPacket;

   static {
      try {
         version = getNMSVersion();
         boolean newversion = Integer.parseInt(version.split("_")[1]) > 7;
         nmsChatSerializer = Class.forName(a(newversion ? "IChatBaseComponent$ChatSerializer" : "ChatSerializer"));
         nmsIChatBaseComponent = Class.forName(a("IChatBaseComponent"));
         packetType = Class.forName(a("PacketPlayOutChat"));
         packetActions = Class.forName(a(newversion ? "PacketPlayOutTitle$EnumTitleAction" : "EnumTitleAction"));
         packetTitle = Class.forName(a("PacketPlayOutTitle"));
         Class<?> typeCraftPlayer = Class.forName(b("entity.CraftPlayer"));
         Class<?> typeNMSPlayer = Class.forName(a("EntityPlayer"));
         Class<?> typePlayerConnection = Class.forName(a("PlayerConnection"));
         getHandle = typeCraftPlayer.getMethod("getHandle");
         playerConnection = typeNMSPlayer.getField("playerConnection");
         sendPacket = typePlayerConnection.getMethod("sendPacket", Class.forName(a("Packet")));
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private NMSTitleUntils() {
   }

   public static String a(String str) {
      return "net.minecraft.server." + version + "." + str;
   }

   public static String b(String str) {
      return "org.bukkit.craftbukkit." + version + "." + str;
   }

   public static String getNMSVersion() {
      return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
   }

   public static class ActionBar {
      private ActionBar() {
      }

      public static void broadcast(String message) {
         Iterator var2 = Player.getOnlinePlayers().iterator();

         while(var2.hasNext()) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player)var2.next();
            send(player, message);
         }

      }

      public static void broadcast(final String message, final int times) {
         (new Thread(new Runnable() {
            public void run() {
               int time = times;

               do {
                  Iterator var3 = Player.getOnlinePlayers().iterator();

                  while(var3.hasNext()) {
                     org.bukkit.entity.Player player = (org.bukkit.entity.Player)var3.next();
                     ActionBar.send(player, message);
                  }

                  try {
                     Thread.sleep(1000L);
                  } catch (InterruptedException var4) {
                  }

                  --time;
               } while(time > 0);

            }
         })).start();
      }

      public static void broadcast(final World world, final String message, final int times) {
         (new Thread(new Runnable() {
            public void run() {
               int time = times;

               do {
                  Iterator var3 = Player.getOnlinePlayers().iterator();

                  while(var3.hasNext()) {
                     org.bukkit.entity.Player player = (org.bukkit.entity.Player)var3.next();
                     if (player.getWorld().getName().equalsIgnoreCase(world.getName())) {
                        ActionBar.send(player, message);
                     }
                  }

                  try {
                     Thread.sleep(1000L);
                  } catch (InterruptedException var4) {
                  }

                  --time;
               } while(time > 0);

            }
         })).start();
      }

      public static void send(org.bukkit.entity.Player receivingPacket, String msg) {
         Object packet = null;

         try {
            Object serialized = NMSTitleUntils.nmsChatSerializer.getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', JSONObject.escape(msg)) + "\"}");
            if (!NMSTitleUntils.version.contains("1_7")) {
               packet = NMSTitleUntils.packetType.getConstructor(NMSTitleUntils.nmsIChatBaseComponent, Byte.TYPE).newInstance(serialized, 2);
            } else {
               packet = NMSTitleUntils.packetType.getConstructor(NMSTitleUntils.nmsIChatBaseComponent, Integer.TYPE).newInstance(serialized, 2);
            }

            Object player = NMSTitleUntils.getHandle.invoke(receivingPacket);
            Object connection = NMSTitleUntils.playerConnection.get(player);
            NMSTitleUntils.sendPacket.invoke(connection, packet);
         } catch (Exception var6) {
            var6.printStackTrace();
         }

      }

      public static void send(final org.bukkit.entity.Player receivingPacket, final String msg, final int times) {
         (new Thread(new Runnable() {
            public void run() {
               int time = times;

               do {
                  ActionBar.send(receivingPacket, msg);

                  try {
                     Thread.sleep(1000L);
                  } catch (InterruptedException var3) {
                  }

                  --time;
               } while(time > 0);

            }
         })).start();
      }
   }

   public static class Player {
      private static Class<?> gameProfileClass;
      private static Constructor<?> gameProfileConstructor;
      private static Constructor<?> craftOfflinePlayerConstructor;
      private static Method getOnlinePlayers;

      static {
         try {
            getOnlinePlayers = Bukkit.class.getDeclaredMethod("getOnlinePlayers");
            if (getOnlinePlayers.getReturnType() != org.bukkit.entity.Player[].class) {
               Method[] var3;
               int var2 = (var3 = Bukkit.class.getDeclaredMethods()).length;

               for(int var1 = 0; var1 < var2; ++var1) {
                  Method method = var3[var1];
                  if (method.getReturnType() == org.bukkit.entity.Player[].class && method.getName().endsWith("getOnlinePlayers")) {
                     getOnlinePlayers = method;
                  }
               }
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }

         try {
            try {
               gameProfileClass = Class.forName("net.minecraft.util.com.mojang.authlib.GameProfile");
            } catch (Exception var5) {
               try {
                  gameProfileClass = Class.forName("com.mojang.authlib.GameProfile");
               } catch (Exception var4) {
               }
            }

            gameProfileConstructor = gameProfileClass.getDeclaredConstructor(UUID.class, String.class);
            gameProfileConstructor.setAccessible(true);
            Class<? extends Server> craftServer = Bukkit.getServer().getClass();
            Class<?> craftOfflinePlayer = Class.forName(craftServer.getName().replace("CraftServer", "CraftOfflinePlayer"));
            craftOfflinePlayerConstructor = craftOfflinePlayer.getDeclaredConstructor(craftServer, gameProfileClass);
            craftOfflinePlayerConstructor.setAccessible(true);
         } catch (Exception var6) {
            var6.printStackTrace();
         }

      }

      private Player() {
      }

      public static OfflinePlayer getOfflinePlayer(String playerName) {
         try {
            Object gameProfile = gameProfileConstructor.newInstance(UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes(Charsets.UTF_8)), playerName);
            Object offlinePlayer = craftOfflinePlayerConstructor.newInstance(Bukkit.getServer(), gameProfile);
            return (OfflinePlayer)offlinePlayer;
         } catch (Exception var3) {
            return Bukkit.getOfflinePlayer(playerName);
         }
      }

      public static Collection<? extends org.bukkit.entity.Player> getOnlinePlayers() {
         try {
            return Arrays.asList((org.bukkit.entity.Player[])getOnlinePlayers.invoke((Object)null));
         } catch (Exception var1) {
            return Bukkit.getOnlinePlayers();
         }
      }
   }

   public static class Title {
      private Title() {
      }

      public static void broadcast(String title, String subtitle) {
         Iterator var3 = Player.getOnlinePlayers().iterator();

         while(var3.hasNext()) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player)var3.next();
            send(player, title, subtitle);
         }

      }

      public static void broadcast(String title, String subtitle, int fadeInTime, int stayTime, int fadeOutTime) {
         Iterator var6 = Player.getOnlinePlayers().iterator();

         while(var6.hasNext()) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player)var6.next();
            send(player, title, subtitle, fadeInTime, stayTime, fadeOutTime);
         }

      }

      public static void broadcast(World world, String title, String subtitle) {
         Iterator var4 = Player.getOnlinePlayers().iterator();

         while(var4.hasNext()) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player)var4.next();
            if (player.getWorld().getName().equalsIgnoreCase(world.getName())) {
               send(player, title, subtitle);
            }
         }

      }

      public static void reset(org.bukkit.entity.Player recoverPlayer) throws Exception {
         Object player = NMSTitleUntils.getHandle.invoke(recoverPlayer);
         Object connection = NMSTitleUntils.playerConnection.get(player);
         Object[] actions = NMSTitleUntils.packetActions.getEnumConstants();
         Object packet = NMSTitleUntils.packetTitle.getConstructor(NMSTitleUntils.packetActions, NMSTitleUntils.nmsIChatBaseComponent).newInstance(actions[4], null);
         NMSTitleUntils.sendPacket.invoke(connection, packet);
      }

      public static void send(org.bukkit.entity.Player receivingPacket, String title, String subtitle) {
         send(receivingPacket, title, subtitle, 1, 2, 1);
      }

      public static void send(org.bukkit.entity.Player receivingPacket, String title, String subtitle, int fadeInTime, int stayTime, int fadeOutTime) {
         if (NMSTitleUntils.packetTitle != null) {
            try {
               reset(receivingPacket);
               Object player = NMSTitleUntils.getHandle.invoke(receivingPacket);
               Object connection = NMSTitleUntils.playerConnection.get(player);
               Object[] actions = NMSTitleUntils.packetActions.getEnumConstants();
               Object packet = null;
               if (fadeInTime != -1 && fadeOutTime != -1 && stayTime != -1) {
                  packet = NMSTitleUntils.packetTitle.getConstructor(NMSTitleUntils.packetActions, NMSTitleUntils.nmsIChatBaseComponent, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(actions[2], null, fadeInTime, stayTime, fadeOutTime);
                  NMSTitleUntils.sendPacket.invoke(connection, packet);
               }

               Object serialized = NMSTitleUntils.nmsChatSerializer.getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', title) + "\"}");
               packet = NMSTitleUntils.packetTitle.getConstructor(NMSTitleUntils.packetActions, NMSTitleUntils.nmsIChatBaseComponent).newInstance(actions[0], serialized);
               NMSTitleUntils.sendPacket.invoke(connection, packet);
               if (!"".equals(subtitle)) {
                  serialized = NMSTitleUntils.nmsChatSerializer.getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}");
                  packet = NMSTitleUntils.packetTitle.getConstructor(NMSTitleUntils.packetActions, NMSTitleUntils.nmsIChatBaseComponent).newInstance(actions[1], serialized);
                  NMSTitleUntils.sendPacket.invoke(connection, packet);
               }
            } catch (Exception var11) {
               var11.printStackTrace();
            }
         }

      }
   }
}
