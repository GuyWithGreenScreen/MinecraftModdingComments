package net.me.minecraft_modding_comments.tools;

import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class mixinTools {

    public static ArrayList<BlockState> arrayList2 = new ArrayList<BlockState>();

    public void addBlocktoCue(BlockState blockState) {
        arrayList2.add(blockState);
    }
}
