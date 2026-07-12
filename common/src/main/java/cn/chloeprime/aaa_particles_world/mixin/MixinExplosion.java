package cn.chloeprime.aaa_particles_world.mixin;

import cn.chloeprime.aaa_particles_world.client.content.ExplosionEffek;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Explosion.class)
public class MixinExplosion {
    @WrapOperation(
            method = "finalizeExplosion",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void betterExplodeParticles(Level level, ParticleOptions vanillaExplosion, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original) {
        var vanilla = (Runnable) () -> original.call(level, vanillaExplosion, x, y, z, dx, dy, dz);
        if (!ExplosionEffek.isEnabled()) {
            vanilla.run();
            return;
        }
        ExplosionEffek.Type type;
        if (vanillaExplosion.getType() == ParticleTypes.EXPLOSION_EMITTER) {
            if (!ExplosionEffek.isReplacingBigExplosion()) {
                vanilla.run();
                return;
            }
            type = ExplosionEffek.Type.BIG;
        } else {
            if (!ExplosionEffek.isReplacingSmallExplosion()) {
                vanilla.run();
                return;
            }
            type = ExplosionEffek.Type.SMALL;
        }
        ExplosionEffek.playExplosion(type, level, x, y, z, radius);
    }

    @Shadow public float radius;
}
