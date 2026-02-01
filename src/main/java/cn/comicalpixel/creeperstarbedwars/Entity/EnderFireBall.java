package cn.comicalpixel.creeperstarbedwars.Entity;

import cn.comicalpixel.creeperstarbedwars.Utils.ConfigUtils;
import cn.comicalpixel.creeperstarbedwars.Utils.NMSTitleUntils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.event.CraftEventFactory;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnderFireBall extends EntityLargeFireball {

    private int e = -1;
    private int f = -1;
    private int g = -1;
    private Block h;
    private boolean i;
    public EntityLiving shooter;
    private int ar;
    private int as;
    public double dirX;
    public double dirY;
    public double dirZ;
    public float bukkitYield = 1.0F;
    public boolean isIncendiary = false;
    public int fireTick = 0;
    public Vector direction = null;

    public EnderFireBall(World world) {
        super(world);
        this.setSize(1.0F, 1.0F);
    }

    protected void h() {
    }

    public EnderFireBall(World world, double d0, double d1, double d2, double d3, double d4, double d5, Vector vector) {
        super(world);
        this.setSize(1.0F, 1.0F);
        this.setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
        this.setPosition(d0, d1, d2);
        double d6 = (double)MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
        this.dirX = d3 / d6 * 0.1;
        this.dirY = d4 / d6 * 0.1;
        this.dirZ = d5 / d6 * 0.1;
        direction = vector.clone();
        dirX = direction.getX() * 0.1;
        dirY = direction.getY() * 0.1;
        dirZ = direction.getZ() * 0.1;
        this.maxFireTicks = 0;
        noFireTask();
    }

    public EnderFireBall(World world, EntityLiving entityliving, double d0, double d1, double d2, Vector vector) {
        super(world);
        this.shooter = entityliving;
        this.projectileSource = (LivingEntity)entityliving.getBukkitEntity();
        this.setSize(0.2F, 0.2F);
        this.setPositionRotation(entityliving.locX, entityliving.locY, entityliving.locZ, entityliving.yaw, entityliving.pitch);
        this.setPosition(this.locX, this.locY, this.locZ);
        this.motX = this.motY = this.motZ = 0.0;
        this.setDirection(d0, d1, d2);
        direction = vector.clone();
        dirX = direction.getX() * 0.1;
        dirY = direction.getY() * 0.1;
        dirZ = direction.getZ() * 0.1;
        this.maxFireTicks = 0;
        noFireTask();
    }

    public void noFireTask() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            if (!this.world.isClientSide && (this.shooter != null && this.shooter.dead || !this.world.isLoaded(new BlockPosition(this)))) {
                scheduler.shutdown();
            } else {
                this.setOnFire(-21);
                this.setOnFire(-21);
                this.setOnFire(-21);
                this.setOnFire(-21);
                this.maxFireTicks = -21;
            }
        }, 10, TimeUnit.NANOSECONDS);
    }

    public void setDirection(double d0, double d1, double d2) {
        d0 += this.random.nextGaussian() * 0.1;
        d1 += this.random.nextGaussian() * 0.1;
        d2 += this.random.nextGaussian() * 0.1;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        this.dirX = d0 / d3 * 0.1;
        this.dirY = d1 / d3 * 0.1;
        this.dirZ = d2 / d3 * 0.1;

    }

    public void t_() {
        if (!this.world.isClientSide && (this.shooter != null && this.shooter.dead || !this.world.isLoaded(new BlockPosition(this)))) {
            this.die();
        } else {
            super.t_();
            this.setOnFire(0);
            if (this.i) {
                if (this.world.getType(new BlockPosition(this.e, this.f, this.g)).getBlock() == this.h) {
                    ++this.ar;
                    if (this.ar == 600) {
                        this.die();
                    }

                    return;
                }

                this.i = false;
                this.motX *= (double)(this.random.nextFloat() * 0.1F);
                this.motY *= (double)(this.random.nextFloat() * 0.1F);
                this.motZ *= (double)(this.random.nextFloat() * 0.1F);
                this.ar = 0;
                this.as = 0;
            } else {
                ++this.as;
            }

            Vec3D vec3d = new Vec3D(this.locX, this.locY, this.locZ);
            Vec3D vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
            MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, vec3d1);
            vec3d = new Vec3D(this.locX, this.locY, this.locZ);
            vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
            if (movingobjectposition != null) {
                vec3d1 = new Vec3D(movingobjectposition.pos.a, movingobjectposition.pos.b, movingobjectposition.pos.c);
            }

            Entity entity = null;
            List list = this.world.getEntities(this, this.getBoundingBox().a(this.motX, this.motY, this.motZ).grow(1.0, 1.0, 1.0));
            double d0 = 0.0;

            for(int i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);
                if (entity1.ad() && (!entity1.k(this.shooter) || this.as >= 25)) {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow((double)f, (double)f, (double)f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.a(vec3d, vec3d1);
                    if (movingobjectposition1 != null) {
                        double d1 = vec3d.distanceSquared(movingobjectposition1.pos);
                        if (d1 < d0 || d0 == 0.0) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null) {
                this.a(movingobjectposition);
                if (this.dead) {
                    CraftEventFactory.callProjectileHitEvent(this);
                }
            }

            this.locX += this.motX;
            this.locY += this.motY;
            this.locZ += this.motZ;
            float f1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
            this.yaw = (float)(MathHelper.b(this.motZ, this.motX) * 180.0 / 3.1415927410125732) + 90.0F;

            for(this.pitch = (float)(MathHelper.b((double)f1, this.motY) * 180.0 / 3.1415927410125732) - 90.0F; this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F) {
            }

            while(this.pitch - this.lastPitch >= 180.0F) {
                this.lastPitch += 360.0F;
            }

            while(this.yaw - this.lastYaw < -180.0F) {
                this.lastYaw -= 360.0F;
            }

            while(this.yaw - this.lastYaw >= 180.0F) {
                this.lastYaw += 360.0F;
            }

            this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
            this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
            float f2 = this.j();
            if (this.V()) {
                for(int j = 0; j < 4; ++j) {
                    float f3 = 0.25F;
                    this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX - this.motX * (double)f3, this.locY - this.motY * (double)f3, this.locZ - this.motZ * (double)f3, this.motX, this.motY, this.motZ, new int[0]);
                }

                f2 = 0.8F;
            }

            this.motX += this.dirX;
            this.motY += this.dirY;
            this.motZ += this.dirZ;
            this.motX *= (double)f2;
            this.motY *= (double)f2;
            this.motZ *= (double)f2;
            this.world.addParticle(EnumParticle.SMOKE_NORMAL, this.locX, this.locY + 0.5, this.locZ, 0.0, 0.0, 0.0, new int[0]);
            this.setPosition(this.locX, this.locY, this.locZ);
            this.setOnFire(0);
            dirX = direction.getX() * 0.1;
            dirY = direction.getY() * 0.1;
            dirZ = direction.getZ() * 0.1;
        }

    }

    protected float j() {
        return 0.95F;
    }

