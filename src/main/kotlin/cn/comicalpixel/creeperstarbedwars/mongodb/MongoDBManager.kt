package cn.comicalpixel.creeperstarbedwars.mongodb

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils
import cn.comicalpixel.creeperstarbedwars.mongodb.type.ShopStats
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.bukkit.Bukkit
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

/**
 * MongoDB 玩家数据管理单例对象(无认证版本)
 * 功能：处理玩家击杀、死亡等基本统计数据，使用连接池优化性能
 */
class MongoDBManager {

    lateinit var mongoClient: com.mongodb.client.MongoClient
    lateinit var database: MongoDatabase

    lateinit var shopStats: ShopStats

    @Synchronized
    fun connect() {
        Bukkit.getLogger().info("&f[&5Ender&2Creeper&f]&7[&2Creeper&eStar&fBedwars&7] &e正在启用MongoDB连接...")
        try {
            // Configure codec registry to handle POJOs
            val pojoCodecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
            )

            // Connection settings with connection pooling
            val settings = MongoClientSettings.builder()
                .applyConnectionString(ConnectionString("mongodb://" + ConfigUtils.getString(CreeperStarBedwars.getPlugin().config, "data.mongodb.host")+ ":" + ConfigUtils.getString(CreeperStarBedwars.getPlugin().config, "data.mongodb.port")))
                .applyToConnectionPoolSettings { builder ->
                    builder.maxSize(50)
                        .maxWaitTime(30, TimeUnit.SECONDS)
                        .maxConnectionLifeTime(30, TimeUnit.MINUTES)
                }
                .codecRegistry(pojoCodecRegistry)
                .build()

            mongoClient = MongoClients.create(settings)
            database = mongoClient.getDatabase(ConfigUtils.getString(CreeperStarBedwars.getPlugin().config, "data.mongodb.datavas"))

            shopStats = ShopStats(database)

            // Test connection
            database.runCommand(org.bson.Document("ping", 1))
            Bukkit.getLogger().info("f[&5Ender&2Creeper&f]&7[&2Creeper&eStar&fBedwars&7] &eMongoDB连接成功 :D！")
        } catch (e: Exception) {
            Bukkit.getLogger().info("f[&5Ender&2Creeper&f]&7[&2Creeper&eStar&fBedwars&7] &cMongoDB无法连接 :(！${e}")
            Thread.sleep(10 * 1000)
            exitProcess(0)
        }
    }

    fun close() {
        mongoClient.close()
    }

}