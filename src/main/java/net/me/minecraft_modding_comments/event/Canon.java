package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.checkerframework.checker.units.qual.C;
import org.joml.Vector3d;

import java.util.ArrayList;
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
        Vec3 finalVec = new Vec3((toPositive(pos1.x)-toPositive(pos2.x)), (toPositive(pos1.y)-toPositive(pos2.y)), (toPositive(pos1.z)-toPositive(pos2.z)));
        return (finalVec.x + finalVec.y + finalVec.z);
    }

    public static Vec3 multiplyVec3(Vec3 firstVector, Vec3 secondVector) {
        return new Vec3(firstVector.x * secondVector.x, firstVector.y * secondVector.y, firstVector.z * secondVector.z);
    }

    public static double getDistance(Vec3 pos1, Vec3 pos2) {
        Vector3d vector3DDif = new Vector3d(toPositive(pos1.x - pos2.x), toPositive(pos1.y - pos2.y), toPositive(pos1.z - pos2.z));
        double hypA = Math.sqrt((vector3DDif.x*vector3DDif.x)+(vector3DDif.z*vector3DDif.z));
        double hypB = Math.sqrt((vector3DDif.y*vector3DDif.y)+(hypA*hypA));
        return hypB;
    }

    @SubscribeEvent
    public static void lookAtEntity(EntityTickEvent.Pre event) {
        Level level = event.getEntity().level();
        Entity entity = event.getEntity();

        if (entity instanceof CanonEntity && !level.isClientSide) {
            int time = 0;
            if (((CanonEntity) entity).getBlockPos() == null) {
                ((CanonEntity) entity).setBlockPos(new BlockPos(((int) Math.floor(entity.position().x)), ((int) Math.floor((entity.position().y))), ((int) Math.floor(entity.position().z))));
            } else {
                if (!level.getBlockState(((CanonEntity) entity).getBlockPos().subtract(new Vec3i(0, 1, 0))).getBlock().equals(Blocks.CHEST)) {
                    entity.kill();
                }
                if (!((CanonEntity) entity).getBlockPos().equals(entity.position())) {
                    entity.setPos(new Vec3(((CanonEntity) entity).getBlockPos().getX() + 0.5, ((CanonEntity) entity).getBlockPos().getY(), ((CanonEntity) entity).getBlockPos().getZ() + 0.5));
                }
            }
            int positionOffset = 100;
            HashMap<Double, Vec3> hashMap = new HashMap<Double, Vec3>();
            ArrayList<Vec3> arrayList = new ArrayList<Vec3>();
            double difference = 0;
            double differenceHolder = 0;
            Entity entity2 = null;
            AABB box = new AABB(entity.position().x - positionOffset, entity.position().y - 15, entity.position().z - positionOffset,
                    entity.position().x + positionOffset, entity.position().y + 15, entity.position().z + positionOffset);
            arrayList.clear();
            for (Entity entity1 : level.getEntities(entity, box)) {
                //differenceHolder = difference(entity.position(), entity1.position());
                //if (differenceHolder < difference) {
                    //difference = differenceHolder;
                    //entity2 = entity1;
                //}
                if (!(entity1 instanceof PrimedTnt) && !(entity1 instanceof ItemEntity) && !(entity1 instanceof Player)) {
                    arrayList.add(entity1.position());
                    //hashMap.put(differenceHolder, entity1.position());
                }

            }
            if (!arrayList.isEmpty()) {
                int left = entity.getTicksFrozen();
                System.out.println(difference);

                BlockEntity blockEntity = level.getBlockEntity(((CanonEntity) entity).getBlockPos().subtract(new Vec3i(0, 1, 0)));

                if (entity.getTicksFrozen() < 2 && blockEntity instanceof ChestBlockEntity chestBlock) {
                    double distance = getDistance(entity.position(), arrayList.getFirst());
                    boolean hasTnt = false;
                    int slot = 0;
                    entity.lookAt(EntityAnchorArgument.Anchor.EYES, arrayList.getFirst());
                    PrimedTnt primedTnt = new PrimedTnt(level, entity.position().x+entity.getLookAngle().x, entity.position().y+entity.getLookAngle().y, entity.position().z+entity.getLookAngle().z, null);
                    primedTnt.setFuse(((int) ((distance - (distance / 10)) * 0.6)));
                    primedTnt.addDeltaMovement(multiplyVec3(entity.getLookAngle(), new Vec3(0.1*distance,0.1*distance,0.1*distance)));
                    primedTnt.addTag("canon");
                    for (int i = 0; i < chestBlock.getContainerSize(); i++) {
                        if (chestBlock.getItem(i).is(Items.TNT)) {
                            hasTnt = true;
                            slot = i;
                        }
                    }
                    if (hasTnt) {
                        chestBlock.removeItem(slot, 1);
                        level.addFreshEntity(primedTnt);
                        entity.setTicksFrozen(20);
                    }
                    //if (level.getBlockState(new BlockPos(((int) entity.position().x), ((int) (entity.position().y - 1)), ((int) entity2.position().z))).getCloneItemStack()) //Add inventory
                }
            }
        } else if (entity instanceof PrimedTnt && entity.getTags().contains("canon")) {
            AABB aabb2 = new AABB(entity.position().x - 1, entity.position().y - 1, entity.position().z - 1,
                    entity.position().x + 1, entity.position().y + 1, entity.position().z + 1);
            if (level.getEntities(entity, aabb2).size() > 1 && ((PrimedTnt) entity).tickCount > 5) {
                ((PrimedTnt) entity).setFuse(0);
            }

        }
    }
}
