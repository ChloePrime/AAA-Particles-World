package cn.chloeprime.aaa_particles_world.client.mc26_1;

import cn.chloeprime.aaa_particles_world.mixin.client.mc26_1.ItemStackRenderStateAccessor;
import cn.chloeprime.aaa_particles_world.mixin.client.mc26_1.ItemStackRenderStateLayerRenderStateAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.state.ItemClusterRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

public final class ItemRenderHelper26_1 {
    public static Vector3fc getItemScale(ItemStack stack, @Nullable Level level, @Nullable ItemOwner owner) {
        return getItemScale(stack, level, owner, ItemClusterRenderState.getSeedForItemStack(stack));
    }

    public static Vector3fc getItemScale(ItemStack stack, @Nullable Level level, @Nullable ItemOwner owner, int seed) {
        var state = new ItemStackRenderState();
        Minecraft.getInstance().getItemModelResolver().updateForTopItem(state, stack, ItemDisplayContext.GROUND, level, owner, seed);
        return ((ItemStackRenderStateLayerRenderStateAccessor) ((ItemStackRenderStateAccessor) state).callFirstLayer()).getItemTransform().scale();
    }

    private ItemRenderHelper26_1() {
    }
}
