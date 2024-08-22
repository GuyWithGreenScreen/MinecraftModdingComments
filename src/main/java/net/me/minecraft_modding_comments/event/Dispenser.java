package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.mixin.FallingBlockMixin;
import net.me.minecraft_modding_comments.tools.mixinTools;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Dispenser {

    @SubscribeEvent
    public static void dispense(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof ItemEntity) {
            if (((ItemEntity) event.getEntity()).getItem().is(Items.ANVIL)) {
                mixinTools.arrayList2.add(Blocks.ANVIL.defaultBlockState());
                FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(EntityType.FALLING_BLOCK,event.getEntity().level());
                fallingBlockEntity.setPos(event.getEntity().position());
                fallingBlockEntity.setDeltaMovement(event.getEntity().getDeltaMovement().multiply(18,18,18));
                event.getEntity().level().addFreshEntity(fallingBlockEntity);
                event.getEntity().kill();
            }
        }
    }
}
