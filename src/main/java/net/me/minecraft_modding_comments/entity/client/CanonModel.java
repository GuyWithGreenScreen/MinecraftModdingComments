package net.me.minecraft_modding_comments.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;

public class CanonModel extends HierarchicalModel<CanonEntity> {
    private final ModelPart Canon;
    private final ModelPart Cannon;

    public CanonModel(ModelPart root) {
        this.Canon = root.getChild("Canon");
        this.Cannon = Canon.getChild("Cannon");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Canon = partdefinition.addOrReplaceChild("Canon", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -91.1F, 0.0F));

        PartDefinition Body = Canon.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 29).addBox(-6.0F, -7.0F, -6.0F, 12.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(33, 0).addBox(-5.0F, -15.0F, -5.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Cannon = Canon.addOrReplaceChild("Cannon", CubeListBuilder.create().texOffs(22, 51).addBox(-2.0F, -2.0F, 1.0F, 4.0F, 4.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -2.0F, 15.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 80, 80);
    }

    @Override
    public void setupAnim(CanonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);
    }

    private void applyHeadRotation(float headyaw, float headpitch) {
        headyaw = Math.clamp(headyaw, 0f, 360f);
        headpitch = Math.clamp(headpitch, -10f, 2f);

        this.Cannon.yRot = headyaw * ((float) (Math.PI / -180f));
        this.Cannon.xRot = headpitch * ((float) (Math.PI / -180f));
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int colour) {
        Canon.render(poseStack, vertexConsumer, packedLight, packedOverlay, colour);
    }

    @Override
    public ModelPart root() {
        return Canon;
    }
}
