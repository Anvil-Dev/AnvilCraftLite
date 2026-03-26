package dev.anvilcraft.lite.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.client.init.ModModelLayers;
import dev.anvilcraft.lite.client.model.entity.MagnetizedNodeModel;
import dev.anvilcraft.lite.client.renderer.entity.state.MagnetizedNodeRenderState;
import dev.anvilcraft.lite.entity.MagnetizedNodeEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class MagnetizedNodeEntityRenderer extends EntityRenderer<MagnetizedNodeEntity, MagnetizedNodeRenderState> {
    public static final Identifier MAGNETIZED_NODE_TEXTURE = AnvilCraftLite.of("textures/entity/magnetized_node.png");

    private final MagnetizedNodeModel model;

    public MagnetizedNodeEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new MagnetizedNodeModel(context.bakeLayer(ModModelLayers.MAGNETIZED_NODE));
    }

    @Override
    public MagnetizedNodeRenderState createRenderState() {
        return new MagnetizedNodeRenderState();
    }

    @Override
    public void extractRenderState(MagnetizedNodeEntity entity, MagnetizedNodeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public void submit(
        MagnetizedNodeRenderState renderState,
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        CameraRenderState camera
    ) {
        poseStack.pushPose();
        poseStack.translate(0, -1.31f, 0);
        renderState.idle.start(0);
        this.model.setupAnim(renderState);
        // TODO
//        VertexConsumer consumer = bufferSource.getBuffer(this.model.renderType(MAGNETIZED_NODE_TEXTURE));
//        this.model.renderToBuffer(poseStack, consumer, renderState.lightCoords, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
