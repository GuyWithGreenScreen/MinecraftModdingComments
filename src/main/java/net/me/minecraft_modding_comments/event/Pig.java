package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Pig {

    @SubscribeEvent
    public static void pigEat(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof net.minecraft.world.entity.animal.Pig) {
            Level level = event.getEntity().level();
            Entity entity = event.getEntity();

            if (entity.getTags().contains("eatOthers")) {
                AABB aabb = new AABB(event.getEntity().position().add(-2,0,-2), event.getEntity().position().add(2,1,2));
                for (Entity entity1 : level.getEntities(entity, aabb)) {
                    if (entity1 instanceof net.minecraft.world.entity.animal.Pig && !entity1.getTags().contains("eatOthers")) {
                        entity1.setPos(0,-85,0);
                        entity1.kill();
                        level.playSound(entity, tools.vectorToBlockPos(entity.position()),SoundEvents.PLAYER_BURP, SoundSource.NEUTRAL, 1 , 1f);
                        AABB aabb1 = new AABB(entity.position().add(-15,-4,-15), entity.position().add(15,2,15));
                        for (Entity entity2 : level.getEntities(entity, aabb1)) {
                            if (entity2 instanceof net.minecraft.world.entity.animal.Pig) {
                                entity.moveTo(entity2.position());
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            }
    }

    @SubscribeEvent
    public static void interact(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof net.minecraft.world.entity.animal.Pig && !event.getTarget().getTags().contains("eatOthers")) {
            if (event.getEntity().getMainHandItem().is(Items.PORKCHOP)) {
                event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.PORKCHOP, event.getEntity().getMainHandItem().getCount() - 1));
                event.getTarget().addTag("eatOthers");
                AABB aabb1 = new AABB(event.getTarget().position().add(-15,-4,-15), event.getTarget().position().add(15,2,15));
                for (Entity entity2 : event.getLevel().getEntities(event.getTarget(), aabb1)) {
                    if (entity2 instanceof net.minecraft.world.entity.animal.Pig) {
                        event.getTarget().moveTo(entity2.position());
                        break;
                    }
                }
            } else if (event.getEntity().getMainHandItem().is(Items.COOKED_PORKCHOP)) {
                event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.COOKED_PORKCHOP, event.getEntity().getMainHandItem().getCount() - 1));
                event.getTarget().addTag("eatOthers");
            }
        }
    }
}
