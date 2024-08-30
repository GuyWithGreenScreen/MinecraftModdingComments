package net.me.minecraft_modding_comments.item.custom;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class PurinaCatChow extends Item {

    public PurinaCatChow(Properties properties) {
        super(properties.food(
                new FoodProperties.Builder().nutrition(2).saturationModifier(2).alwaysEdible().build()
        ));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (isSelected) {
            AABB aabb = new AABB(entity.position().add(15,10,15), entity.position().add(-15, -5, -15));

            for (Entity entity1 : level.getEntities(entity, aabb)) {
                    if (entity1 instanceof Cat) {
                        entity1.lookAt(EntityAnchorArgument.Anchor.EYES, entity.position());
                        entity1.setDeltaMovement(0,0,0);
                    }
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
