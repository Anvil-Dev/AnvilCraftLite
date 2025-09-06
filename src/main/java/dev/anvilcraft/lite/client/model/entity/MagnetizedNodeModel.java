package dev.anvilcraft.lite.client.model.entity;

import dev.anvilcraft.lite.client.renderer.entity.state.MagnetizedNodeRenderState;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class MagnetizedNodeModel extends EntityModel<MagnetizedNodeRenderState> {
    private final KeyframeAnimation idleAnimation;
    public static final AnimationDefinition ROTATING = AnimationDefinition.Builder.withLength(6f).looping().addAnimation(
        "rotating", new AnimationChannel(
            AnimationChannel.Targets.ROTATION,
            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 360f, 0f), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(4f, KeyframeAnimations.degreeVec(0f, 720f, 0f), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(6f, KeyframeAnimations.degreeVec(0f, 1080f, 0f), AnimationChannel.Interpolations.LINEAR)
        )
    ).addAnimation(
        "main", new AnimationChannel(
            AnimationChannel.Targets.ROTATION,
            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(3f, KeyframeAnimations.degreeVec(0f, -360f, 0f), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(6f, KeyframeAnimations.degreeVec(0f, -720f, 0f), AnimationChannel.Interpolations.LINEAR)
        )
    ).build();

    public MagnetizedNodeModel(ModelPart root) {
        super(root, RenderType::entityTranslucent);
        this.idleAnimation = MagnetizedNodeModel.ROTATING.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition rotating = partdefinition.addOrReplaceChild(
            "rotating",
            CubeListBuilder.create().texOffs(0, 4).addBox(-3.0F, -3.0F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 24.0F, 0.0F)
        );
        rotating.addOrReplaceChild(
            "cube_r1",
            CubeListBuilder.create().texOffs(0, 4).addBox(-3.0F, -3.0F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.0944F, 0.0F)
        );
        rotating.addOrReplaceChild(
            "cube_r2",
            CubeListBuilder.create().texOffs(0, 4).addBox(-3.0F, -3.0F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F)
        );
        partdefinition.addOrReplaceChild(
            "main",
            CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 24.0F, 0.0F)
        );
        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(MagnetizedNodeRenderState renderState) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.idleAnimation.apply(renderState.idle, renderState.ageInTicks);
    }
}