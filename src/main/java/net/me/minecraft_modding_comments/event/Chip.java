package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Chip {

    @SubscribeEvent
    public static void eatChip(PlayerTickEvent.Pre event){
        if (event.getEntity().getTags().contains("chip")) {
            if (event.getEntity().getHealth() < 4) {
                event.getEntity().level().explode(event.getEntity(), event.getEntity().position().x, event.getEntity().position().y, event.getEntity().position().z, 4, Level.ExplosionInteraction.TNT);
                event.getEntity().kill();
                event.getEntity().removeTag("chip");
            } else {
                event.getEntity().level().setBlock(tools.vectorToBlockPos(event.getEntity().position().add(0.2,0,0.2)), Blocks.FIRE.defaultBlockState(), 1);
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.CONFUSION, 15));
            }
        }
    }
}
