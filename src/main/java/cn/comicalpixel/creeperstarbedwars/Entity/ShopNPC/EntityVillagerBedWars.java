package cn.comicalpixel.creeperstarbedwars.Entity.ShopNPC;

import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

public class EntityVillagerBedWars extends EntityVillager {
    public EntityVillagerBedWars(World world) {
        super(world);
        this.goalSelector = new PathfinderGoalSelector(world.methodProfiler);
        this.targetSelector = new PathfinderGoalSelector(world.methodProfiler);
    }


    protected String z() {
        return "";
    }

    protected String bo() {
        return "";
    }

    protected String bp() {
        return "";
    }

    public void setPositionRotation(float yaw, float pitch) {
    }

//    public static double look_reaius;
//
//    @Override
//    public void m() {
//        for (Player p : PlayerUtils.getNearbyPlayers(this.getBukkitEntity().getLocation(), 5.0)) {
//            if (GamePlayers.game_players.contains(p) && p.getGameMode() != GameMode.SPECTATOR) {
//                Location loc = p.getLocation();
//                this.getControllerLook().a(loc.getX(),loc.getY()+1.5,loc.getZ(),165.0F,180.0F);
//            }
//        }
//        super.m();
//    }
//
    //阻止移动
    @Override
    public void move(double d0, double d1, double d2) {

    }





}
