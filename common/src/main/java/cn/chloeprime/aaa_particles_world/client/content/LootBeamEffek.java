package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.AAAParticlesWorldMod;
import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import cn.chloeprime.aaa_particles_world.client.ClientConfig;
import mod.chloeprime.aaaparticles.api.client.effekseer.ParticleEmitter;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import mod.chloeprime.aaaparticles.client.registry.EffectRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

public class LootBeamEffek {
    public static final ResourceLocation EFFEK_ID = AAAParticlesWorldMod.loc("loot_beam");
    public static final boolean FLOATS_WITH_ITEM = false;

    public static boolean isEnabled() {
        return ClientConfig.ENABLE_LOOT_BEAM.get() && AAAParticlesWorldClient.isEffekEnabled();
    }

    public static void playLootBeamEffek(ItemEntity entity) {
        var item = entity.getItem();
        var color = getColor(item).orElse(null);
        if (color == null) {
            return;
        }
        var itemModel = Minecraft.getInstance().getItemRenderer().getModel(item, entity.level(), null, entity.getId());
        var heightScale = itemModel.getTransforms().getTransform(ItemDisplayContext.GROUND).scale.y();

        var effekId = EFFEK_ID;
        var emitter = AAAParticlesWorldMod.loc(String.valueOf(entity.getId()));
        var pei = ParticleEmitterInfo.create(entity.level(), effekId, emitter)
                .bindOnEntity(entity)
                .position(0, 0.1125F + 0.25F * (heightScale + 0.5F), 0)
                .parameter(0, color.getRed() / 255F)
                .parameter(1, color.getGreen() / 255F)
                .parameter(2, color.getBlue() / 255F)
                .parameter(3, color.getAlpha() / 255F);
        AAALevel.addParticle(entity.level(), pei);
        if (FLOATS_WITH_ITEM) {
            Optional.ofNullable(EffectRegistry.get(effekId))
                    .flatMap(effek -> effek.getNamedEmitter(ParticleEmitter.Type.WORLD, emitter))
                    .ifPresent(em1 -> em1.addPreDrawCallback((em, partial) -> {
                        if (entity.isRemoved() || !em.exists()) {
                            em.stop();
                            return;
                        }
                        var waveHeight = (Mth.sin((entity.getAge() + partial) / 10 + entity.bobOffs) + 1) * 0.1F;
                        em.setPosition(
                                (float) Mth.lerp(partial, entity.xOld, entity.getX()) + 0,
                                (float) Mth.lerp(partial, entity.yOld, entity.getY()) + waveHeight + 0.0125F + 0.25F * (heightScale + 0.5F),
                                (float) Mth.lerp(partial, entity.zOld, entity.getZ()) + 0
                        );
                    }));
        }
    }

    public static Optional<Color> getColor(ItemStack item) {
        var name = Optional.ofNullable(Minecraft.getInstance().player)
                .stream()
                .flatMap(player -> item.getTooltipLines(player, TooltipFlag.NORMAL).stream())
                .findFirst();

        return name
                .map(LootBeamEffek::getRealColor)
                .filter(color -> !Objects.equals(color, ChatFormatting.WHITE.getColor()))
                .map(Color::new);
    }

    private static @Nullable Integer getRealColor(Component line) {
        if (ComponentContents.EMPTY.equals(line.getContents())) {
            var extra = line.getSiblings();
            if (extra.isEmpty()) {
                return null;
            } else {
                // 如果子段落没有样式（返回null），
                // 那么使用父级的样式（让代码走下去）
                var color = getRealColor(extra.get(0));
                if (color != null) {
                    return color;
                }
            }
        }

        var style = line.getStyle();
        return style == Style.EMPTY
                ? null
                : Optional.ofNullable(style.getColor()).map(TextColor::getValue).orElse(null);
    }
}
