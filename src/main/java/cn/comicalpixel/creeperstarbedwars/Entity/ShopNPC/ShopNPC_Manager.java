package cn.comicalpixel.creeperstarbedwars.Entity.ShopNPC;

import cn.comicalpixel.creeperstarbedwars.Arena.GameData_cfg;
import cn.comicalpixel.creeperstarbedwars.Config.ConfigData;
import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import org.bukkit.Location;

public class ShopNPC_Manager {

    public static void spawn_all_ItemShop() {
        for (String s : GameData_cfg.shopNPC_item_locs) {
            Location loc = ConfigUtils.getLocation(s);
            ItemShop_NPC.spawn(loc);
        }
    }

    public static void spawn_all_UpdateShop() {
        for (String s : GameData_cfg.shopNPC_team_locs) {
            Location loc = ConfigUtils.getLocation(s);
            TeamShop_NPC.spawn(loc);
        }
    }

}
