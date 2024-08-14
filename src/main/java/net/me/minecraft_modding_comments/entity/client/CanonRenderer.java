package net.me.minecraft_modding_comments.entity.client;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CanonRenderer extends MobRenderer<CanonEntity, CanonModel> {
    public CanonRenderer(EntityRendererProvider.Context context) {
        super(context, new CanonModel(context.bakeLayer(ModModelLayers.CANON)), 0.45f);
    }

    @Override
    public ResourceLocation getTextureLocation(CanonEntity canonEntity) {
        return ResourceLocation.fromNamespaceAndPath(Minecraft_modding_comments.MODID, "textures/entity/canon/canon.png");
    }
}
