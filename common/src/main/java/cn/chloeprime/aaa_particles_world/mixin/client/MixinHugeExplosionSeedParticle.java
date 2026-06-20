package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.util.EffekLimiter;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionSeedParticle;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.core.particles.ParticleOptions;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @since 2.0.1
 */
@Mixin(HugeExplosionSeedParticle.class)
public class MixinHugeExplosionSeedParticle extends NoRenderParticle {
    @WrapOperation(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void doNotFallbackToAaaSmallExplosionWhenLargeExplosionHitsQuotaLimit(
            ClientLevel level, ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, Operation<Void> original
    ) {
        var stack = EffekLimiter.EXPLOSION_EMITTER_MERCY_CALL_STACK;
        try {
            var mercy = EffekLimiter.EXPLOSION_EMITTER_MERCY_LIST.contains(this);
            stack.push(mercy);
            original.call(level, particleData, x, y, z, xSpeed, ySpeed, zSpeed);
        } finally {
            stack.poll();
        }
    }

    @ApiStatus.Internal
    public MixinHugeExplosionSeedParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
    }
}
