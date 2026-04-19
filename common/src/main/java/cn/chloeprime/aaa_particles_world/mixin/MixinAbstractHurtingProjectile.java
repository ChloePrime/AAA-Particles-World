package cn.chloeprime.aaa_particles_world.mixin;

import cn.chloeprime.aaa_particles_world.client.content.RocketTrailEffek;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(AbstractHurtingProjectile.class)
public abstract class MixinAbstractHurtingProjectile extends Entity {
    @Override
    @Intrinsic
    public boolean isOnFire() {
        return super.isOnFire();
    }

    @Dynamic
    @ModifyReturnValue(method = {"isOnFire", "m_6060_", "method_5809"}, at = @At("RETURN"))
    public boolean isNotOnFireForEffekFireballs(boolean original) {
        if (level().isClientSide()) {
            if (RocketTrailEffek.isEnabledFor(this) && RocketTrailEffek.isEnabled()) {
                return false;
            }
        }
        return original;
    }

    @WrapOperation(
            method = "tick",
            at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractHurtingProjectile;getTrailParticle()Lnet/minecraft/core/particles/ParticleOptions;")))
    private void disableTrailParticleForEffekedFireballs(
            Level receiver, ParticleOptions particleData,
            double x, double y, double z,
            double dx, double dy, double dz,
            Operation<Void> original
    ) {
        if (receiver.isClientSide()) {
            if (RocketTrailEffek.isEnabledFor(this) && RocketTrailEffek.isEnabled()) {
                return;
            }
        }
        original.call(receiver, particleData, x, y, z, dx, dy, dz);
    }

    @ApiStatus.Internal
    public MixinAbstractHurtingProjectile(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
}
