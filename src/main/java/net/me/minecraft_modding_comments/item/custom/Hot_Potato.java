package net.me.minecraft_modding_comments.item.custom;

import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class Hot_Potato extends Item {
    public Hot_Potato(Properties properties) {
        super(properties.fireResistant().rarity(Rarity.UNCOMMON));
    }

    public static Properties Properties(){
        return new Item.Properties().fireResistant().rarity(Rarity.COMMON);
    }

    int timeInInventory = 0;
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        timeInInventory += 1;
        if (timeInInventory%5==0) {
            level.playSound(entity, tools.vectorToBlockPos(entity.position()), SoundEvents.FIRE_AMBIENT, SoundSource.PLAYERS,
                    tools.alwaysAbove(4-((float) timeInInventory /4000), 0.1f), 1);
        }
        if (timeInInventory > 500) {
            entity.addTag("hot_potato");
            entity.hurt(level.damageSources().source(DamageTypes.ON_FIRE), 1);
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
