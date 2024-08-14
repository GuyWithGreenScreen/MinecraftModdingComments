package net.me.minecraft_modding_comments.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public class Sun {
    @Inject(method = "isSunSensitive", at = @At("HEAD"), cancellable = true)
    protected void suntest(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
