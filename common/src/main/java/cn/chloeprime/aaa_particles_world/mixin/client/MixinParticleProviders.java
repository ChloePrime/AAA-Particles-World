package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.content.ExplosionEffek;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.HugeExplosionSeedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MixinParticleProviders {
    @Mixin(HugeExplosionParticle.Provider.class)
    public static class Explosion {
        @Inject(
                method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;",
                at = @At("HEAD"),
                cancellable = true)
        private void replaceWithEffekParticle(SimpleParticleType simpleParticleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, CallbackInfoReturnable<Particle> cir) {
            if (ExplosionEffek.isEnabled()) {
                var wrapped = ExplosionEffek.createParticleWrapper(
                        ExplosionEffek.Type.SMALL, level,
                        x, y, z,
                        dx, dy, dz,
                        ExplosionEffek.SMALL_VANILLA_EXPLOSION_SIZE);
                cir.setReturnValue(wrapped);
            }
        }
    }

    @Mixin(HugeExplosionSeedParticle.Provider.class)
    public static class LargeExplosionEmitter {
        @Inject(
                method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;",
                at = @At("HEAD"),
                cancellable = true)
        private void replaceWithEffekParticle(SimpleParticleType simpleParticleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, CallbackInfoReturnable<Particle> cir) {
            if (ExplosionEffek.isEnabled()) {
                var wrapped = ExplosionEffek.createParticleWrapper(
                        ExplosionEffek.Type.BIG, level,
                        x, y, z,
                        dx, dy, dz,
                        ExplosionEffek.LARGE_VANILLA_EXPLOSION_SIZE);
                cir.setReturnValue(wrapped);
            }
        }
    }
}
