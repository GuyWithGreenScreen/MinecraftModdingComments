package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.tools.TickHandler;
import net.me.minecraft_modding_comments.tools.custom.Start;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class Creeper {

    @SubscribeEvent
    public static void creeperThrowTNT(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof net.minecraft.world.entity.monster.Creeper) {
            if (((net.minecraft.world.entity.monster.Creeper) event.getEntity()).getTarget() != null && TickHandler.addCheckRemoveTimer(event.getEntity(), 40, Start.TRUE)) {
                if (event.getEntity().distanceTo(((net.minecraft.world.entity.monster.Creeper) event.getEntity()).getTarget()) < 15) {
                    Level level = event.getEntity().level();
                    Entity entity = ((net.minecraft.world.entity.monster.Creeper) event.getEntity()).getTarget();
                    TickHandler.waitThenRunNoDuplicateRequests(55, () -> {
                        entity.sendSystemMessage(Component.literal("Hi"));
                    });
                    event.getEntity().setDeltaMovement(0, event.getEntity().getDeltaMovement().y, 0);
                    event.getEntity().lookAt(EntityAnchorArgument.Anchor.EYES, ((net.minecraft.world.entity.monster.Creeper) event.getEntity()).getTarget().position());
                    PrimedTnt primedTnt = new PrimedTnt(EntityType.TNT, event.getEntity().level());
                    primedTnt.setPos(event.getEntity().position().add(0,1,0));
                    primedTnt.setDeltaMovement(event.getEntity().getLookAngle().add(0, tools.compareHeight(event.getEntity(), ((net.minecraft.world.entity.monster.Creeper) event.getEntity()).getTarget())/10 + 0.3, 0));
                    primedTnt.setFuse(((int) (event.getEntity().distanceTo(((net.minecraft.world.entity.monster.Creeper) event.getEntity()).getTarget()) * 2)));
                    event.getEntity().level().addFreshEntity(primedTnt);
                }
            }
        }
    }
}
