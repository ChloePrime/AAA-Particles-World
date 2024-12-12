package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.content.LightningEffek;
import cn.chloeprime.aaa_particles_world.common.internal.EffekLightningBolt;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBoltRenderer.class)
public class MixinLightningBoltRenderer {
    @Inject(
            method = "render(Lnet/minecraft/world/entity/LightningBolt;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD"), cancellable = true
    )
    private void disableVanillaLightningModelAndPlayEffek(LightningBolt bolt, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (!LightningEffek.isEnabled()) {
            return;
        }
        if (((EffekLightningBolt) bolt).aaaParticles$getEffekTicket()) {
            LightningEffek.playLightningEffek(bolt);
        }
        ci.cancel();
    }
}
