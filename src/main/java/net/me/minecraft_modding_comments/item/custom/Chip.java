package net.me.minecraft_modding_comments.item.custom;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Chip extends Item {
    public Chip(Properties properties) {
        super(properties.food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).alwaysEdible().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        livingEntity.igniteForSeconds(50);
        livingEntity.addTag("chip");
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
