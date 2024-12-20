package cn.chloeprime.aaa_particles_world.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.chloeprime.aaaparticles.api.client.effekseer.ParticleEmitter;
import mod.chloeprime.aaaparticles.client.registry.EffectDefinition;
import mod.chloeprime.aaaparticles.client.registry.EffectRegistry;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EffekParticle extends Particle {
    private final @Nullable ParticleEmitter emitter;
    private final boolean initialized;

    public EffekParticle(ClientLevel level, ResourceLocation effek, double x, double y, double z, double dx, double dy, double dz) {
        super(level, x, y, z, dx, dy, dz);
        this.hasPhysics = false;
        this.emitter = Optional.ofNullable(EffectRegistry.get(effek))
                .map(EffectDefinition::play)
                .orElse(null);
        this.initialized = true;
    }

    public Optional<ParticleEmitter> getEmitter() {
        return Optional.ofNullable(emitter);
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partial) {
        if (emitter != null) {
            var curX = (float)(Mth.lerp(partial, this.xo, this.x));
            var curY = (float)(Mth.lerp(partial, this.yo, this.y));
            var curZ = (float)(Mth.lerp(partial, this.zo, this.z));
            emitter.setPosition(curX, curY, curZ);
        }
    }

    @Override
    public void tick() {
        if (initialized) {
            if (emitter == null || !emitter.exists()) {
                remove();
            }
        }
        super.tick();
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }
}
