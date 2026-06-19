package cn.chloeprime.aaa_particles_world.mixin.client.mc26_1;

import cn.chloeprime.aaa_particles_world.client.content.mc26_1.ExplosionEffek26_1;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPacketListener.class)
public class MixinClientPacketListener26_1 {
    @WrapOperation(
            method = "handleExplosion",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;trackExplosionEffects(Lnet/minecraft/world/phys/Vec3;FILnet/minecraft/util/random/WeightedList;)V"))
    private void removePoof(
            ClientLevel level, Vec3 center, float radius, int blockCount, WeightedList<ExplosionParticleInfo> blockParticles, Operation<Void> original,
            ClientboundExplodePacket packet
    ) {
        // Small
        if (packet.explosionParticle() == ParticleTypes.EXPLOSION && ExplosionEffek26_1.isRemovingPoofForSmallExplosion()) {
            return;
        }
        // Big
        if (packet.explosionParticle() == ParticleTypes.EXPLOSION_EMITTER && ExplosionEffek26_1.isRemovingPoofForLargeExplosion()) {
            return;
        }
        // Mercy
        original.call(level, center, radius, blockCount, blockParticles);
    }
}
