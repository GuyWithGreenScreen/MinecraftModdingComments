package net.me.minecraft_modding_comments.tools;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class ToolEvents {


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void updateTickHandler(ServerTickEvent.Pre event) {
        TickHandler.update();
    }

    @SubscribeEvent()
    public static void registerOnSpawn(EntityTickEvent.Pre event) {
        if (event.getEntity().getTags().contains("registerOnSpawn")) {
            TickHandler.RegisterEntity((event.getEntity()));
            event.getEntity().removeTag("registerOnSpawn");
        }
    }
}
