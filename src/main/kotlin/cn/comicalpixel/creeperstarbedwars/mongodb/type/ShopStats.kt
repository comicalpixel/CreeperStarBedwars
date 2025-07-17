package cn.comicalpixel.creeperstarbedwars.mongodb.type

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import cn.comicalpixel.creeperstarbedwars.data.GamePlayer
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.bukkit.entity.Player
import java.util.*

class ShopStats(private val mongoDatabase: MongoDatabase) {
    init {
        createTable()
    }

    /**
     * 创建一个数据库的表，第一个uuid索引添加
     */

    private fun createTable() {
        try {
            mongoDatabase.createCollection("Shop_Stats")
            // 创建 uuid 索引
            mongoDatabase.getCollection("Shop_Stats")
                .createIndex(Document("uuid", 1))
        } catch (_: Exception) {
            // 忽略已存在的集合
        }
    }

    @Synchronized
    private fun create(player: Player) {
        val gamePlayer = GamePlayer.get(player.uniqueId) ?: return
        val document = Document().apply {
            put("uuid", player.uniqueId.toString())
            put("name",player.name)
            put("solt_1",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt01"))
            put("solt_2",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt02"))
            put("solt_3",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt03"))
            put("solt_4",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt04"))
            put("solt_5",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt05"))
            put("solt_6",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt06"))
            put("solt_7",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt07"))
            put("solt_8",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt08"))
            put("solt_9",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt09"))
            put("solt_10",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt10"))
            put("solt_11",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt11"))
            put("solt_12",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt12"))
            put("solt_13",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt13"))
            put("solt_14",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt14"))
            put("solt_15",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt15"))
            put("solt_16",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt16"))
            put("solt_17",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt17"))
            put("solt_18",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt18"))
            put("solt_19",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt19"))
            put("solt_20",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt20"))
            put("solt_21",CreeperStarBedwars.getPlugin().shopConfig.getString("GUI.gui-quickshop.def-items.solt21"))
        }
        mongoDatabase.getCollection("Shop_Stats").insertOne(document)
    }

    /**
     * 更新玩家的数据，写入表里，如果没有就自行创建，更新
     */

    @Synchronized
    fun update(player: Player) {
        val gamePlayer = GamePlayer.get(player.uniqueId) ?: return
        val filter = Document("uuid", player.uniqueId.toString())
        val update = Document("\$set", Document().apply {
            put("uuid", player.uniqueId.toString())
            put("name",player.name)

        })
        val result = mongoDatabase.getCollection("Shop_Stats")
            .updateOne(filter, update)

        if (result.matchedCount == 0L) {
            create(player)
            update(player)
        }
    }


    /**
     * 获取玩家数据，后面填入json查询内容
     */


    @Synchronized
    fun getStats(uuid: UUID, name: String): String {
        val document = mongoDatabase.getCollection("Shop_Stats")
            .find(Document("uuid", uuid.toString()))
            .first() ?: return ""

        return document.getString(name)
    }

    @Synchronized
    fun getSlot(uuid: UUID, slot: Int): String {
        val document = mongoDatabase.getCollection("Shop_Stats")
            .find(Document("uuid", uuid.toString()))
            .first() ?: return "none"

        return document.getString("solt$slot") ?: "none"
    }

    @Synchronized
    fun setSlot(uuid: UUID, slot: Int, value: String) {
        val filter = Document("uuid", uuid.toString())
        val update = Document("\$set", Document("solt$slot", value))

        mongoDatabase.getCollection("Shop_Stats")
            .updateOne(filter, update)
    }
}