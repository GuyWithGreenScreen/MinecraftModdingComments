package net.me.minecraft_modding_comments.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class Deodorant extends Item {
    public Deodorant(Properties properties) {
        super(properties.rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        if (interactionTarget instanceof Zombie) {
            if (!interactionTarget.getTags().contains("deodorant")) {
                interactionTarget.addTag("deodorant");
                interactionTarget.setTicksFrozen(10);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
