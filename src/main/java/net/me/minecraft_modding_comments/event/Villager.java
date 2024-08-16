package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Villager {

    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.EntityInteract entityInteract) {
        Level level = entityInteract.getLevel();
        Player player = entityInteract.getEntity();

        if (entityInteract.getTarget() instanceof net.minecraft.world.entity.npc.Villager) {
            if (entityInteract.getEntity().isShiftKeyDown()) {
                entityInteract.getTarget().setPos(0, -80,0);
                player.eat(level, new ItemStack(Items.GOLDEN_APPLE));
                level.playSound(player, new BlockPos(((int) player.position().x), ((int) player.position().y), ((int) player.position().z)), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS);
            }
        }
    }
}
