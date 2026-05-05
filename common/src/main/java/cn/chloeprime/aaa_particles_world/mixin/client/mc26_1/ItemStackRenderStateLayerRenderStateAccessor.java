package cn.chloeprime.aaa_particles_world.mixin.client.mc26_1;

import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.resources.model.cuboid.ItemTransform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemStackRenderState.LayerRenderState.class)
public interface ItemStackRenderStateLayerRenderStateAccessor {
    @Accessor ItemTransform getItemTransform();
}
