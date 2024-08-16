package net.me.minecraft_modding_comments.block.custom;

import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class lamp_block extends Block {
    public static final VoxelShape SHAPE = Block.box(6,0,5,12,13,11);
    public lamp_block(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.setBlock(tools.blockadd(new BlockPos(((int) player.position().x), ((int) player.position().y),
                ((int) player.position().z)), new BlockPos(-1,13,-1)), Blocks.ANVIL.defaultBlockState(), 1);
        return InteractionResult.CONSUME;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
