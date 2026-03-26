package dev.anvilcraft.lite.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.client.init.ModModelLayers;
import dev.anvilcraft.lite.client.model.entity.MagnetizedNodeModel;
import dev.anvilcraft.lite.client.renderer.entity.state.MagnetizedNodeRenderState;
import dev.anvilcraft.lite.entity.MagnetizedNodeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
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
    public void render(MagnetizedNodeRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0, -1.31f, 0);
        renderState.idle.start(0);
        this.model.setupAnim(renderState);
        VertexConsumer consumer = bufferSource.getBuffer(this.model.renderType(MAGNETIZED_NODE_TEXTURE));
        this.model.renderToBuffer(poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
