package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.content.LightningEffek;
import cn.chloeprime.aaa_particles_world.common.internal.EffekTicketHolder;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.client.renderer.entity.state.LightningBoltRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBoltRenderer.class)
public class MixinLightningBoltRenderer {
    @Inject(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/LightningBoltRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
            at = @At("HEAD"), cancellable = true)
    private void hideVanillaLightning26_1(LightningBoltRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo ci) {
        if (LightningEffek.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(
            method = "extractRenderState(Lnet/minecraft/world/entity/LightningBolt;Lnet/minecraft/client/renderer/entity/state/LightningBoltRenderState;F)V",
            at = @At("TAIL"))
    private void disableVanillaLightningModelAndPlayEffek(LightningBolt bolt, LightningBoltRenderState state, float partialTicks, CallbackInfo ci) {
        if (!LightningEffek.isEnabled()) {
            return;
        }
        if (((EffekTicketHolder) bolt).aaaParticles$getEffekTicket()) {
            LightningEffek.playLightningEffek(bolt);
        }
    }
}
