package cn.chloeprime.aaa_particles_world.mixin.client;

import cn.chloeprime.aaa_particles_world.client.content.LootBeamEffek;
import cn.chloeprime.aaa_particles_world.client.content.LootDropSound;
import cn.chloeprime.aaa_particles_world.common.internal.EffekTicketHolder;
import cn.chloeprime.aaa_particles_world.util.BooleanField;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class MixinItemEntity extends Entity {
    @Inject(
            method = "tick",
            at = @At("HEAD"))
    private void getTicketAndPlayEffek(CallbackInfo ci) {
        if (!level().isClientSide || !LootBeamEffek.isEnabled()) {
            return;
        }
        var self = (ItemEntity) (Object) this;
        if (((EffekTicketHolder) self).aaaParticles$getEffekTicket()) {
            LootBeamEffek.playLootBeamEffek(self);
        }
    }

    @Inject(
            method = "tick",
            at = @At("HEAD"))
    private void tryPlaySound(CallbackInfo ci) {
        if (!level().isClientSide || !LootDropSound.isEnabled()) {
            return;
        }
        var self = (ItemEntity) (Object) this;
        LootDropSound.tryPlay(self, aaa_pw$hasPlayedLootSoundField);
    }

    private @Unique boolean aaa_pw$hasPlayedLootSound;
    private @Unique final BooleanField aaa_pw$hasPlayedLootSoundField = new BooleanField(
            () -> aaa_pw$hasPlayedLootSound,
            (value) -> aaa_pw$hasPlayedLootSound = value
    );

    public MixinItemEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
}
