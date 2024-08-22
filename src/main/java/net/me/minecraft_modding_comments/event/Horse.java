package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Horse {

    @SubscribeEvent
    public static void horse(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof net.minecraft.world.entity.animal.horse.Horse) {
            if (event.getEntity().isShiftKeyDown()) {
                event.getTarget().startRiding(event.getEntity());
            }
        }
    }
}
