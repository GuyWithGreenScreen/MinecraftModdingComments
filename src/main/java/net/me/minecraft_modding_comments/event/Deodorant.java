package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Deodorant {

    @SubscribeEvent
    public static void turn(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        Level level = event.getEntity().level();


        if (entity instanceof Zombie) {
            if (entity.getTags().contains("deodorant")) {
                if (entity.getTicksFrozen() < 2) {
                    for (int i = 0; i < 50; i++) {
                        level.addParticle(ParticleTypes.WAX_OFF, entity.position().x + tools.randomDouble(-2, 2),
                                entity.position().y + tools.randomDouble(1, 3), entity.position().z + tools.randomDouble(-2, 2),
                                tools.randomDouble(-0.2, 0.2), tools.randomDouble(-0.2, 0.2), tools.randomDouble(-0.2, 0.2));
                    }
                    level.playSound(entity, tools.vectorToBlockPos(entity.position()), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.NEUTRAL, 0.4f, 1);

                    Villager villager = new Villager(EntityType.VILLAGER, level);
                    villager.setPos(entity.position());
                    villager.lookAt(EntityAnchorArgument.Anchor.EYES,entity.position().add(entity.getLookAngle()));
                    villager.setTicksFrozen(10000);
                    villager.addTag("deodorant_villager");
                    entity.setPos(0,-80,0);
                    entity.kill();
                    level.addFreshEntity(villager);
                } else {
                    entity.lookAt(EntityAnchorArgument.Anchor.EYES, entity.position().add(new Vec3(tools.randomInt(-5, 5),
                            tools.randomInt(-5, 5), tools.randomInt(-5, 5))));
                    entity.setYRot(entity.getTicksFrozen());
                }
            }
        }
    }

    @SubscribeEvent
    public static void villagerturnback(LivingDeathEvent event) {
        Level level = event.getEntity().level();
        Entity entity = event.getEntity();

        if (entity instanceof Villager) {
            if (entity.getTags().contains("deodorant_villager")) {
                Zombie zombie = new Zombie(level);
                zombie.setPos(entity.position());
                for (int i = 0; i < 50; i++) {
                    level.addParticle(ParticleTypes.WAX_OFF, entity.position().x + tools.randomDouble(-2, 2),
                            entity.position().y + tools.randomDouble(1, 3), entity.position().z + tools.randomDouble(-2, 2),
                            tools.randomDouble(-0.2, 0.2), tools.randomDouble(-0.2, 0.2), tools.randomDouble(-0.2, 0.2));
                }
                level.playSound(entity, tools.vectorToBlockPos(entity.position()), SoundEvents.ZOMBIE_DEATH, SoundSource.NEUTRAL, 0.2f, 1);
                entity.setPos(0, -85, 0);
                entity.kill();
                level.addFreshEntity(zombie);
            }
        }
    }

    @SubscribeEvent
    public static void maskVillager(LivingDamageEvent.Post event) {
        Entity entity = event.getEntity();
        Level level = event.getEntity().level();

        if (entity instanceof Villager) {
            if (entity.getTags().contains("villager_deodorant")) {
                ((Villager) entity).setHealth(((Villager) entity).getHealth() - event.getOriginalDamage());
                entity.hurtMarked = false;
                ((Villager) entity).hurtDuration = 0;
            }
        }
    }
}
