package nl.jesse_maas.alchemists_dream.mixin;

import nl.jesse_maas.alchemists_dream.AlchemistsDream;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "canFoodHeal", at = @At("RETURN"), cancellable = true)
    protected void canFoodHeal(CallbackInfoReturnable ci) {
        if(((PlayerEntity)(Object) this).hasStatusEffect(AlchemistsDream.NO_REGEN)) {
            ci.setReturnValue(false);
        }
    }
}
