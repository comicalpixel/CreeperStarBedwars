package cn.comicalpixel.creeperstarbedwars.Listener

import cn.comicalpixel.creeperstarbedwars.Arena.GamePlayers
import cn.comicalpixel.creeperstarbedwars.Arena.Stats.GameStats
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData
import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils
import cn.comicalpixel.creeperstarbedwars.data.GamePlayer
import cn.comicalpixel.creeperstarbedwars.data.GamePlayer.Companion.get
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import kotlin.random.Random

class BwimResItemManager : Listener {

    companion object {
        var playerMode = mutableMapOf<Player, Int>()

        fun getModeName(p:Player):String {

            var s = " "

            if (playerMode[p] == 0) {
                s = CreeperStarBedwars.getPlugin().config.getString("select-bwim.name-i")
            }
            if (playerMode[p] == 1) {
                s = CreeperStarBedwars.getPlugin().config.getString("select-bwim.name-xp")
            }

            return s

        }

    }

//    本来是想用来写击杀给予资源的...
//    val player_iron = HashMap<Player, Int>()
//    val player_gold = HashMap<Player, Int>()
//    val player_emerald = HashMap<Player, Int>()
//    val player_diamond = HashMap<Player, Int>()
//
//    val player_xplevel = HashMap<Player, Int>()


    @EventHandler
    fun JoinGame(e: PlayerJoinEvent) {

        val p = e.player

        if (ConfigData.bwimsel_enabled) {

            if (CreeperStarBedwars.getPlugin().config.getString("data.type").equals("mongodb", ignoreCase = true)) {
                val gamePlayer = get(p.uniqueId) ?: GamePlayer.create(p.uniqueId,p.name)
                CreeperStarBedwars.getPlugin().getPlayerStats().update(p);
                if (gamePlayer.bwim_resmode) {
                    playerMode[p] = 1;
                }
                if (!gamePlayer.bwim_resmode) {
                    playerMode[p] = 0;
                }

            } else {
                playerMode[p] = CreeperStarBedwars.getPlugin().playerDataConfig.getInt(p.name + ".bwim")
            }

        } else {
            playerMode[p] = ConfigData.bwimsel_default;
        }

//        BwimResItemManager.playerMode.put(p, CreeperStarBedwars.getPlugin().getPlayerDataConfig().getInt(p.name + ".bwim"))

        // 如果存在错误的模式
        if (playerMode[p] != 0 && playerMode[p] != 1) {
            playerMode[p] = 0
        }


    }

