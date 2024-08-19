package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Lighting {


    @SubscribeEvent
    public static void fallingBlock(EntityTickEvent.Pre event) {
        Level level = event.getEntity().level();
        Entity entity = event.getEntity();

        if (entity instanceof FallingBlockEntity && entity.getTags().contains("tnt")) {
            if (!level.getBlockState(new BlockPos(((int) entity.position().subtract(new Vec3(0, 0.2, 0)).x), ((int) entity.position().
                    subtract(new Vec3(0, 1, 0)).y), ((int) entity.position().subtract(new Vec3(0, 1, 0)).z))).getBlock().equals(Blocks.AIR) && !entity.getTags().contains("timer")) {
                level.explode(event.getEntity(), entity.position().x,
                        entity.position().y, entity.position().z, 2f, Level.ExplosionInteraction.TNT);
                entity.setDeltaMovement(new Vec3(0, 0, 0));
                entity.addTag("timer");
            }
        }

    }


    @SubscribeEvent
    public static void randomChance(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        Player player = event.getEntity();

        if((event.getEntity().getMainHandItem().is(Items.FLINT_AND_STEEL) || event.getEntity().getOffhandItem().is(Items.FLINT_AND_STEEL))
                && !level.getBlockState(event.getPos()).getBlock().equals(ModBlocks.TNT_ROULETTE.get())) {
            if (((int) (Math.random()*100)) < 20) {


                FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(level, new BlockPos(event.getPos().getX(), event.getPos().getY()+2, event.getPos().getZ()),
                        level.getBlockState(event.getPos()));


                fallingBlockEntity.setDeltaMovement(0,0.4,0);
                fallingBlockEntity.addTag("tnt");
                System.out.println(fallingBlockEntity.getBoundingBox());



                AABB aabb = fallingBlockEntity.getBoundingBox();
                double offset = 0;

                fallingBlockEntity.setBoundingBox(new AABB(aabb.minX-offset, aabb.minY-offset, aabb.minZ-offset, aabb.maxX-offset, aabb.maxY-offset, aabb.maxZ-offset));

                level.playSound(player, event.getPos(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS);
                level.destroyBlock(event.getPos(), false);

                //level.explode(event.getEntity(), event.getHitVec().getBlockPos().getX(),
                //        event.getHitVec().getBlockPos().getY(), event.getHitVec().getBlockPos().getZ(), 2f, Level.ExplosionInteraction.TNT);
            }
        }
    }
}
