package cn.comicalpixel.creeperstarbedwars.mongodb.type

import cn.comicalpixel.creeperstarbedwars.data.GamePlayer
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.bukkit.entity.Player
import java.util.UUID

class PlayerStats(private val mongoDatabase: MongoDatabase) {

    init {
        createTable()
    }

    /**
     * 创建一个数据库的表，第一个uuid索引添加
     */

    private fun createTable() {
        try {
            mongoDatabase.createCollection("Player_Stats")
            // 创建 uuid 索引
            mongoDatabase.getCollection("Player_Stats")
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
            put("kills",gamePlayer.kills)
            put("final_kills",gamePlayer.final_kills)
            put("beds",gamePlayer.beds)
            put("wins",gamePlayer.wins)
            put("loser",gamePlayer.loser)
            put("deaths",gamePlayer.deaths)
            put("plays",gamePlayer.plays)
            put("bwim",gamePlayer.bwim_resmode)
        }
        mongoDatabase.getCollection("Player_Stats").insertOne(document)
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
            put("kills",gamePlayer.kills)
            put("final_kills",gamePlayer.final_kills)
            put("beds",gamePlayer.beds)
            put("wins",gamePlayer.wins)
            put("loser",gamePlayer.loser)
            put("deaths",gamePlayer.deaths)
            put("plays",gamePlayer.plays)
            put("bwim",gamePlayer.bwim_resmode)
        })
        val result = mongoDatabase.getCollection("Player_Stats")
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
    fun getStats(uuid: UUID, name: String): Int {
        val document = mongoDatabase.getCollection("Player_Stats")
            .find(Document("uuid", uuid.toString()))
            .first() ?: return 0

        return document.getInteger(name, 0)
    }

    /**
     * 玩家的布尔值
     */

    @Synchronized
    fun getBoolean(uuid: UUID, name: String): Boolean {
        val document = mongoDatabase.getCollection("Player_Stats")
            .find(Document("uuid", uuid.toString()))
            .first() ?: return false

        return document.getBoolean(name, false)
    }

    /**
     * 幼儿园级别写法
     */

    // 相当于说："大家排好队！一个一个来查账本，不要挤！"
    //（防止多人同时查数据出乱子）
    @Synchronized
    fun getStats1(uuid: UUID, name: String): Int {
        // 1. 打开账本
        val collection = mongoDatabase.getCollection("Player_Stats")
        // 2. 按uuid查记录
        val query = Document("uuid", uuid.toString())
        val result = collection.find(query).first()
        // 3. 如果没找到，返回0
        if (result == null) return 0
        // 4. 读出指定项目的值
        return result.getInteger(name, 0)
    }

    fun getPlayerStats(uuid: UUID): Document? {
        return mongoDatabase.getCollection("Player_Stats")
            .find(Document("uuid", uuid.toString()))
            .first()
    }

}