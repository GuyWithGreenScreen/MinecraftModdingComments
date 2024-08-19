package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.item.ModItems;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class EmptyPepsiCan {

    @SubscribeEvent
    public static void giveMilk(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Villager) {
            if (event.getEntity().getTags().contains("EmptyPepsiCan")) {
                event.getEntity().setDeltaMovement(0,0,0);
            }
            if (event.getEntity().getTags().contains("click") && event.getEntity().getTicksFrozen() < 2) {
                event.getEntity().setInvisible(true);
            }
        }
    }

    @SubscribeEvent
    public static void giveMilk2(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof Villager) {
            if (event.getTarget().getTags().contains("EmptyPepsiCan") && !event.getTarget().getTags().contains("click")) {
                event.getTarget().addTag("click");
                ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, event.getLevel());
                itemEntity.setItem(Items.MILK_BUCKET.getDefaultInstance());
                itemEntity.setPos(event.getTarget().position().add(0,1,0));
                itemEntity.setDeltaMovement(tools.multiplyVec3(event.getTarget().getLookAngle(), 0.2));
                event.getLevel().addFreshEntity(itemEntity);
                event.getTarget().setTicksFrozen(55);
            } else if (event.getEntity().getMainHandItem().is(ModItems.BIG_MAC) && !event.getTarget().getTags().contains("EmptyPepsiCan")) {
                event.getTarget().addTag("EmptyPepsiCan");
                event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, ModItems.BIG_MAC.toStack(event.getEntity().getMainHandItem().getCount()));
            }
        }
    }
}
