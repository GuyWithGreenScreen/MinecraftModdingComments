package net.me.minecraft_modding_comments.item.custom;

import net.me.minecraft_modding_comments.item.ModItems;
import net.me.minecraft_modding_comments.tools.TagHandler;
import net.me.minecraft_modding_comments.tools.TickHandler;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.text.DecimalFormat;

public class GeigerCounter extends Item {
    public GeigerCounter(Properties properties) {
        super(properties);
    }

    double radiation;
    double averageradiation;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (isSelected) {
            boolean radium = false;
            TickHandler.waitThenRunNoDuplicateRequests(20, 35, () -> {
                averageradiation = radiation;
                radiation = 0;
            });
            AABB aabb = new AABB(entity.position().add(-25,-25,-25),
                    entity.position().add(25,25,25));
            for (Entity entity1 : level.getEntities(entity, aabb)) {
                if (entity1 instanceof ItemEntity) {
                    if(((ItemEntity) entity1).getItem().is(ModItems.RADIUM224)) {
                        if (entity.distanceTo(entity1) < 4) {
                            TickHandler.waitThenRunNoDuplicateRequests(((int) (entity.distanceTo(entity1)))*2, 56,
                            () -> {
                                entity.hurt(level.damageSources().source(DamageTypes.GENERIC), 2);
                            });
                        }
                        TickHandler.waitThenRunNoDuplicateRequests(((int) entity.distanceTo(entity1)), 25, () -> {
                            level.playSound(null, tools.vectorToBlockPos(entity.position()), SoundEvents.ITEM_PICKUP,
                                    SoundSource.MASTER, 0.5f, 2);
                            //entity.sendSystemMessage(Component.literal("E"));
                            radiation += ((double) tools.randomInt(0, 500)/100 + 1)/(entity.distanceTo(entity1));
                        });
                        radium = true;
                        break;
                    }
                }
            }
            if (TagHandler.getIntTag(entity, "geiger") == 1) {
                TickHandler.waitThenRunNoDuplicateRequests(1, 25, () -> {
                    level.playSound(null, tools.vectorToBlockPos(entity.position()), SoundEvents.ITEM_PICKUP,
                            SoundSource.MASTER, 0.5f, 2);
                    //entity.sendSystemMessage(Component.literal("E"));
                    radiation += ((double) tools.randomInt(0, 500)/100 + 1);
                });
                radium = true;
            }
            if (!radium) {
                TickHandler.waitThenRunNoDuplicateRequests(tools.randomInt(25,35), 25, () -> {
                    level.playSound(null, tools.vectorToBlockPos(entity.position()), SoundEvents.ITEM_PICKUP,
                            SoundSource.MASTER, 0.5f, 2);
                    //entity.sendSystemMessage(Component.literal("E"));
                    radiation += (double) tools.randomInt(0, 100)/100;
                });
            }
            DecimalFormat df = new DecimalFormat("#.###");
            radiation += ((double) tools.randomInt(0, 10) /10000);
            //entity.sendSystemMessage(Component.literal(df.format((Double) averageradiation).toString()+" Radiants"));
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
