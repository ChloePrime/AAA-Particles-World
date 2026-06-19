package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import mod.chloeprime.aaaparticles.api.client.util.VanillaParticleProxy;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class ExplosionEffek {
    public record Type(
            Identifier effekId,
            float intrinsicRadius
    ) {
        public static final Type BIG = new Type(BIG_EXPLOSION_EFFEK, 8);
        public static final Type SMALL = new Type(SMALL_EXPLOSION_EFFEK, 4);
        public static final Type DRAGON_SMALL = new Type(SMALL_EXPLOSION_EFFEK_BLUE, 4);
    }

    public static final float LARGE_VANILLA_EXPLOSION_SIZE = 5;
    public static final float SMALL_VANILLA_EXPLOSION_SIZE = 1;
    public static final float SMALL_DRAGON_EXPLOSION_SIZE = 2.5F;

    public static final Identifier BIG_EXPLOSION_EFFEK = AAAParticlesWorldMod.loc("explosion");
    public static final Identifier SMALL_EXPLOSION_EFFEK = AAAParticlesWorldMod.loc("explosion_mini/yellow");
    public static final Identifier SMALL_EXPLOSION_EFFEK_BLUE = AAAParticlesWorldMod.loc("explosion_mini/blue");

    public static boolean isEnabled() {
        return (ClientConfig.ENABLE_SMALL_EXPLOSION.get() || ClientConfig.ENABLE_BIG_EXPLOSION.get()) && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static boolean isReplacingSmallExplosion() {
        return ClientConfig.ENABLE_SMALL_EXPLOSION.get() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static boolean isReplacingBigExplosion() {
        return ClientConfig.ENABLE_BIG_EXPLOSION.get() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static void playExplosion(Type type, Level level, double x, double y, double z, float radius) {
        var info = ParticleEmitterInfo.create(level, type.effekId())
                .position(x, y, z)
                .scale(radius / type.intrinsicRadius());
        AAALevel.addParticle(level, true, info);
    }

    @SuppressWarnings("UnstableApiUsage")
    public static Particle createParticleWrapper(
            Type type, ClientLevel level,
            double x, double y, double z,
            double dx, double dy, double dz,
            float radius
    ) {
        var scale = radius / type.intrinsicRadius();
        var particle = new VanillaParticleProxy(type.effekId(), level, x, y, z, dx, dy, dz);
        particle.getEmitter().thenAccept(emitter -> emitter.setScale(scale, scale, scale));
        return particle;
    }

    public static boolean checkNearbyDyingEnderDragon(Level level, double x, double y, double z) {
        var radius = 10;
        var area = new AABB(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        var buffer = GET_ENTITY_BUFFER.get();
        try {
            level.getEntities(EntityTypeTest.forClass(EnderDragon.class), area, LivingEntity::isDeadOrDying, buffer);
            return !buffer.isEmpty();
        } finally {
            buffer.clear();
        }
    }

    private static final ThreadLocal<List<Entity>> GET_ENTITY_BUFFER = ThreadLocal.withInitial(ArrayList::new);
}
