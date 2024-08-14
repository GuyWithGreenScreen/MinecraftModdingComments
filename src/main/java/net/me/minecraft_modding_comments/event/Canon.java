package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Canon {

    public static double toPositive(double covertToPositiveNumber) {
        if (covertToPositiveNumber < 0) {
            return covertToPositiveNumber * -1;
        }
        return covertToPositiveNumber;
    }
    public static double difference(Vec3 pos1, Vec3 pos2) {
        Vec3 finalVec = new Vec3(toPositive(pos1.x)-toPositive(pos2.x), toPositive(pos1.y)-toPositive(pos2.y), toPositive(pos1.z)-toPositive(pos2.z));
        return finalVec.x + finalVec.y + finalVec.z;
    }

    @SubscribeEvent
    public static void lookAtEntity(EntityTickEvent.Pre event) {
        Level level = event.getEntity().level();
        Entity entity = event.getEntity();

        if (entity instanceof CanonEntity) {
            int time = 0;
            if (((CanonEntity) entity).getBlockPos() == null) {
                ((CanonEntity) entity).setBlockPos(new BlockPos(((int) Math.floor(entity.position().x)), ((int) Math.floor((entity.position().y))), ((int) Math.floor(entity.position().z))));
            } else {
                if (!((CanonEntity) entity).getBlockPos().equals(entity.position())) {
                    entity.setPos(new Vec3(((CanonEntity) entity).getBlockPos().getX() + 0.5, ((CanonEntity) entity).getBlockPos().getY(), ((CanonEntity) entity).getBlockPos().getZ() + 0.5));
                }
            }
            if (!level.getBlockState(new BlockPos(((int) entity.position().x), ((int) entity.position().y) - 1, ((int) entity.position().z))).getBlock().equals(Blocks.CHEST)) {
                entity.kill();
            }
            int positionOffset = 15;
            HashMap<Double, Vec3> hashMap = new HashMap<Double, Vec3>();
            double difference = 0;
            double differenceHolder = 0;
            Entity entity2 = null;
            AABB box = new AABB(entity.position().x - positionOffset, entity.position().y - 2, entity.position().z - positionOffset,
                    entity.position().x + positionOffset, entity.position().y + 15, entity.position().z + positionOffset);
            for (Entity entity1 : level.getEntities(entity, box)) {
                differenceHolder = difference(entity.position(), entity1.position());
                if (differenceHolder < difference) {
                    difference = differenceHolder;
                    entity2 = entity1;
                }
                if (entity1 instanceof Monster) {
                    hashMap.put(differenceHolder, entity1.position());
                }

            }
            if (hashMap.get(difference) != null) {
                entity.lookAt(EntityAnchorArgument.Anchor.EYES, hashMap.get(difference));

                if (entity.getTicksFrozen() < 2) {
                    PrimedTnt primedTnt = new PrimedTnt(level, entity.position().x, entity.position().y, entity.position().z, null);
                    primedTnt.setFuse(50);
                    primedTnt.addDeltaMovement(entity.getLookAngle());
                    if (level.getBlockEntity(new BlockPos(((int) entity.position().x), ((int) (entity.position().y - 1)), ((int) entity2.position().z)))) //Add inventory
                    level.addFreshEntity(primedTnt);
                    entity.setTicksFrozen(20);
                }
            }
            if (time == 20) {
                time = 0;
            } else {
                time += 1;
            }
        }
    }
}
