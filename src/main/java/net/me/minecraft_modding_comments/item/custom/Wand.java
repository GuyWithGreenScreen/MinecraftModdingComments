package net.me.minecraft_modding_comments.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

import java.util.ArrayList;
import java.util.Objects;

public class Wand extends Item {
    public Wand(Properties properties) {
        super(properties);
    }

    static ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide) {
            if (Objects.requireNonNull(context.getPlayer()).isShiftKeyDown()) {
                if (arrayList.contains(context.getClickedPos())) {
                    arrayList.remove(context.getClickedPos());
                    ParticleOptions particleOptions = ParticleTypes.WAX_OFF;
                    context.getLevel().addParticle(particleOptions, context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1, context.getClickedPos().getZ() + 0.5, 0D, 0.5D, 0D);
                }
            } else {
                if (!arrayList.contains(context.getClickedPos())) {
                    arrayList.add(context.getClickedPos());
                }
                ParticleOptions particleOptions = ParticleTypes.WAX_ON;
                context.getLevel().addParticle(particleOptions, context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1, context.getClickedPos().getZ() + 0.5, 0D, 0.5D, 0D);

            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        System.out.println("Swing");
        if (!arrayList.isEmpty() && !entity.level().isClientSide) {
            entity.level().explode(entity, arrayList.getFirst().getX(), arrayList.getFirst().getY(), arrayList.getFirst().getZ(), 4f, Level.ExplosionInteraction.TNT);
            arrayList.remove(arrayList.getFirst());

        }
        return super.onEntitySwing(stack, entity);
    }
}
