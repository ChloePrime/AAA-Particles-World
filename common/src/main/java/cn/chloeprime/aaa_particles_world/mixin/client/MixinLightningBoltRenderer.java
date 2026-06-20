package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.content.LightningEffek;
import cn.chloeprime.aaa_particles_world.common.internal.EffekTicketHolder;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(LightningBoltRenderer.class)
public class MixinLightningBoltRenderer {
    /**
     * @since 2.0.1
     */
    private final @Unique Set<Entity> aaa_particles_world$forceVanilla = Collections.newSetFromMap(new WeakHashMap<>());

    @Inject(
            method = "render(Lnet/minecraft/world/entity/LightningBolt;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD"), cancellable = true)
    private void disableVanillaLightningModelAndPlayEffek(LightningBolt bolt, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (aaa_particles_world$forceVanilla.contains(bolt) || !LightningEffek.isEnabled()) {
            return;
        }
        if (((EffekTicketHolder) bolt).aaaParticles$getEffekTicket()) {
            if (LightningEffek.hasRemainingQuota()) {
                LightningEffek.playLightningEffek(bolt);
            } else {
                aaa_particles_world$forceVanilla.add(bolt);
                return;
            }
        }
        ci.cancel();
    }
}
