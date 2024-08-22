package net.me.minecraft_modding_comments.event;

import it.unimi.dsi.fastutil.ints.IntList;
import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.block.ModBlocks;
import net.me.minecraft_modding_comments.tools.TickHandler;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.Collections;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class TNT_Roulette {

    @SubscribeEvent
    public static void fallingBlockTNT(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof FallingBlockEntity) {
            if (((FallingBlockEntity) event.getEntity()).getTags().contains("falling_tnt")) {
                if (event.getEntity().getDeltaMovement().y < 0) {
                    event.getEntity().setNoGravity(true);
                    event.getEntity().setDeltaMovement(0,-0.025,0);
                }
                if (TickHandler.getTick(event.getEntity()) < 80) {
                    event.getEntity().level().addParticle(ParticleTypes.FIREWORK, true, event.getEntity().position().x,
                            event.getEntity().position().y + 1,event.getEntity().position().z, 0,
                            1,0);
                } else {
                        if (tools.randomChance(0.5)) {
                            event.getEntity().setNoGravity(false);
                            event.getEntity().level().explode(null, event.getEntity().position().x,
                                    event.getEntity().position().y, event.getEntity().position().z, 4, Level.ExplosionInteraction.TNT);
                        } else {
                            event.getEntity().level().createFireworks(event.getEntity().position().x, event.getEntity().position().y,
                                    event.getEntity().position().z, 0,0,0,
                                    Collections.singletonList(new FireworkExplosion(FireworkExplosion.Shape.LARGE_BALL,
                                            IntList.of(-1000,-1000,-1000), IntList.of(-1000), true, true)));
                            for (int i = 0; i < 50; i++) {
                                event.getEntity().level().addParticle(ParticleTypes.TOTEM_OF_UNDYING, true, event.getEntity().position().x,
                                        event.getEntity().position().y, event.getEntity().position().z,
                                        tools.randomDouble(-0.4,0.4), tools.randomDouble(-0.4,0.4), tools.randomDouble(-0.4,0.4));
                            }

                            event.getEntity().level().explode(null, event.getEntity().position().x,
                                    event.getEntity().position().y, event.getEntity().position().z, 0, Level.ExplosionInteraction.NONE);
                            for (int i = 0; i < 25; i++) {
                                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, event.getEntity().level());
                                itemEntity.setPos(event.getEntity().position());
                                int random = tools.randomInt(0,100);
                                if (random < 20) {
                                    itemEntity.setItem(Items.DIAMOND.getDefaultInstance());
                                } else if (random < 40) {
                                    itemEntity.setItem(Items.GOLD_INGOT.getDefaultInstance());
                                } else if (random < 60) {
                                    itemEntity.setItem(Items.IRON_INGOT.getDefaultInstance());
                                } else if (random < 80) {
                                    itemEntity.setItem(Items.EMERALD.getDefaultInstance());
                                } else {
                                    itemEntity.setItem(Items.GOLD_NUGGET.getDefaultInstance());
                                }
                                itemEntity.setDeltaMovement((double) tools.randomInt(-5, 5) /10,
                                        (double) tools.randomInt(-5, 5) /10, (double) tools.randomInt(-5, 5) /10);
                                event.getEntity().level().addFreshEntity(itemEntity);
                            }

                        }
                        TickHandler.RemoveEntity(event.getEntity());
                        event.getEntity().kill();


                }
            }
        }
    }
}
