package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.TrackingEmitterAlternatives;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleEngine.class)
public class MixinParticleEngine {
    @Inject(at = @At("HEAD"), cancellable = true, method = "createTrackingEmitter(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/particles/ParticleOptions;)V")
    private void onCreatingTrackingEmitters(Entity entity, ParticleOptions particleData, CallbackInfo ci) {
        TrackingEmitterAlternatives.onCreatingTrackingEmitter(entity, particleData, ci::cancel);
    }

    @Inject(at = @At("HEAD"), cancellable = true, method = "createTrackingEmitter(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/particles/ParticleOptions;I)V")
    private void onCreatingTrackingEmitters(Entity entity, ParticleOptions data, int lifetime, CallbackInfo ci) {
        TrackingEmitterAlternatives.onCreatingTrackingEmitter(entity, data, ci::cancel);
    }
}
