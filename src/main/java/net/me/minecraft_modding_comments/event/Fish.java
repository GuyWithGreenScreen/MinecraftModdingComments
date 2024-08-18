package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Fish {

    @SubscribeEvent
    public static void FishSpawn(EntityEvent.EnteringSection event) {
        if (event.getEntity() instanceof Cod || event.getEntity() instanceof Salmon || event.getEntity() instanceof TropicalFish) {
            if (tools.randomChance(0.25d)) {
                for (Entity entity : event.getEntity().level().getEntities(event.getEntity(),
                        new AABB(event.getEntity().position().add(45,45,45),
                                event.getEntity().position().add(-45,-45,-45)))) {
                    if (entity instanceof Player) {
                        for (int i = 0; i < 5; i++) {
                            Warden warden = new Warden(EntityType.WARDEN, entity.level());
                            warden.setPos(entity.position().add(new Vec3(tools.randomInt(-4,4), 0, tools.randomInt(-4,4) )));
                            warden.swing(InteractionHand.MAIN_HAND);
                            entity.level().addFreshEntity(warden);
                        }
                    }
                }
            }
        }
    }
}
