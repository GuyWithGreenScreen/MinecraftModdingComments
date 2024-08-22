package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.item.ModItems;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.item.ItemEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

//@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class GeigerCounter {


    //@SubscribeEvent
    public static void geiger(ItemEntityPickupEvent.Pre event) {
        if (event.getPlayer().getMainHandItem().is(ModItems.GEIGERCOUNTER)) {
            event.setCanPickup(TriState.FALSE);
        } else {
            event.setCanPickup(TriState.DEFAULT);
        }
    }
}
