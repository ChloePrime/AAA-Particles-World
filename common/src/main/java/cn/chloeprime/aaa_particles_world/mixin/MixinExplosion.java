package cn.chloeprime.aaa_particles_world.mixin;

import cn.chloeprime.aaa_particles_world.client.content.ExplosionEffek;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPacketListener.class)
public class MixinExplosion {
    @WrapOperation(
            method = "handleExplosion",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void betterExplodeParticles(
            ClientLevel level, ParticleOptions particle, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original,
            ClientboundExplodePacket packet) {
        if (!ExplosionEffek.isEnabled()) {
            original.call(level, particle, x, y, z, dx, dy, dz);
            return;
        }
        ExplosionEffek.Type type;
        if (particle == ParticleTypes.EXPLOSION_EMITTER) {
            type = ExplosionEffek.Type.BIG;
        } else {
            type = ExplosionEffek.Type.SMALL;
        }
        ExplosionEffek.playExplosion(type, level, x, y, z, packet.radius());
    }
}
