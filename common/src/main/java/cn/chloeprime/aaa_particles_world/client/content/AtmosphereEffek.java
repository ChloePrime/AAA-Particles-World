package cn.chloeprime.aaa_particles_world.client.content;

import cn.chloeprime.aaa_particles_world.client.AAAParticlesWorldClient;
import mod.chloeprime.aaaparticles.api.client.EffectDefinition;
import mod.chloeprime.aaaparticles.api.client.EffectHolder;
import mod.chloeprime.aaaparticles.api.client.EffectRegistry;
import mod.chloeprime.aaaparticles.api.client.effekseer.ParticleEmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class AtmosphereEffek {
    private final Identifier effekId;

    protected AtmosphereEffek(Identifier effekId) {
        this.effekId = effekId;
    }

    public boolean isEnabled(Minecraft mc) {
        return AAAParticlesWorldClient.isEffekEnabled();
    }

    public Identifier getEffekId(Minecraft mc) {
        return this.effekId;
    }

    public CompletableFuture<ParticleEmitter> play(Minecraft mc) {
        var player = mc.player;
        if (player == null) {
            return CompletableFuture.completedFuture(null);
        }
        return Optional.ofNullable(EffectRegistry.get(getEffekId(mc)))
                .map(EffectHolder::load)
                .map(future -> future.thenApply(opt -> opt
                        .map(EffectDefinition::play)
                        .map(emitter -> initEmitter(emitter, player))
                        .orElse(null)))
                .orElseGet(() -> CompletableFuture.completedFuture(null));
    }

    private ParticleEmitter initEmitter(ParticleEmitter emitter, Entity center) {
        emitter.setPosition((float) center.getX(), (float) center.getY(), (float) center.getZ());
        return emitter;
    }

    public int getFadeOutTicks() {
        return 20;
    }
}
