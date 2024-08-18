package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class IronGolem {

    @SubscribeEvent
    public static void IronGolemDeath(LivingDeathEvent event) {
        Level level = event.getEntity().level();
        Entity entity = event.getEntity();

        if (entity instanceof net.minecraft.world.entity.animal.IronGolem) {
            //System.out.println(entity.);
        }
    }

}
