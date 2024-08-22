package net.me.minecraft_modding_comments.event;


import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.entity.ModEntities;
import net.me.minecraft_modding_comments.entity.client.CanonModel;
import net.me.minecraft_modding_comments.entity.client.ModModelLayers;
import net.me.minecraft_modding_comments.entity.client.RacoonModel;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.me.minecraft_modding_comments.entity.custom.Racoon;
import net.me.minecraft_modding_comments.tools.TickHandler;
import net.me.minecraft_modding_comments.tools.tools;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Minecraft_modding_comments.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.CANON, CanonModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.RACOON, RacoonModel::createBodyLayer);
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.CANON.get(), CanonEntity.createAttributes().build());
        event.put(ModEntities.RACOON.get(), Racoon.createAttributes().build());
    }

}
