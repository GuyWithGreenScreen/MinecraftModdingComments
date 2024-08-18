package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.item.ModItems;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Taco {

    @SubscribeEvent
    public static void tacoGround(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof ItemEntity) {
            if (((ItemEntity) event.getEntity()).getItem().is(ModItems.TACO)) {
                if (event.getEntity().onGround()) {
                    for (int i = 0; i < 4; i++) {
                        ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, event.getEntity().level());
                        itemEntity.setItem(ModItems.TACO.toStack(1));
                        itemEntity.setPos(event.getEntity().position().add(tools.randomInt(-2,2), 1, tools.randomInt(-2,2)));
                        itemEntity.setDeltaMovement(tools.randomDouble(-0.8, 0.8), tools.randomDouble(0.4, 0.5), tools.randomDouble(-0.8, 0.8));
                        event.getEntity().level().addFreshEntity(itemEntity);
                    }
                    event.getEntity().level().explode(event.getEntity(), event.getEntity().position().x,
                            event.getEntity().position().y, event.getEntity().position().z, 0, Level.ExplosionInteraction.NONE);
                    event.getEntity().kill();
                }
            }
        }
    }
}
