package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.item.ModItems;
import net.me.minecraft_modding_comments.tools.TagHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import javax.swing.text.html.HTML;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Gieger {

    @SubscribeEvent
    public static void radium(PlayerTickEvent.Post event) {
        if (event.getEntity().getInventory().contains(ModItems.RADIUM224.toStack())) {
            TagHandler.AddIntTag(event.getEntity(), "geiger", 1);
        } else {
            TagHandler.removeEntityTags(event.getEntity());
        }
    }
}
