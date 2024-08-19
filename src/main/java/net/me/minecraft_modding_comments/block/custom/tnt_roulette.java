package net.me.minecraft_modding_comments.block.custom;

import net.me.minecraft_modding_comments.block.ModBlocks;
import net.me.minecraft_modding_comments.tools.TickHandler;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.core.BlockPos;
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
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(level, pos, ModBlocks.TNT_ROULETTE.get().defaultBlockState());
        fallingBlockEntity.setPos(tools.BlockPosToVec3(pos).add(0.5,2,0.5));
        fallingBlockEntity.addTag("falling_tnt");
        fallingBlockEntity.setNoGravity(true);
        fallingBlockEntity.setDeltaMovement(0,-0.1,0);
        level.removeBlock(pos, false);
        TickHandler.RegisterOnSpawn(fallingBlockEntity);
        level.addFreshEntity(fallingBlockEntity);
        return InteractionResult.CONSUME;
    }
}
