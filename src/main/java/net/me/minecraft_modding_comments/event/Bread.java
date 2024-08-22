package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Objects;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Bread {

    @SubscribeEvent
    public static void bread(LivingEntityUseItemEvent.Finish event) {
        if (!event.getEntity().level().isClientSide) {
            if (event.getItem().is(Items.BREAD)) {
                if (tools.randomChance(0.5)) {
                    event.getEntity().level().explode(null,
                            event.getEntity().position().x, event.getEntity().position().y, event.getEntity().position().z,
                            2, Level.ExplosionInteraction.TNT);
                } else {
                    ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, event.getEntity().level());
                    itemEntity.setPos(event.getEntity().position().add(0,0,0));
                    itemEntity.setItem(new ItemStack(Items.BREAD, 8));
                    itemEntity.setPickUpDelay(0);
                    event.getEntity().level().addFreshEntity(itemEntity);
                }
                System.out.println("bread");
            } else {
                if (tools.randomChance(0.1)) {
                    event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON,800, 1, false,true));
                }
            }
        }
    }
}
