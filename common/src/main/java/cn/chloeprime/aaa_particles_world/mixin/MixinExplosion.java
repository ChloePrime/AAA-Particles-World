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
    // 这里的替换拥有精确的爆炸大小信息，
    // 动态调整爆炸大小是替换 Provider 无法实现的，
    // 所以请勿删除该方法！
    @WrapOperation(
            method = "handleExplosion",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void betterExplodeParticles(
            ClientLevel level, ParticleOptions vanillaExplosion, double x, double y, double z, double dx, double dy, double dz, Operation<Void> original,
            ClientboundExplodePacket packet
    ) {
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
        // 将 radius 应用到特效时偏小
        // 所以这里放大一下 :)
        var visualRadius = packet.radius() * 1.75F;
        ExplosionEffek.playExplosion(type, level, x, y, z, visualRadius);
    }
}
