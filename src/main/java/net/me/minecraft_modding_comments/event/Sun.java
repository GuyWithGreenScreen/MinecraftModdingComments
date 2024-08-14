package net.me.minecraft_modding_comments.event;


import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.block.AirBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.openjdk.nashorn.internal.ir.Block;

import static net.minecraft.world.damagesource.DamageTypes.ON_FIRE;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Sun {

    @SubscribeEvent
    public static void Sun(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Zombie) && !(entity instanceof Skeleton) && !entity.level().isClientSide) {
            boolean sky = true;
            for (int i = 0; i < 20; i++) {
                if (!entity.level().getBlockState(new BlockPos(((int) entity.position().x), ((int) (entity.position().y + i)), ((int) entity.position().z))).isAir()) {
                    sky = false;
                }
            }
            if (entity.getRemainingFireTicks() < 2 && sky) {
                entity.igniteForSeconds(8);
            }
        } else {}
        if (entity.level().isNight() && !entity.level().isClientSide) {
            entity.igniteForSeconds(8);
        }
    }

    @SubscribeEvent
    public static void Skeleton(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        if (entity instanceof Skeleton) {
            if (entity.isOnFire()) {
                entity.clearFire();
            }
        }
    }


}
