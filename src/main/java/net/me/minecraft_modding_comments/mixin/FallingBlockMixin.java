package net.me.minecraft_modding_comments.mixin;

import net.me.minecraft_modding_comments.tools.mixinTools;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(FallingBlockEntity.class)
public class FallingBlockMixin {


    @Shadow private BlockState blockState;

    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At("TAIL"))
    private void FallingBlockEntity(CallbackInfo ci) {
        if (!mixinTools.arrayList2.isEmpty()) {
            this.blockState = mixinTools.arrayList2.getFirst();
            mixinTools.arrayList2.remove(mixinTools.arrayList2.getFirst());
        } else {
            this.blockState = Blocks.SAND.defaultBlockState();
        }
    }
}
