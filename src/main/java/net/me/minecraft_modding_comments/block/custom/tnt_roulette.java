package net.me.minecraft_modding_comments.block.custom;

import net.me.minecraft_modding_comments.block.ModBlocks;
import net.me.minecraft_modding_comments.sound.ModSounds;
import net.me.minecraft_modding_comments.tools.TickHandler;
import net.me.minecraft_modding_comments.tools.mixinTools;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class tnt_roulette extends Block {
    public tnt_roulette(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        mixinTools.arrayList2.add(ModBlocks.TNT_ROULETTE.get().defaultBlockState());
        FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(EntityType.FALLING_BLOCK, level);
        fallingBlockEntity.setPos(tools.BlockPosToVec3(pos).add(0.5,0.5,0.5));
        fallingBlockEntity.addTag("falling_tnt");
        fallingBlockEntity.setDeltaMovement(0,0.4,0);
        level.removeBlock(pos, false);
        level.playSound(fallingBlockEntity, pos, ModSounds.CASINO_MACHINE.get(), SoundSource.NEUTRAL,1,1);
        level.playSound(fallingBlockEntity, pos, SoundEvents.TNT_PRIMED, SoundSource.NEUTRAL,1,1);
        TickHandler.RegisterOnSpawn(fallingBlockEntity);
        level.addFreshEntity(fallingBlockEntity);
        return InteractionResult.CONSUME;
    }
}
