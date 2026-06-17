package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.content.ExplosionEffek;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.HugeExplosionSeedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
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
        if (ExplosionEffek.isReplacingSmallExplosion()) {
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
    @Inject(
            method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;",
            at = @At("HEAD"),
            cancellable = true)
    private void replaceWithEffekParticle(SimpleParticleType simpleParticleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz, CallbackInfoReturnable<Particle> cir) {
        if (ExplosionEffek.isReplacingBigExplosion()) {
            var wrapped = ExplosionEffek.createParticleWrapper(
                    ExplosionEffek.Type.BIG, level,
                    x, y, z,
                    dx, dy, dz,
                    ExplosionEffek.LARGE_VANILLA_EXPLOSION_SIZE);
            cir.setReturnValue(wrapped);
        }
    }
}
