package net.me.minecraft_modding_comments.mixin;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Foods.class)
public class Bread {

    @Shadow
    public static final FoodProperties BREAD = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.6F).alwaysEdible().build();
}