    @EventHandler
    fun PickUp(e: PlayerPickupItemEvent) {
        if (GameStats.get() != 2 && GameStats.get() != 3) return
        if (!GamePlayers.players.contains(e.player)) return
        if (e.item.itemStack.type != Material.IRON_INGOT && e.item.itemStack.type != Material.GOLD_INGOT &&
            e.item.itemStack.type != Material.DIAMOND && e.item.itemStack.type != Material.EMERALD) {
            return
        }
        if (e.isCancelled) return

        val p = e.player
        val nearbyPlayers = p.getNearbyEntities(1.0, 1.0, 1.0)
            .filterIsInstance<Player>()
            .filter { it != p && GamePlayers.players.contains(it) }


        fun shareResources(resourceType: Material, amount: Int) {
            if (Random.nextInt(5) > 1 && nearbyPlayers.isNotEmpty()) {
                nearbyPlayers.forEach { player ->
                    when (playerMode.getOrDefault(player, ConfigData.bwimsel_default)) {
                        0 -> {
                            val sharedItem = e.item.itemStack.clone().apply {
                                this.amount = amount
                                itemMeta = itemMeta?.apply { displayName = null } // 清除物品名称
                            }
                            player.inventory.addItem(sharedItem)
                            ConfigUtils.playSound(player, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim0")
                        }
                        1 -> {
                            val xp = when (resourceType) {
                                Material.IRON_INGOT -> amount * ConfigData.bwim_conversion_iron
                                Material.GOLD_INGOT -> amount * ConfigData.bwim_conversion_gold
                                Material.EMERALD -> amount * ConfigData.bwim_conversion_emerald
                                Material.DIAMOND -> amount * ConfigData.bwim_conversion_diamond
                                else -> 0
                            }
                            player.level += xp
                            ConfigUtils.playSound(player, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim1")
                        }
                    }
                }
            }
        }

        if (ConfigData.bwimsel_enabled) {
            when (playerMode.get(p)) {
                0 -> {
                    e.isCancelled = false
                    e.item.remove()
                    val meta = e.item.itemStack.itemMeta.apply { displayName = null }
                    e.item.itemStack.itemMeta = meta
                    ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim0")
                    shareResources(e.item.itemStack.type, 1)
                }
                1 -> {
                    e.isCancelled = true
                    var xp = 0
                    val amount = e.item.itemStack.amount
                    when (e.item.itemStack.type) {
                        Material.IRON_INGOT -> xp = amount * ConfigData.bwim_conversion_iron
                        Material.GOLD_INGOT -> xp = amount * ConfigData.bwim_conversion_gold
                        Material.EMERALD -> xp = amount * ConfigData.bwim_conversion_emerald
                        Material.DIAMOND -> xp = amount * ConfigData.bwim_conversion_diamond
                        else -> {}
                    }
                    e.item.remove()
                    p.level += xp
                    ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim1")
                    shareResources(e.item.itemStack.type, 1)
                }
            }
        } else {
            when (ConfigData.bwimsel_default) {
                0 -> {
                    e.isCancelled = true
                    e.item.remove()
                    val meta = e.item.itemStack.itemMeta.apply { displayName = null }
                    e.item.itemStack.itemMeta = meta
                    p.inventory.addItem(e.item.itemStack)
                    ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim0")
                    shareResources(e.item.itemStack.type, 1)
                }
                1 -> {
                    e.isCancelled = true
                    var xp = 0
                    val amount = e.item.itemStack.amount
                    when (e.item.itemStack.type) {
                        Material.IRON_INGOT -> xp = amount * 1
                        Material.GOLD_INGOT -> xp = amount * 10
                        Material.EMERALD, Material.DIAMOND -> xp = amount * 100
                        else -> {}
                    }
                    e.item.remove()
                    p.level += xp
                    ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim1")
                    shareResources(e.item.itemStack.type, 1)
                }
            }
        }
    }

//    @EventHandler
//    fun PickUp(e: PlayerPickupItemEvent) {
//
//        if (GameStats.get() != 2 && GameStats.get() != 3) return
//
//        if (!GamePlayers.players.contains(e.player)) return
//
//        if (e.item.itemStack.type != Material.IRON_INGOT && e.item.itemStack.type != Material.GOLD_INGOT && e.item.itemStack.type != Material.DIAMOND && e.item.itemStack.type != Material.EMERALD) {
//            return
//        }
//
//        if (e.isCancelled) return
//
//        val p = e.player
////        val gamePlayer = get(p.uniqueId) ?: return
//
//        /**/
//
//        if (ConfigData.bwimsel_enabled) {
//
//            if (playerMode.get(p) == 0) {
//
//                e.isCancelled = false
//                e.item.remove()
//                val meta = e.item.itemStack.itemMeta
//                meta.displayName = null
//                e.item.itemStack.setItemMeta(meta)
//
//                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim0")
//
//            }
//            else if (playerMode[p] == 1) { // || gamePlayer.bwim_resmode
//
//                e.isCancelled = true
//                var xp = 0
//                val amount = e.item.itemStack.amount
//                if (e.item.itemStack.type == Material.IRON_INGOT) {
//                    xp += amount * ConfigData.bwim_conversion_iron
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//                if (e.item.itemStack.type == Material.GOLD_INGOT) {
//                    xp += amount * ConfigData.bwim_conversion_gold
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//                if (e.item.itemStack.type == Material.EMERALD) {
//                    xp += amount * ConfigData.bwim_conversion_emerald
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//                if (e.item.itemStack.type == Material.DIAMOND) {
//                    xp += amount * ConfigData.bwim_conversion_diamond
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//
//                p.level = p.level + xp
//
//                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim1")
//
//            }
//
//        } else {
//            if (ConfigData.bwimsel_default == 0) { // || !gamePlayer.bwim_resmode
//
//                e.isCancelled = true
//                e.item.remove()
//                val meta = e.item.itemStack.itemMeta
//                meta.displayName = null
//                e.item.itemStack.setItemMeta(meta)
//                p.inventory.addItem(e.item.itemStack)
//
//                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim0")
//
//            } else if (ConfigData.bwimsel_default == 1) {
//
//                e.isCancelled = true
//                var xp = 0
//                val amount = e.item.itemStack.amount
//                if (e.item.itemStack.type == Material.IRON_INGOT) {
//                    xp += amount * 1
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//                if (e.item.itemStack.type == Material.GOLD_INGOT) {
//                    xp += amount * 10
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//                if (e.item.itemStack.type == Material.EMERALD) {
//                    xp += amount * 100
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//                if (e.item.itemStack.type == Material.DIAMOND) {
//                    xp += amount * 100
//                    e.isCancelled = true
//                    e.item.remove()
//                }
//
//                p.level = p.level + xp
//
//                ConfigUtils.playSound(p, CreeperStarBedwars.getPlugin().config, "sound.resitem-pickup-bwim1")
//
//            }
//        }
//
//    }


    @EventHandler
    fun onDrop(e:PlayerDropItemEvent) {
        //ChatColor.WHITE + "[CreeperStarBedwars] Diamond " + random.nextInt(25565) + 1000

        if (GameStats.get() != 2 && GameStats.get() != 3) return

        if (!GamePlayers.players.contains(e.player)) return

        if (e.itemDrop.itemStack.type != Material.IRON_INGOT && e.itemDrop.itemStack.type != Material.GOLD_INGOT && e.itemDrop.itemStack.type != Material.DIAMOND && e.itemDrop.itemStack.type != Material.EMERALD) {
            return
        }

        if (e.isCancelled) return

        val p = e.player

        if (e.itemDrop.itemStack.type == Material.IRON_INGOT) {
            e.itemDrop.itemStack.itemMeta.displayName = "[CreeperStarBedwars] IRON " + Random.nextInt(25565) + 1000;
        } else if (e.itemDrop.itemStack.type == Material.GOLD_INGOT) {
            e.itemDrop.itemStack.itemMeta.displayName = "[CreeperStarBedwars] GOLD " + Random.nextInt(25565) + 1000;
        } else if (e.itemDrop.itemStack.type == Material.DIAMOND) {
            e.itemDrop.itemStack.itemMeta.displayName = "[CreeperStarBedwars] DIAMOND " + Random.nextInt(25565) + 1000;
        } else if (e.itemDrop.itemStack.type == Material.EMERALD) {
            e.itemDrop.itemStack.itemMeta.displayName = "[CreeperStarBedwars] EMERALD " + Random.nextInt(25565) + 1000;
        }

    }

}