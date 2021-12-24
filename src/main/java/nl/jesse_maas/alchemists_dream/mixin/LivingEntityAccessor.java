package nl.jesse_maas.alchemists_dream.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    @Accessor
    boolean getJumping();

    @Accessor
    int getJumpingCooldown();

    @Accessor
    void setJumpingCooldown(int value);
}