//    protected void a(MovingObjectPosition var1) {
//
//    }
//
//    public void b(NBTTagCompound nbttagcompound) {
//        nbttagcompound.setShort("xTile", (short)this.e);
//        nbttagcompound.setShort("yTile", (short)this.f);
//        nbttagcompound.setShort("zTile", (short)this.g);
//        MinecraftKey minecraftkey = (MinecraftKey)Block.REGISTRY.c(this.h);
//        nbttagcompound.setString("inTile", minecraftkey == null ? "" : minecraftkey.toString());
//        nbttagcompound.setByte("inGround", (byte)(this.i ? 1 : 0));
//        nbttagcompound.set("power", this.a((double[])(new double[]{this.dirX, this.dirY, this.dirZ})));
//        nbttagcompound.set("direction", this.a((double[])(new double[]{this.motX, this.motY, this.motZ})));
//    }
//
//    public void a(NBTTagCompound nbttagcompound) {
//        this.e = nbttagcompound.getShort("xTile");
//        this.f = nbttagcompound.getShort("yTile");
//        this.g = nbttagcompound.getShort("zTile");
//        if (nbttagcompound.hasKeyOfType("inTile", 8)) {
//            this.h = Block.getByName(nbttagcompound.getString("inTile"));
//        } else {
//            this.h = Block.getById(nbttagcompound.getByte("inTile") & 255);
//        }
//
//        this.i = nbttagcompound.getByte("inGround") == 1;
//        NBTTagList nbttaglist;
//        if (nbttagcompound.hasKeyOfType("power", 9)) {
//            nbttaglist = nbttagcompound.getList("power", 6);
//            this.dirX = nbttaglist.d(0);
//            this.dirY = nbttaglist.d(1);
//            this.dirZ = nbttaglist.d(2);
//        } else if (nbttagcompound.hasKeyOfType("direction", 9)) {
//            nbttaglist = nbttagcompound.getList("direction", 6);
//            this.motX = nbttaglist.d(0);
//            this.motY = nbttaglist.d(1);
//            this.motZ = nbttaglist.d(2);
//        } else {
//            this.die();
//        }
//
//    }

    public boolean ad() {
        return true;
    }

    public float ao() {
        return 1.0F;
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else {
            this.ac();
            if (damagesource.getEntity() != null) {
                if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, (double)f)) {
                    return false;
                } else {
                    Vec3D vec3d = damagesource.getEntity().ap();
                    if (vec3d != null) {
                        this.motX = vec3d.a;
                        this.motY = vec3d.b;
                        this.motZ = vec3d.c;
                        this.dirX = this.motX * 0.1;
                        this.dirY = this.motY * 0.1;
                        this.dirZ = this.motZ * 0.1;
                    }

                    if (damagesource.getEntity() instanceof EntityLiving) {
                        this.shooter = (EntityLiving)damagesource.getEntity();
                        this.projectileSource = (ProjectileSource)this.shooter.getBukkitEntity();
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public float c(float f) {
        return 1.0F;
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isClientSide) {
            if (movingobjectposition.entity != null) {
                movingobjectposition.entity.damageEntity(DamageSource.fireball(this, this.shooter), 6.0F);
                this.a(this.shooter, movingobjectposition.entity);
            }

            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
            ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive) CraftEntity.getEntity(this.world.getServer(), this));
            this.world.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire(), flag);
            }

            this.die();
        }

    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("ExplosionPower", this.yield);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("ExplosionPower", 99)) {
            this.bukkitYield = (float)(this.yield = nbttagcompound.getInt("ExplosionPower"));
        }

    }

}
