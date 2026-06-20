package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.content.ExplosionEffek;
import cn.chloeprime.aaa_particles_world.util.EffekLimiter;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.HugeExplosionSeedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
public class MixinParticleProviders {
}

@Mixin(HugeExplosionParticle.Provider.class)
class MixinHugeExplosionParticleProvider {
    @Inject(
            method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;",
            at = @At("HEAD"),
            cancellable = true)
    private void replaceWithEffekParticle(SimpleParticleType simpleParticleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, CallbackInfoReturnable<Particle> cir) {
        if (EffekLimiter.EXPLOSION_EMITTER_MERCY_CALL_STACK.peek() == Boolean.TRUE) {
            return;
        }
        if (ExplosionEffek.isReplacingSmallExplosion() && ExplosionEffek.hasEnoughQuotaForSmall()) {
            var isDragon = ExplosionEffek.checkNearbyDyingEnderDragon(level, x, y, z);
            var type = isDragon
                    ? ExplosionEffek.Type.DRAGON_SMALL
                    : ExplosionEffek.Type.SMALL;
            var size = isDragon
                    ? ExplosionEffek.SMALL_DRAGON_EXPLOSION_SIZE
                    : ExplosionEffek.SMALL_VANILLA_EXPLOSION_SIZE;
            var wrapped = ExplosionEffek.createParticleWrapper(
                    type, level,
                    x, y, z,
                    dx, dy, dz,
                    size);
            cir.setReturnValue(wrapped);
        }
    }
}

@Mixin(HugeExplosionSeedParticle.Provider.class)
class MixinHugeExplosionSeedParticleProvider {
    /**
     * @since 2.0.1
     */
    private @Unique boolean aaa_particles_world$isOutOfQuotaCache;

    @Inject(
            method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;",
            at = @At("HEAD"),
            cancellable = true)
    private void replaceWithEffekParticle(SimpleParticleType simpleParticleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, CallbackInfoReturnable<Particle> cir) {
        if (ExplosionEffek.isReplacingBigExplosion()) {
            var isOutOfQuota = aaa_particles_world$isOutOfQuotaCache = !ExplosionEffek.hasEnoughQuotaForLarge();
            if (isOutOfQuota) {
                return;
            }
            var wrapped = ExplosionEffek.createParticleWrapper(
                    ExplosionEffek.Type.BIG, level,
                    x, y, z,
                    dx, dy, dz,
                    ExplosionEffek.LARGE_VANILLA_EXPLOSION_SIZE);
            cir.setReturnValue(wrapped);
        } else {
            aaa_particles_world$isOutOfQuotaCache = false;
        }
    }

    /**
     * @since 2.0.1
     */
    @Inject(
            method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;",
            at = @At("TAIL"))
    private void doNotFallbackToAaaSmallExplosionWhenLargeExplosionHitsQuotaLimit(
            SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
            CallbackInfoReturnable<Particle> cir
    ) {
        if (aaa_particles_world$isOutOfQuotaCache) {
            EffekLimiter.EXPLOSION_EMITTER_MERCY_LIST.add(cir.getReturnValue());
        }
    }
}
