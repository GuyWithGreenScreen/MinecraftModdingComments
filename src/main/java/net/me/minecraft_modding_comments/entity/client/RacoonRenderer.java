package net.me.minecraft_modding_comments.entity.client;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.me.minecraft_modding_comments.entity.custom.Racoon;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RacoonRenderer extends MobRenderer<Racoon, RacoonModel> {
    public RacoonRenderer(EntityRendererProvider.Context context) {
        super(context, new RacoonModel(context.bakeLayer(ModModelLayers.RACOON)), 0.45f);
    }

    @Override
    public ResourceLocation getTextureLocation(Racoon racoon) {
        return ResourceLocation.fromNamespaceAndPath(Minecraft_modding_comments.MODID, "textures/entity/racoon/racoon.png");
    }
}
