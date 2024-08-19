package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.block.ModBlocks;
import net.me.minecraft_modding_comments.tools.TickHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class TNT_Roulette {

    @SubscribeEvent
    public static void fallingBlockTNT(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof FallingBlockEntity) {
            if (((FallingBlockEntity) event.getEntity()).getTags().contains("falling_tnt")) {
                System.out.println(TickHandler.getTick(event.getEntity()));
            }
        }
    }
}
