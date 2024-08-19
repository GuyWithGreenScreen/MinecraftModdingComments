package net.me.minecraft_modding_comments.event;

import it.unimi.dsi.fastutil.ints.IntList;
import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.item.ModItems;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.Collections;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Racoon {

    @SubscribeEvent
    public static void racoonFeed(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof net.me.minecraft_modding_comments.entity.custom.Racoon) {
            if (event.getEntity().getMainHandItem().is(ModItems.BIG_MAC)) {
                event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, ModItems.BIG_MAC.toStack(event.getEntity().getMainHandItem().getCount() - 1));
                event.getTarget().addTag("racoonEat");
                event.getLevel().playSound(event.getTarget(), tools.vectorToBlockPos(event.getTarget().position()), SoundEvents.PLAYER_BURP, SoundSource.NEUTRAL, 1f, 1f);
                event.getLevel().playSound(event.getTarget(), tools.vectorToBlockPos(event.getTarget().position()), SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.NEUTRAL, 1f, 1f);
                event.getTarget().setTicksFrozen(40);

            }
        }
    }

    @SubscribeEvent
    public static void racoonFly(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof net.me.minecraft_modding_comments.entity.custom.Racoon) {
            if (event.getEntity().getTags().contains("racoonEat")) {
                Vec3 pos = event.getEntity().position();
                if (event.getEntity().getTicksFrozen() > 2) {
                    event.getEntity().setYBodyRot(tools.randomInt(-180, 180));
                    event.getEntity().setDeltaMovement(0,1,0);
                    event.getEntity().level().addParticle(ParticleTypes.FIREWORK, pos.x,pos.y,pos.z,0,-1,0);
                } else {
                    event.getEntity().level().createFireworks(pos.x, pos.y, pos.z, 0,0,0,
                            Collections.singletonList(new FireworkExplosion(FireworkExplosion.Shape.LARGE_BALL,
                            IntList.of(-1000,-1000,-1000), IntList.of(-1000), true, true)));
                    event.getEntity().removeTag("racoonEat");
                    for (int i = 0; i < 100; i++) {
                        ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, event.getEntity().level());
                        itemEntity.setItem(ModItems.BIG_MAC.toStack(1));
                        itemEntity.setDeltaMovement((double) tools.randomInt(-15, 15) /tools.randomInt(8,15), (double) tools.randomInt(-15, 15) /tools.randomInt(8,15), (double) tools.randomInt(-15, 15) /tools.randomInt(8,15));
                        itemEntity.setPickUpDelay(45);
                        itemEntity.setPos(event.getEntity().position());
                        event.getEntity().level().addFreshEntity(itemEntity);
                        event.getEntity().level().addParticle(ParticleTypes.TOTEM_OF_UNDYING,pos.x, pos.y, pos.z, (double) tools.randomInt(-15, 15) /tools.randomInt(8,15),(double) tools.randomInt(-15, 15) /tools.randomInt(8,15),(double) tools.randomInt(-15, 15) /tools.randomInt(8,15));
                    }
                    event.getEntity().kill();
                }
            }
        }
    }
}
