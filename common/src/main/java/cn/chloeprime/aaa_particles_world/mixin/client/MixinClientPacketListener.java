package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.content.CritEffek;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class MixinClientPacketListener {
    @Inject(
            method = "handleAnimate", cancellable = true,
            at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/network/protocol/PacketUtils;ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/util/thread/BlockableEventLoop;)V"))
    private void onCriticalHit(ClientboundAnimatePacket packet, CallbackInfo ci) {
        var entity = this.level.getEntity(packet.getId());
        if (entity == null) {
            return;
        }
        switch (packet.getId()) {
            case 4, 5 -> {
                if (!CritEffek.isEnabled()) {
                    return;
                }
                CritEffek.playCritEffek(entity);
            }
            default -> {
                return;
            }
        }
        ci.cancel();
    }

    @Shadow private ClientLevel level;
}
