package net.me.minecraft_modding_comments.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class CanonEntity extends Monster {
    private BlockPos registeredBlock;
    public CanonEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public BlockPos getBlockPos() {
        return registeredBlock;
    }
    public void setBlockPos(BlockPos blockPos) {
        registeredBlock = blockPos;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ATTACK_DAMAGE, 0D)
                .add(Attributes.ATTACK_SPEED, 0D)
                .add(Attributes.FOLLOW_RANGE, 0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10D);
    }
    @Override
    protected void registerGoals() {
    }
}
