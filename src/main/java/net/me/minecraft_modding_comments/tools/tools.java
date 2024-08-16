package net.me.minecraft_modding_comments.tools;

import net.minecraft.core.BlockPos;

public class tools {

    public static BlockPos blockadd(BlockPos blockPos1, BlockPos blockPos2) {
        return new BlockPos(blockPos1.getX() + blockPos2.getX(), blockPos1.getY() + blockPos2.getY(), blockPos1.getZ() + blockPos2.getZ());
    }
}
