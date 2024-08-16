package net.me.minecraft_modding_comments.mixin;


import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class Potato {

    @Inject(method = "getDefaultMaxStackSize", at = @At("HEAD"))
    public void getDefaultMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(55);
    }
}
