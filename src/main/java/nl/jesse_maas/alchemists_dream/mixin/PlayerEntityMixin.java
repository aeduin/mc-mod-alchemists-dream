package nl.jesse_maas.alchemists_dream.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import nl.jesse_maas.alchemists_dream.AlchemistsDream;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract void playSound(SoundEvent event, SoundCategory category, float volume, float pitch);

    int jumpsLeft = 0;
    int doubleJumpCoolDown = DOUBLE_JUMP_COOLDOWN;
    private static final int DOUBLE_JUMP_COOLDOWN = 5;

    @Inject(method = "canFoodHeal", at = @At("RETURN"), cancellable = true)
    protected void noRegeneration(CallbackInfoReturnable ci) {
        if(((PlayerEntity)(Object) this).hasStatusEffect(AlchemistsDream.NO_REGEN)) {
            ci.setReturnValue(false);
        }
    }

    @Inject(method = "tickMovement", at = @At("TAIL"))
    protected void doubleJump(CallbackInfo ci) {
        var player = (PlayerEntity) (Object) this;
        if(player.isOnGround() || player.isInLava() || player.isSwimming()) {
            StatusEffectInstance double_jump_instance = player.getStatusEffect(AlchemistsDream.DOUBLE_JUMP);

            if(double_jump_instance != null) {
                this.jumpsLeft = double_jump_instance.getAmplifier() + 1;
                this.doubleJumpCoolDown = DOUBLE_JUMP_COOLDOWN;
            }
        }
        else {
            var playerAccessor = ((LivingEntityAccessor)player);
            if(playerAccessor.getJumping() /* implicit: && !player.isOnGround() && !player.isSwimming() && !player.isInLava() */ && jumpsLeft > 0 && this.doubleJumpCoolDown == 0) {
                player.jump();
                this.jumpsLeft--;
                this.doubleJumpCoolDown = DOUBLE_JUMP_COOLDOWN;
            }
        }

        this.doubleJumpCoolDown--;
        if(this.doubleJumpCoolDown < 0) {
            this.doubleJumpCoolDown = 0;
        }
    }
}
