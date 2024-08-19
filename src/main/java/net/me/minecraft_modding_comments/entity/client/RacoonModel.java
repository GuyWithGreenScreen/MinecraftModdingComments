package net.me.minecraft_modding_comments.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.me.minecraft_modding_comments.entity.custom.Racoon;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class RacoonModel extends HierarchicalModel<Racoon> {
    private final ModelPart racoon;
    private final ModelPart head;

    public RacoonModel(ModelPart root) {
        this.racoon = root.getChild("racoon");
        this.head = racoon.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition racoon = partdefinition.addOrReplaceChild("racoon", CubeListBuilder.create(), PartPose.offset(-18.0F, 7.0F, 0.0F));

        PartDefinition body = racoon.addOrReplaceChild("body", CubeListBuilder.create().texOffs(30, 36).addBox(2.0F, 1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 36).addBox(7.0F, 7.0F, -6.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(32, 24).addBox(7.0F, 7.0F, 4.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 24).addBox(24.0F, 7.0F, -6.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(24.0F, 7.0F, 4.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(7.0F, -1.0F, -6.0F, 19.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(20, 39).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 34).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(36, 37).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 20).addBox(-1.0F, -2.0F, -1.0F, 13.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.7F, 2.0F, 0.0F, 0.0F, 0.0F, 0.5061F));

        PartDefinition head = racoon.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -7.0F, -4.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(40, 24).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 24).addBox(-2.0F, -0.8F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 27).addBox(-2.0F, -2.0F, 1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 36).addBox(-2.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 32).addBox(-4.0F, -4.0F, -1.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 39).addBox(-3.9F, -4.6F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 40).addBox(-4.0F, -4.0F, 0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 36).addBox(-4.0F, -3.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-2.0F, -5.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 0).addBox(-0.2F, -5.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 30).addBox(-0.2F, -5.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 2.0F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 36).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -6.0F, -3.0F, 0.733F, 0.0F, 0.0F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 39).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2F, -6.0F, 3.2F, -0.733F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Racoon racoon, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);
    }

    private void applyHeadRotation(float headyaw, float headpitch) {
        headyaw = Math.clamp(headyaw, 0f, 360f);
        headpitch = Math.clamp(headpitch, -25f, 5f);

        this.head.yRot = headyaw * ((float) (Math.PI / -180f));
        this.head.xRot = headpitch * ((float) (Math.PI / -180f));
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        racoon.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return racoon;
    }

}
