package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Deodorant {

    @SubscribeEvent
    public static void turn(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        Level level = event.getEntity().level();

        if (entity instanceof Zombie) {
            if (entity.getTags().contains("deodorant")) {
                if (entity.getTicksFrozen() < 2) {
                    Villager villager = new Villager(EntityType.VILLAGER, level);
                    villager.setPos(entity.position());
                    villager.lookAt(EntityAnchorArgument.Anchor.EYES,entity.position().add(entity.getLookAngle()));
                    villager.setTicksFrozen(20);
                    villager.addTag("deodorant_villager");
                    entity.setPos(0,-80,0);
                    entity.kill();
                    level.addFreshEntity(villager);
                }
            }
        }
    }

    @SubscribeEvent
    public static void villagerturnback(LivingDeathEvent event) {
        Level level = event.getEntity().level();
        Entity entity = event.getEntity();

        if (entity instanceof Villager) {
            if (entity.getTags().contains("deodorant_villager")) {

            }
        }
    }
}
