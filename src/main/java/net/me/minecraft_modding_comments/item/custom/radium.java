package net.me.minecraft_modding_comments.item.custom;

import net.me.minecraft_modding_comments.tools.TagHandler;
import net.me.minecraft_modding_comments.tools.TickHandler;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class radium extends Item {

    public radium(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        //TagHandler.AddIntTag(entity, "geiger", 1);
        TickHandler.waitThenRunNoDuplicateRequests(10, 55, () -> {
            entity.hurt(level.damageSources().source(DamageTypes.GENERIC), 2);
        });
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
