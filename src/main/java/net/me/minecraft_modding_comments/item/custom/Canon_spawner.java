package net.me.minecraft_modding_comments.item.custom;

import net.me.minecraft_modding_comments.entity.ModEntities;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.me.minecraft_modding_comments.event.Canon;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class Canon_spawner extends Item {
    public Canon_spawner(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (!level.isClientSide) {
            if (level.getBlockState(context.getClickedPos()).getBlock() == Blocks.CHEST) {
                DirectionProperty directionProperty;
                BlockState blockState = level.getBlockState(context.getClickedPos());
                CanonEntity canonEntity = new CanonEntity(ModEntities.CANON.get(), level);
                canonEntity.setPos(context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1, context.getClickedPos().getZ() + 0.5);
                level.addFreshEntity(canonEntity);
            }
        }

        return InteractionResult.CONSUME;
    }
}
