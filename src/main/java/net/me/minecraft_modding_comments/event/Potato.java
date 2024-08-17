package net.me.minecraft_modding_comments.event;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.item.ModItems;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID)
public class Potato {


    @SubscribeEvent
    public static void breakIce(LivingDeathEvent event) {
        if (event.getEntity().getTags().contains("hot_potato")) {
            System.out.println("e");
            event.getEntity().removeTag("hot_potato");
        }
    }

    @SubscribeEvent
    public static void hitBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getEntity().getTags().contains("hot_potato")) {
            event.getEntity().hurt(event.getLevel().damageSources().source(DamageTypes.ON_FIRE), 1);
        }
    }

    @SubscribeEvent
    public static void PickupItem(ItemEntityPickupEvent.Pre event) {
        if (event.getPlayer().getTags().contains("hot_potato")) {
            event.setCanPickup(TriState.FALSE);
        } else {
            event.setCanPickup(TriState.DEFAULT);
        }
    }

    @SubscribeEvent
    public static void DropItems(PlayerTickEvent.Pre event) {
        if (event.getEntity().getTags().contains("hot_potato")) {
            event.getEntity().getInventory().dropAll();
        }
    }

    @SubscribeEvent
    public static void ItemDestroy(EntityTickEvent.Pre event) {
        if (event.getEntity() instanceof ItemEntity) {
            if (((ItemEntity) event.getEntity()).getItem().is(Items.POTATO.asItem())) {
                if (event.getEntity().isOnFire()) {
                    System.out.println("test");
                    event.getEntity().level().playSound(event.getEntity(), tools.vectorToBlockPos(event.getEntity().position()),
                            SoundEvents.BLAZE_SHOOT, SoundSource.NEUTRAL, 1, 1);
                    ItemEntity hotPotato = new ItemEntity(event.getEntity().level(),
                            event.getEntity().position().x, event.getEntity().position().y, event.getEntity().position().z,
                            ModItems.HOT_POTATO.toStack(((ItemEntity) event.getEntity()).getItem().getCount()));
                    hotPotato.addDeltaMovement(new Vec3(0, 1,0));
                    event.getEntity().level().addFreshEntity(hotPotato);
                    event.getEntity().kill();
                }
            }
        }
    }
}
